package gravestone.handelers;

import gravestone.mod_Gravestone;
import gravestone.grave.te.TEGrave;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	public int x1;
	public int y1;
	public int z1;

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		this.handlePacket(packet, player);

		if(packet.channel.equals("graveData"))
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
			if(guiId == 1)
			{
				x1 = dis.readInt();
				y1 = dis.readInt();
				z1 = dis.readInt();

				TileEntity tile = world.getBlockTileEntity(x1, y1, z1);

				if(dis.readInt() == 6)
				{
					if (tile != null)
					{
						TEGrave te = (TEGrave)tile;
						if(te != null)
						{
							for(int par1 =0; par1 < te.inv.length;par1++)
							{
								ItemStack stacks = te.getStackInSlot(par1);

								if(stacks != null)
								{
									EntityItem item = new EntityItem(p.worldObj);
									item.setEntityItemStack(stacks);

									p.worldObj.spawnEntityInWorld(item);
									//if(par1 < 36){
									//p.inventory.addItemStackToInventory(stacks);
									//	te.setInventorySlotContents(par1, null);
									//}else if(par1 >= 36){
									//	int i = EntityLiving.getArmorPosition(stacks) - 1;
									//	ItemStack itemstack1 = p.getCurrentArmor(i);
									//	if (itemstack1 == null){
									//		p.setCurrentItemOrArmor(i + 1, stacks);
									//	}else{
									//		p.dropPlayerItem(stacks);
									//	}
									//	te.setInventorySlotContents(par1, null);
									//}
								}
							}
						}
					}
				}
			}
			if(guiId == 2)
			{
				x1 = dis.readInt();
				y1 = dis.readInt();
				z1 = dis.readInt();
				if(dis.readInt() == 7)
				{
					world.setBlock(x1,y1,z1,0);
				}
			}

			if(guiId == 3)
			{
				int c =dis.readInt();
				mod_Gravestone.proxy.setRenderID(p.username, c);
			}

			if(guiId == 4)
			{
				x1 = dis.readInt();
				y1 = dis.readInt();
				z1 = dis.readInt();

				TileEntity tile = world.getBlockTileEntity(x1, y1, z1);

				if (tile != null)
				{
					TEGrave te = (TEGrave)tile;
					if(te != null)
					{
						if(!world.isRemote)
							if(world.getPlayerEntityByName(te.playername)!= null)
								world.getPlayerEntityByName(te.playername).addChatMessage(dis.readUTF()+" requests you to salvage your grave.");
					}
				}
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
