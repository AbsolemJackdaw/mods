package telepads.block;

import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import telepads.Telepads;
import telepads.util.TelePadGuiHandler;
import telepads.util.TelepadWorldData;

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

	public String telepadname = "TelePad";

	public int dimension;

	public String ownerName = "";

	public boolean isStandingOnPlatform = false;

	public static final int def_count = 5*20;
	public int counter = def_count;

	//TODO set channel client side when placed
	public String TELEPORTCHANNEL = "DefaultChannel";

	/**Set when player walks on a pad*/
	public EntityPlayer playerStandingOnPad = null;

	private int previousSize = 0;
	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {

		par1nbtTagCompound.setString("name", telepadname);
		par1nbtTagCompound.setString("owner", ownerName);
		par1nbtTagCompound.setInteger("dimension", dimension);
		par1nbtTagCompound.setString("channel", TELEPORTCHANNEL);

		super.writeToNBT(par1nbtTagCompound);
		System.out.println("write");
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {

		telepadname = (par1nbtTagCompound.getString("name"));
		ownerName = par1nbtTagCompound.getString("owner");
		dimension = par1nbtTagCompound.getInteger("dimension");
		TELEPORTCHANNEL = par1nbtTagCompound.getString("channel");

		super.readFromNBT(par1nbtTagCompound);
		System.out.println("read");
	}

	@Override
	public void updateEntity() {

		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+0.5, yCoord+0.5, zCoord+0.5);

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

				if(counter >=0)counter --;
			}
			if(counter < 0){
				if(p != null){
					markDirty();
					p.openGui(Telepads.instance, TelePadGuiHandler.TELEPORT, worldObj, xCoord, yCoord, zCoord);
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
		markDirty();
		TelepadWorldData.get(worldObj).markDirty();
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
