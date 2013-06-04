package scythemod.weaponrender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import scythemod.item.FireScythe;

public class ScytheInWorld {


	public void renderInWorld(ItemStack item,String textureFilePath,ModelBase model, Object... data){

		GL11.glPushMatrix();

		if(textureFilePath.equals("fireScythe")){
			if(((FireScythe)item.getItem()).active == true){
				Minecraft.getMinecraft().renderEngine.bindTexture("/DSM/3dScythes/scytheFire.png");
			}else{
				Minecraft.getMinecraft().renderEngine.bindTexture("/DSM/3dScythes/scytheFireOff.png");
			}
		}else{
			Minecraft.getMinecraft().renderEngine.bindTexture("/DSM/3dScythes/" + textureFilePath + ".png");
		}
		GL11.glScalef(1f, 1f,1f);
		GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
		GL11.glRotatef(0, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(-180, 1.0f, 0.0f, 0.0f);
		GL11.glTranslatef(0f,0f,0F);
		model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}
