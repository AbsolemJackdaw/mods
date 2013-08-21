package berryBushes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import berryBushes.te.BushTE;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Bush extends BlockContainer{

	public int Meta;

	public Bush(int par1, int meta ) {
		super(par1, Material.leaves);
		Meta = meta;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new BushTE();
	}
	

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		switch(Meta){
		case 0:
			this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.6F, 0.8F);
			break;
		case 1:
			this.setBlockBounds(0.15F, 0.0F, 0.15F, 0.85F, 0.8F, 0.85F);
			break;
		case 2:
			this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.9F, 0.9F);
			break;
		case 3 : 
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
			break;
		default :
			break;
		}
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2,
			int par3, int par4) {

		switch(Meta){
		case 0:return 0x0f6316;
		case 1:return 0x0f8916;
		case 2: return 0x266916;
		case 3:return 0x3f8b49;
		default: return 0;
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> dropped = super.getBlockDropped(world, x, y, z, metadata, fortune);

		Item berry;
		berry = (Meta == 0 ? Base.berry : Meta == 1 ? Base.berryII : Meta == 2 ? Base.berryIII : Base.berryIV);

		int c = new Random().nextInt(2)+1;
		for (int k1 = 0; k1 < c; ++k1)
		{
			dropped.add(new ItemStack(berry, 1));
		}
		return dropped;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("leaves_oak_opaque");

	}

	@Override
	public int getRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
