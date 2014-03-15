package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.helper.FrameHelper;
import threeDitems.render.Render3DInterface;

public class RenderItemFrame extends Render3DInterface{


	RenderBlocks render = new RenderBlocks();
	FrameHelper helper = new FrameHelper();
	/**Used for quick rendering. Basic and general code that
	 * should work on any proper modeled model*/
	public RenderItemFrame(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void preSpecials(ItemStack item, ModelBase model, Object...data) {
		super.preSpecials(item, model);
		helper.renderFrameItemAsBlock(render, item.getItem());
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(90,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(90,1,0,0);

		GL11.glTranslatef(0f, 0f, -1f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(95,0,0,1);
		GL11.glRotatef(90,1,0,0);

		GL11.glTranslatef(0.3f, 0.3f, -0.2f);

		float f = 0.4f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(55,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(-0.2f, -0.5f, 0.8f);
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);
	}

	@Override
	@Deprecated
	protected boolean shouldIgnoreModelRendering() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@Deprecated
	protected boolean shouldIgnoreTextureRendering() {
		// TODO Auto-generated method stub
		return true;
	}
}
