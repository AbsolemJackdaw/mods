package betterbreeds;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import betterbreeds.entity.EntityChicken2;
import betterbreeds.entity.EntityChicken3;
import betterbreeds.entity.EntityChicken4;
import betterbreeds.entity.EntityChicken5;
import betterbreeds.entity.EntityCow2;
import betterbreeds.entity.EntityCow3;
import betterbreeds.entity.EntityCow4;
import betterbreeds.entity.EntityJelly;
import betterbreeds.entity.EntityPig2;
import betterbreeds.entity.EntityPig3;
import betterbreeds.entity.EntityPig4;
import betterbreeds.entity.EntityPig5;
import betterbreeds.entity.EntityPig6;
import betterbreeds.entity.EntitySheep2;
import betterbreeds.entity.EntitySheep3;
import betterbreeds.entity.EntitySheep4;
import betterbreeds.entity.EntitySheep5;
import betterbreeds.entity.EntityWolf2;
import betterbreeds.entity.EntityWolf3;
import betterbreeds.entity.EntityWolf4;
import betterbreeds.entity.EntityWolf5;
import betterbreeds.entity.EntityWolf6;
import betterbreeds.item.ItemCEgg;
import betterbreeds.item.ItemChocolatPie;
import betterbreeds.item.ItemDogTag;
import betterbreeds.item.ItemDough;
import betterbreeds.item.ItemSheepsMilk;
import betterbreeds.item.ItemSheppardPie;
import betterbreeds.item.ItemSubsFood;
import betterbreeds.item.ItemXmasSpecial;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "Breeds", name = "ModBreeds", version = "1.5.1 v2.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"firstChannel" }, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"secondChannel" }, packetHandler = ServerPacketHandler.class))


public class ModBreeds{

	public static  Item Sheepmilk ;
	public static  Item Sheepraw  ;
	public static  Item Sheepcooked;
	public static  Item SheppardsPie ;
	public static  Item PastryDough;
	public static  Item EasterEgg ;
	public static  Item Dogtag ;
	public static  Item ChocolatePie ;
	public static  Item SweetBread ;
	public static  Item jelly ;

	public static Item BlackBread;
	public static Item Sandwich;
	public static Item ChocoBrood;
	public static Item lasagna;

	public static Item horsemeat;
	public static Item horsemeatCooked;

	public static Item XmasSpecial;
	public static CreativeTabs BBT ;
	private static final World World = null;


	@SidedProxy(clientSide = "betterbreeds.ClientProxy", serverSide = "betterbreeds.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		BreedsConfig.instance.loadConfig(event.getSuggestedConfigurationFile());
	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		BBT = new BBTab(CreativeTabs.getNextID(), "BreedTab");

		Sheepmilk = new ItemSheepsMilk(BreedsConfig.instance.milk, 6, 0.3F, false).setUnlocalizedName("a.milk").setContainerItem(Item.bucketEmpty).setCreativeTab(BBT);
		Sheepraw =  new ItemSubsFood(BreedsConfig.instance.sheepraw, 2, 0.2F, false).setUnlocalizedName("sheepRaw").setCreativeTab(BBT);
		Sheepcooked = new ItemSubsFood(BreedsConfig.instance.sheep,6,  1F, true).setUnlocalizedName("sheepCooked").setCreativeTab(BBT);
		SheppardsPie = new ItemSheppardPie(BreedsConfig.instance.meatpie).setUnlocalizedName("pie").setCreativeTab(BBT);
		PastryDough = new ItemDough(BreedsConfig.instance.dough).setUnlocalizedName("a.clay").setCreativeTab(BBT);
		EasterEgg = new ItemCEgg(BreedsConfig.instance.Cegg).setUnlocalizedName("a.egg").setCreativeTab(BBT);
		Dogtag = new ItemDogTag(BreedsConfig.instance.tag).setUnlocalizedName("dogTag").setCreativeTab(BBT);
		ChocolatePie = new ItemChocolatPie(BreedsConfig.instance.pieC).setUnlocalizedName("pieCocoa").setCreativeTab(BBT);
		SweetBread = new ItemSubsFood(BreedsConfig.instance.bread,  6 , 0.8F, false).setUnlocalizedName("breadSweet").setCreativeTab(BBT);
		BlackBread = new ItemSubsFood(BreedsConfig.instance.breadB, 10, 1.5F,true).setUnlocalizedName("breadBlack").setCreativeTab(BBT);
		Sandwich   = new ItemSubsFood(BreedsConfig.instance.breadS, 8 , 1.2F,false).setUnlocalizedName("breadEgg").setCreativeTab(BBT);
		ChocoBrood = new ItemSubsFood(BreedsConfig.instance.breadC, 14, 2F,false).setUnlocalizedName("breadCocoa").setCreativeTab(BBT);

		horsemeat = new ItemSubsFood(BreedsConfig.instance.horseraw,10,0.6F,false).setUnlocalizedName("horseRaw").setCreativeTab(BBT);
		horsemeatCooked = new ItemSubsFood(BreedsConfig.instance.horse,14,1.5F,false).setUnlocalizedName("horse").setCreativeTab(BBT);
		lasagna = new ItemSubsFood(BreedsConfig.instance.lasagna,14,2F,false).setUnlocalizedName("lasagna").setCreativeTab(BBT);

		XmasSpecial = new ItemXmasSpecial(BreedsConfig.instance.xmas).setFull3D().setUnlocalizedName("xmas").setCreativeTab(BBT);
		jelly = new ItemJelly(BreedsConfig.instance.bear).setUnlocalizedName("jellybear").setCreativeTab(BBT);

		GameRegistry.addSmelting(Sheepraw.itemID, new ItemStack(Sheepcooked), 0.2F);
		GameRegistry.addSmelting(PastryDough.itemID, new ItemStack(SweetBread), 0.1F);
		GameRegistry.addSmelting(horsemeat.itemID, new ItemStack(horsemeatCooked), 0.1F);

		GameRegistry.addShapelessRecipe(new ItemStack(PastryDough, 1), new Object[] {Item.wheat, Item.bucketMilk});
		GameRegistry.addShapelessRecipe(new ItemStack(PastryDough, 1), new Object[] {Item.wheat, Sheepmilk});
		GameRegistry.addShapelessRecipe(new ItemStack(BlackBread, 1), new Object[] {Item.beefCooked, SweetBread});
		GameRegistry.addShapelessRecipe(new ItemStack(BlackBread, 1), new Object[] {Item.porkCooked, SweetBread});
		GameRegistry.addShapelessRecipe(new ItemStack(BlackBread, 1), new Object[] {Item.chickenCooked, SweetBread});
		GameRegistry.addShapelessRecipe(new ItemStack(BlackBread, 1), new Object[] {  Sheepcooked, SweetBread});
		GameRegistry.addShapelessRecipe(new ItemStack(Sandwich, 1), new Object[] {Item.egg,Item.egg, SweetBread});
		GameRegistry.addShapelessRecipe(new ItemStack(ChocoBrood, 1), new Object[] {EasterEgg, SweetBread});

		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Sheepmilk, 'B', Item.sugar, 'C', Item.wheat, 'E', Item.egg});

		GameRegistry.addRecipe(new ItemStack (SheppardsPie,1), new Object [] { "MMM","PPP", 'P',PastryDough, 'M',Sheepcooked});
		GameRegistry.addRecipe(new ItemStack (ChocolatePie,1), new Object [] { "MMM","PPP", 'P',PastryDough, 'M',EasterEgg});
		GameRegistry.addRecipe(new ItemStack (Dogtag,1), new Object [] { "LD","LL", 'L',Item.leather, 'D',Item.diamond});
		GameRegistry.addRecipe(new ItemStack (XmasSpecial,1,4), new Object [] { "PS","PS", 'P',Block.planks, 'S',Item.stick});
		GameRegistry.addRecipe(new ItemStack (XmasSpecial,1,5), new Object [] { "FRF","RSR","FRF", 'F',new ItemStack(Block.tallGrass,1,2), 'S',Item.sugar,'R', new ItemStack(Item.dyePowder,1,1)});
		GameRegistry.addRecipe(new ItemStack (jelly,1), new Object [] { "DSD","SSS","DSD", 'S',Item.sugar, 'D',new ItemStack(Item.dyePowder,1,9)});

		LanguageRegistry.addName(Sheepmilk   ,"Sheeps Milk");
		LanguageRegistry.addName(Sheepraw    ,"Sheeps Gigot");
		LanguageRegistry.addName(Sheepcooked ,"Roasted Gigot");
		LanguageRegistry.addName(SheppardsPie,"Sheppards Pie");
		LanguageRegistry.addName(PastryDough ,"Pastry Dough");
		LanguageRegistry.addName(EasterEgg   ,"Chocolat Egg");
		LanguageRegistry.addName(Dogtag      ,"Pet Collar");
		LanguageRegistry.addName(SweetBread  ,"Sweet Bread");
		LanguageRegistry.addName(ChocolatePie,"Easter Pie ");
		LanguageRegistry.addName(BlackBread  ,"Black_Eye's Meat Sandwich");
		LanguageRegistry.addName(Sandwich    ,"Egg Sandwich");
		LanguageRegistry.addName(ChocoBrood  ,"Chocolat Bun");
		LanguageRegistry.addName(jelly  ,"Jelly Bear");
		LanguageRegistry.addName(horsemeat  ,"Raw Horse Meat");
		LanguageRegistry.addName(lasagna  ,"Lasagna");
		LanguageRegistry.addName(horsemeatCooked  ,"Horse Meat");


		LanguageRegistry.addName(new ItemStack(XmasSpecial,1,0),"PepperMinth Egg");
		LanguageRegistry.addName(new ItemStack(XmasSpecial,1,1) ,"Honey Glazed Ham");
		LanguageRegistry.addName(new ItemStack(XmasSpecial,1,2)  ,"Hot Chocolat");
		LanguageRegistry.addName(new ItemStack(XmasSpecial,1,3)  ,"Magic Meat");
		LanguageRegistry.addName(new ItemStack(XmasSpecial,1,4)  ,"Empty Mug");
		LanguageRegistry.addName(new ItemStack(XmasSpecial,1,5)  ,"The Spirit of Christmas");

		LanguageRegistry.instance().addNameForObject(Sheepmilk, "fr_FR", "Lait Mouton");
		LanguageRegistry.instance().addNameForObject(Sheepraw    , "fr_FR","Jambe de Mouton");
		LanguageRegistry.instance().addNameForObject(Sheepcooked , "fr_FR","Mouton Cuit");
		LanguageRegistry.instance().addNameForObject(SheppardsPie, "fr_FR","Gateau de Berger");
		LanguageRegistry.instance().addNameForObject(PastryDough , "fr_FR","Pate a Cuire");
		LanguageRegistry.instance().addNameForObject(EasterEgg   , "fr_FR", "Oeuf en Chocolat");
		LanguageRegistry.instance().addNameForObject(Dogtag      , "fr_FR", "Chaine d'animaux");
		LanguageRegistry.instance().addNameForObject(SweetBread  , "fr_FR", "Pain Sucre");
		LanguageRegistry.instance().addNameForObject(ChocolatePie, "fr_FR", "Gateau de Paque ");
		LanguageRegistry.instance().addNameForObject(BlackBread, "fr_FR", "Sandwich Viande de Black_Eye ");
		LanguageRegistry.instance().addNameForObject(Sandwich, "fr_FR", "Sandwich a l'Oeuf ");
		LanguageRegistry.instance().addNameForObject(ChocoBrood, "fr_FR", "Sandwich au Chocolat ");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,5),"fr_FR"  ,"L'esprit de Noel");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,4),"fr_FR"  ,"Tasse Vide");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,3),"fr_FR"  ,"Viande Serene");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,2),"fr_FR"  ,"Chocolat Chaud");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,1),"fr_FR"  ,"Jambon au Bain de Miel");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,0),"fr_FR"  ,"Oeuf A La Menthe");

		LanguageRegistry.instance().addNameForObject(Sheepmilk, "de_DE", "Schafsmilch");
		LanguageRegistry.instance().addNameForObject(Sheepraw    , "de_DE","Lammkeule");
		LanguageRegistry.instance().addNameForObject(Sheepcooked , "de_DE","Gekochte Lammkeule");
		LanguageRegistry.instance().addNameForObject(SheppardsPie, "de_DE","Kuchen des Schafers");
		LanguageRegistry.instance().addNameForObject(PastryDough , "de_DE","Teig");
		LanguageRegistry.instance().addNameForObject(EasterEgg   , "de_DE", "Osterei");
		LanguageRegistry.instance().addNameForObject(Dogtag      , "de_DE", "Halsband");
		LanguageRegistry.instance().addNameForObject(SweetBread  , "de_DE", "Susses Brot");
		LanguageRegistry.instance().addNameForObject(ChocolatePie, "de_DE", "Schokoladenkuchen");
		LanguageRegistry.instance().addNameForObject(BlackBread, "de_DE", "Black_Eye's Fleish Brotchen");
		LanguageRegistry.instance().addNameForObject(Sandwich, "de_DE", "Brotchen mit Ei");
		LanguageRegistry.instance().addNameForObject(ChocoBrood, "de_DE", "Schokoladenbrotchen");      
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,5),"de_DE"  ,"Der Geist der Weihnacht");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,4),"de_DE"  ,"Leere Tasse");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,3),"de_DE"  ,"Viande Serene");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,2),"de_DE"  ,"Heisse Schokolade");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,1),"de_DE"  ,"Schinken mit Honigglasur");
		LanguageRegistry.instance().addNameForObject(new ItemStack(XmasSpecial,1,0),"de_DE"  ,"Minz-Ei");

		LanguageRegistry.instance().addStringLocalization("entity.Superpig2.name", "fr_FR", "2eme Race");
		LanguageRegistry.instance().addStringLocalization("entity.Superpig3.name", "fr_FR", "3eme Race");
		LanguageRegistry.instance().addStringLocalization("entity.Superpig4.name", "fr_FR", "4eme Race");
		LanguageRegistry.instance().addStringLocalization("entity.Superpig5.name", "fr_FR", "5eme Race");
		LanguageRegistry.instance().addStringLocalization("entity.BeefCow.name", "fr_FR", "Vache a Viande ");
		LanguageRegistry.instance().addStringLocalization("entity.LeatherCow.name", "fr_FR", "Vache a Cuire");
		LanguageRegistry.instance().addStringLocalization("entity.GreyWolf.name", "fr_FR", "Loup Gris");
		LanguageRegistry.instance().addStringLocalization("entity.DarkWolf.name", "fr_FR", "Loup Fonce");
		LanguageRegistry.instance().addStringLocalization("entity.Shepard.name", "fr_FR", "Berger");
		LanguageRegistry.instance().addStringLocalization("entity.DeathWolf.name", "fr_FR", "Loup Mort");
		LanguageRegistry.instance().addStringLocalization("entity.MilkSheep.name", "fr_FR", "Mouton Mamelle");
		LanguageRegistry.instance().addStringLocalization("entity.MeatSHeep.name", "fr_FR", "Mouton a Viande");
		LanguageRegistry.instance().addStringLocalization("entity.ScotSheep.name", "fr_FR", "Mouton Ecosse");
		LanguageRegistry.instance().addStringLocalization("entity.FluffyChick.name", "fr_FR", "Poullet Doux");
		LanguageRegistry.instance().addStringLocalization("entity.BreedChick.name", "fr_FR", "Machine aux Oeuf");
		LanguageRegistry.instance().addStringLocalization("entity.AwesomeChick.name", "fr_FR", "Poulet de Paque");
		LanguageRegistry.instance().addStringLocalization("entity.XmasChick.name", "fr_FR", "Poulet Menthe");
		LanguageRegistry.instance().addStringLocalization("entity.XmasPig.name", "fr_FR", "Cochon au Miel");
		LanguageRegistry.instance().addStringLocalization("entity.XmasCow.name", "fr_FR", "Vache ChocoChaud");
		LanguageRegistry.instance().addStringLocalization("entity.XmasWolf.name", "fr_FR", "Loup de Neige");
		LanguageRegistry.instance().addStringLocalization("entity.XmasSheep.name", "fr_FR", "MouMoute");

		LanguageRegistry.instance().addStringLocalization("entity.Superpig2.name", "en_US", "2nd Breed");
		LanguageRegistry.instance().addStringLocalization("entity.Superpig3.name", "en_US", "3th Breed");
		LanguageRegistry.instance().addStringLocalization("entity.Superpig4.name", "en_US", "4th Breed");
		LanguageRegistry.instance().addStringLocalization("entity.Superpig5.name", "en_US", "5th Breed");
		LanguageRegistry.instance().addStringLocalization("entity.BeefCow.name", "en_US", "Meat Cow");
		LanguageRegistry.instance().addStringLocalization("entity.LeatherCow.name", "en_US", "Leather Cow");
		LanguageRegistry.instance().addStringLocalization("entity.GreyWolf.name", "en_US", "Grey Wolf");
		LanguageRegistry.instance().addStringLocalization("entity.DarkWolf.name", "en_US", "Dark Wolf");
		LanguageRegistry.instance().addStringLocalization("entity.Shepard.name", "en_US", "Shepard");
		LanguageRegistry.instance().addStringLocalization("entity.DeathWolf.name", "en_US", "Death Wolf");
		LanguageRegistry.instance().addStringLocalization("entity.MilkSheep.name", "en_US", "Milk Sheep");
		LanguageRegistry.instance().addStringLocalization("entity.MeatSHeep.name", "en_US", "Meat Sheep");
		LanguageRegistry.instance().addStringLocalization("entity.ScotSheep.name", "en_US", "Scottish Sheep");
		LanguageRegistry.instance().addStringLocalization("entity.FluffyChick.name", "en_US", "Fluffy Chicken");
		LanguageRegistry.instance().addStringLocalization("entity.BreedChick.name", "en_US", "Egg Machine");
		LanguageRegistry.instance().addStringLocalization("entity.AwesomeChick.name", "en_US", "Easter Chicken");
		LanguageRegistry.instance().addStringLocalization("entity.XmasChick.name", "en_US", "Minth Chicken");
		LanguageRegistry.instance().addStringLocalization("entity.XmasPig.name", "en_US", "HoneyDew Pig");
		LanguageRegistry.instance().addStringLocalization("entity.XmasCow.name", "en_US", "Hot Cow");
		LanguageRegistry.instance().addStringLocalization("entity.XmasWolf.name", "en_US", "Snow Wolf");
		LanguageRegistry.instance().addStringLocalization("entity.XmasSheep.name", "en_US", "Wooly");
		LanguageRegistry.instance().addStringLocalization("entity.JellyBear.name", "en_US", "Jelly Bear");


		LanguageRegistry.instance().addStringLocalization("entity.Superpig2.name", "de_DE", "2. Zucht");
		LanguageRegistry.instance().addStringLocalization("entity.Superpig3.name", "de_DE", "3. Zucht");
		LanguageRegistry.instance().addStringLocalization("entity.Superpig4.name", "de_DE", "4. Zucht");
		LanguageRegistry.instance().addStringLocalization("entity.Superpig5.name", "de_DE", "5. Zucht");
		LanguageRegistry.instance().addStringLocalization("entity.BeefCow.name", "de_DE", "Fleischkuh");
		LanguageRegistry.instance().addStringLocalization("entity.LeatherCow.name", "de_DE", "Lederkuh");
		LanguageRegistry.instance().addStringLocalization("entity.GreyWolf.name", "de_DE", "Grauer Wolf");
		LanguageRegistry.instance().addStringLocalization("entity.DarkWolf.name", "de_DE", "Dunkler Wolf");
		LanguageRegistry.instance().addStringLocalization("entity.Shepard.name", "de_DE", "Schaferhund");
		LanguageRegistry.instance().addStringLocalization("entity.DeathWolf.name", "de_DE", "Todeswolf");
		LanguageRegistry.instance().addStringLocalization("entity.MilkSheep.name", "de_DE", "Milchschaf");
		LanguageRegistry.instance().addStringLocalization("entity.MeatSHeep.name", "de_DE", "Fleishshaf");
		LanguageRegistry.instance().addStringLocalization("entity.ScotSheep.name", "de_DE", "Schottisches Schaf");
		LanguageRegistry.instance().addStringLocalization("entity.FluffyChick.name", "de_DE", "Flauschiges Huhn");
		LanguageRegistry.instance().addStringLocalization("entity.BreedChick.name", "de_DE", "Eiermaschine");
		LanguageRegistry.instance().addStringLocalization("entity.AwesomeChick.name", "de_DE", "Oster-Huhn");
		LanguageRegistry.instance().addStringLocalization("entity.XmasChick.name", "de_DE", "Minze Hunn");
		LanguageRegistry.instance().addStringLocalization("entity.XmasPig.name", "de_DE", "Honigtauschwein");
		LanguageRegistry.instance().addStringLocalization("entity.XmasCow.name", "de_DE", "Heisse Kuh");
		LanguageRegistry.instance().addStringLocalization("entity.XmasWolf.name", "de_DE", "Schneewolf");
		LanguageRegistry.instance().addStringLocalization("entity.XmasSheep.name", "de_DE", "WolleWol");

		EntityRegistry.registerGlobalEntityID(EntityPig2.class, "Superpig2", EntityRegistry.findGlobalUniqueEntityId(), 0xf2afaf, 0xe34a4b);
		EntityRegistry.registerGlobalEntityID(EntityPig3.class, "Superpig3", EntityRegistry.findGlobalUniqueEntityId(), 0xee8552, 0xe34a4b);
		EntityRegistry.registerGlobalEntityID(EntityPig4.class, "Superpig4", EntityRegistry.findGlobalUniqueEntityId(), 0xeabac0, 0xe34a4b);
		EntityRegistry.registerGlobalEntityID(EntityPig5.class, "Superpig5", EntityRegistry.findGlobalUniqueEntityId(), 0xeb8182, 0xe34a4b);
		EntityRegistry.registerGlobalEntityID(EntityPig6.class, "XmasPig",   EntityRegistry.findGlobalUniqueEntityId(), 0x184315, 0xcd0d09);

		EntityRegistry.registerGlobalEntityID(EntityCow2.class, "BeefCow",   EntityRegistry.findGlobalUniqueEntityId(), 0x4c2f18, 0xffffff);
		EntityRegistry.registerGlobalEntityID(EntityCow3.class, "LeatherCow",EntityRegistry.findGlobalUniqueEntityId(), 0x6f2b19, 0xdd7e65);
		EntityRegistry.registerGlobalEntityID(EntityCow4.class, "XmasCow",EntityRegistry.findGlobalUniqueEntityId(), 0x184315, 0xcd0d09);

		EntityRegistry.registerGlobalEntityID(EntityWolf2.class, "GreyWolf",EntityRegistry.findGlobalUniqueEntityId(), 0x808080 ,0xc0c0c0 );
		EntityRegistry.registerGlobalEntityID(EntityWolf3.class, "DarkWolf",EntityRegistry.findGlobalUniqueEntityId(), 0x808080 ,0x202020 );
		EntityRegistry.registerGlobalEntityID(EntityWolf4.class, "Shepard" ,EntityRegistry.findGlobalUniqueEntityId(), 0x808080 ,0x783b0a );
		EntityRegistry.registerGlobalEntityID(EntityWolf5.class, "DeathWolf",EntityRegistry.findGlobalUniqueEntityId(),0x808080 ,0xc40b0c );
		EntityRegistry.registerGlobalEntityID(EntityWolf6.class, "XmasWolf",EntityRegistry.findGlobalUniqueEntityId(),0x184315, 0xcd0d09 );

		EntityRegistry.registerGlobalEntityID(EntitySheep2.class, "MilkSheep",EntityRegistry.findGlobalUniqueEntityId(), 0xfffff0, 0xa11f99);
		EntityRegistry.registerGlobalEntityID(EntitySheep3.class, "MeatSHeep",EntityRegistry.findGlobalUniqueEntityId(), 0xfffff0, 0xeeab98);
		EntityRegistry.registerGlobalEntityID(EntitySheep4.class, "ScotSheep",EntityRegistry.findGlobalUniqueEntityId(), 0xfffff0, 0xc01314);
		EntityRegistry.registerGlobalEntityID(EntitySheep5.class, "XmasSheep",EntityRegistry.findGlobalUniqueEntityId(), 0x184315, 0xcd0d09);

		EntityRegistry.registerGlobalEntityID(EntityChicken2.class, "FluffyChick",EntityRegistry.findGlobalUniqueEntityId(), 0xffffff, 0xff9729);
		EntityRegistry.registerGlobalEntityID(EntityChicken3.class, "BreedChick" ,EntityRegistry.findGlobalUniqueEntityId(), 0xffffff, 0xffda59);
		EntityRegistry.registerGlobalEntityID(EntityChicken4.class, "AwesomeChick",EntityRegistry.findGlobalUniqueEntityId(),0xffffff, 0x843f0e);
		EntityRegistry.registerGlobalEntityID(EntityChicken5.class, "XmasChick",EntityRegistry.findGlobalUniqueEntityId(),0x184315, 0xcd0d09);

		EntityRegistry.registerGlobalEntityID(EntityJelly.class, "JellyBear",EntityRegistry.findGlobalUniqueEntityId(),0x710556, 0xfd31ca);

		TickRegistry.registerScheduledTickHandler(new AiReplacer(), Side.SERVER);
		proxy.registerRenderInformation();
	}
}