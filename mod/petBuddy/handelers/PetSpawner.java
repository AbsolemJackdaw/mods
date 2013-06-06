package petBuddy.handelers;

import gravestone.mod_Gravestone;
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
		NBTTagCompound nbt = PetBuddyMain.proxy.getLoginPet(player.username);
		int loginid = nbt.getInteger("pet_login_id:"+player.username);
		PetBuddyMain.proxy.setGuiId(loginid);
		FMLLog.getLogger().info("login pet : " + loginid);
		buddy.writeEntityToNBT(nbt);
		bud = buddy;
		if(!player.worldObj.isRemote){
			player.worldObj.spawnEntityInWorld(buddy);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		
		PetBuddyMain.proxy.setLoginPet(player.username, bud);
		NBTTagCompound nbt = PetBuddyMain.proxy.getLoginPet(player.username);
		
		FMLLog.getLogger().info("pet login id saved. login id = " + nbt.getInteger("pet_login_id:"+player.username));
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
	}
	
	@Override
	public void onPlayerRespawn(EntityPlayer player) {
	}
}
