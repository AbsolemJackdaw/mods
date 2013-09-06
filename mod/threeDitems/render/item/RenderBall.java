package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderBall extends Render3DInterface {

	public RenderBall(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 01f, 0.5f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 0f, 0f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(30,1,0,0);

		GL11.glTranslatef(0f, 1.2f, 0.3f);	

		float f = 0.7f;
		GL11.glScalef(f, f, f);	
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void preSpecials(ItemStack item, ModelBase model) {
		super.preSpecials(item, model);
//		if(item.getItem().equals(Item.slimeBall)){
//			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
//			GL11.glEnable(GL11.GL_BLEND);
//		}

	}

	@Override
	public void postSpecials(ItemStack item, ModelBase model) {
		super.postSpecials(item, model);
//		if(item.getItem().equals(Item.slimeBall))
//			GL11.glDisable(GL11.GL_BLEND);
	}
}