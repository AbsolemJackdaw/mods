package toolbelts;

import modUpdateChecked.OnPlayerLogin;
import net.minecraftforge.common.MinecraftForge;
import toolbelts.handlers.CommonTickHandler;
import toolbelts.handlers.HandleLogin;
import toolbelts.handlers.packets.PacketHandler;
import toolbelts.proxy.ClientHipProxy;
import toolbelts.proxy.CommonHipProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "ToolBelt", name = "ToolBelts", version = "1.6.4 v4")
@NetworkMod(clientSideRequired = true, serverSideRequired = true,

clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"ToolBelt"}, packetHandler = PacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"ToolBelt"}, packetHandler = PacketHandler.class))

public class mod_hipItems {

	private static final String name = "ToolBelts";
	private static final String version = "1.6.4 v4";
			
	public static final int BELTID_MAX = 6; //used in tickhandler to hop back to default value
	public static final int BELTID_MIN = 0; //default value
	
	@SidedProxy(serverSide = "toolbelts.proxy.CommonHipProxy", clientSide = "toolbelts.proxy.ClientHipProxy")
	public static CommonHipProxy proxy;

	@EventHandler
	public void load (FMLInitializationEvent e){
		
		proxy.registerKeyHandler();
		
		MinecraftForge.EVENT_BUS.register(new EntityCostruction());
		
		GameRegistry.registerPlayerTracker(new HandleLogin());
		GameRegistry.registerPlayerTracker(new OnPlayerLogin(version, name));
		
		ClientHipProxy.renderHandler();

		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);

	}
}
