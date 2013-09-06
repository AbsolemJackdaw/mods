package threeDitems_old.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class wheat extends ModelBase
{
  //fields
    ModelRenderer BaseSolo2;
    ModelRenderer TopHalmRight1;
    ModelRenderer TopHalmLeft;
    ModelRenderer TopHalmBack;
    ModelRenderer TopHalmRight2;
    ModelRenderer MidHalmFront;
    ModelRenderer MidHalmLeft;
    ModelRenderer MidHalmRight;
    ModelRenderer MidHalmBack;
    ModelRenderer Base;
    ModelRenderer BaseFront;
    ModelRenderer BaseRight;
    ModelRenderer BaseBack;
    ModelRenderer BottomHalm;
    ModelRenderer TopHalmFront;
    ModelRenderer BaseLeft;
    ModelRenderer BottomHalmSmall;
    ModelRenderer BaseSolo1;
  
  public wheat()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      BaseSolo2 = new ModelRenderer(this, 4, 6);
      BaseSolo2.addBox(0F, 0F, 0F, 1, 4, 1);
      BaseSolo2.setRotationPoint(-6F, -4F, -9F);
      BaseSolo2.setTextureSize(64, 32);
      BaseSolo2.mirror = true;
      setRotation(BaseSolo2, 0F, 0F, 0F);
      TopHalmRight1 = new ModelRenderer(this, 0, 23);
      TopHalmRight1.addBox(0F, 0F, 0F, 1, 1, 1);
      TopHalmRight1.setRotationPoint(-9F, -4F, -8F);
      TopHalmRight1.setTextureSize(64, 32);
      TopHalmRight1.mirror = true;
      setRotation(TopHalmRight1, 0F, 0F, 0F);
      TopHalmLeft = new ModelRenderer(this, 4, 30);
      TopHalmLeft.addBox(0F, 0F, 0F, 2, 1, 1);
      TopHalmLeft.setRotationPoint(-5F, -3F, -9F);
      TopHalmLeft.setTextureSize(64, 32);
      TopHalmLeft.mirror = true;
      setRotation(TopHalmLeft, 0F, 0F, 0F);
      TopHalmBack = new ModelRenderer(this, 0, 25);
      TopHalmBack.addBox(0F, 0F, 0F, 1, 2, 1);
      TopHalmBack.setRotationPoint(-6F, -4F, -6F);
      TopHalmBack.setTextureSize(64, 32);
      TopHalmBack.mirror = true;
      setRotation(TopHalmBack, 0F, 0F, 0F);
      TopHalmRight2 = new ModelRenderer(this, 0, 20);
      TopHalmRight2.addBox(0F, 0F, 0F, 1, 2, 1);
      TopHalmRight2.setRotationPoint(-10F, -4F, -8F);
      TopHalmRight2.setTextureSize(64, 32);
      TopHalmRight2.mirror = true;
      setRotation(TopHalmRight2, 0F, 0F, 0F);
      MidHalmFront = new ModelRenderer(this, 0, 15);
      MidHalmFront.addBox(0F, 0F, 0F, 1, 4, 1);
      MidHalmFront.setRotationPoint(-7F, -3F, -10F);
      MidHalmFront.setTextureSize(64, 32);
      MidHalmFront.mirror = true;
      setRotation(MidHalmFront, 0F, 0F, 0F);
      MidHalmLeft = new ModelRenderer(this, 0, 11);
      MidHalmLeft.addBox(0F, 0F, 0F, 1, 3, 1);
      MidHalmLeft.setRotationPoint(-5F, -2F, -9F);
      MidHalmLeft.setTextureSize(64, 32);
      MidHalmLeft.mirror = true;
      setRotation(MidHalmLeft, 0F, 0F, 0F);
      MidHalmRight = new ModelRenderer(this, 4, 19);
      MidHalmRight.addBox(0F, 0F, 0F, 1, 5, 1);
      MidHalmRight.setRotationPoint(-8F, -4F, -8F);
      MidHalmRight.setTextureSize(64, 32);
      MidHalmRight.mirror = true;
      setRotation(MidHalmRight, 0F, 0F, 0F);
      MidHalmBack = new ModelRenderer(this, 4, 25);
      MidHalmBack.addBox(0F, 0F, 0F, 1, 4, 1);
      MidHalmBack.setRotationPoint(-6F, -3F, -7F);
      MidHalmBack.setTextureSize(64, 32);
      MidHalmBack.mirror = true;
      setRotation(MidHalmBack, 0F, 0F, 0F);
      Base = new ModelRenderer(this, 8, 23);
      Base.addBox(0F, 0F, 0F, 2, 5, 2);
      Base.setRotationPoint(-7F, 0F, -9F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      BaseFront = new ModelRenderer(this, 8, 5);
      BaseFront.addBox(0F, 0F, 0F, 1, 5, 1);
      BaseFront.setRotationPoint(-6F, 0F, -10F);
      BaseFront.setTextureSize(64, 32);
      BaseFront.mirror = true;
      setRotation(BaseFront, 0F, 0F, 0F);
      BaseRight = new ModelRenderer(this, 8, 11);
      BaseRight.addBox(0F, 0F, 0F, 1, 5, 1);
      BaseRight.setRotationPoint(-8F, 0F, -9F);
      BaseRight.setTextureSize(64, 32);
      BaseRight.mirror = true;
      setRotation(BaseRight, 0F, 0F, 0F);
      BaseBack = new ModelRenderer(this, 0, 2);
      BaseBack.addBox(0F, 0F, 0F, 1, 5, 1);
      BaseBack.setRotationPoint(-7F, 0F, -7F);
      BaseBack.setTextureSize(64, 32);
      BaseBack.mirror = true;
      setRotation(BaseBack, 0F, 0F, 0F);
      BottomHalm = new ModelRenderer(this, 0, 8);
      BottomHalm.addBox(0F, 0F, 0F, 1, 2, 1);
      BottomHalm.setRotationPoint(-7F, 5F, -9F);
      BottomHalm.setTextureSize(64, 32);
      BottomHalm.mirror = true;
      setRotation(BottomHalm, 0F, 0F, 0F);
      TopHalmFront = new ModelRenderer(this, 0, 28);
      TopHalmFront.addBox(0F, 0F, 0F, 1, 3, 1);
      TopHalmFront.setRotationPoint(-7F, -5F, -11F);
      TopHalmFront.setTextureSize(64, 32);
      TopHalmFront.mirror = true;
      setRotation(TopHalmFront, 0F, 0F, 0F);
      BaseLeft = new ModelRenderer(this, 8, 17);
      BaseLeft.addBox(0F, 0F, 0F, 1, 5, 1);
      BaseLeft.setRotationPoint(-5F, 0F, -8F);
      BaseLeft.setTextureSize(64, 32);
      BaseLeft.mirror = true;
      setRotation(BaseLeft, 0F, 0F, 0F);
      BottomHalmSmall = new ModelRenderer(this, 4, 17);
      BottomHalmSmall.addBox(0F, 0F, 0F, 1, 1, 1);
      BottomHalmSmall.setRotationPoint(-6F, 5F, -8F);
      BottomHalmSmall.setTextureSize(64, 32);
      BottomHalmSmall.mirror = true;
      setRotation(BottomHalmSmall, 0F, 0F, 0F);
      BaseSolo1 = new ModelRenderer(this, 4, 11);
      BaseSolo1.addBox(0F, 0F, 0F, 1, 5, 1);
      BaseSolo1.setRotationPoint(-7F, -5F, -8F);
      BaseSolo1.setTextureSize(64, 32);
      BaseSolo1.mirror = true;
      setRotation(BaseSolo1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    BaseSolo2.render(f5);
    TopHalmRight1.render(f5);
    TopHalmLeft.render(f5);
    TopHalmBack.render(f5);
    TopHalmRight2.render(f5);
    MidHalmFront.render(f5);
    MidHalmLeft.render(f5);
    MidHalmRight.render(f5);
    MidHalmBack.render(f5);
    Base.render(f5);
    BaseFront.render(f5);
    BaseRight.render(f5);
    BaseBack.render(f5);
    BottomHalm.render(f5);
    TopHalmFront.render(f5);
    BaseLeft.render(f5);
    BottomHalmSmall.render(f5);
    BaseSolo1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
