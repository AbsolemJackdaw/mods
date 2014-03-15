package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderStick extends Render3DInterface{

	public RenderStick(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEntity() {
		//		GL11.glTranslatef(0, -0.25f, 0);
		GL11.glRotatef(90, 1, 0, 0);
	}

	@Override
	public void renderEquipped() {
		float f = 0.3f;
		GL11.glTranslatef(0.65f, 0.5f, -0.05f);
		GL11.glRotatef(210, 0, 0, 1);
		GL11.glScalef(f,f,f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glTranslatef(0.7f, 1f, 0.3f);
	}

	@Override
	public void renderScale() {
		GL11.glScalef(5F,5F,5F);
	}
}