package threeDitems.render.item.food;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderCarrot extends Render3DInterface{

	/**Used for quick rendering. Basic and general code that 
	 * should work on any proper modeled model*/
	public RenderCarrot(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(55,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(-0.2f, -0.5f, 0.8f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(180,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(90,1,0,0);
			
		GL11.glTranslatef(0f, 0f, 0.3f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(60,1,0,0);
			
		GL11.glTranslatef(0f, 0.5f, 0.9f);		
	}

	@Override
	public void renderScale() {
		float f = 2.2f;
		GL11.glScalef(f, f+1, f);		
	}

}
