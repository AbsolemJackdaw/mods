package gravestone.bones;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;

public class TEBones extends TileEntity {

	public String texture = "";

	public float rotation = 0.0f;
	private int saturation;

	public void setTexture(String icon)
	{
		texture = icon;
	}

	public int getSat()
	{
		return saturation;
	}

	public void setSat(int sat)
	{
		saturation = sat;
	}
	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {

		int id = this.worldObj.getBlockId(xCoord,yCoord-1,zCoord);
		Block block = Block.blocksList[id];
		Icon icon;
		String texture = "";
		int sat=0;
		if(block != null && block.blockID >0){
			int meta2 = block.getDamageValue(this.worldObj, xCoord, yCoord-1, zCoord);
			if(this.worldObj.isRemote){
				icon = block.getBlockTextureFromSide(1);
				texture =icon.getIconName();
				sat = block.getBlockColor();
				if(this.texture.length() <=0 )
					this.setTexture(texture);
				this.setSat(sat);
			}
		}
	}

	public Packet getDescriptionPacket(){
		Packet132TileEntityData datapacket = null;
		NBTTagCompound blockinfo = new NBTTagCompound();
		writeToNBT(blockinfo);
		//4th paramater is action type, 1-4 will perform vanilla functions via this packet, dont use.
		datapacket = new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord,5, blockinfo);
		return datapacket;
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setString("icon", texture);
		par1NBTTagCompound.setFloat("rotation", rotation);
		par1NBTTagCompound.setInteger("sat", saturation);

	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);

		texture = par1NBTTagCompound.getString("icon");
		rotation = par1NBTTagCompound.getFloat("rotation");
		saturation = par1NBTTagCompound.getInteger("sat");
	}
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.data);
	}
}
