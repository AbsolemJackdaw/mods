package petBuddy.handelers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import petBuddy.root.BuddyBase;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class BuddyPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {	

		if(packet.channel.equals("buddyPet"))
		{
			this.handlePacket(packet, player);
		}
	}

	private void handlePacket(Packet250CustomPayload packet, Player player) {
		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;
//		Random rand = new Random();
		
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {

			int guiId = dis.readInt();

			BuddyBase myBuddy = null;

			List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABB(EntityLiving.class, p.boundingBox.expand(10, 10, 10));
			if (entl != null && entl.size() > 0) {
				for (EntityLiving el : entl) {
					if (el != null && el instanceof BuddyBase){
						if(((BuddyBase)el).getOwnerName().equals(p.username)){
							myBuddy = (BuddyBase)el;
							PetBuddyMain.proxy.setGuiId(guiId);
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}