package net.smileycorp.rottenfiends.common.entities;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import net.smileycorp.rottenfiends.config.EntityConfig;

public class EntityExcavatorZombie extends EntityZombie {
    
    public EntityExcavatorZombie(World world) {
        super(world);
    }
    
    @Override
    protected void applyEntityAttributes() {
       super.applyEntityAttributes();
        EntityConfig.excavatorZombie.applyAttributes(this);
    }
    
}
