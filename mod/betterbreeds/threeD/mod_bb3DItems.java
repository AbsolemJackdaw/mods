package betterbreeds.threeD;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "3dBreedsItems", name = "3DBreedsItems", version = "2", dependencies="required-after:3dMod;required-after:Breeds")
// dependencies assures it's loaded AFTER 3d items and your mod which provides the items
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class mod_bb3DItems {

	@SidedProxy(clientSide = "betterbreeds.threeD.clp", serverSide = "betterbreeds.threeD.cmp")
	public static cmp proxy;
	//all you need are your proxies !
	
	@EventHandler
	public void load(FMLInitializationEvent e){
		FMLLog.getLogger().info("[MODBREEDS]3DVanilla Items Detected. Rendering 3D Items.");
		proxy.render();
	}

}
