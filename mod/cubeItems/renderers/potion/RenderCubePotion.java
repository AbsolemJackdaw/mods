package cubeItems.renderers.potion;

import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cubeItems.ModelCubeWorld;
import cubeItems.RenderCubeInterface;

public class RenderCubePotion extends RenderCubeInterface{

	ModelCubeWorld potion_base_greyScale = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_bottom_greyscale.cub"));
	ModelCubeWorld potion_base_empty = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_bottom_empty.cub"));
	ModelCubeWorld potion_base_water = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_bottom_water.cub"));
	ModelCubeWorld potion_top_splash = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_top_splash.cub"));
	ModelCubeWorld potion_top_regular = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_top.cub"));

	ModelCubeWorld potion_bottom;

	private static final int EMPTY= 0;
	private static final int POTION = 1;
	private static final int EXPBOTTLE = 2;

	int type;
	/**0 empty, 1 potion, 2 exp bottle*/
	public RenderCubePotion(ModelCubeWorld model, int type) {
		super(model);

		this.type = type;

		switch (type) {
		case 0:
			potion_bottom = potion_base_empty;
			break;

		case 1:
			potion_bottom = potion_base_greyScale;
			break;

		case 2:
			potion_bottom = potion_base_greyScale;
			break;

		default:
			break;
		}
	}


	@Override
	public void postSpecials(ItemStack item, ModelCubeWorld model, Object... data) {
		super.postSpecials(item, model, data);

		//		GL11.glEnable(GL11.GL_BLEND);
		//				GL11.glDisable(GL11.GL_LIGHTING);
		//		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);

		boolean isColored = false;
		float r = 1;
		float g = 1;
		float b = 1;
		if((type == POTION) && (item.getItemDamage() > 0))
		{
			int color = ((ItemPotion)item.getItem()).getColorFromDamage(item.getItemDamage());

			//			color/=3;

			//			float red = (float)(color >> 16 & 255) /*/ 255.0F*/;
			//			float green = (float)(color >> 8 & 255) /*/ 255.0F*/;
			//			float blue = (float)(color & 255) /*/ 255.0F*/;

			float red = ((color >> 16) & 255) / 255.0F;
			float green = ((color >> 8) & 255) / 255.0F;
			float blue = (color & 255) / 255.0F;

			//			GL11.glColor4f(red, green,blue, 1.0F);
			//			GL11.glColor3f(1,0,0);

			r=red; g = green; b = blue;

			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
			potion_bottom = potion_base_greyScale;
			isColored = true;

		}else if((type == POTION)  && (item.getItemDamage() == 0))
			potion_bottom = potion_base_water;
		else if(type == EMPTY)
			potion_bottom = potion_base_empty;

		if(isColored)
			//		GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_DST_COLOR);
			//			GL11.glEnable(GL11.GL_BLEND);
			//			this.blockrender.renderStandardBlock(Block.dirt, (int)d, (int)d1, (int)d2);
			//			GL11.glColor3f(r, g, b);
			//this.blockrender.renderBlockAsItem(Block.blocksList[te.itemToImbue.itemID], te.itemToImbue.getItemDamage(), f3);
			potion_bottom.renderWithColor(r, g, b, 0.1f);
		//			GL11.glDisable(GL11.GL_BLEND);
		//			potion_bottom.renderWithColor(r,g,b, 0.2f);
		//			GL11.glDisable(GL11.GL_LIGHTING);
		else

			potion_bottom.render();
		//		GL11.glDisable(GL11.GL_BLEND);

	}

	@Override
	public void preSpecials(ItemStack item, ModelCubeWorld model,
			Object... data) {
		if(item.getItem() instanceof ItemPotion){
			if(ItemPotion.isSplash(item.getItemDamage()))
				this.model = potion_top_splash;
		}
		else
			this.model = potion_top_regular;


		super.preSpecials(item, model, data);
	}

	@Override
	public void renderEntity() {
		renderInWorld();
	}

	@Override
	public void renderEquipped() {
		renderTP();
	}

	@Override
	public void renderEquippedFP() {
		renderFP();
	}

	private void renderFP(){
		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(-0.3f,0.2f,0.7f);

	}




	private void renderInWorld(){
		GL11.glRotatef(0, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(-0.3f,-0.3f,0.3f);
	}

	@Override
	public void renderScale() {
		float f = 1.5f;
		GL11.glScalef(f,f,f);
	}

	private void renderTP(){

		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(45, 0, 0, 1);

		GL11.glTranslatef(0.4f,-0.4f,0.2f);
	}
}
