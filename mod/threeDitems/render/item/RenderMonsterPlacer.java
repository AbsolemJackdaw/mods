package threeDitems.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderMonsterPlacer extends Render3DInterface{

	private final ResourceLocation spots;


	public RenderMonsterPlacer(ModelBase model, String texture) {
		super(model, texture);
		spots = new ResourceLocation("subaraki:3d/items/eggSpawnSpots.png");
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(190,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(-0.2f, -0.5f, 0.2f);		
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
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(30,1,0,0);
			
		GL11.glTranslatef(0f, 0.6f, 0.8f);		
	}

	@Override
	public void renderScale() {
		float f = 1.5f;
		GL11.glScalef(f, f, f);		
	}
	
	@Override
	public void preSpecials(ItemStack item, ModelBase model) {
		super.preSpecials(item, model);
		
		int color = ((ItemMonsterPlacer)item.getItem()).getColorFromItemStack(item,1);
		float red = (float)(color >> 16 & 255) / 255.0F;
		float green = (float)(color >> 8 & 255) / 255.0F;
		float blue = (float)(color & 255) / 255.0F;					
		GL11.glColor3f(red, green, blue);
		
	}

	@Override
	public void postSpecials(ItemStack item, ModelBase model)
	{
		super.postSpecials(item, model);
		Minecraft.getMinecraft().renderEngine.func_110577_a(spots);
		int color = ((ItemMonsterPlacer)item.getItem()).getColorFromItemStack(item,0);
		float red = (float)(color >> 16 & 255) / 255.0F;
		float green = (float)(color >> 8 & 255) / 255.0F;
		float blue = (float)(color & 255) / 255.0F;					
		GL11.glColor4f(red, green,blue,0.5F);
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}
}
