package net.smileycorp.rottenfiends.common.entities;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import net.smileycorp.rottenfiends.config.EntityConfig;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class EntityExcavatorZombie extends EntityZombie implements IAnimatable {
    
    private final AnimationFactory factory = new AnimationFactory(this);
    
    public EntityExcavatorZombie(World world) {
        super(world);
    }
    
    @Override
    protected void applyEntityAttributes() {
       super.applyEntityAttributes();
        EntityConfig.excavatorZombie.applyAttributes(this);
    }
    
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
    
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "excavator_zombie", 5, this::controller));
    }
    
    private PlayState controller(AnimationEvent<EntityExcavatorZombie> event) {
        return PlayState.CONTINUE;
    }
    
}
