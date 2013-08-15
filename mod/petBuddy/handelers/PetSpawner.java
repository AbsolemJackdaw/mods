package petBuddy.handelers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

public class PetSpawner implements IPlayerTracker{

	//	EntityBuddy bud;
	//	float color1;
	//	float color2;
	//	float color3;
	//	
	//	float dragon_color1;
	//	float dragon_color2;
	//	float dragon_color3;
	//	
	//	String name = "null";
	//	String skin = "no skin";

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		FMLLog.getLogger().info("[PETBUDDY MOD INFO] "+player.username + " logged in");

		if(PetBuddyMain.playersWithPets.containsKey(player.username)){
			PetBuddyMain.playersWithPets.remove(player.username);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		FMLLog.getLogger().info("[PETBUDDY MOD INFO] "+player.username + " logged out");

		savePet(player);
		looped = false;	
		}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		FMLLog.getLogger().info("[PETBUDDY MOD INFO] "+player.username + " changed dimension");

		savePet(player);
		looped = false;
		}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {

		FMLLog.getLogger().info("[PETBUDDY MOD INFO] "+player.username + " respawned");
		savePet(player);
		looped = false;
	}

	public boolean looped = false;

	private void savePet(EntityPlayer player){
		if(PetBuddyMain.playersWithPets.containsKey(player.username)){
			if(player.inventory.hasItem(PetBuddyMain.petStatue.itemID)){
				EntityBuddy bud = (EntityBuddy) player.worldObj.getEntityByID(PetBuddyMain.playersWithPets.get(player.username));


				for(int c = 0; c < player.inventory.mainInventory.length; c++){
					if(!looped){
						if(player.inventory.getStackInSlot(c) != null && player.inventory.getStackInSlot(c).getItem().equals(PetBuddyMain.petStatue)){
							ItemStack stack = new ItemStack(PetBuddyMain.petStatue,1);
							NBTTagCompound nbt = new NBTTagCompound();

							if(bud != null){
								nbt.setInteger("guiID", bud.getGuiId());
								nbt.setString("name", bud.getName());
								nbt.setString("skin", bud.getSkinName());

								FMLLog.getLogger().info("[PETBUDDY MOD INFO] Succesfully saved pet to a statue ");
								looped = true;
							}
							stack.setTagCompound(nbt);
							player.inventory.setInventorySlotContents(c, stack);
						}
					}
				}

			}
			PetBuddyMain.playersWithPets.remove(player.username);
		}
	}
}
