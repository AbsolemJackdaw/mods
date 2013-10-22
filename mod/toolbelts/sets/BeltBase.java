package toolbelts.sets;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

public class BeltBase {


	RenderBlocks blockrender = new RenderBlocks();
	protected static final ResourceLocation glint = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	// *stack keeps track of when an item gets switched
	//prev*Stack keeps track of the previous held stack and renders it on the back
	//third*Stack keeps track of wether the current held switched out item

	public static HashMap<String, ItemStack> swordStack = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> prevSwordStack = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> thirdSwordStack = new HashMap<String, ItemStack>();

	public static HashMap<String, ItemStack> toolStack = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> prevToolStack = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> thirdToolStack = new HashMap<String, ItemStack>();

	public static HashMap<String, ItemStack> pickaxeStack = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> prevPickaxeStack = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> thirdPickaxeStack = new HashMap<String, ItemStack>();

	protected ItemStack heldItem;

	public BeltBase(RenderPlayerEvent.SetArmorModel evt){
		heldItem = evt.entityPlayer.getCurrentEquippedItem();

		renderDefault(evt);

		boolean f = true;
		evt.renderer.modelBipedMain.bipedHead.showModel = f;
		evt.renderer.modelBipedMain.bipedHeadwear.showModel = f;
		evt.renderer.modelBipedMain.bipedLeftArm.showModel = f;
		evt.renderer.modelBipedMain.bipedLeftLeg.showModel = f;
		evt.renderer.modelBipedMain.bipedRightArm.showModel = f;
		evt.renderer.modelBipedMain.bipedRightLeg.showModel = f;
		evt.renderer.modelBipedMain.bipedHead.showModel = f;
	}

	public void renderDefault(RenderPlayerEvent.SetArmorModel evt ){}

	protected void addItemToBelt(ItemStack stack, EntityPlayer player, int sideToFlip, float scale){

		if(player.getActivePotionEffect(Potion.invisibility) == null){
			if(stack != null){
				if(stack != player.getCurrentEquippedItem()){
					GL11.glPushMatrix();
					GL11.glRotatef(180, 0, 0, 1);
					renderItem(stack, player, sideToFlip, scale);
					GL11.glPopMatrix();
				}
			}
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

			case 5://used for swords and pickaxes
				GL11.glTranslatef(-0.3f,-0.1f,0f);
				GL11.glRotatef(25, 0, 0, 1);
				break;

			case 2:
				GL11.glTranslatef(-0.35f,0.05f,0f);
				GL11.glRotatef(-25, 0, 0, 1);
				break;

			case 6:
				GL11.glTranslatef(0f,0.0f,0.29f);
				GL11.glRotatef(-30, 1, 0, 0);
				break;
			case 14:
				GL11.glTranslatef(0f,0.1f,0.4f);
				GL11.glRotatef(-30, 1, 0, 0);
				break;
			case 7:
				GL11.glTranslatef(0f,-0.05f,0.1f);
				GL11.glRotatef(-30, 1, 0, 0);
				break;

			case 8:// used for bow on the player's back
				GL11.glTranslatef(0f,0.1f,-0.25f);
				GL11.glRotatef(30, 1, 0, 0);
				break;

			case 9:
				GL11.glTranslatef(0f,0.0f,-0.2f);
				GL11.glRotatef(30, 1, 0, 0);
				break;


			case 10:
				GL11.glTranslatef(-1.5f,0.05f,0f);
				GL11.glRotatef(-25, 0, 0, 1);
				break;
			case 11:
				GL11.glTranslatef(-0.5f,-0.02f,0f);
				GL11.glRotatef(-25, 0, 0, 1);
				break;
			case 12:
				GL11.glTranslatef(0.5f,0.15f,0f);
				GL11.glRotatef(30, 0, 0, 1);
				break;
			case 13:
				GL11.glTranslatef(0f,0.1f,-0.3f);
				GL11.glRotatef(30, 1, 0, 0);
				break;

				//case 14 used

			case 15: 
				GL11.glTranslatef(0f,0.0f,0.35f);
				GL11.glRotatef(-30, 1, 0, 0);
				break;
			case 16: 
				GL11.glTranslatef(0f,0.15f,-0.32f);
				GL11.glRotatef(30, 1, 0, 0);
				break;

			case 17:
				GL11.glTranslatef(0.25f,0.12f,0f);
				GL11.glRotatef(30, 0, 0, 1);
				break;

			default:
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

				if(scale > 0f){
					f2 = scale;
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
		if (var19 < 2)
		{
			var24 = 1;
		}
		else
		{
			if (var19 < 16)
			{
				var24 = 2;
			}
			else
			{
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

	protected void renderBelt(ItemStack itemstack, EntityPlayer player){
		GL11.glPushMatrix();

		GL11.glTranslatef(0f,0.65f,0f);

		if(player.isSneaking()){
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glTranslatef(0f,0.1f,0.3f);
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

				this.blockrender.renderBlockAsItem(Block.blocksList[itemstack.itemID], itemstack.getItemDamage(), f3);

				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();
	}

	protected void renderShoulderBelt(ItemStack itemstack, EntityPlayer player){
		GL11.glPushMatrix();
		GL11.glTranslatef(0.02f,0.35f,0f);
		if(player.isSneaking()){
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glRotatef(-50, 0, 0, 1);
			GL11.glTranslatef(-0.05f,0.05f,0.15f);
		}else{
			GL11.glRotatef(-50, 0, 0, 1);
		}

		if ((itemstack != null) && (Item.itemsList[itemstack.itemID] != null))
		{
			bindTextureMap(itemstack);
			/*=======BLOCKS=======*/
			if ((itemstack.itemID < 256) && (RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())))
			{
				float f2 = 0.25F;
				int i1 = Block.blocksList[itemstack.itemID].getRenderType();

				if ((i1 == 1) || (i1 == 19) || (i1 == 12) || (i1 == 2)){
					f2 = 0.5F;
				}
				GL11.glPushMatrix();

				GL11.glScalef(f2+0.55f, 0.1f, f2+0.05f);
				float f3 = 1.0F;

				this.blockrender.renderBlockAsItem(Block.blocksList[itemstack.itemID], itemstack.getItemDamage(), f3);

				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();
	}


	protected void renderHeldSword(ItemStack heldItem, EntityPlayer player){

		//		FMLLog.getLogger().info("" + player.username);

		if(heldItem != null)
			if(heldItem.getItem() instanceof ItemSword)
				swordStack.put(player.username, heldItem.copy());

		if(swordStack.get(player.username) != null && player.inventory.hasItemStack(swordStack.get(player.username))){
			if(heldItem == null)
				if(prevSwordStack.get(player.username) != null)
				{
					if(thirdSwordStack.get(player.username) == null)
						thirdSwordStack.put(player.username, prevSwordStack.get(player.username));
					else if(thirdSwordStack.get(player.username) != prevSwordStack.get(player.username)){
						prevSwordStack.put(player.username, thirdSwordStack.get(player.username));
						thirdSwordStack.put(player.username, prevSwordStack.get(player.username));
					}

				}else{
					prevSwordStack.put(player.username, swordStack.get(player.username));
				}
			else if (heldItem != null){

				if(prevSwordStack.get(player.username) != null)
				{
					if(thirdSwordStack.get(player.username) == null)
						thirdSwordStack.put(player.username, heldItem);
					else if(thirdSwordStack.get(player.username) != heldItem){
						prevSwordStack.put(player.username, thirdSwordStack.get(player.username));
						if(heldItem.getItem() instanceof ItemSword){
							thirdSwordStack.put(player.username, heldItem);
						}
					}
				}else{
					prevSwordStack.put(player.username, heldItem);
				}
			}
		}else
			prevSwordStack.remove(player.username);

		if(prevSwordStack != null){
			GL11.glPushMatrix();
			GL11.glTranslatef(0.44f, 1.25f, -0.2f);
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glRotatef(180, 0, 0, 1);
			addItemToBelt(prevSwordStack.get(player.username), player,5, 0.7f);
			GL11.glPopMatrix();
		}

		//		FMLLog.getLogger().info("" + swordStack.toString() + " " + prevSwordStack.toString() + " " +thirdSwordStack.toString());
	}

	protected void renderHeldPickaxe(ItemStack heldItem, EntityPlayer player){
		if(heldItem != null)
			if(heldItem.getItem() instanceof ItemPickaxe)
				pickaxeStack.put(player.username, heldItem);

		if(pickaxeStack.get(player.username) != null && player.inventory.hasItemStack(pickaxeStack.get(player.username))){
			if(heldItem == null)
				if(prevPickaxeStack.get(player.username) != null)
				{
					if(thirdPickaxeStack.get(player.username) == null)
						thirdPickaxeStack.put(player.username, prevPickaxeStack.get(player.username));
					else if(thirdPickaxeStack.get(player.username) != prevPickaxeStack.get(player.username)){
						prevPickaxeStack.put(player.username, thirdPickaxeStack.get(player.username));
						thirdPickaxeStack.put(player.username, prevPickaxeStack.get(player.username));
					}

				}else{
					prevPickaxeStack.put(player.username, pickaxeStack.get(player.username));
				}
			else if (heldItem != null){

				if(prevPickaxeStack.get(player.username) != null)
				{
					if(thirdPickaxeStack.get(player.username) == null)
						thirdPickaxeStack.put(player.username, heldItem);
					else if(thirdPickaxeStack.get(player.username) != heldItem){
						prevPickaxeStack.put(player.username, thirdPickaxeStack.get(player.username));
						if(heldItem.getItem() instanceof Item ){
							if(heldItem.getItem() instanceof ItemPickaxe){
								thirdPickaxeStack.put(player.username, heldItem);
							}
						}
					}
				}else{
					prevPickaxeStack.put(player.username, heldItem);
				}
			}
		}else
			prevPickaxeStack.remove(player.username);

		if(prevPickaxeStack != null  && heldItem != prevPickaxeStack.get(player.username)){
			GL11.glPushMatrix();
			GL11.glTranslatef(-0.14f, 1.35f, -0.1f);
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glRotatef(180, 0, 0, 1);
			addItemToBelt(prevPickaxeStack.get(player.username), player,5, 0.7f);
			GL11.glPopMatrix();
		}
	}


	protected void renderHeldTool(ItemStack heldItem, EntityPlayer player){

		if(heldItem != null)
			if(heldItem.getItem() instanceof ItemPickaxe || heldItem.getItem() instanceof ItemSword){
			}

			else if(heldItem.getItem().isFull3D() && heldItem.getItem() instanceof Item)
				toolStack.put(player.username, heldItem);

		if(toolStack.get(player.username) != null && player.inventory.hasItemStack(toolStack.get(player.username))){

			if(heldItem == null)
				if(prevToolStack.get(player.username) != null)
				{
					if(thirdToolStack.get(player.username) == null)
						thirdToolStack.put(player.username, prevToolStack.get(player.username));
					else if(thirdToolStack.get(player.username) != prevToolStack.get(player.username)){
						prevToolStack.put(player.username, thirdToolStack.get(player.username));
						thirdToolStack.put(player.username, prevToolStack.get(player.username));
					}

				}else{
					prevToolStack.put(player.username, toolStack.get(player.username));
				}
			else if (heldItem != null){

				if(prevToolStack.get(player.username) != null)
				{
					if(thirdToolStack.get(player.username) == null)
						thirdToolStack.put(player.username, heldItem);
					else if(thirdToolStack.get(player.username) != heldItem){
						prevToolStack.put(player.username, thirdToolStack.get(player.username));
						if(heldItem.getItem() instanceof Item ){
							if(heldItem.getItem() instanceof ItemPickaxe || heldItem.getItem() instanceof ItemSword){

							}
							else if (heldItem.getItem().isFull3D() || heldItem.getItemUseAction().equals(EnumAction.bow) || heldItem.getItem().itemID == Item.pocketSundial.itemID)
								thirdToolStack.put(player.username, heldItem);
						}
					}
				}else{
					prevToolStack.put(player.username, heldItem);
				}
			}

		}else
			prevToolStack.remove(player.username);

		if(prevToolStack != null){

			if(!player.isSneaking()){
				GL11.glPushMatrix();
				GL11.glTranslatef(-0.15f, -0.25f, 0.32f);
				addItemToBelt(prevToolStack.get(player.username), player,100, 0.7f);
				GL11.glPopMatrix();
			}else{
				GL11.glPushMatrix();	
				GL11.glRotatef(30, 1, 0, 0);
				GL11.glTranslatef(-0.15f, -0.25f, 0.28f);
				addItemToBelt(prevToolStack.get(player.username), player,100, 0.7f);
				GL11.glPopMatrix();
			}
		}
	}
}
