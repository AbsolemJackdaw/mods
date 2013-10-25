package telepads;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiTeleport extends GuiScreen{

	public EntityPlayer thePlayer;
	public TETelepad te;
	/**can be null !*/
	public ItemStack telepadCertificate = null;

	public static final int EXIT_BUTTON = 10000;

	int[] ray = new int[3];

	public GuiTeleport(EntityPlayer player, TETelepad te){
		this.te = te;
		thePlayer = player;

		ray[0] = te.xCoord;
		ray[1] = te.yCoord;
		ray[2] = te.zCoord;


		if(player.inventory.hasItem(mod_telepads.padLocator.itemID)){
			for(int i = 0; i < player.inventory.getSizeInventory(); i++){
				if(player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() instanceof ItemPadLocations){
					telepadCertificate = player.inventory.getStackInSlot(i);
				}
			}
		}
	}

	@Override
	public void initGui() {

		int posX = (this.width) / 2;
		int posY = (this.height) / 2;
		this.buttonList.clear();

		try {
			int c = te.allCoords.size();

			this.buttonList.add(new GuiButton(EXIT_BUTTON, posX, posY, 20, 20,"X")); 

			for(int i = 0; i < c; i++){

				boolean isCurrentPad = telepadCertificate.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+i)[0] == ray[0] &&
						telepadCertificate.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+i)[1] == ray[1] &&
						telepadCertificate.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+i)[2] == ray[2];

				String name = isCurrentPad ? "Current Location" :	telepadCertificate.getTagCompound().getString("TelePadName_"+i);

				this.buttonList.add(new GuiButton(i, /*x*/posX-200 + (i < 10 ? 0 : i< 20 ? 150 : 300),/*y*/posY+(i*25) - ( i< 10 ? 100 : i < 20 ? 350 : 600), 
						/*size*/100, 20, /**/name)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void drawScreen(int par1, int par2, float par3) {

		this.drawBackground(par1);

		super.drawScreen(par1, par2, par3);
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
				sendPacket(EXIT_BUTTON);
				thePlayer.closeScreen();

			}else{
				sendPacket(id);
				thePlayer.closeScreen();
			}
		}

		te.resetTE();
	}


	public void sendPacket(int id){

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(bytes);
		try {
			outputStream.writeInt(TelePadsTeleportHandler.IDENTIFIER_TELPORTER);

			outputStream.writeInt(te.xCoord);
			outputStream.writeInt(te.yCoord);
			outputStream.writeInt(te.zCoord);

			outputStream.writeInt(id);

			if(id < EXIT_BUTTON){
				outputStream.writeInt(te.allCoords.get(id)[0]);//x
				outputStream.writeInt(te.allCoords.get(id)[1]);//y
				outputStream.writeInt(te.allCoords.get(id)[2]);//z

				outputStream.writeInt(te.allDims.get(id));
			}


			Packet250CustomPayload packet = new Packet250CustomPayload("telePads", bytes.toByteArray());
			PacketDispatcher.sendPacketToServer(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
