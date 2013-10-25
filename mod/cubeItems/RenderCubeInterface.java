package cubeItems;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public abstract class RenderCubeInterface implements IItemRenderer {
	public ModelCubeWorld model;

	private final ResourceLocation glint;


	RenderItem rend = new RenderItem();

	public RenderCubeInterface(ModelCubeWorld model){
		this.model = model;

		glint = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	}

	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != type.INVENTORY && !rend.renderInFrame ? true : false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return type != type.INVENTORY && !rend.renderInFrame ? true : false;
	} 

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {


//		if(!shouldIgnoreTextureRendering())
//			Minecraft.getMinecraft().renderEngine.bindTexture(modelTexture);
		
		GL11.glPushMatrix();
		
		renderScale();
		
		switch(type){
		case ENTITY:
			renderEntity();
			break;
		case EQUIPPED:
			renderEquipped();
			break;
		case EQUIPPED_FIRST_PERSON:
			renderEquippedFP();
			break;
		default : 
			break;
		}
		

		preSpecials(item, model, data);
		
		if(!shouldIgnoreModelRendering()){
			model.render();
		}
		
		postSpecials(item, model, data);
		
		renderglow(item);

		GL11.glPopMatrix();

	}

	private void renderglow(ItemStack item){
		if (item.hasEffect(item.getItemDamage()))
		{
			float tickModifier = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 48.0F;
			Minecraft.getMinecraft().renderEngine.bindTexture(glint);
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
					model.render();
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
	}


	public abstract void renderEquippedFP();
	public abstract void renderEntity();
	public abstract void renderEquipped();
	public abstract void renderScale();

	public void preSpecials(ItemStack item, ModelCubeWorld model, Object... data){
	}
	public void postSpecials(ItemStack item, ModelCubeWorld model, Object... data){
	}

	/**Do not use this unless you know what you are doing !
	 * Used for Armor and minecarts to ignore the actual loading of the primary, 
	 * If you use this, make sure to manually load a texture
	 * UNUSED*/
	protected boolean shouldIgnoreTextureRendering(){
		return false;
	}

	/**used to bypass general model rendering. used for blocks and ItemFrame*/
	protected boolean shouldIgnoreModelRendering(){
		return false;
	}


	public boolean preRenderBlock(IBlockAccess world, int x, int y, int z, Block block){
		return true;
	}
}
