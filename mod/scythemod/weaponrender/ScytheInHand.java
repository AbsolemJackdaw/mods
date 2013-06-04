package scythemod.weaponrender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import scythemod.item.FireScythe;
import scythemod.item.ItemSwordTest2;

public class ScytheInHand {


	public void renderInHand(ItemRenderType type, ItemStack item,String textureFilePath,ModelBase model, Object... data){
		float scale = 0.6F;

		GL11.glPushMatrix();

		if(textureFilePath.equals("fireScythe")){
			if(((FireScythe)item.getItem()).active == true){
				Minecraft.getMinecraft().renderEngine.bindTexture("/DSM/3dScythes/scytheFire.png");
			}else{
				Minecraft.getMinecraft().renderEngine.bindTexture("/DSM/3dScythes/scytheFireOff.png");
			}
		}else{
			Minecraft.getMinecraft().renderEngine.bindTexture("/DSM/3dScythes/"+ textureFilePath +".png");
		}

		GL11.glScalef(scale,scale,scale);
		GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(180F, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(-50F, 0.0f, 0.0f, 1.0f);
		GL11.glRotatef(180F, 1.0f, 0.0f, 1.0f);


		if(data[1] != null && data[1] instanceof EntityPlayer){
			if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && 
					Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && 
					!((Minecraft.getMinecraft().currentScreen instanceof GuiInventory 
							|| Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) 
							&& RenderManager.instance.playerViewY == 180.0F))){
				GL11.glTranslatef(-0.05F, 0.5F, -0.43F);
			}else{
				GL11.glRotatef(40f, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
				GL11.glTranslatef(0f,-0.3f,-0.2f);
			}
		}else{
			GL11.glTranslatef(-0.05F, 0.5F, -0.43F);
		}

		GL11.glTranslatef(0F, -0.3F, -0.5F);

		model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();

	}
}
