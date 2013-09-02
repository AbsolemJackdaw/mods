package redstone.ammo.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderPlasma extends Render
{

	ModelBase base;
	public RenderPlasma(ModelBase model)
	{
		base = model;
	}

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glColor3f(0f,1f,1f);
		this.renderManager.renderEngine.func_110577_a(new ResourceLocation("subaraki:rhg/plasma.png"));
		base.render(par1Entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
