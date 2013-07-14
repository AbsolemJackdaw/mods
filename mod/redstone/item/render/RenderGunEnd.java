package redstone.item.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import redstone.models.ModelGunEnd;

public class RenderGunEnd implements IItemRenderer {

	ModelGunEnd gun ;
	
	public RenderGunEnd()
	{
		gun = new ModelGunEnd();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case  EQUIPPED: return true;
//		case  EQUIPPED_FIRST_PERSON: return true;
		case ENTITY: return true;
		default:
                    if("EQUIPPED_FIRST_PERSON".equals(type.name())){
                            return true;
                    }
                    return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type){
		case  EQUIPPED:
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/subaraki/gunE.png"));
			GL11.glRotatef(101, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(-15, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(192, 1.0f, 0.0f, 0.0f);

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glScalef(1.9f, 1.9f, 1.9f);
					GL11.glTranslatef(-0.15F, 0.32F, -0.1F);			
				}
				else
				{
					GL11.glScalef(2f, 2f, 2f);
					GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(-0F, 1.0f, 0.0f, 1.0f);
					GL11.glRotatef(-30f, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(120F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(-0.2f ,0f,-0.5F);
				}
			}
			else
			{
				GL11.glScalef(1.5f, 1.5f, 1.5f);
				GL11.glTranslatef(0.07F, -0.045F, -0.2F);
			}
			gun.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
			break;
//		case  EQUIPPED_FIRST_PERSON:
//			GL11.glPushMatrix();
//			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/subaraki/gunE.png");
//			GL11.glRotatef(101, 0.0f, 0.0f, 1.0f);
//			GL11.glRotatef(-15, 0.0f, 1.0f, 0.0f);
//			GL11.glRotatef(192, 1.0f, 0.0f, 0.0f);
//
//			if(data[1] != null && data[1] instanceof EntityPlayer)
//			{
//				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
//				{
//					GL11.glScalef(1.9f, 1.9f, 1.9f);
//					GL11.glTranslatef(-0.15F, 0.32F, -0.1F);			
//				}
//				else
//				{
//					GL11.glScalef(2f, 2f, 2f);
//					GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
//					GL11.glRotatef(-0F, 1.0f, 0.0f, 1.0f);
//					GL11.glRotatef(-30f, 0.0f, 1.0f, 0.0f);
//					GL11.glRotatef(120F, 0.0f, 0.0f, 1.0f);
//					GL11.glTranslatef(-0.2f ,0f,-0.5F);
//				}
//			}
//			else
//			{
//				GL11.glScalef(1.5f, 1.5f, 1.5f);
//				GL11.glTranslatef(0.07F, -0.045F, -0.2F);
//			}
//			gun.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
//
//			GL11.glPopMatrix();
//			break;
		case ENTITY:
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/subaraki/gunE.png"));
			GL11.glScalef(3f, 3f, 3f);
			GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(0, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-180, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0f,-0.2f,0F);
			gun.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			break;
		default:
                    if("EQUIPPED_FIRST_PERSON".equals(type.name())){
                        GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/subaraki/gunE.png"));
			GL11.glRotatef(101, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(-15, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(192, 1.0f, 0.0f, 0.0f);

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glScalef(1.9f, 1.9f, 1.9f);
					GL11.glTranslatef(-0.15F, 0.32F, -0.1F);			
				}
				else
				{
					GL11.glScalef(2f, 2f, 2f);
					GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(-0F, 1.0f, 0.0f, 1.0f);
					GL11.glRotatef(-30f, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(120F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(-0.2f ,0f,-0.5F);
				}
			}
			else
			{
				GL11.glScalef(1.5f, 1.5f, 1.5f);
				GL11.glTranslatef(0.07F, -0.045F, -0.2F);
			}
			gun.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
                    }
                    break;
		}

	}
}