package telepads;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;

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
		fontRenderer.drawSplitString("As the owner of this pad, it looks like you lost your register. Would you like a new one ?", posX+1 -80, posY-1-60, 180 ,0x000000);
		fontRenderer.drawSplitString("As the owner of this pad, it looks like you lost your register. Would you like a new one ?", posX -80, posY-60, 180 ,0xd494e8);
	}
	
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			ItemStack stack = new ItemStack(mod_telepads.padLocator);
			stack.setTagCompound(new NBTTagCompound());
			
			for(int i = 0; i < te.allCoords.size(); i++ ){
				
				stack.getTagCompound().setIntArray(ItemPadLocations.LOCATION_+i, te.allCoords.get(i));
			}
			
			for (int t = 0; t < te.allNames.size(); t++){
				stack.getTagCompound().setString("TelePadName_"+t, te.allNames.get(t));
			}
			stack.getTagCompound().setInteger(ItemPadLocations.SIZE, te.allCoords.size());

			sendPacket(button.id, stack);
			thePlayer.closeScreen();
			break;

		default:
			thePlayer.closeScreen();
			break;
		}
		
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		// TODO Auto-generated method stub
		return false;
	}

	public void sendPacket(int id, ItemStack stack){

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutput out;
		DataOutputStream outputStream = new DataOutputStream(bytes);
		try {
			outputStream.writeInt(TelePadsTeleportHandler.IDENTIFIER_REGISTER);

			Packet.writeItemStack(stack, outputStream);
			Packet250CustomPayload packet = new Packet250CustomPayload("telePads", bytes.toByteArray());
			PacketDispatcher.sendPacketToServer(packet);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
