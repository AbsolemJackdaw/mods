package gravestone.grave;

import gravestone.mod_Gravestone;
import gravestone.grave.te.TEGrave;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class BlockGrave extends BlockContainer{

	Random rand = new Random();
	String name = "";
	
	int slotID = 0;

	
	public BlockGrave(int par1) {
		super(par1, Material.rock);
		this.setBlockBounds(0.4f, 0.0F, 0.4F, 0.6f, 1.0f, 0.6f);
		this.setTickRandomly(true);
		this.setBlockUnbreakable();

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
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		return getExplosionResistance(par1Entity);
	}

	@Override
	public float getExplosionResistance(Entity par1Entity) {
		return 18000000F;
	}

	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
		//DO NOTHING, prevents creeper griefing of gravestones.
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
		this.blockHardness = 10;
		TEGrave te = (TEGrave)par1World.getBlockTileEntity(x, y, z);	
		
		if(te != null)
		{
			if(!par5EntityPlayer.isSneaking())
			{
				if(te.theMeta > 0)
				{
					FMLNetworkHandler.openGui(par5EntityPlayer, mod_Gravestone.instance, 2, par1World, x, y, z);

				}
				else
				{
					te.theMeta = 1;
					if(!par1World.isRemote)
						par5EntityPlayer.addChatMessage("You didn't set a grave Model! Repairing...");
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
		return true;
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

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4,
			EntityPlayer par5EntityPlayer) {
		super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
		TEGrave te = (TEGrave)par1World.getBlockTileEntity(par2,par3,par4);
		if(te != null && te.playername.length() > 0){
			if(par1World.playerEntities!= null && par1World.playerEntities.contains(par1World.getPlayerEntityByName(te.playername))){
				if(te.playername.equals(par5EntityPlayer.username)){
				}else{
					FMLNetworkHandler.openGui(par5EntityPlayer, mod_Gravestone.instance, 2, par1World, par2,par3,par4);
				}
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

		TEGrave te = (TEGrave)world.getBlockTileEntity(x,y,z);

		if (te != null)
		{
			for (int j1 = 0; j1 < te.getSizeInventory(); ++j1)
			{
				ItemStack itemstack = te.getStackInSlot(j1);

				if (itemstack != null)
				{
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0)
					{
						int k1 = this.rand.nextInt(21) + 10;

						if (k1 > itemstack.stackSize)
						{
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
						entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
						if(!world.isRemote)
							world.spawnEntityInWorld(entityitem);
					}
				}
			}
			world.func_96440_m(x,y,z, par5);
		}
		super.breakBlock(world, x,y,z, par5, par6);
	}
}