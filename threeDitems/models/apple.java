package threeDitems.models;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class apple extends ModelBase
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
  
  public apple()
  {
    textureWidth = 16;
    textureHeight = 8;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 1, 1, 1);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(16, 8);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(-1.5F, -3F, -1.5F, 4, 3, 4);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(16, 8);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(-0.5F, 0F, -1.5F, 2, 1, 1);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(16, 8);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(-0.5F, 0F, 1.5F, 2, 1, 1);
      Shape4.setRotationPoint(0F, 0F, 0F);
      Shape4.setTextureSize(16, 8);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(1.5F, 0F, -0.5F, 1, 1, 2);
      Shape5.setRotationPoint(0F, 0F, 0F);
      Shape5.setTextureSize(16, 8);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(-1.5F, 0F, -0.5F, 1, 1, 2);
      Shape6.setRotationPoint(0F, 0F, 0F);
      Shape6.setTextureSize(16, 8);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(-0.5F, -3F, -2F, 2, 3, 5);
      Shape7.setRotationPoint(0F, 0F, 0F);
      Shape7.setTextureSize(16, 8);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(-2F, -3F, -0.5F, 5, 3, 2);
      Shape8.setRotationPoint(0F, 0F, 0F);
      Shape8.setTextureSize(16, 8);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 0, 0);
      Shape9.addBox(-0.5F, -4.5F, -0.5F, 2, 1, 2);
      Shape9.setRotationPoint(0F, 0F, 0F);
      Shape9.setTextureSize(16, 8);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 0, 0);
      Shape10.addBox(-1.5F, -4F, -0.5F, 4, 1, 2);
      Shape10.setRotationPoint(0F, 0F, 0F);
      Shape10.setTextureSize(16, 8);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 0, 0);
      Shape11.addBox(-0.5F, -4F, -1.5F, 2, 1, 4);
      Shape11.setRotationPoint(0F, 0F, 0F);
      Shape11.setTextureSize(16, 8);
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
