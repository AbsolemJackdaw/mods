package telepads.gui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import org.lwjgl.input.Keyboard;

import telepads.ServerPacketHandler;
import telepads.Telepads;
import telepads.block.TETelepad;
import telepads.util.TelePadGuiHandler;
import telepads.util.TelepadWorldData;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class GuiNameTelepad extends GuiScreen{

	private GuiTextField padNameField;
	private GuiTextField channelNameField;

	public EntityPlayer thePlayer;
	public TETelepad te;

	public GuiNameTelepad(EntityPlayer player, TETelepad te){
		thePlayer = player;
		this.te = te;
	}

	@Override
	public void initGui() {

		int posX = (this.width ) / 2;
		int posY = (this.height ) / 2;
		this.buttonList.clear();

		padNameField = new GuiTextField(fontRendererObj, posX-(150/2)    -100 , posY-50, 150, 20);
		channelNameField = new GuiTextField(fontRendererObj, posX-(150/2)+100 , posY-50, 150, 20);

		String padName = te.telepadname.equals("TelePad") ? te.getWorldObj().getBiomeGenForCoords(te.xCoord, te.zCoord).biomeName : te.telepadname;
		String channel = te.TELEPORTCHANNEL;

		if(padNameField != null){
			padNameField.setText(padName);
			padNameField.setMaxStringLength(50);
		}

		if(channelNameField!= null){
			channelNameField.setText(channel);
			channelNameField.setMaxStringLength(50);
		}
	}


	@Override
	public void drawScreen(int par1, int par2, float par3) {

		int posX = (this.width ) / 2;
		int posY = (this.height ) / 2;
		try{
			fontRendererObj.drawSplitString("Press Enter to confirm", posX+1 -75, posY-1, 180 ,0x000000);
			fontRendererObj.drawSplitString("Press Enter to confirm", posX -75, posY, 180 ,0xffffff);

			fontRendererObj.drawSplitString("Name Your TelePad : "+padNameField.getText(), posX+1 -75 -100, posY-1-20, 180 ,0x000000);
			fontRendererObj.drawSplitString("Name Your TelePad : "+padNameField.getText(), posX   -75 -100, posY  -20, 180 ,0xff0000);

			fontRendererObj.drawSplitString("Name Your Channel : "+channelNameField.getText(), posX+1 -75 +100, posY-1-20, 180 ,0x000000);
			fontRendererObj.drawSplitString("Name Your Channel : "+channelNameField.getText(), posX   -75 +100, posY  -20, 180 ,0xff0000);
		}finally{
			if(padNameField != null) padNameField.drawTextBox();
			if(channelNameField != null) channelNameField.drawTextBox();
		}

	}

	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		if(i == Keyboard.KEY_RETURN){
			sendPacket(padNameField.getText(), channelNameField.getText());
		}

		if(padNameField != null) 
			padNameField.textboxKeyTyped(c, i);
		if(channelNameField != null) 
			channelNameField.textboxKeyTyped(c, i);
	}

	protected void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		if(padNameField != null) padNameField.mouseClicked(i, j, k);
		if(channelNameField != null) channelNameField.mouseClicked(i, j, k);

	}

	public boolean doesGuiPauseGame(){
		return false;
	}


	@Override
	public void actionPerformed(GuiButton button) {
		sendPacket(padNameField.getText(), channelNameField.getText());
	}

	public void sendPacket(String padName, String channelName){
		ByteBuf buf = Unpooled.buffer();
		ByteBufOutputStream out = new ByteBufOutputStream(buf);
		try {

			out.writeInt(ServerPacketHandler.IDENTIFIER_NAMEPAD);
			out.writeInt(te.xCoord);
			out.writeInt(te.yCoord);
			out.writeInt(te.zCoord);
			out.writeUTF(padName);
			out.writeUTF(channelName);
			out.close();

			Telepads.Channel.sendToServer(new FMLProxyPacket(buf, Telepads.channelName ));

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.mc.thePlayer.closeScreen();
	}
}
