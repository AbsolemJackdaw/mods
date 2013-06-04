package threeDitems.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class bone extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
  
  public bone()
  {
    textureWidth = 10;
    textureHeight = 5;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, -2F, 1, 1, 4);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(10, 5);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0.5F, 0.5F, -3F, 1, 1, 1);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(10, 5);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(-0.5F, -0.5F, -3F, 1, 1, 1);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(10, 5);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(0.5F, 0.5F, 2F, 1, 1, 1);
      Shape4.setRotationPoint(0F, 0F, 0F);
      Shape4.setTextureSize(10, 5);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(-0.5F, -0.5F, 2F, 1, 1, 1);
      Shape5.setRotationPoint(0F, 0F, 0F);
      Shape5.setTextureSize(10, 5);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);

    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  

}
