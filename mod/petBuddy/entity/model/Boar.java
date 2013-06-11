package petBuddy.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class Boar extends ModelBase
{
	//fields
	ModelRenderer Hornpart1L;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer FrontBody;
	ModelRenderer Nose;
	ModelRenderer Hornpart1R;

	public Boar()
	{
		textureWidth = 65;
		textureHeight = 64;

		Hornpart1L = new ModelRenderer(this, 18, 32);
		Hornpart1L.addBox(0F, 0F, -3F, 1, 1, 3);
		Hornpart1L.setRotationPoint(2F, 0F, 0F);
		Hornpart1L.setTextureSize(65, 64);
		Hornpart1L.mirror = true;
		setRotation(Hornpart1L, -0.1745329F, -0.2443461F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -7F, -8F, 8, 8, 8);
		head.setRotationPoint(0F, 12F, -6F);
		head.setTextureSize(65, 64);
		head.mirror = true;
		setRotation(head, 0.2094395F, 0F, 0F);
		body = new ModelRenderer(this, 28, 16);
		body.addBox(-5F, -7F, -7F, 10, 11, 8);
		body.setRotationPoint(0F, 10F, 7F);
		body.setTextureSize(65, 64);
		body.mirror = true;
		setRotation(body, 1.308997F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 20);
		leg1.addBox(-1.9F, 0F, -2F, 4, 8, 4);
		leg1.setRotationPoint(-3F, 16F, 7F);
		leg1.setTextureSize(65, 64);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 20);
		leg2.addBox(-2.1F, 0F, -2F, 4, 8, 4);
		leg2.setRotationPoint(3F, 16F, 7F);
		leg2.setTextureSize(65, 64);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 20);
		leg3.addBox(-2F, 0F, -2F, 4, 8, 4);
		leg3.setRotationPoint(-3F, 16F, -5F);
		leg3.setTextureSize(65, 64);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 20);
		leg4.addBox(-2F, 0F, -2F, 4, 8, 4);
		leg4.setRotationPoint(3F, 16F, -5F);
		leg4.setTextureSize(65, 64);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		FrontBody = new ModelRenderer(this, 0, 45);
		FrontBody.addBox(-4F, 0F, -4F, 11, 10, 9);
		FrontBody.setRotationPoint(-1.5F, 6F, -4F);
		FrontBody.setTextureSize(65, 64);
		FrontBody.mirror = true;
		setRotation(FrontBody, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 0, 36);
		Nose.addBox(-2F, -2F, -3F, 4, 3, 3);
		Nose.setRotationPoint(0F, -1F, -6.5F);
		Nose.setTextureSize(65, 64);
		Nose.mirror = true;
		setRotation(Nose, 0.0698132F, 0F, 0F);
		Hornpart1R = new ModelRenderer(this, 18, 36);
		Hornpart1R.addBox(0F, 0F, -3F, 1, 1, 3);
		Hornpart1R.setRotationPoint(-3F, 0F, 0F);
		Hornpart1R.setTextureSize(65, 64);
		Hornpart1R.mirror = true;
		setRotation(Hornpart1R, -0.1745329F, 0.2443461F, 0F);

				Nose.addChild(Hornpart1R);
				Nose.addChild(Hornpart1L);
//				FrontBody.addChild(body);
				head.addChild(Nose);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		FrontBody.render(f5);
//		Nose.render(f5);
//		Hornpart1L.render(f5);
//		Hornpart1R.render(f5);
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
