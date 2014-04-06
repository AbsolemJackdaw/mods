package telepads.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import telepads.Telepads;
import telepads.util.TelePadGuiHandler;
import telepads.util.TelepadWorldData;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockTelepad extends BlockContainer{

	public BlockTelepad(Material par2Material) {
		super( par2Material);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int x, int y, int z,
			EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {

		TelepadWorldData.get(par1World).print();

		TETelepad te = new TETelepad();

		int c = 0;
		if(par5EntityLivingBase instanceof EntityPlayer){
			EntityPlayer p = (EntityPlayer)par5EntityLivingBase;		

			te.ownerName = p.getDisplayName();
			te.dimension = par1World.provider.dimensionId;

			p.openGui(Telepads.instance, TelePadGuiHandler.NAMETELEPAD, par1World, x, y, z);
		}

		par1World.setTileEntity(x, y, z, te);
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
	{
		if(par5Entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)par5Entity;
			TETelepad te = (TETelepad)par1World.getTileEntity(x, y, z);

			te.playerStandingOnPad = player;
			
		}
	}


	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TETelepad();
	}

	@Override
	public int getRenderType(){
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	protected void dropBlockAsItem(World p_149642_1_, int p_149642_2_,int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {
		super.dropBlockAsItem(p_149642_1_, p_149642_2_, p_149642_3_, p_149642_4_,p_149642_5_);
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
	public void breakBlock(World par1World, int par2, int par3, int par4,Block b, int par6) {
		super.breakBlock(par1World, par2, par3, par4, b, par6);

		par1World.removeTileEntity(par2, par3, par4);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y,
			int z, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {

		TETelepad te = (TETelepad)par1World.getTileEntity(x, y, z);
		
		if(!par5EntityPlayer.isSneaking()){
			if(te.ownerName.equals(par5EntityPlayer.getDisplayName())){
				par1World.setBlockToAir(x, y, z);
				par1World.removeTileEntity(x, y, z);

				ItemStack stack = new ItemStack(Telepads.telepad);
				EntityItem ei = new EntityItem(par1World, x, y, z, stack);
				if(!par1World.isRemote)
					par1World.spawnEntityInWorld(ei);

				TelepadWorldData.get(par1World).removePad(te);
				TelepadWorldData.get(par1World).print();
			}
			else
				if(par1World.isRemote)
					par5EntityPlayer.addChatComponentMessage(new ChatComponentText("This is not mine. I should not do this !"));
	}else{
		TelepadWorldData.get(par1World).print();
		par5EntityPlayer.openGui(Telepads.instance, TelePadGuiHandler.TELEPORT, par1World, x, y, z);
	}
		return false;
	}



	@Override
	public void randomDisplayTick(World par1World, int x, int y, int z, Random par5Random)
	{
		TETelepad te = (TETelepad)par1World.getTileEntity(x, y, z);

		if(te.isStandingOnPlatform)	{	
			//	par1World.playSound((double)x , (double)y, (double)z, "subaraki:telepadLong", 0.7F, par5Random.nextFloat() * 0.4F + 0.4F, false);
			for (int l = 0; l < 100; ++l)
			{
				double d0 = (double)((float)x + par5Random.nextFloat());
				double d1 = (double)((float)y + par5Random.nextFloat()*1.5f);
				d0 = (double)((float)z + par5Random.nextFloat());
				double d2 = 0.0D;
				double d3 = 0.0D;
				double d4 = 0.0D;
				int i1 = par5Random.nextInt(2) * 2 - 1;
				int j1 = par5Random.nextInt(2) * 2 - 1;
				d2 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
				d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
				d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
				double d5 = (double)z + 0.5D + 0.25D * (double)j1;
				d4 = (double)(par5Random.nextFloat() * 1.0F * (float)j1);
				double d6 = (double)x + 0.5D + 0.25D * (double)i1;
				d2 = (double)(par5Random.nextFloat() * 1.0F * (float)i1);
				par1World.spawnParticle("portal", x+0.5, d1, z+0.5, d2, d3, d4);
			}
		}else{
			if (par5Random.nextInt(50) == 0)
			{
				//par1World.playSound((double)x , (double)y, (double)z, "subaraki:telepadShort", 1.0F, par5Random.nextFloat() * 0.4F + 0.8F, false);
			}
			for (int l = 0; l < 5; ++l)
			{
				double d0 = (double)((float)x + par5Random.nextFloat());
				double d1 = (double)((float)y + par5Random.nextFloat()*1.5f);
				d0 = (double)((float)z + par5Random.nextFloat());
				double d2 = 0.0D;
				double d3 = 0.0D;
				double d4 = 0.0D;
				int i1 = par5Random.nextInt(2) * 2 - 1;
				int j1 = par5Random.nextInt(2) * 2 - 1;
				d2 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
				d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
				d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
				double d5 = (double)z + 0.5D + 0.25D * (double)j1;
				d4 = (double)(par5Random.nextFloat() * 1.0F * (float)j1);
				double d6 = (double)x + 0.5D + 0.25D * (double)i1;
				d2 = (double)(par5Random.nextFloat() * 1.0F * (float)i1);
				par1World.spawnParticle("portal", x+0.5, d1, z+0.5, d2, d3, d4);
			}
		}
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z,
			Entity entity) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TETelepad();
	}
}
