package scythemod.item;

import scythemod.BaseScytheFile;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemArmorPerso extends ItemArmor implements IArmorTextureProvider
{

	public ItemArmorPerso(int par1, EnumArmorMaterial par2EnumArmorMaterial,int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);


	}
	public String getArmorTextureFile (ItemStack par1)
	{
		if (par1.itemID == BaseScytheFile.GodBoots.itemID ||par1.itemID == BaseScytheFile.GodChestplate.itemID ||par1.itemID == BaseScytheFile.GodCrown.itemID)
		{
			return "/armor/godarmor_1.png";
		}
		else if (par1.itemID == BaseScytheFile.GodTrousers.itemID)
		{
			return "/armor/godarmor_2.png";

		}
		return "/armor/godarmor_1.png"; 
	}


	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("DSM:" + itemName);
	}




}
