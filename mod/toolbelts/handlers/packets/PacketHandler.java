package toolbelts.handlers.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import toolbelts.PlayerBeltTracker;
import toolbelts.Utils;
import toolbelts.sets.BeltBase;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	public static final int SMP_SYNC_BELT = 1;
	public static final int SMP_SYNC_BELTID = 2;


	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals("ToolBelt")) {
			handlePackets(player, packet);
		}
	}

	private void handlePackets(Player player, Packet250CustomPayload packet){

		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {

			int guiId = dis.readInt();
			switch (guiId) {
			case SMP_SYNC_BELT:
				String otherPlayerName = dis.readUTF();
				EntityPlayer other = world.getPlayerEntityByName(otherPlayerName);

				for(int i = 0; i < other.inventory.mainInventory.length; i++)
					other.inventory.setInventorySlotContents(i, packet.readItemStack(dis));
				
				other.setCurrentItemOrArmor(0, packet.readItemStack(dis));

				PlayerBeltTracker.get(other).BeltID = dis.readInt();
				break;

			case SMP_SYNC_BELTID:
				int id = dis.readInt();
				PlayerBeltTracker.get(p).BeltID = id;
				p.addChatMessage("Set belt to " + Utils.beltIDToName(id));
				break;
			}
		}catch(Exception e){

		}
	}
}