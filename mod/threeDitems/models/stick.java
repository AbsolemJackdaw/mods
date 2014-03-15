package threeDitems.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class stick extends ModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;

	public stick()
	{
		textureWidth = 4;
		textureHeight = 7;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, -3F, 0F, 1, 6, 1);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(4, 7);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, -2F, 0F, 1, 2, 1);
		Shape2.setRotationPoint(0F, -3F, 0F);
		Shape2.setTextureSize(4, 7);
		Shape2.mirror = true;
		setRotation(Shape2, -0.6422812F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(0F, -7F, 1F, 1, 3, 1);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(4, 7);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
