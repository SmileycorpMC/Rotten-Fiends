package net.smileycorp.rottenfiends.common.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.smileycorp.rottenfiends.common.entities.IMiningMob;

public class EntityAIMineBlock  extends EntityAIBase {
    
    protected final EntityLiving entity;
    protected final double miningRange, maxHardness, miningSpeed, reach;
    protected final PathNavigate navigation;
    protected BlockPos target = BlockPos.ORIGIN;
    protected IBlockState state = null;
    private int totalBreakingTime;
    private int breakingTime;
    private int previousBreakProgress = -1;
    
    public EntityAIMineBlock(EntityLiving entity, double miningRange, double maxHardness, double miningSpeed, double reach) {
        this.entity = entity;
        this.miningRange = miningRange;
        this.maxHardness = maxHardness;
        this.miningSpeed = miningSpeed;
        this.reach = reach;
        navigation = entity.getNavigator();
    }
    
    @Override
    public void resetTask() {
        target = BlockPos.ORIGIN;
        state = null;
        totalBreakingTime = 0;
        breakingTime = 0;
        previousBreakProgress = -1;
        if (entity instanceof IMiningMob) ((IMiningMob) entity).setMining(false);
    }
    
    public void updateTask() {
        super.updateTask();
        if (entity.getRNG().nextInt(20) == 0) {
            SoundType type = state.getBlock().getSoundType(state, entity.world, target, entity);
            entity.playSound(type.getHitSound(), (type.getVolume() + 1) / 8f, type.getPitch() * 0.5f);
        }
        breakingTime ++;
        int i = (int)((float)breakingTime / totalBreakingTime * 10.0F);
        if (i != previousBreakProgress) {
            entity.world.sendBlockBreakProgress(entity.getEntityId(),target, i);
            previousBreakProgress = i;
        }
        if (breakingTime >= totalBreakingTime) {
            entity.world.setBlockToAir(target);
            entity.world.playEvent(1021, target, 0);
            entity.world.playEvent(2001, target, Block.getStateId(state));
            resetTask();
        }
    }
    
    @Override
    public boolean shouldExecute() {
        if (entity.getAttackTarget() == null) return false;
        if (entity.getDistanceSq(entity.getAttackTarget()) > miningRange * miningRange) return false;
        Path path = navigation.getPath();
        for (int i = 0; i < Math.min(path.getCurrentPathIndex() + 2, path.getCurrentPathLength()); ++i) {
            PathPoint pathpoint = path.getPathPointFromIndex(i);
            if (pathpoint.nodeType != PathNodeType.DOOR_WOOD_CLOSED) continue;
            target = new BlockPos(pathpoint.x, pathpoint.y, pathpoint.z);
            if (entity.getDistanceSq(target.getX(), entity.posY, target.getZ()) <= 2.25) {
                state = entity.world.getBlockState(target);
                float hardness = state.getBlockHardness(entity.world, target);
                if (state != null && hardness < maxHardness) {
                    if (entity instanceof IMiningMob) ((IMiningMob) entity).setMining(true);
                    totalBreakingTime = (int)((miningSpeed + 1) / hardness / 90f);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean shouldContinueExecuting() {
        if (state == null) return false;
        if (entity.getAttackTarget() == null) return false;
        if (entity.getDistanceSq(entity.getAttackTarget()) > miningRange * miningRange) return false;
        if (entity.getDistanceSq(target) > reach * reach) return false;
        return entity.world.getBlockState(target) == (state);
    }
    
}
