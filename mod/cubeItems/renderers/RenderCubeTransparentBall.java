package cubeItems.renderers;

import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cubeItems.ModelCubeWorld;
import cubeItems.RenderCubeInterface;

public class RenderCubeTransparentBall extends RenderCubeInterface{

	ModelCubeWorld centerEye = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/ball_ender_eye.cub"));

	/**RenderFood : platform of 10x10*/
	public RenderCubeTransparentBall(ModelCubeWorld model) {
		super(model);
	}


	@Override
	public void preSpecials(ItemStack item, ModelCubeWorld model,
			Object... data) {

		if(item.getItem() instanceof ItemEnderEye)
			centerEye.render();

		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_DST_COLOR);
		GL11.glEnable(GL11.GL_BLEND);
		//			this.blockrender.renderStandardBlock(Block.dirt, (int)d, (int)d1, (int)d2);
		//			GL11.glColor3f(r, g, b);
		//this.blockrender.renderBlockAsItem(Block.blocksList[te.itemToImbue.itemID], te.itemToImbue.getItemDamage(), f3);
		model.render();
		GL11.glDisable(GL11.GL_BLEND);
		//			potion_bottom.renderWithColor(r,g,b, 0.2f);
		GL11.glDisable(GL11.GL_LIGHTING);
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(-0.3f,-0.3f,0.3f);

	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(45, 0, 0, 1);

		GL11.glTranslatef(0.4f,-0.4f,0.2f);
	}

	@Override
	public void renderEquippedFP() {

		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);

		GL11.glTranslatef(-0.3f,0.2f,0.7f);

	}
	@Override
	public void renderScale() {
		float f = 1.5f;
		GL11.glScalef(f,f,f);
	}

	@Override
	protected boolean shouldIgnoreModelRendering() {
		return true;
	}
}
