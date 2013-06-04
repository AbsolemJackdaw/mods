package betterbreeds;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import betterbreeds.entity.EntityJelly;

public class ItemJelly extends Item{

	public ItemJelly(int par1) {
		super(par1);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("Breeds:" + texture);
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		par1ItemStack.stackSize--;

		if (!par3World.isRemote)
		{
			EntityJelly var21 = new EntityJelly(par3World);
			var21.setLocationAndAngles(par4, par5+1, par6, 0, 0);    
			var21.setOwner(par2EntityPlayer.username);
			par3World.spawnEntityInWorld(var21);
		}
		return false;
	}
}
