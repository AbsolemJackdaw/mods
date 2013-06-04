package threeDitems.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class spidereye extends ModelBase
{
  //fields
    ModelRenderer eyehead;
    ModelRenderer eyemidde;
    ModelRenderer eyelast;
  
  public spidereye()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      eyehead = new ModelRenderer(this, 0, 0);
      eyehead.addBox(0F, 0F, 0F, 4, 4, 4);
      eyehead.setRotationPoint(-2F, -2F, -3F);
      eyehead.setTextureSize(64, 32);
      eyehead.mirror = true;
      setRotation(eyehead, 0F, 0F, 0F);
      eyemidde = new ModelRenderer(this, 0, 8);
      eyemidde.addBox(0F, 0F, 0F, 2, 2, 2);
      eyemidde.setRotationPoint(-3F, 1F, 0F);
      eyemidde.setTextureSize(64, 32);
      eyemidde.mirror = true;
      setRotation(eyemidde, 0F, 0F, 0F);
      eyelast = new ModelRenderer(this, 0, 12);
      eyelast.addBox(-2F, 0F, -1F, 2, 2, 2);
      eyelast.setRotationPoint(0F, 2F, 3F);
      eyelast.setTextureSize(64, 32);
      eyelast.mirror = true;
      setRotation(eyelast, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    eyehead.render(f5);
    eyemidde.render(f5);
    eyelast.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
