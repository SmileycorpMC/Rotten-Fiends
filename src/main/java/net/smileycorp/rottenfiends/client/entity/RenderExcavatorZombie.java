package net.smileycorp.rottenfiends.client.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.smileycorp.rottenfiends.client.entity.model.ModelExcavatorZombie;
import net.smileycorp.rottenfiends.common.entities.EntityExcavatorZombie;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderExcavatorZombie extends GeoEntityRenderer<EntityExcavatorZombie> {
    
    public RenderExcavatorZombie(RenderManager rm) {
        super(rm, new ModelExcavatorZombie());
    }
    
}
