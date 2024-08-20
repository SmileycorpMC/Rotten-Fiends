package net.smileycorp.rottenfiends.client;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.smileycorp.rottenfiends.client.entity.RenderExcavatorZombie;
import net.smileycorp.rottenfiends.common.CommonProxy;
import net.smileycorp.rottenfiends.common.Constants;
import net.smileycorp.rottenfiends.common.entities.EntityExcavatorZombie;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid= Constants.MODID)
public class ClientProxy extends CommonProxy {
    
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityExcavatorZombie.class, RenderExcavatorZombie::new);
    }
    
}
