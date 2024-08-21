package net.smileycorp.rottenfiends.client.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.smileycorp.rottenfiends.client.entity.model.ModelExcavatorZombie;
import net.smileycorp.rottenfiends.common.Constants;
import net.smileycorp.rottenfiends.common.entities.EntityExcavatorZombie;

import javax.annotation.Nullable;

public class RenderExcavatorZombie extends RenderLiving<EntityExcavatorZombie> {
    
    public RenderExcavatorZombie(RenderManager rm) {
        super(rm, new ModelExcavatorZombie(0f, false), 0.5f);
        addLayer(new LayerBipedArmor(this) {
            protected void initArmor() {
                modelLeggings = new ModelExcavatorZombie(0.5F, true);
                modelArmor = new ModelExcavatorZombie(1.0F, true);
            }
        });
    }
    
    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityExcavatorZombie entity) {
        return Constants.loc("textures/entities/excavator_zombie.png");
    }
    
}
