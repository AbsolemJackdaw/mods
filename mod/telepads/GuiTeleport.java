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

			this.buttonList.add(new GuiButton(10000, //id
					//xposition
					posX, 
					// y position 
					posY, 
					//button size
					20, 20,
					//name of the button
					"X")); 

			for(int i = 0; i < c; i++){
				//				String s = "";
				//				TETelepad other = (TETelepad)te.worldObj.getBlockTileEntity(te.allCoords.get(i)[0],te.allCoords.get(i)[1],te.allCoords.get(i)[2]);
				//				if(te!=null)
				//					s = other.telepadname;

				this.buttonList.add(new GuiButton(i, //id
						//xposition
						posX-200 + (i < 10 ? 0 : i< 20 ? 150 : 300), 
						// y position 
						posY+(i*25) - ( i< 10 ? 100 : i < 20 ? 350 : 600), 
						//button size
						100, 20,
						//name of the button
						telepadCertificate.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+i)[0] == ray[0] &&
						telepadCertificate.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+i)[1] == ray[1] &&
						telepadCertificate.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+i)[2] == ray[2]?
								"Current Location" :	telepadCertificate.getTagCompound().getString("TelePadName_"+i))); 
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
	private static final Random field_110644_e = new Random(31100L);
	FloatBuffer field_76908_a = GLAllocation.createDirectFloatBuffer(16);

	float c = 0;
	float sd = 0;
	@Override
	public void drawBackground(int par1) {
		c += 1f;
		sd +=0.01f;
		float k = c+2;
		GL11.glPushMatrix();
		GL11.glColor4f(0.2f, 0.6f, 1f, sd < 0.95f ? sd : 0.95f);
		mc.getMinecraft().renderEngine.bindTexture(endPortalTextures);
		drawTexturedModalRect(0, 0, (int)k, (int)c , 3000, 3000);
		GL11.glPopMatrix();
//				super.drawBackground(par1);
	}

	@Override
	public void drawDefaultBackground() {
		//		super.drawDefaultBackground();
//		mc.getMinecraft().renderEngine.bindTexture(endPortalTextures);
//		drawTexturedModalRect(0, 0, 100, 100, 500, 500);
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton button) {

		if(thePlayer != null){
			int id = button.id;
			if(id == 10000 ){
				sendPacket(id);
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
		ObjectOutput out;
		DataOutputStream outputStream = new DataOutputStream(bytes);
		try {
			outputStream.writeInt(TelePadsTeleportHandler.IDENTIFIER_TELPORTER);
			outputStream.writeInt(id);

			outputStream.writeInt(te.xCoord);
			outputStream.writeInt(te.yCoord);
			outputStream.writeInt(te.zCoord);

			if(id < 10000){
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
