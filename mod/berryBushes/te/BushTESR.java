package berryBushes.te;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class BushTESR extends TileEntitySpecialRenderer{

	private static final bush b = new bush();

	private static final ResourceLocation glint = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	private static final ResourceLocation loc = new ResourceLocation("berries:bush.png");

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1,
			double d2, float f) {

		
		if(tileentity != null && tileentity instanceof BushTE){
			
			BushTE te = (BushTE)tileentity;
			this.bindTexture(loc);
			
			GL11.glPushMatrix();

			GL11.glTranslatef((float)d + 0.4F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
			GL11.glScalef(1.0F, -1F, -1F);

			if(!te.isCrop){

				float f1 = 0.8f;
				float f3 = 1.2f;
				float f4 = 1.3f;

				switch (te.Meta) {
				case 0:
					GL11.glTranslatef(0, 0.3f, 0);
					GL11.glScalef(f1,f1,f1);
					break;
				case 1:
					GL11.glTranslatef(0, 0, 0);
					GL11.glScalef(1, 1, 1);
					break;
				case 2:
					GL11.glTranslatef(0, -0.3f, 0);
					GL11.glScalef(f3,f3,f3);
					break;
				case 3:
					GL11.glTranslatef(0, -0.45f, 0);
					GL11.glScalef(f4,f4,f4);
					break;
				default:
					break;
				}
			}
			// te.isCrop
			else{
				float maxScale = 1.35f;
				float minScale = -0.9f;
				float tot = (-(minScale) + maxScale);

				GL11.glTranslatef(0,  maxScale - ((((float)te.count)/24000.0f)*tot), -0.1f);

				float sc = 0.1f+(te.count/16000.0f);
				GL11.glScalef(sc,sc,sc);

			}

			b.render(0.0625f);
			GL11.glPopMatrix();



			if(te.isCrop){

				GL11.glPushMatrix();
				GL11.glTranslatef((float)d + 0.5F, (float)d1 + 0.8F, (float)d2 + 0.5F);

				GL11.glEnable(32826);

				GL11.glTranslatef(0, 0, 0);


				if ((te.stack != null) && (Item.itemsList[te.stack.itemID] != null))
				{
					bindTextureMap(te.stack);

					/*=======BLOCKS=======*/
					//no blocks. check TESRShrine in PetBuddy mod for that

					/*=====ITEMS=====*/
					GL11.glPushMatrix();
					float f9 = 0.5f;
					try
					{
						GL11.glScalef(f9,f9,f9);

						float maxScale = -1.5f;
						float minScale = -0.7f;
						
						float maxScaleZ = 0.1f;
						float minScaleZ = -1f;
						
						float tot = (-(minScale) + maxScale);
						float totZ = (-(minScaleZ) + maxScaleZ);

						//maxScale - ((((float)te.count)/24000.0f)*tot)
						GL11.glTranslatef(-0.1f,
								maxScale - ((((float)te.count)/24000.0f)*tot),
								maxScaleZ - ((((float)te.count)/24000.0f)*totZ));

						float sc = 0.1f+(te.count/30000.0f);
						GL11.glScalef(sc,sc,sc);
						
						if (te.stack.getItem().requiresMultipleRenderPasses())
						{
							for (int k = 0; k <= 1; k++)
								drawItem(te.stack, k);
						}
						else{
							drawItem(te.stack, 0);
						}
						
						/*===Duplicates of few above lines for rendering a second berry item===*/
						GL11.glTranslatef(0, 0, 2.2f);
						GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);	

						if (te.stack.getItem().requiresMultipleRenderPasses())
						{
							for (int k = 0; k <= 1; k++)
								drawItem(te.stack, k);
						}
						else{
							drawItem(te.stack, 0);
						}
					}

					catch (Throwable throwable)
					{
						throw new RuntimeException(throwable);
					}

					GL11.glPopMatrix();
				}
				GL11.glDisable(32826);
				GL11.glPopMatrix();	
			}
		}
	}

	private void bindTextureMap(ItemStack item) {
		bindTexture(RenderManager.instance.renderEngine.getResourceLocation(item.getItemSpriteNumber()));
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
				bindTexture(glint);
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
}
