//package tutorial;
//
//import net.minecraft.item.EnumArmorMaterial;
//import net.minecraft.item.Item;
//import cpw.mods.fml.common.Mod;
//import cpw.mods.fml.common.Mod.EventHandler;
//import cpw.mods.fml.common.SidedProxy;
//import cpw.mods.fml.common.event.FMLInitializationEvent;
//import cpw.mods.fml.common.network.NetworkMod;
//import cpw.mods.fml.common.registry.LanguageRegistry;
//
//@Mod(modid="ArmorTutorial", name="ArmorTutorial", version="tutorial")
//@NetworkMod(clientSideRequired=true, serverSideRequired=false)
//public class mod_ArmorTutorial {
//
//	@SidedProxy(serverSide="tutorial.CommonProxy", clientSide="tutorial.ClientProxy")
//	public static CommonProxy proxy;
//
//	public static Item chest;
//	public static Item legs;
//	public static Item boots;
//	public static Item helmet;
//
//	@EventHandler
//	public void load(FMLInitializationEvent e)
//	{
//		helmet = new CustomArmor(8469, EnumArmorMaterial.IRON, 4, 0).setUnlocalizedName("a.iron_helmet");
//		chest   = new CustomArmor(8470, EnumArmorMaterial.IRON, 4, 1).setUnlocalizedName("a.iron_chestplate");
//		legs  = new CustomArmor(8471, EnumArmorMaterial.IRON, 4, 2).setUnlocalizedName("a.iron_leggings");
//		boots = new CustomArmor(8472, EnumArmorMaterial.IRON, 4, 3).setUnlocalizedName("a.iron_boots");
//
//		LanguageRegistry.addName(helmet, "3D Helmet");
//		LanguageRegistry.addName(chest, "3D Chest");
//		LanguageRegistry.addName(legs, "3D Legs");
//		LanguageRegistry.addName(boots, "3D Boots");
//
//	}	
//}
