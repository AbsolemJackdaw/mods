package redstone;

import modUpdateChecked.OnPlayerLogin;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redstone.ammo.AranAmmo;
import redstone.ammo.BlueAmmo;
import redstone.ammo.EndAmmo;
import redstone.ammo.GatlingAmmo;
import redstone.ammo.GreenAmmo;
import redstone.ammo.PlasmaAmmo;
import redstone.ammo.RedAmmo;
import redstone.ammo.ThawAmmo;
import redstone.item.ItemDefenseGun;
import redstone.item.ItemGun;
import redstone.item.ItemRedstoneGun;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RedStoneHandGuns", name = "RedStoneGuns", version = mod_RedStoneGuns.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"GunPacket"}, packetHandler = GunHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"GunPacket"}, packetHandler = GunHandler.class))


public class mod_RedStoneGuns {

	public static Item redGun;
	public static Item blueGun;
	public static Item greenGun;
	public static Item rifle;
	public static Item cannon;
	public static Item thawer;
	public static Item gatling;
	public static Item plasmaRifle;
	public static Item components;

	public static Item defGun;

	public static CreativeTabs RedTab;

	public static int uniqueID =0;
	@SidedProxy(serverSide = "redstone.CommonProxy", clientSide = "redstone.ClientProxy")
	public static CommonProxy proxy;

	private static final String name = "RedStoneHandGuns";
	protected static final String version = "1.6.4 v2";

	public int getUniqueID() {
		return uniqueID++;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		GunConfig.instance.loadConfig(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void load (FMLInitializationEvent e){

		GameRegistry.registerPlayerTracker(new OnPlayerLogin(version, name));


		RedTab = new RedTab(CreativeTabs.getNextID(), "redTab");

		redGun = new ItemRedstoneGun(GunConfig.instance.redGun).setUnlocalizedName("rGun").setCreativeTab(RedTab);
		
		blueGun = new ItemGun(GunConfig.instance.blueGun,1,0).setUnlocalizedName("bGun").setCreativeTab(RedTab);
		greenGun = new ItemGun(GunConfig.instance.greenGun,2,0).setUnlocalizedName("gGun").setCreativeTab(RedTab);
		rifle = new ItemGun(GunConfig.instance.endGun,3,0).setUnlocalizedName("eGun").setCreativeTab(RedTab);
		cannon = new ItemGun(GunConfig.instance.cannon, 4,0).setUnlocalizedName("cannon").setCreativeTab(RedTab);
		thawer = new ItemGun(GunConfig.instance.thawer,5,300).setUnlocalizedName("thawer").setCreativeTab(RedTab);
		gatling = new ItemGun(GunConfig.instance.gatling, 6, 192).setUnlocalizedName("gatling").setCreativeTab(RedTab);
		plasmaRifle = new ItemGun(GunConfig.instance.plasma, 7, 30).setUnlocalizedName("haloGun").setCreativeTab(RedTab);
		
		components = new ItemComponents(GunConfig.instance.components).setUnlocalizedName("comp").setCreativeTab(RedTab);

		defGun = new ItemDefenseGun(GunConfig.instance.defGun).setUnlocalizedName("defgun").setCreativeTab(RedTab);

		if(GunConfig.instance.setAmmoTab == true){
			Item.redstone.setCreativeTab(RedTab);
			Item.enderPearl.setCreativeTab(RedTab);
			Item.rottenFlesh.setCreativeTab(RedTab);
			Item.gunpowder.setCreativeTab(RedTab);
			Item.bucketWater.setCreativeTab(RedTab);
			Item.bucketLava.setCreativeTab(RedTab);
			Block.dirt.setCreativeTab(RedTab);
			Block.cobblestone.setCreativeTab(RedTab);
		}


		LanguageRegistry.addName(redGun, "RedStone Automatic Gun");
		LanguageRegistry.addName(blueGun, "Lapis Launcher");
		LanguageRegistry.addName(greenGun, "Zombie Hord ShotGun");
		LanguageRegistry.addName(rifle, "Ender Sniper Rifle");
		LanguageRegistry.addName(cannon, "Aran Cannon");
		LanguageRegistry.addName(thawer, "Thawer");
		LanguageRegistry.addName(gatling, "Gatling Gun");
		LanguageRegistry.addName(plasmaRifle, "Type-25 Directed Energy Rifle");
		LanguageRegistry.addName(defGun, "M6D Personal Defense Weapon");

		LanguageRegistry.addName(new ItemStack(components,1,0), "Core");
		LanguageRegistry.addName(new ItemStack(components,1,1), "Laser");
		LanguageRegistry.addName(new ItemStack(components,1,2), "Cuffs");
		LanguageRegistry.addName(new ItemStack(components,1,3), "Shell");

		addGunRecipe(redGun, Item.redstone);
		addGunRecipe(blueGun, new ItemStack(Item.dyePowder, 1, 4));
		addGunRecipe(greenGun, Item.rottenFlesh);	
		addGunRecipe(rifle, Item.enderPearl);
		addGunRecipe(thawer, Item.bucketEmpty);
		addGunRecipe(cannon, Item.blazeRod);
		addGunRecipe(gatling, Item.fireworkCharge);
		addGunRecipe(plasmaRifle, Item.netherStar);
		addGunRecipe(defGun, Item.ingotIron);

		GameRegistry.addRecipe(new ItemStack(components,1,0), new Object[]{
			"iii","ii ", 'i', Item.ingotGold
		});
		GameRegistry.addRecipe(new ItemStack(components,1,1), new Object[]{
			"ggg","rrr","ggg", 'g', Block.glass, 'r', Item.redstone
		});
		GameRegistry.addRecipe(new ItemStack(components,1,2), new Object[]{
			"www","ll ", 'w', Block.cloth, 'l', Item.leather
		});
		GameRegistry.addRecipe(new ItemStack(components,1,3), new Object[]{
			"iii","ii ", 'i', Item.ingotIron
		});

		EntityRegistry.registerModEntity(RedAmmo.class, "redAmmo", getUniqueID(), this, 80, 20, true);
		EntityRegistry.registerGlobalEntityID(RedAmmo.class, "redAmmo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(BlueAmmo.class, "BlueAmmo", getUniqueID(), this, 80, 20, true);
		EntityRegistry.registerGlobalEntityID(BlueAmmo.class, "BlueAmmo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(GreenAmmo.class, "GreenAmmo", getUniqueID(), this, 80, 20, true);
		EntityRegistry.registerGlobalEntityID(GreenAmmo.class, "GreenAmmo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EndAmmo.class, "EndAmmo", getUniqueID(), this, 80, 20, true);
		EntityRegistry.registerGlobalEntityID(EndAmmo.class, "EndAmmo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(ThawAmmo.class, "ThawAmmo", getUniqueID(), this, 80, 20, true);
		EntityRegistry.registerModEntity(GatlingAmmo.class, "GatlingAmmo", getUniqueID(), this, 80, 20, true);
		EntityRegistry.registerModEntity(AranAmmo.class, "AranAmmo", getUniqueID(), this, 80, 20, true);
		EntityRegistry.registerGlobalEntityID(AranAmmo.class, "AranAmmo", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(PlasmaAmmo.class, "plasmaAmmo", getUniqueID(), this, 80, 20, true);
		EntityRegistry.registerGlobalEntityID(PlasmaAmmo.class, "plasmaAmmo", EntityRegistry.findGlobalUniqueEntityId());
		this.proxy.render();

	}

	public void addGunRecipe( Item result, Item parser){

		GameRegistry.addRecipe(new ItemStack(result,1), new Object[]{ 
			"x  ","ycr","z  ", 'r', parser, 'x', new ItemStack(components,1,3),
			'y', new ItemStack(components, 1,1), 'z', new ItemStack(components, 1,0), 
			'c', new ItemStack(components, 1, 2)});
	}

	public void addGunRecipe( Item result, ItemStack parser){

		GameRegistry.addRecipe(new ItemStack(result,1), new Object[]{ 
			"x  ","ycr","z  ", 'r', parser, 'x', new ItemStack(components,1,3),
			'y', new ItemStack(components, 1,1), 'z', new ItemStack(components, 1,0), 
			'c', new ItemStack(components, 1, 2)});
	}
}

