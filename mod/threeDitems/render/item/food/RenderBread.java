package threeDitems.render.item.food;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderBread extends Render3DInterface{


	public RenderBread(ModelBase model, String texture) {
		super(model, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(180,1,0,0);

		GL11.glTranslatef(-0.1f, -0.4f, -0.1f);
		float f = 1.5f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(95,0,0,1);
		GL11.glRotatef(180,1,0,0);

		GL11.glTranslatef(0.2f, 0.2f, -0.2f);

		float f = 0.7f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(55,0,1,0);
		GL11.glRotatef(190,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, -1f, 0.3f);
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);
	}
}
