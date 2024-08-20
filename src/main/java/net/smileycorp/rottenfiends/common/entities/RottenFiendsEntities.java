package net.smileycorp.rottenfiends.common.entities;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.registries.IForgeRegistry;
import net.smileycorp.rottenfiends.common.Constants;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class RottenFiendsEntities {

    
    
    private static int ID = 187;
    
    public static final EntityEntry EXCAVATOR_ZOMBIE = EntityEntryBuilder.create().entity(EntityExcavatorZombie.class).id(Constants.loc("excavator_zombie"), ID)
            .name(Constants.name("ExcavatorZombie")).egg(0x445040, 0x058370).tracker(64, 3, true).build();
    
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        IForgeRegistry<EntityEntry> registry = event.getRegistry();
        registry.register(EXCAVATOR_ZOMBIE);
    }

}
