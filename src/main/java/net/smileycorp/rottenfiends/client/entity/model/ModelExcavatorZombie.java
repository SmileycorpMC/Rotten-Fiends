package net.smileycorp.rottenfiends.client.entity.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;

public class ModelExcavatorZombie extends ModelZombie {
    
    public ModelExcavatorZombie(float size, boolean armour) {
        super(size, armour);
        bipedHeadwear.showModel = false;
        
        bipedHead = new ModelRenderer(this);
        bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
        bipedHead.cubeList.add(new ModelBox(bipedHead, 32, 12, 1.0F, -3.5F, -7.0F, 2, 0, 3, 0.0F, false));
        
        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.cubeList.add(new ModelBox(bipedBody, 0, 16, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false));
        
        bipedRightArm = new ModelRenderer(this);
        bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        setRotationAngle(bipedRightArm, -1.5708F, 0.0F, 0.0F);
        bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 0, 32, -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false));
        
        ModelRenderer rightClaw2_r1 = new ModelRenderer(this);
        rightClaw2_r1.setRotationPoint(-1.0F, 7.0F, 2.0F);
        bipedRightArm.addChild(rightClaw2_r1);
        setRotationAngle(rightClaw2_r1, -2.9666F, -0.0334F, 3.1135F);
        rightClaw2_r1.cubeList.add(new ModelBox(rightClaw2_r1, 24, 9, 0.0237F, -1.607F, -3.5547F, 0, 12, 7, 0.0F, false));
        
        ModelRenderer rightClaw1_r1 = new ModelRenderer(this);
        rightClaw1_r1.setRotationPoint(-2.0F, 7.0F, -2.0F);
        bipedRightArm.addChild(rightClaw1_r1);
        setRotationAngle(rightClaw1_r1, 0.0F, 0.4363F, 0.0F);
        rightClaw1_r1.cubeList.add(new ModelBox(rightClaw1_r1, 24, 9, 0.0F, 0.0F, -6.0F, 0, 12, 7, 0.0F, false));
        
        ModelRenderer rightClaw_r1 = new ModelRenderer(this);
        rightClaw_r1.setRotationPoint(0.0F, 7.0F, -2.0F);
        bipedRightArm.addChild(rightClaw_r1);
        setRotationAngle(rightClaw_r1, 0.0F, -0.4363F, 0.0F);
        rightClaw_r1.cubeList.add(new ModelBox(rightClaw_r1, 24, 9, 0.0F, 0.0F, -6.0F, 0, 12, 7, 0.0F, false));
        
        bipedLeftArm = new ModelRenderer(this);
        bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        setRotationAngle(bipedLeftArm, -1.5708F, 0.0F, 0.0F);
        bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 0, 32, -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false));
        
        ModelRenderer leftClaw3_r1 = new ModelRenderer(this);
        leftClaw3_r1.setRotationPoint(-11.0F, 7.0F, 2.0F);
        bipedLeftArm.addChild(leftClaw3_r1);
        setRotationAngle(leftClaw3_r1, -2.9666F, -0.0334F, 3.1135F);
        leftClaw3_r1.cubeList.add(new ModelBox(leftClaw3_r1, 24, 9, -11.9649F, -1.3453F, -4.008F, 0, 12, 7, 0.0F, false));
        
        ModelRenderer leftClaw2_r1 = new ModelRenderer(this);
        leftClaw2_r1.setRotationPoint(0.0F, 7.0F, -2.0F);
        bipedLeftArm.addChild(leftClaw2_r1);
        setRotationAngle(leftClaw2_r1, 0.0F, 0.4363F, 0.0F);
        leftClaw2_r1.cubeList.add(new ModelBox(leftClaw2_r1, 24, 9, 0.0F, 0.0F, -6.0F, 0, 12, 7, 0.0F, false));
        
        ModelRenderer leftClaw_r1 = new ModelRenderer(this);
        leftClaw_r1.setRotationPoint(2.0F, 7.0F, -2.0F);
        bipedLeftArm.addChild(leftClaw_r1);
        setRotationAngle(leftClaw_r1, 0.0F, -0.4363F, 0.0F);
        leftClaw_r1.cubeList.add(new ModelBox(leftClaw_r1, 24, 9, 0.0F, 0.0F, -6.0F, 0, 12, 7, 0.0F, false));
        
        bipedRightLeg = new ModelRenderer(this);
        bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 16, 32, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));
        
        bipedLeftLeg = new ModelRenderer(this);
        bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        bipedLeftLeg.cubeList.add(new ModelBox(bipedLeftLeg, 24, 0, -1.8F, 0.0F, -2.0F, 4, 4, 4, 0.0F, false));
        
        ModelRenderer leftLeg2_r1 = new ModelRenderer(this);
        leftLeg2_r1.setRotationPoint(0.1F, 5.5539F, -3.0045F);
        bipedLeftLeg.addChild(leftLeg2_r1);
        leftLeg2_r1.cubeList.add(new ModelBox(leftLeg2_r1, 33, 38, -1.0F, -6.5539F, 2.0F, 2, 8, 2, 0.0F, false));
        
        ModelRenderer leftLeg_r1 = new ModelRenderer(this);
        leftLeg_r1.setRotationPoint(0.1F, 9.5539F, 1.9955F);
        bipedLeftLeg.addChild(leftLeg_r1);
        leftLeg_r1.cubeList.add(new ModelBox(leftLeg_r1, 32, 5, 0.0F, -4.5539F, -3.0F, 0, 7, 3, 0.0F, false));
    }
    
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
}
