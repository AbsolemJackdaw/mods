package telepads;

import rpgInventory.handlers.packets.ServerPacketHandler;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Telepads.modID, name = Telepads.modName, version = Telepads.version)
public class Telepads {

	protected static final String version = "1.6.4 v5";
	protected static final String modID = "telepads";
	protected static final String modName = "Teleportation Pads";
	
	public static BlockTelepad telepad;
	
	public static ItemPadLocations padLocator;
	
	@SidedProxy(serverSide = "telepads.SProxy", clientSide = "telepads.CLProxy")
	public static SProxy proxy;
	public static Telepads instance;
	
	public static FMLEventChannel Channel;

	
	@EventHandler
	public void load(FMLInitializationEvent evt){
		instance = this;
		
		telepad = (BlockTelepad) new BlockTelepad( Material.wood).setBlockName("telepad").setLightLevel(0.2f).setCreativeTab(CreativeTabs.tabTransport).setBlockUnbreakable();
		
		padLocator = (ItemPadLocations) new ItemPadLocations().setUnlocalizedName("padLocator").setMaxStackSize(1).setFull3D();
		
		GameRegistry.registerBlock(telepad, "TelePad");
		
		proxy.registerTileEntity();
		GameRegistry.registerTileEntity(TETelepad.class, "TETelepad");
		proxy.registerItemRenderer();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new TelePadGuiHandler());

		LanguageRegistry.addName(telepad, "TelePad");
		LanguageRegistry.addName(padLocator, "Register With TelePadLocations");
		
		proxy.registerSound();
				
		GameRegistry.addRecipe(new ItemStack(telepad,2),new Object[] {"GGG", "RER", "RIR", 
			'G', Blocks.glass, 'R', Items.redstone, 'E', Items.ender_pearl, 'I', Blocks.iron_block});

		Channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("TelePadsPacket");
		Channel.register(new ServerPacketHandler());
//		GameRegistry.registerPlayerTracker(new OnPlayerLogin(version, modName));

	}

}
