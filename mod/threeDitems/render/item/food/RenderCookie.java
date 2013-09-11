package threeDitems.render.item.food;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderCookie extends Render3DInterface {

	public RenderCookie(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(80,1,0,0);
			
		GL11.glTranslatef(0f, 0.8f, -01f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(-0.8f, 0f, -0.8f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(130,0,1,0);
		GL11.glRotatef(120,0,0,1);
		GL11.glRotatef(90,1,0,0);
			
		GL11.glTranslatef(0.2f, 0f, -0.7f);		
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);		
	}

}