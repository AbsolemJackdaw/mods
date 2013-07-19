package threeDitems;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class smallSwordPlain extends ModelBase
{
	//fields
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
	ModelRenderer Shape16;
	ModelRenderer Shape17;
	ModelRenderer Shape18;
	ModelRenderer Shape19;
	ModelRenderer Shape20;
	ModelRenderer Shape21;
	ModelRenderer Shape2;
	ModelRenderer Shape22;
	ModelRenderer Shape23;
	ModelRenderer Shape1;

	public smallSwordPlain()
	{
		textureWidth = 35;
		textureHeight = 18;

		Shape4 = new ModelRenderer(this, 0, 0);
		Shape4.addBox(-1.5F, 0F, 0F, 2, 4, 1);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(35, 18);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 7, 3);
		Shape5.addBox(-2F, 4F, 0F, 3, 1, 1);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(35, 18);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 7, 3);
		Shape6.addBox(-2F, 8F, 0F, 3, 1, 1);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(35, 18);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 7, 1);
		Shape7.addBox(1F, 5F, 0F, 1, 3, 1);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(35, 18);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 7, 1);
		Shape8.addBox(-3F, 5F, 0F, 1, 3, 1);
		Shape8.setRotationPoint(0F, 0F, 0F);
		Shape8.setTextureSize(35, 18);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 0, 14);
		Shape9.addBox(-2F, 5F, 0F, 3, 3, 1);
		Shape9.setRotationPoint(0F, 0F, 0F);
		Shape9.setTextureSize(35, 18);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 0, 6);
		Shape10.addBox(-4F, -1F, 0F, 7, 1, 1);
		Shape10.setRotationPoint(0F, 0F, 0F);
		Shape10.setTextureSize(35, 18);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 0, 8);
		Shape11.addBox(-6F, -2F, 0F, 11, 1, 1);
		Shape11.setRotationPoint(0F, 0F, 0F);
		Shape11.setTextureSize(35, 18);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 0, 6);
		Shape12.addBox(-7F, -3F, 0F, 4, 1, 1);
		Shape12.setRotationPoint(0F, 0F, 0F);
		Shape12.setTextureSize(35, 18);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 0, 6);
		Shape13.addBox(2F, -3F, 0F, 4, 1, 1);
		Shape13.setRotationPoint(0F, 0F, 0F);
		Shape13.setTextureSize(35, 18);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 0, 8);
		Shape14.addBox(-6F, -4F, 0F, 1, 1, 1);
		Shape14.setRotationPoint(0F, 0F, 0F);
		Shape14.setTextureSize(35, 18);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 0, 8);
		Shape15.addBox(4F, -4F, 0F, 1, 1, 1);
		Shape15.setRotationPoint(0F, 0F, 0F);
		Shape15.setTextureSize(35, 18);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 0, 10);
		Shape16.addBox(-5F, -2F, -0.5F, 9, 1, 2);
		Shape16.setRotationPoint(0F, 0F, 0F);
		Shape16.setTextureSize(35, 18);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 7, 10);
		Shape17.addBox(-6F, -3F, -0.5F, 1, 1, 2);
		Shape17.setRotationPoint(0F, 0F, 0F);
		Shape17.setTextureSize(35, 18);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, 0F);
		Shape18 = new ModelRenderer(this, 11, 10);
		Shape18.addBox(4F, -3F, -0.5F, 1, 1, 2);
		Shape18.setRotationPoint(0F, 0F, 0F);
		Shape18.setTextureSize(35, 18);
		Shape18.mirror = true;
		setRotation(Shape18, 0F, 0F, 0F);
		Shape19 = new ModelRenderer(this, 25, 0);
		Shape19.addBox(-2.5F, -18F, 0F, 4, 16, 1);
		Shape19.setRotationPoint(0F, 0F, 0F);
		Shape19.setTextureSize(35, 18);
		Shape19.mirror = true;
		setRotation(Shape19, 0F, 0F, 0F);
		Shape20 = new ModelRenderer(this, 26, 0);
		Shape20.addBox(-2F, -19F, 0F, 3, 1, 1);
		Shape20.setRotationPoint(0F, 0F, 0F);
		Shape20.setTextureSize(35, 18);
		Shape20.mirror = true;
		setRotation(Shape20, 0F, 0F, 0F);
		Shape21 = new ModelRenderer(this, 26, 0);
		Shape21.addBox(-1.5F, -18F, -0.5F, 2, 16, 2);
		Shape21.setRotationPoint(0F, 0F, 0F);
		Shape21.setTextureSize(35, 18);
		Shape21.mirror = true;
		setRotation(Shape21, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 28, 1);
		Shape2.addBox(-1F, 5F, 1F, 1, 3, 1);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(35, 18);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape22 = new ModelRenderer(this, 0, -1);
		Shape22.addBox(-1F, 0F, -0.5F, 1, 4, 2);
		Shape22.setRotationPoint(0F, 0F, 0F);
		Shape22.setTextureSize(35, 18);
		Shape22.mirror = true;
		setRotation(Shape22, 0F, 0F, 0F);
		Shape23 = new ModelRenderer(this, 28, 1);
		Shape23.addBox(-1F, 5F, -1F, 1, 3, 1);
		Shape23.setRotationPoint(0F, 0F, 0F);
		Shape23.setTextureSize(35, 18);
		Shape23.mirror = true;
		setRotation(Shape23, 0F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 25, 7);
		Shape1.addBox(-2F, 6F, -0.5F, 3, 1, 2);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(35, 18);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
		Shape17.render(f5);
		Shape18.render(f5);
		Shape19.render(f5);
		Shape20.render(f5);
		Shape21.render(f5);
		Shape2.render(f5);
		Shape22.render(f5);
		Shape23.render(f5);
		Shape1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}

