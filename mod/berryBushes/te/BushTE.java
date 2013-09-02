package berryBushes.te;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import berryBushes.Base;
import berryBushes.BerryCrops;
import berryBushes.Bush;

public class BushTE extends TileEntity{

	/*===Bush===*/
	protected int Meta;


	/*===Crops===*/
	public float count = 0;
	public boolean isCrop = false;
	public ItemStack stack;


	@Override
	public void updateEntity() {
		super.updateEntity();
		Block block = Block.blocksList[worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)];

		if(block != null && block instanceof Bush){
			Bush b = (Bush)block;
			if(Meta != b.Meta){
				Meta = b.Meta;
			}
		}

		if(block != null && block instanceof BerryCrops){

			if (count >= 0 && count < 24000){
				if(worldObj.getBlockMaterial(xCoord, yCoord-2, zCoord).equals(Material.rock) ){
					count -= 0.3f;
				}
				if(worldObj.getBlockId(xCoord, yCoord-2, zCoord) == Block.whiteStone.blockID ){
					count +=5f;
				}
				if(worldObj.getBlockId(xCoord, yCoord-2, zCoord) == Block.dirt.blockID ){
					count+=0.2f;
					if(worldObj.getBlockId(xCoord, yCoord-3, zCoord) == Block.dirt.blockID){
						count += 0.2f;
					}
				}
				if(worldObj.getBlockId(xCoord, yCoord-4, zCoord) == Block.blockClay.blockID ){
					count += 0.5f;
				}
				count+=1f;

				int c = worldObj.rand.nextInt(10);
				switch(c){
				case 0:
					count +=0.5f;
					break;
				case 2:
					count +=0.2f;
					break;
				case 3:
					count -= 0.1f;
					break;
				default :
					break;
				}
			}

			if(count > 8000 && count < 12000){
				stack = new ItemStack(Base.berry);
			}if(count >= 12000 && count < 18000){
				stack = new ItemStack(Base.berryII);
			}if(count >= 18000 && count < 24000){
				stack = new ItemStack(Base.berryIII);
			}if(count >= 24000){
				stack = new ItemStack(Base.berryIV);
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		isCrop = par1nbtTagCompound.getBoolean("isCrop");
		count = par1nbtTagCompound.getFloat("count");
		super.readFromNBT(par1nbtTagCompound);
	}
	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		par1nbtTagCompound.setBoolean("isCrop", isCrop);
		par1nbtTagCompound.setFloat("count", count);
		super.writeToNBT(par1nbtTagCompound);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
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
}
