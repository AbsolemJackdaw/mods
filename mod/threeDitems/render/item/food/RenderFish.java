package threeDitems.render.item.food;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderFish extends Render3DInterface{

	
	public RenderFish(ModelBase model, String texture) {
		super(model, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(90,1,0,0);
			
		GL11.glTranslatef(0f, 1f, -1.7f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(90,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(-0.5f, 0f, -1f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(15,0,0,1);
		GL11.glRotatef(110,1,0,0);
			
		GL11.glTranslatef(0.25f, -0.2f, -0.9f);	
		
		float f = 0.45f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void renderScale() {
		float f = 1.7f;
		GL11.glScalef(f, f, f);		
	}

}
