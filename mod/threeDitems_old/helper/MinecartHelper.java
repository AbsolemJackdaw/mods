package threeDitems_old.helper;

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

import org.lwjgl.opengl.GL11;

public class MinecartHelper {
	
	DynamicTexture icon;

	public void cartzz(ItemStack item, ModelBase theItem, RenderBlocks render, Object... data){
		if(item.getItem().equals(Item.minecartEmpty)){
			theItem.render(new EntityMinecartEmpty(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);				
		}if(item.getItem().equals(Item.minecartCrate)){
			theItem.render(new EntityMinecartChest(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.chest, render);
		}if(item.getItem().equals(Item.minecartPowered)){
			theItem.render(new EntityMinecartFurnace(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.furnaceIdle, render);
		}if(item.getItem().equals(Item.minecartTnt)){
			theItem.render(new EntityMinecartTNT(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.tnt, render);
		}if(item.getItem().equals(Item.minecartHopper)){
			theItem.render(new EntityMinecartHopper(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.hopperBlock, render);
		}
		try {
			icon = new DynamicTexture(ImageIO.read(getClass().getResourceAsStream("/assets/minecraft/textures/entity/minecart.png")));
		} catch (IOException c) {
			c.printStackTrace();
		}
		icon.func_110564_a();
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
