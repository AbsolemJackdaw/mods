package threeDitems.helper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class FrameHelper extends Render
{
	private final RenderBlocks renderBlocksInstance = new RenderBlocks();

	ResourceLocation birch = new ResourceLocation("textures/blocks/planks_birch.png");
	ResourceLocation oak = new ResourceLocation("textures/blocks/planks_oak.png");
	ResourceLocation frame = new ResourceLocation("textures/blocks/itemframe_background.png");

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	public void renderAABBwithUV(AxisAlignedBB par0AxisAlignedBB) {
		float i = 2f;
		float i2 = 0f;
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();

		tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ, i+1, i2+2);
		tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ, i+2, i);
		tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ, i+1, i);
		tess.addVertexWithUV(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ, i+1, i2+1);
		tess.draw();
	}
	public void renderFrame(Block block, float f2, float f){
		Minecraft.getMinecraft().renderEngine.bindTexture(birch);

		GL11.glPushMatrix();
		renderBlocksInstance.overrideBlockBounds(0.0D, 0.5F - f2, 0.5F - f2, f + 1.0E-4F, (f + 0.5F) - f2, 0.5F + f2);
		renderBlocksInstance.renderBlockAsItem(block, 0, 1.0F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		renderBlocksInstance.overrideBlockBounds(0.0D, (0.5F + f2) - f, 0.5F - f2, f + 1.0E-4F, 0.5F + f2, 0.5F + f2);
		renderBlocksInstance.renderBlockAsItem(block, 0, 1.0F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		renderBlocksInstance.overrideBlockBounds(0.0D, 0.5F - f2, 0.5F - f2, f, 0.5F + f2, (f + 0.5F) - f2);
		renderBlocksInstance.renderBlockAsItem(block, 0, 1.0F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		renderBlocksInstance.overrideBlockBounds(0.0D, 0.5F - f2, (0.5F + f2) - f, f, 0.5F + f2, 0.5F + f2);
		renderBlocksInstance.renderBlockAsItem(block, 0, 1.0F);
		GL11.glPopMatrix();
		renderBlocksInstance.unlockBlockBounds();
		renderBlocksInstance.clearOverrideBlockTexture();
	}

	public void renderFrameItemAsBlock(RenderBlocks renderBlocksInstance, Item item)
	{
		GL11.glPushMatrix();


		Minecraft.getMinecraft().renderEngine.bindTexture(oak);
		Block block = Block.planks;
		float f = 0.0625F;
		float f1 = 0.75F;
		float f2 = f1 / 2.0F;

		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(frame);
		GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
		GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
		GL11.glTranslatef(-0.5f,-0.5f,-0.5f);
		this.renderAABBwithUV(AxisAlignedBB.getBoundingBox(0.0D, (0.5F - f2) + 0.0625F-0.05, (0.5F - f2) + 0.0625F-0.05, f * 0.5F, (0.5F + f2) - 0.0625F+0.05, (0.5F + f2) - 0.0625F+0.05));
		GL11.glPopMatrix();

		renderFrame(block, f2, f);
		GL11.glPopMatrix();
	}
}
