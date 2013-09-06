package threeDitems.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class axe extends ModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;

	public axe()
	{
		textureWidth = 25;
		textureHeight = 11;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, -3F, 1, 1, 10);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(25, 11);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, -1F, 7F, 1, 4, 3);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(25, 11);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(0F, -2F, 8F, 1, 1, 2);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(25, 11);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 13, 0);
		Shape4.addBox(0F, 3F, 5.5F, 1, 1, 5);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(25, 11);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 13, 0);
		Shape5.addBox(0F, 0F, -1.5F, 1, 1, 5);
		Shape5.setRotationPoint(0.5F, 3.3F, 7F);
		Shape5.setTextureSize(25, 11);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0.7853982F);
		Shape6 = new ModelRenderer(this, 17, 3);
		Shape6.addBox(0F, 0F, -1.5F, 0, 1, 2);
		Shape6.setRotationPoint(0.5F, 2F, 6.7F);
		Shape6.setTextureSize(25, 11);
		Shape6.mirror = true;
		setRotation(Shape6, 0.7853982F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5,entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
