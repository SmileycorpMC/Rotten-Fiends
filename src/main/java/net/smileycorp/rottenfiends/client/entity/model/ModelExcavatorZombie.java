package net.smileycorp.rottenfiends.client.entity.model;

import net.minecraft.util.ResourceLocation;
import net.smileycorp.rottenfiends.common.Constants;
import net.smileycorp.rottenfiends.common.entities.EntityExcavatorZombie;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelExcavatorZombie extends AnimatedGeoModel<EntityExcavatorZombie> {
    
    @Override
    public ResourceLocation getModelLocation(EntityExcavatorZombie entity) {
        return Constants.loc("geo/excavator_zombie.geo.json");
    }
    
    @Override
    public ResourceLocation getTextureLocation(EntityExcavatorZombie entity) {
        return Constants.loc("textures/entities/excavator_zombie.png");
    }
    
    @Override
    public ResourceLocation getAnimationFileLocation(EntityExcavatorZombie entity) {
        return Constants.loc("animations/excavator_zombie.animation.json");
    }
    
}
