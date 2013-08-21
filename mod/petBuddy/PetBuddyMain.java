package petBuddy;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import petBuddy.block.PetShrine;
import petBuddy.block.TEShrine;
import petBuddy.entity.EntityBuddy;
import petBuddy.handelers.BuddyCommonProxy;
import petBuddy.handelers.BuddyPacketHandler;
import petBuddy.handelers.PetSpawner;
import petBuddy.handelers.gen.ShrineGen;
import petBuddy.item.PetStatue;
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


/* TODO Add config for item ID's. */

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
	
	public static final PetAchievements pa = new PetAchievements();
	
	private static final long time = System.currentTimeMillis();
	
	public static Item petStatue;
	public static Item shrineItem;

	public static Block shrine;

	@EventHandler
	public void load (FMLPreInitializationEvent e){
		
		pa.loadPetAchievments();
	}

	@EventHandler
	public void load (FMLInitializationEvent e){

		GameRegistry.registerWorldGenerator(new ShrineGen());
		
		petStatue = new PetStatue(2564).setUnlocalizedName("petstatue");
		shrine =  new PetShrine(1000).setUnlocalizedName("petshrine").setHardness(7).setResistance(10).setCreativeTab(CreativeTabs.tabDecorations);
		
		LanguageRegistry.addName(petStatue, "Unity Statue");
		LanguageRegistry.addName(shrine, "Shrine of Unity");

		GameRegistry.registerBlock(shrine,"PetShrine");
		GameRegistry.registerTileEntity(TEShrine.class, "petShrineTE");
		
//		GameRegistry.addRecipe(new ItemStack(shrine,1),new Object[] {"BBB", " B ", "TBT", 'B',Block.cobblestone, 'T', Block.torchWood });

		GameRegistry.addRecipe(new ItemStack(petStatue,1),new Object[] {" C ", "ECE", " B ", 'B',Block.cobblestone, 'C', Block.blockClay, 'E', Item.stick });
		
		EntityRegistry.registerGlobalEntityID(EntityBuddy.class, "TinyClone", EntityRegistry.findGlobalUniqueEntityId());

		this.proxy.render();

		GameRegistry.registerPlayerTracker(new PetSpawner());
	}
	
	/**Used in animating the red glow for shrines*/
	public static float getSysTimeF()
	{
		return (float)(System.currentTimeMillis() - time) / 50F;
	}
}