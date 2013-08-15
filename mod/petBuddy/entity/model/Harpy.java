package petBuddy.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class Harpy extends ModelBase
{
	//fields
	ModelRenderer body;
	ModelRenderer breast;
	ModelRenderer Shape1;
	ModelRenderer rightleg01;
	ModelRenderer rightleg02;
	ModelRenderer rightleg03;
	ModelRenderer rightleg04;
	ModelRenderer leftleg01;
	ModelRenderer leftleg02;
	ModelRenderer leftleg03;
	ModelRenderer leftleg04;
	ModelRenderer leftarm01;
	ModelRenderer leftarm02;
	ModelRenderer leftwing01;
	ModelRenderer leftwing02;
	ModelRenderer leftwing03;
	ModelRenderer rightarm01;
	ModelRenderer rightarm02;
	ModelRenderer rightwing01;
	ModelRenderer rightwing02;
	ModelRenderer rightwing03;
	ModelRenderer head01;
	ModelRenderer lefthair;
	ModelRenderer righthair;
	ModelRenderer hair01;

	public Harpy()
	{
		textureWidth = 64;
		textureHeight = 128;

		body = new ModelRenderer(this, 46, 18);
		body.addBox(-3F, 0F, -2F, 6, 12, 3);
		body.setRotationPoint(0F, 0F, -1F);
		body.setTextureSize(64, 128);
		body.mirror = true;
		setRotation(body, 0.6108652F, 0F, 0F);

		breast = new ModelRenderer(this, 46, 12);
		breast.addBox(-3F, 1.5F, -1F, 6, 3, 3);
		breast.setRotationPoint(0F, 0F, 0F);
		breast.setTextureSize(64, 128);
		breast.mirror = true;
		setRotation(breast, -0.3141593F-0.6108652F, 0F, 0F);

		Shape1 = new ModelRenderer(this, 0, 60);
		Shape1.addBox(-3F, 9F, -4F, 6, 20, 0);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 128);
		Shape1.mirror = true;
		setRotation(Shape1, 1.132812F-0.6108652F, 0F, 0F);

		rightleg01 = new ModelRenderer(this, 0, 14);
		rightleg01.addBox(-2F, -2F, -2F, 4, 10, 4);
		rightleg01.setRotationPoint(-2F, 10F, 4F);
		rightleg01.setTextureSize(64, 128);
		rightleg01.mirror = true;
		setRotation(rightleg01, -0.9948377F, 0.1745329F, 0F);

		rightleg02 = new ModelRenderer(this, 16, 14);
		rightleg02.addBox(-0.5F, -2F, -8.5F, 2, 9, 2);
		rightleg02.setRotationPoint(0F, 0F, 0F);
		rightleg02.setTextureSize(64, 128);
		rightleg02.mirror = true;
		setRotation(rightleg02, 0.6283185F+0.9948377F, 0.1745329F-0.1745329F, 0.0872665F);

		rightleg03 = new ModelRenderer(this, 24, 14);
		rightleg03.addBox(-0.5F, 4F, -13F, 2, 2, 6);
		rightleg03.setRotationPoint(0f,0f,0f);
		rightleg03.setTextureSize(64, 128);
		rightleg03.mirror = true;
		setRotation(rightleg03, 0.9075712F+0.9948377F, 0.1745329F-0.1745329F, 0.0872665F);

		rightleg04 = new ModelRenderer(this, 24, 22);
		rightleg04.addBox(-2F, 11.5F, -4F, 3, 2, 5);
		rightleg04.setRotationPoint(0F, 0F, 0F);
		rightleg04.setTextureSize(64, 128);
		rightleg04.mirror = true;
		setRotation(rightleg04, 0.1745329F+0.9948377F, 0.1745329F-0.1745329F, 0F);

		leftleg01 = new ModelRenderer(this, 0, 14);
		leftleg01.addBox(-2F, -2F, -2F, 4, 10, 4);
		leftleg01.setRotationPoint(2F, 10F, 4F);
		leftleg01.setTextureSize(64, 128);
		leftleg01.mirror = true;
		setRotation(leftleg01, -0.9948377F, -0.1745329F, 0F);

		leftleg02 = new ModelRenderer(this, 16, 14);
		leftleg02.addBox(-1.5F, -2F, -8.5F, 2, 9, 2);
		leftleg02.setRotationPoint(0F, 0F, 0F);
		leftleg02.setTextureSize(64, 128);
		leftleg02.mirror = true;
		setRotation(leftleg02, 0.6283185F+0.9948377F, -0.1745329F+0.1745329F, -0.0872665F);

		leftleg03 = new ModelRenderer(this, 24, 14);
		leftleg03.addBox(-1.5F, 4F, -13F, 2, 2, 6);
		leftleg03.setRotationPoint(0F, 0F, 0F);
		leftleg03.setTextureSize(64, 128);
		leftleg03.mirror = true;
		setRotation(leftleg03, 0.9075712F+0.9948377F, -0.1745329F+0.1745329F, -0.0872665F);

		leftleg04 = new ModelRenderer(this, 24, 22);
		leftleg04.addBox(-1F, 11.5F, -4F, 3, 2, 5);
		leftleg04.setRotationPoint(0F, 0F, 0F);
		leftleg04.setTextureSize(64, 128);
		leftleg04.mirror = true;
		setRotation(leftleg04, 0.1745329F+0.9948377F, -0.1745329F+0.1745329F, 0F);

		leftarm01 = new ModelRenderer(this, 56, 4);
		leftarm01.addBox(-1F, 0F, -1F, 2, 6, 2);
		leftarm01.setRotationPoint(3F, 1F, 0F);
		leftarm01.setTextureSize(64, 128);
		leftarm01.mirror = true;
		setRotation(leftarm01, 0.1487144F, -0.2230717F, -0.9294653F);

		leftarm02 = new ModelRenderer(this, 44, 0);
		leftarm02.addBox(1F, 1F, 3F, 8, 2, 2);
		leftarm02.setRotationPoint(3F, 1F, 0F);
		leftarm02.setTextureSize(64, 128);
		leftarm02.mirror = true;
		setRotation(leftarm02, 0F, 1.308997F+0.2230717F, 0.8726646F+0.9294653F);

		leftwing01 = new ModelRenderer(this, 20, 50);
		leftwing01.addBox(8F, 2F, -4F, 8, 0, 14);
		leftwing01.setRotationPoint(0F, 0F, 0F);
		leftwing01.setTextureSize(64, 128);
		leftwing01.mirror = true;
		setRotation(leftwing01, 0.8726646F, 0.3490659F, 0.4363323F);

		leftwing02 = new ModelRenderer(this, 14, 33);
		leftwing02.addBox(-13F, -3F, -5F, 8, 0, 17);
		leftwing02.setRotationPoint(0F, 0F, 0F);
		leftwing02.setTextureSize(64, 128);
		leftwing02.mirror = true;
		setRotation(leftwing02, -0.8203047F, 0F, -3.01942F);

		leftwing03 = new ModelRenderer(this, 14, 33);
		leftwing03.addBox(-11F, -3F, -5F, 8, 0, 17);
		leftwing03.setRotationPoint(0F, 0F, 0F);
		leftwing03.setTextureSize(64, 128);
		leftwing03.mirror = true;
		setRotation(leftwing03, -0.7330383F, -0.2617994F, 3.141593F);

		rightarm01 = new ModelRenderer(this, 56, 4);
		rightarm01.addBox(-1F, 0F, -1F, 2, 6, 2);
		rightarm01.setRotationPoint(-3F, 1F, 0F);
		rightarm01.setTextureSize(64, 128);
		rightarm01.mirror = true;
		setRotation(rightarm01, 0.1487144F, 0.2230705F, 0.9294576F);

		rightarm02 = new ModelRenderer(this, 44, 0);
		rightarm02.addBox(-9F, 1F, 3F, 8, 2, 2);
		rightarm02.setRotationPoint(-3F, 1F, 0F);
		rightarm02.setTextureSize(64, 128);
		rightarm02.mirror = true;
		setRotation(rightarm02, 0F-0.1487144F, -1.308997F-0.2230705F, -0.8726646F-0.9294576F);

		rightwing01 = new ModelRenderer(this, 20, 50);
		rightwing01.addBox(8F, -2F, -4F, 8, 0, 14);
		rightwing01.setRotationPoint(0F, 0F, 0F);
		rightwing01.setTextureSize(64, 128);
		rightwing01.mirror = true;
		setRotation(rightwing01, -0.8726646F-0.1487144F, -0.3490659F-0.2230705F, 2.70526F-0.9294576F);

		rightwing02 = new ModelRenderer(this, 14, 33);
		rightwing02.addBox(-11F, 3F, -5F, 8, 0, 17);
		rightwing02.setRotationPoint(0F, 0F, 0F);
		rightwing02.setTextureSize(64, 128);
		rightwing02.mirror = true;
		setRotation(rightwing02, 0.7330383F-0.1487144F, 0.2617994F-0.2230705F, 0F-0.9294576F);

		rightwing03 = new ModelRenderer(this, 14, 33);
		rightwing03.addBox(-13F, 3F, -5F, 8, 0, 17);
		rightwing03.setRotationPoint(0F, 0F, 0F);
		rightwing03.setTextureSize(64, 128);
		rightwing03.mirror = true;
		setRotation(rightwing03, 0.8203047F-0.1487144F, 0F-0.2230705F, -0.122173F-0.9294576F);

		head01 = new ModelRenderer(this, 0, 0);
		head01.addBox(-3.5F, -7.5F, -5F, 7, 7, 7);
		head01.setRotationPoint(0F, 0.5333334F, -1F);
		head01.setTextureSize(64, 128);
		head01.mirror = true;
		setRotation(head01, 0F, 0F, 0F);

		lefthair = new ModelRenderer(this, 0, 19);
		lefthair.addBox(3F, -3F, 4F, 0, 9, 9);
		lefthair.setRotationPoint(0F, 0F, 0F);
		lefthair.setTextureSize(64, 128);
		lefthair.mirror = true;
		setRotation(lefthair, 1.134464F, 0.2617994F, 0.0872665F);

		righthair = new ModelRenderer(this, 0, 19);
		righthair.addBox(-3F, -3F, 4F, 0, 9, 9);
		righthair.setRotationPoint(0F, 0F, 0F);
		righthair.setTextureSize(64, 128);
		righthair.mirror = true;
		setRotation(righthair, 1.134464F, -0.2617994F, -0.0872665F);

		hair01 = new ModelRenderer(this, 0, 42);
		hair01.addBox(-4F, -7.7F, -5.5F, 8, 10, 8);
		hair01.setRotationPoint(0F, 0F, 0F);
		hair01.setTextureSize(64, 128);
		hair01.mirror = true;
		setRotation(hair01, 0F, 0F, 0F);

		body.addChild(breast);
		body.addChild(Shape1);

		rightleg01.addChild(rightleg02);
		rightleg01.addChild(rightleg03);
		rightleg01.addChild(rightleg04);

		leftleg01.addChild(leftleg02);
		leftleg01.addChild(leftleg03);
		leftleg01.addChild(leftleg04);

		leftarm01.addChild(leftarm02);
		leftarm01.addChild(leftwing01);
		leftarm01.addChild(leftwing02);
		leftarm01.addChild(leftwing03);

		rightarm01.addChild(rightarm02);
		rightarm01.addChild(rightwing01);
		rightarm01.addChild(rightwing02);
		rightarm01.addChild(rightwing03);

		head01.addChild(hair01);
		head01.addChild(lefthair);
		head01.addChild(righthair);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.rightleg01.rotateAngleX = 5.7f- MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leftleg01.rotateAngleX = 5.7f- MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		
		leftleg04.rotateAngleX = leftleg04.rotateAngleX-0.5f;
		rightleg04.offsetZ = rightleg04.offsetZ +0.2f;

		super.render(entity, f, f1, f2, f3, f4, f5);
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		body.render(f5);
		rightleg01.render(f5);
		leftleg01.render(f5);
		leftarm01.render(f5);
		rightarm01.render(f5);
		head01.render(f5);



	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
