package threeDitems.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class fish extends ModelBase
{
  //fields
    ModelRenderer bottom_back_tail;
    ModelRenderer bottom_jaw;
    ModelRenderer top_jaw;
    ModelRenderer body;
    ModelRenderer tail_part_1;
    ModelRenderer tail_part_2;
    ModelRenderer top_back_tail;
    ModelRenderer right_fin;
    ModelRenderer left_fin;
  
  public fish()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      bottom_back_tail = new ModelRenderer(this, 29, 9);
      bottom_back_tail.addBox(0F, 0F, -2F, 1, 4, 6);
      bottom_back_tail.setRotationPoint(3F, 2F, 21F);
      bottom_back_tail.setTextureSize(64, 32);
      bottom_back_tail.mirror = true;
      setRotation(bottom_back_tail, -0.7063871F, 0F, 0F);
      bottom_jaw = new ModelRenderer(this, 45, 12);
      bottom_jaw.addBox(0F, 0F, 0F, 3, 3, 6);
      bottom_jaw.setRotationPoint(2F, 2F, 1F);
      bottom_jaw.setTextureSize(64, 32);
      bottom_jaw.mirror = true;
      setRotation(bottom_jaw, -0.3717861F, 0F, 0F);
      top_jaw = new ModelRenderer(this, 45, 0);
      top_jaw.addBox(0F, 0F, 0F, 3, 3, 6);
      top_jaw.setRotationPoint(2F, 2F, 0F);
      top_jaw.setTextureSize(64, 32);
      top_jaw.mirror = true;
      setRotation(top_jaw, 0.4833219F, 0F, 0F);
      body = new ModelRenderer(this, 0, 15);
      body.addBox(0F, 0F, -1F, 3, 6, 11);
      body.setRotationPoint(2F, 0F, 5F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      tail_part_1 = new ModelRenderer(this, 16, 0);
      tail_part_1.addBox(0F, 0F, -1F, 3, 4, 3);
      tail_part_1.setRotationPoint(2F, 1F, 16F);
      tail_part_1.setTextureSize(64, 32);
      tail_part_1.mirror = true;
      setRotation(tail_part_1, 0F, 0F, 0F);
      tail_part_2 = new ModelRenderer(this, 0, 0);
      tail_part_2.addBox(0F, 0F, -1F, 3, 2, 2);
      tail_part_2.setRotationPoint(2F, 2F, 19F);
      tail_part_2.setTextureSize(64, 32);
      tail_part_2.mirror = true;
      setRotation(tail_part_2, 0F, 0F, 0F);
      top_back_tail = new ModelRenderer(this, 29, 22);
      top_back_tail.addBox(0F, 0F, -1F, 1, 4, 6);
      top_back_tail.setRotationPoint(3F, 1F, 18F);
      top_back_tail.setTextureSize(64, 32);
      top_back_tail.mirror = true;
      setRotation(top_back_tail, 0.7063936F, 0F, 0F);
      right_fin = new ModelRenderer(this, 31, 0);
      right_fin.addBox(0F, 2F, 0F, 1, 2, 5);
      right_fin.setRotationPoint(1F, 0F, 8F);
      right_fin.setTextureSize(64, 32);
      right_fin.mirror = true;
      setRotation(right_fin, 0F, -0.2230705F, 0F);
      left_fin = new ModelRenderer(this, 31, 0);
      left_fin.addBox(0F, 2F, 0F, 1, 2, 5);
      left_fin.setRotationPoint(5F, 0F, 8F);
      left_fin.setTextureSize(64, 32);
      left_fin.mirror = true;
      setRotation(left_fin, 0F, 0.2230717F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    bottom_back_tail.render(f5);
    bottom_jaw.render(f5);
    top_jaw.render(f5);
    body.render(f5);
    tail_part_1.render(f5);
    tail_part_2.render(f5);
    top_back_tail.render(f5);
    right_fin.render(f5);
    left_fin.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
