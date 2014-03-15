package threeDitems.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class brod extends ModelBase
{
	//fields
	ModelRenderer Shape1;

	public brod()
	{
		textureWidth = 4;
		textureHeight = 12;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, -6F, -1F, 1, 11, 1);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(4, 12);
		Shape1.mirror = true;
		setRotation(Shape1, -1.570796F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
