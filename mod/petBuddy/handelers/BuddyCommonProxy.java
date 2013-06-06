package petBuddy.handelers;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import petBuddy.entity.EntityBuddy;
import petBuddy.root.BuddyBase;
import cpw.mods.fml.common.FMLLog;

public class BuddyCommonProxy {

	public void render() {

	}
	public void openGui(int id, EntityPlayer player, String name)
	{

	}

	private int guiID = 3;
	public int getGuiId(){
		return guiID;
	}

	public void setGuiId(int guiId){
		guiID = guiId;
		FMLLog.getLogger().info("" + guiID);
	}

	public NBTTagCompound getBuddyData(String username) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).hasKey("Pet_Buddy_Player:"+username)){
					return player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getCompoundTag("Pet_Buddy_Player:"+username);
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setCompoundTag("Pet_Buddy_Player:"+username, new NBTTagCompound());
					return new NBTTagCompound();
				}
			}
		}
		return new NBTTagCompound();
	}

	public void setBuddyData(String username, EntityBuddy buddy) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("Pet_Buddy_Player:"+username, buddy.getGuiId());
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setCompoundTag("Pet_Buddy_Player:"+username, new NBTTagCompound());
				}
			}
		}
	}
}
