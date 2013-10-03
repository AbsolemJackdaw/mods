package threeDitems.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import threeDitems.models.bucket;
import threeDitems.render.Render3DInterface;

public class RenderBucket extends Render3DInterface {

	public RenderBucket(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(190,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0f, -0.3f, 0.5f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0f, 0f, 0f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(15,0,0,1);
		GL11.glRotatef(180,1,0,0);
			
		GL11.glTranslatef(0.4f, 0.2f, -0.4f);	
		
		float f = 0.7f;
		GL11.glScalef(f, f, f);			
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void preSpecials(ItemStack item, ModelBase model, Object... data) {
		super.preSpecials(item, model, data);
		
//		GL11.glDisable(GL11.GL_LIGHTING);
		if(item.getItem().equals(Item.bucketWater)){
			((bucket)model).Shape11.isHidden = true;
		}
		
		if(item.getItem().equals(Item.bucketLava)){
			((bucket)model).Shape11.isHidden = true;
		}
	}
	
	@Override
	public void postSpecials(ItemStack item, ModelBase model, Object... data) {
		super.postSpecials(item, model, data);
		
		if(item.getItem().equals(Item.bucketWater)){
			((bucket)model).Shape11.isHidden = false;
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("subaraki:3d/items/bucketWater.png"));
			
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
			GL11.glEnable(GL11.GL_BLEND);
			((bucket)model).render(null, 0, 0, 0, 0, 0, 0.0625f);
			GL11.glDisable(GL11.GL_BLEND);
		}
		
		if(item.getItem().equals(Item.bucketLava)){
			((bucket)model).Shape11.isHidden = false;
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("subaraki:3d/items/bucketLava.png"));
			
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
			GL11.glEnable(GL11.GL_BLEND);
			((bucket)model).render(null, 0, 0, 0, 0, 0, 0.0625f);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}
}