package berryBushes;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import berryBushes.te.BushTE;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BerryCrops extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public BerryCrops(int par1)
	{
		super(par1, Material.wood);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return Base.berry.itemID;
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3,
			int par4, int par5) {
		
		ItemStack stack = new ItemStack(Base.berry);
		stack.stackSize = 1;
		EntityItem ei = new EntityItem(par1World, par2, par3, par4, stack);
		
		if(!par1World.isRemote)
			par1World.spawnEntityInWorld(ei);
		
		
		if(par1World.getBlockTileEntity(par2, par3, par4) instanceof BushTE){
			BushTE te = (BushTE)par1World.getBlockTileEntity(par2, par3, par4);
			float count = te.count;
			ItemStack stack1 = null;
			if(count > 8000 && count < 12000){
				stack1 = new ItemStack(Base.berry);
			}if(count >= 12000 && count < 18000){
				stack1 = new ItemStack(Base.berryII);
			}if(count >= 18000 && count < 24000){
				stack1 = new ItemStack(Base.berryIII);
			}if(count >= 24000){
				stack1 = new ItemStack(Base.berryIV);
			}
			if(count >8000){
				EntityItem ei1 = new EntityItem(par1World, par2, par3, par4, stack1);
				Random rand = new Random();
				int k = rand.nextInt(2)+1;
				stack1.stackSize = k;
				if(!par1World.isRemote)
					par1World.spawnEntityInWorld(ei1);

				te.count = 5000;
				te.stack = null;
			}
		}
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {

		if(par5EntityPlayer.getCurrentEquippedItem() != null){
			if(par5EntityPlayer.getCurrentEquippedItem().getItem() instanceof ItemDye){
				if(par5EntityPlayer.getCurrentEquippedItem().getItemDamage() == 15 ){
					if(par1World.getBlockTileEntity(par2, par3, par4) instanceof BushTE){
						BushTE te = (BushTE)par1World.getBlockTileEntity(par2, par3, par4);
						if(te.count < 24000){
							te.count += 400;
							if(!par5EntityPlayer.capabilities.isCreativeMode)
								par5EntityPlayer.getCurrentEquippedItem().stackSize--;
						}
					}
				}
			}
		}else{
			if(par1World.getBlockTileEntity(par2, par3, par4) instanceof BushTE){
				BushTE te = (BushTE)par1World.getBlockTileEntity(par2, par3, par4);
				float count = te.count;
				ItemStack stack = null;
				if(count > 8000 && count < 12000){
					stack = new ItemStack(Base.berry);
				}if(count >= 12000 && count < 18000){
					stack = new ItemStack(Base.berryII);
				}if(count >= 18000 && count < 24000){
					stack = new ItemStack(Base.berryIII);
				}if(count >= 24000){
					stack = new ItemStack(Base.berryIV);
				}
				if(count >8000){
					EntityItem ei = new EntityItem(par1World, par2, par3, par4, stack);
					Random rand = new Random();
					int k = rand.nextInt(2)+1;
					stack.stackSize = k;
					if(!par1World.isRemote)
						par1World.spawnEntityInWorld(ei);

					te.count = 5000;
					te.stack = null;
				}
			}
		}
		return false;
	}

	@Override
	public int getRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@SideOnly(Side.CLIENT)

	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("leaves_oak");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new BushTE();
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
