package threeDitems_old.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class bucket extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
  
  public bucket()
  {
    textureWidth = 36;
    textureHeight = 24;
    
      Shape1 = new ModelRenderer(this, 0, 10);
      Shape1.addBox(-2F, -0.5F, -2F, 4, 1, 4);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(36, 24);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(-2F, -6F, -1F, 4, 6, 1);
      Shape2.setRotationPoint(-2F, 0F, 0F);
      Shape2.setTextureSize(36, 24);
      Shape2.mirror = true;
      setRotation(Shape2, -0.1745329F, -1.570796F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(-2F, -6F, -1F, 4, 6, 1);
      Shape3.setRotationPoint(0F, 0F, 2F);
      Shape3.setTextureSize(36, 24);
      Shape3.mirror = true;
      setRotation(Shape3, -0.1745329F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(-2F, -6F, -1F, 4, 6, 1);
      Shape4.setRotationPoint(2F, 0F, 0F);
      Shape4.setTextureSize(36, 24);
      Shape4.mirror = true;
      setRotation(Shape4, -0.1745329F, 1.570796F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(-2F, -6F, 0F, 4, 6, 1);
      Shape5.setRotationPoint(0F, 0F, -2F);
      Shape5.setTextureSize(36, 24);
      Shape5.mirror = true;
      setRotation(Shape5, 0.1745329F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 16, 0);
      Shape6.addBox(-1F, -6F, -1F, 1, 6, 1);
      Shape6.setRotationPoint(2F, 0F, 2F);
      Shape6.setTextureSize(36, 24);
      Shape6.mirror = true;
      setRotation(Shape6, -0.1745329F, 0F, 0.1745329F);
      Shape7 = new ModelRenderer(this, 16, 0);
      Shape7.addBox(0F, -6F, -1F, 1, 6, 1);
      Shape7.setRotationPoint(-2F, 0F, 2F);
      Shape7.setTextureSize(36, 24);
      Shape7.mirror = true;
      setRotation(Shape7, -0.1745329F, 0F, -0.1745329F);
      Shape8 = new ModelRenderer(this, 16, 0);
      Shape8.addBox(-1F, -6F, 0F, 1, 6, 1);
      Shape8.setRotationPoint(2F, 0F, -2F);
      Shape8.setTextureSize(36, 24);
      Shape8.mirror = true;
      setRotation(Shape8, 0.1745329F, 0F, 0.1745329F);
      Shape9 = new ModelRenderer(this, 16, 0);
      Shape9.addBox(0F, -6F, 0F, 1, 6, 1);
      Shape9.setRotationPoint(-2F, 0F, -2F);
      Shape9.setTextureSize(36, 24);
      Shape9.mirror = true;
      setRotation(Shape9, 0.1745329F, 0F, -0.1745329F);
      Shape10 = new ModelRenderer(this, 1, 17);
      Shape10.addBox(-2F, 0F, -3F, 6, 1, 6);
      Shape10.setRotationPoint(0F, -4F, 0F);
      Shape10.setTextureSize(36, 24);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0.8028515F);
      Shape11 = new ModelRenderer(this, 16, 9);
      Shape11.addBox(-2.5F, -5.5F, -2.5F, 5, 1, 5);
      Shape11.setRotationPoint(0F, 0F, 0F);
      Shape11.setTextureSize(36, 24);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
