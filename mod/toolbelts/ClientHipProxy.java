package toolbelts;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientHipProxy extends CommonHipProxy {


	@SideOnly(Side.CLIENT)
	public static void renderHandler() {
		MinecraftForge.EVENT_BUS.register(new RenderHipItemsOnPlayer());
	}
}
