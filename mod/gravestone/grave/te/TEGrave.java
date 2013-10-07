package gravestone.grave.te;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TEGrave extends TileEntity  implements IInventory
{

	public ItemStack[] inv;

	public String playername = "";
	public int theMeta =0;
	public int base = 0;
	public float ModelRotation = 0;
	public EntityPlayer thePlayer;
	public String message1 = "";
	public String message2= "";
	public boolean customName = false;

	public boolean hasItems = false;

	public String locked = "";

	//used in slotgrave
	public boolean otherPlayerHasTakenItemStack = false;

	public TEGrave(){
		inv = new ItemStack[40];
	}

	Random rand = new Random();

	public int getSizeInventory()
	{
		return inv.length;
	}

	public ItemStack getStackInSlot(int par1)
	{
		return this.inv[par1];
	}

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.inv[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();
	}

	public ItemStack decrStackSize(int slot, int ammount)
	{
		if (this.inv[slot] != null)
		{
			ItemStack itemstack;

			if (this.inv[slot].stackSize <= ammount)
			{
				itemstack = this.inv[slot];
				this.inv[slot] = null;
				this.onInventoryChanged();
				return itemstack;
			}
			else
			{
				itemstack = this.inv[slot].splitStack(ammount);

				if (this.inv[slot].stackSize == 0)
				{
					this.inv[slot] = null;
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
		if (this.inv[par1] != null)
		{
			ItemStack itemstack = this.inv[par1];
			this.inv[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	public int getInventoryStackLimit()
	{
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : 
			par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public String getInvName()
	{
		return "Grave";
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	public boolean isInvNameLocalized()
	{
		return true;
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

		otherPlayerHasTakenItemStack = par1NBTTagCompound.getBoolean("isLooted");
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.inv = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.inv.length)
			{
				this.inv[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
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
		par1NBTTagCompound.setBoolean("isLooted", otherPlayerHasTakenItemStack);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inv.length; ++i)
		{
			if (this.inv[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				inv[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);
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
		this.readFromNBT(pkt.data);
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
	public void setDeathMessage(String message)
	{
		message1 = message;
	}
	public void setDeathMessage2(String message)
	{
		message2 = message;
	}
	public void setMeta(int i)
	{
		theMeta = i;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		for(int i =0; i < getSizeInventory(); i++){
			if(getStackInSlot(i) != null){
				//if al stacks are null, hasItems = false;
				hasItems = true;
				return;
			}else{
				hasItems = false;
			}
		}
	}

	/*256 blocks squared*/
	@Override
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return this.INFINITE_EXTENT_AABB;
	}

}
