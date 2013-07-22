package petBuddy.handelers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

public class PetSpawner implements IPlayerTracker{

	EntityBuddy bud;
	float color1;
	float color2;
	float color3;
	
	float dragon_color1;
	float dragon_color2;
	float dragon_color3;
	
	String name = "null";
	String skin = "no skin";

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		player.addChatMessage(player.username + " found his Buddy");
		spawnPet(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {

		PetBuddyMain.proxy.setLoginPet(player.username, bud);
		bud.setDead();
		NBTTagCompound nbt = PetBuddyMain.proxy.getLoginPet(player.username);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		try{
			PetBuddyMain.proxy.setLoginPet(player.username, bud);
			NBTTagCompound nbt = PetBuddyMain.proxy.getLoginPet(player.username);
			FMLLog.getLogger().info("Changed Dimensions. Step 1: Saving Pet.");
		}
		finally {
			spawnPet(player);
			FMLLog.getLogger().info("Changed Dimensions. Step 2: Spawning Pet.");
		}
		if(player.worldObj.provider.dimensionId == -1){
			player.addStat(PetBuddyMain.portalNether, 1);
		}if(player.worldObj.provider.dimensionId == 1){
			player.addStat(PetBuddyMain.endPortal, 1);
		}
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		try{
			PetBuddyMain.proxy.setLoginPet(player.username, bud);
			NBTTagCompound nbt = PetBuddyMain.proxy.getLoginPet(player.username);
			FMLLog.getLogger().info("Player Respawned. Step 1: Saving Pet.");
		}
		finally {
			spawnPet(player);
			FMLLog.getLogger().info("Player Respawned. Step 2: Spawning Pet.");
		}
	}

	private void spawnPet(EntityPlayer player){

		EntityBuddy buddy = new EntityBuddy(player.worldObj, player);
		for(int i = 2; i > 0; i--){
			if(player.worldObj.getBlockId((int)player.posX+i, (int)player.posY, (int)player.posZ+i) == 0){
				buddy.setLocationAndAngles(player.posX+i, player.posY, player.posZ+i, 0.0F, 0.0F);
				break;
			}else if(player.worldObj.getBlockId((int)player.posX-i, (int)player.posY, (int)player.posZ+i) == 0){
				buddy.setLocationAndAngles(player.posX-i, player.posY, player.posZ+i, 0.0F, 0.0F);
				break;
			}else if(player.worldObj.getBlockId((int)player.posX+i, (int)player.posY, (int)player.posZ-i) == 0){
				buddy.setLocationAndAngles(player.posX+i, player.posY, player.posZ-i, 0.0F, 0.0F);
				break;
			}else if(player.worldObj.getBlockId((int)player.posX-i, (int)player.posY, (int)player.posZ-i) == 0){
				buddy.setLocationAndAngles(player.posX-i, player.posY, player.posZ-i, 0.0F, 0.0F);
				break;
			}	
			else{
				buddy.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);	
			}
		}

		NBTTagCompound nbt = PetBuddyMain.proxy.getLoginPet(player.username);

		int loginid = nbt.getInteger("pet_login_id:"+player.username);

		if(nbt.hasKey("pet_color1:"+player.username) && nbt.hasKey("pet_color2:"+player.username) && nbt.hasKey("pet_color3:"+player.username)){
			color1 = nbt.getFloat("pet_color1:"+player.username);
			color2 = nbt.getFloat("pet_color2:"+player.username);
			color3 = nbt.getFloat("pet_color3:"+player.username);
		}
		if(nbt.hasKey("pet_dragon_color1:"+player.username) && nbt.hasKey("pet_dragon_color2:"+player.username) && nbt.hasKey("pet_dragon_color3:"+player.username)){
			dragon_color1 = nbt.getFloat("pet_dragon_color1:"+player.username);
			dragon_color2 = nbt.getFloat("pet_dragon_color2:"+player.username);
			dragon_color3 = nbt.getFloat("pet_dragon_color3:"+player.username);
		}
		if(nbt.hasKey("pet_name:"+player.username)){
			name = nbt.getString("pet_name:"+player.username).equals("null") ||
					nbt.getString("pet_name:"+player.username).equals("") ? player.username+"'s Buddy" :
						nbt.getString("pet_name:"+player.username);
		}
		if(nbt.hasKey("pet_skin:"+player.username)){
			skin = nbt.getString("pet_skin:"+player.username).equals("null") ||
					nbt.getString("pet_skin:"+player.username).toLowerCase().equals("i")||
					nbt.getString("pet_skin:"+player.username).toLowerCase().equals("me")? player.username :
						nbt.getString("pet_skin:"+player.username);
		}
		PetBuddyMain.proxy.setName(name);
		PetBuddyMain.proxy.setSkinName(skin);
		PetBuddyMain.proxy.setGuiId(loginid);
		PetBuddyMain.proxy.setColor(color1,color2,color3);
		PetBuddyMain.proxy.setDragonColor(dragon_color1,dragon_color2,dragon_color3);

		buddy.writeEntityToNBT(nbt);

		try{
			//if(!player.worldObj.isRemote){
				player.worldObj.spawnEntityInWorld(buddy);
			//}
		}finally{
			bud = (EntityBuddy) player.worldObj.getEntityByID(buddy.entityId);		
			PetBuddyMain.playersWithPets.put(buddy.getOwnerName(), buddy.entityId);
		}
	}
}
