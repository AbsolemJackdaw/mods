package gravestone.bones;

import gravestone.mod_Gravestone;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBones extends BlockContainer {

	Random rand = new Random();

	public BlockBones(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setTickRandomly(true);
	}

	@Override
	public int tickRate(World par1World)
	{
		return 10;
	}

	public int onBlockPlaced(World par1World, int x, int y, int z, int l, float par6, float par7, float par8, int par9)
	{
		this.updateTick(par1World, x, y, z, rand);
		par1World.scheduleBlockUpdate(x, y, z, this.blockID, tickRate(par1World));
		return par9;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("dirt");
	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TEBones();
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return -1;
	}

	@Override
	public int getRenderType()
	{
		return mod_Gravestone.instance.renderID;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	} 

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		world.markBlockForUpdate(x, y, z);
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null)
		{
			if(par5EntityPlayer.isSneaking())
			{
				TEBones bs = (TEBones)te;
				if(bs.rotation <= 90*3-1f)
					bs.rotation += 90f;
				else
					bs.rotation =0f;
			}
		}
		return false;	
	}

	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

		int c = 1+rand.nextInt(4);
		EntityItem item;
		item = new EntityItem(world, x,y,z, new ItemStack(Item.bone, c));
		world.spawnEntityInWorld(item);

		world.removeBlockTileEntity(x,y,z);
	}
}
