//package vanilla3DArmor;
//
//import net.minecraft.item.EnumArmorMaterial;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemArmor;
//import net.minecraft.item.ItemStack;
//import cpw.mods.fml.common.Mod;
//import cpw.mods.fml.common.Mod.EventHandler;
//import cpw.mods.fml.common.SidedProxy;
//import cpw.mods.fml.common.event.FMLInitializationEvent;
//import cpw.mods.fml.common.network.NetworkMod;
//import cpw.mods.fml.common.registry.GameRegistry;
//import cpw.mods.fml.common.registry.LanguageRegistry;
//
//@Mod(modid="Vanilla3DArmor", name="Vanilla3DArmor", version="1")
//@NetworkMod(clientSideRequired=true, serverSideRequired=false)
//
//public class mod_VanillaArmor3D {
//
//	@SidedProxy(serverSide="vanilla3DArmor.CommonProxy", clientSide="vanilla3DArmor.ClientProxy")
//	public static CommonProxy proxy;
//
//	public static Item helmetD,chestD,legsD,bootsD,
//	helmetI,chestI,legsI,bootsI,
//	helmetG,chestG,legsG,bootsG;
//
//	int itemID = 8800;
//	
//	private String[][] recipePatterns;
//	private Object[][] recipeItems;
//	
//	@EventHandler
//	public void load(FMLInitializationEvent e)
//	{
//		helmetI = new CustomArmor(itemID++, EnumArmorMaterial.IRON, 2, 0).setUnlocalizedName("vanilla3dArmor.iron_helmet");
//		chestI = new CustomArmor(itemID++, EnumArmorMaterial.IRON, 2, 1).setUnlocalizedName("vanilla3dArmor.iron_chestplate");
//		legsI = new CustomArmor(itemID++, EnumArmorMaterial.IRON, 2, 2).setUnlocalizedName("vanilla3dArmor.iron_leggings");
//		bootsI = new CustomArmor(itemID++, EnumArmorMaterial.IRON, 2, 3).setUnlocalizedName("vanilla3dArmor.iron_boots");
//
//		helmetD = (new CustomArmor(itemID++, EnumArmorMaterial.DIAMOND, 3, 0)).setUnlocalizedName("vanilla3dArmor.diamond_helmet");
//		chestD = (new CustomArmor(itemID++, EnumArmorMaterial.DIAMOND, 3, 1)).setUnlocalizedName("vanilla3dArmor.diamond_chestplate");
//		legsD = (new CustomArmor(itemID++, EnumArmorMaterial.DIAMOND, 3, 2)).setUnlocalizedName("vanilla3dArmor.diamond_leggings");
//		bootsD = (new CustomArmor(itemID++, EnumArmorMaterial.DIAMOND, 3, 3)).setUnlocalizedName("vanilla3dArmor.diamond_boots");
//
//		helmetG = (new CustomArmor(itemID++, EnumArmorMaterial.GOLD, 4, 0)).setUnlocalizedName("vanilla3dArmor.gold_helmet");
//		chestG = (new CustomArmor(itemID++, EnumArmorMaterial.GOLD, 4, 1)).setUnlocalizedName("vanilla3dArmor.gold_chestplate");
//		legsG = (new CustomArmor(itemID++, EnumArmorMaterial.GOLD, 4, 2)).setUnlocalizedName("vanilla3dArmor.gold_leggings");
//		bootsG = (new CustomArmor(itemID++, EnumArmorMaterial.GOLD, 4, 3)).setUnlocalizedName("vanilla3dArmor.gold_boots");
//		
//		LanguageRegistry.addName(helmetD, "3D Diamond Helmet");
//		LanguageRegistry.addName(chestD, "3D Diamond ChestPlate");
//		LanguageRegistry.addName(legsD, "3D Diamond Legs");
//		LanguageRegistry.addName(bootsD, "3D Diamond Boots");
//		
//		
//		recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
//		recipeItems = new Object[][]{{Item.diamond, Item.ingotIron, Item.ingotGold}, {helmetD, helmetI, helmetG},
//				{chestD, chestI, chestG}, {legsD, legsI, legsG},
//				{bootsD, bootsI, bootsG}};
//
//		for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
//			Object var3 = this.recipeItems[0][var2];
//
//			for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
//				Item var5 = (Item) this.recipeItems[var4 + 1][var2];
//				GameRegistry.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], 'X', var3});
//			}
//		}
//
//	}	
//}
