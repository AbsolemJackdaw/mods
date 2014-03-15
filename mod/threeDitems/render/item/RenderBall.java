package threeDitems.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderBall extends Render3DInterface {


	private static final ResourceLocation magma = new ResourceLocation("subaraki:3d/items/magmacream.png");
	private static final ResourceLocation slime = new ResourceLocation("subaraki:3d/items/magmacream2.png");

	public RenderBall(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void postSpecials(ItemStack item, ModelBase model, Object... data) {
		super.postSpecials(item, model);
		if(item.getItem().equals(Item.magmaCream))
			GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void preSpecials(ItemStack item, ModelBase model, Object...data) {
		super.preSpecials(item, model);
		if(item.getItem().equals(Item.clay)){
			float f = 0.5f;
			GL11.glColor3f(f,f,f);
		}

		if(item.getItem().equals(Item.magmaCream))
		{
			//			float f = 20f;
			//			GL11.glScalef(f,f,f);

			GL11.glPushMatrix();
			float f2 = 0.7f;
			GL11.glScalef(f2,f2,f2);
			GL11.glTranslatef(0.03f,-0.03f, 0.03f);

			Minecraft.getMinecraft().renderEngine.bindTexture(magma);
			model.render(null, 0, 0, 0, 0, 0, 0.0625f);
			GL11.glPopMatrix();

			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
			GL11.glEnable(GL11.GL_BLEND);

			Minecraft.getMinecraft().renderEngine.bindTexture(slime);

		}
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 0.5f, 0f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(15,0,0,1);
		GL11.glRotatef(180,1,0,0);

		GL11.glTranslatef(0.4f, 0f, -0.3f);

		float f = 0.4f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 01f, 0.5f);
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);
	}

}