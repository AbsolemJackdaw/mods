package threeDitems_old.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class rail_norm extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
  
  public rail_norm()
  {
    textureWidth = 50;
    textureHeight = 17;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 14, 1, 2);
      Shape1.setRotationPoint(-7F, 23F, 5F);
      Shape1.setTextureSize(50, 17);
   
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, 0F, 0F, 14, 1, 2);
      Shape2.setRotationPoint(-7F, 23F, 1F);
      Shape2.setTextureSize(50, 17);
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, 0F, 0F, 14, 1, 2);
      Shape3.setRotationPoint(-7F, 23F, -3F);
      Shape3.setTextureSize(50, 17);
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(0F, 0F, 0F, 14, 1, 2);
      Shape4.setRotationPoint(-7F, 23F, -7F);
      Shape4.setTextureSize(50, 17);
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 16, 0);
      Shape5.addBox(0F, 0F, 0F, 1, 1, 16);
      Shape5.setRotationPoint(5F, 22.5F, -8F);
      Shape5.setTextureSize(50, 17);
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 16, 0);
      Shape6.addBox(0F, 0F, 0F, 1, 1, 16);
      Shape6.setRotationPoint(-6F, 22.5F, -8F);
      Shape6.setTextureSize(50, 17);
      setRotation(Shape6, 0F, 0F, 0F);
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
  }
  
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
  }

}
