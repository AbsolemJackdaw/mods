package threeDitems.render.item.tools;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.mod_3d;
import threeDitems.render.Render3DInterface;

public class RenderSpade extends Render3DInterface {

	public RenderSpade(ModelBase model, String texture) {
		super(model, texture);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void renderEquippedFP() {
		
		GL11.glRotatef(45+90,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(-50,1,0,0);
		
		GL11.glTranslatef(-1.3f, 0f, -0.5f);		
	}

	@Override
	public void renderEntity() {
		float f = 1.5f;
		GL11.glRotatef(90, 0, 0, 1);
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(-90,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(-50,1,0,0);
			
		GL11.glTranslatef(-0.1f, 0.7f, -0.2f);	
		
		float f = 0.4f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void renderScale() {
		float f = 4f;
		GL11.glScalef(f, f, f);		
	}

}
