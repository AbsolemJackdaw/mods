package petItems.itemEater;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class Items extends Item {

	public Items(int par1) {
		super(par1);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("petItems:" + itemName);
	}

}
