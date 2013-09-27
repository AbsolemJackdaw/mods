package gravestone.grave.te;
import gravestone.grave.ModelGrave;
import gravestone.grave.ModelHead;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TEGraveSpecial extends TileEntitySpecialRenderer // because your block is, and you are too.
{
	private ModelGrave model = new ModelGrave(); 
	private ModelHead modelhead = new ModelHead();

	private ModelHead modelarmorhead = new ModelHead();
	private ModelHead modelarmorchest = new ModelHead();

	//	private ModelBiped modelBip = new ModelBiped(1.5f);
	public static TEGraveSpecial teRender;


	public void setTileEntityRenderer(TileEntityRenderer par1TileEntityRenderer)
	{
		super.setTileEntityRenderer(par1TileEntityRenderer);
		teRender = this;
	}

	public void renderAModelAt(TEGrave tile, double d, double d1, double d2, float f)
	{

		renderBeam(tile, d, d1, d2);

		this.switchTexture(tile.theMeta);
		float rot = tile.ModelRotation;
		GL11.glPushMatrix(); //start
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
		GL11.glScalef(1.0F, -1F, -1F);

		if(tile != null)
		{
			model.showBasic(false);
			model.showZerk(false);
			model.showTomb(false);
			model.showPillar(false);
			model.renderSkeleton(false);
			model.renderCross(false);
			model.renderAngel(false);
			model.renderKnight(false);
			switch(tile.theMeta)
			{
			case 1:
				model.showBasic(true);
				GL11.glRotatef(0+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			case 2:
				model.showZerk(true);
				GL11.glRotatef(-90+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			case 3 :
				model.showTomb(true);
				GL11.glRotatef(-90+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			case 4:
				model.showPillar(true);
				model.renderSkeleton(true);
				GL11.glRotatef(90+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			case 5:
				model.showPillar(true);
				GL11.glRotatef(0+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			case 6:
				model.renderCross(true);
				GL11.glRotatef(-90+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
			case 7:
				model.showPillar(true);
				GL11.glRotatef(0+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			case 8:
				model.renderAngel(true);
				GL11.glRotatef(0+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			case 9:
				model.renderKnight(true);
				GL11.glRotatef(0+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			default :
				model.showBasic(true);
				GL11.glRotatef(0+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			}
		}

		model.renderModel(0.0625F); //renders and 0.0625F is exactly 1/16. every block has 16 entities/pixels. if you make the number 1, every pixel will be as big as a block. make it 0.03125 and your block will be half as big as a normal one.
		GL11.glPopMatrix(); //end

		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
		GL11.glScalef(0.7F, -0.7F, -0.7F);
		if(tile != null)
		{			
			ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
			if (tile.playername != null && tile.playername.length() > 0)
			{
				resourcelocation = AbstractClientPlayer.getLocationSkin(tile.playername);
				AbstractClientPlayer.getDownloadImageSkin(resourcelocation, tile.playername);

			}else{
				resourcelocation = new ResourceLocation( "textures/entity/steve.png");
			}
			Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);

			switch(tile.theMeta)
			{
			case 5:
				model.showBasic(false);
				model.showZerk(false);
				model.showTomb(false);
				model.showPillar(true);
				model.renderSkeleton(false);
				model.renderCross(false);
				model.renderAngel(false);
				model.renderKnight(false);
				GL11.glRotatef(-90+rot, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
				break;
			default :
				model.showBasic(false);
				model.showZerk(false);
				model.showTomb(false);
				model.showPillar(false);
				model.renderSkeleton(false);
				model.renderCross(false);
				break;
			}
		}
		switch(tile.theMeta)
		{
		case 5:
			modelhead.renderHead(0.0625f);
			break;
		default:
			break;
		}
		ArmorHelper helper = new ArmorHelper();
		
		GL11.glPushMatrix();
		if(tile.getStackInSlot(tile.getSizeInventory()-1) != null && tile.theMeta == 5){
			//			FMLLog.getLogger().info("in");
			float f2 = 1.2f;
			GL11.glScalef(f2,f2,f2);
			GL11.glTranslatef(0f, 0.05f, 0f);
			ItemStack item = tile.getStackInSlot(tile.getSizeInventory()-1);
			helper.setArmorModel(modelarmorhead, item, 
					((ItemArmor)item.getItem()).armorType, RenderBiped.bipedArmorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
			modelarmorhead.renderHead(0.0625f); //render(null, 0, 0, 0, 0, 0, 0.0625f);
		}
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		if(tile.getStackInSlot(tile.getSizeInventory()-2) != null && tile.theMeta == 5){
			float f2 = 1.1f;
			GL11.glScalef(f2,f2,f2);
			GL11.glTranslatef(0f, -0.02f, 0f);
			ItemStack item = tile.getStackInSlot(tile.getSizeInventory()-2);
			helper.setArmorModel(modelarmorchest, item, 
					((ItemArmor)item.getItem()).armorType, RenderBiped.bipedArmorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
			modelarmorchest.renderHead(0.0625f); // render(null, 0, 0, 0, 0, 0, 0.0625f);
		}
		GL11.glPopMatrix();

		GL11.glPopMatrix();

	}

	private void switchTexture(int theMeta) {
		ResourceLocation resourcelocation = null;
		switch(theMeta)
		{
		case 1:
			resourcelocation =new ResourceLocation("subaraki:grave/gravestone.png");
			break;
		case 2:
			resourcelocation =new ResourceLocation("subaraki:grave/gravezerk.png");
			break;
		case 3 :
			resourcelocation =new ResourceLocation("subaraki:grave/gravestone.png");
			break;
		case 4:
			resourcelocation =new ResourceLocation("subaraki:grave/gravepillar.png");
			break;
		case 5:
			resourcelocation =new ResourceLocation("subaraki:grave/gravepillar.png");
			break;
		case 6:
			resourcelocation =new ResourceLocation("subaraki:grave/gravewood.png");
			break;
		case 7:
			resourcelocation =new ResourceLocation("subaraki:grave/gravepillar.png");
			break;
		case 8:
			resourcelocation =new ResourceLocation("subaraki:grave/Angel.png");
			break;
		case 9:
			resourcelocation =new ResourceLocation("subaraki:grave/knight.png");
			break;
		default:
			resourcelocation =new ResourceLocation("subaraki:grave/gravestone.png");
			break;
		}
		this.bindTexture(resourcelocation);
	}



	private void renderBeam(TEGrave tileentity, double d, double d1, double d2/*x,y,z*/){

		if(tileentity!= null && tileentity.hasItems){
			//			float f1 = 1;
			Tessellator tesselator = Tessellator.instance;
			GL11.glDisable(3553 /*GL_TEXTURE_2D */);
			GL11.glDisable(2896/*GL_LIGHTING */);
			GL11.glDisable(2912/*GL_FOG */);
			GL11.glDepthMask(false);
			GL11.glDisable(GL11.GL_CULL_FACE);

			GL11.glEnable(3042/*GL_BLEND */);
			//			    GL11.glBlendFunc(770/* GL_SRC_ALPHA */, 1/*GL_LINES */);
			GL11.glBlendFunc(770/* GL_SRC_ALPHA */, GL11.GL_LINES/*GL_LINES */);

			char var5 = 0x000F0;
			int var6 = var5 % 65536;
			int var7 = var5 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0F, var7 / 1.0F);

			int height = 256;
			//			float brightness = 0.06F;
			double topWidthFactor = 0.5D;
			double bottomWidthFactor = 0.5D;
			float r = 255;
			float b = 255;
			float g = 255;

			// true when player is someone else.
			boolean other = tileentity.playername.equals(Minecraft.getMinecraft().thePlayer.username) ? false: true;
			float otherFloat = 0f; //other ? 0.6f : 0f;
			float color =  other && !Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode ? 0f : 0.7f;

			float colorOther = 0;
			for (int width = 0; width < (other ? 2: 5); width++)
			{
				tesselator.startDrawing(5);
				tesselator.setColorRGBA_F(color-otherFloat,color-otherFloat,color, 0.11f);
				double var32 = 0.1D + width * 0.2D;
				var32 *= topWidthFactor;

				double var34 = 0.1D + width * 0.2D;
				var34 *= bottomWidthFactor;

				for (int side = 0; side < 5; side++)
				{
					double vertX2 = d + 0.5D - var32;
					double vertZ2 = d2 + 0.5D - var32;

					if ((side == 1) || (side == 2))
					{
						vertX2 += var32 * 2.0D;
					}

					if ((side == 2) || (side == 3))
					{
						vertZ2 += var32 * 2.0D;
					}

					double vertX1 = d + 0.5D - var34;
					double vertZ1 = d2 + 0.5D - var34;

					if ((side == 1) || (side == 2))
					{
						vertX1 += var34 * 2.0D;
					}

					if ((side == 2) || (side == 3))
					{
						vertZ1 += var34 * 2.0D;
					}

					tesselator.addVertex(vertX1, d1 + 0.0D, vertZ1);
					tesselator.addVertex(vertX2, d1 + height, vertZ2);
				}

				tesselator.draw();
			}
			GL11.glDisable(3042/*GL_BLEND */);
			GL11.glEnable(2912/*GL_FOG */);
			GL11.glEnable(2896/*GL_LIGHTING */);
			GL11.glEnable(3553/*GL_TEXTURE_2D */);
			GL11.glDepthMask(true);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1,	double d2, float f) {
		renderAModelAt((TEGrave)tileentity, d, d1, d2, f);	
	}

}