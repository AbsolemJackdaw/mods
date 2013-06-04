package redstone.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import redstone.ammo.GatlingAmmo;
import redstone.ammo.PlasmaAmmo;
import redstone.ammo.ThawAmmo;

public class ItemGunHelper {

	public ItemGun theGun;
	public ItemGunHelper(ItemGun gun){
		theGun = gun;
	}
	public void addInfoHelper(String damage, String info, List list, NBTTagCompound nbt){
		list.add(StatCollector.translateToLocal("Base Dmg: "+damage));
		list.add(StatCollector.translateToLocal(info));

		if(nbt != null){
			if(nbt.hasKey("state")){
				list.add(StatCollector.translateToLocal("State : "+ nbt.getString("state")));
				if(theGun.thaw == 0)
					if (nbt.getBoolean("ReCharging")) list.add("UNDERCOOLED");
				if(theGun.thaw == 1)
					if (nbt.getBoolean("ReCharging")) list.add("OVERHEAT");
			}
			if(theGun.meta == 6 || theGun.meta == 7)
				if (nbt.getBoolean("ReCharging")) list.add("OVERHEAT");
			if(nbt.hasKey("EnergyCharge"))
				list.add("Heat: "+Math.floor(nbt.getFloat("EnergyCharge"))+"/"+theGun.maxUse);
			if(nbt.hasKey("load"))
				list.add(StatCollector.translateToLocal("Loaded : "+ String.valueOf(nbt.getInteger("load"))));
		}
	}

	public void spawnMultiple (World world, Entity el1,Entity el2,Entity el3,Entity el4,Entity el5) {
		world.spawnEntityInWorld(el1);
		world.spawnEntityInWorld(el2);
		world.spawnEntityInWorld(el3);
		world.spawnEntityInWorld(el4);
		world.spawnEntityInWorld(el5);
	}

	public void lapisHelper( EntityPlayer player, ItemStack stack2){
		boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack2) > 0;
		if(player.inventory.hasItem(Item.dyePowder.itemID))
		{
			for(int i = 0; i < player.inventory.mainInventory.length; i++){
				ItemStack stack = player.inventory.getStackInSlot(i);
				if(stack != null && stack.getItemDamage() == 4 && stack.getItem().equals(Item.dyePowder))
				{
					if(!flag){
						player.inventory.consumeInventoryItem(stack.getItem().itemID);
						break;
					}
				}
			}
		}
	}

	public void setMeta5Nbt(ItemStack stack){
		if(!stack.hasTagCompound()){
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("status", theGun.thaw);
			if(theGun.thaw == 0){
				theGun.state = "Frozen";
				nbt.setString("state", theGun.state);
			}else{
				theGun.state = "Liquid";
				nbt.setString("state", theGun.state);
			}
			stack.setTagCompound(nbt);
		}else{
			NBTTagCompound nbt = stack.getTagCompound();
			nbt.setInteger("status", theGun.thaw);
			if(theGun.thaw == 0){
				theGun.state = "Frozen";
				nbt.setString("state", theGun.state);
			}else{
				theGun.state = "Liquid";
				nbt.setString("state", theGun.state);
			}
			stack.setTagCompound(nbt);
		}
	}

	public void loadZombieGun(NBTTagCompound nbt, ItemStack stack,EntityPlayer player){
		int l = nbt.getInteger("load");
		switch(l)
		{
		case 0:
			nbt.setInteger("load", 1);	
			theGun.Loaded =1;
			theGun.consume(player);
			break;
		case 1:
			nbt.setInteger("load", 2);		
			theGun.Loaded =2;
			theGun.consume(player);		
			break;
		case 2:
			nbt.setInteger("load", 3);
			theGun.Loaded =3;
			theGun.consume(player);	
			break;
		case 3:
			nbt.setInteger("load", 4);
			theGun.Loaded = 4;
			theGun.consume(player);
			break;
		case 4:
			nbt.setInteger("load", 5);
			theGun.Loaded =5;
			theGun.consume(player);
			stack.setTagCompound(nbt);
			break;
		}
	}

	public boolean ItemCheck(EntityPlayer par3EntityPlayer, int meta, int thaw, int block){

		return meta==0 ? par3EntityPlayer.inventory.hasItem(Item.redstone.itemID) : 
			meta==1 ?par3EntityPlayer.inventory.hasItemStack(new ItemStack(Item.dyePowder,1,4)):
				meta == 2? par3EntityPlayer.inventory.hasItem(Item.rottenFlesh.itemID) : 
					meta == 3? par3EntityPlayer.inventory.hasItem(Item.enderPearl.itemID):
						meta == 4 ? par3EntityPlayer.inventory.hasItem(Item.gunpowder.itemID):
							meta == 5 && thaw == 0? par3EntityPlayer.inventory.hasItem(Item.bucketWater.itemID) :
								meta == 5 && thaw == 1 ? par3EntityPlayer.inventory.hasItem(Item.bucketLava.itemID):
									meta == 6 && block == 1 ? par3EntityPlayer.inventory.hasItem(Block.dirt.blockID):
										meta == 6 && block == 2 ? par3EntityPlayer.inventory.hasItem(Block.cobblestone.blockID):
											meta == 7 ? true : false;
	}
	public boolean findBucket(EntityPlayer player, int parser){
		for(int i = 0; i < player.inventory.mainInventory.length; i++){
			if(i <9){
				if(player.inventory.getStackInSlot(i) != null){
					int objectID =player.inventory.getStackInSlot(i).itemID;

					if(parser == 1){
						if(objectID == Item.bucketWater.itemID){
							theGun.thaw = 0;
							return true;
						}
						if(objectID == Item.bucketLava.itemID){
							theGun.thaw = 1;
							return true;
						}
					}
					if( parser == 3){
						if(objectID == Item.rottenFlesh.itemID){
							theGun.block = 1;
							return true;
						}
					}
					if(parser == 2){
						if(objectID == Block.dirt.blockID){
							theGun.block = 1;
							return true;
						}
						if(objectID == Block.cobblestone.blockID){
							theGun.block = 2;
							return true;
						}

					}
				}
			}
		}
		return false;
	}


	public void shootGatling(ItemStack stack, EntityPlayer player, int block, int thaw, int maxUse){

		boolean shot = false ;
		NBTTagCompound nbt = stack.getTagCompound();

		if(!player.worldObj.isRemote){

			GatlingAmmo gat = new GatlingAmmo(player.worldObj, player, block);

			if(findBucket(player, 2)){
				if ( nbt.getFloat("EnergyCharge") < maxUse && !nbt.getBoolean("ReCharging")) {
					player.worldObj.spawnEntityInWorld(gat);
					nbt.setFloat("EnergyCharge", nbt.getFloat("EnergyCharge")+1.0F);
					shot = true;
				} else {
					if (!nbt.getBoolean("ReCharging"))
						stack.damageItem(5, player);
					nbt.setBoolean("ReCharging", true);
					player.stopUsingItem();
				}
			}
			stack.setTagCompound(nbt);
		}
		if(shot == true && !player.capabilities.isCreativeMode){
			if(block == 2)
				player.inventory.consumeInventoryItem(Block.cobblestone.blockID);
			else if( block == 1)
				player.inventory.consumeInventoryItem(Block.dirt.blockID);
		}
	}

	public NBTTagCompound baseNBT() {
		NBTTagCompound nbt = new NBTTagCompound(); //stack.getTagCompound();
		nbt.setFloat("EnergyCharge", 0.0F);
		nbt.setBoolean("ReCharging", false);
		return nbt;
	}

	public void setMeta7Nbt(ItemStack stack){
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) stack.setTagCompound(baseNBT());
		nbt = stack.getTagCompound();
		if (nbt.getBoolean("ReCharging")) {
			nbt.setFloat("EnergyCharge", nbt.getFloat("EnergyCharge")- 0.1f);
		} else {
			nbt.setFloat("EnergyCharge", nbt.getFloat("EnergyCharge")- 0.3f);	
		}

		if (nbt.getFloat("EnergyCharge") <= 0) {
			nbt.setBoolean("ReCharging", false);
			nbt.setFloat("EnergyCharge", 0.0F);
		}
		stack.setTagCompound(nbt);
	}

	public void shootMeta5(ItemStack stack, int count, EntityPlayer p){
		int time = theGun.getMaxItemUseDuration(stack) - count;
		NBTTagCompound nbt = stack.getTagCompound();
		if (!p.worldObj.isRemote) {
			if ( nbt.getFloat("EnergyCharge") < theGun.maxUse && !nbt.getBoolean("ReCharging")) {
				ThawAmmo var9 = new ThawAmmo(p.worldObj, p, theGun.thaw);
				p.worldObj.spawnEntityInWorld(var9);
				nbt.setFloat("EnergyCharge", nbt.getFloat("EnergyCharge")+1.0F);
			} else {
				if (!nbt.getBoolean("ReCharging"))
					stack.damageItem(5, p);
				nbt.setBoolean("ReCharging", true);
				p.stopUsingItem();
			}
		}
		stack.setTagCompound(nbt);
	}

	public void shootMeta7(ItemStack stack, EntityPlayer p){

		NBTTagCompound nbt = stack.getTagCompound();
		if (!p.worldObj.isRemote) {
			if ( nbt.getFloat("EnergyCharge") < theGun.maxUse && !nbt.getBoolean("ReCharging")) {
				PlasmaAmmo var9 = new PlasmaAmmo(p.worldObj, p);
				p.worldObj.spawnEntityInWorld(var9);
				nbt.setFloat("EnergyCharge", nbt.getFloat("EnergyCharge")+1.0F);
			} else {
				if (!nbt.getBoolean("ReCharging"))
					stack.damageItem(1, p);
				nbt.setBoolean("ReCharging", true);
				p.stopUsingItem();
			}
		}
		stack.setTagCompound(nbt);
	}
}
