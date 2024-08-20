package net.smileycorp.rottenfiends.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.smileycorp.rottenfiends.common.Constants;

import java.io.File;

public class EntityConfig {
    
    public static EntityAttributesEntry excavatorZombie;
    public static double excavatorMiningRange;
    public static double excavatorMaxHardness;
    public static double excavatorMiningSpeed;
    
    public static void syncConfig(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(new File(event.getModConfigurationDirectory().getPath() + "/" + Constants.MODID + "/entities.cfg"));
        try{
            config.load();
            //excavator
            excavatorZombie = new EntityAttributesEntry(config, "Excavator Zombie", 0.13, 35, 6, 12, 1, 0, 0);
            excavatorMiningRange = config.get("Excavator Zombie", "miningRange", 20, "How close do excavator zombies need to be to their target to start mining blocks?").getDouble();
            excavatorMaxHardness = config.get("Excavator Zombie", "maxHardness", 10, "How much hardness can excavator zombies mine through?").getDouble();
            excavatorMiningSpeed = config.get("Excavator Zombie", "miningSpeed ", 3, "How quickly do excavator zombies mine?").getDouble();
        } catch(Exception e) {
        } finally {
            if (config.hasChanged()) config.save();
        }
    }
    
}
