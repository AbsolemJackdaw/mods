package gravestone.grave;

import gravestone.mod_Gravestone;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGrave extends BlockContainer{

	Random rand = new Random();
	String name = "";
	public BlockGrave(int par1) {
		super(par1, Material.rock);
		this.setBlockBounds(0.4f, 0.0F, 0.4F, 0.6f, 1.0f, 0.6f);
		this.setTickRandomly(true);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("stonebrick");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TEGrave();
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
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		TEGrave te = (TEGrave)par1World.getBlockTileEntity(x, y, z);	

		if(par5EntityPlayer.getCurrentEquippedItem() != null)
		{

		}else{
			if(te != null)
			{
				if(!par5EntityPlayer.isSneaking())
				{
					if(te.theMeta > 0)
					{
						switch(te.playername.length())
						{
						case 0:
							mod_Gravestone.proxy.openGui(1, par5EntityPlayer, "!Empty!",te);
							break;
						default:
							mod_Gravestone.proxy.openGui(1, par5EntityPlayer, te.playername,te);
							break;
						}
					}
					else
					{
						te.theMeta = 1;
						if(!par1World.isRemote)
							par5EntityPlayer.sendChatToPlayer("You didn't set a grave Model! Repairing...");
					}
				}
				else
				{
					if(te.ModelRotation < 348.75)
					{
						te.ModelRotation +=11.25f;
					}
					else
					{
						te.ModelRotation =0;
					}
				}
			}
		}
		return false;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z) {
		TEGrave te = (TEGrave)par1IBlockAccess.getBlockTileEntity(x,y,z);
		int meta = te.theMeta;
		switch (meta) {
		case 1:
			this.setBlockBounds(0.4f, 0.0F, 0.4F, 0.6f, 1.0f, 0.6f);
			break;
		case 2:
			this.setBlockBounds(0.43f, 0.0F, 0.14F, 0.57f, 1.05f, 0.86f);
			break;
		case 3:
			this.setBlockBounds(0.33f, 0.0F, 0.25F, 0.67f, 0.95f, 0.75f);
			break;
		case 4:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.25f, 0.8f);
			break;
		case 5:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.25f, 0.8f);
			break;
		case 7:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.25f, 0.8f);
			break;
		case 6:
			this.setBlockBounds(0.4f, 0.0F, 0.4F, 0.6f, 1.0f, 0.6f);
			break;
		case 8:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.5f, 0.8f);
			break;
		case 9:
			this.setBlockBounds(0.2f, 0.0F, 0.2F, 0.8f, 1.5f, 0.8f);
			break;
		default:
			this.setBlockBounds(0.4f, 0.0F, 0.4F, 0.6f, 1.0f, 0.6f);
			break;
		}
	}

	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

		world.removeBlockTileEntity(x,y,z);
	}
}