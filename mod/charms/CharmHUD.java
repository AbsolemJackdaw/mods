package charms;
import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CharmHUD implements ITickHandler {

	public Minecraft mc;

	public CharmHUD() {
		this.mc = Minecraft.getMinecraft();
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		FontRenderer fontrenderer = mc.fontRenderer;
		int width = scaledresolution.getScaledWidth();
		int height = scaledresolution.getScaledHeight();
		mc.entityRenderer.setupOverlayRendering();
		if (type.equals(EnumSet.of(TickType.RENDER)))
		{
			onRenderTick(width, height);
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "charm_HUD";
	}
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

	}

	public void onRenderTick(int width, int height){
		if(mc.thePlayer != null){
			if(mc.thePlayer.inventory.mainInventory[8] != null && mc.thePlayer.inventory.mainInventory[8].getItem() instanceof Charm){
				if(mc.currentScreen == null || mc.currentScreen instanceof GuiChat || mc.currentScreen instanceof GuiIngameMenu || mc.currentScreen instanceof GuiGameOver){

					GuiIngame gui = this.mc.ingameGUI;
					ItemStack stack = mc.thePlayer.inventory.mainInventory[8];

					mc.fontRenderer.drawString(""+(((Charm)stack.getItem()).heartsToHeal -stack.getItemDamage()), width/2+44, height-57, mc.currentScreen == null || mc.currentScreen instanceof GuiChat ? 0xffffff : 0x555555);

					this.mc.renderEngine.bindTexture("/charms/hud.png");

					GL11.glColor3f(0,1,1);
					if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)){
						GL11.glColor3f(0.2f,0.2f,0.2f);
					}
					// back ground
					gui.drawTexturedModalRect(width/2+10, height-55, 0, 0, 80, 5);
					GL11.glColor3f(1,1,1);
					if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)){
						GL11.glColor3f(0.2f,0.2f,0.2f);
					}

					// foreground which diminishes as the charm looses value
					GL11.glColor3f(1,1,1);
					if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)){
						GL11.glColor3f(0.2f,0.2f,0.2f);
					}
					gui.drawTexturedModalRect(width/2+10, height-55, 0, 0,scaleCharmBar(stack) ,5);

				}
			}
		}
	}

	public int scaleCharmBar(ItemStack stack){

		return (int)(((((Charm)stack.getItem()).heartsToHeal - stack.getItemDamage()))/ (float) ((((Charm)stack.getItem()).heartsToHeal)) *80f);

		//sample > (120 /120)*182 = 1*182 = 182. when the charm has its max value, the bar will be full
		//sample > (60  /120)*182 = 0.5*182 = 91. when the charm is halfway trough, the bar will be half.
		//the texture is 182 long.
	}
}