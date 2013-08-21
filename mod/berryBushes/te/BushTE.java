package berryBushes.te;

import berryBushes.Bush;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class BushTE extends TileEntity{

	protected int Meta;
	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		Block block = Block.blocksList[worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)];
		
		if(block != null && block instanceof Bush){
			Bush b = (Bush)block;
			Meta = b.Meta;
		}
	}
}
