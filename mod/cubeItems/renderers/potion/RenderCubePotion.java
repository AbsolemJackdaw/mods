package cubeItems.renderers.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;

import threeDitems.models.bottle;

import cubeItems.ModelCubeWorld;
import cubeItems.RenderCubeInterface;

public class RenderCubePotion extends RenderCubeInterface{

	ModelCubeWorld potion_base_greyScale = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_bottom_greyscale.cub"));
	ModelCubeWorld potion_base_empty = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_bottom_empty.cub"));
	ModelCubeWorld potion_base_water = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_bottom_water.cub"));

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
	public void renderEquippedFP() {
		renderFP();
	}

	@Override
	public void renderEquipped() {
		renderTP();
	}

	@Override
	public void renderEntity() {
		renderInWorld();
	}

	@Override
	public void renderScale() {
		float f = 1.5f;
		GL11.glScalef(f,f,f);
	}

	@Override
	public void postSpecials(ItemStack item, ModelCubeWorld model, Object... data) {
		super.postSpecials(item, model, data);

//		GL11.glEnable(GL11.GL_BLEND);
//				GL11.glDisable(GL11.GL_LIGHTING);
		//		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);

		if(type == POTION && item.getItemDamage() > 0)
		{
			int color = ((ItemPotion)item.getItem()).getColorFromDamage(item.getItemDamage());
			float red = (float)(color >> 16 & 255) / 255.0F;
			float green = (float)(color >> 8 & 255) / 255.0F;
			float blue = (float)(color & 255) / 255.0F;	
			
//			GL11.glColor4f(red, green,blue, 1.0F);
			GL11.glColor3f(1,0,0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
			potion_bottom = potion_base_greyScale;

		}else if(type == POTION  && item.getItemDamage() == 0){
			potion_bottom = potion_base_water;

		}
		//		else if(type == EXPBOTTLE){
		//			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
		//			GL11.glEnable(GL11.GL_BLEND);
		//			GL11.glDisable(GL11.GL_BLEND);
		//			GL11.glColor4f(0.7f, 1.0f, 0.0f, 1.0F);
		//			potion_bottom = potion_base_greyScale;

		//		}
		else if(type == EMPTY){
			potion_bottom = potion_base_empty;
		}

		potion_bottom.render();			
//		GL11.glDisable(GL11.GL_BLEND);

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

	private void renderTP(){

		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(45, 0, 0, 1);

		GL11.glTranslatef(0.4f,-0.4f,0.2f);	
	}
}
