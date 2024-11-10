package net.smileycorp.rottenfiends.common.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.smileycorp.rottenfiends.common.entities.IMiningMob;

public class EntityAIMineBlock  extends EntityAIBase {
    
    private static final float PI_OVER_4 =  0.785398f;
    private static final float PI = 3.141592f;
    protected final EntityLiving entity;
    protected final double miningRange, maxHardness, miningSpeed, reach;
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
        setMutexBits(2);
    }
    
    @Override
    public void resetTask() {
        entity.world.sendBlockBreakProgress(entity.getEntityId(), target, -1);
        target = BlockPos.ORIGIN;
        state = null;
        totalBreakingTime = 0;
        breakingTime = 0;
        previousBreakProgress = -1;
        entity.getNavigator().tryMoveToEntityLiving(entity.getAttackTarget(), 1);
        if (entity instanceof IMiningMob) ((IMiningMob) entity).setMining(false);
    }
    
    public void updateTask() {
        super.updateTask();
        if (entity.getRNG().nextInt(20) == 0) {
            entity.getLookHelper().setLookPosition(target.getX() + 0.5f, target.getY() + 0.5f, target.getZ() + 0.5f,
                    180, 20);
            SoundType type = state.getBlock().getSoundType(state, entity.world, target, entity);
            entity.playSound(type.getHitSound(), (type.getVolume() + 1) / 8f, type.getPitch() * 0.5f);
        }
        breakingTime++;
        int i = (int)((float)breakingTime / totalBreakingTime * 10.0F);
        if (i != previousBreakProgress) {
            entity.world.sendBlockBreakProgress(entity.getEntityId(),target, i);
            previousBreakProgress = i;
        }
        if (breakingTime >= totalBreakingTime) {
            entity.world.setBlockToAir(target);
            entity.world.playEvent(2001, target, Block.getStateId(state));
            resetTask();
        }
    }
    
    @Override
    public boolean shouldExecute() {
        if (entity.getAttackTarget() == null) return false;
        Entity attackTarget = entity.getAttackTarget();
        double distance = entity.getDistanceSq(attackTarget);
        if (distance > miningRange * miningRange || distance < 2.25) return false;
        Path path = entity.getNavigator().getPath();
        if (path == null || path.isFinished()) {
            double scale = Math.min(Math.sqrt(distance), miningRange);
            RayTraceResult ray = rayTrace(new Vec3d(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ),
                    new Vec3d(attackTarget.posX, attackTarget.posY + attackTarget.getEyeHeight(), attackTarget.posZ), scale);
            if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
                target = ray.getBlockPos();
                if (canMine()) return true;
            }
            ray = rayTrace(entity.getPositionVector(), attackTarget.getPositionVector(), scale);
            if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
                target = ray.getBlockPos();
                if (canMine()) return true;
            }
            return false;
        }
        return false;
    }
    
    private boolean canMine() {
        if (entity.getDistanceSq(target) > reach * reach) return false;
        state = entity.world.getBlockState(target);
        float hardness = state.getBlockHardness(entity.world, target);
        if (hardness < maxHardness) {
            if (entity instanceof IMiningMob) ((IMiningMob) entity).setMining(true);
            totalBreakingTime = (int) (1f / ((miningSpeed + 1) / hardness / 90f));
            entity.getLookHelper().setLookPosition(target.getX() + 0.5f, target.getY() + 0.5f, target.getZ() + 0.5f,
                    180, 20);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean shouldContinueExecuting() {
        if (state == null) return false;
        EntityLivingBase attackTarget = entity.getAttackTarget();
        if (attackTarget == null) return false;
        if (entity.getDistanceSq(attackTarget) > miningRange * miningRange) return false;
        if (entity.getDistanceSq(target) > reach * reach) return false;
        if (entity.world.getBlockState(target) != state) return false;
        Vec3d start = new Vec3d(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
        Vec3d dir1 = getDirectionVec(start,
                new Vec3d(attackTarget.posX, attackTarget.posY + attackTarget.getEyeHeight(), attackTarget.posZ));
        Vec3d dir2 = getDirectionVec(start, new Vec3d(target.getX() + 0.5f, target.getY() + 0.5f, target.getZ() + 0.5f));
        if (Math.abs(dir1.x - dir2.x) > PI_OVER_4) return false;
        if (Math.abs(dir1.z - dir2.z) > PI_OVER_4) return false;
        return Math.abs(dir1.y - dir2.y) <= PI;
    }
    
    private RayTraceResult rayTrace(Vec3d start, Vec3d end, double scale) {
        return entity.world.rayTraceBlocks(start, start.add(getDirectionVec(start, end).scale(scale)), false, true, false);
    }
    
    public static Vec3d getDirectionVec(Vec3d startpos, Vec3d endpos) {
        if (startpos.equals(endpos)) return new Vec3d(0,0,0);
        double dx = endpos.x - startpos.x;
        double dy = endpos.y - startpos.y;
        double dz = endpos.z - startpos.z;
        double magnitude = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));
        return new Vec3d(dx / magnitude, dy / magnitude, dz / magnitude);
    }
    
}
