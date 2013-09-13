package threeDitems.render.item.tools;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderPickaxe extends Render3DInterface {

	public RenderPickaxe(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(50,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
		
		GL11.glTranslatef(0f, -1.3f, 0.5f);
	}

	@Override
	public void renderEntity() {
		float f = 3f;
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(-135,0,0,1);
		GL11.glRotatef(0,1,0,0);
		GL11.glTranslatef(-0.7f, -0.4f, -0.05f);
		
		float f = 0.7f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderScale() {
		float f = 1f;
		GL11.glScalef(f, f, f);
	}

}