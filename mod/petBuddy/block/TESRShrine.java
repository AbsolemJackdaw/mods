package petBuddy.block;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
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

import petBuddy.PetBuddyMain;

public class TESRShrine extends TileEntitySpecialRenderer{

	private static final ResourceLocation glint = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	private static final Shrine shrine = new Shrine();
	private static final ModelBiped pet = new ModelBiped();

	RenderBlocks blockrender = new RenderBlocks();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1,
			double d2, float f) {

		if(tileentity != null){
			TEShrine te = (TEShrine)tileentity;

			
			if(te.hasStatue){

				this.func_110628_a(new ResourceLocation("subaraki:mobs/puppet.png"));
				GL11.glPushMatrix();
				GL11.glColor3f( 1f,1f,1f);

				GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
				GL11.glScalef(1.0F, -1F, -1F);

				GL11.glScalef(0.2f,0.2f,0.2f);

				GL11.glTranslatef(0f, 2.8f, 0f);

				pet.bipedBody.render(0.0625f);
				pet.bipedHead.render(0.0625f);
				pet.bipedLeftArm.render(0.0625f);
				pet.bipedLeftLeg.render(0.0625f);
				pet.bipedRightArm.render(0.0625f);
				pet.bipedRightLeg.render(0.0625f);

				GL11.glTranslatef(0f, 2f, 0f);
				GL11.glScalef(1.5f, 1, 1.3f);
				pet.bipedHeadwear.render(0.0625f);

				GL11.glPopMatrix();					
			}	

			this.func_110628_a(new ResourceLocation("subaraki:mobs/Shrine.png"));
			GL11.glPushMatrix();

			GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
			GL11.glScalef(1.0F, -1F, -1F);

			shrine.render(0.0625f);
			shrine.candles(0.0625f);

			GL11.glPopMatrix();

			if(te.hasStatue){
				GL11.glPushMatrix();
				if(!te.cycleDone)
					GL11.glColor4f( 1f,0f + (float) te.cooldown / (40*20), 0f+ (float) te.cooldown / (30*20), 1f);
				else{
					float time = (float)Math.cos(PetBuddyMain.getSysTimeF()/10f) +0.4f;
					GL11.glColor4f( 1f,time, time, 1f);
				}

				this.func_110628_a(new ResourceLocation("subaraki:mobs/ShrineLayover.png"));
				GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
				GL11.glScalef(1.0F, -1F, -1F);
				shrine.render(0.0625f);
				shrine.candles(0.0625f);
				GL11.glPopMatrix();	
			}
			if(te.hasItemStack && te.itemToImbue != null)
			{
				GL11.glPushMatrix();
				GL11.glTranslatef((float)d + 0.5F, (float)d1 + 0.8F, (float)d2 + 0.5F);

				//				int i = te.getBlockMetadata() & 0x3;
				//
				//				switch (i)
				//				{
				//				case 0:
				//					GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				//					break;
				//				case 1:
				//					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
				//					break;
				//				case 2:
				//					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				//					break;
				//				case 3:
				//					GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
				//				}

				GL11.glEnable(32826);

				for(int j = 0; j <4; j++){
					switch (j)
					{
					case 0:
						GL11.glTranslatef(0, -0.5f, 0.21f);
						break;
					case 1:
						GL11.glTranslatef(0, 0, -0.42f);
						break;
					case 2:
						GL11.glTranslatef(0.2f, 0f, 0.21f);
						break;
					case 3:
						GL11.glTranslatef(-0.4f, 0f, 0f);
						break;
					default:
						GL11.glTranslatef(0, 0, 0);
						break;
					}

					if ((te.itemToImbue != null) && (Item.itemsList[te.itemToImbue.itemID] != null))
					{
						bindTextureMap(te.itemToImbue);
						/*=======BLOCKS=======*/
						if ((te.itemToImbue.itemID < 256) && (RenderBlocks.renderItemIn3d(Block.blocksList[te.itemToImbue.itemID].getRenderType())))
						{
							float f2 = 0.25F;
							int i1 = Block.blocksList[te.itemToImbue.itemID].getRenderType();

							if ((i1 == 1) || (i1 == 19) || (i1 == 12) || (i1 == 2))
							{
								f2 = 0.5F;
							}

							GL11.glPushMatrix();

							GL11.glScalef(f2, f2, f2);
							GL11.glTranslatef(0.0F, 0.35F, 0.0F);
							float f3 = 1.0F;
							GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
							GL11.glEnable(GL11.GL_BLEND);
							
							this.blockrender.renderBlockAsItem(Block.blocksList[te.itemToImbue.itemID], te.itemToImbue.getItemDamage(), f3);
							GL11.glDisable(GL11.GL_BLEND);

							GL11.glPopMatrix();
						}
						/*=====ITEMS=====*/
						else
						{
							GL11.glPushMatrix();
							float f9 = 0.5f;
							try
							{
								GL11.glScalef(f9,f9,f9);

								switch (j)
								{
								case 0:
									GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
									break;
								case 1:
									GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
									break;
								case 2:
									GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
									break;
								case 3:
									GL11.glRotatef(-90F, 0.0F, 1.0F, 0.0F);
									break;
								default:
									GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);	
									break;
								}

								if (te.itemToImbue.getItem().requiresMultipleRenderPasses())
								{
									GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
									GL11.glEnable(GL11.GL_BLEND);
									for (int k = 0; k <= 1; k++)
										drawItem(te.itemToImbue, k);
									GL11.glDisable(GL11.GL_BLEND);

								}
								else{
									GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
									GL11.glEnable(GL11.GL_BLEND);

									drawItem(te.itemToImbue, 0);
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
				}
				GL11.glDisable(32826);
				GL11.glPopMatrix();
			}

		}
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
			ItemRenderer.renderItemIn2D(var8, c2, c3, c1, c4, icon.func_130010_a(), icon.func_110967_i(), 0.0625F);

			if ((var18 != null) && (var18.hasEffect()) && (par2 == 0))
			{
				GL11.glDepthFunc(514);
				GL11.glDisable(2896);
				func_110628_a(glint);
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
		func_110628_a(RenderManager.instance.renderEngine.func_130087_a(item.getItemSpriteNumber()));
	}
}
