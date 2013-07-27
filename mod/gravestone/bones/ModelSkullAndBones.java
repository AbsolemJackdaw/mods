package gravestone.bones;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSkullAndBones extends ModelBase
{
	//fields
	ModelRenderer bone1;

	public ModelSkullAndBones()
	{
		textureWidth = 16;
		textureHeight = 16;

		bone1 = new ModelRenderer(this, 0, 0);
		bone1.addBox(-8F, -16+24+14F, -8F, 16, 2, 16);
		bone1.setRotationPoint(0F, 0F, 0F);
		bone1.setTextureSize(16, 16);
		bone1.mirror = true;
		setRotation(bone1, 0f, 0f, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bone1.render(f5);
	}

	public void render(float f5)
	{
		bone1.render(f5);
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