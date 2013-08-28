package threeDitems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="3d", name="3ditems", version="1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class mod_3d
{

	@SidedProxy(serverSide="threeDitems.cmp", clientSide="threeDitems.clp")
	public static cmp proxy;
	
	public static mod_3d inst;
	public boolean isRendering3D = false;

	@EventHandler
	public void preLoad(FMLPreInitializationEvent e){
		Config3D.instance.loadConfig(e.getSuggestedConfigurationFile());
	}
	@EventHandler
	public void load(FMLInitializationEvent e)
	{
		inst = this;
		proxy.render();
	}
}
