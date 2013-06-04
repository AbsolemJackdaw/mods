package betterbreeds;

// These are the required imports
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import betterbreeds.entity.EntityWolf2;
import betterbreeds.entity.EntityWolf3;
import betterbreeds.entity.EntityWolf4;
import betterbreeds.entity.EntityWolf5;
import betterbreeds.entity.EntityWolf6;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		EntityPlayer entityplayer = (EntityPlayer)player;
		World World = entityplayer.worldObj;
		ObjectInputStream dis;
		try
		{
			dis = new ObjectInputStream(new ByteArrayInputStream(packet.data));
			int guiId = dis.readInt();
			int wolfID = dis.readInt();
			String wolfname = dis.readUTF();
			System.out.println("preRecieved");
			dis.close();

			if(guiId == 1)
			{
				EntityWolf2 wolf2 = (EntityWolf2)World.getEntityByID(wolfID);
				wolf2.setName(wolfname);
				System.out.println("nameRecieved");
				if(!entityplayer.capabilities.isCreativeMode)
				{
					entityplayer.inventory.consumeInventoryItem(ModBreeds.Dogtag.itemID);
					System.out.println("ItemConsumed");
				}
				else
				{
					System.out.println("PlayerIsInCreativeMode");
				}

			}
			if(guiId == 2)
			{
				EntityWolf3 wolf3 = (EntityWolf3)World.getEntityByID(wolfID);
				wolf3.setName(wolfname);
				System.out.println("nameRecieved");
				if(!entityplayer.capabilities.isCreativeMode)
				{
					entityplayer.inventory.consumeInventoryItem(ModBreeds.Dogtag.itemID);
					System.out.println("ItemConsumed");
				}
				else
				{
					System.out.println("PlayerIsInCreativeMode");
				}

			}
			if(guiId == 3)
			{
				EntityWolf4 wolf4 = (EntityWolf4)World.getEntityByID(wolfID);
				wolf4.setName(wolfname);
				System.out.println("nameRecieved");
				if(!entityplayer.capabilities.isCreativeMode)
				{
					entityplayer.inventory.consumeInventoryItem(ModBreeds.Dogtag.itemID);
					System.out.println("ItemConsumed");
				}
				else
				{
					System.out.println("PlayerIsInCreativeMode");
				}

			}
			if(guiId == 4)
			{
				EntityWolf5 wolf5 = (EntityWolf5)World.getEntityByID(wolfID);
				wolf5.setName(wolfname);
				System.out.println("nameRecieved");
				if(!entityplayer.capabilities.isCreativeMode)
				{
					entityplayer.inventory.consumeInventoryItem(ModBreeds.Dogtag.itemID);
					System.out.println("ItemConsumed");
				}
				else
				{
					System.out.println("PlayerIsInCreativeMode");
				}

			}
			if(guiId == 5)
			{
				EntityWolf6 wolf6 = (EntityWolf6)World.getEntityByID(wolfID);
				wolf6.setName(wolfname);
				System.out.println("nameRecieved");
				if(!entityplayer.capabilities.isCreativeMode)
				{
					entityplayer.inventory.consumeInventoryItem(ModBreeds.Dogtag.itemID);
					System.out.println("ItemConsumed");
				}
				else
				{
					System.out.println("PlayerIsInCreativeMode");
				}

			}
		}

		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("nameRecievingFailed");
		}

	}


}