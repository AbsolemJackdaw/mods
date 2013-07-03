package threeDitems.helper;

import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;

import threeDitems.models.bottle;
import threeDitems.models.egg;
import threeDitems.models.enderball;
import threeDitems.models.head;

public class CaseEntity
{
	DynamicTexture icon;

	public void render(ItemRenderType type, ItemStack item, float x, float y, float z, float rotZ, float rotY, float rotX, 
			float X, float Y, float Z, float fpsX, float fpsY, float fpsZ, float scale, 
			String name, RenderBlocks render, FrameHelper frame, ModelBase theItem, MinecartHelper helper, 
			Block blockToRender, String[] armorFilenamePrefix, Object[] data)
	{
		GL11.glPushMatrix();
		if(item.getItem() instanceof ItemSkull){
			switch(item.getItemDamage()){
			case 0:
				name = "/mob/skeleton.png";
				break;
			case 1:
				name = "/mob/skeleton_wither.png";
				break;
			case 2:
				name = "/mob/zombie.png";
				break;
			case 3: 
				//				if(item.getTagCompound() != null){
				//					if (item.getTagCompound().hasKey("SkullOwner")){
				//						try{
				//							GL11.glBindTexture(GL11.GL_TEXTURE_2D,
				//									Minecraft.getMinecraft().
				//									renderEngine.getTextureForDownloadableImage("http://skins.minecraft.net/MinecraftSkins/" + 
				//											StringUtils.stripControlCodes(item.getTagCompound().getString("SkullOwner")) +
				//											".png", "/mob/char.png"));
				//						}catch(Throwable e){}
				//					}else
				//						name = "/mob/char.png";
				//				}else
				name = "/mob/char.png";
				break;
			case 4:
				name = "/mob/creeper.png";
				break;
			}
		}
		try {
			icon = new DynamicTexture(ImageIO.read(getClass().getResourceAsStream(name)));
		} catch (IOException c) {
			c.printStackTrace();
		}
		icon.func_110564_a();

		GL11.glScalef(3f, 3f,3f);
		GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
		GL11.glRotatef(0, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(-180, 1.0f, 0.0f, 0.0f);
		GL11.glTranslatef(0f,0f,0F);

		GL11.glScalef(scale,scale, scale);

		if(item.getItem().equals(Item.itemFrame)){
			GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(360-90, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0f,0f,-0.5F);
			frame.renderFrameItemAsBlock(render, item.getItem());
		}

		if(item.getItem() instanceof ItemArmor){
			//			ArmorHelper ah= new ArmorHelper();
			//			int c =((ItemArmor)item.getItem()).armorType;
			//			ah.setArmorModel((ModelBiped)theItem, item, c, armorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
			//			if(c == 0){
			//				GL11.glTranslatef(0f, -0.5f ,0F);
			//			}if(c == 1){
			//				GL11.glTranslatef(0f, 0f ,-0.8F);
			//				GL11.glRotatef(90f, 1.0f,0.0f,0.0f);
			//			}if(c == 2){
			//				GL11.glTranslatef(0f, 0f,-1.2F);
			//				GL11.glRotatef(90f, 1.0f,0.0f,0.0f);
			//			}if(c == 3){
			//				GL11.glTranslatef(0f, 0f ,-1.4F);
			//				GL11.glRotatef(90f, 1.0f,0.0f,0.0f);
			//			}
		}
		if(item.getItem() instanceof ItemMinecart){
			GL11.glTranslatef(0f,-0.2f,0F);
			helper.cartzz(item, theItem, render, data);
		}
		if(blockToRender != null){
			render.renderBlockAsItem(blockToRender, 0, 1.0f);
		}

		if(item.getItem() instanceof ItemSkull){
			switch(item.getItemDamage()){
			case 0:
				((head)theItem).renderHead(0.0625f);
				break;
			case 1:
				((head)theItem).renderHead(0.0625f);
				break;
			case 2:
				((head)theItem).renderZombie(0.0625f);
				break;
			case 3: 
				((head)theItem).renderHead(0.0625f);
				break;
			case 4:
				((head)theItem).renderHead(0.0625f);
				break;
			}
		}

		if(item.getItem().equals(Item.bow)){
			FMLLog.getLogger().info("" +item.getItemSpriteNumber());
		}

		if(item.getItem() instanceof ItemMonsterPlacer){
			if(item.getItem().equals(Item.monsterPlacer))
			{
				int color = ((ItemMonsterPlacer)item.getItem()).getColorFromItemStack(item,1);
				float red = (float)(color >> 16 & 255) / 255.0F;
				float green = (float)(color >> 8 & 255) / 255.0F;
				float blue = (float)(color & 255) / 255.0F;					
				GL11.glColor4f(red, green,blue, 1.0F);
			}
		}
		theItem.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		if(item.getItem() instanceof ItemMonsterPlacer){
			renderDots((Entity)data[1], item, theItem, 0);			
		}
		if(item.getItem().equals(Item.blazeRod)){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0f,0.5f,0f, 0.5f);
			GL11.glScalef(1.2f,1.2f,1.2f);
			GL11.glTranslatef(-0.005f, 0.005f, 0.01f);
			theItem.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		if(item.getItem() instanceof ItemEnderEye ){
			GL11.glScalef(0.5f,0.5f,0.5f);
			((enderball)theItem).renderEye((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0f, 0.5f, 0f, 0.2f);
			((enderball)theItem).renderBall((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		if(item.getItem() instanceof ItemPotion|| item.getItem().equals(Item.expBottle)){
			this.potionContent((Entity)data[1], item, theItem);
		}

		if(item.getItem() instanceof ItemPotion ||
				item.getItem().equals(Item.glassBottle)||
				item.getItem().equals(Item.expBottle)){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(0.2f, 0.2f, 0.2f, 0.2f);
			((bottle)theItem).renderBottle((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		if(item.getItem() instanceof ItemEnderPearl ){
			GL11.glScalef(0.5f,0.5f,0.5f);
			GL11.glColor4f(0f, 0f, 0f, 1.0f);
			((enderball)theItem).renderBall((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		if(item.getItem() instanceof ItemEnderPearl|| item.getItem() instanceof ItemEnderEye ){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(0.3f, 0.3f, 0.3f, 0.3f);
			GL11.glTranslatef(-0.02f, 0.05f, -0.001f);
			GL11.glScalef(1.3f,1.3f,1.3f);
			((enderball)theItem).renderBall((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		GL11.glPopMatrix();
	}

	public void potionContent(Entity p, ItemStack item, ModelBase theItem)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.func_110581_b(new ResourceLocation("/subaraki/3d/bottle.png"));
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if(item.getItem() != null)
		{
			if(item.getItem().equals(Item.potion) || item.getItem().equals(Item.glassBottle))
			{
				int color = ((ItemPotion)item.getItem()).getColorFromDamage(item.getItemDamage());
				float red = (float)(color >> 16 & 255) / 255.0F;
				float green = (float)(color >> 8 & 255) / 255.0F;
				float blue = (float)(color & 255) / 255.0F;					
				GL11.glColor4f(red, green,blue, 1.0F);
				((bottle)theItem).renderContent(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}
			else if(item.getItem().equals(Item.expBottle))
			{
				GL11.glColor4f(0.7f, 1.0f, 0.0f, 1.0F);
				((bottle)theItem).renderContent(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}
		}
	}

	public void renderDots(Entity p, ItemStack item, ModelBase theItem, int colorParser)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.func_110581_b(new ResourceLocation("/subaraki/3d/eggSpawnSpots.png"));
		if(item.getItem() != null)
		{
			if(item.getItem().equals(Item.monsterPlacer))
			{
				int color = ((ItemMonsterPlacer)item.getItem()).getColorFromItemStack(item,colorParser);
				float red = (float)(color >> 16 & 255) / 255.0F;
				float green = (float)(color >> 8 & 255) / 255.0F;
				float blue = (float)(color & 255) / 255.0F;					
				GL11.glColor4f(red, green,blue, 0.3F);
				theItem.render(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}
		}
	}
}
