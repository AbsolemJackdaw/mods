package petBuddy.handelers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import petBuddy.BuddyUtils;
import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
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

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {

			int guiId = dis.readInt();
			int secondID;
			String buddyName;
			EntityBuddy buddy;

			switch(guiId){

			case 100:
				secondID = dis.readInt();
				buddyName = dis.readUTF();

				buddy = (EntityBuddy)world.getEntityByID(secondID);

				String name = buddyName.equals("null") || buddyName.equals("") ? buddy.getOwnerName()+"'s Buddy" :
					buddyName;
				buddy.setName(name);
				p.addStat(PetBuddyMain.pa.nameBuddy, 1);
				break;

			case 101:
				secondID = dis.readInt();
				buddyName = dis.readUTF();

				buddy = (EntityBuddy)world.getEntityByID(secondID);

				String skin = buddyName.equals("null") || buddyName.equals("")|| buddyName.toLowerCase().equals("me") ||
						buddyName.toLowerCase().equals("i")? buddy.getOwnerName() :	buddyName;
						buddy.setSkinName(skin);
						if(!skin.equals(buddy.getOwnerName())){
							p.addStat(PetBuddyMain.pa.skin, 1);
						}
						break;

			case 256:
				int petToID = dis.readInt();
				if(p.getCurrentEquippedItem() != null){
					ItemStack stack = p.getCurrentEquippedItem();
					if(!world.isRemote){
						p.addChatMessage("Your "+stack.getDisplayName()+ " turned into a " + BuddyUtils.IDToName(petToID) + " Statue !");
					}
					stack.stackTagCompound.setInteger("guiID", petToID);	
				}

				break;
				
			default :
				secondID = dis.readInt();
				buddyName = dis.readUTF();

				buddy = (EntityBuddy)world.getEntityByID(secondID);

				buddy.setGuiId(guiId);
				
				if(p.capabilities.isCreativeMode && !world.isRemote){
					p.addChatMessage("Set buddy to " + BuddyUtils.IDToName(guiId));
				}
				p.addStat(PetBuddyMain.pa.nameBuddy, 1);
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
