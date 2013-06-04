package betterbreeds.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCEgg extends Item
{
	public ItemCEgg(int i)
	{
		super(i);
		this.setMaxDamage(0);
		this.setMaxStackSize(16);

	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon(texture);
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.eat;
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 32;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer var3)
	{
		if(var3.getFoodStats().getFoodLevel() < 20 )
		{
			var3.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;

	}
	
	@Override
	public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
	{
		var1.damageItem(1, var3);
		var3.getFoodStats().setFoodSaturationLevel(2F);
		var3.getFoodStats().setFoodLevel(var3.getFoodStats().getFoodLevel()+4);
		return var1;
	}

	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		return 0x543f26;
	}
}
