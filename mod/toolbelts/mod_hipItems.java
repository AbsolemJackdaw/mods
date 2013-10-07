package toolbelts;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "ToolBelt", name = "ToolBelts", version = "1.6.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = true,

clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"ToolBelt"}, packetHandler = PacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"ToolBelt"}, packetHandler = PacketHandler.class))

public class mod_hipItems {

	@SidedProxy(serverSide = "toolbelts.CommonHipProxy", clientSide = "toolbelts.ClientHipProxy")
	public static CommonHipProxy proxy;


	@EventHandler
	public void load (FMLInitializationEvent e){
		ClientHipProxy.renderHandler();

		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);

	}
}
