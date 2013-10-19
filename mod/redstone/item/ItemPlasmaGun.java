package redstone.item;

import java.util.List;

import redstone.ammo.PlasmaAmmo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemPlasmaGun extends Item{


	public ItemPlasmaGun(int par1) {
		super(par1);
		setMaxDamage(15000);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("redstonehandguns:" + itemName);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {

		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}


	@Override
	public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count) {
		super.onUsingItemTick(stack, player, count);

		stack.damageItem(1, player);
		PlasmaAmmo var9 = new PlasmaAmmo(player.worldObj, player);
		player.worldObj.spawnEntityInWorld(var9);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		par3List.add("Base Dmg: 6");
		par3List.add("No Ammo");
	}
}
