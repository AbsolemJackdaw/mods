package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderBriquet extends Render3DInterface{

	/**Used for quick rendering. Basic and general code that
	 * should work on any proper modeled model*/
	public RenderBriquet(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 0f, 0f);

		float f = 2f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(-75,0,1,0);
		GL11.glRotatef(15,0,0,1);
		GL11.glRotatef(90,1,0,0);

		GL11.glTranslatef(0.2f, -0.3f, -0.1f);

		float f = 0.7f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(55,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(90,1,0,0);

		GL11.glTranslatef(-0.2f, 0.8f, -0.7f);
	}

	@Override
	public void renderScale() {
		float f = 2f;
		GL11.glScalef(f, f, f);
	}

}
