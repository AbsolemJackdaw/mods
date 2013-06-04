package gravestone.bones;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBones extends ModelBase
{
	//fields
	ModelRenderer bone1;
	ModelRenderer bone2;
	ModelRenderer bone3;
	ModelRenderer bone4;
	ModelRenderer bone6;
	ModelRenderer bone5;
	ModelRenderer bone7;
	ModelRenderer bone8;
	ModelRenderer bone9;

	public ModelBones()
	{
		textureWidth = 64;
		textureHeight = 64;

		bone1 = new ModelRenderer(this, 0, 10);
		bone1.addBox(-1.5F, 2F, -2F, 4, 2, 1);
		bone1.setRotationPoint(-4F, 21-2F, 2F);
		bone1.setTextureSize(64, 32);
		bone1.mirror = true;
		setRotation(bone1, -0.2617994F, -0.4537856F, 0F);
		bone2 = new ModelRenderer(this, 0, 0);
		bone2.addBox(-2F, -2F, -2F, 5, 4, 6);
		bone2.setRotationPoint(-4F, 21-2F, 2F);
		bone2.setTextureSize(64, 32);
		bone2.mirror = true;
		setRotation(bone2, -0.2617994F, -0.4537856F, 0F);
		bone3 = new ModelRenderer(this, 0, 19);
		bone3.addBox(0F, 0F, 0F, 1, 1, 5);
		bone3.setRotationPoint(7F, 23-2F, -7F);
		bone3.setTextureSize(64, 32);
		bone3.mirror = true;
		setRotation(bone3, 0.3141593F, -0.837758F, 0F);
		bone4 = new ModelRenderer(this, 0, 25);
		bone4.addBox(0F, 0F, 0F, 1, 1, 5);
		bone4.setRotationPoint(3F, 23-2F, -5F);
		bone4.setTextureSize(64, 32);
		bone4.mirror = true;
		setRotation(bone4, 0F, 0.7504916F, 0F);
		bone6 = new ModelRenderer(this, 12, 10);
		bone6.addBox(-2F, 0F, 0F, 4, 1, 1);
		bone6.setRotationPoint(2F, 23-2F, 4F);
		bone6.setTextureSize(64, 32);
		bone6.mirror = true;
		setRotation(bone6, 0F, 1.134464F, 0F);
		bone5 = new ModelRenderer(this, 0, 15);
		bone5.addBox(2F, -1F, -1F, 2, 1, 1);
		bone5.setRotationPoint(-6F, 23-2F, -4F);
		bone5.setTextureSize(64, 32);
		bone5.mirror = true;
		setRotation(bone5, 0F, -0.4363323F, 0.2617994F);
		bone7 = new ModelRenderer(this, 6, 13);
		bone7.addBox(0F, 0F, -1F, 2, 1, 2);
		bone7.setRotationPoint(-6F, 23-2F, -4F);
		bone7.setTextureSize(64, 32);
		bone7.mirror = true;
		setRotation(bone7, 0F, 0F, -0.1570796F);
		bone8 = new ModelRenderer(this, 0, 13);
		bone8.addBox(2F, -1F, -1F, 2, 1, 1);
		bone8.setRotationPoint(-6F, 23-2F, -4F);
		bone8.setTextureSize(64, 32);
		bone8.mirror = true;
		setRotation(bone8, 0F, 0.122173F, 0.2617994F);
		bone9 = new ModelRenderer(this, 0, 17);
		bone9.addBox(1F, 0F, -1F, 2, 1, 1);
		bone9.setRotationPoint(-6F, 23-2F, -4F);
		bone9.setTextureSize(64, 32);
		bone9.mirror = true;
		setRotation(bone9, 0F, 0.6108652F, 0.1047198F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bone1.render(f5);
		bone2.render(f5);
		bone3.render(f5);
		bone4.render(f5);
		bone6.render(f5);
		bone5.render(f5);
		bone7.render(f5);
		bone8.render(f5);
		bone9.render(f5);
	}

	public void render(float f5)
	{
		bone1.render(f5);
		bone2.render(f5);
		bone3.render(f5);
		bone4.render(f5);
		bone6.render(f5);
		bone5.render(f5);
		bone7.render(f5);
		bone8.render(f5);
		bone9.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity ent)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5,ent);
	}

}
