package offlineSkin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import petBuddy.handelers.PetSpawner;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;



@Mod(modid = "skins", name = "my skins", version = "0.0")

@NetworkMod(clientSideRequired = true, serverSideRequired = false)


public class main {

	//	@SidedProxy(serverSide = "petBuddy.handelers.BuddyCommonProxy", clientSide = "petBuddy.handelers.BuddyClientProxy")
	//	public static BuddyCommonProxy proxy;


	@Init
	public void load (FMLInitializationEvent e){

//		GameRegistry.registerPlayerTracker(new SkinAplyer());
	}
}