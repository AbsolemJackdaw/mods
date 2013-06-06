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
		//		if(player.worldObj != null){
		EntityBuddy buddy = new EntityBuddy(player.worldObj, player);
		buddy.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);
		/*START*/
		//section to get the buddy data and spawn it when the player logs in. this prevents reseting the buddy.
		NBTTagCompound tag = PetBuddyMain.proxy.getBuddyData(player.username);
		FMLLog.getLogger().info(""+ tag);
		FMLLog.getLogger().info(""+ player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getCompoundTag("Pet_Buddy_Player:"+player.username));
		buddy.writeToNBT(tag);
		buddy.readFromNBT(tag);
		bud = buddy;
		/*END*/
		if(!player.worldObj.isRemote){
			player.worldObj.spawnEntityInWorld(buddy);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {		
		PetBuddyMain.proxy.setBuddyData(player.username, bud);
		FMLLog.getLogger().info(""+ bud.getEntityData());
		FMLLog.getLogger().info(""+ player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getCompoundTag("Pet_Buddy_Player:"+player.username));

	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {

	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {

	}
}
