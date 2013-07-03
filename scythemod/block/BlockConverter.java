package scythemod.block;

import java.util.Random;

import scythemod.BaseScytheFile;
import scythemod.block.te.TileEntityConverter;


import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class BlockConverter extends BlockContainer
{

	private static boolean keepInventory = false;
	public Icon ConvTop ;
	public Icon ConvSide ;
	private Icon ConverterTopActive ;

	/**
	 * Is the random generator used by furnace to drop the inventory contents in random directions.
	 */
	private Random goldRand;

	public BlockConverter(int id, Material material, boolean active)
	{
		super(id, material);
		goldRand = new Random();

	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = ConvSide = par1IconRegister.registerIcon("DSM:conv2");
		ConvTop = par1IconRegister.registerIcon("DSM:conv1");
		ConverterTopActive = par1IconRegister.registerIcon("DSM:conv3");
	}


	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World par1World, int x, int y, int z)
	{
		super.onBlockAdded(par1World, x, y, z);
		setDefaultDirection(par1World, x, y, z);
		par1World.markBlockForRenderUpdate(x, y, z);
	}

	/**
	 * set a blocks direction
	 */
	private void setDefaultDirection(World par1World, int x, int y, int z)
	{
		TileEntity blockEntity = par1World.getBlockTileEntity(x, y, z);

		if (par1World.isRemote)
		{
			return;
		}

		((TileEntityConverter)blockEntity).setFrontDirection(1);
	}

	/**
	 * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	 */
	public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side) {	{
		int front = 1;
		Icon icon;
		TileEntity tile = Minecraft.getMinecraft().theWorld.getBlockTileEntity(x, y, z);
		if( !((TileEntityConverter)tile).isActive() )
		{
			front = access.getBlockMetadata(x, y, z);
		}
		else if( ((TileEntityConverter)tile).isActive() )
		{
			Minecraft.getMinecraft().theWorld.markBlockForUpdate(x, y, z);
		}

		switch(side)
		{
		case 0: 
			icon =Block.planks.getBlockTextureFromSide(0);
			break;
		case 1:
			icon = ConvTop;
			break;
		case 2:
			icon = ConvSide;	
			break;
		case 3:
			icon = ConvSide;	
			break;
		case 4:
			icon = ConvSide;	
			break;
		case 5:
			icon = ConvSide;	
			break;
		default :
			return Block.planks.getBlockTextureFromSide(0);
		}
		if(side == front){
			return ((TileEntityConverter) tile).isActive() ? ConverterTopActive : ConvTop;
		} else {
			return icon;	
		}
	}
	}


	/**
	 * called everytime the player right clicks this block
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t){
        FMLNetworkHandler.openGui(player, BaseScytheFile.instance, 1, world, x, y, z);
		return true;
	}

	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		if (!keepInventory)
		{
			TileEntityConverter gold = (TileEntityConverter)par1World.getBlockTileEntity(par2, par3, par4);

			if (gold != null)
			{
				for (int var8 = 0; var8 < gold.getSizeInventory(); ++var8)
				{
					ItemStack item = gold.getStackInSlot(var8);

					if (item != null)
					{
						float var10 = this.goldRand.nextFloat() * 0.8F + 0.1F;
						float var11 = this.goldRand.nextFloat() * 0.8F + 0.1F;
						float var12 = this.goldRand.nextFloat() * 0.8F + 0.1F;

						while (item.stackSize > 0)
						{
							int var13 = this.goldRand.nextInt(21) + 10;

							if (var13 > item.stackSize)
							{
								var13 = item.stackSize;
							}

							item.stackSize -= var13;
							EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(item.itemID, var13, item.getItemDamage()));

							if (item.hasTagCompound())
							{
								item.setTagCompound((NBTTagCompound)item.getTagCompound().copy());
							}

							float var15 = 0.05F;
							var14.motionX = (double)((float)this.goldRand.nextGaussian() * var15);
							var14.motionY = (double)((float)this.goldRand.nextGaussian() * var15 + 0.2F);
							var14.motionZ = (double)((float)this.goldRand.nextGaussian() * var15);
							par1World.spawnEntityInWorld(var14);
						}
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public TileEntity createNewTileEntity(World world){
		return new TileEntityConverter();
	}

}

