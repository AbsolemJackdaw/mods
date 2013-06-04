package scythemod.item;

import scythemod.BaseScytheFile;
import scythemod.entity.EntityFarmScythe;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFarmScythe extends Item
{
    public ItemFarmScythe(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setCreativeTab(BaseScytheFile.DTab);
    }
    @Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("DSM:" + itemName);
	}

    /**
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par2World.spawnEntityInWorld(new EntityFarmScythe(par2World, par3EntityPlayer));


        par2World.playSoundAtEntity(par3EntityPlayer, "none", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        

        return par1ItemStack;
    }
}

