package threeDitems.helper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

public class FrameHelper extends Render
{
	private final RenderBlocks renderBlocksInstance = new RenderBlocks();
	private Icon icon;

	public void updateIcons(IconRegister par1IconRegister)
	{
		this.icon = par1IconRegister.registerIcon("itemframe_back");
	} 

	public void renderFrameItemAsBlock(RenderBlocks renderBlocksInstance, Item item)
	{
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/terrain.png"));
		//textures/blocks/itemframe_back.png
		Block block = Block.planks;
		float f = 0.0625F;
		float f1 = 0.75F;
		float f2 = f1 / 2.0F;

		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/textures/blocks/itemframe_back.png"));
		GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
		GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
		GL11.glTranslatef(-0.5f,-0.5f,-0.5f);
		this.renderAABBwithUV(AxisAlignedBB.getBoundingBox(0.0D, (double)(0.5F - f2 + 0.0625F)-0.05, (double)(0.5F - f2 + 0.0625F)-0.05, (double)(f * 0.5F), (double)(0.5F + f2 - 0.0625F)+0.05, (double)(0.5F + f2 - 0.0625F)+0.05));
		GL11.glPopMatrix();

		renderFrame(block, f2, f);
		GL11.glPopMatrix();
	}

	public void renderFrame(Block block, float f2, float f){
		Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/terrain.png"));
		renderBlocksInstance.setOverrideBlockTexture(Block.planks.getIcon(1, 2));
		GL11.glPushMatrix();
		renderBlocksInstance.overrideBlockBounds(0.0D, (double)(0.5F - f2), (double)(0.5F - f2), (double)(f + 1.0E-4F), (double)(f + 0.5F - f2), (double)(0.5F + f2));
		renderBlocksInstance.renderBlockAsItem(block, 0, 1.0F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		renderBlocksInstance.overrideBlockBounds(0.0D, (double)(0.5F + f2 - f), (double)(0.5F - f2), (double)(f + 1.0E-4F), (double)(0.5F + f2), (double)(0.5F + f2));
		renderBlocksInstance.renderBlockAsItem(block, 0, 1.0F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		renderBlocksInstance.overrideBlockBounds(0.0D, (double)(0.5F - f2), (double)(0.5F - f2), (double)f, (double)(0.5F + f2), (double)(f + 0.5F - f2));
		renderBlocksInstance.renderBlockAsItem(block, 0, 1.0F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		renderBlocksInstance.overrideBlockBounds(0.0D, (double)(0.5F - f2), (double)(0.5F + f2 - f), (double)f, (double)(0.5F + f2), (double)(0.5F + f2));
		renderBlocksInstance.renderBlockAsItem(block, 0, 1.0F);
		GL11.glPopMatrix();
		renderBlocksInstance.unlockBlockBounds();
		renderBlocksInstance.clearOverrideBlockTexture();
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
	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {		
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
