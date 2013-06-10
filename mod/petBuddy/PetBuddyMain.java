package petBuddy;

import java.util.HashMap;

import petBuddy.entity.EntityBuddy;
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

	public static HashMap<String, Integer> playersWithPets = new HashMap();

	@Init
	public void load (FMLInitializationEvent e){

		EntityRegistry.registerGlobalEntityID(EntityBuddy.class, "TinyClone", EntityRegistry.findGlobalUniqueEntityId());
		this.proxy.render();

		GameRegistry.registerPlayerTracker(new PetSpawner());
	}
}