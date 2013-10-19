package redstone.item;

import java.util.List;

import redstone.ammo.RedAmmo;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRedstoneGun extends Item{

	public ItemRedstoneGun(int par1) {
		super(par1);
		this.setMaxDamage(800);
		this.maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return false;
	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("redstonehandguns:" + itemName);
	}

	public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {

		NBTTagCompound nbt = stack.getTagCompound();
		list.add("Base Dmg: "+3);
		list.add("Rapid Fire");
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack){
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack){
		return EnumAction.bow;
	}

	@Override
	public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count) {

		if(count%5 == 0){
			player.playSound("random.fizz", 0.4f, 1f);
		}
		if(count%2 == 0){
			RedAmmo r= new RedAmmo(player.worldObj, player);
			if(player.inventory.hasItem(Item.redstone.itemID)){

				if(!player.worldObj.isRemote)
					player.worldObj.spawnEntityInWorld(r);


				if(!player.capabilities.isCreativeMode){
					player.inventory.consumeInventoryItem(Item.redstone.itemID);
					stack.damageItem(1, player);
				}
			}
		}

		super.onUsingItemTick(stack, player, count);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if(par3EntityPlayer.inventory.hasItem(Item.redstone.itemID))
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

		return par1ItemStack;
	}
}
