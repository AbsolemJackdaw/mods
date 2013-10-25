package cubeItems;

import javax.imageio.spi.RegisterableService;

import modUpdateChecked.OnPlayerLogin;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cubeItems.proxy.CubeS;

@Mod(modid="cubeItems", name=mod_cubeItems.name, version=mod_cubeItems.version)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class mod_cubeItems
{

	protected static final String name = "Cube Items";
	protected static final String version = "Beta Launch";


	@SidedProxy(serverSide="cubeItems.proxy.CubeS", clientSide="cubeItems.proxy.CubeCL")
	public static CubeS proxy;

	public static mod_cubeItems inst;

	@EventHandler
	public void load(FMLInitializationEvent e)
	{
		
		GameRegistry.registerPlayerTracker(new OnPlayerLogin(version, name));

		inst = this;				
		proxy.render();
	}

}
