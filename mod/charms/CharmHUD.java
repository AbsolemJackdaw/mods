package charms;
import java.io.IOException;
import java.util.EnumSet;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
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
		if(mc.thePlayer != null && !mc.thePlayer.capabilities.isCreativeMode && mc.isGuiEnabled()){
			if(mc.thePlayer.inventory.mainInventory[ConfigClass.instance.slotID] != null && mc.thePlayer.inventory.mainInventory[ConfigClass.instance.slotID].getItem() instanceof Charm){
				if(mc.currentScreen == null || mc.currentScreen instanceof GuiChat || mc.currentScreen instanceof GuiIngameMenu || mc.currentScreen instanceof GuiGameOver){

					GuiIngame gui = this.mc.ingameGUI;
					ItemStack stack = mc.thePlayer.inventory.mainInventory[ConfigClass.instance.slotID];

					int yOffset = height-45-1;
					int xOffset = width/2+10;

					boolean leftyFlip = ConfigClass.instance.leftyFlip;
					boolean showHearts  = ConfigClass.instance.showHearts;
					boolean halfHearts = ConfigClass.instance.halfHearts;

					//					boolean noHero = mc.thePlayer.getMaxHealth() > 20;
					//					boolean noHero2 = mc.thePlayer.getMaxHealth() > 40;

					if(leftyFlip == true){
						xOffset -= 101;
						//						if(noHero == true){
						//							yOffset -= 10;
						//							if(noHero2 == true){
						//								yOffset -= 10;
						//							}
					}
					for(int i =0; i<4; i++){
						if(mc.thePlayer.inventory.armorItemInSlot(i) != null){
							yOffset = height-55;
							//								if(noHero == true){
							//									yOffset = height-55- 10;
							//									if(noHero2 == true){
							//										yOffset = height-55- 20;
							//									}
							//						}
							//					}
						}
					}

					if(mc.thePlayer.getAir() <300){
						yOffset = height-55;
					}
					float c = halfHearts ? 1 :2;
					int healAmount = ((Charm)stack.getItem()).heartsToHeal - stack.getItemDamage();
					mc.fontRenderer.drawString(""+(healAmount/c), xOffset+34, yOffset-2, mc.currentScreen == null || mc.currentScreen instanceof GuiChat ? 0xffffff : 0x555555);

					//binds the texture
					if(Core.charmBar != null)
						Core.charmBar.func_110564_a();

					GL11.glColor3f(0,1,1);
					if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)){
						GL11.glColor3f(0.2f,0.2f,0.2f);
					}
					// back ground
					gui.drawTexturedModalRect(xOffset, yOffset, 0, 0, 80, 5);
					GL11.glColor3f(1,1,1);
					if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)){
						GL11.glColor3f(0.2f,0.2f,0.2f);
					}

					// foreground which diminishes as the charm looses value
					GL11.glColor3f(1,1,1);
					if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)){
						GL11.glColor3f(0.2f,0.2f,0.2f);
					}
					gui.drawTexturedModalRect(xOffset, yOffset, 0, 0,scaleCharmBar(stack) ,5);

					//texture should not be found !
					this.mc.renderEngine.func_110577_a(new ResourceLocation("white.png"));
					GL11.glColor3f(0.7f,0f,0.7f);
					if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)){
						GL11.glColor3f(0.3f,0f,0.3f);
					}
					gui.drawTexturedModalRect(xOffset+1, yOffset, 0, 0, 80-(int)(( (float)(((Charm)stack.getItem()).cooldown)/600f)*80f)-1 ,1);
					gui.drawTexturedModalRect(xOffset+1, yOffset+4, 0, 0, 80-(int)(( (float)(((Charm)stack.getItem()).cooldown)/600f)*80f)-1 ,1);

					if(showHearts == true){
						int h = halfHearts ? 5:9;

						GL11.glColor3f(1,1,1);
						if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)){
							GL11.glColor3f(0.2f,0.2f,0.2f);
						}
						if(Core.icons != null)
							Core.icons.func_110564_a();
						gui.drawTexturedModalRect(xOffset+24, yOffset-2, 16, 0, h ,9);
						gui.drawTexturedModalRect(xOffset+24, yOffset-2, 52, 0, h ,9);

					}
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