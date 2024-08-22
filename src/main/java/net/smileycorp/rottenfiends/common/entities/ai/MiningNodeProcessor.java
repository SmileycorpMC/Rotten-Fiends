package net.smileycorp.rottenfiends.common.entities.ai;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class MiningNodeProcessor extends WalkNodeProcessor {
    
    private final EntityLiving entity;
    private final double maxHardness;
    
    public MiningNodeProcessor(EntityLiving entity, double maxHardness) {
        this.entity = entity;
        this.maxHardness = maxHardness;
        canEnterDoors = true;
        canOpenDoors = true;
    }
    
    protected PathNodeType getPathNodeTypeRaw(IBlockAccess world, int x, int y, int z) {
        PathNodeType type = super.getPathNodeTypeRaw(world, x, y, z);
        if ((type == PathNodeType.BLOCKED || type == PathNodeType.FENCE)) {
            BlockPos pos = new BlockPos(x, y, z);
            IBlockState state = world.getBlockState(pos);
            if (state.getBlockHardness(entity.world, pos) < maxHardness) return PathNodeType.DOOR_WOOD_CLOSED;
        }
        return type;
    }
    
}
