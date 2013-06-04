package betterbreeds.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemDogTag extends Item
{
    public ItemDogTag(int i)
    {
        super(i);
        maxStackSize = 1;
        
    }
    @Override
	public void registerIcons(IconRegister par1IconRegister) {
		String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("Breeds:" + texture);
	}
}
