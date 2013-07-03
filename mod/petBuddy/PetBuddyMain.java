package petBuddy;

import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import petBuddy.entity.EntityBuddy;
import petBuddy.handelers.BuddyCommonProxy;
import petBuddy.handelers.BuddyPacketHandler;
import petBuddy.handelers.PetSpawner;
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



@Mod(modid = "buddyPet", name = "My tiny buddy", version = "1.0")

@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"buddyPet"}, packetHandler = BuddyPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"buddyPet"}, packetHandler = BuddyPacketHandler.class))


public class PetBuddyMain {

	@SidedProxy(serverSide = "petBuddy.handelers.BuddyCommonProxy", clientSide = "petBuddy.handelers.BuddyClientProxy")
	public static BuddyCommonProxy proxy;

	public static Achievement findBuddy ;
	public static Achievement pigAchieve ;
	public static Achievement tinyYou ;
	public static Achievement zombie ;
	public static Achievement nameBuddy ;
	public static Achievement skin ;

	public static Achievement slime ;
	public static Achievement sheep ;
	public static Achievement cat ;
	public static Achievement silverfish ;
	public static Achievement golem ;
	public static Achievement snow ;
	public static Achievement creeper ;
	public static Achievement cow ;
	public static Achievement mooshroom ;
	public static Achievement spider ;
	public static Achievement skelet ;
	public static Achievement chicken ;
	public static Achievement squid ;
	public static Achievement villager ;
	public static Achievement wolf ;

	public static Achievement portalNether ;
	public static Achievement ghast ;
	public static Achievement pigZombie ;
	public static Achievement wither ;
	public static Achievement witherSkelly ;
	public static Achievement ghost ;
	public static Achievement magma ;
	public static Achievement bat ;

	public static Achievement rpgSpider ;
	public static Achievement rpgBull ;
	public static Achievement rpgBoar ;

	public static Achievement endPearl ;
	public static Achievement endPortal ;
	public static Achievement endDragon ;
	

	public static AchievementPage page1;
	public static HashMap<String, Integer> playersWithPets = new HashMap();	
	
	@PreInit
	public void load (FMLPreInitializationEvent e){
		
		

		findBuddy = new Achievement(s(), "startBuddy", -1, 0, new ItemStack(Item.skull,1,3), null).setIndependent().registerAchievement();
		nameBuddy = new Achievement(s(), "name", -1, -2, Item.stick, findBuddy).registerAchievement();
		pigAchieve = new Achievement(s(), "pigBuddy", 2, -1, Item.porkRaw, findBuddy).registerAchievement();
		tinyYou = new Achievement(s(), "tinyBuddy", 3, 2, Item.bread, pigAchieve).registerAchievement();
		zombie = new Achievement(s(), "zombie", 10, 1, Item.rottenFlesh, tinyYou).registerAchievement();
		
		rpgSpider = new Achievement(s(), "rpgSpin", 1, -2, Item.fermentedSpiderEye, pigAchieve).registerAchievement();
		rpgBull = new Achievement(s(), "rpgBull", 3, -2, Item.beefCooked, pigAchieve).registerAchievement();
		rpgBoar = new Achievement(s(), "rpgBoar", 2, -3, Item.porkCooked, pigAchieve).registerAchievement();

		endPearl = new Achievement(s(), "endPearl", 5, 4, Item.enderPearl, tinyYou).registerAchievement();
		endPortal = new Achievement(s(), "endPortal", 5, 6, Block.endPortalFrame, endPearl).registerAchievement();
		endDragon = new Achievement(s(), "endDragon", 5, 8, Block.whiteStone, endPortal).setSpecial().registerAchievement();

		skin = new Achievement(s(), "skin", -2, -1, Item.leather, findBuddy).registerAchievement();

		portalNether = new Achievement(s(), "portal", 1, 4, Block.portal, tinyYou).registerAchievement();
		ghast = new Achievement(s(), "ghast", 0, 7, Item.ghastTear, portalNether).registerAchievement();
		pigZombie = new Achievement(s(), "zombPig", 0, 5, Item.netherQuartz, portalNether).registerAchievement();
		wither = new Achievement(s(), "wither", 1, 11, Item.netherStar, portalNether).setSpecial().registerAchievement();
		witherSkelly = new Achievement(s(), "witherSkel", 2, 8, new ItemStack(Item.skull,1,1), portalNether).registerAchievement();
		ghost = new Achievement(s(), "ghost", 2, 6, Item.blazePowder, portalNether).registerAchievement();
		magma = new Achievement(s(), "magma", 0, 9, Item.magmaCream, portalNether).registerAchievement();
		bat = new Achievement(s(), "bat", 2, 10, Item.netherStalkSeeds, portalNether).registerAchievement();

		cow = new Achievement(s(), "cow", 6, -1, Item.beefRaw, pigAchieve).registerAchievement();
		mooshroom = new Achievement(s(), "mooshroom", 7, 0, Block.mycelium, cow).setSpecial().registerAchievement();

		sheep = new Achievement(s(), "sheep", 7, -3, Block.cloth, cow).registerAchievement();
		cat = new Achievement(s(), "cat", 8, -3, Item.fishRaw, cow).registerAchievement();
		golem = new Achievement(s(), "golem", 9, -3, Block.blockIron, cow).registerAchievement();
		snow = new Achievement(s(), "snow", 10, -3, Item.snowball, cow).registerAchievement();
		chicken = new Achievement(s(), "chicken", 8, -4, Item.feather, cow).registerAchievement();
		villager = new Achievement(s(), "villager", 9, -4, Item.book, cow).registerAchievement();
		squid = new Achievement(s(), "squid", 10, -4, new ItemStack(Item.dyePowder, 1,0), cow).registerAchievement();
		wolf = new Achievement(s(), "wolf", 7, -4, Item.bone, cow).registerAchievement();

		silverfish = new Achievement(s(), "silverfish", 11, 2, Item.fishCooked, zombie).registerAchievement();
		creeper = new Achievement(s(), "creeper", 9, 3, Item.gunpowder, zombie).registerAchievement();
		slime = new Achievement(s(), "slime", 11, 4, Item.slimeBall, zombie).registerAchievement();
		spider = new Achievement(s(), "spider", 9, 5, Item.spiderEye, zombie).registerAchievement();
		skelet = new Achievement(s(), "skelet", 11, 6, Item.arrow, zombie).registerAchievement();
		
		this.addAchievementLocalizations();
		
		page1 = new AchievementPage("Buddy", findBuddy, nameBuddy,pigAchieve,tinyYou,zombie, portalNether,
				ghast,pigZombie, wither, witherSkelly, ghost, rpgSpider, rpgBull, rpgBoar, magma, bat, 
				endPearl, endPortal, endDragon, skin, slime, sheep, cat, silverfish, golem ,snow, creeper,
				cow, mooshroom, spider, skelet, chicken, villager, squid, wolf);
		
		AchievementPage.registerAchievementPage(page1);
	}

	@Init
	public void load (FMLInitializationEvent e){

		EntityRegistry.registerGlobalEntityID(EntityBuddy.class, "TinyClone", EntityRegistry.findGlobalUniqueEntityId());
		this.proxy.render();

		GameRegistry.registerPlayerTracker(new PetSpawner());
	}

	//not very long to write. i like this. I should give it a proper name though ...
	private int s(){
		return AchievementList.achievementList.size() + 1;
	}
	
	private void addAchievementLocalizations()
	{
		addAchievName("startBuddy", "A Friend !");
		addAchievMouseOver("startBuddy", "Carry your Buddy");

		addAchievName("name", "... With A Name.");
		addAchievMouseOver("name", "Name your Buddy");

		addAchievName("pigBuddy", "Walking Bacon Friend !");
		addAchievMouseOver("pigBuddy", "Change your Buddy");

		addAchievName("tinyBuddy", "Bread Miracle !");
		addAchievMouseOver("tinyBuddy", "Change your Buddy to Tiny You");

		addAchievName("zombie", "Oh Dear ...");
		addAchievMouseOver("zombie", "Change Tiny You to a Zombie");
		
		addAchievName("portal", "Gathering Materials");
		addAchievMouseOver("portal", "Go to the nether to find more Buddy material");
		
		addAchievName("ghast", "A Tear For A Friend");
		addAchievMouseOver("ghast", "Change your Buddy into a Ghast");
		
		addAchievName("zombPig", "Walking Bacon Friend II");
		addAchievMouseOver("zombPig", "Change your Buddy into a Ghast");
		
		addAchievName("witherSkel", "Carbonized Skeleton");
		addAchievMouseOver("witherSkel", "Change your Buddy into a Wither Skeleton");
		
		addAchievName("wither", "Foes For Friends I");
		addAchievMouseOver("wither", "Change your Buddy into Wither");
		
		addAchievName("ghost", "All Fired Up");
		addAchievMouseOver("ghost", "Change your Buddy into a Blaze");
		
		addAchievName("magma", "Hot Ooze");
		addAchievMouseOver("magma", "Change your Buddy into a Magma Cube");
		
		addAchievName("bat", "Stay Low, Keep High");
		addAchievMouseOver("bat", "Change your Buddy into a .. Bat ?");
		
		addAchievName("rpgSpin", "Rpg Offspring");
		addAchievMouseOver("rpgSpin", "Change your Buddy into the Rpg Spider");
		
		addAchievName("rpgBull", "Rpg Offspring");
		addAchievMouseOver("rpgBull", "Change your Buddy into the Rpg Bull");
		
		addAchievName("rpgBoar", "Rpg Offspring");
		addAchievMouseOver("rpgBoar", "Change your Buddy into the Rpg Boar");
		
		addAchievName("skin", "Esthetic Changes");
		addAchievMouseOver("skin", "Get your buddy a new skin");
		
		addAchievName("endPearl", "The Truth Is Out There");
		addAchievMouseOver("endPearl", "Change your Buddy into an Enderman");
		
		addAchievName("endPortal", "Trough the Portal And the Frames");
		addAchievMouseOver("endPortal", "Go to the end to find more Buddy material");
		
		addAchievName("endDragon", "Foes For Friends II");
		addAchievMouseOver("endDragon", "Change your Buddy in the EnderDragon");
		
		addAchievName("slime", "Sticky Friend");
		addAchievMouseOver("slime", "Change your Buddy into a Slime");
		
		addAchievName("sheep", "Colorful Friends");
		addAchievMouseOver("sheep", "Change your Buddy into a Sheep");

		addAchievName("cat", "A Purring Fish");
		addAchievMouseOver("cat", "Change your Buddy into an Ocelot");

		addAchievName("silverfish", "Philosopher's Phish");
		addAchievMouseOver("silverfish", "Change your Buddy into a SilverFish");

		addAchievName("golem", "Rusty Friend");
		addAchievMouseOver("golem", "Change your Buddy into an Iron Golem");

		addAchievName("snow", "Where's the Pumpkin?");
		addAchievMouseOver("snow", "Change your Buddy into a SnowGolem");

		addAchievName("creeper", "Creeping Death");
		addAchievMouseOver("creeper", "Change your Buddy into a Creeper");

		addAchievName("cow", "Milk For Ants");
		addAchievMouseOver("cow", "Change your Buddy into a Cow");

		addAchievName("mooshroom", "Mootated Cows");
		addAchievMouseOver("mooshroom", "Change your Buddy into a MooShroom");

		addAchievName("spider", "Eight Legged Friend");
		addAchievMouseOver("spider", "Change your Buddy into a Spider");

		addAchievName("skelet", "..'till Death Do You Part ");
		addAchievMouseOver("skelet", "Change your Buddy into a Skeleton");

		addAchievName("chicken", "Scrambled Eggs..For Ants");
		addAchievMouseOver("chicken", "Change your Buddy into a Chicken");

		addAchievName("squid", "Why U No Kraken ?!");
		addAchievMouseOver("squid", "Change your Buddy into a Squid");

		addAchievName("villager", "Knowledge In Your Pocket");
		addAchievMouseOver("villager", "Change your Buddy into a Villager");

		addAchievName("wolf", "Cuteness Overload");
		addAchievMouseOver("wolf", "Change your Buddy into a Wolf");

		
	}

	private void addAchievName(String ach, String name)
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach, name);
	}

	private void addAchievMouseOver(String ach, String desc)
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", desc);
	}
}