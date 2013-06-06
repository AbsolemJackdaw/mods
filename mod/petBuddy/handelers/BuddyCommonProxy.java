package petBuddy.handelers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import petBuddy.entity.EntityBuddy;

public class BuddyCommonProxy {

	public void render() {}
	public void openGui(int id, EntityPlayer player, String name){}

	private int guiID = 3;
	public int getGuiId(){
		return guiID;
	}

	public void setGuiId(int guiId){
		guiID = guiId;
	}

	public NBTTagCompound getLoginPet(String username) {
		NBTTagCompound PETNBT = new NBTTagCompound();
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).hasKey("pet_login_id:"+username)) {

					PETNBT.setInteger("pet_login_id:"+username, player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getInteger("pet_login_id:"+username));
					return PETNBT;
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username,3);
					PETNBT.setInteger("pet_login_id:"+username, 3);
					return PETNBT;
				}
			}
		}else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if (player.getEntityData().hasKey(player.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).hasKey("pet_login_id:"+username)) {
					PETNBT.setInteger("pet_login_id:"+username, player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getInteger("pet_login_id:"+username));
					return PETNBT;
				} else {
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username,3);
					PETNBT.setInteger("pet_login_id:"+username, 3);
					return PETNBT;				
				}
			}
		}
		return PETNBT;
	}

	public void setLoginPet(String username, EntityBuddy buddy) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username, buddy.getGuiId());
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username, 3);
				}
			}
		}else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if (player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username, buddy.getGuiId());
				} else {
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username,3);
				}
			}
		}
	}
}