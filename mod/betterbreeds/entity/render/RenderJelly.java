package betterbreeds.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import betterbreeds.entity.EntityJelly;
import betterbreeds.entity.model.ModelJelly;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJelly extends RenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation("subaraki:BB/Jelly.png");

	
	protected ResourceLocation func_110919_a(EntityJelly jelly)
	{
		return texture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.func_110919_a((EntityJelly)par1Entity);
	}
	
	public RenderJelly(ModelBase par1ModelBase, float par3)
	{
		super(par1ModelBase, par3);
	}

	ModelJelly jelly = new ModelJelly();

	protected void preRenderStuff(EntityJelly jelly, float par2)
	{
		GL11.glPushMatrix();
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		
		float f = 0.9f;
		GL11.glScalef(f, f, f);
		GL11.glTranslatef(0, -1.55f, 0);
		bindTexture(texture);
		this.jelly.render(0.0625f);
		GL11.glPopMatrix();
		
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		
		

	}
	
	@Override
    protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2) {
    	
    	this.preRenderStuff((EntityJelly)par1EntityLiving, par2);
    }

}
