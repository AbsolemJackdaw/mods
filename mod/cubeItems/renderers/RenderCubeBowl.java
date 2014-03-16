package cubeItems.renderers;

import org.lwjgl.opengl.GL11;

import cubeItems.ModelCubeWorld;
import cubeItems.RenderCubeInterface;

public class RenderCubeBowl extends RenderCubeInterface {

	public RenderCubeBowl(ModelCubeWorld model) {
		super(model);
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(-0.5f, -0.3f, 0.5f);

	}

	@Override
	public void renderEquipped() {

		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(45, 0, 0, 1);

		GL11.glTranslatef(0.3f, -0.2f, 0.4f);
	}

	@Override
	public void renderEquippedFP() {

		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(-0.3f, 0.2f, 0.7f);

	}

	@Override
	public void renderScale() {

		float f = 1.5f;
		GL11.glScalef(f, f, f);

	}
}
