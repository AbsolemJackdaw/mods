package scythemod.item;

import java.util.List;

import scythemod.dragon.entity.DragonEntity;


import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDragonWand extends ItemBallz
{
	public static final String[] dragons = new String[] {"ZombieDragon", "SacredDragon"};
	public ItemDragonWand(int par1)
	{
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.maxStackSize = 1;
	}

	public String getItemNameIS(ItemStack par1ItemStack)
	{
		int var2 = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 6);
		return dragons[var2];
	}
	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1)); 
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
	{

		if(!var2.isRemote)
		{
			if (var1.getItemDamage() == 0)
			{
				var3.swingItem();
				--var1.stackSize;

				var3.addChatMessage("You call forth upon Death's Dragon."); 
				DragonEntity var4 = new DragonEntity(var2);

				var4.setLocationAndAngles(var3.posX, var3.posY, var3.posZ, var2.rand.nextFloat() * 360.0F, 0.0F);
				((EntityLiving)var4).initCreature();
				var2.spawnEntityInWorld(var4);
				((EntityLiving)var4).playLivingSound();

				var4.setTamed(true);
				var4.setPathToEntity(null);
				var4.setAttackTarget(null);
				var4.setZombie(true);
				var4.isSaddled();
				var4.setOwner(var3.username);

			}


			else if (var1.getItemDamage() == 1)
			{
				var3.swingItem();
				--var1.stackSize;

				var3.addChatMessage("You call forth upon Zeus' Dragon"); 
				DragonEntity var4 = new DragonEntity(var2);

				if (var4 != null)
				{
					var4.setLocationAndAngles(var3.posX, var3.posY, var3.posZ, var2.rand.nextFloat() * 360.0F, 0.0F);
					((EntityLiving)var4).initCreature();
					var2.spawnEntityInWorld(var4);
					((EntityLiving)var4).playLivingSound();

					var4.setTamed(true);
					var4.setPathToEntity(null);
					var4.setAttackTarget(null);
					var4.setZombie(false);
					var4.isSaddled();
					var4.setOwner(var3.username);

				}

			}
		}
		return var1;
	}

	public int getColorFromItemStack(ItemStack b2, int par2)
	{    	
		if (b2.getItemDamage() == 0)
		{
			return 0x313616;
		}

		if (b2.getItemDamage() == 1)
		{
			return 0x102328;
		}

		return 0x000000;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("DSM:" + itemName);
	}

}
