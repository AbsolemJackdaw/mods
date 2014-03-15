package decorativeCollectibles;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import decorativeCollectibles.block.BlockGoblet;
import decorativeCollectibles.block.BlockGoldenPig;
import decorativeCollectibles.crafting.CraftingRecipes;
import decorativeCollectibles.item.ItemGoblet;
import decorativeCollectibles.item.ItemGoldenPig;
import decorativeCollectibles.proxy.SCollectiblesProxy;


@Mod(modid = mod_collectibles.modID, name = mod_collectibles.modName, version = mod_collectibles.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class mod_collectibles {

	protected static final String version = "Beta";
	protected static final String modID = "decorativeCollectibles";
	protected static final String modName = "Decorative Collectibles";

	public static final int GENERAL_META = 7;

	public static String[] namesGoblet = {"Golden Goblet", "Diamond Goblet", "Emerald Goblet", "Lapis Goblet","DragonStone Goblet",
		"Lava Goblet", "Water Goblet"};
	
	public static String[] namesPigs= {"Golden Pig", "Diamond Pig", "Emerald Pig", "Lapis Pig","DragonStone Pig",
		"Lava Pig", "Water Pig"};
	
	public static ItemGoblet goblet;
	public static BlockGoblet gobletBlock;
	
	public static ItemGoldenPig goldenPigs;
	public static BlockGoldenPig goldenPigsBlock;

	@SidedProxy(serverSide = "decorativeCollectibles.proxy.SCollectiblesProxy", clientSide = "decorativeCollectibles.proxy.CLCollectiblesProxy")
	public static SCollectiblesProxy proxy;

	@EventHandler
	public void load(FMLInitializationEvent evt){

		gobletBlock = (BlockGoblet) new BlockGoblet(478).setUnlocalizedName("gobletBlock").setLightValue(0.3f);
		goblet = (ItemGoblet) new ItemGoblet(478-256).setUnlocalizedName("goldenGoblets");
		
		goldenPigsBlock = (BlockGoldenPig) new BlockGoldenPig(479).setUnlocalizedName("goldenPigsBlock").setLightValue(0.3f);
		goldenPigs = (ItemGoldenPig) new ItemGoldenPig(479-256).setUnlocalizedName("goldenPigs");
		
		
		GameRegistry.registerBlock(gobletBlock, ItemGoblet.class, "Goblet Block");
		GameRegistry.registerBlock(goldenPigsBlock, ItemGoldenPig.class, "Golden Pigs");

		for(int i = 0; i < GENERAL_META; i++){
			LanguageRegistry.addName(new ItemStack(gobletBlock, 1, i), namesGoblet[i]);
			LanguageRegistry.addName(new ItemStack(goblet, 1, i), namesGoblet[i]);
			
			LanguageRegistry.addName(new ItemStack(goldenPigsBlock, 1, i), namesPigs[i]);
			LanguageRegistry.addName(new ItemStack(goldenPigs, 1, i), namesPigs[i]);
		}

		CraftingRecipes.loadRecipes();
		
		proxy.registerRenderers();
	}

}
