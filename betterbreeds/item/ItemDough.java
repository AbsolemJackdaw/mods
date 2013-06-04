package betterbreeds.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemDough extends Item

{
	public ItemDough(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
    }
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon(texture);
	}
	
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		return 0xffd87b;
	}
}
