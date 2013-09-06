package threeDitems_old.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class sword extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
  
  public sword()
  {
    textureWidth = 27;
    textureHeight = 14;
    
      Shape1 = new ModelRenderer(this, 13, 0);
      Shape1.addBox(0F, 0F, -3F, 1, 1, 6);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(27, 14);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, -2F, 3F, 1, 5, 1);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(27, 14);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, -1F, 4F, 1, 3, 11);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(27, 14);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 4, 0);
      Shape4.addBox(0F, -0.5F, 15F, 1, 2, 1);
      Shape4.setRotationPoint(0F, 0F, 0F);
      Shape4.setTextureSize(27, 14);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 4, 3);
      Shape5.addBox(0F, 0F, 16F, 1, 1, 1);
      Shape5.setRotationPoint(0F, 0F, 0F);
      Shape5.setTextureSize(27, 14);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 1, 2);
      Shape6.addBox(0.5F, -0.5F, 4F, 1, 2, 10);
      Shape6.setRotationPoint(0F, 0F, 0F);
      Shape6.setTextureSize(27, 14);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 1, 2);
      Shape7.addBox(-0.5F, -0.5F, 4F, 1, 2, 10);
      Shape7.setRotationPoint(0F, 0F, 0F);
      Shape7.setTextureSize(27, 14);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
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
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
