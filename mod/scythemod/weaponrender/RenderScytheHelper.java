package scythemod.weaponrender;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderScytheHelper {
	
	public static final RenderScytheHelper inst = new RenderScytheHelper();
	
	public void addScythe(Item item, String texture)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new ScytheRenderer(new deathScythe(), texture));
	}

}
