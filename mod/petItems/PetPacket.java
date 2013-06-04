package petItems;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PetPacket implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		if(packet.channel.equals("petTools"))
		{
			this.handlePacket(packet, player);
		}
	}

	public void handlePacket(Packet250CustomPayload packet, Player player)
	{
		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			int guiId = dis.readInt();
			int itemID = dis.readInt();
			int xp = dis.readInt();

			if(guiId == 1){
				ItemStack itemstack = null ;
				if(itemID == 1){
					itemstack = new ItemStack(PetItems.hungerSword);
				}else if(itemID == 2){
					itemstack = new ItemStack(PetItems.hungerSpade);
				}else if(itemID == 3){
					itemstack = new ItemStack(PetItems.hungerAxe);
				}else if(itemID == 4){
					itemstack = new ItemStack(PetItems.hungerPick);
				}

				if(itemstack != null){
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setInteger("xpTotal", xp);
					itemstack.setTagCompound(nbt);
				}

				p.inventory.addItemStackToInventory(itemstack);
			}
		}catch(Throwable e){
			FMLLog.getLogger().info("Severe Error. Please Report to Mod Author.");

		}
	}
}
