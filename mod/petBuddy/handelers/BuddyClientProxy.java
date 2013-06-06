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
}
