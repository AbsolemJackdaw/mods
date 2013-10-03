
package tutorial;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class tutarm extends ModelBiped
{
	//fields

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape3;

	public tutarm(float f)
	{
		super(f, 0, 64,64);
		textureWidth = 64;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 32);
		Shape1.addBox(-1F, -3F, -2.5F, 5, 5, 5);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 20, 32);
		Shape2.addBox(-4F, -3F, -2.5F, 5, 5, 5);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(64, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 4, 42);
		Shape4.addBox(-2F, 1F, -3F, 4, 9, 1);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(64, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 14, 42);
		Shape5.addBox(-4F, 4F, -2.7F, 8, 2, 1);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(64, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 47, 32);
		Shape6.addBox(-1F, 0F, -3F, 2, 9, 1);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(64, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 41, 32);
		Shape7.addBox(-1F, 0F, -3F, 2, 9, 1);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(64, 64);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 42);
		Shape3.addBox(4F, -12F, 3F, 1, 7, 1);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(64, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);

		this.bipedHead.addChild(Shape3);
		this.bipedLeftArm.addChild(Shape1);
		this.bipedRightArm.addChild(Shape2);
		this.bipedLeftLeg.addChild(Shape6);
		this.bipedLeftLeg.addChild(Shape7);
		this.bipedBody.addChild(Shape4);
		this.bipedBody.addChild(Shape5);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
