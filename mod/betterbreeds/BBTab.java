package betterbreeds;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BBTab extends CreativeTabs {
	
	 public BBTab(int par1, String par2Str)
     {
             super(par1, par2Str);
     }
	 
     @SideOnly(Side.CLIENT)
     public int getTabIconItemIndex()
     {
             return ModBreeds.ChocolatePie.itemID;
     }

     public String getTranslatedTabLabel()
     {
      return "Better Breeding Tab";
     }

}
