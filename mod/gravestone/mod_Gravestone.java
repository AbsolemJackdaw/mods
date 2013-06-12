package gravestone;

import gravestone.bones.BlockBones;
import gravestone.bones.ItemSkulls;
import gravestone.bones.TEBones;
import gravestone.grave.BlockGrave;
import gravestone.grave.ItemGrave;
import gravestone.grave.TEGrave;
import gravestone.handelers.CommonProxy;
import gravestone.handelers.DeathEvent;
import gravestone.handelers.GuiHandler;
import gravestone.handelers.PacketHandler;
import gravestone.handelers.PlayerTracker;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutput;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.BlockPortal;


@Mod(modid = "GraveStoneMod", name = "GraveStone", version = "Beta")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"graveData"}, packetHandler = PacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"graveData"}, packetHandler = PacketHandler.class))

public class mod_Gravestone{

	public static mod_Gravestone instance;
	public ItemStack[] stack;
	public int renderID = RenderingRegistry.getNextAvailableRenderId();
	Random rand = new Random();
	public static Block gravestone;
	public static Block bones;
	public static Item graveItem;
	public static Item bonesItem;
	@SidedProxy(serverSide = "gravestone.handelers.CommonProxy", clientSide = "gravestone.handelers.ClientProxy")
	public static CommonProxy proxy;

	public mod_Gravestone() {
		instance = this;
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		ConfigClass.instance.loadConfig(event.getSuggestedConfigurationFile());
	}
	@Init
	public void load(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new DeathEvent());

		gravestone = new BlockGrave(ConfigClass.instance.graveBlock).setBlockUnbreakable().setResistance(5000f).setLightOpacity(0).setLightValue(0f).setUnlocalizedName("GraveStone");
		bones = new BlockBones(ConfigClass.instance.bonesBlock, Material.ground).setHardness(10f);
		graveItem = new ItemGrave(ConfigClass.instance.grave).setUnlocalizedName("graveItem").setCreativeTab(CreativeTabs.tabDecorations);
		bonesItem = new ItemSkulls(ConfigClass.instance.bones).setUnlocalizedName("bonesItem").setCreativeTab(CreativeTabs.tabDecorations);

		GameRegistry.registerBlock(gravestone,"GraveStone");
		LanguageRegistry.addName(gravestone, "GraveStone");
		GameRegistry.registerBlock(bones,"Bones");
		LanguageRegistry.addName(bones, "Bones");
		LanguageRegistry.addName(graveItem, "Grave");
		LanguageRegistry.addName(bonesItem, "Bone and Skull");

		GameRegistry.addRecipe(new ItemStack(graveItem,1),new Object[] {"BBB", " B ", " B ", 'B',Block.cobblestone });
		GameRegistry.addRecipe(new ItemStack(bonesItem,1),new Object[] {"BBB", " B ", " B ", 'B',Item.bone });

		GameRegistry.registerTileEntity(TEGrave.class, "grave");
		GameRegistry.registerTileEntity(TEBones.class, "playerbody");
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerPlayerTracker(new PlayerTracker());
		proxy.registerRender();
	}

	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
		commandManager.registerCommand(new gravestone.handelers.CommandPanel());
	}
        
	public void buildGravestone(EntityPlayer player, InventoryPlayer inv ) {

		int x = MathHelper.floor_double(player.posX),
				y = MathHelper.floor_double(player.posY),
				z = MathHelper.floor_double(player.posZ);
                if(player.worldObj.isAirBlock(x, y, z)){
                    if(y < 0){
                        return;
                    }
                    while(player.worldObj.isAirBlock(x, y, z)){
                        y--;
                    }
                }
                if(player.worldObj.getBlockId(x, y, z) == Block.lavaStill.blockID || player.worldObj.getBlockId(x, y, z) == Block.lavaMoving.blockID){
                    return;
                }
                int scanTime = 0;
                while(scanTime < 100 && (!player.worldObj.getBlockMaterial(x, y, z).isSolid() || !player.worldObj.getBlockMaterial(x, y - 1, z).isSolid())){
                    scanTime++;
                    if(y < 0){
                        return;
                    }
                    //Diagonal movement, avoids portal bugs
                    x++;
                    z++;
                    while(player.worldObj.isAirBlock(x, y, z)){
                        if(y < 0){
                            return;
                        }
                        y--;
                    }
                    while(!player.worldObj.isAirBlock(x, y + 1, z) && y < 512){
                        y++;
                    }
                }
                if(scanTime == 100){
                    //never found a plot.
                    return;
                }
                y+=1;

		player.worldObj.setBlock(x, y, z, gravestone.blockID);
		TileEntity te = player.worldObj.getBlockTileEntity(x, y, z);

		int c = rand.nextInt(2);
		if(c == 0)
		{
			player.worldObj.setBlock(x, y-2, z, bones.blockID);
			player.worldObj.setBlockTileEntity(x, y-2, z,new TEBones());
		}

		if(te != null) {
			try {
				TEGrave tegrave = (TEGrave)te;
				tegrave.setName(player.username);
				tegrave.setMeta(proxy.getRenderID(player.username));
				tegrave.setPlayer(player);
				tegrave.dropItems();
				if(stack != null)
					tegrave.setItems(stack);

				for(int id = 0; id <inv.getSizeInventory(); id++)
				{
                                        ItemStack is = inv.getStackInSlot(id);
					if(is != null && id < tegrave.getSizeInventory())
					{
						tegrave.setInventorySlotContents(id, is);
                                                inv.setInventorySlotContents(id, null);
					}
                                        
				}
			}
			catch(Throwable e)
			{
				e.printStackTrace();
			}
		}
	}
}