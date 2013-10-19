package redstone.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class defensegun extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape9;
    ModelRenderer Shape5;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
  
  public defensegun()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 1, 6, 1, -0.005f);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0.2F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 4, 0);
      Shape2.addBox(-0.5F, 0F, 0.5F, 2, 6, 2);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0.2F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 8);
      Shape3.addBox(0F, 0F, -5F, 1, 1, 8);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 18, 5);
      Shape4.addBox(0F, -1F, -5F, 2, 2, 10, 0.0005f);
      Shape4.setRotationPoint(-0.5F, -0.5F, 0F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 28, 3);
      Shape9.addBox(0F, 0F, 0F, 1, 1, 1, -0.005f);
      Shape9.setRotationPoint(0F, 0F, 0F);
      Shape9.setTextureSize(64, 32);
      Shape9.mirror = true;
      setRotation(Shape9, -0.5F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 18);
      Shape5.addBox(0F, -1F, -3.8F, 2, 1, 2, -0.005f);
      Shape5.setRotationPoint(-0.5F, 0F, 1F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, -0.4F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 21);
      Shape7.addBox(-0.5F, -2.4F, -4F, 2, 1, 2);
      Shape7.setRotationPoint(0F, 0F, 0F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 24);
      Shape8.addBox(0F, -2F, -4.8F, 1, 1, 1);
      Shape8.setRotationPoint(0F, 0F, 0F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 12, 0);
      Shape10.addBox(0F, 6F, 0.3F, 1, 2, 2);
      Shape10.setRotationPoint(0F, 0F, 0F);
      Shape10.setTextureSize(64, 32);
      Shape10.mirror = true;
      setRotation(Shape10, 0.2F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 58, 3);
      Shape11.addBox(0F, -2F, 2.5F, 1, 1, 2);
      Shape11.setRotationPoint(0F, 0F, 0F);
      Shape11.setTextureSize(64, 32);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 58, 0);
      Shape12.addBox(-0.1F, -2.2F, 2.5F, 1, 1, 2);
      Shape12.setRotationPoint(0F, 0F, 0F);
      Shape12.setTextureSize(64, 32);
      Shape12.mirror = true;
      setRotation(Shape12, 0F, 0F, 0.5F);
      Shape13 = new ModelRenderer(this, 52, 0);
      Shape13.addBox(-0.9F, -2.2F, 2.5F, 1, 1, 2);
      Shape13.setRotationPoint(1F, 0F, 0F);
      Shape13.setTextureSize(64, 32);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, -0.5F);
      Shape14 = new ModelRenderer(this, 58, 7);
      Shape14.addBox(0.8F, -1.5F, 2.5F, 1, 2, 2);
      Shape14.setRotationPoint(0F, 0F, 0F);
      Shape14.setTextureSize(64, 32);
      Shape14.mirror = true;
      setRotation(Shape14, 0F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 58, 11);
      Shape15.addBox(-0.8F, -1.5F, 2.5F, 1, 2, 2);
      Shape15.setRotationPoint(0F, 0F, 0F);
      Shape15.setTextureSize(64, 32);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 27, 26);
      Shape16.addBox(0F, 1F, -1F, 1, 1, 1);
      Shape16.setRotationPoint(0F, 0F, 0F);
      Shape16.setTextureSize(64, 32);
      Shape16.mirror = true;
      setRotation(Shape16, 0F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 47, 23);
      Shape17.addBox(0F, 0.6F, -3.6F, 1, 4, 1, - 0.005f);
      Shape17.setRotationPoint(0F, 0F, 0F);
      Shape17.setTextureSize(64, 32);
      Shape17.mirror = true;
      setRotation(Shape17, 0F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 31, 24);
      Shape18.addBox(0F, 5F, -1F, 1, 1, 3, -0.005f);
      Shape18.setRotationPoint(0F, 0F, 0F);
      Shape18.setTextureSize(64, 32);
      Shape18.mirror = true;
      setRotation(Shape18, 0F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 39, 24);
      Shape19.addBox(0F, 4.7F, -1F, 1, 1, 3);
      Shape19.setRotationPoint(0F, 0F, 0F);
      Shape19.setTextureSize(64, 32);
      Shape19.mirror = true;
      setRotation(Shape19, -0.5F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape9.render(f5);
    Shape5.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
    Shape16.render(f5);
    Shape17.render(f5);
    Shape18.render(f5);
    Shape19.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
