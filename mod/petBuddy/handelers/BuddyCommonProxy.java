package petBuddy.handelers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class BuddyCommonProxy {

	private static int originalGuiScale;

	public int getGuiScale(){
		return originalGuiScale;
	}
	
	public void render() {}
	
	public void openGui(int id, EntityPlayer player, String name, int entityID, boolean creative, Item item){}
	
}