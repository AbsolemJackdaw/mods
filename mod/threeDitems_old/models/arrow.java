
package threeDitems_old.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class arrow extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
  
  public arrow()
  {
    textureWidth = 32;
    textureHeight = 10;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-8F, -5F, 0F, 16, 5, 0);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(32, 10);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0.2094395F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(-7.4F, -2.5F, 0.5F, 16, 5, 0);
      Shape2.setRotationPoint(0F, -3F, 0F);
      Shape2.setTextureSize(32, 10);
      Shape2.mirror = true;
      setRotation(Shape2, -1.570796F, 0F, 0.2094395F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(-8F, -5F, -2.5F, 0, 5, 5);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(32, 10);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0.2094395F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
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
