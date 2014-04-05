package telepads.gui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.awt.TextField;
import java.io.IOException;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import telepads.ServerPacketHandler;
import telepads.Telepads;
import telepads.block.TETelepad;
import telepads.util.ExportTelepad;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class GuiTeleport extends GuiScreen{

	private GuiTextField channelField;

	public EntityPlayer thePlayer;
	public TETelepad te;

	public static final int EXIT_BUTTON = 10000;

	private String channelName;

	int[] ray = new int[3];

	public GuiTeleport(EntityPlayer player, TETelepad te){
		mc.getMinecraft().gameSettings.guiScale = 2;

		this.te = te;
		thePlayer = player;

		ray[0] = te.xCoord;
		ray[1] = te.yCoord;
		ray[2] = te.zCoord;

		channelName = te.channelName;
	}

	@Override
	public void initGui() {

		//used to offset buttons
		int offSetX = 150;
		int offSetY = 250;

		int posX = (this.width) / 2;
		int posY = (this.height) / 2;
		this.buttonList.clear();

		channelField = new GuiTextField(fontRendererObj, posX- 50 , posY- 130, 150, 20);

		if(channelField != null){
			channelField.setText(channelName);
			channelField.setMaxStringLength(50);
		}

		try {
			List<int[]> allCoords = ExportTelepad.read(channelName);
			int c = allCoords.size();

			if(c == 0){
				return;
			}

			for(int i = 0; i < c; i++){

				String name = ExportTelepad.readName(allCoords.get(i));

				this.buttonList.add(new GuiTeleportButton(i, /*x*/posX-200 + (i/10 > 0 && i%10 >= 0 ? 120*(i/10) : 0),/*y*/posY+((i*25)) - (i/10 > 0 && i%10 >= 0 ? (250*(i/10))+100 : 100), 
						/*size*/100, 20, /**/name, allCoords.get(i)[0], allCoords.get(i)[1], allCoords.get(i)[2], allCoords.get(i)[3])); 
			}

			this.buttonList.add(new GuiButton(EXIT_BUTTON, posX-200, posY - 130, 20, 20,"X")); 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		if(i == Keyboard.KEY_RETURN){
			initGui();
		}

		if(channelField != null) {
			channelField.textboxKeyTyped(c, i);

			channelName = channelField.getText();
		}
	}

	protected void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		if(channelField != null) channelField.mouseClicked(i, j, k);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {

		this.drawBackground(par1);

		super.drawScreen(par1, par2, par3);

		int posX = (this.width ) / 2;
		int posY = (this.height ) / 2;
		try{
			fontRendererObj.drawSplitString("Press Enter to confirm", posX+1 -75, posY-1, 180 ,0x000000);
			fontRendererObj.drawSplitString("Press Enter to confirm", posX -75, posY, 180 ,0xffffff);

			fontRendererObj.drawSplitString("What Channel do you want to connect to ? : "+channelField.getText(), posX+1 -75, posY-1-20, 180 ,0x000000);
			fontRendererObj.drawSplitString("What Channel do you want to connect to ? : "+channelField.getText(), posX   -75, posY  -20, 180 ,0xff0000);

		}finally{
			if(channelName != null) channelField.drawTextBox();
		}
	}

	private static final ResourceLocation enderPortalEndSkyTextures = new ResourceLocation("textures/environment/end_sky.png");
	private static final ResourceLocation endPortalTextures = new ResourceLocation("textures/entity/end_portal.png");

	float c = 0;
	float sd = 0;

	@Override
	public void drawBackground(int par1) {
		c += 1f;
		sd +=0.01f;
		float k = c+2;

		GL11.glPushMatrix();
		GL11.glColor4f(0.2f, 0.6f, 1f, sd < 0.7f ? sd : 0.7f);
		mc.getMinecraft().renderEngine.bindTexture(enderPortalEndSkyTextures);
		drawTexturedModalRect(0, 0, -(int)k*2, -(int)c*2 , 3000, 3000);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glColor4f(0.2f, 0.6f, 1f, sd < 0.75f ? sd : 0.75f);
		mc.getMinecraft().renderEngine.bindTexture(endPortalTextures);
		drawTexturedModalRect(0, 0, (int)k*2, (int)c*2 , 3000, 3000);
		GL11.glPopMatrix();

	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton button) {

		if(thePlayer != null){
			int id = button.id;
			if(id == EXIT_BUTTON ){
				sendPacket(EXIT_BUTTON, button);
				this.mc.thePlayer.closeScreen(); //closes the screen

			}else{
				sendPacket(id, (GuiTeleportButton)button);
				this.mc.thePlayer.closeScreen(); //closes the screen
			}
		}

		te.resetTE();
	}


	public void sendPacket(int id, GuiButton button){

		ByteBuf buf = Unpooled.buffer();
		ByteBufOutputStream out = new ByteBufOutputStream(buf);

		try {
			out.writeInt(ServerPacketHandler.IDENTIFIER_TELEPORTER);

			out.writeInt(te.xCoord);
			out.writeInt(te.yCoord);
			out.writeInt(te.zCoord);

			out.writeInt(id);

			if(id < EXIT_BUTTON){
				if(button instanceof GuiTeleportButton){
					GuiTeleportButton b = (GuiTeleportButton)button;
					out.writeInt(b.xTeleport);//x
					out.writeInt(b.yTeleport);//y
					out.writeInt(b.zTeleport);//z
					out.writeInt(b.dimTeleport);//dim
				}
			}

			Telepads.Channel.sendToServer(new FMLProxyPacket(buf, Telepads.channelName));

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
