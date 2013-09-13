package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderBlazeRod extends Render3DInterface{

	public RenderBlazeRod(ModelBase model, String texture) {
		super(model, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45+90,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(100,1,0,0);
			
		GL11.glTranslatef(-1f, 0f, -1f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(-90,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0f, 0f, 0f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(15,0,0,1);
		GL11.glRotatef(90,1,0,0);
			
		GL11.glTranslatef(0.4f, 0f, -0.7f);	
		
		float f = 0.5f;
		GL11.glScalef(f, f, f);			
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void postSpecials(ItemStack item, ModelBase model, Object ... data) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0f,0.5f,0f, 0.5f);
		GL11.glScalef(1.2f,1.2f,1.2f);
		GL11.glTranslatef(-0.005f, 0.005f, 0.01f);
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}
}
