package petBuddy;

import petBuddy.entity.BatBuddy;
import petBuddy.entity.BlazeBuddy;
import petBuddy.entity.ChickenBuddy;
import petBuddy.entity.CowBuddy;
import petBuddy.entity.CreeperBuddy;
import petBuddy.entity.EnderManBuddy;
import petBuddy.entity.EntityBuddy;
import petBuddy.entity.GhastBuddy;
import petBuddy.entity.GolemBuddy;
import petBuddy.entity.MooshroomBuddy;
import petBuddy.entity.OcelotBuddy;
import petBuddy.entity.PigBuddy;
import petBuddy.entity.PigManBuddy;
import petBuddy.entity.SheepBuddy;
import petBuddy.entity.SilverFishBuddy;
import petBuddy.entity.SkeletonBuddy;
import petBuddy.entity.SkeletonWBuddy;
import petBuddy.entity.SnowManBuddy;
import petBuddy.entity.SpiderBuddy;
import petBuddy.entity.SpiderCaveBuddy;
import petBuddy.entity.SpiderRpgBuddy;
import petBuddy.entity.SquidBuddy;
import petBuddy.entity.VillagerBuddy;
import petBuddy.entity.WitchBuddy;
import petBuddy.entity.WolfBuddy;
import petBuddy.entity.ZombieBuddy;
import petBuddy.handelers.BuddyCommonProxy;
import petBuddy.handelers.BuddyPacketHandler;
import petBuddy.handelers.PetSpawner;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;



@Mod(modid = "buddyPet", name = "My tiny buddy", version = "1.0")

@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"buddyPet"}, packetHandler = BuddyPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"buddyPet"}, packetHandler = BuddyPacketHandler.class))


public class PetBuddyMain {

	@SidedProxy(serverSide = "petBuddy.handelers.BuddyCommonProxy", clientSide = "petBuddy.handelers.BuddyClientProxy")
	public static BuddyCommonProxy proxy;


	@Init
	public void load (FMLInitializationEvent e){

		EntityRegistry.registerGlobalEntityID(EntityBuddy.class, "TinyClone", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(PigBuddy.class, "BuddyPig", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(CowBuddy.class, "BuddyCow", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(CreeperBuddy.class, "BuddyCreeper", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(BlazeBuddy.class, "BuddyBlaze", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(GhastBuddy.class, "BuddyGhast", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(ZombieBuddy.class, "BuddyZombie", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SpiderBuddy.class, "BuddySpider", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SpiderCaveBuddy.class, "BuddyCaveSpider", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SkeletonBuddy.class, "BuddySkeleton", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SkeletonWBuddy.class, "BuddyWitherSkeleton", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SpiderRpgBuddy.class, "BuddyRpgInvSpider", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SheepBuddy.class, "BuddySheep", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EnderManBuddy.class, "BuddyEnderMan", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SilverFishBuddy.class, "BuddySilverMan", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SnowManBuddy.class, "BuddySnowMan", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(GolemBuddy.class, "BuddyGolem", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(WitchBuddy.class, "BuddyWitch", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(BatBuddy.class, "BuddyBat", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(ChickenBuddy.class, "BuddyChicken", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(MooshroomBuddy.class, "BuddyMooshroom", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(OcelotBuddy.class, "BuddyOcelot", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(SquidBuddy.class, "BuddySquid", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(VillagerBuddy.class, "BuddyVillager", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(WolfBuddy.class, "BuddyWolf", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(PigManBuddy.class, "BuddyPigMan", EntityRegistry.findGlobalUniqueEntityId());

		this.proxy.render();

		GameRegistry.registerPlayerTracker(new PetSpawner());

	}
}