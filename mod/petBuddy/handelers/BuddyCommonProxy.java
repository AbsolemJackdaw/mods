package petBuddy.handelers;

import net.minecraft.entity.player.EntityPlayer;
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
