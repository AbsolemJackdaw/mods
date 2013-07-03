package scythemod.item;

import scythemod.BaseScytheFile;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemArmorReaper extends ItemArmor implements IArmorTextureProvider
{

	public ItemArmorReaper(int par1, EnumArmorMaterial par2EnumArmorMaterial,int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);


	}
	public String getArmorTextureFile (ItemStack par1)
	{
		if (par1.itemID == BaseScytheFile.ReaperBoots.itemID ||par1.itemID == BaseScytheFile.ReaperGown.itemID ||par1.itemID == BaseScytheFile.ReaperHood.itemID)
		{
			return "/armor/reaper_1.png";
		}
		if (par1.itemID == BaseScytheFile.ReaperTrousers.itemID)
		{
			return "/armor/reaper_2.png";

		}
		return "/armor/reaper_1.png";
	}



	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("DSM:" + itemName);
	}

}
