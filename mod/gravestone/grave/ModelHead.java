package gravestone.grave;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHead extends ModelBiped {

//	public ModelRenderer modelhead;
//	public ModelRenderer modelheadwear;
//	public ModelRenderer bipedBody;
//	public ModelRenderer bipedRightArm;
//	public ModelRenderer bipedLeftArm;

	
	public ModelHead()
	{
		textureWidth = 64;
		textureHeight = 32;
		///CrossShaped Model
//		bipedHead = new ModelRenderer(this, 0, 0);
//		bipedHead.addBox(0F, 0F, 0F, 8, 8, 8);
//		bipedHead.setRotationPoint(-4F, -8F, -4F);
//		bipedHead.setTextureSize(64, 32);
//		bipedHead.mirror = true;
//		setRotation(bipedHead, 0F, 0F, 0F);
//
//		bipedHeadwear = new ModelRenderer(this, 32, 0);
//		bipedHeadwear.addBox(0F, 0F, 0F, 8, 8, 8, 0.4f);
//		bipedHeadwear.setRotationPoint(-4F, -8F, -4F);
//		bipedHeadwear.setTextureSize(64, 32);
//		bipedHeadwear.mirror = true;
//		setRotation(bipedHeadwear, 0F, 0F, 0F);

		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4);
		this.bipedBody.setRotationPoint(0.0F, 0.0F , 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 40, 16);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 6, 4);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F , 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 40, 16);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 6, 4);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F , 0.0F);
		
		this.bipedLeftLeg.showModel = false;
		this.bipedRightLeg.showModel = false;

	}

	public void renderHead(float f5)
	{
		bipedHead.render(f5);
		bipedHeadwear.render(f5);
		bipedBody.render(f5);
		bipedLeftArm.render(f5);
		bipedRightArm.render(f5);
	}
	
	public void showHead(boolean b)
	{
		bipedHead.showModel=bipedHeadwear.showModel=bipedBody.showModel=bipedLeftArm.showModel=bipedRightArm.showModel=b;
	}
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		renderHead(f5);
	}
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
