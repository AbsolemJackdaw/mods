package petBuddy;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import petBuddy.block.PetShrine;
import petBuddy.block.TEShrine;
import petBuddy.entity.EntityBuddy;
import petBuddy.handelers.BuddyCommonProxy;
import petBuddy.handelers.BuddyPacketHandler;
import petBuddy.handelers.PetSpawner;
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

		petStatue = new PetStatue(2564).setUnlocalizedName("petstatue");
		shrine =  new PetShrine(1000).setUnlocalizedName("petshrine").setCreativeTab(CreativeTabs.tabDecorations);
		
		LanguageRegistry.addName(petStatue, "Unity Statue");
		LanguageRegistry.addName(shrine, "Shrine of Unity");

		GameRegistry.registerBlock(shrine,"PetShrine");
		GameRegistry.registerTileEntity(TEShrine.class, "petShrineTE");
		
		GameRegistry.addRecipe(new ItemStack(shrine,1),new Object[] {"BBB", " B ", "TBT", 'B',Block.cobblestone, 'T', Block.torchWood });

		GameRegistry.addRecipe(new ItemStack(petStatue,1),new Object[] {" C ", "ECE", " B ", 'B',Block.cobblestone, 'C', Block.blockClay, 'E', Item.stick });
		
		EntityRegistry.registerGlobalEntityID(EntityBuddy.class, "TinyClone", EntityRegistry.findGlobalUniqueEntityId());

		this.proxy.render();

		GameRegistry.registerPlayerTracker(new PetSpawner());
	}

	


//	public static void spawnPet(EntityPlayer player, float color1, float color2, float color3, 
//			float dragon_color1, float dragon_color2, float dragon_color3, String skin, String name, BuddyBase bud){
//
//		EntityBuddy buddy = new EntityBuddy(player.worldObj, player);
//		for(int i = 2; i > 0; i--){
//			if(player.worldObj.getBlockId((int)player.posX+i, (int)player.posY, (int)player.posZ+i) == 0){
//				buddy.setLocationAndAngles(player.posX+i, player.posY, player.posZ+i, 0.0F, 0.0F);
//				break;
//			}else if(player.worldObj.getBlockId((int)player.posX-i, (int)player.posY, (int)player.posZ+i) == 0){
//				buddy.setLocationAndAngles(player.posX-i, player.posY, player.posZ+i, 0.0F, 0.0F);
//				break;
//			}else if(player.worldObj.getBlockId((int)player.posX+i, (int)player.posY, (int)player.posZ-i) == 0){
//				buddy.setLocationAndAngles(player.posX+i, player.posY, player.posZ-i, 0.0F, 0.0F);
//				break;
//			}else if(player.worldObj.getBlockId((int)player.posX-i, (int)player.posY, (int)player.posZ-i) == 0){
//				buddy.setLocationAndAngles(player.posX-i, player.posY, player.posZ-i, 0.0F, 0.0F);
//				break;
//			}	
//			else{
//				buddy.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);	
//			}
//		}
//
//		NBTTagCompound nbt = PetBuddyMain.proxy.getLoginPet(player.username);
//
//		int loginid = nbt.getInteger("pet_login_id:"+player.username);
//
//		if(nbt.hasKey("pet_color1:"+player.username) && nbt.hasKey("pet_color2:"+player.username) && nbt.hasKey("pet_color3:"+player.username)){
//			color1 = nbt.getFloat("pet_color1:"+player.username);
//			color2 = nbt.getFloat("pet_color2:"+player.username);
//			color3 = nbt.getFloat("pet_color3:"+player.username);
//		}
//		if(nbt.hasKey("pet_dragon_color1:"+player.username) && nbt.hasKey("pet_dragon_color2:"+player.username) && nbt.hasKey("pet_dragon_color3:"+player.username)){
//			dragon_color1 = nbt.getFloat("pet_dragon_color1:"+player.username);
//			dragon_color2 = nbt.getFloat("pet_dragon_color2:"+player.username);
//			dragon_color3 = nbt.getFloat("pet_dragon_color3:"+player.username);
//		}
//		if(nbt.hasKey("pet_name:"+player.username)){
//			name = nbt.getString("pet_name:"+player.username).equals("null") ||
//					nbt.getString("pet_name:"+player.username).equals("") ? player.username+"'s Buddy" :
//						nbt.getString("pet_name:"+player.username);
//		}
//		if(nbt.hasKey("pet_skin:"+player.username)){
//			skin = nbt.getString("pet_skin:"+player.username).equals("null") ||
//					nbt.getString("pet_skin:"+player.username).toLowerCase().equals("i")||
//					nbt.getString("pet_skin:"+player.username).toLowerCase().equals("me")? player.username :
//						nbt.getString("pet_skin:"+player.username);
//		}
//		
//		PetBuddyMain.proxy.setName(name);
//		PetBuddyMain.proxy.setSkinName(skin);
//		PetBuddyMain.proxy.setGuiId(loginid);
//		PetBuddyMain.proxy.setColor(color1,color2,color3);
//		PetBuddyMain.proxy.setDragonColor(dragon_color1,dragon_color2,dragon_color3);
//
//		buddy.writeEntityToNBT(nbt);
//
//		try{
//			//if(!player.worldObj.isRemote){
//			player.worldObj.spawnEntityInWorld(buddy);
//			//}
//		}finally{
//			bud = (EntityBuddy) player.worldObj.getEntityByID(buddy.entityId);		
//			PetBuddyMain.playersWithPets.put(buddy.getOwnerName(), buddy.entityId);
//		}
//	}
	
	/**Used in animating the red glow for shrines*/
	public static float getSysTimeF()
	{
		return (float)(System.currentTimeMillis() - time) / 50F;
	}
}