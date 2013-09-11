package threeDitems.helper;

import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import threeDitems.mod_3d;

public class MinecartHelper {

	private static final ResourceLocation MINECART_TEXTURE = new ResourceLocation("textures/entity/minecart.png");

	public void cartzz(ItemStack item, ModelBase theItem, RenderBlocks renderer){
//		Minecraft.getMinecraft().renderEngine.func_110577_a(MINECART_TEXTURE);
//		mod_3d.proxy.render();
		if(item.getItem().equals(Item.minecartEmpty)){
			theItem.render(new EntityMinecartEmpty(Minecraft.getMinecraft().theWorld),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);				
		}if(item.getItem().equals(Item.minecartCrate)){
			theItem.render(new EntityMinecartChest(Minecraft.getMinecraft().theWorld),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.chest, renderer);
		}if(item.getItem().equals(Item.minecartPowered)){
			theItem.render(new EntityMinecartFurnace(Minecraft.getMinecraft().theWorld),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.furnaceIdle, renderer);
		}if(item.getItem().equals(Item.minecartTnt)){
			theItem.render(new EntityMinecartTNT(Minecraft.getMinecraft().theWorld),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.tnt, renderer);
		}if(item.getItem().equals(Item.minecartHopper)){
			theItem.render(new EntityMinecartHopper(Minecraft.getMinecraft().theWorld),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.hopperBlock, renderer);
		}

	}

	public void renderBlock(Block block, RenderBlocks render){
		if (block != null)
		{
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.func_110577_a(TextureMap.field_110575_b);
			float f8 = 0.75F;
			GL11.glScalef(f8, f8, f8);
			GL11.glTranslatef(0.0F, (float)-5 / 16.0F, 0.0F);
			GL11.glRotatef(180, 1, 0, 0);
			render.renderBlockAsItem(block, 1, 2);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
		
	}
}
