package toolbelts.proxy;

import net.minecraftforge.common.MinecraftForge;
import toolbelts.handlers.CycleBeltKeyPressed;
import toolbelts.render.RenderHipItemsOnPlayer;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientHipProxy extends CommonHipProxy {


	@SideOnly(Side.CLIENT)
	public static void renderHandler() {
		MinecraftForge.EVENT_BUS.register(new RenderHipItemsOnPlayer());
	}

	@Override
	public void registerKeyHandler() {
		KeyBindingRegistry.registerKeyBinding(new CycleBeltKeyPressed());
	}
}
