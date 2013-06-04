package petBuddy.handelers;

import net.minecraft.entity.player.EntityPlayer;
import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import cpw.mods.fml.common.IPlayerTracker;

public class PetSpawner implements IPlayerTracker{

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		player.sendChatToPlayer(player.username + " just connected");
		if(player.worldObj != null){
			EntityBuddy buddy = new EntityBuddy(player.worldObj, player);
			buddy.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);
			player.worldObj.spawnEntityInWorld(buddy);
//			PetBuddyMain.proxy.openGui(0, player, player.username);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {		

	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {

	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {

	}
}
