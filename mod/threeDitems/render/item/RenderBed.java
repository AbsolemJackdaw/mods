package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderBed extends Render3DInterface{

	/**Used for quick rendering. Basic and general code that 
	 * should work on any proper modeled model*/
	public RenderBed(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(-45-90,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(-0.2f, -0.5f, -0.8f);		
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
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(95,0,0,1);
		GL11.glRotatef(180,1,0,0);
			
		GL11.glTranslatef(0.4f, 0.5f, -0.3f);	
		
		float f = 0.7f;
		GL11.glScalef(f, f, f);			
			
	}

	@Override
	public void renderScale() {
		float f = 1f;
		GL11.glScalef(f, f, f);		
	}

}
