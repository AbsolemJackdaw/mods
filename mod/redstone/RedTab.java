package redstone;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RedTab extends CreativeTabs {

	public RedTab(int par1, String par2Str)
	{
		super(par1, par2Str);
	}
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return mod_RedStoneGuns.redGun.itemID;
	}

	public String getTranslatedTabLabel()
	{
		return "Hand Guns";
	}
}
