package threeDitems.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class bow extends ModelBase
{
	//fields
	ModelRenderer handle1x1;
	ModelRenderer handledown;
	ModelRenderer handleup;
	ModelRenderer bowWoodup1;
	ModelRenderer bowWoodup2;
	ModelRenderer bowWooddown1;
	ModelRenderer bowWooddown2;
	public ModelRenderer string;
	ModelRenderer bowWoodup1Bis;
	ModelRenderer bowWoodup2Bis;
	ModelRenderer bowWooddown1Bis;
	ModelRenderer bowWooddown2Bis;
	ModelRenderer arrowShaft;
	ModelRenderer arrowHead;
	ModelRenderer arrowHeadDeco;
	ModelRenderer stringpart1Bis;
	ModelRenderer stringpart2Bis;
	ModelRenderer bowWoodup1Bis2;
	ModelRenderer bowWoodup2Bis2;
	ModelRenderer bowWooddown1Bis2;
	ModelRenderer bowWooddown2Bis2;
	ModelRenderer stringpart1Bis2;
	ModelRenderer stringpart2Bis2;
	ModelRenderer arrowShaftBis;
	ModelRenderer arrowHeadDecoBis;
	ModelRenderer arrowHeadBis;

	public bow()
	{
		textureWidth = 32;
		textureHeight = 32;

		handle1x1 = new ModelRenderer(this, 8, 0);
		handle1x1.addBox(0F, 0F, 0F, 1, 1, 1);
		handle1x1.setRotationPoint(0F, 0F, 0F);
		handle1x1.setTextureSize(32, 32);
		handle1x1.mirror = true;
		setRotation(handle1x1, 0F, 0F, 0F);
		handledown = new ModelRenderer(this, 0, 7);
		handledown.addBox(0F, 0F, 0F, 2, 4, 1);
		handledown.setRotationPoint(0F, 1F, 0F);
		handledown.setTextureSize(32, 32);
		handledown.mirror = true;
		setRotation(handledown, 0.0698132F, 0F, 0F);
		handleup = new ModelRenderer(this, 0, 7);
		handleup.addBox(0F, 0F, 0F, 2, 4, 1);
		handleup.setRotationPoint(0F, -4F, 0.3F);
		handleup.setTextureSize(32, 32);
		handleup.mirror = true;
		setRotation(handleup, -0.0698132F, 0F, 0F);
		bowWoodup1 = new ModelRenderer(this, 0, 0);
		bowWoodup1.addBox(0F, 0F, 0F, 1, 5, 1);
		bowWoodup1.setRotationPoint(0.5F, -3F, 1.2F);
		bowWoodup1.setTextureSize(32, 32);
		bowWoodup1.mirror = true;
		setRotation(bowWoodup1, 2.827433F, 0F, 0F);
		bowWoodup2 = new ModelRenderer(this, 0, 0);
		bowWoodup2.addBox(0F, 0F, 0F, 1, 6, 1);
		bowWoodup2.setRotationPoint(0.5F, -7.54F, 2.74F);
		bowWoodup2.setTextureSize(32, 32);
		bowWoodup2.mirror = true;
		setRotation(bowWoodup2, 2.600541F, 0F, 0F);
		bowWooddown1 = new ModelRenderer(this, 0, 0);
		bowWooddown1.addBox(0F, 0F, 0F, 1, 6, 1);
		bowWooddown1.setRotationPoint(0.5F, 4F, 0.3F);
		bowWooddown1.setTextureSize(32, 32);
		bowWooddown1.mirror = true;
		setRotation(bowWooddown1, 0.3141593F, 0F, 0F);
		bowWooddown2 = new ModelRenderer(this, 0, 0);
		bowWooddown2.addBox(0F, 0F, -1F, 1, 6, 1);
		bowWooddown2.setRotationPoint(0.5F, 9.3F, 3.05F);
		bowWooddown2.setTextureSize(32, 32);
		bowWooddown2.mirror = true;
		setRotation(bowWooddown2, 0.5410521F, 0F, 0F);
		string = new ModelRenderer(this, 6, 0);
		string.addBox(0F, 0F, 0F, 1, 27, 0);
		string.setRotationPoint(0.5F, -12.8F, 5.6F);
		string.setTextureSize(32, 32);
		string.mirror = true;
		setRotation(string, 0F, 0F, 0F);
		bowWoodup1Bis = new ModelRenderer(this, 0, 0);
		bowWoodup1Bis.addBox(0F, 0F, 0F, 1, 5, 1);
		bowWoodup1Bis.setRotationPoint(0.5F, -3F, 1.2F);
		bowWoodup1Bis.setTextureSize(32, 32);
		bowWoodup1Bis.mirror = true;
		setRotation(bowWoodup1Bis, 2.617994F, 0F, 0F);
		bowWoodup2Bis = new ModelRenderer(this, 0, 0);
		bowWoodup2Bis.addBox(0F, 0F, -1F, 1, 6, 1);
		bowWoodup2Bis.setRotationPoint(0.5F, -7.54F, 2.74F);
		bowWoodup2Bis.setTextureSize(32, 32);
		bowWoodup2Bis.mirror = true;
		setRotation(bowWoodup2Bis, 2.426008F, 0F, 0F);
		bowWooddown1Bis = new ModelRenderer(this, 0, 0);
		bowWooddown1Bis.addBox(0F, 0F, 0F, 1, 6, 1);
		bowWooddown1Bis.setRotationPoint(0.5F, 4F, 0.3F);
		bowWooddown1Bis.setTextureSize(32, 32);
		bowWooddown1Bis.mirror = true;
		setRotation(bowWooddown1Bis, 0.5235988F, 0F, 0F);
		bowWooddown2Bis = new ModelRenderer(this, 0, 0);
		bowWooddown2Bis.addBox(0F, 0F, 0F, 1, 6, 1);
		bowWooddown2Bis.setRotationPoint(0.5F, 9.3F, 3.05F);
		bowWooddown2Bis.setTextureSize(32, 32);
		bowWooddown2Bis.mirror = true;
		setRotation(bowWooddown2Bis, 0.715585F, 0F, 0F);
		arrowShaft = new ModelRenderer(this, 0, 16);
		arrowShaft.addBox(1F, 0F, -6.2F, 1, 1, 15);
		arrowShaft.setRotationPoint(0F, 0F, 0F);
		arrowShaft.setTextureSize(32, 32);
		arrowShaft.mirror = true;
		setRotation(arrowShaft, 0F, 0F, 0F);
		arrowHead = new ModelRenderer(this, 24, 18);
		arrowHead.addBox(1F, 0F, 0F, 1, 3, 3);
		arrowHead.setRotationPoint(0F, 0.5F, -8.7F);
		arrowHead.setTextureSize(32, 32);
		arrowHead.mirror = true;
		setRotation(arrowHead, 0.7853982F, 0F, 0F);
		arrowHeadDeco = new ModelRenderer(this, 24, 24);
		arrowHeadDeco.addBox(0.5F, -1F, -6F, 2, 3, 1);
		arrowHeadDeco.setRotationPoint(0F, 0F, -0.6F);
		arrowHeadDeco.setTextureSize(32, 32);
		arrowHeadDeco.mirror = true;
		setRotation(arrowHeadDeco, 0F, 0F, 0F);
		stringpart1Bis = new ModelRenderer(this, 6, 0);
		stringpart1Bis.addBox(0.5F, 0F, 0F, 1, 12, 0);
		stringpart1Bis.setRotationPoint(0F, -11F, 7F);
		stringpart1Bis.setTextureSize(32, 32);
		stringpart1Bis.mirror = true;
		setRotation(stringpart1Bis, 0.1745329F, 0F, 0F);
		stringpart2Bis = new ModelRenderer(this, 6, 0);
		stringpart2Bis.addBox(-0.5F, -13F, 0F, 1, 13, 0);
		stringpart2Bis.setRotationPoint(1F, 13F, 7F);
		stringpart2Bis.setTextureSize(32, 32);
		stringpart2Bis.mirror = true;
		setRotation(stringpart2Bis, -0.1570796F, 0F, 0F);
		bowWoodup1Bis2 = new ModelRenderer(this, 0, 0);
		bowWoodup1Bis2.addBox(0F, 0F, 0F, 1, 5, 1);
		bowWoodup1Bis2.setRotationPoint(0.5F, -3F, 1.2F);
		bowWoodup1Bis2.setTextureSize(32, 32);
		bowWoodup1Bis2.mirror = true;
		setRotation(bowWoodup1Bis2, 2.356194F, 0F, 0F);
		bowWoodup2Bis2 = new ModelRenderer(this, 0, 0);
		bowWoodup2Bis2.addBox(0F, 0F, -2F, 1, 6, 1);
		bowWoodup2Bis2.setRotationPoint(0.5F, -7.54F, 2.74F);
		bowWoodup2Bis2.setTextureSize(32, 32);
		bowWoodup2Bis2.mirror = true;
		setRotation(bowWoodup2Bis2, 2.111848F, 0F, 0F);
		bowWooddown1Bis2 = new ModelRenderer(this, 0, 0);
		bowWooddown1Bis2.addBox(0F, 0F, 0F, 1, 6, 1);
		bowWooddown1Bis2.setRotationPoint(0.5F, 4F, 0.3F);
		bowWooddown1Bis2.setTextureSize(32, 32);
		bowWooddown1Bis2.mirror = true;
		setRotation(bowWooddown1Bis2, 0.7853982F, 0F, 0F);
		bowWooddown2Bis2 = new ModelRenderer(this, 0, 0);
		bowWooddown2Bis2.addBox(0F, 0F, 1F, 1, 6, 1);
		bowWooddown2Bis2.setRotationPoint(0.5F, 9.3F, 4.05F);
		bowWooddown2Bis2.setTextureSize(32, 32);
		bowWooddown2Bis2.mirror = true;
		setRotation(bowWooddown2Bis2, 1.151917F, 0F, 0F);
		stringpart1Bis2 = new ModelRenderer(this, 6, 0);
		stringpart1Bis2.addBox(0.5F, 0F, 0F, 1, 11, 0);
		stringpart1Bis2.setRotationPoint(0F, -9F, 8F);
		stringpart1Bis2.setTextureSize(32, 32);
		stringpart1Bis2.mirror = true;
		setRotation(stringpart1Bis2, 0.5585054F, 0F, 0F);
		stringpart2Bis2 = new ModelRenderer(this, 6, 0);
		stringpart2Bis2.addBox(-0.5F, -11F, 0F, 1, 11, 0);
		stringpart2Bis2.setRotationPoint(1F, 10F, 9F);
		stringpart2Bis2.setTextureSize(32, 32);
		stringpart2Bis2.mirror = true;
		setRotation(stringpart2Bis2, -0.4886922F, 0F, 0F);
		arrowShaftBis = new ModelRenderer(this, 0, 16);
		arrowShaftBis.addBox(1F, 0F, -1.2F, 1, 1, 15);
		arrowShaftBis.setRotationPoint(0F, 0F, 0F);
		arrowShaftBis.setTextureSize(32, 32);
		arrowShaftBis.mirror = true;
		setRotation(arrowShaftBis, 0F, 0F, 0F);
		arrowHeadDecoBis = new ModelRenderer(this, 24, 24);
		arrowHeadDecoBis.addBox(0.5F, -1F, -1F, 2, 3, 1);
		arrowHeadDecoBis.setRotationPoint(0F, 0F, -0.6F);
		arrowHeadDecoBis.setTextureSize(32, 32);
		arrowHeadDecoBis.mirror = true;
		setRotation(arrowHeadDecoBis, 0F, 0F, 0F);
		arrowHeadBis = new ModelRenderer(this, 24, 18);
		arrowHeadBis.addBox(1F, 0F, 0F, 1, 3, 3);
		arrowHeadBis.setRotationPoint(0F, 0.5F, -3.7F);
		arrowHeadBis.setTextureSize(32, 32);
		arrowHeadBis.mirror = true;
		setRotation(arrowHeadBis, 0.7853982F, 0F, 0F);
	}

	public void pullHard(boolean show){
		bowWoodup1Bis2.showModel =
				bowWoodup2Bis2.showModel =
				bowWooddown1Bis2.showModel =
				bowWooddown2Bis2.showModel =
				stringpart1Bis2.showModel =
				stringpart2Bis2.showModel =
				arrowShaftBis.showModel =
				arrowHeadDecoBis.showModel =
				arrowHeadBis.showModel = show;
	}
	public void pullSlow(boolean show){
		arrowShaft.showModel =
				arrowHead.showModel =
				arrowHeadDeco.showModel =
				stringpart1Bis.showModel =
				stringpart2Bis.showModel =
				bowWoodup1Bis.showModel =
				bowWoodup2Bis.showModel =
				bowWooddown1Bis.showModel =
				bowWooddown2Bis.showModel = show;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		//base
		handle1x1.render(f5);
		handledown.render(f5);
		handleup.render(f5);
		//rest
		bowWoodup1.render(f5);
		bowWoodup2.render(f5);
		bowWooddown1.render(f5);
		bowWooddown2.render(f5);
		string.render(f5);
		//pull slow
		bowWoodup1Bis.render(f5);
		bowWoodup2Bis.render(f5);
		bowWooddown1Bis.render(f5);
		bowWooddown2Bis.render(f5);
		arrowShaft.render(f5);
		arrowHead.render(f5);
		arrowHeadDeco.render(f5);
		stringpart1Bis.render(f5);
		stringpart2Bis.render(f5);
		//pull hard
		bowWoodup1Bis2.render(f5);
		bowWoodup2Bis2.render(f5);
		bowWooddown1Bis2.render(f5);
		bowWooddown2Bis2.render(f5);
		stringpart1Bis2.render(f5);
		stringpart2Bis2.render(f5);
		arrowShaftBis.render(f5);
		arrowHeadDecoBis.render(f5);
		arrowHeadBis.render(f5);

	}
	/*should always return true;*/
	public void renderBase(){
		handle1x1.showModel = handledown.showModel = handleup.showModel = true;
	}
	public void restBow(boolean show){
		bowWooddown1.showModel =
				bowWooddown2.showModel =
				bowWoodup1.showModel =
				bowWoodup2.showModel =
				string.showModel = show;
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
