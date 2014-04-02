package telepads;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

public class GuiNameTelepad extends GuiScreen{

	private GuiTextField textfield;
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

		textfield = new GuiTextField(fontRendererObj, posX-(150/2) , posY-50, 150, 20);

		String text = te.telepadname.equals("TelePad") ? te.getWorldObj().getBiomeGenForCoords(te.xCoord, te.zCoord).biomeName : te.telepadname;

		if(textfield != null){
			textfield.setText(text);
			textfield.setMaxStringLength(50);
		}
	}


	@Override
	public void drawScreen(int par1, int par2, float par3) {
		
		int posX = (this.width ) / 2;
		int posY = (this.height ) / 2;
		try{
			fontRendererObj.drawSplitString("Press Enter to confirm", posX+1 -75, posY-1, 180 ,0x000000);
			fontRendererObj.drawSplitString("Press Enter to confirm", posX -75, posY, 180 ,0xffffff);
			
			fontRendererObj.drawSplitString("Name Your TelePad : "+textfield.getText(), posX+1 -75, posY-1-20, 180 ,0x000000);
			fontRendererObj.drawSplitString("Name Your TelePad : "+textfield.getText(), posX   -75, posY  -20, 180 ,0xff0000);
		}finally{
			if(textfield != null) textfield.drawTextBox();
		}

	}

	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		if(i == Keyboard.KEY_RETURN){
			sendPacket(textfield.getText());
		}
		
		if(textfield != null) 
			textfield.textboxKeyTyped(c, i);
	}

	protected void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		if(textfield != null) textfield.mouseClicked(i, j, k);
	}

	public boolean doesGuiPauseGame(){
		return false;
	}


	@Override
	public void actionPerformed(GuiButton button) {
		sendPacket(textfield.getText());
		thePlayer.openContainer = thePlayer.inventoryContainer;
	}

	public void sendPacket(String padName){
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutput out;
		DataOutputStream outputStream = new DataOutputStream(bytes);
		try {
			outputStream.writeInt(ServerPacketHandler.IDENTIFIER_NAMEPAD);

			System.out.println("SEND PACKET HERE ! TelePort gui tp packet");
//			outputStream.writeInt(te.xCoord);
//			outputStream.writeInt(te.yCoord);
//			outputStream.writeInt(te.zCoord);
//
//			outputStream.writeUTF(padName);
//
//			Packet250CustomPayload packet = new Packet250CustomPayload("telePads", bytes.toByteArray());
//			PacketDispatcher.sendPacketToServer(packet);

		} catch (IOException e) {
			e.printStackTrace();
		}
		thePlayer.openContainer = thePlayer.inventoryContainer; //closes the screen
	}
}
