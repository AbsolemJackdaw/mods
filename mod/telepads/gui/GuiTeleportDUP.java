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

import org.lwjgl.input.Keyboard;

import telepads.ServerPacketHandler;
import telepads.Telepads;
import telepads.block.TETelepad;
import telepads.util.TelePadGuiHandler;
import telepads.util.TelepadWorldData;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class GuiTeleportDUP extends GuiScreen{

	private GuiTextField channelNameField;

	public EntityPlayer thePlayer;
	public TETelepad te;

	public static final int EXIT_BUTTON = 10000;


	public GuiTeleportDUP(EntityPlayer player, TETelepad te){
		thePlayer = player;
		this.te = te;
	}

	@Override
	public void initGui() {

		int posX = (this.width ) / 2;
		int posY = (this.height ) / 2;
		this.buttonList.clear();

		channelNameField = new GuiTextField(fontRendererObj, posX-175 , posY-118, 150, 17);

		String channel = "DefaultChannel";

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
			fontRendererObj.drawSplitString("Press Enter to confirm", posX+1 -15, posY-1 - 105, 180 ,0x000000);
			fontRendererObj.drawSplitString("Press Enter to confirm", posX -15, posY - 105, 180 ,0xffffff);

			fontRendererObj.drawSplitString("Channel to Join : "+channelNameField.getText(), posX+1 -15, posY-1-20 - 98 , 180 ,0x000000);
			fontRendererObj.drawSplitString("Channel to Join : "+channelNameField.getText(), posX -15 , posY  -20 -98, 180 ,0xff0000);
		}finally{
			if(channelNameField != null) channelNameField.drawTextBox();
		}

	}

	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		if(i == Keyboard.KEY_RETURN){

		}

		if(channelNameField != null) 
			channelNameField.textboxKeyTyped(c, i);
	}

	protected void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		if(channelNameField != null) channelNameField.mouseClicked(i, j, k);

	}

	public boolean doesGuiPauseGame(){
		return false;
	}


	@Override
	public void actionPerformed(GuiButton button) {
		//sendPacket(padNameField.getText(), channelNameField.getText());

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
				//				System.out.println(b.xTeleport + " " + b.yTeleport + " " + b.zTeleport);

				ArrayList<int[]>a = TelepadWorldData.get(thePlayer.worldObj).getCoords();
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
