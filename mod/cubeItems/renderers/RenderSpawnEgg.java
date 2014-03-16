package cubeItems.renderers;

import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cubeItems.ModelCubeWorld;
import cubeItems.RenderCubeInterface;

public class RenderSpawnEgg extends RenderCubeInterface {

	ModelCubeWorld egg_base = new ModelCubeWorld(
			ModelCubeWorld.class
					.getResourceAsStream("/assets/subaraki/cubeModels/egg_base.cub"));
	ModelCubeWorld egg_overlay = new ModelCubeWorld(
			ModelCubeWorld.class
					.getResourceAsStream("/assets/subaraki/cubeModels/egg_overlay.cub"));

	public RenderSpawnEgg(ModelCubeWorld model) {
		super(model);
	}

	@Override
	public void postSpecials(ItemStack item, ModelCubeWorld model,
			Object... data) {
		super.postSpecials(item, model, data);

		float r = 1;
		float g = 1;
		float b = 1;

		int color = ((ItemMonsterPlacer) item.getItem()).getColorFromItemStack(
				item, 0);

		float red = ((color >> 16) & 255) / 255.0F;
		float green = ((color >> 8) & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;

		r = red;
		g = green;
		b = blue;

		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);

		egg_base.renderWithColor(r, g, b, 0.1f);

		int color2 = ((ItemMonsterPlacer) item.getItem())
				.getColorFromItemStack(item, 1);

		float red2 = ((color2 >> 16) & 255) / 255.0F;
		float green2 = ((color2 >> 8) & 255) / 255.0F;
		float blue2 = (color2 & 255) / 255.0F;

		r = red2;
		g = green2;
		b = blue2;
		egg_overlay.renderWithColor(r, g, b, 0.1f);
	}

	@Override
	public void preSpecials(ItemStack item, ModelCubeWorld model,
			Object... data) {

		super.preSpecials(item, model, data);
	}

	@Override
	public void renderEntity() {
		renderInWorld();
	}

	@Override
	public void renderEquipped() {
		renderTP();
	}

	@Override
	public void renderEquippedFP() {
		renderFP();
	}

	private void renderFP() {
		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(-0.3f, 0.2f, 0.7f);

	}

	private void renderInWorld() {
		GL11.glRotatef(0, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(-0.3f, -0.3f, 0.3f);
	}

	@Override
	public void renderScale() {
		float f = 2f;
		GL11.glScalef(f, f, f);
	}

	private void renderTP() {

		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(45, 0, 0, 1);

		GL11.glTranslatef(0.4f, -0.4f, 0.2f);
	}

	@Override
	protected boolean shouldIgnoreModelRendering() {
		return true;
	}
}
