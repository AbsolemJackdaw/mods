package berryBushes;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import berryBushes.proxy.Bsproxy;
import berryBushes.te.BushTE;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid = "BerryBushes", name = "Berry Bushes", version = "1.0")

@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class Base {

	public static Item berry;
	public static Item berryII;
	public static Item berryIII;
	public static Item berryIV;

	public static Block bushI;
	public static Block bushII;
	public static Block bushIII;
	public static Block bushIV;

	public static Block berryCrop;

	
	@SidedProxy(serverSide = "berryBushes.proxy.Bsproxy", clientSide = "berryBushes.proxy.Bcproxy")
	public static Bsproxy proxy;
	
	@EventHandler
	public void load (FMLPreInitializationEvent e){

		BerryConfig.instance.loadConfig(e.getSuggestedConfigurationFile());

	}

	@EventHandler
	public void load (FMLInitializationEvent e){

		GameRegistry.registerTileEntity(BushTE.class, "bushtileentity");

		berry    = new Berry(BerryConfig.instance.berryI, 1, 0.5f,0).setUnlocalizedName("berryI");
		berryII  = new Berry(BerryConfig.instance.berryII, 3, 0.5f,1).setUnlocalizedName("berryII");
		berryIII = new Berry(BerryConfig.instance.berryIII, 4, 0.5f,2).setUnlocalizedName("berryIII");
		berryIV  = new Berry(BerryConfig.instance.berryIV, 6, 0.5f,3).setUnlocalizedName("berryIV");

		bushI = new Bush(745,0).setUnlocalizedName("BerryBush").setCreativeTab(CreativeTabs.tabBlock);
		bushII = new Bush(746,1).setUnlocalizedName("BerryBushi").setCreativeTab(CreativeTabs.tabBlock);
		bushIII = new Bush(747,2).setUnlocalizedName("BerryBushii").setCreativeTab(CreativeTabs.tabBlock);
		bushIV = new Bush(748,3).setUnlocalizedName("BerryBushiii").setCreativeTab(CreativeTabs.tabBlock);
		
		berryCrop = new BerryCrops(749).setUnlocalizedName("berryCrops").setHardness(0.5f).setResistance(0.2f);


		LanguageRegistry.addName(berry, "Juicy Berry");
		LanguageRegistry.addName(berryII, "Sweet Berry");
		LanguageRegistry.addName(berryIII, "Luscious Berry");
		LanguageRegistry.addName(berryIV, "Nectarous Berry");

		LanguageRegistry.addName(bushI, "Small BerryBush");
		LanguageRegistry.addName(bushII, "BerryBush");
		LanguageRegistry.addName(bushIII, "Medium BerryBush");
		LanguageRegistry.addName(bushIV, "Bigger BerryBush");

		GameRegistry.registerBlock(bushI, "berryBush");
		GameRegistry.registerBlock(bushII, "berryBushII");
		GameRegistry.registerBlock(bushIII, "berryBushIII");
		GameRegistry.registerBlock(bushIV, "berryBushIV");

		GameRegistry.registerWorldGenerator(new BushGen());
		
		proxy.init();
	}
}