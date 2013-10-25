package cubeItems.renderers.tools;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;
import cubeItems.ModelCubeWorld;
import cubeItems.RenderCubeInterface;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderCubeSword extends RenderCubeInterface{

	RenderItem rend = new RenderItem();

	public RenderCubeSword (ModelCubeWorld model){
		super(model);
	}

	@Override
	public void renderEquippedFP() {
	
		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);
		
		GL11.glTranslatef(0.5f, -0.3f, 0f);
	
	}

	@Override
	public void renderEquipped() {
		
		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(-75, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);
		
		GL11.glTranslatef(-0.1f, -0.8f, 0.75f);	
	}
	
	@Override
	public void renderEntity() {
		
		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(-75, 1, 0, 0);
		GL11.glRotatef(0, 0, 0, 1);
		
		GL11.glTranslatef(-0.1f, -0.8f, 0.8f);
			
	}

	@Override
	public void renderScale() {
		
		GL11.glScalef(2, 2, 2);

	}

}
