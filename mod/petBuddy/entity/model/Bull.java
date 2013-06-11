
package petBuddy.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class Bull extends ModelBase
{
	//fields
	ModelRenderer Nose;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer HornR1;
	ModelRenderer HornR2;
	ModelRenderer HornR3;
	ModelRenderer HornL1;
	ModelRenderer HornL2;
	ModelRenderer HornL3;
	ModelRenderer BullBody;

	public Bull()
	{
		textureWidth = 65;
		textureHeight = 64;

		Nose = new ModelRenderer(this, 39, 0);
		Nose.addBox(0F, 0F, 0F, 6, 4, 2);
		Nose.setRotationPoint(-3F, -1F, -8F);
		Nose.setTextureSize(65, 64);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -4F, -7F, 8, 8, 7);
		head.setRotationPoint(0F, 3F, -7F);
		head.setTextureSize(65, 64);
		head.mirror = true;
		setRotation(head, 0.2094395F, 0F, 0F);
		body = new ModelRenderer(this, 20, 12);
		body.addBox(-6F, -6F, -7F, 12, 10, 10);
		body.setRotationPoint(0F, 6F, 7F);
		body.setTextureSize(65, 64);
		body.mirror = true;
		setRotation(body, 1.343904F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-2.9F, 0F, -2F, 4, 12, 4);
		leg1.setRotationPoint(-3F, 12F, 7F);
		leg1.setTextureSize(65, 64);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1.1F, 0F, -2F, 4, 12, 4);
		leg2.setRotationPoint(3F, 12F, 7F);
		leg2.setTextureSize(65, 64);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-3F, 0F, -3F, 4, 12, 4);
		leg3.setRotationPoint(-3F, 12F, -5F);
		leg3.setTextureSize(65, 64);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1F, 0F, -3F, 4, 12, 4);
		leg4.setRotationPoint(3F, 12F, -5F);
		leg4.setTextureSize(65, 64);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		HornR1 = new ModelRenderer(this, 23, 0);
		HornR1.addBox(-1F, -1F, -1F, 2, 2, 2);
		HornR1.setRotationPoint(-3.5F, -3.5F, -2F);
		HornR1.setTextureSize(65, 64);
		HornR1.mirror = true;
		setRotation(HornR1, 0F, 0F, 0F);
		HornR2 = new ModelRenderer(this, 31, 0);
		HornR2.addBox(-1.5F, -3F, -0.5F, 1, 3, 1);
		HornR2.setRotationPoint(0F, 0F, 0F);
		HornR2.setTextureSize(65, 64);
		HornR2.mirror = true;
		setRotation(HornR2, 0F, 0F, -0.4363323F);
		HornR3 = new ModelRenderer(this, 35, 0);
		HornR3.addBox(-2.7F, -3.9F, 0.3F, 1, 2, 1);
		HornR3.setRotationPoint(-0F, 0F, 0F);
		HornR3.setTextureSize(65, 64);
		HornR3.mirror = true;
		setRotation(HornR3, 0.3665191F, 0F, 0F);
		HornL1 = new ModelRenderer(this, 23, 0);
		HornL1.addBox(-1F, -1F, -1F, 2, 2, 2);
		HornL1.setRotationPoint(3.5F, -3.5F, -2F);
		HornL1.setTextureSize(65, 64);
		HornL1.mirror = true;
		setRotation(HornL1, 0F, 0F, 0F);
		HornL2 = new ModelRenderer(this, 31, 0);
		HornL2.addBox(0.5F, -3F, -0.5F, 1, 3, 1);
		HornL2.setRotationPoint(0F, 0F, 0F);
		HornL2.setTextureSize(65, 64);
		HornL2.mirror = true;
		setRotation(HornL2, 0F, 0F, 0.4363323F);
		HornL3 = new ModelRenderer(this, 35, 0);
		HornL3.addBox(1.7F, -3.9F, 0.3F, 1, 2, 1);
		HornL3.setRotationPoint(0F, 0F, 0F);
		HornL3.setTextureSize(65, 64);
		HornL3.mirror = true;
		setRotation(HornL3, 0.3665191F, 0F, 0F);
		BullBody = new ModelRenderer(this, 0, 32);
		BullBody.addBox(-6F, -5F, -3F, 13, 11, 10);
		BullBody.setRotationPoint(-0.5F, 6F, -5F);
		BullBody.setTextureSize(65, 64);
		BullBody.mirror = true;
		setRotation(BullBody, 0F, 0F, 0F);

//		BullBody.addChild(body);
		HornR1.addChild(HornR2);
		HornR1.addChild(HornR3);
		HornL1.addChild(HornL2);
		HornL1.addChild(HornL3);
		head.addChild(HornR1);
		head.addChild(HornL1);
		head.addChild(Nose);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		//Nose.render(f5);
		head.render(f5);
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		//		HornR1.render(f5);
		//    HornR2.render(f5);
		//    HornR3.render(f5);
		//		HornL1.render(f5);
		//		HornL2.render(f5);
		//		HornL3.render(f5);
		BullBody.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	}

}
