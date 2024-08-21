package net.smileycorp.rottenfiends.common.entities;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.smileycorp.rottenfiends.common.entities.ai.EntityAIMineBlock;
import net.smileycorp.rottenfiends.config.EntityConfig;

import javax.annotation.Nullable;

public class EntityExcavatorZombie extends EntityZombie implements IMiningMob {
    
    private boolean mining;
    
    public EntityExcavatorZombie(World world) {
        super(world);
    }
    
    @Override
    protected void updateAITasks() {
        if (mining && ticksExisted % 10 == 0) setArmsRaised(!isArmsRaised());
    }
    
    @Override
    protected void applyEntityAttributes() {
       super.applyEntityAttributes();
        EntityConfig.excavatorZombie.applyAttributes(this);
    }
    
    @Override
    protected void initEntityAI() {
        tasks.addTask(1, new EntityAIMineBlock(this, EntityConfig.excavatorMiningRange, EntityConfig.excavatorMaxHardness,
                EntityConfig.excavatorMiningSpeed, EntityConfig.excavatorReach));
        super.initEntityAI();
    }
    
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        return livingdata;
    }
    
    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slot, ItemStack stack) {
        if (slot.getSlotType() == EntityEquipmentSlot.Type.HAND || slot == EntityEquipmentSlot.FEET) return;
        super.setItemStackToSlot(slot, stack);
    }
    
    @Override
    protected boolean canEquipItem(ItemStack stack) {
        EntityEquipmentSlot slot = getSlotForItemStack(stack);
        return (slot.getSlotType() == EntityEquipmentSlot.Type.HAND || slot == EntityEquipmentSlot.FEET) ? false : super.canEquipItem(stack);
    }
    
    @Override
    public void setMining(boolean mining) {
        if (!mining) setArmsRaised(false);
        this.mining = mining;
    }
    
}
