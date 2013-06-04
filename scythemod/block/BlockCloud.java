package scythemod.block;

import java.util.Random;

import scythemod.BaseScytheFile;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockCloud extends Block
{
    public BlockCloud()
    {
        super(230, Material.ice);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }
    
    @Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("DSM:cloud");
	}

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 1;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

    public int getMobilityFlag()
    {
        return 3;
    }
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float var5 = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)par2 + var5), (double)par3, (double)((float)par4 + var5), (double)((float)(par2 + 1) - var5), (double)((float)(par3 + 1) - var5), (double)((float)(par4 + 1) - var5));
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float var5 = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)par2 + var5), (double)par3, (double)((float)par4 + var5), (double)((float)(par2 + 1) - var5), (double)(par3 + 1), (double)((float)(par4 + 1) - var5));
    }
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	if(par5Entity instanceof EntityPlayer)
    	{
    		EntityPlayer var1 = (EntityPlayer)par5Entity;
    		
    		if(var1.inventory.hasItem(BaseScytheFile.GodHalo.itemID))
    		{
    			var1.getFoodStats().setFoodLevel(1000);
    			var1.getFoodStats().setFoodSaturationLevel(1000);
    		}
    		else
    		{
    	        var1.attackEntityFrom(DamageSource.cactus, 4);

    		}
    	}
    	
    }
}
