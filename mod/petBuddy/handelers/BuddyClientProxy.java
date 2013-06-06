package petBuddy.handelers;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import petBuddy.entity.EntityBuddy;
import petBuddy.entity.gui.PetInterface;
import petBuddy.root.BuddyBase;
import petBuddy.root.RenderBuddy;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;



public class BuddyClientProxy extends BuddyCommonProxy{

	public void render() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBuddy.class, new RenderBuddy(0.3f,0.4f));
	}

	public void openGui(int id, EntityPlayer player, String name)
	{
		Minecraft.getMinecraft().displayGuiScreen(new PetInterface(player, name));
	}

	private int guiID = 3;

	public int getGuiId(){
		return guiID;
	}

	public void setGuiId(int guiId){
		guiID = guiId;
	}


	@Override
	public NBTTagCompound getBuddyData(String username) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).hasKey("Buddy_Player:"+username)) {
					return player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getCompoundTag("Buddy_Player:"+username);
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setCompoundTag("Buddy_Player:"+username, new NBTTagCompound());
					return new NBTTagCompound();
				}
			}
		}else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if (player.getEntityData().hasKey(player.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).hasKey("Buddy_Player:"+username)) {
					return player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getCompoundTag("Buddy_Player:"+username);
				} else {
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setCompoundTag("Buddy_Player:"+username, new NBTTagCompound());
					return new NBTTagCompound();
				}
			}
		}
		return new NBTTagCompound();
	}

	@Override
	public void setBuddyData(String username, EntityBuddy buddy) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setCompoundTag("Buddy_Player:"+username, buddy.getEntityData());
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setCompoundTag("Buddy_Player:"+username, new NBTTagCompound());
				}
			}
		}else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if (player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setCompoundTag("Buddy_Player:"+username, buddy.getEntityData());
				} else {
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setCompoundTag("Buddy_Player:"+username, new NBTTagCompound());
				}
			}
		}
	}
}
