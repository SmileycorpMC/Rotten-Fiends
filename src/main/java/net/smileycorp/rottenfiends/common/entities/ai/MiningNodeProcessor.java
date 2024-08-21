package net.smileycorp.rottenfiends.common.entities.ai;

import net.minecraft.block.state.IBlockState;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MiningNodeProcessor extends WalkNodeProcessor {
    
    private final double maxHardness;
    
    public MiningNodeProcessor(double maxHardness) {
        this.maxHardness = maxHardness;
        canEnterDoors = true;
        canOpenDoors = true;
    }
    
    protected PathNodeType getPathNodeTypeRaw(IBlockAccess world, int x, int y, int z) {
        PathNodeType type = super.getPathNodeTypeRaw(world, x, y, z);
        if ((type == PathNodeType.BLOCKED || type == PathNodeType.FENCE) && world instanceof World) {
            BlockPos pos = new BlockPos(x, y, z);
            IBlockState state = world.getBlockState(pos);
            if (state.getBlockHardness((World) world, pos) < maxHardness) return PathNodeType.DOOR_WOOD_CLOSED;
        }
        return type;
    }
    
}
