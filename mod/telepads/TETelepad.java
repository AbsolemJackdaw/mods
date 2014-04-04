package telepads;

import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;

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

		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+0.5, yCoord+0.5, zCoord+0.5);//this.().copy().expand(-0.5, 0.5, -0.5);

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
				if(p != null){
					if(p.inventory.hasItem(Telepads.padLocator)){
						for(int i = 0; i < p.inventory.getSizeInventory(); i++){
							if(p.inventory.getStackInSlot(i) != null && p.inventory.getStackInSlot(i).getItem() instanceof ItemPadLocations){

								ItemStack stack = p.inventory.getStackInSlot(i);//the telepad register
								//for nitwits that put in a new list from creative or trough 'cheating'
								if(!stack.hasTagCompound()){
									ResetAndNotify("Damn, that's not the right TelePad Register...", p);
									break;
								}else{

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
									System.out.println(ownerName + " " +p.getDisplayName());
									if(p.getDisplayName().equals(ownerName)){
										setRegisterToPad(stack);
										System.out.println("passed");
									}
									if(!worldObj.isRemote)
										stack.getTagCompound().setInteger("originalGUIScale", Minecraft.getMinecraft().gameSettings.guiScale);
									//opens gui in proxy wit id 0
									p.openGui(Telepads.instance, 0, worldObj, xCoord, yCoord, zCoord);
									break;
								}
							}
						}
					}else{

						if(p.getDisplayName().equals(ownerName)){
							p.openGui(Telepads.instance, 2, worldObj, xCoord, yCoord, zCoord);
						}
						ResetAndNotify("TelePad Error :"+ p.getDisplayName() + " has no TeleRegister !", p);
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
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());  //packet.data	
	}

	public void resetTE(){
		counter = def_count;
		isStandingOnPlatform = false;
	}

	/**Saves the register's content to the TelePad*/
	public void setRegisterToPad(ItemStack stack){

		ItemStack stack1 = stack;

		allCoords = new ArrayList<int[]>();
		allNames = new ArrayList<String>();
		allDims = new ArrayList<Integer>();

		int size = stack1.getTagCompound().getInteger(ItemPadLocations.SIZE);

		for(int c =0; c < size; c++){

			int[] ray = new int[3];
			ray[0] = stack1.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[0];
			ray[1] = stack1.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[1];
			ray[2] = stack1.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[2];

			String padName = stack1.getTagCompound().getString("TelePadName_"+c);
			int dim = stack1.getTagCompound().getInteger(ItemPadLocations.DIM_+c);
			allCoords.add(ray);
			allNames.add(padName);
			allDims.add(dim);
		}
	}

	/**Sets isStandingOnPlatform, and reset's TE if false*/
	public void changePlatformState(boolean b){
		isStandingOnPlatform = b;
		if(!b){
			resetTE();
			playerStandingOnPad = null;
		}
	}

	/**Resets the TelePad and notifies the player of it : aka, send chat mesage*/
	public void ResetAndNotify(String message, EntityPlayer p){
		if(!worldObj.isRemote)
			p.addChatComponentMessage(new ChatComponentText(message));
		resetTE();
	}


	private void writeBasic(int identifier, ByteBufOutputStream stream) throws IOException{
		stream.writeInt(identifier);

		stream.writeInt(xCoord);
		stream.writeInt(yCoord);
		stream.writeInt(zCoord);
	}
}
