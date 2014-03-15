package threeDitems.blocks;

import net.minecraft.block.BlockRail;

public class Block3DRail extends BlockRail{
	public int renderId = 1;
	public Block3DRail(int par1) {
		super(par1);
	}

	@Override
	public int getRenderType()
	{
		return renderId;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}