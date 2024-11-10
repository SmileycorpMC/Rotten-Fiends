package net.smileycorp.rottenfiends.common.entities;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.smileycorp.rottenfiends.common.entities.ai.EntityAIMineBlock;
import net.smileycorp.rottenfiends.common.entities.ai.PathNavigateMining;
import net.smileycorp.rottenfiends.config.EntityConfig;

import javax.annotation.Nullable;

public class EntityExcavatorZombie extends EntityZombie implements IMiningMob {
    
    private boolean mining;
    
    public EntityExcavatorZombie(World world) {
        super(world);
        navigator = new PathNavigateMining(this, world, EntityConfig.excavatorMaxHardness);
    }
    
    @Override
    protected void updateAITasks() {
        if (mining && ticksExisted % 3 == 0) super.setArmsRaised(!isArmsRaised());
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
    
    @Override
    protected void applyEntityAI() {
        tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPigZombie.class));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
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
    public void setArmsRaised(boolean armsRaised) {}
    
    @Override
    public void setMining(boolean mining) {
        if (!mining) super.setArmsRaised(false);
        this.mining = mining;
    }
    
}
