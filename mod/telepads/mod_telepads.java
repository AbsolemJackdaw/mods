package telepads;

import modUpdateChecked.OnPlayerLogin;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = mod_telepads.modID, name = mod_telepads.modName, version = mod_telepads.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"telePads"}, packetHandler = TelePadsTeleportHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"telePads"}, packetHandler = TelePadsTeleportHandler.class))

public class mod_telepads {

	protected static final String version = "1.6.4 v1";
	protected static final String modID = "telepads";
	protected static final String modName = "Teleportation Pads";
	
	public static BlockTelepad telepad;
	
	public static ItemPadLocations padLocator;
	
	@SidedProxy(serverSide = "telepads.SProxy", clientSide = "telepads.CLProxy")
	public static SProxy proxy;
	public static mod_telepads instance;
	
	
	@EventHandler
	public void load(FMLInitializationEvent evt){
		instance = this;
		
		telepad = (BlockTelepad) new BlockTelepad(412, Material.wood).setUnlocalizedName("telepad").setLightValue(0.2f).setCreativeTab(CreativeTabs.tabTransport).setBlockUnbreakable();
		
		padLocator = (ItemPadLocations) new ItemPadLocations(7520).setUnlocalizedName("padLocator").setMaxStackSize(1).setFull3D();
		
		GameRegistry.registerBlock(telepad, "TelePad");
		
		
		proxy.registerTileEntity();
		proxy.registerItemRenderer();
		
		NetworkRegistry.instance().registerGuiHandler(this, new TelePadGuiHandler());

		LanguageRegistry.addName(telepad, "TelePad");
		LanguageRegistry.addName(padLocator, "Register With TelePadLocations");
		
		proxy.registerSound();
		
//		MinecraftForge.EVENT_BUS.register(new sndmngr());
		
		GameRegistry.addRecipe(new ItemStack(telepad,2),new Object[] {"GGG", "RER", "RIR", 
			'G', Block.glass, 'R', Item.redstone, 'E', Item.enderPearl, 'I', Block.blockIron });

		GameRegistry.registerPlayerTracker(new OnPlayerLogin(version, modName));

	}

}
