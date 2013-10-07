package petBuddy.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLLog;

public class TEShrine extends TileEntity{


	public boolean hasStatue = false;
	public int cooldown = 40*20; // 40 seconds * ticks
	public boolean countdown = false;
	public boolean cycleDone = false;

	public boolean hasItemStack = false;
	public ItemStack itemToImbue;


	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public Packet getDescriptionPacket() {
		Packet132TileEntityData datapacket = null;
		NBTTagCompound blockinfo = new NBTTagCompound();
		writeToNBT(blockinfo);
		//4th paramater is action type, 1-4 will perform vanilla functions via this packet, don't use.
		datapacket = new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 5, blockinfo);
		FMLLog.getLogger().info("BLOCKINFO "+ blockinfo);
		return datapacket;
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.data);
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		cooldown  = nbt.getInteger("cooldown");
		countdown = nbt.getBoolean("isCounting");
		hasStatue = nbt.getBoolean("hasStatue");
		cycleDone = nbt.getBoolean("isCycleDone");
		hasItemStack = nbt.getBoolean("stack");

		NBTTagList nbttaglist = nbt.getTagList("Items");

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			this.itemToImbue = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("cooldown", cooldown);
		nbt.setBoolean("isCounting", countdown);
		nbt.setBoolean("hasStatue", hasStatue);
		nbt.setBoolean("isCycleDone", cycleDone);
		nbt.setBoolean("stack", hasItemStack);

		NBTTagList nbttaglist = new NBTTagList();

		if (this.itemToImbue != null){
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			itemToImbue.writeToNBT(nbttagcompound1);
			nbttaglist.appendTag(nbttagcompound1);
		}
		nbt.setTag("Items", nbttaglist);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
	
		if(cooldown == 0){
			countdown = false;
			cycleDone = true;
		}

		if(countdown){
			cooldown --;
		}

	}

}
