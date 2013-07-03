package scythemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.StringTranslate;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class DSMTab extends CreativeTabs
{
	public DSMTab(int par1, String par2Str)
	{
		super(par1, par2Str);
	}
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return BaseScytheFile.GodHalo.itemID;
	}
	
	@Override
	public String getTranslatedTabLabel()
    {
        return "DSMTab";
    }

}