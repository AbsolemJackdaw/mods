package threeDitems.blocks;

import net.minecraft.block.BlockRail;

public class Block3DRail extends BlockRail{
	public int renderId = 1;
    public Block3DRail(int par1) {
		super(par1);
	}

	public int getRenderType()
    {
        return renderId;
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }
}