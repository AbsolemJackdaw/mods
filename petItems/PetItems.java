package petItems;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.EnumHelper;
import petItems.entity.EntityToolPet;
import petItems.itemEater.ItemEaterAxe;
import petItems.itemEater.ItemEaterPick;
import petItems.itemEater.ItemEaterSpade;
import petItems.itemEater.ItemEaterSword;
import petItems.itemEater.Items;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid = "petItems", name = "Hungry Tools", version = "1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"petTools"}, packetHandler = PetPacket.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"petTools"}, packetHandler = PetPacket.class))


public class PetItems {

	@SidedProxy(serverSide = "petItems.PetCommonPlatform", clientSide = "petItems.PetClientPlatform")
	public static PetCommonPlatform proxy;

	public static Item hungerAxe;
	public static Item hungerPick;
	public static Item hungerSword;
	public static Item hungerSpade;
	public static Item Upgrade6;
	public static Item Upgrade7;
	public static Item Upgrade8;

	static EnumToolMaterial spade = EnumHelper.addToolMaterial("hungerSpade", 1, 0, 1.0f, 2, 0);
	static EnumToolMaterial axe = EnumHelper.addToolMaterial("hungerAxe", 1, 0, 1.0f, 2, 0);
	static EnumToolMaterial pick = EnumHelper.addToolMaterial("hungerPick", 2, 0, 1.0f, 2, 0);
	static EnumToolMaterial sword = EnumHelper.addToolMaterial("hungerPick", 2, 0, 1.0f, 4, 0);

	/*Blocks Eaten by Spade, pick and axe*/
	private Block[] eatable = { Block.dirt, Block.cobblestone, Block.oreIron, Block.oreGold,Block.oreLapis, Block.blockRedstone};
	private Block[] eatWood = { Block.planks, Block.woodenButton, Block.workbench, Block.chest, Block.chestTrapped, Block.wood};

	/*Items eaten by sword*/
	private Item[] eatableItem = {Item.ingotIron, Item.emerald, Item.diamond, Item.ingotGold, Item.rottenFlesh, Item.bone};

	@Init
	public void load (FMLInitializationEvent e){

		// I should make a config file for the ID's
		hungerAxe = new ItemEaterAxe(9545,axe,eatWood).setUnlocalizedName("hungerAxe").setCreativeTab(CreativeTabs.tabTools);
		hungerPick = new ItemEaterPick(9546, pick, eatable).setUnlocalizedName("hungerPick").setCreativeTab(CreativeTabs.tabTools);
		hungerSword = new ItemEaterSword(9547, sword, eatableItem).setUnlocalizedName("hungerSword").setCreativeTab(CreativeTabs.tabCombat);
		hungerSpade = new ItemEaterSpade(9548, spade, eatable).setUnlocalizedName("hungerSpade").setCreativeTab(CreativeTabs.tabTools);
		Upgrade6 = new Items(9549).setUnlocalizedName("Upgrade6").setUnlocalizedName("upgrade6").setCreativeTab(CreativeTabs.tabMaterials);
		Upgrade7 = new Items(9550).setUnlocalizedName("Upgrade7").setUnlocalizedName("upgrade7").setCreativeTab(CreativeTabs.tabMaterials);
		Upgrade8 = new Items(9551).setUnlocalizedName("Upgrade8").setUnlocalizedName("upgrade8").setCreativeTab(CreativeTabs.tabMaterials);

		LanguageRegistry.addName(hungerAxe, "Hungry Axe");
		LanguageRegistry.addName(hungerPick, "Hungry Pickaxe");
		LanguageRegistry.addName(hungerSword, "Hungry Sword");
		LanguageRegistry.addName(hungerSpade, "Hungry Shovel");
		LanguageRegistry.addName(Upgrade6, "Super Upgrade");
		LanguageRegistry.addName(Upgrade7, "Giga Upgrade");
		LanguageRegistry.addName(Upgrade8, "Omega Upgrade");

		EntityRegistry.registerGlobalEntityID(EntityToolPet.class, "ToolPet", EntityRegistry.findGlobalUniqueEntityId(), 0x00ff00, 0xff00ff);
		
		addChestLoot(new ItemStack(hungerAxe), 1, 1, 30);
		addChestLoot(new ItemStack(hungerSword), 1, 1, 30);
		addChestLoot(new ItemStack(hungerSpade), 1, 1, 30);
		addChestLoot(new ItemStack(hungerPick), 1, 1, 30);
		
		addChestLoot(new ItemStack(Upgrade6), 1, 1, 10);
		addChestLoot(new ItemStack(Upgrade7), 1, 1, 10);
		addChestLoot(new ItemStack(Upgrade8), 1, 1, 10);
		
		this.proxy.render();
	}

	public void addChestLoot(ItemStack is, int min, int max, int rarity) {
		WeightedRandomChestContent chestGen = new WeightedRandomChestContent(is.copy(), min, max, rarity);

		ChestGenHooks.getInfo("dungeonChest").addItem(chestGen);
		ChestGenHooks.getInfo("villageBlacksmith").addItem(chestGen);
		ChestGenHooks.getInfo("pyramidJungleChest").addItem(chestGen);
		ChestGenHooks.getInfo("pyramidDesertyChest").addItem(chestGen);
		ChestGenHooks.getInfo("mineshaftCorridor").addItem(chestGen);

	}
}