package telepads;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class TETelepad extends TileEntity{

	public ArrayList<int[]> allCoords = new ArrayList<int[]>();

	public ArrayList<String> allNames = new ArrayList<String>();

	public ArrayList<Integer> allDims = new ArrayList<Integer>();

	public String telepadname = "TelePad";

	public String ownerName = "";

	public boolean isStandingOnPlatform = false;

	public static final int def_count = 5*20;
	public int counter = def_count;

	/**Set when player walks on a pad*/
	public EntityPlayer playerStandingOnPad = null;

	private int previousSize = 0;
	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {

		par1nbtTagCompound.setString("name", telepadname);
		par1nbtTagCompound.setString("owner", ownerName);

		par1nbtTagCompound.setInteger("arraySize", allCoords.size());

		allCoords.trimToSize();
		allNames.trimToSize();
		allDims.trimToSize();

		for (int i = 0; i < this.allCoords.size(); ++i)
		{
			par1nbtTagCompound.setIntArray("Coords_"+i, allCoords.get(i));
		}

		for (int i = 0; i < this.allNames.size(); ++i)
		{
			par1nbtTagCompound.setString("PadName_"+i, allNames.get(i));
		}

		for(int i = 0; i < allDims.size(); i++){
			par1nbtTagCompound.setInteger("Dimension_"+i, allDims.get(i));
		}

		super.writeToNBT(par1nbtTagCompound);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {

		telepadname = (par1nbtTagCompound.getString("name"));
		ownerName = par1nbtTagCompound.getString("owner");

		int c = par1nbtTagCompound.getInteger("arraySize");
		allCoords = new ArrayList<int[]>();
		allNames = new ArrayList<String>();
		for (int i = 0; i < c ; ++i){
			this.allCoords.add(par1nbtTagCompound.getIntArray("Coords_"+i));
			this.allNames.add(par1nbtTagCompound.getString("PadName_"+i));
			this.allDims.add(par1nbtTagCompound.getInteger("Dimension_"+i));
		}

		allCoords.trimToSize();
		allNames.trimToSize();
		allDims.trimToSize();

		super.readFromNBT(par1nbtTagCompound);
	}

	@Override
	public void updateEntity() {

		if(ownerName.equals("UNIVERSAL") && !telepadname.equals("Universal Pad")){
			this.telepadname = "Universal Pad";
		}

		if(worldObj.isRemote){//client only
			AxisAlignedBB aabb = this.getRenderBoundingBox().copy().expand(-0.5, 0.5, -0.5);

			List<EntityPlayer> playerInAabb = worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);		

			if(isStandingOnPlatform){
				if(playerInAabb.isEmpty() || !playerInAabb.contains(playerStandingOnPad)){
					changePlatformState(false);
				}
			}

			for(EntityPlayer p : playerInAabb){
				if(p!=null){
					if(isStandingOnPlatform == false)//check to prevent packet from spamming
						changePlatformState(true);

					if(counter >=0)
						counter --;
				}

				if(counter <= 0){
					if(p != null && Minecraft.getMinecraft().currentScreen == null){
						if(p.inventory.hasItem(mod_telepads.padLocator.itemID)){
							for(int i = 0; i < p.inventory.getSizeInventory(); i++){
								if(p.inventory.getStackInSlot(i) != null && p.inventory.getStackInSlot(i).getItem() instanceof ItemPadLocations){

									ItemStack stack = p.inventory.getStackInSlot(i);//the telepad register

									//for nitwits that put in a new list from creative or trough 'cheating'
									if(!stack.hasTagCompound()){
										ResetAndNotify("Damn, that's not the right TelePad Register...");
										break;
									}else{

										if(p.username.equals(ownerName) || ownerName.equals("UNIVERSAL")){

											allCoords = new ArrayList<int[]>();
											allNames = new ArrayList<String>();
											allDims = new ArrayList<Integer>();

											int size = stack.getTagCompound().getInteger(ItemPadLocations.SIZE);

											for(int c =0; c < size; c++){

												int[] ray = new int[3];
												ray[0] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[0];
												ray[1] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[1];
												ray[2] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[2];

												String padName = stack.getTagCompound().getString("TelePadName_"+c);
												int dim = stack.getTagCompound().getInteger(ItemPadLocations.DIM_+c);
												allCoords.add(ray);
												allNames.add(padName);
												allDims.add(dim);
											}

											setRegisterToPad(stack);

//											setMCGuiSettingToRegister(stack);
											stack.getTagCompound().setInteger("originalGUIScale", Minecraft.getMinecraft().gameSettings.guiScale);
											
											// ... and open gui
											p.openGui(mod_telepads.instance, 0, worldObj, xCoord, yCoord, zCoord);
											break;
										}else{
											ResetAndNotify("Oops, this is not my TelePad.");
											break;
										}
									}
								}
							}
						}else{

							if(p.username.equals(ownerName))
								p.openGui(mod_telepads.instance, 2, worldObj, xCoord, yCoord, zCoord);
							ResetAndNotify("Damn, I forgot my TelePad Register...");
							break;
						}
					}
				}
			}
		}
	}

	

	@Override
	public boolean canUpdate() {
		return true;
	}

	public Packet getDescriptionPacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.data);
	}


	public void resetTE(){

		counter = def_count;
		isStandingOnPlatform = false;

	}


//	/**Saves current mc gui screen size setting to the pad to restore it afterwards.*/
//	private void setMCGuiSettingToRegister(ItemStack stack) {
//		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//		DataOutputStream outputStream = new DataOutputStream(bytes);
//		try {
//			writeBasic(TelePadsTeleportHandler.IDENTIFIER_GUISIZE, outputStream);
//
//			Packet.writeItemStack(stack, outputStream);
//
//			Packet250CustomPayload packet = new Packet250CustomPayload("telePads", bytes.toByteArray());
//			PacketDispatcher.sendPacketToServer(packet);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**Saves the register's content to the TelePad*/
	public void setRegisterToPad(ItemStack stack){

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(bytes);
		try {
			writeBasic(TelePadsTeleportHandler.IDENTIFIER_TE, outputStream);

			Packet.writeItemStack(stack, outputStream);

			Packet250CustomPayload packet = new Packet250CustomPayload("telePads", bytes.toByteArray());
			PacketDispatcher.sendPacketToServer(packet);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**Sets isStandingOnPlatform, and reset's TE if false*/
	public void changePlatformState(boolean b){

		isStandingOnPlatform = b;

		if(!b)
			playerStandingOnPad = null;

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(bytes);

		try {
			writeBasic(TelePadsTeleportHandler.IDENTIFIER_PLATFORM, outputStream);

			outputStream.writeBoolean(b);

			Packet250CustomPayload packet = new Packet250CustomPayload("telePads", bytes.toByteArray());
			PacketDispatcher.sendPacketToServer(packet);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**Resets the TelePad and notifies the player of it : aka, send chat mesage*/
	public void ResetAndNotify(String message){
		resetTE();
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream outputStream = new DataOutputStream(bytes);

		try {
			writeBasic(TelePadsTeleportHandler.IDENTIFIER_RESETnNOTIFY, outputStream);

			outputStream.writeUTF(message);

			Packet250CustomPayload packet = new Packet250CustomPayload("telePads", bytes.toByteArray());
			PacketDispatcher.sendPacketToServer(packet);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void writeBasic(int identifier, DataOutputStream stream) throws IOException{
		stream.writeInt(identifier);

		stream.writeInt(xCoord);
		stream.writeInt(yCoord);
		stream.writeInt(zCoord);
	}
}
