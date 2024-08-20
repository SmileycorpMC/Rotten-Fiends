package net.smileycorp.rottenfiends.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.smileycorp.rottenfiends.config.EntityConfig;

public class CommonProxy {
    
    public void preInit(FMLPreInitializationEvent event) {
        EntityConfig.syncConfig(event);
    }
    
    public void init(FMLInitializationEvent event) {}
    
    public void postInit(FMLPostInitializationEvent event) {}
    
}
