package redstone.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGunBlue extends ModelBase
{
	//fields
	ModelRenderer left_barrel;
	ModelRenderer bottom_barrel;
	ModelRenderer left_energy;
	ModelRenderer middle_barrel;
	ModelRenderer right_barrel;
	ModelRenderer top_barrel;
	ModelRenderer back_left_body;
	ModelRenderer top_energy_case;
	ModelRenderer top_right_energy_case;
	ModelRenderer back_right_body;
	ModelRenderer front_left_energy_case_post;
	ModelRenderer front_right_energy_post;
	ModelRenderer back_body;
	ModelRenderer back_left_energy_case_post;
	ModelRenderer top_left_energy_case;
	ModelRenderer top_back_energy_post;
	ModelRenderer back_right_energy_case_post;
	ModelRenderer main_energy;
	ModelRenderer right_energy;

	public ModelGunBlue()
	{
		textureWidth = 64;
		textureHeight = 32;

		left_barrel = new ModelRenderer(this, 26, 0);
		left_barrel.addBox(0F, 0F, 0F, 1, 2, 3);
		left_barrel.setRotationPoint(4F, 3F, 0F);
		left_barrel.setTextureSize(64, 32);
		left_barrel.mirror = true;
		setRotation(left_barrel, 0F, 0F, 0F);
		bottom_barrel = new ModelRenderer(this, 36, 0);
		bottom_barrel.addBox(0F, 0F, 0F, 3, 1, 8);
		bottom_barrel.setRotationPoint(1F, 5F, 0F);
		bottom_barrel.setTextureSize(64, 32);
		bottom_barrel.mirror = true;
		setRotation(bottom_barrel, 0F, 0F, 0F);
		left_energy = new ModelRenderer(this, 0, 27);
		left_energy.addBox(0F, 0F, 0F, 1, 2, 2);
		left_energy.setRotationPoint(3F, 3F, 1F);
		left_energy.setTextureSize(64, 32);
		left_energy.mirror = true;
		setRotation(left_energy, 0F, 0F, 0F);
		middle_barrel = new ModelRenderer(this, 26, 5);
		middle_barrel.addBox(0F, 0F, 0F, 1, 2, 3);
		middle_barrel.setRotationPoint(2F, 3F, 0F);
		middle_barrel.setTextureSize(64, 32);
		middle_barrel.mirror = true;
		setRotation(middle_barrel, 0F, 0F, 0F);
		right_barrel = new ModelRenderer(this, 26, 0);
		right_barrel.addBox(0F, 0F, 0F, 1, 2, 3);
		right_barrel.setRotationPoint(0F, 3F, 0F);
		right_barrel.setTextureSize(64, 32);
		right_barrel.mirror = true;
		setRotation(right_barrel, 0F, 0F, 0F);
		top_barrel = new ModelRenderer(this, 27, 20);
		top_barrel.addBox(0F, 0F, 0F, 3, 1, 3);
		top_barrel.setRotationPoint(1F, 2F, 0F);
		top_barrel.setTextureSize(64, 32);
		top_barrel.mirror = true;
		setRotation(top_barrel, 0F, 0F, 0F);
		back_left_body = new ModelRenderer(this, 27, 10);
		back_left_body.addBox(0F, 0F, 0F, 1, 2, 8);
		back_left_body.setRotationPoint(4F, 3F, 3F);
		back_left_body.setTextureSize(64, 32);
		back_left_body.mirror = true;
		setRotation(back_left_body, 0F, 0F, 0F);
		top_energy_case = new ModelRenderer(this, 17, 0);
		top_energy_case.addBox(0F, 0F, 0F, 1, 1, 3);
		top_energy_case.setRotationPoint(2F, 1F, 1F);
		top_energy_case.setTextureSize(64, 32);
		top_energy_case.mirror = true;
		setRotation(top_energy_case, 0F, 0F, 0F);
		top_right_energy_case = new ModelRenderer(this, 0, 10);
		top_right_energy_case.addBox(0F, 0F, 0F, 1, 1, 8);
		top_right_energy_case.setRotationPoint(1F, 1F, 3F);
		top_right_energy_case.setTextureSize(64, 32);
		top_right_energy_case.mirror = true;
		setRotation(top_right_energy_case, 0F, 0F, 0F);
		back_right_body = new ModelRenderer(this, 27, 10);
		back_right_body.addBox(0F, 0F, 0F, 1, 2, 8);
		back_right_body.setRotationPoint(0F, 3F, 3F);
		back_right_body.setTextureSize(64, 32);
		back_right_body.mirror = true;
		setRotation(back_right_body, 0F, 0F, 0F);
		front_left_energy_case_post = new ModelRenderer(this, 0, 20);
		front_left_energy_case_post.addBox(0F, 0F, 0F, 1, 1, 1);
		front_left_energy_case_post.setRotationPoint(4F, 2F, 3F);
		front_left_energy_case_post.setTextureSize(64, 32);
		front_left_energy_case_post.mirror = true;
		setRotation(front_left_energy_case_post, 0F, 0F, 0F);
		front_right_energy_post = new ModelRenderer(this, 0, 20);
		front_right_energy_post.addBox(0F, 0F, 0F, 1, 1, 1);
		front_right_energy_post.setRotationPoint(0F, 2F, 3F);
		front_right_energy_post.setTextureSize(64, 32);
		front_right_energy_post.mirror = true;
		setRotation(front_right_energy_post, 0F, 0F, 0F);
		back_body = new ModelRenderer(this, 0, 6);
		back_body.addBox(0F, 0F, 0F, 3, 3, 1);
		back_body.setRotationPoint(1F, 2F, 8F);
		back_body.setTextureSize(64, 32);
		back_body.mirror = true;
		setRotation(back_body, 0F, 0F, 0F);
		back_left_energy_case_post = new ModelRenderer(this, 0, 20);
		back_left_energy_case_post.addBox(0F, 0F, 0F, 1, 1, 1);
		back_left_energy_case_post.setRotationPoint(4F, 2F, 8F);
		back_left_energy_case_post.setTextureSize(64, 32);
		back_left_energy_case_post.mirror = true;
		setRotation(back_left_energy_case_post, 0F, 0F, 0F);
		top_left_energy_case = new ModelRenderer(this, 0, 10);
		top_left_energy_case.addBox(0F, 0F, 0F, 1, 1, 8);
		top_left_energy_case.setRotationPoint(3F, 1F, 3F);
		top_left_energy_case.setTextureSize(64, 32);
		top_left_energy_case.mirror = true;
		setRotation(top_left_energy_case, 0F, 0F, 0F);
		top_back_energy_post = new ModelRenderer(this, 0, 20);
		top_back_energy_post.addBox(0F, 0F, 0F, 1, 1, 1);
		top_back_energy_post.setRotationPoint(2F, 1F, 8F);
		top_back_energy_post.setTextureSize(64, 32);
		top_back_energy_post.mirror = true;
		setRotation(top_back_energy_post, 0F, 0F, 0F);
		back_right_energy_case_post = new ModelRenderer(this, 0, 20);
		back_right_energy_case_post.addBox(0F, 0F, 0F, 1, 1, 1);
		back_right_energy_case_post.setRotationPoint(0F, 2F, 8F);
		back_right_energy_case_post.setTextureSize(64, 32);
		back_right_energy_case_post.mirror = true;
		setRotation(back_right_energy_case_post, 0F, 0F, 0F);
		main_energy = new ModelRenderer(this, 0, 23);
		main_energy.addBox(0F, 0F, 0F, 3, 3, 5);
		main_energy.setRotationPoint(1F, 2F, 3F);
		main_energy.setTextureSize(64, 32);
		main_energy.mirror = true;
		setRotation(main_energy, 0F, 0F, 0F);
		right_energy = new ModelRenderer(this, 0, 27);
		right_energy.addBox(0F, 0F, 0F, 1, 2, 2);
		right_energy.setRotationPoint(1F, 3F, 1F);
		right_energy.setTextureSize(64, 32);
		right_energy.mirror = true;
		setRotation(right_energy, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		left_barrel.render(f5);
		bottom_barrel.render(f5);
		left_energy.render(f5);
		middle_barrel.render(f5);
		right_barrel.render(f5);
		top_barrel.render(f5);
		back_left_body.render(f5);
		top_energy_case.render(f5);
		top_right_energy_case.render(f5);
		back_right_body.render(f5);
		front_left_energy_case_post.render(f5);
		front_right_energy_post.render(f5);
		back_body.render(f5);
		back_left_energy_case_post.render(f5);
		top_left_energy_case.render(f5);
		top_back_energy_post.render(f5);
		back_right_energy_case_post.render(f5);
		main_energy.render(f5);
		right_energy.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}