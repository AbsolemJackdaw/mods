package toolbelts.handlers.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.WorldServer;
import toolbelts.PlayerBeltTracker;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;

public class BeltPacket {


	public static void sendServerPacket(EntityPlayer player){

		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		try {
			out.writeInt(PacketHandler.SMP_SYNC_BELT);
			out.writeUTF(player.username);

			for(int i = 0; i < player.inventory.mainInventory.length; i++)
				Packet.writeItemStack(player.inventory.mainInventory[i], out);

			Packet.writeItemStack(player.inventory.getCurrentItem(), out);
			
			out.writeInt(PlayerBeltTracker.get(player).BeltID);
						
			if(!player.worldObj.isRemote){
				((WorldServer)player.worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(player,
						new Packet250CustomPayload("ToolBelt", out.toByteArray()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void sendServerBeltId(EntityPlayer player){

		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		try {
			out.writeInt(PacketHandler.SMP_SYNC_BELTID);
		
			out.writeInt(PlayerBeltTracker.get(player).BeltID);

			Packet packet = new Packet250CustomPayload("ToolBelt", out.toByteArray());
			PacketDispatcher.sendPacketToServer(packet);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
