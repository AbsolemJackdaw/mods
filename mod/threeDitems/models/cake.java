package threeDitems.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class cake extends ModelBase
{
  //fields
    ModelRenderer Shape1;
  
  public cake()
  {
    textureWidth = 56;
    textureHeight = 22;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-7F, -4F, -7F, 14, 8, 14);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(56, 22);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    Shape1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
