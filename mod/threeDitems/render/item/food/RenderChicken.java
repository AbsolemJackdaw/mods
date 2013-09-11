package threeDitems.render.item.food;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderChicken extends Render3DInterface {

	
	public RenderChicken(ModelBase model, String texture) {
		super(model, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(-90+45,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(-0.5f, -0.3f, 0f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0f, 0.2f, 0f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(30,1,0,0);
			
		GL11.glTranslatef(0f, 0.5f, 0.9f);		
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);		
	}
}
