package threeDitems.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class enderball extends ModelBase
{
	//fields
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
	ModelRenderer Shape13;
	ModelRenderer Shape16;
	ModelRenderer Shape17;
	ModelRenderer Shape19;
	ModelRenderer Shape12;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	ModelRenderer Shape18;
	ModelRenderer Shape20;
	ModelRenderer Shape21;
	ModelRenderer Shape22;
	ModelRenderer eye;

	public enderball()
	{
		textureWidth = 18;
		textureHeight = 24;

		eye = new ModelRenderer(this, 7, 0);
		eye.addBox(0.5f, -3.5f, 0f, 1, 4, 1);
		eye.setRotationPoint(0f, 0f, 0f);
		eye.setTextureSize(18, 24);
		eye.mirror = true;
		
		Shape1 = new ModelRenderer(this, 0, 21);
		Shape1.addBox(-1F, 0F, 0F, 4, 1, 2);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(18, 24);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, 0F, -1F, 2, 1, 1);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(18, 24);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(0F, 0F, 2F, 2, 1, 1);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(18, 24);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 7);
		Shape4.addBox(2F, -4F, -1F, 1, 4, 1);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(18, 24);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 7);
		Shape5.addBox(-1F, -4F, -1F, 1, 4, 1);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(18, 24);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 0, 7);
		Shape6.addBox(-1F, -4F, 2F, 1, 4, 1);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(18, 24);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 7);
		Shape7.addBox(2F, -4F, 2F, 1, 4, 1);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(18, 24);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 5);
		Shape8.addBox(0F, -1F, -2F, 2, 1, 1);
		Shape8.setRotationPoint(0F, 0F, 0F);
		Shape8.setTextureSize(18, 24);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 0, 5);
		Shape9.addBox(0F, -1F, 3F, 2, 1, 1);
		Shape9.setRotationPoint(0F, 0F, 0F);
		Shape9.setTextureSize(18, 24);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 10, 19);
		Shape10.addBox(3F, -1F, 0F, 1, 1, 2);
		Shape10.setRotationPoint(0F, 0F, 0F);
		Shape10.setTextureSize(18, 24);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 10, 16);
		Shape11.addBox(-2F, -1F, 0F, 1, 1, 2);
		Shape11.setRotationPoint(0F, 0F, 0F);
		Shape11.setTextureSize(18, 24);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 0, 12);
		Shape13.addBox(3F, -3F, -1F, 1, 2, 4);
		Shape13.setRotationPoint(0F, 0F, 0F);
		Shape13.setTextureSize(18, 24);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 0, 18);
		Shape16.addBox(-1F, -3F, -2F, 4, 2, 1);
		Shape16.setRotationPoint(0F, 0F, 0F);
		Shape16.setTextureSize(18, 24);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 0, 12);
		Shape17.addBox(-2F, -3F, -1F, 1, 2, 4);
		Shape17.setRotationPoint(0F, 0F, 0F);
		Shape17.setTextureSize(18, 24);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, 0F);
		Shape19 = new ModelRenderer(this, 8, 13);
		Shape19.addBox(-1F, -3F, 3F, 4, 2, 1);
		Shape19.setRotationPoint(0F, 0F, 0F);
		Shape19.setTextureSize(18, 24);
		Shape19.mirror = true;
		setRotation(Shape19, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 6, 6);
		Shape12.addBox(0F, -4F, -2F, 2, 1, 1);
		Shape12.setRotationPoint(0F, 0F, 0F);
		Shape12.setTextureSize(18, 24);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 6, 8);
		Shape14.addBox(0F, -4F, 3F, 2, 1, 1);
		Shape14.setRotationPoint(0F, 0F, 0F);
		Shape14.setTextureSize(18, 24);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 12, 7);
		Shape15.addBox(3F, -4F, 0F, 1, 1, 2);
		Shape15.setRotationPoint(0F, 0F, 0F);
		Shape15.setTextureSize(18, 24);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape18 = new ModelRenderer(this, 12, 4);
		Shape18.addBox(-2F, -4F, 0F, 1, 1, 2);
		Shape18.setRotationPoint(0F, 0F, 0F);
		Shape18.setTextureSize(18, 24);
		Shape18.mirror = true;
		setRotation(Shape18, 0F, 0F, 0F);
		Shape20 = new ModelRenderer(this, 6, 10);
		Shape20.addBox(-1F, -5F, 0F, 4, 1, 2);
		Shape20.setRotationPoint(0F, 0F, 0F);
		Shape20.setTextureSize(18, 24);
		Shape20.mirror = true;
		setRotation(Shape20, 0F, 0F, 0F);
		Shape21 = new ModelRenderer(this, 12, 2);
		Shape21.addBox(0F, -5F, 2F, 2, 1, 1);
		Shape21.setRotationPoint(0F, 0F, 0F);
		Shape21.setTextureSize(18, 24);
		Shape21.mirror = true;
		setRotation(Shape21, 0F, 0F, 0F);
		Shape22 = new ModelRenderer(this, 12, 0);
		Shape22.addBox(0F, -5F, -1F, 2, 1, 1);
		Shape22.setRotationPoint(0F, 0F, 0F);
		Shape22.setTextureSize(18, 24);
		Shape22.mirror = true;
		setRotation(Shape22, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	public void renderBall(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape13.render(f5);
		Shape16.render(f5);
		Shape17.render(f5);
		Shape19.render(f5);
		Shape12.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape18.render(f5);
		Shape20.render(f5);
		Shape21.render(f5);
		Shape22.render(f5);
//		eye.render(f5);

	}

	public void renderEye(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		eye.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
