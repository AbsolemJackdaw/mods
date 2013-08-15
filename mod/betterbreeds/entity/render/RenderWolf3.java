package betterbreeds.entity.render;import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import betterbreeds.entity.EntityWolf3;



public class RenderWolf3 extends RenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation("subaraki:BB/BlackWolf.png");
	private static final ResourceLocation field_110917_a = new ResourceLocation("textures/entity/wolf/wolf.png");
	private static final ResourceLocation field_110916_g = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

	protected ResourceLocation func_110914_a(EntityWolf3 par1EntityWolf)
	{
		return par1EntityWolf.isTamed() ? texture : (par1EntityWolf.isAngry() ? field_110916_g : field_110917_a);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity par1Entity)
	{
		return this.func_110914_a((EntityWolf3)par1Entity);
	}
    public RenderWolf3(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    public void renderWolf(EntityWolf3 b, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(b, par2, par4, par6, par8, par9);
        
    }

    protected float getTailRotation(EntityWolf3 par1EntityWolf, float par2)
    {
        return par1EntityWolf.getTailRotation();
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving par1EntityLiving, float par2)
    {
        return this.getTailRotation((EntityWolf3)par1EntityLiving, par2);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderWolf((EntityWolf3)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderWolf((EntityWolf3)par1Entity, par2, par4, par6, par8, par9);
    }
}
