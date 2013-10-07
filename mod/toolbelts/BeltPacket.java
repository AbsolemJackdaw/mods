package toolbelts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.WorldServer;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BeltPacket {


	public static void sendServerPacket(EntityPlayer player){


		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		try {
			out.writeInt(PacketHandler.SMP_SYNC_BELT);
			out.writeUTF(player.username);

			for(int i = 0; i < player.inventory.mainInventory.length; i++)
				Packet.writeItemStack(player.inventory.mainInventory[i], out);

			out.writeInt(player.inventory.currentItem);
			
			if(!player.worldObj.isRemote){
				((WorldServer)player.worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(player,
						new Packet250CustomPayload("ToolBelt", out.toByteArray()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
