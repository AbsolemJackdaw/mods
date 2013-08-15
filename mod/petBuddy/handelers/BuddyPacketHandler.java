package petBuddy.handelers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import petBuddy.root.BuddyBase;
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
			int secondID = dis.readInt();
			String buddyName = dis.readUTF();
			BuddyBase myBuddy = null;

			if(guiId == 1000){
				int c = secondID;
				String s = buddyName;
						PetBuddyMain.playersWithPets.put(s, c);
			}
			if(guiId == 100){
				EntityBuddy buddy = (EntityBuddy)world.getEntityByID(secondID);
				String name = buddyName.equals("null") || buddyName.equals("") ? buddy.getOwnerName()+"'s Buddy" :
					buddyName;
				buddy.setName(name);
				p.addStat(PetBuddyMain.pa.nameBuddy, 1);
				
			}if(guiId == 101){
				EntityBuddy buddy = (EntityBuddy)world.getEntityByID(secondID);
				String skin = buddyName.equals("null") || buddyName.equals("")|| buddyName.toLowerCase().equals("me") ||
						buddyName.toLowerCase().equals("i")? buddy.getOwnerName() :	buddyName;
				buddy.setSkinName(skin);
				if(!skin.equals(buddy.getOwnerName())){
					p.addStat(PetBuddyMain.pa.skin, 1);
				}
				
			}else if (guiId < 100){
				List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABB(EntityLiving.class, p.boundingBox.expand(10, 10, 10));
				if (entl != null && entl.size() > 0) {
					for (EntityLiving el : entl) {
						if (el != null && el instanceof BuddyBase){
							if(((BuddyBase)el).getOwnerName().equals(p.username)){
								myBuddy = (BuddyBase)el;
								myBuddy.setGuiId(secondID);
							}
						}
					}
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
