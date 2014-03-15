package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderSpiderEye extends Render3DInterface{

	/**Used for quick rendering. Basic and general code that
	 * should work on any proper modeled model*/
	public RenderSpiderEye(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void preSpecials(ItemStack item,ModelBase model, Object... data) {
		super.preSpecials(item, model);

		if(item.getItem().equals(Item.fermentedSpiderEye)){
			float f = 1.5f;
			GL11.glScalef(f,f,f);
			GL11.glColor4f(0.5f, 0.2f, 0.9f, 01f);
		}
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 0f, 0f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(15,0,0,1);
		GL11.glRotatef(180,1,0,0);

		GL11.glTranslatef(0.4f, -0.2f, -0.3f);

		float f = 0.7f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(10,1,0,0);

		GL11.glTranslatef(-0.2f, -0.7f, 0.8f);
	}

	@Override
	public void renderScale() {
		float f = 2f;
		GL11.glScalef(f, f, f);
	}

}
