package telepads.gui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import telepads.ServerPacketHandler;
import telepads.Telepads;
import telepads.block.TETelepad;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class GuiNewRegister extends GuiScreen {

	EntityPlayer thePlayer;
	TETelepad te;

	public GuiNewRegister(EntityPlayer player, TETelepad te){
		thePlayer = player;	
		this.te = te;

	}

	@Override
	public void initGui() {

		int posX = (this.width) / 2;
		int posY = (this.height) / 2;

		super.initGui();

		this.buttonList.clear();

		buttonList.add(new GuiButton(0, posX -75, posY-30, 150, 20, "I'd like a new Register Please."));
		buttonList.add(new GuiButton(1, posX -75, posY, 150, 20, "No thanks, I only forgot it."));

	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		int posX = (this.width) / 2;
		int posY = (this.height) / 2;
		fontRendererObj.drawSplitString("As the owner of this pad, it looks like you lost your register. Would you like a new one ?", posX+1 -80, posY-1-60, 180 ,0x000000);
		fontRendererObj.drawSplitString("As the owner of this pad, it looks like you lost your register. Would you like a new one ?", posX -80, posY-60, 180 ,0xd494e8);
	}


	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			sendPacket(button.id, null);
			this.mc.thePlayer.closeScreen(); //closes the screen
			break;

		default:
			this.mc.thePlayer.closeScreen(); //closes the screen
			break;
		}

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public void sendPacket(int id, ItemStack stack){

		ByteBuf buf = Unpooled.buffer();
		ByteBufOutputStream out = new ByteBufOutputStream(buf);
		try {
//			out.writeInt(ServerPacketHandler.IDENTIFIER_REGISTER);
			
			System.out.println("SEND PACKET HERE! making register");

			out.writeInt(te.xCoord);
			out.writeInt(te.yCoord);
			out.writeInt(te.zCoord);
			out.close();

//			Telepads.Channel.sendToServer(new FMLProxyPacket(buf, Telepads.channelName));			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
