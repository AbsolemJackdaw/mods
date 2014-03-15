package decorativeCollectibles.tileEntity;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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

import decorativeCollectibles.models.ModelGoblet;

public class TileEntitySpecialRendererGoblet extends TileEntitySpecialRenderer{

	ModelGoblet goblet = new ModelGoblet();
	ResourceLocation LOC0 = new ResourceLocation("collectibles:textures/models/goblet_0.png");
	ResourceLocation LOC1 = new ResourceLocation("collectibles:textures/models/goblet_1.png");
	ResourceLocation LOC2 = new ResourceLocation("collectibles:textures/models/goblet_2.png");
	ResourceLocation LOC3 = new ResourceLocation("collectibles:textures/models/goblet_3.png");
	ResourceLocation LOC4 = new ResourceLocation("collectibles:textures/models/goblet_4.png");
	ResourceLocation LOC5 = new ResourceLocation("collectibles:textures/models/goblet_5.png");
	ResourceLocation LOC6 = new ResourceLocation("collectibles:textures/models/goblet_6.png");

	private RenderBlocks blockRender = new RenderBlocks();

	@Override
	public void renderTileEntityAt(TileEntity te, double d, double d1,
			double d2, float f) {

		Block block = Block.blocksList[te.worldObj.getBlockId(te.xCoord,te.yCoord, te.zCoord)];
		int meta = block.getDamageValue(te.worldObj, te.xCoord, te.yCoord, te.zCoord);

		/*Basic 'block' to start of with for te renders. without this, it would bob derpely when moving the player.*/
		GL11.glPushMatrix(); 
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 0.5F, (float)d2 + 0.5F); 
		GL11.glScalef(1.0F, -1F, -1F);

		/*bind textures*/
		switch(meta){
		case 0 :bindTexture(LOC0);	break;
		case 1 :bindTexture(LOC1);	break;
		case 2 :bindTexture(LOC2);	break;
		case 3 :bindTexture(LOC3);	break;
		case 4 :bindTexture(LOC4);	break;
		case 5 :bindTexture(LOC5);	break;
		case 6 :bindTexture(LOC6);	break;
		default : bindTexture(LOC0); break;
		}
		
		goblet.renderAll(0.0625f);

		GL11.glDisable(GL11.GL_BLEND);
	
		if(meta == 5 || meta == 6 ){
			ItemStack fluid = new ItemStack(meta == 5 ? Block.lavaStill : Block.waterStill);
			GL11.glPushMatrix();
			renderFluidBlock(fluid);
			GL11.glPopMatrix();
		}

		GL11.glPopMatrix();
	}

	private void renderFluidBlock(ItemStack itemstack){
		GL11.glPushMatrix();
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

				this.blockRender.renderBlockAsItem(Block.blocksList[itemstack.itemID], itemstack.getItemDamage(), f3);

				GL11.glDisable(GL11.GL_BLEND);

				GL11.glPopMatrix();
			}
			/*=====ITEMS=====*/
			else
			{
				GL11.glPushMatrix();

				try
				{
					float scale = 0.2f;
					GL11.glScalef(scale, scale ,scale);
					GL11.glTranslatef(0f, -0.07f, -0.2f);
					GL11.glRotatef(90, 1, 0, 0);
					
					if (itemstack.getItem().requiresMultipleRenderPasses())
					{
						for (int k = 0; k <= 1; k++)
							drawItem(itemstack, k);
					}
					else{
						drawItem(itemstack, 0);
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

		}
		GL11.glPopMatrix();
	}

	private void bindTextureMap(ItemStack item) {
		Minecraft.getMinecraft().renderEngine.bindTexture(RenderManager.instance.renderEngine.getResourceLocation(item.getItemSpriteNumber()));
	}
}
