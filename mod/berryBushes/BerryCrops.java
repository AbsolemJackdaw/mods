package berryBushes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BerryCrops extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;

    public BerryCrops(int par1)
    {
        super(par1);
    }

    
    @Override
    public int getRenderType() {
    	return 1;
    }
    
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 > 7)
        {
            par2 = 7;
        }

        return this.iconArray[par2];
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return par1 == 4 ? Base.berryII.itemID : par1 == 5 ? Base.berryII.itemID :
        		par1 == 6 ? Base.berryIII.itemID : par1 == 7 ? Base.berryIV.itemID: this.getSeedItem();
    }
    /**
     * Generate a seed ItemStack for this crop.
     */
    @Override
    protected int getSeedItem()
    {
        return Base.berry.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    @Override
    protected int getCropItem()
    {
        return Base.berry.itemID;
    }

    @Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);

        if (metadata >= 7)
        {
            for (int n = 0; n < 3 + fortune; n++)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(new ItemStack(Base.berryIV, 1, 0));
                }
            }
        }else if(metadata == 6){
        	for (int n = 0; n < 3 + fortune; n++)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(new ItemStack(Base.berryIII, 2, 0));
                }
            }
        }else if(metadata == 5 || metadata == 4){
        	for (int n = 0; n < 3 + fortune; n++)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(new ItemStack(Base.berryII, 2, 0));
                }
            }
        }else{
//        	 ret.add(new ItemStack(Base.berry, 1, 0));
        }

        return ret;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[8];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("berries:block_crop_" + i);
        }
    }
}
