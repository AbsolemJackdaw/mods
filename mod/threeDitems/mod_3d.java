package threeDitems;

import threeDitems.proxy.cmp;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="3dMod", name="Vanilla3DItems", version="1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class mod_3d
{

	@SidedProxy(serverSide="threeDitems.proxy.cmp", clientSide="threeDitems.proxy.clp")
	public static cmp proxy;

	public static mod_3d inst;
	public boolean isRendering3D = true;
	public boolean showHud = false;

	@EventHandler
	public void load(FMLInitializationEvent e)
	{
		inst = this;

		//		proxy.renderBlocks();

		//		proxy.render();
	}

	@EventHandler
	public void preLoad(FMLPreInitializationEvent e){
		//		Config3D.instance.loadConfig(e.getSuggestedConfigurationFile());
	}

}
