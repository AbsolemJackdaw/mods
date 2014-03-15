package decorativeCollectibles.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import decorativeCollectibles.mod_collectibles;

public class ItemGoldenPig extends ItemBlock{

	public ItemGoldenPig(int par1) {
		super(par1);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	private Icon[] icons;

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = "";
		int i = itemstack.getItemDamage();
		switch(itemstack.getItemDamage()){
		case 0: name = mod_collectibles.namesPigs[i];	break;
		case 1: name = mod_collectibles.namesPigs[i];	break;
		case 2: name = mod_collectibles.namesPigs[i];	break;
		case 3: name = mod_collectibles.namesPigs[i];	break;
		case 4: name = mod_collectibles.namesPigs[i];	break;
		case 5: name = mod_collectibles.namesPigs[i];	break;
		case 6: name = mod_collectibles.namesPigs[i];	break;
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		String name = "";
		int i = itemstack.getItemDamage();
		switch(itemstack.getItemDamage()){
		case 0: name = mod_collectibles.namesPigs[i];	break;
		case 1: name = mod_collectibles.namesPigs[i];	break;
		case 2: name = mod_collectibles.namesPigs[i];	break;
		case 3: name = mod_collectibles.namesPigs[i];	break;
		case 4: name = mod_collectibles.namesPigs[i];	break;
		case 5: name = mod_collectibles.namesPigs[i];	break;
		case 6: name = mod_collectibles.namesPigs[i];	break;
		}
		return name;
	}

    @SideOnly(Side.CLIENT)

    public Icon getIconFromDamage(int par1)
    {
        return mod_collectibles.goldenPigsBlock.getIcon(2, par1);
    }
}
