package toolbelts;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

public class RenderHipItemsOnPlayer {

	RenderBlocks blockrender = new RenderBlocks();
	private static final ResourceLocation glint = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	private boolean renderSingleItem = false;

	private static HashMap<String, ItemStack> swordStack = new HashMap<String, ItemStack>();
	private static HashMap<String, ItemStack> prevSwordStack = new HashMap<String, ItemStack>();

	private static HashMap<String, ItemStack> pickaxeStack = new HashMap<String, ItemStack>();
	private static HashMap<String, ItemStack> prevPickaxeStack = new HashMap<String, ItemStack>();



	@ForgeSubscribe
	public void PlayerRender(RenderPlayerEvent.SetArmorModel evt ){

		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		float scale = 0.3f;

		ItemStack heldItem = evt.entityPlayer.getCurrentEquippedItem();

		renderHeldSword(heldItem, items, evt.entityPlayer);
		renderHeldPickaxe(heldItem, items, evt.entityPlayer);

		for (int i = 0; i < evt.entityPlayer.inventory.getSizeInventory(); i ++){
			ItemStack is = evt.entityPlayer.inventory.getStackInSlot(i);
			if(is != null){

				if(is.getItem().equals(Item.potion)){
					items.add(is);
					float f = 0;
					GL11.glPushMatrix();
					GL11.glTranslatef(0f, 0f, 0f);
					addItemToBelt(is, evt.entityPlayer, items,0, scale);
					GL11.glPopMatrix();
				}

				if(is.getItem().equals(Item.glassBottle)){
					items.add(is);
					float f = 0;
					GL11.glPushMatrix();
					GL11.glTranslatef(0f, 0f, 0.3f);
					addItemToBelt(is, evt.entityPlayer, items,1, scale);
					GL11.glPopMatrix();
				}

				if(is.itemID == Block.torchWood.blockID){
					items.add(is);
					GL11.glPushMatrix();
					GL11.glTranslatef(-0.15f, -0.07f, 0f);
					addItemToBelt(is, evt.entityPlayer, items,0 , scale + 0.05f);
					GL11.glPopMatrix();
				}

				if(is.getItem().equals(Item.arrow)){
					items.add(is);
					GL11.glPushMatrix();
					GL11.glTranslatef(-0.3f, -0.05f, 0.3f);
					addItemToBelt(is, evt.entityPlayer, items,1 , 0.4f);
					GL11.glPopMatrix();
				}
			}
		}

		if(evt.entityPlayer.getActivePotionEffect(Potion.invisibility) == null){
			ItemStack st = new ItemStack(Block.cloth, 1 , 12);
			renderBelt(st, evt.entityPlayer);
		}
		if(evt.entityPlayer.getActivePotionEffect(Potion.invisibility) == null){

			ItemStack chest = new ItemStack(Block.chest);
			GL11.glPushMatrix();
			GL11.glRotatef(-90, 0, 1, 0);
			GL11.glTranslatef(-0.3f, 0.02f, 0.3f);
			addItemToBelt(chest, evt.entityPlayer, items, 2, scale);
			GL11.glPopMatrix();
		}
	}


	private void addItemToBelt(ItemStack stack, EntityPlayer player, ArrayList<ItemStack> items, int sideToFlip, float scale){

		if(stack != null){
			if(stack != player.getCurrentEquippedItem()){

				if(player.getActivePotionEffect(Potion.invisibility) == null){
					GL11.glPushMatrix();
					GL11.glRotatef(180, 0, 0, 1);
					renderItem(stack, player, sideToFlip, scale);
					GL11.glPopMatrix();
				}
			}
			//			else{
			//				try{
			//					if(items.get((items.indexOf(stack)-1)) != null &&
			//							items.get((items.indexOf(stack)-1)).getItem().equals(items.get((items.indexOf(stack))).getItem())){
			//						if(player.getActivePotionEffect(Potion.invisibility) == null){
			//							GL11.glPushMatrix();
			//							GL11.glRotatef(180, 0, 0, 1);
			//							renderItem(items.get((items.indexOf(stack)-1)), player, sideToFlip, scale);
			//							GL11.glPopMatrix();
			//						}
			//					}
			//				}catch(Exception e){
			//				}
			//			}
		}		
	}

	private void renderItem(ItemStack itemstack, EntityPlayer player, int sideToFlip, float scale){
		GL11.glPushMatrix();

		GL11.glTranslatef(-0.2f, -0.75f, -0.15f);

		if(player.isSneaking()){

			switch (sideToFlip) {
			case 0:
				GL11.glTranslatef(0f,0.05f,0.3f);
				GL11.glRotatef(-25, 1, 0, 0);
				break;
			case 1:
				GL11.glTranslatef(0f,0.17f,0.3f);
				GL11.glRotatef(-25, 1, 0, 0);
				break;
			case 5:
				GL11.glTranslatef(-0.3f,-0.1f,0f);
				GL11.glRotatef(25, 0, 0, 1);
				break;
			case 2:
				GL11.glTranslatef(-0.35f,0.05f,0f);
				GL11.glRotatef(-25, 0, 0, 1);
				break;
			case 4:
				GL11.glTranslatef(0f,0.05f,0.3f);
				GL11.glRotatef(-25, 0, 0, 1);
				break;
			default:
				GL11.glRotatef(180, 1, 1, 1);//if wrong
				break;
			}
		}

		if ((itemstack != null) && (Item.itemsList[itemstack.itemID] != null))
		{
			bindTextureMap(itemstack);
			/*=======BLOCKS=======*/
			if ((itemstack.itemID < 256) && (RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())))
			{
				float f2 = 0.25F;
				int i1 = Block.blocksList[itemstack.itemID].getRenderType();

				if ((i1 == 1) || (i1 == 19) || (i1 == 12) || (i1 == 2))
				{
					f2 = 0.5F;
				}
				GL11.glPushMatrix();

				GL11.glScalef(f2, f2, f2);
				GL11.glTranslatef(0.0F, 0.35F, 0.0F);
				float f3 = 1.0F;

				this.blockrender.renderBlockAsItem(Block.blocksList[itemstack.itemID], itemstack.getItemDamage(), f3);

				GL11.glDisable(GL11.GL_BLEND);

				GL11.glPopMatrix();
			}
			/*=====ITEMS=====*/
			else
			{
				GL11.glPushMatrix();

				try
				{
					GL11.glScalef(scale, scale ,scale );

					if (itemstack.getItem().requiresMultipleRenderPasses())
					{
						GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
						GL11.glEnable(GL11.GL_BLEND);
						for (int k = 0; k <= 1; k++)
							drawItem(itemstack, k);
						GL11.glDisable(GL11.GL_BLEND);

					}
					else{
						GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
						GL11.glEnable(GL11.GL_BLEND);
						drawItem(itemstack, 0);
						GL11.glDisable(GL11.GL_BLEND);

					}
				}

				catch (Throwable throwable)
				{
					throw new RuntimeException(throwable);
				}

				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();
	}


	private void drawItem(ItemStack var18, int par2) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Icon var4 = var18.getItem().getIconFromDamageForRenderPass(var18.getItemDamage(), par2);
		if (!(var4 instanceof TextureAtlasSprite))
			return;
		TextureAtlasSprite icon = (TextureAtlasSprite)var4;

		GL11.glPushMatrix();
		Tessellator var8 = Tessellator.instance;

		float c1 = var4.getMinU();
		float c2 = var4.getMaxU();
		float c3 = var4.getMinV();
		float c4 = var4.getMaxV();

		float var14 = 0.5F;
		float var15 = 0.25F;

		float var16 = 0.0625F;
		float var17 = 0.021875F;
		int var19 = var18.stackSize;
		byte var24;
		//		byte var24;
		if (var19 < 2)
		{
			var24 = 1;
		}
		else
		{
			//			byte var24;
			if (var19 < 16)
			{
				var24 = 2;
			}
			else
			{
				//				byte var24;
				if (var19 < 32)
				{
					var24 = 3;
				}
				else
				{
					var24 = 4;
				}
			}
		}
		GL11.glTranslatef(-var14, -var15, -((var16 + var17) * var24 / 2.0F));

		for (int var20 = 0; var20 < var24; var20++)
		{
			GL11.glTranslatef(0.0F, 0.0F, var16 + var17);

			bindTextureMap(var18);

			int k1 = Item.itemsList[var18.itemID].getColorFromItemStack(var18, par2);
			float f4 = (k1 >> 16 & 0xFF) / 255.0F;
			float f6 = (k1 >> 8 & 0xFF) / 255.0F;
			float f8 = (k1 & 0xFF) / 255.0F;
			GL11.glColor4f(f4, f6, f8, 1.0F);
			ItemRenderer.renderItemIn2D(var8, c2, c3, c1, c4, icon.getIconHeight(), icon.getIconWidth(), 0.0625F);

			if ((var18 != null) && (var18.hasEffect()) && (par2 == 0))
			{
				GL11.glDepthFunc(514);
				GL11.glDisable(2896);
				Minecraft.getMinecraft().renderEngine.bindTexture(glint);
				GL11.glEnable(3042);
				GL11.glBlendFunc(768, 1);
				float colorMult = 0.76F;
				GL11.glColor4f(0.5F * colorMult, 0.25F * colorMult, 0.8F * colorMult, 1.0F);
				GL11.glMatrixMode(5890);
				GL11.glPushMatrix();
				float scale = 0.125F;
				GL11.glScalef(scale, scale, scale);
				float time = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
				GL11.glTranslatef(time, 0.0F, 0.0F);
				GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(scale, scale, scale);
				time = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
				GL11.glTranslatef(-time, 0.0F, 0.0F);
				GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
				GL11.glPopMatrix();
				GL11.glMatrixMode(5888);
				GL11.glDisable(3042);
				GL11.glEnable(2896);
				GL11.glDepthFunc(515);
			}
		}
		GL11.glPopMatrix();
	}

	private void bindTextureMap(ItemStack item) {
		Minecraft.getMinecraft().renderEngine.bindTexture(RenderManager.instance.renderEngine.getResourceLocation(item.getItemSpriteNumber()));
	}


	private void renderBelt(ItemStack itemstack, EntityPlayer player){
		GL11.glPushMatrix();

		GL11.glTranslatef(0f,0.65f,0f);

		if(player.isSneaking()){
			GL11.glRotatef(25, 1, 0, 0);
			GL11.glTranslatef(0f,0.05f,0.3f);
		}

		if ((itemstack != null) && (Item.itemsList[itemstack.itemID] != null))
		{
			bindTextureMap(itemstack);
			/*=======BLOCKS=======*/
			if ((itemstack.itemID < 256) && (RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())))
			{
				float f2 = 0.25F;
				int i1 = Block.blocksList[itemstack.itemID].getRenderType();

				if ((i1 == 1) || (i1 == 19) || (i1 == 12) || (i1 == 2))
				{
					f2 = 0.5F;
				}

				GL11.glPushMatrix();

				GL11.glScalef(f2+0.3f, 0.1f, f2+0.05f);
				float f3 = 1.0F;
				//				GL11.glDisable(GL11.GL_LIGHTING);
				//				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
				//				GL11.glEnable(GL11.GL_BLEND);
				//						this.blockrender.renderStandardBlock(Block.dirt, (int)d, (int)d1, (int)d2);

				this.blockrender.renderBlockAsItem(Block.blocksList[itemstack.itemID], itemstack.getItemDamage(), f3);

				//				GL11.glDisable(GL11.GL_BLEND);

				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();
	}



	private void renderHeldSword(ItemStack heldItem, ArrayList<ItemStack> items, EntityPlayer player){

		if(heldItem != null){
			if(heldItem.getItem() instanceof ItemSword){
				swordStack.put(player.username, heldItem);

			}
		}
		if(heldItem == null){
			prevSwordStack.put(player.username, swordStack.get(player.username));
		}
		else if (heldItem != null && (heldItem.getItem() instanceof ItemSword)){
			prevSwordStack.put(player.username, heldItem);
		}

		if(prevSwordStack != null  && heldItem != prevSwordStack.get(player.username)){

			items.add(prevSwordStack.get(player.username));
			GL11.glPushMatrix();
			GL11.glTranslatef(0.44f, 1.25f, -0.2f);
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glRotatef(180, 0, 0, 1);
			addItemToBelt(prevSwordStack.get(player.username), player, items,5, 0.7f);
			GL11.glPopMatrix();
		}
	}

	private void renderHeldPickaxe(ItemStack heldItem, ArrayList<ItemStack> items, EntityPlayer player){


		if(heldItem != null){
			if(heldItem.getItem() instanceof ItemPickaxe){
				pickaxeStack.put(player.username, heldItem);

			}
		}
		if(heldItem == null || heldItem != null && !(heldItem.getItem() instanceof ItemPickaxe)){
			prevPickaxeStack.put(player.username, pickaxeStack.get(player.username));
		}

		if(prevPickaxeStack != null  && heldItem != prevPickaxeStack.get(player.username)){

			items.add(prevPickaxeStack.get(player.username));
			float f = 0;
			GL11.glPushMatrix();
			GL11.glTranslatef(-0.14f, 1.35f, -0.1f);
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glRotatef(180, 0, 0, 1);
			addItemToBelt(prevPickaxeStack.get(player.username), player, items,5, 0.7f);
			GL11.glPopMatrix();
		}
	}
}
