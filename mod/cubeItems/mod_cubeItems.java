package cubeItems;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cubeItems.proxy.CubeS;

@Mod(modid="cubeItems", name="cube items", version=" Beta Launch")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class mod_cubeItems
{

	@SidedProxy(serverSide="cubeItems.proxy.CubeS", clientSide="cubeItems.proxy.CubeCL")
	public static CubeS proxy;
	
	public static mod_cubeItems inst;

	@EventHandler
	public void load(FMLInitializationEvent e)
	{
		inst = this;				
		proxy.render();
	}

}
