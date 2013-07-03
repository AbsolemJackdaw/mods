package scythemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.EnumHelper;
import scythemod.block.BlockAngelSpawner;
import scythemod.block.BlockCloud;
import scythemod.block.BlockConverter;
import scythemod.block.BlockOreHoly;
import scythemod.block.BlockOreUnholy;
import scythemod.block.te.TileEntityConverter;
import scythemod.entity.EntityCReaper;
import scythemod.entity.EntityCloudVillager;
import scythemod.entity.EntityDeath;
import scythemod.entity.EntityDrainScythe;
import scythemod.entity.EntityFarmScythe;
import scythemod.entity.EntityGhost;
import scythemod.entity.EntityLightningScythe;
import scythemod.entity.EntityMiniender;
import scythemod.entity.EntityTheFrozen;
import scythemod.entity.EntityThrowCake;
import scythemod.entity.EntityThrowScythe;
import scythemod.entity.EntityZombieReaper;
import scythemod.gen.ChoiceGen;
import scythemod.item.EnumToolMaterialTest;
import scythemod.item.FireScythe;
import scythemod.item.ItemArmorPerso;
import scythemod.item.ItemArmorReaper;
import scythemod.item.ItemBallz;
import scythemod.item.ItemDragonWand;
import scythemod.item.ItemDrainScythe;
import scythemod.item.ItemFarmScythe;
import scythemod.item.ItemFoodCursed;
import scythemod.item.ItemHaloDeath;
import scythemod.item.ItemHaloGod;
import scythemod.item.ItemLightningScythe;
import scythemod.item.ItemScake;
import scythemod.item.ItemSwordTest2;
import scythemod.item.ItemThrowScythe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;




@Mod(modid = "DSM", name = "Dsm mod By kirwii", version = "1.5.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"DSM" }, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"DSM" }, packetHandler = ServerPacketHandler.class))


public class BaseScytheFile
{

	@SidedProxy(
			serverSide = "scythemod.CommonProxy",
			clientSide = "scythemod.ClientProxy"
			)
	public static CommonProxy proxy;

	@Instance(value= "DSM")
	public static BaseScytheFile instance = new BaseScytheFile();

	private GuiHandler guiHandler = new GuiHandler();

	int goldOvenGUIid = 0;
	public static Item ShadyPearl;
	public static Item DeathScythe;
	public static Item HolyScythe;
	public static Item UnholyScythe;
	public static Item DeathScythe1;
	public static Item HolyScythe1;
	public static Item UnholyScythe1;
	public static Item ZombieScythe;
	public static Item ZombieScythe1;
	public static Item ThrowScythe;
	public static Item DrainScythe;
	public static Item LightningScythe;
	public static Item FarmScythe;
	public static Item FireScythe;
	public static Item ReaperHood;
	public static Item ReaperGown;
	public static Item ReaperTrousers;
	public static Item ReaperBoots;
	public static Item GodCrown;
	public static Item GodChestplate;
	public static Item GodTrousers;
	public static Item GodBoots;
	public static Item Soul;
	public static Item SoulPearl;
	public static Item UnholyCrystalSplinter;
	public static Item HolyCrystalSplinter;
	public static Item UnholyCrystal;
	public static Item HolyCrystal;
	public static Item Grindstone;
	public static Item CursedSoulDrink;
	public static Item FrozenSoul;
	public static Item HolySoulPearl;
	public static Item UnholySoulPearl;
	public static Item Blood;
	public static Item Scake;
	public static Item GodHalo;
	public static Item DedHalo;

	public static Item DragonWand;

	public static Block UnholyOre;
	public static Block HolyOre;
	public static Block Converter;
	public static Block Cloud;
	public static Block Angel;

	public static final CreativeTabs DTab = new DSMTab(CreativeTabs.getNextID(), "DSMTab");

	static EnumArmorMaterial ArmorMat = EnumHelper.addArmorMaterial("GOD",    100000, new int[]{2, 2, 2, 2}, 0);
	static EnumArmorMaterial ArmorDed = EnumHelper.addArmorMaterial("Reaper", 100000, new int[]{2, 2, 2, 2}, 0);
	static EnumArmorMaterial Halo     = EnumHelper.addArmorMaterial("Halo",   100000, new int[]{8, 8, 8, 8}, 0);

	@Init
	public void load(FMLInitializationEvent event)
	{
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);

		GameRegistry.registerTileEntity(TileEntityConverter.class, "converterthinghy");
//		GameRegistry.registerTileEntity(TileEntityAngelSpawning.class, "spawnershit");

		TileEntityConverter tileent1 = new TileEntityConverter();
//		TileEntityAngelSpawning tileent2 = new TileEntityAngelSpawning();

		DeathScythe = new ItemSwordTest2(2029, EnumToolMaterialTest.DEATH).setFull3D().setFull3D().setUnlocalizedName("scythedeath").setCreativeTab(DTab);
		HolyScythe = new ItemSwordTest2(2030, EnumToolMaterialTest.HOLY).setFull3D().setUnlocalizedName("scytheholy").setCreativeTab(DTab);
		UnholyScythe = new ItemSwordTest2(2031, EnumToolMaterialTest.UNHOLY).setFull3D().setUnlocalizedName("scytheunholy").setCreativeTab(DTab);
		DeathScythe1 = new ItemSwordTest2(2101, EnumToolMaterialTest.DEATH).setFull3D().setUnlocalizedName("scythedeath1").setCreativeTab(DTab);
		HolyScythe1 = new ItemSwordTest2(2102, EnumToolMaterialTest.HOLY).setFull3D().setUnlocalizedName("scytheholy1").setCreativeTab(DTab);
		UnholyScythe1 = new ItemSwordTest2(2103, EnumToolMaterialTest.UNHOLY).setFull3D().setUnlocalizedName("scytheunholy1").setCreativeTab(DTab);
		ZombieScythe = new ItemSwordTest2(2028, EnumToolMaterialTest.DEATH).setFull3D().setUnlocalizedName("scythezombie").setCreativeTab(DTab);
		ZombieScythe1 = new ItemSwordTest2(2027, EnumToolMaterialTest.DEATH).setFull3D().setUnlocalizedName("scythezombie1").setCreativeTab(DTab);
		FireScythe = new FireScythe(2077, EnumToolMaterialTest.FIRE).setFull3D().setUnlocalizedName("scythefireOFF").setCreativeTab(DTab);

		ThrowScythe = new ItemThrowScythe(2012).setFull3D().setUnlocalizedName("scythethrow").setCreativeTab(DTab);
		DrainScythe = new ItemDrainScythe(2013).setCreativeTab(DTab).setFull3D().setUnlocalizedName("scythedrain");
		LightningScythe = new ItemLightningScythe(2014).setFull3D().setUnlocalizedName("scythethunder").setCreativeTab(DTab);
		FarmScythe = new ItemFarmScythe(2015).setFull3D().setUnlocalizedName("scytheharvest").setCreativeTab(DTab);
		Scake = new ItemScake(2104).setFull3D().setUnlocalizedName("scake").setCreativeTab(DTab);

		ReaperHood = new ItemArmorReaper(2032, ArmorDed, 4, 0).setFull3D().setUnlocalizedName("reaper4").setCreativeTab(DTab);
		ReaperGown = new ItemArmorReaper(2033, ArmorDed, 4, 1).setCreativeTab(DTab).setFull3D().setUnlocalizedName("reaper3");
		ReaperTrousers = new ItemArmorReaper(2034, ArmorDed, 4, 2).setCreativeTab(DTab).setFull3D().setUnlocalizedName("reaper2");
		ReaperBoots = new ItemArmorReaper(2035, ArmorDed, 4, 3).setCreativeTab(DTab).setFull3D().setUnlocalizedName("reaper1");
		GodCrown = new ItemArmorPerso(2072, ArmorMat, 4, 0).setCreativeTab(DTab).setFull3D().setUnlocalizedName("god4");
		GodChestplate = new ItemArmorPerso(2073, ArmorMat, 4, 1).setCreativeTab(DTab).setFull3D().setUnlocalizedName("god3");
		GodTrousers = new ItemArmorPerso(2074, ArmorMat, 4, 2).setCreativeTab(DTab).setFull3D().setUnlocalizedName("god2");
		GodBoots = new ItemArmorPerso(2075, ArmorMat, 4, 3).setCreativeTab(DTab).setFull3D().setUnlocalizedName("god1");
		// GodHalo = new ItemArmorPerso(2105, Halo, 4, 0).setIconIndex(23).setItemName("GodHalo").setCreativeTab(DTab);
		// DedHalo = new ItemArmorPerso(2106, Halo, 4, 0).setIconIndex(24).setItemName("DedHalo").setCreativeTab(DTab);

		GodHalo = new ItemHaloGod(2105).setCreativeTab(DTab).setFull3D().setUnlocalizedName("sacredhalo");
		DedHalo = new ItemHaloDeath(2106).setCreativeTab(DTab).setFull3D().setUnlocalizedName("corrupthalo");

		DragonWand = new ItemDragonWand(2076).setCreativeTab(DTab).setFull3D().setUnlocalizedName("DragonWand");

		ShadyPearl = new ItemBallz(2036).setCreativeTab(DTab).setFull3D().setUnlocalizedName("shadypearl");
		Soul = new ItemBallz(2037).setCreativeTab(DTab).setFull3D().setUnlocalizedName("Soul");
		SoulPearl = new ItemBallz(2038).setCreativeTab(DTab).setFull3D().setUnlocalizedName("soulpearl");
		UnholyCrystalSplinter = new ItemBallz(2039).setCreativeTab(DTab).setFull3D().setUnlocalizedName("UnholyCrystalSplinter");
		HolyCrystalSplinter = new ItemBallz(2040).setCreativeTab(DTab).setFull3D().setUnlocalizedName("HolyCrystalSplinter");
		UnholyCrystal = new ItemBallz(2041).setCreativeTab(DTab).setFull3D().setUnlocalizedName("UnholyCrystal");
		HolyCrystal = new ItemBallz(2042).setCreativeTab(DTab).setFull3D().setUnlocalizedName("HolyCrystal");
		Grindstone = new ItemBallz(2050).setCreativeTab(DTab).setFull3D().setUnlocalizedName("grindstone");
		CursedSoulDrink = new ItemFoodCursed(2051, -4, 0.5F, false).setFull3D().setUnlocalizedName("drink").setCreativeTab(DTab);
		FrozenSoul = new ItemBallz(2054).setCreativeTab(DTab).setFull3D().setUnlocalizedName("FrozenSoul");
		HolySoulPearl = new ItemBallz(2055).setCreativeTab(DTab).setFull3D().setUnlocalizedName("holysoulpearl");
		UnholySoulPearl = new ItemBallz(2056).setCreativeTab(DTab).setFull3D().setUnlocalizedName("unholypearl");
		Blood = new ItemBallz(2057).setCreativeTab(DTab).setFull3D().setUnlocalizedName("Blood");

		UnholyOre = (new BlockOreUnholy (228, 0)).setHardness(10.0F).setResistance(10000.0F).setUnlocalizedName("unholyore").setCreativeTab(DTab);
		HolyOre = (new BlockOreHoly (229, 0)).setHardness(10.0F).setResistance(10000.0F).setCreativeTab(DTab).setUnlocalizedName("holyore");

		Converter = (new BlockConverter(232, Material.iron, false)).setHardness(5F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("converter").setCreativeTab(DTab);

		Cloud = (new BlockCloud()).setHardness(0F).setResistance(500F).setStepSound(Block.soundClothFootstep).setCreativeTab(DTab).setUnlocalizedName("cloud");
		Angel = (new BlockAngelSpawner(231,5).setHardness(1000F).setResistance(0F).setCreativeTab(DTab)).setUnlocalizedName("spawner");


		EntityRegistry.addSpawn(EntityMiniender.class, 2, 1, 1, EnumCreatureType.monster, WorldType.base12Biomes);

		//Mobs:
		LanguageRegistry.addName(UnholyOre, "Unholy Ore");		
		LanguageRegistry.addName(HolyOre, "Holy Ore");
		LanguageRegistry.addName(DeathScythe, "Death Scythe");
		LanguageRegistry.addName(DeathScythe1, "Sharpened Death Scythe");
		LanguageRegistry.addName(HolyScythe, "Holy Scythe");
		LanguageRegistry.addName(UnholyScythe, "Unholy Scythe");
		LanguageRegistry.addName(HolyScythe1, "Sharpened Holy Scythe");
		LanguageRegistry.addName(UnholyScythe1, "Sharpened Unholy Scythe");
		LanguageRegistry.addName(ZombieScythe, "Zombie Hunter Scythe");
		LanguageRegistry.addName(ZombieScythe1, "Sharpened Zombie Scythe");
		LanguageRegistry.addName(ThrowScythe, "Throwable Scythe");
		LanguageRegistry.addName(DrainScythe, "Vampiric Scythe");
		LanguageRegistry.addName(LightningScythe, "Thunder Scythe");
		LanguageRegistry.addName(CursedSoulDrink, "Soul Drink");
		LanguageRegistry.addName(FarmScythe, "Harvester Scythe");
		LanguageRegistry.addName(ReaperGown, "Grim Reaper's Gown");
		LanguageRegistry.addName(Grindstone, "Grindstone");
		LanguageRegistry.addName(Scake, "Scake");		
		LanguageRegistry.addName(GodHalo, "Sacred Halo");		
		LanguageRegistry.addName(DedHalo, "Corrupted Halo");
		LanguageRegistry.addName(FireScythe, "FireScythe");

		LanguageRegistry.addName(new ItemStack(DragonWand, 1, 0), "Pariunt Dei Draco");
		LanguageRegistry.addName(new ItemStack(DragonWand, 1, 1), "Pariunt Mors Draco");

		//angle spawner
		GameRegistry.registerBlock(Angel, "CloudSpawner");
		LanguageRegistry.addName(Angel,"Spawn");
		//cloud
		GameRegistry.registerBlock(Cloud,"Cloud");
		LanguageRegistry.addName(Cloud,"Cloud");
		//Unholy Ore
		GameRegistry.registerBlock(UnholyOre,"UnHoly Ore");
		//Holy Ore  
		GameRegistry.registerBlock(HolyOre, "Holy Ore");
		GameRegistry.registerBlock(Converter,"Converter");
		LanguageRegistry.addName(Converter, "Converter");    


		GameRegistry.addRecipe(new ItemStack(Scake, 1), new Object[]{ "CCC", "CSC", "CCC",  Character.valueOf('C'), Item.cake, Character.valueOf('S'), ThrowScythe });
		GameRegistry.addRecipe(new ItemStack(this.DragonWand, 1,0), new Object[]{ "H", "S", "S",  'H', this.DedHalo, 'S', Item.netherStar });
		GameRegistry.addRecipe(new ItemStack(this.DragonWand, 1,1), new Object[]{ "H", "S", "S",  'H', this.GodHalo, 'S', Item.netherStar });
		GameRegistry.addRecipe(new ItemStack(FireScythe, 1), new Object[]{ "CCC", "CSC", "CCC",  'C', Item.fireballCharge, Character.valueOf('S'),DeathScythe });

		// Halo's
		GameRegistry.addRecipe(new ItemStack(DedHalo, 1), new Object[]{ "XHX","PTL","XBX", Character.valueOf('X'), UnholyCrystal, Character.valueOf('H'), ReaperHood,
			Character.valueOf('P'), ReaperGown, Character.valueOf('L'), ReaperTrousers, Character.valueOf('B'), ReaperBoots,Character.valueOf('T'), Item.ghastTear
		});

		GameRegistry.addRecipe(new ItemStack(GodHalo, 1), new Object[]{"HXP","XTX","LXB",Character.valueOf('X'), HolyCrystal,Character.valueOf('H'), GodCrown,
			Character.valueOf('P'), GodChestplate,Character.valueOf('L'), GodTrousers,Character.valueOf('B'), GodBoots,Character.valueOf('T'), Item.ghastTear});
		//Death Scythe

		GameRegistry.addRecipe(new ItemStack(DeathScythe, 1, -6), new Object[]{
			"PPP",
			"  S",
			" S ",
			Character.valueOf('P'), SoulPearl,
			Character.valueOf('S'), Item.stick
		});

		//Verbessern:

		GameRegistry.addRecipe(new ItemStack(DeathScythe, 1, -4), new Object[]{
			"D",
			"S",
			Character.valueOf('D'), new ItemStack(DeathScythe, 1, -6),
			Character.valueOf('S'), Grindstone,
		});

		//Holy Scythe

		GameRegistry.addRecipe(new ItemStack(HolyScythe, 1, -1), new Object[]{
			"HHH",
			"HDH",
			"HHH",
			Character.valueOf('D'), DeathScythe,
			Character.valueOf('H'), HolyCrystal,
			Character.valueOf('O'), Block.obsidian
		});

		//Verbessern:

		GameRegistry.addRecipe(new ItemStack(HolyScythe1, 1, -5), new Object[]{
			"D",
			"S",
			Character.valueOf('D'), HolyScythe,
			Character.valueOf('S'), Grindstone,
		});


		//Unholy Scythe

		GameRegistry.addRecipe(new ItemStack(UnholyScythe, 1, -6), new Object[]{
			"UUU",
			"UDU",
			"UUU",
			Character.valueOf('D'), DeathScythe,
			Character.valueOf('U'), UnholyCrystal,
			Character.valueOf('O'), Block.obsidian
		});	

		//Verbessern:

		GameRegistry.addRecipe(new ItemStack(UnholyScythe1, 1, -4), new Object[]{
			"D",
			"S",
			Character.valueOf('D'), UnholyScythe,
			Character.valueOf('S'), Grindstone,
		});


		//Zombie Hunter Scythe

		GameRegistry.addRecipe(new ItemStack(ZombieScythe, 1, -2), new Object[]{
			"XBX",
			"BSB",
			"XBX",
			Character.valueOf('S'), DeathScythe,
			Character.valueOf('X'), Item.rottenFlesh,
			Character.valueOf('B'), Item.bone,
		});

		//Verbessern:

		GameRegistry.addRecipe(new ItemStack(ZombieScythe1, 1, -3), new Object[]{
			"D",
			"S",
			Character.valueOf('D'), ZombieScythe,
			Character.valueOf('S'), Grindstone,
		});

		//Throwable Scythe
		GameRegistry.addRecipe(new ItemStack(ThrowScythe, 1), new Object[]{
			"FGF",
			"FDF",
			"FGF",
			Character.valueOf('F'), Item.feather,
			Character.valueOf('G'), BaseScytheFile.Grindstone,
			Character.valueOf('D'), BaseScytheFile.DeathScythe,

		});

		//Vampiric Scythe
		GameRegistry.addRecipe(new ItemStack(DrainScythe, 1), new Object[]{
			"FKF",
			"KDK",
			"FKF",
			Character.valueOf('F'), BaseScytheFile.Blood,
			Character.valueOf('K'), BaseScytheFile.UnholyCrystal,
			Character.valueOf('D'), BaseScytheFile.DeathScythe,

		});

		//Lightning Scythe
		GameRegistry.addRecipe(new ItemStack(LightningScythe, 1), new Object[]{
			"FKF",
			"KDK",
			"FKF",
			Character.valueOf('D'), BaseScytheFile.DeathScythe,
			Character.valueOf('F'), Item.ghastTear,
			Character.valueOf('K'), BaseScytheFile.HolyCrystal,		
		});



		//Harvester Scythe
		GameRegistry.addRecipe(new ItemStack(FarmScythe, 1), new Object[]{
			"LLL",
			"LDL",
			"WWW",
			Character.valueOf('D'), BaseScytheFile.DeathScythe,
			Character.valueOf('W'), Item.wheat,
			Character.valueOf('L'), Item.leather,
		});


		//Rustungen:
		//Grim Reaper's Hood
		LanguageRegistry.addName(ReaperHood, "Grim Reaper's Hood");
		GameRegistry.addRecipe(new ItemStack (ReaperHood, 1), new Object[]{
			"XXX",
			"XSX",
			Character.valueOf('X'), UnholySoulPearl,
		});

		//Grim Reaper's Gown
		GameRegistry.addRecipe(new ItemStack (ReaperGown, 1), new Object[]{
			"X0X",
			"XXX",
			"XXX",
			Character.valueOf('X'), UnholySoulPearl
		});

		//Grim Reaper's Leggings
		LanguageRegistry.addName(ReaperTrousers, "Grim Reaper's Leggings");
		GameRegistry.addRecipe(new ItemStack (ReaperTrousers, 1), new Object[]{
			"XXX",
			"X0X",
			"X0X",
			Character.valueOf('X'), UnholySoulPearl
		});

		//Grim Reaper's Boots
		LanguageRegistry.addName(ReaperBoots, "Grim Reaper's Boots");
		GameRegistry.addRecipe(new ItemStack (ReaperBoots, 1), new Object[]{
			"X0X",
			"X0X",
			Character.valueOf('X'), UnholySoulPearl
		});

		//God Crown
		LanguageRegistry.addName(GodCrown, "God Crown");
		GameRegistry.addRecipe(new ItemStack (GodCrown, 1), new Object[]{
			"XXX",
			"XSX",
			Character.valueOf('X'), HolySoulPearl
		});

		//God Chestplate
		LanguageRegistry.addName(GodChestplate, "God Chestplate");
		GameRegistry.addRecipe(new ItemStack (GodChestplate, 1), new Object[]{
			"X0X",
			"XXX",
			"XXX",
			Character.valueOf('X'), HolySoulPearl
		});

		//God Leggings
		LanguageRegistry.addName(GodTrousers, "God Leggings");
		GameRegistry.addRecipe(new ItemStack (GodTrousers, 1), new Object[]{
			"XXX",
			"X0X",
			"X0X",
			Character.valueOf('X'), HolySoulPearl
		});

		//God Boots
		LanguageRegistry.addName(GodBoots, "God Boots");
		GameRegistry.addRecipe(new ItemStack (GodBoots, 1), new Object[]{
			"X0X",
			"X0X",
			Character.valueOf('X'), HolySoulPearl
		});

		//Andere:		
		//Shady Pearl
		LanguageRegistry.addName(ShadyPearl, "Shady Pearl");
		GameRegistry.addSmelting(368, new ItemStack(ShadyPearl), 1.0F);

		//Soul
		LanguageRegistry.addName(Soul, "Soul");
		GameRegistry.addSmelting(88, new ItemStack(Soul), 1.0F);

		//Soul Pearl
		LanguageRegistry.addName(SoulPearl, "Soul Pearl");
		GameRegistry.addRecipe(new ItemStack(SoulPearl, 1), new Object[]{
			"SSS",
			"SPS",
			"SSS",
			Character.valueOf('S'), Soul,
			Character.valueOf('P'), ShadyPearl
		});

		//Unholy Crystal Splinter
		LanguageRegistry.addName(UnholyCrystalSplinter, "Unholy Crystal Shard");

		//Holy Crystal Splinter
		LanguageRegistry.addName(HolyCrystalSplinter, "Holy Crystal Shard");

		//Unholy Crystal
		LanguageRegistry.addName(UnholyCrystal, "Unholy Crystal");
		GameRegistry.addRecipe(new ItemStack(UnholyCrystal, 1), new Object[]{
			"XX",
			"XX",
			Character.valueOf('X'), UnholyCrystalSplinter
		});

		//Holy Crystal
		LanguageRegistry.addName(HolyCrystal, "Holy Crystal");
		GameRegistry.addRecipe(new ItemStack(HolyCrystal, 1), new Object[]{
			"XX",
			"XX",
			Character.valueOf('X'), HolyCrystalSplinter
		});

		//Grindstone
		GameRegistry.addRecipe(new ItemStack(Grindstone, 1), new Object[]{
			"I0I",
			"ISI",
			"I0I",
			Character.valueOf('S'), Block.stone,
			Character.valueOf('I'), Item.ingotIron,
		});

		//Cursed Soul Drink
		GameRegistry.addShapelessRecipe (new ItemStack(CursedSoulDrink, 1), new Object[]{Item.glassBottle, Soul});

		//Frozen Soul
		LanguageRegistry.addName(FrozenSoul, "Frozen Soul");
		GameRegistry.addSmelting(FrozenSoul.itemID, new ItemStack(Soul), 1.0F);

		//Holy Soul Pearl
		LanguageRegistry.addName(HolySoulPearl, "Holy Soul Pearl");

		//Unholy Soul Pearl
		LanguageRegistry.addName(UnholySoulPearl, "Unholy Soul Pearl");

		//Blood
		LanguageRegistry.addName(Blood, "Blood");

		//Converter  
		GameRegistry.addRecipe(new ItemStack(Converter,1), new Object[] 
				{"B B",
			"PPP",
			"PTP", 
			'B', Item.glassBottle ,'P', new ItemStack (Block.planks, 1, 0), 'T',Block.torchWood });

		GameRegistry.registerWorldGenerator(new ChoiceGen());

		EntityRegistry.registerGlobalEntityID(EntityCReaper.class, "Creaper", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 16184564);
		EntityRegistry.registerGlobalEntityID(EntityTheFrozen.class, "Frozen", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 65526);
		EntityRegistry.registerGlobalEntityID(EntityGhost.class, "Ghost", EntityRegistry.findGlobalUniqueEntityId(), 16184564, 65526);
		EntityRegistry.registerGlobalEntityID(EntityZombieReaper.class, "ZombieReaper", EntityRegistry.findGlobalUniqueEntityId(), 6824646, 65526);
		EntityRegistry.registerGlobalEntityID(EntityDeath.class, "Death", EntityRegistry.findGlobalUniqueEntityId(), 16184564, 0x000000);
		EntityRegistry.registerGlobalEntityID(EntityMiniender.class, "MiniEnder", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 6824646);
		EntityRegistry.registerGlobalEntityID(EntityCloudVillager.class, "CloudVillager", EntityRegistry.findGlobalUniqueEntityId(), 0xffffff , 0xff22ff);

		EntityRegistry.registerGlobalEntityID(EntityThrowScythe.class, "ThrowdaScythe", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityThrowScythe.class, "ThrowdaScythe", EntityRegistry.findGlobalUniqueEntityId(),this,80,1,true);

		EntityRegistry.registerGlobalEntityID(EntityLightningScythe.class, "kaboomitsays", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityLightningScythe.class, "kaboomitsays", EntityRegistry.findGlobalUniqueEntityId(),this,80,1,true);
		
		EntityRegistry.registerGlobalEntityID(EntityDrainScythe.class, "drainer", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity( EntityDrainScythe.class, "drainer", EntityRegistry.findGlobalUniqueEntityId(), this , 80, 1, true);
		
		EntityRegistry.registerGlobalEntityID(EntityFarmScythe.class, "farmer", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity( EntityFarmScythe.class, "farmer", EntityRegistry.findGlobalUniqueEntityId(), this , 80, 1, true);
		
		EntityRegistry.registerGlobalEntityID(EntityThrowCake.class, "whatawaste", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityThrowCake.class, "whatawaste", EntityRegistry.findGlobalUniqueEntityId(),this,80,1,true);

		LanguageRegistry.instance().addStringLocalization("entity.Creaper.name", "Greaper");
		LanguageRegistry.instance().addStringLocalization("entity.Frozen.name", "The Frozen");
		LanguageRegistry.instance().addStringLocalization("entity.Ghost.name",  "Ghost");
		LanguageRegistry.instance().addStringLocalization("entity.ZombieReaper.name", "Zombie Reaper");
		LanguageRegistry.instance().addStringLocalization("entity.MiniEnder.name", "EnderKid");
		LanguageRegistry.instance().addStringLocalization("entity.Death.name", "Death");
		LanguageRegistry.instance().addStringLocalization("entity.CloudVillager.name", "Angel");

		
		
		proxy.registerRenderInformation();

		
	}




	@PreInit
	public void PreInit(FMLPreInitializationEvent event){

	}

	@PostInit
	public void PostInit(FMLPostInitializationEvent event){

	}




}

