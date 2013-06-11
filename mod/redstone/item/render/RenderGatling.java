package redstone.item.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;
import static net.minecraftforge.client.IItemRenderer.ItemRenderType.ENTITY;
import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;

import redstone.models.ModelGatling;
import redstone.models.ModelGunBlue;

public class RenderGatling implements IItemRenderer {

	private ModelGatling gun;

	public RenderGatling() {
		gun = new ModelGatling();
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
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch(type){
		case  EQUIPPED:
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture("/subaraki/gatling.png");
			if(item.getTagCompound() != null && item.getTagCompound().hasKey("EnergyCharge")){
				float red = item.getTagCompound().getFloat("EnergyCharge")/192;
				GL11.glColor4f(1.0f, 1.0f-red, 1.0f-red, 0.5f);
			}
			GL11.glRotatef(15, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(12, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(195, 1.0f, 0.0f, 0.0f);

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glScalef(2f, 2f, 2f);
					GL11.glTranslatef(0.09F, -0.05F, -0.0F);
				}
				else
				{
					GL11.glScalef(2f, 2f, 2f);
					GL11.glRotatef(80F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(0F, 1.0f, 0.0f, 1.0f);
					GL11.glRotatef(-110F, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(95F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(-0.5f ,-0f,-0.5F);
				}
			}
			else
			{
				GL11.glScalef(2f, 2f, 2f);
				GL11.glTranslatef(0.09F, -0.05F, -0.0F);
			}
			gun.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
			break;
//		case  EQUIPPED_FIRST_PERSON:
//			GL11.glPushMatrix();
//			Minecraft.getMinecraft().renderEngine.bindTexture("/subaraki/gatling.png");
//			if(item.getTagCompound() != null && item.getTagCompound().hasKey("EnergyCharge")){
//				float red = item.getTagCompound().getFloat("EnergyCharge")/192;
//				GL11.glColor4f(1.0f, 1.0f-red, 1.0f-red, 0.5f);
//			}
//			GL11.glRotatef(15, 0.0f, 0.0f, 1.0f);
//			GL11.glRotatef(12, 0.0f, 1.0f, 0.0f);
//			GL11.glRotatef(195, 1.0f, 0.0f, 0.0f);
//
//			if(data[1] != null && data[1] instanceof EntityPlayer)
//			{
//				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
//				{
//					GL11.glScalef(2f, 2f, 2f);
//					GL11.glTranslatef(0.09F, -0.05F, -0.0F);
//				}
//				else
//				{
//					GL11.glScalef(2f, 2f, 2f);
//					GL11.glRotatef(80F, 1.0f, 0.0f, 0.0f);
//					GL11.glRotatef(0F, 1.0f, 0.0f, 1.0f);
//					GL11.glRotatef(-110F, 0.0f, 1.0f, 0.0f);
//					GL11.glRotatef(95F, 0.0f, 0.0f, 1.0f);
//					GL11.glTranslatef(-0.5f ,-0f,-0.5F);
//				}
//			}
//			else
//			{
//				GL11.glScalef(2f, 2f, 2f);
//				GL11.glTranslatef(0.09F, -0.05F, -0.0F);
//			}
//			gun.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
//
//			GL11.glPopMatrix();
//			break;
		case ENTITY:
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture("/subaraki/gatling.png");
			GL11.glScalef(2f, 2f, 2f);
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
			Minecraft.getMinecraft().renderEngine.bindTexture("/subaraki/gatling.png");
			if(item.getTagCompound() != null && item.getTagCompound().hasKey("EnergyCharge")){
				float red = item.getTagCompound().getFloat("EnergyCharge")/192;
				GL11.glColor4f(1.0f, 1.0f-red, 1.0f-red, 0.5f);
			}
			GL11.glRotatef(15, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(12, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(195, 1.0f, 0.0f, 0.0f);

			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glScalef(2f, 2f, 2f);
					GL11.glTranslatef(0.09F, -0.05F, -0.0F);
				}
				else
				{
					GL11.glScalef(2f, 2f, 2f);
					GL11.glRotatef(80F, 1.0f, 0.0f, 0.0f);
					GL11.glRotatef(0F, 1.0f, 0.0f, 1.0f);
					GL11.glRotatef(-110F, 0.0f, 1.0f, 0.0f);
					GL11.glRotatef(95F, 0.0f, 0.0f, 1.0f);
					GL11.glTranslatef(-0.5f ,-0f,-0.5F);
				}
			}
			else
			{
				GL11.glScalef(2f, 2f, 2f);
				GL11.glTranslatef(0.09F, -0.05F, -0.0F);
			}
			gun.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
			break;
                    }
                    break;
		}		
	}
}
