package cubeItems.renderers.food;

import org.lwjgl.opengl.GL11;

import cubeItems.ModelCubeWorld;
import cubeItems.RenderCubeInterface;

public class RenderCubeDiscFood extends RenderCubeInterface {

	public RenderCubeDiscFood(ModelCubeWorld model) {
		super(model);
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(-0.3f, 0f, 0.3f);

	}

	@Override
	public void renderEquipped() {

		GL11.glScalef(0.5f, 0.5f, 0.5f);
		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(0.3f, -0.05f, 0.0f);
	}

	@Override
	public void renderEquippedFP() {

		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(0f, 0.5f, 0.2f);

	}

	@Override
	public void renderScale() {

		float f = 3f;
		GL11.glScalef(f, f, f);

	}
}
