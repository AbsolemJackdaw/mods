package threeDitems.helper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.resources.ResourceLocation;
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


	public void cartzz(ItemStack item, ModelBase theItem, RenderBlocks render, Object... data){
		if(item.getItem().equals(Item.minecartEmpty)){
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/item/cart.png"));
			theItem.render(new EntityMinecartEmpty(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);				
		}if(item.getItem().equals(Item.minecartCrate)){
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/item/cart.png"));
			theItem.render(new EntityMinecartChest(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.chest, render);
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/item/cart.png"));
		}if(item.getItem().equals(Item.minecartPowered)){
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/item/cart.png"));
			theItem.render(new EntityMinecartFurnace(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.furnaceIdle, render);
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/item/cart.png"));
		}if(item.getItem().equals(Item.minecartTnt)){
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/item/cart.png"));
			theItem.render(new EntityMinecartTNT(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.tnt, render);
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/item/cart.png"));
		}if(item.getItem().equals(Item.minecartHopper)){
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/item/cart.png"));
			theItem.render(new EntityMinecartHopper(((Entity)data[1]).worldObj),0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderBlock(Block.hopperBlock, render);
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/item/cart.png"));
		}
	}

	public void renderBlock(Block block, RenderBlocks render){
		if (block != null)
		{
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/terrain.png"));
			float f8 = 0.75F;
			GL11.glScalef(f8, f8, f8);
			GL11.glTranslatef(0.0F, -1.5f / 16.0F, 0.0F);
			GL11.glRotatef(180f, 0.0f, 0.0f, 1.0f);
			render.renderBlockAsItem(block, 0, 1);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
