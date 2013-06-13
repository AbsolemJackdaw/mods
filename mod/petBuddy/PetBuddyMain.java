package petBuddy;

import java.util.HashMap;

import net.minecraft.block.Block;
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

	public static AchievementPage page1;
	public static HashMap<String, Integer> playersWithPets = new HashMap();

	@PreInit
	public void load (FMLPreInitializationEvent e){
		findBuddy = new Achievement(s(), "startBuddy", 0, 0, new ItemStack(Item.skull,1,3), null).setIndependent().registerAchievement();
		nameBuddy = new Achievement(s(), "name", 0, -2, Item.stick, findBuddy).registerAchievement();
		pigAchieve = new Achievement(s(), "pigBuddy", 2, -1, Item.porkRaw, findBuddy).registerAchievement();
		tinyYou = new Achievement(s(), "tinyBuddy", 3, 2, Item.bread, pigAchieve).registerAchievement();
		zombie = new Achievement(s(), "zombie", 5, 1, Item.rottenFlesh, tinyYou).registerAchievement();
		
		rpgSpider = new Achievement(s(), "rpgSpin", -1, 1, Item.fermentedSpiderEye, pigAchieve).registerAchievement();
		rpgBull = new Achievement(s(), "rpgBoar", 0, 1, Item.porkCooked, pigAchieve).registerAchievement();
		rpgBoar = new Achievement(s(), "rpgBull", 1, 1, Item.leather, pigAchieve).registerAchievement();

		portalNether = new Achievement(s(), "portal", 1, 4, Block.portal, tinyYou).registerAchievement();
		ghast = new Achievement(s(), "ghast", 0, 7, Item.ghastTear, portalNether).registerAchievement();
		pigZombie = new Achievement(s(), "zombPig", 0, 5, Item.netherQuartz, portalNether).registerAchievement();
		wither = new Achievement(s(), "wither", 1, 11, Item.netherStar, portalNether).setSpecial().registerAchievement();
		witherSkelly = new Achievement(s(), "witherSkel", 2, 8, new ItemStack(Item.skull,1,1), portalNether).registerAchievement();
		ghost = new Achievement(s(), "ghost", 2, 6, Item.blazePowder, portalNether).registerAchievement();
		magma = new Achievement(s(), "magma", 0, 9, Item.magmaCream, portalNether).registerAchievement();
		bat = new Achievement(s(), "bat", 2, 10, Item.netherStalkSeeds, portalNether).registerAchievement();

		this.addAchievementLocalizations();
		
		page1 = new AchievementPage("Buddy", findBuddy, nameBuddy,pigAchieve,tinyYou,zombie, portalNether,
				ghast,pigZombie, wither, witherSkelly, ghost, rpgSpider, rpgBull, rpgBoar, magma, bat);
		
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