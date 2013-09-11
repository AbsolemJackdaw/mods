package threeDitems.render.item.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityFurnace;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;

import threeDitems.mod_3d;
import threeDitems.render.Render3DInterface;

public class RenderItemBlock extends Render3DInterface{

	RenderBlocks render = new RenderBlocks();
	Block block;
	public RenderItemBlock(ModelBase model, String texture, Block b) {
		super(model, texture);
		block = b;
	}

	@Override
	public void renderEquippedFP() {
//		mod_3d.proxy.render();
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 1.2f, 0.5f);	
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 1f, 0f);	
//
//		float f = 2f;
//		GL11.glScalef(f, f, f);	
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(-30,1,0,0);

		GL11.glTranslatef(0f, 0.5f, 1f);		
	}

	@Override
	public void renderScale() {
		float f = 1.5f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void preSpecials(ItemStack item, ModelBase model) {
		super.preSpecials(item, model);
		Minecraft.getMinecraft().renderEngine.func_110577_a(TextureMap.field_110575_b);
		render.renderBlockAsItem(block, item.getItemDamage(), 1);

	}

	@Override
	@Deprecated
	protected boolean shouldIgnoreModelRendering() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@Deprecated
	protected boolean shouldIgnoreTextureRendering() {
		// TODO Auto-generated method stub
		return true;
	}
}
