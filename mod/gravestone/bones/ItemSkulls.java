package gravestone.bones;

import gravestone.mod_Gravestone;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemSkulls extends Item{

	public ItemSkulls(int par1) {
		super(par1);
	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("bone");
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		String texture;
		if(world.getBlockId(x, y+1, z) == 0 && world.doesBlockHaveSolidTopSurface(x, y, z)){

			world.setBlock(x, y+1, z, mod_Gravestone.bones.blockID);
			TileEntity te = world.getBlockTileEntity(x,y+1,z);

			int id = world.getBlockId(x,y,z);
			int meta = world.getBlockMetadata(x,y,z);
			Block block = Block.blocksList[id];
			Icon icon = block.getIcon(1, meta);
			int sat = block.getBlockColor();
			texture = icon.getIconName();
			if(te != null)
			{
				TEBones bones = (TEBones)te;
				bones.setTexture(texture);
				bones.setSat(sat);
				world.setBlockTileEntity(x, y+1, z, bones);
			}	
			else
				par2EntityPlayer.addChatMessage("te ==null");
		}
		return false;

	}
}
