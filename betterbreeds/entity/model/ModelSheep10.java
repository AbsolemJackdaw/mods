package betterbreeds.entity.model;

import betterbreeds.entity.EntitySheep5;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLiving;

public class ModelSheep10 extends ModelQuadruped
{
    private float field_78152_i;

    public ModelSheep10()
    {
        super(12, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
        this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
        float var1 = 0.5F;
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
        this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
        this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
        this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
        this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4)
    {
        super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
        this.head.rotationPointY = 6.0F + ((EntitySheep5)par1EntityLiving).func_70894_j(par4) * 9.0F;
        this.field_78152_i = ((EntitySheep5)par1EntityLiving).func_70890_k(par4);
    }

    /**
     * Sets the models various rotation angles.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, null);
        this.head.rotateAngleX = this.field_78152_i;
    }
}
