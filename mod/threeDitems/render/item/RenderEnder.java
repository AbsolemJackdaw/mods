package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;
import threeDitems_old.models.enderball;

public class RenderEnder extends Render3DInterface{

	public RenderEnder(ModelBase model, String texture) {
		super(model, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 01f, 0.5f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 0.4f, 0f);	
		float f = 1.5f;
		GL11.glScalef(f, f, f);	
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(30,1,0,0);

		GL11.glTranslatef(0f, 1.2f, 0.3f);	
		
		float f = 1.2f;
		GL11.glScalef(f, f, f);	
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);		
	}
	
	@Override
	public void preSpecials(ItemStack item, ModelBase model) {
		// TODO Auto-generated method stub
		super.preSpecials(item, model);
	}
	
	@Override
	public void postSpecials(ItemStack item, ModelBase model) {
		super.postSpecials(item, model);
		
		if(item.getItem() instanceof ItemEnderEye ){
			GL11.glScalef(0.5f,0.5f,0.5f);
			((enderball)model).renderEye(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0f, 0.5f, 0f, 0.2f);
			((enderball)model).renderBall(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		
		if(item.getItem() instanceof ItemEnderPearl ){
			GL11.glScalef(0.5f,0.5f,0.5f);
			GL11.glColor4f(0f, 0f, 0f, 1.0f);
			((enderball)model).renderBall(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		
		if(item.getItem() instanceof ItemEnderPearl|| item.getItem() instanceof ItemEnderEye ){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(0.3f, 0.3f, 0.3f, 0.3f);
			GL11.glTranslatef(-0.02f, 0.05f, -0.001f);
			GL11.glScalef(1.3f,1.3f,1.3f);
			((enderball)model).renderBall(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
	}
}
