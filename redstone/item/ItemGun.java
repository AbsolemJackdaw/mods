package redstone.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import redstone.ammo.AranAmmo;
import redstone.ammo.BlueAmmo;
import redstone.ammo.EndAmmo;
import redstone.ammo.GreenAmmo;
import redstone.ammo.RedAmmo;

public class ItemGun extends Item {

	public int meta;
	public static int thaw = 0;
	public static int block = 0;
	public String state = "";
	public int Loaded;
	public int maxUse;
	EntityPlayer meta7Player;

	public ItemGunHelper helper;

	public ItemGun(int par1, int meta, int overFlow) {
		super(par1);
		helper = new ItemGunHelper(this);
		this.maxStackSize = 1;
		this.maxUse = overFlow;
		switch(meta)
		{
		case 0:
			this.setMaxDamage(800);
			break;
		case 1:
			this.setMaxDamage(300);
			break;
		case 2:
			this.setMaxDamage(350);
			break;
		case 3:
			this.setMaxDamage(200);
			break;
		case 4:
			this.setMaxDamage(250);
			break;
		case 5:
			this.setMaxDamage(600);
			break;
		case 6:
			this.setMaxDamage(200);
			break;
		case 7:
			this.setMaxDamage(0);
			break;
		}
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.meta = meta;

	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {

		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("RedStoneHandGuns:" + itemName);

	}

	public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {
		NBTTagCompound nbt = stack.getTagCompound();
		switch(meta){
		case 0:
			helper.addInfoHelper("3", "Rapid Fire", list, nbt);
			break;
		case 1:
			helper.addInfoHelper("8", "Medium Damage", list, nbt);
			break;
		case 2:
			helper.addInfoHelper("5", "Wide Range", list, nbt);
			break;
		case 3:
			helper.addInfoHelper("14", "Long Distance Shot", list, nbt);
			break;
		case 4:
			helper.addInfoHelper("8", "Explosive Shot", list, nbt);
			break;
		case 5:
			helper.addInfoHelper("4", "Switchable State", list, nbt);
			break;
		case 6:
			helper.addInfoHelper("1", "Gatling Fire", list, nbt);
			break;
		case 7:
			helper.addInfoHelper("6", "No Ammo", list, nbt);
			break;
		}
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
		boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

		RedAmmo r = new RedAmmo(par2World,par3EntityPlayer);
		BlueAmmo b = new BlueAmmo(par2World,par3EntityPlayer);
		GreenAmmo g = new GreenAmmo(par2World,par3EntityPlayer,0);
		EndAmmo e = new EndAmmo(par2World,par3EntityPlayer);
		AranAmmo a = new AranAmmo(par2World, par3EntityPlayer);

		if (flag || helper.ItemCheck(par3EntityPlayer, meta, thaw, block))
		{
			float f = (float)j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;
			if ((double)f < 0.1D)
				return;

			if (f >= 5.0F){
				r.damage += 2;
				b.damage += 3;
				g.damage += 4;
				e.damage += 5;
				a.damage += 3;
			}

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
			if (k > 0){
				r.damage += 2+(double)k*0.5d+0.5d;
				b.damage += 2+(double)k*0.5d+0.5d;
				g.damage += 2+(double)k*0.5d+0.5d;
				e.damage += 2+(double)k*0.5d+0.5d;
				a.damage += 2+(double)k*0.5d+0.5d;
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0){
				r.setFire(100);
				b.setFire(120);
				g.setFire(140);
				e.setFire(160);
				a.setFire(100);
			}

			par1ItemStack.damageItem(1, par3EntityPlayer);

			if (!par2World.isRemote && f >= 1.0f || !par2World.isRemote && meta == 2)
			{
				par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
				switch(meta)
				{
				case 0:
					par2World.spawnEntityInWorld(r);
					par3EntityPlayer.inventory.consumeInventoryItem(Item.redstone.itemID);
					break;
				case 1: 
					par2World.spawnEntityInWorld(b);
					helper.lapisHelper(par3EntityPlayer, par1ItemStack);
					break;
				case 2: 
					GreenAmmo g2 = new GreenAmmo(par2World, par3EntityPlayer,-1);
					GreenAmmo g3 = new GreenAmmo(par2World, par3EntityPlayer, 1);
					GreenAmmo g4 = new GreenAmmo(par2World, par3EntityPlayer, 0.5d);
					GreenAmmo g5 = new GreenAmmo(par2World, par3EntityPlayer, -0.5d);
					helper.spawnMultiple(par2World, g, g2, g3, g4, g5);
					if(par1ItemStack.getTagCompound() != null){
						NBTTagCompound tag = par1ItemStack.getTagCompound();
						tag.setInteger("load", 0);
						Loaded = 0;
						par1ItemStack.setTagCompound(tag);
					}
					break;
				case 3: 
					par2World.spawnEntityInWorld(e);
					if(!par3EntityPlayer.capabilities.isCreativeMode)
						par3EntityPlayer.inventory.consumeInventoryItem(Item.enderPearl.itemID);
					break;
				case 4: 
					par2World.spawnEntityInWorld(a);
					if(!par3EntityPlayer.capabilities.isCreativeMode)
						par3EntityPlayer.inventory.consumeInventoryItem(Item.gunpowder.itemID);
					break;
				default:
					break;
				}
			}
		}
	}

	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		return par1ItemStack;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack){
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack){
		return EnumAction.bow;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if( meta == 7){
			helper.shootMeta7(par1ItemStack, par3EntityPlayer);
			meta7Player = par3EntityPlayer;
		}
		else if(meta == 5 && helper.findBucket(par3EntityPlayer,1)||
				meta == 6 && helper.findBucket(par3EntityPlayer, 2)){
			if(meta == 5)
				helper.setMeta5Nbt(par1ItemStack);
			if (par3EntityPlayer.isUsingItem()) {
				par3EntityPlayer.stopUsingItem();
			} else {
				par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));	
			}
		}
		else if (meta == 5 && !helper.findBucket(par3EntityPlayer,1)
				||meta == 6 && !helper.findBucket(par3EntityPlayer, 2)){
			if(!par2World.isRemote)
				par3EntityPlayer.sendChatToPlayer("You dont have ammo in your hotbar");
		}
		else if (meta == 2 &&  Loaded <5)
		{
			if(!par2World.isRemote){
				NBTTagCompound nbt = par1ItemStack.getTagCompound();	
				if(par3EntityPlayer.inventory.hasItem(Item.rottenFlesh.itemID) && helper.findBucket(par3EntityPlayer, 3)){
					if(nbt != null){
						helper.loadZombieGun(nbt, par1ItemStack, par3EntityPlayer);
					}else{
						nbt = new NBTTagCompound();
						nbt.setInteger("load", 0);
						Loaded =0;  
						par1ItemStack.setTagCompound(nbt);
						consume(par3EntityPlayer);
					}
				}else if(!par3EntityPlayer.inventory.hasItem(Item.rottenFlesh.itemID)){
					if(!par2World.isRemote){
						if(nbt != null){
							if(nbt.hasKey("load")){
								par3EntityPlayer.sendChatToPlayer("You still need "+(5-nbt.getInteger("load"))+" Rotten Flesh to fully charge");
							}else{
								par3EntityPlayer.sendChatToPlayer("You still need "+5+" Rotten Flesh to fully charge");
							}
						}else{
							par3EntityPlayer.sendChatToPlayer("You still need "+5+" Rotten Flesh to fully charge");
						}
					}
				}
			}
		}
		else if( meta == 0)
		{
			RedAmmo r= new RedAmmo(par2World,par3EntityPlayer);
			if(!par2World.isRemote && par3EntityPlayer.inventory.hasItem(Item.redstone.itemID)){

				par2World.spawnEntityInWorld(r);
				if(!par3EntityPlayer.capabilities.isCreativeMode){
					par3EntityPlayer.inventory.consumeInventoryItem(Item.redstone.itemID);
					par1ItemStack.damageItem(1, par3EntityPlayer);
				}
			}
		}
		else if (par3EntityPlayer.capabilities.isCreativeMode || helper.ItemCheck(par3EntityPlayer, meta, thaw, block))
		{
			if(meta == 2 && Loaded < 5|| meta == 7){

			}else{
				par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
			}
		}
		return par1ItemStack;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getItemEnchantability()
	{
		return 30;
	}

	public void consume(EntityPlayer par3EntityPlayer){
		if(!par3EntityPlayer.capabilities.isCreativeMode)
			par3EntityPlayer.inventory.consumeInventoryItem(Item.rottenFlesh.itemID);
	}

	public void onCreated(ItemStack stack, World world, EntityPlayer p) 
	{
		if(meta == 5 || meta == 6 || meta == 7){
			stack.setTagCompound(helper.baseNBT());
		}
	}	

	@Override
	public void onUsingItemTick(ItemStack stack, EntityPlayer p, int count)
	{
		if(meta == 5){
			helper.shootMeta5(stack, count, p);
		}
		if(meta ==6){
			helper.shootGatling(stack, p, block, thaw, this.maxUse);
		}		
	}

	public void onUpdate(ItemStack stack, World w, Entity e, int par4, boolean par5) 
	{
		if(meta == 5 || meta == 6 || meta == 7){
			NBTTagCompound nbt = stack.getTagCompound();
			if (nbt == null) stack.setTagCompound(helper.baseNBT());
			nbt = stack.getTagCompound();
			if (nbt.getBoolean("ReCharging")) {
				nbt.setFloat("EnergyCharge", nbt.getFloat("EnergyCharge")- (meta == 5 ? 0.15F : meta == 6 ? 0.2f : 0.05f));
			} else { 
				nbt.setFloat("EnergyCharge", nbt.getFloat("EnergyCharge")- (meta == 5? 0.05F : meta == 6 ? 0.1f : 0.08f));	
			}
			if (nbt.getFloat("EnergyCharge") <= 0) {
				nbt.setBoolean("ReCharging", false);
				nbt.setFloat("EnergyCharge", 0.0F);
			}
			if(nbt != null && nbt.hasKey("EnergyCharge")){
				if(meta7Player != null){
					if(nbt.getFloat("EnergyCharge") > 29){
						if(!w.isRemote)
							meta7Player.attackEntityFrom(DamageSource.causePlayerDamage(meta7Player), 2);
						nbt.setFloat("EnergyCharge", 29);
						nbt.setBoolean("ReCharging", true);
					}
				}
			}	
		}
	}
}