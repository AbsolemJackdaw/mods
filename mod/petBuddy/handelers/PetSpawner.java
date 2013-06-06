package petBuddy.handelers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

public class PetSpawner implements IPlayerTracker{

	EntityBuddy bud;
	@Override
	public void onPlayerLogin(EntityPlayer player) {
		player.sendChatToPlayer(player.username + " found his Buddy");
		EntityBuddy buddy = new EntityBuddy(player.worldObj, player);
		buddy.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);
		if(!player.worldObj.isRemote){
			player.worldObj.spawnEntityInWorld(buddy);
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
