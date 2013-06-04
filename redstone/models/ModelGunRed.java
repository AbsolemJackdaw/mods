package redstone.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGunRed extends ModelBase
{
  //fields
    ModelRenderer bottom_right_energy;
    ModelRenderer body;
    ModelRenderer bottom;
    ModelRenderer top_right;
    ModelRenderer left_energy;
    ModelRenderer f_right_energy_post;
    ModelRenderer f_left_energy_post;
    ModelRenderer left_top_barrel;
    ModelRenderer bottom_barrel;
    ModelRenderer top_back;
    ModelRenderer top_right_barrel;
    ModelRenderer top_right_frontback;
    ModelRenderer top_left_frontback;
    ModelRenderer top_barrel;
    ModelRenderer top_left;
    ModelRenderer top_base;
    ModelRenderer middle_barrel;
    ModelRenderer back_left_energy;
    ModelRenderer bottom_energy;
    ModelRenderer left_forward_energy;
    ModelRenderer right_energy;
  
  public ModelGunRed()
  {
    textureWidth = 64;
    textureHeight = 32;
    

      bottom_right_energy = new ModelRenderer(this, 0, 23);
      bottom_right_energy.addBox(0F, 0F, 0F, 1, 1, 5);
      bottom_right_energy.setRotationPoint(-1F, 0F, 13F);
      bottom_right_energy.setTextureSize(64, 32);
      bottom_right_energy.mirror = true;
      setRotation(bottom_right_energy, 0F, 0F, 0F);
      body = new ModelRenderer(this, 0, 0);
      body.addBox(0F, 0F, 0F, 6, 3, 7);
      body.setRotationPoint(-1F, -1F, 6F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      bottom = new ModelRenderer(this, 0, 17);
      bottom.addBox(0F, 0F, 0F, 4, 1, 5);
      bottom.setRotationPoint(0F, 2F, 6F);
      bottom.setTextureSize(64, 32);
      bottom.mirror = true;
      setRotation(bottom, 0F, 0F, 0F);
      top_right = new ModelRenderer(this, 44, 0);
      top_right.addBox(0F, 0F, 0F, 1, 1, 9);
      top_right.setRotationPoint(-1F, -2F, 13F);
      top_right.setTextureSize(64, 32);
      top_right.mirror = true;
      setRotation(top_right, 0F, 0F, 0F);
      left_energy = new ModelRenderer(this, 28, 0);
      left_energy.addBox(0F, 0F, 0F, 1, 1, 3);
      left_energy.setRotationPoint(3F, -1F, 14F);
      left_energy.setTextureSize(64, 32);
      left_energy.mirror = true;
      setRotation(left_energy, 0F, 0F, 0F);
      f_right_energy_post = new ModelRenderer(this, 0, 0);
      f_right_energy_post.addBox(0F, 0F, 0F, 1, 1, 1);
      f_right_energy_post.setRotationPoint(-1F, -1F, 13F);
      f_right_energy_post.setTextureSize(64, 32);
      f_right_energy_post.mirror = true;
      setRotation(f_right_energy_post, 0F, 0F, 0F);
      f_left_energy_post = new ModelRenderer(this, 0, 0);
      f_left_energy_post.addBox(0F, 0F, 0F, 1, 1, 1);
      f_left_energy_post.setRotationPoint(-1F, -1F, 17F);
      f_left_energy_post.setTextureSize(64, 32);
      f_left_energy_post.mirror = true;
      setRotation(f_left_energy_post, 0F, 0F, 0F);
      left_top_barrel = new ModelRenderer(this, 0, 10);
      left_top_barrel.addBox(0F, 0F, 0F, 1, 3, 1);
      left_top_barrel.setRotationPoint(3F, -1F, 5F);
      left_top_barrel.setTextureSize(64, 32);
      left_top_barrel.mirror = true;
      setRotation(left_top_barrel, 0F, 0F, 0F);
      bottom_barrel = new ModelRenderer(this, 4, 12);
      bottom_barrel.addBox(0F, 0F, 0F, 2, 1, 1);
      bottom_barrel.setRotationPoint(1F, 2F, 5F);
      bottom_barrel.setTextureSize(64, 32);
      bottom_barrel.mirror = true;
      setRotation(bottom_barrel, 0F, 0F, 0F);
      top_back = new ModelRenderer(this, 41, 0);
      top_back.addBox(0F, 0F, 0F, 2, 1, 3);
      top_back.setRotationPoint(1F, -2F, 15F);
      top_back.setTextureSize(64, 32);
      top_back.mirror = true;
      setRotation(top_back, 0F, 0F, 0F);
      top_right_barrel = new ModelRenderer(this, 0, 10);
      top_right_barrel.addBox(0F, 0F, 0F, 1, 3, 1);
      top_right_barrel.setRotationPoint(0F, -1F, 5F);
      top_right_barrel.setTextureSize(64, 32);
      top_right_barrel.mirror = true;
      setRotation(top_right_barrel, 0F, 0F, 0F);
      top_right_frontback = new ModelRenderer(this, 30, 15);
      top_right_frontback.addBox(0F, 0F, 0F, 1, 1, 16);
      top_right_frontback.setRotationPoint(0F, -2F, 6F);
      top_right_frontback.setTextureSize(64, 32);
      top_right_frontback.mirror = true;
      setRotation(top_right_frontback, 0F, 0F, 0F);
      top_left_frontback = new ModelRenderer(this, 30, 15);
      top_left_frontback.addBox(0F, 0F, 0F, 1, 1, 16);
      top_left_frontback.setRotationPoint(3F, -2F, 6F);
      top_left_frontback.setTextureSize(64, 32);
      top_left_frontback.mirror = true;
      setRotation(top_left_frontback, 0F, 0F, 0F);
      top_barrel = new ModelRenderer(this, 4, 10);
      top_barrel.addBox(0F, 0F, 0F, 2, 1, 1);
      top_barrel.setRotationPoint(1F, -2F, 5F);
      top_barrel.setTextureSize(64, 32);
      top_barrel.mirror = true;
      setRotation(top_barrel, 0F, 0F, 0F);
      top_left = new ModelRenderer(this, 44, 0);
      top_left.addBox(0F, 0F, 0F, 1, 1, 9);
      top_left.setRotationPoint(4F, -2F, 13F);
      top_left.setTextureSize(64, 32);
      top_left.mirror = true;
      setRotation(top_left, 0F, 0F, 0F);
      top_base = new ModelRenderer(this, 42, 0);
      top_base.addBox(0F, 0F, 0F, 2, 1, 9);
      top_base.setRotationPoint(1F, -3F, 6F);
      top_base.setTextureSize(64, 32);
      top_base.mirror = true;
      setRotation(top_base, 0F, 0F, 0F);
      middle_barrel = new ModelRenderer(this, 0, 14);
      middle_barrel.addBox(0F, 0F, 0.1F, 4, 1, 1);
      middle_barrel.setRotationPoint(0F, 0F, 5F);
      middle_barrel.setTextureSize(64, 32);
      middle_barrel.mirror = true;
      setRotation(middle_barrel, 0F, 0F, 0F);
      back_left_energy = new ModelRenderer(this, 0, 0);
      back_left_energy.addBox(0F, 0F, 0F, 1, 1, 1);
      back_left_energy.setRotationPoint(4F, -1F, 17F);
      back_left_energy.setTextureSize(64, 32);
      back_left_energy.mirror = true;
      setRotation(back_left_energy, 0F, 0F, 0F);
      bottom_energy = new ModelRenderer(this, 0, 23);
      bottom_energy.addBox(0F, 0F, 0F, 1, 1, 5);
      bottom_energy.setRotationPoint(4F, 0F, 13F);
      bottom_energy.setTextureSize(64, 32);
      bottom_energy.mirror = true;
      setRotation(bottom_energy, 0F, 0F, 0F);
      left_forward_energy = new ModelRenderer(this, 0, 0);
      left_forward_energy.addBox(0F, 0F, 0F, 1, 1, 1);
      left_forward_energy.setRotationPoint(4F, -1F, 13F);
      left_forward_energy.setTextureSize(64, 32);
      left_forward_energy.mirror = true;
      setRotation(left_forward_energy, 0F, 0F, 0F);
      right_energy = new ModelRenderer(this, 28, 0);
      right_energy.addBox(0F, 0F, 0F, 1, 1, 3);
      right_energy.setRotationPoint(0F, -1F, 14F);
      right_energy.setTextureSize(64, 32);
      right_energy.mirror = true;
      setRotation(right_energy, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    bottom_right_energy.render(f5);
    body.render(f5);
    bottom.render(f5);
    top_right.render(f5);
    left_energy.render(f5);
    f_right_energy_post.render(f5);
    f_left_energy_post.render(f5);
    left_top_barrel.render(f5);
    bottom_barrel.render(f5);
    top_back.render(f5);
    top_right_barrel.render(f5);
    top_right_frontback.render(f5);
    top_left_frontback.render(f5);
    top_barrel.render(f5);
    top_left.render(f5);
    top_base.render(f5);
    middle_barrel.render(f5);
    back_left_energy.render(f5);
    bottom_energy.render(f5);
    left_forward_energy.render(f5);
    right_energy.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
//  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
//  {
//    super.setRotationAngles(f, f1, f2, f3, f4, f5);
//  }

}
