package betterbreeds.entity.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

import betterbreeds.entity.EntitySheep2;

public class ModelSheep4 extends ModelQuadruped
{
    private float field_78153_i;
    ModelRenderer udders;
    public ModelSheep4()
    {
        super(12, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
        
        udders = new ModelRenderer(this, 52, 0);
        udders.addBox(-3F, -6F, 0F, 6, 8, 2);
        udders.setRotationPoint(0F, 15.5F, 4F);
        udders.setTextureSize(64, 32);
        setRotation(udders, 1.570796F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
      super.render(entity, f, f1, f2, f3, f4, f5);
      setRotationAngles(f, f1, f2, f3, f4, f5);
      if (this.isChild)
      {
          float var8 = 2.0F;
          
          GL11.glPushMatrix();
          GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
          GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
         
          this.udders.render(f5);
          GL11.glPopMatrix();
      }
      else
      {
    	  this.udders.render(f5);
      }
    }
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }
    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4)
    {
        super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
        this.head.rotationPointY = 6.0F + ((EntitySheep2)par1EntityLiving).func_70894_j(par4) * 9.0F;
        this.field_78153_i = ((EntitySheep2)par1EntityLiving).func_70890_k(par4);
    }

    /**
     * Sets the models various rotation angles.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, null);
        this.head.rotateAngleX = this.field_78153_i;
    }
}
