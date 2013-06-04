package betterbreeds.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemSubsFood extends ItemFood
{

	public ItemSubsFood(int par1, int par2, float par3, boolean par4)
	{
		super(par1, par2,par3, par4);
		this.setCreativeTab(CreativeTabs.tabFood);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("Breeds:" + texture);
	}
}
