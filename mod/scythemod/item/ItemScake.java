package scythemod.item;

import scythemod.entity.EntityThrowCake;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemScake extends Item 
{
	public ItemScake(int par1)
	{
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(99);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("DSM:" + itemName);
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.eat;
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 5;
	}
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		EntityThrowCake var8 = new EntityThrowCake(par2World, par3EntityPlayer);

		if(par3EntityPlayer.getFoodStats().needFood() == false && !par2World.isRemote)
		{
			if (!par3EntityPlayer.capabilities.isCreativeMode)
			{
				par1ItemStack.damageItem(3, par3EntityPlayer);
			}

			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			par2World.spawnEntityInWorld((var8));
		}

		if(par3EntityPlayer.getFoodStats().needFood() == true && !par2World.isRemote)
		{
			par1ItemStack.damageItem(5, par3EntityPlayer);
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;
	}
	public ItemStack onFoodEaten(ItemStack var1, World var2, EntityPlayer var3)
	{
		var3.getFoodStats().setFoodSaturationLevel(5.0F);
		var3.getFoodStats().setFoodLevel(var3.getFoodStats().getFoodLevel()+2);
		return var1;

	}

	public static void regenTimer( ItemStack itemstack, int duration, int regenAmount, EntityLiving entity ) {
		if( itemstack.stackTagCompound == null ) {
			itemstack.setTagCompound( new NBTTagCompound() );
		}

		if( !itemstack.stackTagCompound.hasKey( "regenTimer" ) ) {
			itemstack.stackTagCompound.setInteger( "regenTimer", duration );
			return;
		}

		int currentTick = itemstack.stackTagCompound.getInteger( "regenTimer" );

		if( itemstack.getItemDamage() > 0 ) {
			currentTick--;
			System.out.println(currentTick);

			if( currentTick == 0 ) {
				currentTick = duration;
				if( itemstack.getItemDamage() > 0 )
				{
					itemstack.damageItem( -1 * regenAmount, entity );

				}
			}
		}
		else
			currentTick = duration;

		itemstack.stackTagCompound.setInteger( "regenTimer", currentTick );
	}

	public void onUpdate( ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5 ) 
	{
		this.regenTimer( par1ItemStack, 1*20, 1, (EntityLiving)par3Entity );
	}
}
