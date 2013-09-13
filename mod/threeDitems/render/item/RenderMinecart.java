package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.helper.MinecartHelper;
import threeDitems.render.Render3DInterface;

public class RenderMinecart extends Render3DInterface{

	
	private static final MinecartHelper helper = new MinecartHelper();
	private static final RenderBlocks renderer = new RenderBlocks();
	private static final ModelMinecart cart = new ModelMinecart();
	public RenderMinecart(ModelBase model, String texture) {
		super(model, texture);
		cart.textureWidth = 45;
		this.model = cart;
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0f, 0f, 01f);	
		
		float f = 0.5f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0f, -0.6f, 0f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(95,0,0,1);
		GL11.glRotatef(180,1,0,0);
			
		GL11.glTranslatef(0.4f, 0f, -0.3f);	
		
		float f = 0.25f;
		GL11.glScalef(f, f, f);			
	}

	@Override
	public void renderScale() {
		float f = 4f;
		GL11.glScalef(f, f, f);		
	}
	
	@Override
	public void postSpecials(ItemStack item, ModelBase model, Object... data) {
		super.postSpecials(item, model);
		helper.cartzz(item, model, renderer);
	}
}
