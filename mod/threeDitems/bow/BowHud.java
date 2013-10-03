//package threeDitems.bow;
//import java.util.EnumSet;
//import java.util.HashMap;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.gui.GuiChat;
//import net.minecraft.client.gui.GuiGameOver;
//import net.minecraft.client.gui.GuiIngame;
//import net.minecraft.client.gui.GuiIngameMenu;
//import net.minecraft.client.gui.ScaledResolution;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemBow;
//import net.minecraft.util.ResourceLocation;
//import threeDitems.mod_3d;
//import cpw.mods.fml.common.ITickHandler;
//import cpw.mods.fml.common.TickType;
//
//public class BowHud implements ITickHandler {
//
//	public Minecraft mc;
//
//	private static final ResourceLocation loc = new ResourceLocation("subaraki:vision.png");
//	public BowHud() {
//		this.mc = Minecraft.getMinecraft();
//	}
//
//	@Override
//	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
//		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
//		FontRenderer fontrenderer = mc.fontRenderer;
//		int width = scaledresolution.getScaledWidth();
//		int height = scaledresolution.getScaledHeight();
//		mc.entityRenderer.setupOverlayRendering();
//		if (type.equals(EnumSet.of(TickType.RENDER)))
//		{
//			onRenderTick(width, height);
//		}
//	}
//
//	@Override
//	public EnumSet<TickType> ticks() {
//		return EnumSet.of(TickType.RENDER);
//	}
//
//	@Override
//	public String getLabel() {
//		return "bow hud";
//	}
//
//	@Override
//	public void tickStart(EnumSet<TickType> type, Object... tickData) {
//
//	}
//
//	HashMap map = new HashMap<Integer, Integer>();
//
//	public void onRenderTick(int width, int height){
//		if(mc.thePlayer != null && mc.isGuiEnabled()){
//			if(mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow){
//				if(mc.currentScreen == null || mc.currentScreen instanceof GuiChat || mc.currentScreen instanceof GuiIngameMenu || mc.currentScreen instanceof GuiGameOver){
//
//					if(mod_3d.inst.showHud && mc.inGameHasFocus){
//						GuiIngame gui = this.mc.ingameGUI;
//						mc.renderEngine.bindTexture(loc);
//						gui.drawTexturedModalRect(width/2, height/2, 0, 0, 100, 100);
//
//						if(mc.thePlayer.inventory.hasItem(Item.arrow.itemID)){
//
//							int arrows = 0;
//
//							for(int k =0; k< mc.thePlayer.inventory.mainInventory.length; k++){
//								if(mc.thePlayer.inventory.getStackInSlot(k) != null){
//									if(mc.thePlayer.inventory.getStackInSlot(k).getItem().equals(Item.arrow)){
//										arrows += mc.thePlayer.inventory.getStackInSlot(k).stackSize;
//									}
//								}
//							}
//							if(arrows > 0)
//								gui.drawString(mc.fontRenderer, ""+arrows, 100, 100, 0xffffff);
//						}
//					}
//				}
//			}
//		}
//	}
//}