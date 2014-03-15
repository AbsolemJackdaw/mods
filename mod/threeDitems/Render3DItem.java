package threeDitems;

import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class Render3DItem {

	public static Render3DItem inst = new Render3DItem();

	public Render3DItem(){
		inst = this;
	}
	public void add3DItem(Item item, IItemRenderer renderer){
		MinecraftForgeClient.registerItemRenderer(item.itemID, renderer);
	}
}
