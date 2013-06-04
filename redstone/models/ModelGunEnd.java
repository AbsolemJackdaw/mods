package redstone.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelGunEnd extends ModelBase
{
	ModelRenderer middleleftbarrel;
	ModelRenderer topmiddlebarrel;
	ModelRenderer bottommiddlebarrel;
	ModelRenderer rightmiddlebarrel;
	ModelRenderer bottombase;
	ModelRenderer topbase;
	ModelRenderer rightbottombackbase2;
	ModelRenderer leftbottombackbase2;
	ModelRenderer rightbase;
	ModelRenderer leftbase;
	ModelRenderer toprightbarrel;
	ModelRenderer topleftbarrel;
	ModelRenderer bottomrightbarrel;
	ModelRenderer bottomleftbarrel;
	ModelRenderer leftbottombackbase;
	ModelRenderer rightbottombackbase;
	ModelRenderer middlerightbackbase;
	ModelRenderer middleleftbackbase;
	ModelRenderer backrightbase;
	ModelRenderer backleftbase;
	ModelRenderer scope;

	public ModelGunEnd()
	{
		textureWidth = 64;
		textureHeight = 32;

		middleleftbarrel = new ModelRenderer(this, 1, 0);
		middleleftbarrel.addBox(0F, 0F, 0F, 1, 1, 9);
		middleleftbarrel.setRotationPoint(2F, -2F, -9F);
		middleleftbarrel.setTextureSize(64, 32);
		middleleftbarrel.mirror = true;
		setRotation(middleleftbarrel, 0F, 0F, 0F);
		topmiddlebarrel = new ModelRenderer(this, 1, 0);
		topmiddlebarrel.addBox(0F, 0F, 0F, 1, 1, 9);
		topmiddlebarrel.setRotationPoint(1F, -3F, -9F);
		topmiddlebarrel.setTextureSize(64, 32);
		topmiddlebarrel.mirror = true;
		setRotation(topmiddlebarrel, 0F, 0F, 0F);
		bottommiddlebarrel = new ModelRenderer(this, 1, 0);
		bottommiddlebarrel.addBox(0F, 0F, 0F, 1, 1, 9);
		bottommiddlebarrel.setRotationPoint(1F, -1F, -9F);
		bottommiddlebarrel.setTextureSize(64, 32);
		bottommiddlebarrel.mirror = true;
		setRotation(bottommiddlebarrel, 0F, 0F, 0F);
		rightmiddlebarrel = new ModelRenderer(this, 1, 0);
		rightmiddlebarrel.addBox(0F, 0F, 0F, 1, 1, 9);
		rightmiddlebarrel.setRotationPoint(0F, -2F, -9F);
		rightmiddlebarrel.setTextureSize(64, 32);
		rightmiddlebarrel.mirror = true;
		setRotation(rightmiddlebarrel, 0F, 0F, 0F);
		bottombase = new ModelRenderer(this, 0, 11);
		bottombase.addBox(0F, 0F, 0F, 3, 1, 9);
		bottombase.setRotationPoint(0F, 0F, 0F);
		bottombase.setTextureSize(64, 32);
		bottombase.mirror = true;
		setRotation(bottombase, 0F, 0F, 0F);
		topbase = new ModelRenderer(this, 0, 22);
		topbase.addBox(0F, 0F, 0F, 3, 1, 9);
		topbase.setRotationPoint(0F, -4F, 0F);
		topbase.setTextureSize(64, 32);
		topbase.mirror = true;
		setRotation(topbase, 0F, 0F, 0F);
		rightbottombackbase2 = new ModelRenderer(this, 0, 0);
		rightbottombackbase2.addBox(0F, 0F, 0F, 1, 1, 4);
		rightbottombackbase2.setRotationPoint(-1F, 0F, 9F);
		rightbottombackbase2.setTextureSize(64, 32);
		rightbottombackbase2.mirror = true;
		setRotation(rightbottombackbase2, 0F, 0F, 0F);
		leftbottombackbase2 = new ModelRenderer(this, 0, 0);
		leftbottombackbase2.addBox(0F, 0F, 0F, 1, 1, 4);
		leftbottombackbase2.setRotationPoint(3F, 0F, 9F);
		leftbottombackbase2.setTextureSize(64, 32);
		leftbottombackbase2.mirror = true;
		setRotation(leftbottombackbase2, 0F, 0F, 0F);
		rightbase = new ModelRenderer(this, 33, 20);
		rightbase.addBox(0F, 0F, 0F, 1, 3, 9);
		rightbase.setRotationPoint(-1F, -3F, 0F);
		rightbase.setTextureSize(64, 32);
		rightbase.mirror = true;
		setRotation(rightbase, 0F, 0F, 0F);
		leftbase = new ModelRenderer(this, 33, 20);
		leftbase.addBox(0F, 0F, 0F, 1, 3, 9);
		leftbase.setRotationPoint(3F, -3F, 0F);
		leftbase.setTextureSize(64, 32);
		leftbase.mirror = true;
		setRotation(leftbase, 0F, 0F, 0F);
		toprightbarrel = new ModelRenderer(this, 16, 9);
		toprightbarrel.addBox(0F, 0F, 0F, 1, 1, 8);
		toprightbarrel.setRotationPoint(0F, -3F, -8F);
		toprightbarrel.setTextureSize(64, 32);
		toprightbarrel.mirror = true;
		setRotation(toprightbarrel, 0F, 0F, 0F);
		topleftbarrel = new ModelRenderer(this, 16, 9);
		topleftbarrel.addBox(0F, 0F, 0F, 1, 1, 8);
		topleftbarrel.setRotationPoint(2F, -3F, -8F);
		topleftbarrel.setTextureSize(64, 32);
		topleftbarrel.mirror = true;
		setRotation(topleftbarrel, 0F, 0F, 0F);
		bottomrightbarrel = new ModelRenderer(this, 16, 9);
		bottomrightbarrel.addBox(0F, 0F, 0F, 1, 1, 8);
		bottomrightbarrel.setRotationPoint(0F, -1F, -8F);
		bottomrightbarrel.setTextureSize(64, 32);
		bottomrightbarrel.mirror = true;
		setRotation(bottomrightbarrel, 0F, 0F, 0F);
		bottomleftbarrel = new ModelRenderer(this, 16, 9);
		bottomleftbarrel.addBox(0F, 0F, 0F, 1, 1, 8);
		bottomleftbarrel.setRotationPoint(2F, -1F, -8F);
		bottomleftbarrel.setTextureSize(64, 32);
		bottomleftbarrel.mirror = true;
		setRotation(bottomleftbarrel, 0F, 0F, 0F);
		leftbottombackbase = new ModelRenderer(this, 30, 0);
		leftbottombackbase.addBox(0F, 0F, 0F, 1, 1, 5);
		leftbottombackbase.setRotationPoint(4F, -1F, 9F);
		leftbottombackbase.setTextureSize(64, 32);
		leftbottombackbase.mirror = true;
		setRotation(leftbottombackbase, 0F, 0F, 0F);
		rightbottombackbase = new ModelRenderer(this, 30, 0);
		rightbottombackbase.addBox(0F, 0F, 0F, 1, 1, 5);
		rightbottombackbase.setRotationPoint(-2F, -1F, 9F);
		rightbottombackbase.setTextureSize(64, 32);
		rightbottombackbase.mirror = true;
		setRotation(rightbottombackbase, 0F, 0F, 0F);
		middlerightbackbase = new ModelRenderer(this, 30, 0);
		middlerightbackbase.addBox(0F, 0F, 0F, 1, 1, 5);
		middlerightbackbase.setRotationPoint(-2F, -3F, 9F);
		middlerightbackbase.setTextureSize(64, 32);
		middlerightbackbase.mirror = true;
		setRotation(middlerightbackbase, 0F, 0F, 0F);
		middleleftbackbase = new ModelRenderer(this, 30, 0);
		middleleftbackbase.addBox(0F, 0F, 0F, 1, 1, 5);
		middleleftbackbase.setRotationPoint(4F, -3F, 9F);
		middleleftbackbase.setTextureSize(64, 32);
		middleleftbackbase.mirror = true;
		setRotation(middleleftbackbase, 0F, 0F, 0F);
		backrightbase = new ModelRenderer(this, 33, 7);
		backrightbase.addBox(0F, 0F, 0F, 2, 1, 8);
		backrightbase.setRotationPoint(-1F, -4F, 9F);
		backrightbase.setTextureSize(64, 32);
		backrightbase.mirror = true;
		setRotation(backrightbase, 0F, 0F, 0F);
		backleftbase = new ModelRenderer(this, 33, 7);
		backleftbase.addBox(0F, 0F, 0F, 2, 1, 8);
		backleftbase.setRotationPoint(2F, -4F, 9F);
		backleftbase.setTextureSize(64, 32);
		backleftbase.mirror = true;
		setRotation(backleftbase, 0F, 0F, 0F);
		scope = new ModelRenderer(this, 43, 0);
		scope.addBox(0F, 0F, 0F, 1, 1, 4);
		scope.setRotationPoint(1F, -4F, -4F);
		scope.setTextureSize(64, 32);
		scope.mirror = true;
		setRotation(scope, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		middleleftbarrel.render(f5);
		topmiddlebarrel.render(f5);
		bottommiddlebarrel.render(f5);
		rightmiddlebarrel.render(f5);
		bottombase.render(f5);
		topbase.render(f5);
		rightbottombackbase2.render(f5);
		leftbottombackbase2.render(f5);
		rightbase.render(f5);
		leftbase.render(f5);
		toprightbarrel.render(f5);
		topleftbarrel.render(f5);
		bottomrightbarrel.render(f5);
		bottomleftbarrel.render(f5);
		leftbottombackbase.render(f5);
		rightbottombackbase.render(f5);
		middlerightbackbase.render(f5);
		middleleftbackbase.render(f5);
		backrightbase.render(f5);
		backleftbase.render(f5);
		scope.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}