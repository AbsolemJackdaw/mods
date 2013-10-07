package vanilla3DArmor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VanillaArmorModel extends ModelBiped
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;

	public VanillaArmorModel(float f)
	{
		super(f, 0,64,64);

		textureWidth = 64;
		textureHeight = 64;
		Shape1 = new ModelRenderer(this, 0, 32);
		Shape1.addBox(-1F, -3F, -2.5F, 5, 3, 5,f);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0.3F);
		Shape2 = new ModelRenderer(this, 20, 32);
		Shape2.addBox(-5F, -3F, -2.5F, 5, 3, 5,f);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(64, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, -0.3F);
		Shape3 = new ModelRenderer(this, 2, 28);
		Shape3.addBox(-2F, 10F, -4F, 4, 2, 2,f);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(64, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 2, 28);
		Shape4.addBox(-2F, 10F, -4F, 4, 2, 2,f);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(64, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 40);
		Shape5.addBox(-5F, 1F, -3F, 4, 4, 1,f);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(64, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 10, 40);
		Shape6.addBox(1F, 1F, -3F, 4, 4, 1,f);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(64, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 20, 40);
		Shape7.addBox(-2F, 5F, -2.5F, 4, 2, 1,f);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(64, 64);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 30, 40);
		Shape8.addBox(-1.5F, 7F, -2.5F, 3, 2, 1,f);
		Shape8.setRotationPoint(0F, 0F, 0F);
		Shape8.setTextureSize(64, 64);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		
		this.bipedLeftArm.addChild(Shape1);
		this.bipedRightArm.addChild(Shape2);
		this.bipedLeftLeg.addChild(Shape3);
		this.bipedRightLeg.addChild(Shape4);
		this.bipedBody.addChild(Shape5);
		this.bipedBody.addChild(Shape6);
		this.bipedBody.addChild(Shape7);
		this.bipedBody.addChild(Shape8);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
//		Shape1.render(f5);
//		Shape2.render(f5);
//		Shape3.render(f5);
//		Shape4.render(f5);
//		Shape5.render(f5);
//		Shape6.render(f5);
//		Shape7.render(f5);
//		Shape8.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
