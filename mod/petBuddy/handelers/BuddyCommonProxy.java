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
}