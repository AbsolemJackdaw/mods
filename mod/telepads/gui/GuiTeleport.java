package telepads.gui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import telepads.ServerPacketHandler;
import telepads.Telepads;
import telepads.block.TETelepad;
import telepads.util.TelepadWorldData;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class GuiTeleport extends GuiScreen{

	private GuiTextField channelField;

	public EntityPlayer thePlayer;
	public TETelepad te;

	public static final int EXIT_BUTTON = 10000;

	boolean nameGui;

	String channelName;

	public GuiTeleport(EntityPlayer player, TETelepad te){
		mc.getMinecraft().gameSettings.guiScale = 2;

		this.te = te;
		thePlayer = player;

		nameGui = true;

		channelName = te.TELEPORTCHANNEL;
	}

	@Override
	public void initGui() {

		//used to offset buttons
		int offSetX = 150;
		int offSetY = 250;

		int posX = (this.width) / 2;
		int posY = (this.height) / 2;
		this.buttonList.clear();

		System.out.println("The channel for this telepad is : " + te.TELEPORTCHANNEL);

		channelField = new GuiTextField(fontRendererObj, posX- 50 , posY- 130, 150, 20);

		if(channelField != null){
			channelField.setText(channelName);
			channelField.setMaxStringLength(50);
		}

		ArrayList<int[]>allCoords = TelepadWorldData.get(thePlayer.worldObj).getCoordsForChannel(te.TELEPORTCHANNEL);
		int c = allCoords.size();

		if(c < 1){
			return;
		}

		for(int i = 0; i < c; i++){
			String name = TelepadWorldData.get(thePlayer.worldObj).getPadName(allCoords.get(i));
			this.buttonList.add(new GuiButton(i, /*x*/posX-200 + (i/10 > 0 && i%10 >= 0 ? 120*(i/10) : 0),/*y*/posY+((i*25)) - (i/10 > 0 && i%10 >= 0 ? (250*(i/10))+100 : 100), 
					/*size*/100, 20, /**/name)); 
		}

		this.buttonList.add(new GuiButton(EXIT_BUTTON, posX-200, posY - 120, 20, 20,"X")); 

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
			fontRendererObj.drawSplitString("What Channel do you want to connect to ? : "+channelField.getText(), posX -75, posY -20, 180 ,0xff0000);

		}finally{
			if(channelName != null) channelField.drawTextBox();
		}
	}


	@Override
	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);

		if(channelField != null) {
			channelField.textboxKeyTyped(c, i);
			channelName = channelField.getText();
		}

		if(i == Keyboard.KEY_ESCAPE){
			te.resetTE();
			mc.thePlayer.closeScreen();
		}
	}

	@Override
	protected void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		if(channelField != null) channelField.mouseClicked(i, j, k);
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
				sendPacket(id, button);
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
				ArrayList<int[]>a = TelepadWorldData.get(thePlayer.worldObj).getCoordsForChannel(te.TELEPORTCHANNEL);
				int x = a.get(button.id)[0];
				int y = a.get(button.id)[1];
				int z = a.get(button.id)[2];

				System.out.println(x +" "+ y + " " + z);
				out.writeInt(x);//x
				out.writeInt(y);//y
				out.writeInt(z);//z
				out.writeInt(TelepadWorldData.get(thePlayer.worldObj).getDimension(a.get(button.id)));//dim
			}

			Telepads.Channel.sendToServer(new FMLProxyPacket(buf, Telepads.channelName));

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
