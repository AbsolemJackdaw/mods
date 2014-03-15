package threeDitems.render.item.food;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderBowl extends Render3DInterface {

	public RenderBowl(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(180,1,0,0);

		GL11.glTranslatef(-0.1f, 0f, -0.1f);
		float f = 1.5f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(15f,0,0,1);
		GL11.glRotatef(170,1,0,0);

		GL11.glTranslatef(0.2f, 0.2f, -0.4f);

		float f = 0.7f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, -0.7f, 0.2f);
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);
	}
}