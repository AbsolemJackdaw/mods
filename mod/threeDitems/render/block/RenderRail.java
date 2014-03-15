package threeDitems.render.block;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderRail extends Render3DInterface{

	public RenderRail(ModelBase model, String s) {
		super(model,s);
	}

	@Override
	public void renderEntity() {
		GL11.glTranslatef(0, -0.25f, 0);
	}

	@Override
	public void renderEquipped() {
		GL11.glTranslatef(0.58f, 0f, 0.5f);
		GL11.glScalef(1.15F,1.15f,1.15F);
	}

	@Override
	public void renderEquippedFP() {

	}

	@Override
	public void renderScale() {
		GL11.glScalef(0.01F,0.01F,0.01F);
	}
}