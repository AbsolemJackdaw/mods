package charms;

import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;



@Mod(modid = "Charms", name = "Health Charms", version = "5.0")

@NetworkMod(clientSideRequired = true, serverSideRequired = false)
/*,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"charms"}, packetHandler = BuddyPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"charms"}, packetHandler = BuddyPacketHandler.class))
 */

public class Core {

	@SidedProxy(serverSide = "charms.CharmHUDCommonRenderer", clientSide = "charms.CharmHUDClientRenderer")
	public static CharmHUDCommonRenderer proxy;

	public static Item charmTierI;
	public static Item charmTierII;
	public static Item charmTierIII;
	public static Item charmTierIV;
	public static Item charmTierV;
	
	public static DynamicTexture icons;
	public static DynamicTexture charmBar;
	

	@EventHandler
	public void load (FMLPreInitializationEvent e){

		ConfigClass.instance.loadConfig(e.getSuggestedConfigurationFile());
		
		proxy.register();
		try {
			icons = new DynamicTexture(ImageIO.read(getClass().getResourceAsStream("/assets/minecraft/textures/gui/icons.png")));
			charmBar = new DynamicTexture(ImageIO.read(getClass().getResourceAsStream("/charms/hud.png")));
		} catch (IOException c) {
			c.printStackTrace();
		}
		MinecraftForge.EVENT_BUS.register(new CharmRes());
		
		charmTierI = new Charm(845, 10*2,0xc1c1c1,1).setUnlocalizedName("charmTierI");
		charmTierII = new Charm(846, 15*2, 0xffe083,2).setUnlocalizedName("charmTierII");
		charmTierIII = new Charm(847, 30*2, 0xdfd8cf,3).setUnlocalizedName("charmTierIII");
		charmTierIV = new Charm(848, 70*2, 0xd1fbf3,4).setUnlocalizedName("charmTierIV");
		charmTierV = new Charm(849, 100*2, 0x9bffcc,5).setUnlocalizedName("charmTierV");
		
		LanguageRegistry.addName(charmTierI, "Iron Charm");
		LanguageRegistry.addName(charmTierII, "Gold Charm");
		LanguageRegistry.addName(charmTierIII, "Quartz Charm");
		LanguageRegistry.addName(charmTierIV, "Emerald Charm");
		LanguageRegistry.addName(charmTierV, "Diamond Charm");
	}

	@EventHandler
	public void load (FMLInitializationEvent e){
		

		GameRegistry.addShapelessRecipe(new ItemStack(charmTierI), new Object[]{Item.paper, new ItemStack(Item.dyePowder, 1, 0), 
			Item.ingotIron, Item.ingotIron, Item.ingotIron});
		GameRegistry.addShapelessRecipe(new ItemStack(charmTierII), new Object[]{charmTierI, Item.ingotGold, Item.ingotGold,
			new ItemStack(Item.dyePowder, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(charmTierIII), new Object[]{charmTierII, Item.netherQuartz, 
			Item.netherQuartz, Item.netherQuartz, Item.netherQuartz, new ItemStack(Item.dyePowder, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(charmTierIV), new Object[]{charmTierIII, Item.emerald, Item.emerald
			, new ItemStack(Item.dyePowder, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(charmTierV), new Object[]{charmTierIV, Item.diamond
			, new ItemStack(Item.dyePowder, 1, 0)});

	}
}