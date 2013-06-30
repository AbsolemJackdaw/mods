package gravestone.grave;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TEGrave extends TileEntity // implements IInventory
{
	public String playername = "";
	public boolean hasItems = true;
	public ItemStack[] graveContents = new ItemStack[40];
	public int theMeta =0;
	public int base = 0;
	public float ModelRotation = 0;
	public EntityPlayer thePlayer;
	Random rand = new Random();
	public String message1 = "";
	public String message2= "";
	public boolean customName = false;

	public String setName(String name)
	{
		playername = name;
		return playername;
	}

	public Entity setPlayer(EntityPlayer player)
	{
		thePlayer = player;
		return player;
	}
	public void setDeathMessage(String message, String message2)
	{
		message1 = message;
		this.message2 = message2;
	}

	public void setMeta(int i)
	{
		theMeta = i;
	}

	private String field_94045_s;

	public int getSizeInventory()
	{
		return graveContents.length;
	}

	public ItemStack getStackInSlot(int par1)
	{
		return this.graveContents[par1];
	}

	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.graveContents[par1] != null)
		{
			ItemStack itemstack;

			if (this.graveContents[par1].stackSize <= par2)
			{
				itemstack = this.graveContents[par1];
				this.graveContents[par1] = null;
				this.onInventoryChanged();
				return itemstack;
			}
			else
			{
				itemstack = this.graveContents[par1].splitStack(par2);

				if (this.graveContents[par1].stackSize == 0)
				{
					this.graveContents[par1] = null;
				}

				this.onInventoryChanged();
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.graveContents[par1] != null)
		{
			ItemStack itemstack = this.graveContents[par1];
			this.graveContents[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.graveContents[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();

	}

	public String getInvName()
	{
		return this.isInvNameLocalized() ? this.field_94045_s : "container.chest";
	}

	public boolean isInvNameLocalized()
	{
		return this.field_94045_s != null && this.field_94045_s.length() > 0;
	}

	public void func_94043_a(String par1Str)
	{
		this.field_94045_s = par1Str;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);

		playername = par1NBTTagCompound.getString("name");
		message1 = par1NBTTagCompound.getString("message");
		message2 = par1NBTTagCompound.getString("message2");
		theMeta = par1NBTTagCompound.getInteger("Meta");
		base = par1NBTTagCompound.getInteger("base");
		ModelRotation = par1NBTTagCompound.getFloat("rotation");
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.graveContents = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.graveContents.length)
			{
				this.graveContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setString("name", playername);
		par1NBTTagCompound.setString("message", message1);
		par1NBTTagCompound.setString("message2", message2);
		par1NBTTagCompound.setInteger("Meta", theMeta);
		par1NBTTagCompound.setInteger("base", base);
		par1NBTTagCompound.setFloat("rotation", ModelRotation);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.graveContents.length; ++i)
		{
			if (this.graveContents[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				graveContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);
	}

	public int getInventoryStackLimit()
	{
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	public Packet getDescriptionPacket() {
		Packet132TileEntityData datapacket = null;
		NBTTagCompound blockinfo = new NBTTagCompound();
		writeToNBT(blockinfo);
		//4th paramater is action type, 1-4 will perform vanilla functions via this packet, dont use.
		datapacket = new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord,5, blockinfo);
		return datapacket;
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.customParam1);
	}

	public void dropContents(World world, int x, int y, int z) {

		if (this != null) {
			for (int slotIndex = 0; slotIndex < this.getSizeInventory(); slotIndex++) {
				ItemStack items = this.getStackInSlot(slotIndex);

				if (items != null) {
					float var10 = rand.nextFloat() * 0.8F + 0.1F;
					float var11 = rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityItem;

					for (float var12 = rand.nextFloat() * 0.8F + 0.1F; items.stackSize > 0; world.spawnEntityInWorld(entityItem)) {
						int var13 = rand.nextInt(21) + 10;

						if (var13 > items.stackSize) {
							var13 = items.stackSize;
						}

						items.stackSize -= var13;
						entityItem = new EntityItem(world, (double) (x + var10), (double) (y + var11), (double) (z + var12), new ItemStack(items.itemID, var13, items.getItemDamage()));
						entityItem.motionX = (double) (rand.nextGaussian() * 0.05F);
						entityItem.motionY = (double) (rand.nextGaussian() * 0.25F);
						entityItem.motionZ = (double) (rand.nextGaussian() * 0.05F);

						if (items.hasTagCompound()) {
							entityItem.getEntityItem().setTagCompound((NBTTagCompound) items.getTagCompound().copy());
						}
					}
				}
			}
		}
	}

	public void setItems(ItemStack[] items)
	{
		if (items != null) 
		{
			for (int i = 0; i < items.length; i++) 
			{
				setInventorySlotContents(i, items[i]);
			}
		}
	}
}
