package threeDitems.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import threeDitems.mod_3d;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public abstract class Render3DInterface implements IItemRenderer /*,ISimpleBlockRenderingHandler*/{
	public ModelBase model;
	private int renderId = -1;
	
	private final ResourceLocation modelTexture;
	private final ResourceLocation glint;

	public Render3DInterface(ModelBase model, String texture){
		this.model = model;

		modelTexture = new ResourceLocation(texture);
		glint = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	}
	
	RenderItem rend = new RenderItem();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return mod_3d.inst.isRendering3D ? type != type.INVENTORY && !rend.renderInFrame :false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {

		return   mod_3d.inst.isRendering3D ? (type == ItemRenderType.ENTITY ? false: true) : false;
	} 

	float zLevel = 0;
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		RenderHelper.enableStandardItemLighting();
		//		if(hasSmoothingLighting())
		//			GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glPushMatrix();

		renderObject(type, item,false, data);

		if (item.hasEffect(item.getItemDamage()))
		{
			float tickModifier = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 48.0F;
			Minecraft.getMinecraft().renderEngine.func_110577_a(glint);
			GL11.glEnable(GL11.GL_BLEND);
			float var20 = 0.5F;
			GL11.glColor4f(var20, var20, var20, 1);
			GL11.glDepthFunc(GL11.GL_EQUAL);
			GL11.glDepthMask(false);

			for (int var21 = 0; var21 < 2; var21++) {
				GL11.glDisable(GL11.GL_LIGHTING);
				float var22 = 0.76F;
				GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
				GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				float var23 = tickModifier * (0.001F + (float) var21 * 0.003F) * 20;
				float var24 = 0.33333334F;
				GL11.glScalef(var24, var24, var24);
				GL11.glRotatef(-50, 0, 0, 1);
				GL11.glTranslatef(0, var23, 0);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				if(!shouldIgnoreModelRendering())
					model.render((Entity)data[1],0,0,0,0,0,0.0625f);
			}

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glDepthMask(true);
			GL11.glLoadIdentity();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
		}
		GL11.glPopMatrix();
		GL11.glColor4f(1, 1, 1, 1.0F);
		RenderHelper.enableStandardItemLighting();
	}

	public void renderObject(ItemRenderType type, ItemStack item, boolean glow, Object... data){
		if(!shouldIgnoreTextureRendering())
			Minecraft.getMinecraft().renderEngine.func_110577_a(modelTexture);


		if(type == ItemRenderType.ENTITY){
			renderEntity();
		}
		else if(type == ItemRenderType.EQUIPPED){
			if((Entity)data[1] instanceof EntityPlayer)
				renderEquipped();
		}
		else if(type == ItemRenderType.EQUIPPED_FIRST_PERSON){
			renderEquippedFP();
		}
		
		renderScale();


		if(glow)
			Minecraft.getMinecraft().renderEngine.func_110577_a(glint);	

		preSpecials(item, model);
		if(!shouldIgnoreModelRendering())
			model.render((Entity)data[1],0,0,0,0,0,0.0625f);
		postSpecials(item, model);

	}

//	@Override
//	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
//			Block block, int modelId, RenderBlocks renderer) {
//		RenderHelper.enableStandardItemLighting();
//		if(hasSmoothingLighting())
//			GL11.glShadeModel(GL11.GL_SMOOTH);
//
//		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//
//		int var3 = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
//		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var3 % 65536, var3 / 65536);
//		GL11.glPushMatrix();
//		GL11.glTranslated(x + Tessellator.instance.xOffset + 0.5, y + Tessellator.instance.yOffset, z + Tessellator.instance.zOffset + 0.5);
//
//		if(preRenderBlock(world, x, y, z, block)){
//
//			int color = block.colorMultiplier(world, x, y, z);
//			GL11.glColor4f((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, 1);
//
//			renderScale();
//			GL11.glScalef(0.75F,0.75F,0.75F);
//			//			model.render(null, 0, 0, 0, 0, 0, 0.0625f);
//		}
//
//		GL11.glPopMatrix();
//
//		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//		RenderHelper.disableStandardItemLighting();
//		//Minecraft.getMinecraft().renderEngine.bindTexture("/terrain.png");
//		if (Minecraft.isAmbientOcclusionEnabled())
//		{
//			GL11.glShadeModel(GL11.GL_SMOOTH);
//		}
//		else
//		{
//			GL11.glShadeModel(GL11.GL_FLAT);
//		}
//
//		return true;
//	}

	public boolean preRenderBlock(IBlockAccess world, int x, int y, int z, Block block){
		return true;
	}

//	@Override
//	public boolean shouldRender3DInInventory() {
//		return false;
//	}

	public boolean hasSmoothingLighting(){
		return false;
	}

//	@Override
//	public int getRenderId() {
//		if(renderId < 0)
//			renderId = RenderingRegistry.getNextAvailableRenderId();
//		return renderId;
//	}
//
//	@Override
//	public void renderInventoryBlock(Block block, int metadata, int modelID,
//			RenderBlocks renderer) {
//	}

	public abstract void renderEquippedFP();
	public abstract void renderEntity();
	public abstract void renderEquipped();
	public abstract void renderScale();

	public void preSpecials(ItemStack item, ModelBase model){
	}
	public void postSpecials(ItemStack item, ModelBase model){
	}

	/**Do not use this unless you know what you are doing !
	 * Used for Armor and minecarts to ignore the actual loading of the primary, 
	 * If you use this, make sure to manually load a texture*/
	protected boolean shouldIgnoreTextureRendering(){
		return false;
	}

	/**used to bypass general model rendering. used for blocks and ItemFrame*/
	 protected boolean shouldIgnoreModelRendering(){
		return false;
	}
}
