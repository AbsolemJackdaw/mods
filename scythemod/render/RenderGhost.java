package scythemod.render;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

import scythemod.entity.EntityGhost;

public class RenderGhost extends RenderLiving
{
	private int field_77068_a;

	public RenderGhost(ModelBlaze modelblaze, float f)
	{
		super (modelblaze, f);
	}

	protected void preRenderStuff(EntityGhost par1EntityShadowGoliath, float par2)
	{
		float scale = 0.9f;
		GL11.glScalef(scale, scale, scale);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glFrontFace(GL11.GL_CCW);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11. GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {

		this.preRenderStuff((EntityGhost)par1EntityLiving, par2);
	}

	public void renderBlaze(EntityGhost par1EntityGhost, double par2, double par4, double par6, float par8, float par9)
	{
		int var10 = ((ModelBlaze)this.mainModel).func_78104_a();

		if (var10 != this.field_77068_a)
		{
			this.field_77068_a = var10;
			this.mainModel = new ModelBlaze();
		}

		super.doRenderLiving(par1EntityGhost, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderBlaze((EntityGhost)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderBlaze((EntityGhost)par1Entity, par2, par4, par6, par8, par9);
	}
}
