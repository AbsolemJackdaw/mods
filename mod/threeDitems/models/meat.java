package threeDitems.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class meat extends ModelBase
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;

	public meat()
	{
		this.textureHeight = 11;
		this.textureWidth = 28;

		this.Shape1 = new ModelRenderer(this, 0, 2);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape1.setTextureSize(28, 11);
		this.Shape1.mirror = true;
		setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		this.Shape2 = new ModelRenderer(this, 0, 2);
		this.Shape2.addBox(2.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape2.setTextureSize(28, 11);
		this.Shape2.mirror = true;
		setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		this.Shape3 = new ModelRenderer(this, 0, 2);
		this.Shape3.addBox(1.0F, 0.0F, 1.0F, 1, 1, 1);
		this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape3.setTextureSize(28, 11);
		this.Shape3.mirror = true;
		setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
		this.Shape4 = new ModelRenderer(this, 0, 2);
		this.Shape4.addBox(1.0F, 0.0F, -1.0F, 1, 1, 1);
		this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape4.setTextureSize(28, 11);
		this.Shape4.mirror = true;
		setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
		this.Shape5 = new ModelRenderer(this, 0, 0);
		this.Shape5.addBox(-1.0F, 0.0F, -1.0F, 5, 1, 4);
		this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape5.setTextureSize(28, 11);
		this.Shape5.mirror = true;
		setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
		this.Shape6 = new ModelRenderer(this, 18, 0);
		this.Shape6.addBox(-1.0F, 0.0F, -2.0F, 4, 1, 1);
		this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape6.setTextureSize(28, 11);
		this.Shape6.mirror = true;
		setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
		this.Shape7 = new ModelRenderer(this, 0, 5);
		this.Shape7.addBox(0.0F, 0.0F, -5.0F, 3, 1, 3);
		this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape7.setTextureSize(28, 11);
		this.Shape7.mirror = true;
		setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
		this.Shape8 = new ModelRenderer(this, 2, 6);
		this.Shape8.addBox(0.0F, 0.0F, -7.0F, 2, 1, 2);
		this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape8.setTextureSize(28, 11);
		this.Shape8.mirror = true;
		setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
		this.Shape9 = new ModelRenderer(this, 12, 5);
		this.Shape9.addBox(-2.0F, 0.0F, -2.0F, 1, 1, 5);
		this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape9.setTextureSize(28, 11);
		this.Shape9.mirror = true;
		setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
		this.Shape10 = new ModelRenderer(this, 13, 6);
		this.Shape10.addBox(4.0F, 0.0F, -1.0F, 1, 1, 4);
		this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape10.setTextureSize(28, 11);
		this.Shape10.mirror = true;
		setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
		this.Shape11 = new ModelRenderer(this, 12, 9);
		this.Shape11.addBox(-1.0F, 0.0F, 3.0F, 5, 1, 1);
		this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape11.setTextureSize(28, 11);
		this.Shape11.mirror = true;
		setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
		this.Shape12 = new ModelRenderer(this, 13, 6);
		this.Shape12.addBox(3.0F, 0.0F, -5.0F, 1, 1, 4);
		this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape12.setTextureSize(28, 11);
		this.Shape12.mirror = true;
		setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
		this.Shape13 = new ModelRenderer(this, 15, 8);
		this.Shape13.addBox(2.0F, 0.0F, -7.0F, 1, 1, 2);
		this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape13.setTextureSize(28, 11);
		this.Shape13.mirror = true;
		setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
		this.Shape14 = new ModelRenderer(this, 12, 5);
		this.Shape14.addBox(-1.0F, 0.0F, -7.0F, 1, 1, 5);
		this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape14.setTextureSize(28, 11);
		this.Shape14.mirror = true;
		setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
		this.Shape15 = new ModelRenderer(this, 15, 9);
		this.Shape15.addBox(0.0F, 0.0F, -8.0F, 2, 1, 1);
		this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape15.setTextureSize(28, 11);
		this.Shape15.mirror = true;
		setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Shape1.render(f5);
		this.Shape2.render(f5);
		this.Shape3.render(f5);
		this.Shape4.render(f5);
		this.Shape5.render(f5);
		this.Shape6.render(f5);
		this.Shape7.render(f5);
		this.Shape8.render(f5);
		this.Shape9.render(f5);
		this.Shape10.render(f5);
		this.Shape11.render(f5);
		this.Shape12.render(f5);
		this.Shape13.render(f5);
		this.Shape14.render(f5);
		this.Shape15.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
