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

	public String ownerName;

	public boolean isStandingOnPlatform = false;

	public static final int def_count = 5*20;
	public int counter = def_count;

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

		//check to see if the block exists at xyz coords. if not, remove the location from allCoords
		//		for(int t = 0; t < allCoords.size(); t++){
		//			if(allCoords.get(t) != null)
		//				if(worldObj.blockExists(allCoords.get(t)[0], allCoords.get(t)[1], allCoords.get(t)[2]) && Block.blocksList[worldObj.getBlockId(allCoords.get(t)[0], allCoords.get(t)[1], allCoords.get(t)[2])] instanceof BlockTelepad){
		//				}else{
		//					//if the block is inexistant, or is not a telepad, or both
		//					allCoords.remove(t);
		//					allCoords.trimToSize();
		//				}
		//		}

		if(ownerName.equals("UNIVERSAL") && !telepadname.equals("Universal Pad")){
			this.telepadname = "Universal Pad";
		}


		AxisAlignedBB aabb = this.getRenderBoundingBox().copy().expand(-0.5, 0.5, -0.5);

		List<EntityPlayer> playerInAabb = worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);		

		if(isStandingOnPlatform){
			if(playerInAabb.isEmpty() || !playerInAabb.contains(playerStandingOnPad)){
				isStandingOnPlatform = false;
				resetTE();
			}
		}
		for(EntityPlayer p : playerInAabb){
			if(p!=null){
				isStandingOnPlatform = true;
				if(counter >=0)
					counter --;
			}

			if(counter <= 0){
				if(p != null && Minecraft.getMinecraft().currentScreen == null){
					if(p.inventory.hasItem(mod_telepads.padLocator.itemID)){
						for(int i = 0; i < p.inventory.getSizeInventory(); i++){
							if(p.inventory.getStackInSlot(i) != null && p.inventory.getStackInSlot(i).getItem() instanceof ItemPadLocations){
								ItemStack stack = p.inventory.getStackInSlot(i);


								//for nitwits that put in a new list from creative or trough 'cheating'
								if(!stack.hasTagCompound()){
									//if the pad is the player's, give new register TODO
									if(!worldObj.isRemote)
										p.addChatMessage("Damn, that's not the right TelePad Register...");
									resetTE();
									break;
								}else{

									//FMLLog.getLogger().info(""+size);
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
											//FMLLog.getLogger().info(""+ray);
											allCoords.add(ray);
											allNames.add(padName);
											allDims.add(dim);
											//FMLLog.getLogger().info("Added " + padName + " to allNames : "+ allNames);
										}

										// ... and open gui
										FMLNetworkHandler.openGui(p, mod_telepads.instance, 0, worldObj, xCoord, yCoord, zCoord);
									}else{
										if(!worldObj.isRemote)
											p.addChatMessage("Oops, this is not my TelePad.");
										resetTE();
										break;
									}
								}
							}
						}
					}else{
						if(!worldObj.isRemote)
							p.addChatMessage("Damn, I forgot my TelePad Register...");

						if(p.username.equals(ownerName))
							FMLNetworkHandler.openGui(p, mod_telepads.instance, 2, worldObj, xCoord, yCoord, zCoord);

						resetTE();
						break;
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
		//		if(!worldObj.isRemote && playerStandingOnPad != null)
		//			playerStandingOnPad.addChatMessage("reset tile at pos " + xCoord + " " +yCoord);
		counter = def_count;
		isStandingOnPlatform = false;
		//		playerStandingOnPad = null;

	}


	public void sendPacket(){

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutput out;
		DataOutputStream outputStream = new DataOutputStream(bytes);
		try {
			outputStream.writeInt(TelePadsTeleportHandler.IDENTIFIER_TE);

			outputStream.writeInt(xCoord);
			outputStream.writeInt(yCoord);
			outputStream.writeInt(zCoord);

			Packet250CustomPayload packet = new Packet250CustomPayload("telePads", bytes.toByteArray());
			PacketDispatcher.sendPacketToPlayer(packet, (Player)playerStandingOnPad);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
