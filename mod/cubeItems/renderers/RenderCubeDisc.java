package cubeItems.renderers;

import org.lwjgl.opengl.GL11;

import cubeItems.ModelCubeWorld;
import cubeItems.RenderCubeInterface;
import cubeItems.mod_cubeItems;

public class RenderCubeDisc extends RenderCubeInterface{

	public RenderCubeDisc(ModelCubeWorld model) {
		super(model);
	}


	@Override
	public void renderEquippedFP() {
	
		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);
		
		GL11.glTranslatef(0f,0.5f,0f);
	
	}

	@Override
	public void renderEquipped() {
//		mod_cubeItems.proxy.render();
		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);
		
		GL11.glTranslatef(0.5f,-0.1f,0f);	
	}
	
	@Override
	public void renderEntity() {
		GL11.glRotatef(0, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);
		
		GL11.glTranslatef(-0.3f,-0.3f,0.3f);
			
	}

	@Override
	public void renderScale() {
		
		float f = 1.5f;
		GL11.glScalef(f,f,f);

	}
}
