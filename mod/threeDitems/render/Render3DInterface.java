package threeDitems.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import threeDitems.mod_3d;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public abstract class Render3DInterface implements IItemRenderer,ISimpleBlockRenderingHandler{
	public ModelBase model;
	private int renderId = -1;

	private final ResourceLocation modelTexture;
	private final ResourceLocation glint;

	public Render3DInterface(ModelBase model, String texture){
		this.model = model;

		modelTexture = new ResourceLocation(texture);
		glint = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return mod_3d.inst.isRendering3D ? type != type.INVENTORY : false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		//		switch (type) {
		//		case ENTITY:
		//			return false;
		//		default:
		//			return item.itemID != Block.pumpkin.blockID; //true if item is not pumpkin, false if item is pumpkin
		//		}
		return  mod_3d.inst.isRendering3D ? (type == ItemRenderType.ENTITY ? false: true) : false;
	} 

	float zLevel = 0;
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		RenderHelper.enableStandardItemLighting();
		//		if(hasSmoothingLighting())
		//			GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glPushMatrix();

		renderItem(type, item,false);

		if (item.hasEffect(item.getItemDamage()))
		{
			GL11.glDepthFunc(GL11.GL_EQUAL);
			GL11.glDisable(GL11.GL_LIGHTING);
			Minecraft.getMinecraft().renderEngine.func_110577_a(glint);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
			float f7 = 0.76F;
			GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			float f8 = 0.125F;
			GL11.glScalef(f8, f8, f8);
			float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
			GL11.glTranslatef(f9, 0.0F, 0.0F);
			GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
			//renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
			renderItem(type, item, true);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(f8, f8, f8);
			f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
			GL11.glTranslatef(-f9, 0.0F, 0.0F);
			GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
			//renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
			renderItem(type, item, true);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
		}

		GL11.glPopMatrix();
		GL11.glColor4f(1, 1, 1, 1.0F);
		RenderHelper.enableStandardItemLighting();
	}

	public void renderItem(ItemRenderType type, ItemStack item, boolean glow){
		if(!shouldIgnoreTextureRendering())
			Minecraft.getMinecraft().renderEngine.func_110577_a(modelTexture);

		if(type == ItemRenderType.ENTITY){
			renderEntity();
		}
		if(type == ItemRenderType.EQUIPPED){
			renderEquipped();
		}
		if(type == ItemRenderType.EQUIPPED_FIRST_PERSON){
			renderEquippedFP();
		}
		renderScale();

		if(glow)
			Minecraft.getMinecraft().renderEngine.func_110577_a(glint);	


		preSpecials(item, model);
//		if(!shouldIgnoreModelRendering())
			model.render(null,0,0,0,0,0,0.0625f);
		postSpecials(item, model);

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		RenderHelper.enableStandardItemLighting();
		if(hasSmoothingLighting())
			GL11.glShadeModel(GL11.GL_SMOOTH);

		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		int var3 = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var3 % 65536, var3 / 65536);
		GL11.glPushMatrix();
		GL11.glTranslated(x + Tessellator.instance.xOffset + 0.5, y + Tessellator.instance.yOffset, z + Tessellator.instance.zOffset + 0.5);

		if(preRenderBlock(world, x, y, z, block)){

			int color = block.colorMultiplier(world, x, y, z);
			GL11.glColor4f((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, 1);

			renderScale();
			GL11.glScalef(0.75F,0.75F,0.75F);
			//			model.render(null, 0, 0, 0, 0, 0, 0.0625f);
		}

		GL11.glPopMatrix();

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		//Minecraft.getMinecraft().renderEngine.bindTexture("/terrain.png");
		if (Minecraft.isAmbientOcclusionEnabled())
		{
			GL11.glShadeModel(GL11.GL_SMOOTH);
		}
		else
		{
			GL11.glShadeModel(GL11.GL_FLAT);
		}

		return true;
	}

	public boolean preRenderBlock(IBlockAccess world, int x, int y, int z, Block block){
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	public boolean hasSmoothingLighting(){
		return false;
	}

	@Override
	public int getRenderId() {
		if(renderId < 0)
			renderId = RenderingRegistry.getNextAvailableRenderId();
		return renderId;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

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
	@Deprecated protected boolean shouldIgnoreTextureRendering(){
		return false;
	}
}
