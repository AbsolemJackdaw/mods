package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderBone extends Render3DInterface{

	/**Used for quick rendering. Basic and general code that 
	 * should work on any proper modeled model*/
	public RenderBone(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(-20,0,0,1);
		GL11.glRotatef(90,1,0,0);
			
		GL11.glTranslatef(0.7f,1.5f,-0.7f);	
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0f, 0f, 0f);	
		float f = 2f;
		GL11.glScalef(f, f, f);	
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(30,0,0,1);
		GL11.glRotatef(90,1,0,0);
			
		GL11.glTranslatef(0.7f, -0.05f, 0.1f);		
		float f = 0.5f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f+1);		
	}

}
