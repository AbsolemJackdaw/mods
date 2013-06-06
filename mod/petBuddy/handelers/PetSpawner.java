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
	String name = "null";
	@Override
	public void onPlayerLogin(EntityPlayer player) {
		player.sendChatToPlayer(player.username + " found his Buddy");
		EntityBuddy buddy = new EntityBuddy(player.worldObj, player);
		buddy.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);
		NBTTagCompound nbt = PetBuddyMain.proxy.getLoginPet(player.username);
		int loginid = nbt.getInteger("pet_login_id:"+player.username);
		if(nbt.hasKey("pet_color1:"+player.username) && nbt.hasKey("pet_color2:"+player.username) &&nbt.hasKey("pet_color3:"+player.username)){
			color1 = nbt.getFloat("pet_color1:"+player.username);
			color2 = nbt.getFloat("pet_color2:"+player.username);
			color3 = nbt.getFloat("pet_color3:"+player.username);
		}
		if(nbt.hasKey("pet_color1:"+player.username)){
			name = nbt.getString("pet_name:"+player.username);
		}
		PetBuddyMain.proxy.setName(name);
		PetBuddyMain.proxy.setGuiId(loginid);
		PetBuddyMain.proxy.setColor(color1,color2,color3);

		buddy.writeEntityToNBT(nbt);
		bud = buddy;
		if(!player.worldObj.isRemote){
			player.worldObj.spawnEntityInWorld(buddy);
		}

		FMLLog.getLogger().info("pet initiated. login id = " + nbt.getInteger("pet_login_id:"+player.username) +
				" , sheep colors in RGB : "+ color1 +","+color2 +","+color3);

	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {

		PetBuddyMain.proxy.setLoginPet(player.username, bud);
		NBTTagCompound nbt = PetBuddyMain.proxy.getLoginPet(player.username);

		FMLLog.getLogger().info("pet saved. login id = " + nbt.getInteger("pet_login_id:"+player.username) +
				" , sheep colors in RGB : "+ color1 +","+color2+","+ color3);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
	}
}
