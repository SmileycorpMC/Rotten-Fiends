package net.smileycorp.rottenfiends.common.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.World;

public class PathNavigateMining extends PathNavigateGround {
    
    private final double maxHardness;
    
    public PathNavigateMining(EntityLiving entity, World world, double maxHardness) {
        super(entity, world);
        this.maxHardness = maxHardness;
        setBreakDoors(true);
    }
    
    protected PathFinder getPathFinder() {
        nodeProcessor = new MiningNodeProcessor(entity, maxHardness);
        return new PathFinder(nodeProcessor);
    }
    
}
