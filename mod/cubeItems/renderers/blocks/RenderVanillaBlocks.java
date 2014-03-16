package cubeItems.renderers.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cubeItems.ModelCubeWorld;
import cubeItems.RenderCubeInterface;

public class RenderVanillaBlocks extends RenderCubeInterface {

	RenderBlocks render = new RenderBlocks();
	Block block;

	public RenderVanillaBlocks(ModelCubeWorld model, Block b) {
		super(model);
		block = b;
	}

	@Override
	public void preSpecials(ItemStack item, ModelCubeWorld model,
			Object... data) {
		super.preSpecials(item, model, data);
		Minecraft.getMinecraft().renderEngine
				.bindTexture(TextureMap.locationBlocksTexture);
		render.renderBlockAsItem(block, item.getItemDamage(), 1);
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0, 0, 1, 0);
		GL11.glRotatef(0, 0, 0, 1);
		GL11.glRotatef(0, 1, 0, 0);

		GL11.glTranslatef(0f, 1f, 0f);

	}

	@Override
	public void renderEquipped() {

		GL11.glRotatef(35, 0, 1, 0);
		GL11.glRotatef(0, 0, 0, 1);
		GL11.glRotatef(-30, 1, 0, 0);

		GL11.glTranslatef(0.3f, 0.25f, 0.6f);

	}

	@Override
	public void renderEquippedFP() {

		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(0, 0, 0, 1);
		GL11.glRotatef(0, 1, 0, 0);

		GL11.glTranslatef(0f, 0.8f, 0.5f);
	}

	@Override
	public void renderScale() {
		float f = 1.5f;
		GL11.glScalef(f, f, f);
	}

	@Override
	protected boolean shouldIgnoreModelRendering() {
		return true;
	}

	@Override
	protected boolean shouldIgnoreTextureRendering() {
		return true;
	}
}
