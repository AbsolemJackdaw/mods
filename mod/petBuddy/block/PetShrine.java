package petBuddy.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import petBuddy.PetBuddyMain;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PetShrine extends BlockContainer {

	public PetShrine(int par1) {
		super(par1, Material.rock);
		this.setTickRandomly(true);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("stonebrick");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TEShrine();
	}

	@Override
	public int quantityDropped(Random par1Random)
	{

		return 1;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return PetBuddyMain.shrine.blockID;
	}

	@Override
	public int getRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
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
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z) {

		this.setBlockBounds(0.15f, 0.0F, 0.15F, 0.85f, 0.7f, 0.85f);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y,
			int z, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {

		//		ItemStack stack = new ItemStack(PetBuddyMain.petStatue,1);
		//		par5EntityPlayer.inventory.addItemStackToInventory(stack);

		TEShrine te = (TEShrine)par1World.getBlockTileEntity(x, y, z);
		//
		if(te != null){
			if(par5EntityPlayer.inventory.getCurrentItem() != null){
				if(par5EntityPlayer.inventory.getCurrentItem().getItem().equals(PetBuddyMain.petStatue)){
					te.hasStatue = true;
					te.countdown = true;
					if(!par5EntityPlayer.capabilities.isCreativeMode)
						par5EntityPlayer.setCurrentItemOrArmor(0, null);
					
				}
			}else{
				if(te.cycleDone){

					te.hasStatue = false;
					te.cycleDone = false;
					te.cooldown = 40*20;

					ItemStack stack = new ItemStack(PetBuddyMain.petStatue,1);
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setInteger("guiID", getRandomBuddy());
					nbt.setString("name", par5EntityPlayer.username +"'s Buddy");
					nbt.setString("skin", par5EntityPlayer.username);
					nbt.setFloat("d_c_1", 0f);
					nbt.setFloat("d_c_2", 0f);
					nbt.setFloat("d_c_3", 0f);
					nbt.setFloat("s_c_1", 1f);
					nbt.setFloat("s_c_2", 1f);
					nbt.setFloat("s_c_3", 1f);
					
					stack.setTagCompound(nbt);

					par5EntityPlayer.inventory.addItemStackToInventory(stack);
				}
			}
		}
//		if(par1World.isRemote){
//			for(int c = 0; c < par5EntityPlayer.inventory.getSizeInventory(); c ++){
//				if(par5EntityPlayer.inventory.getStackInSlot(c) != null)
//					FMLLog.getLogger().info("S "+par5EntityPlayer.inventory.getStackInSlot(c) + " boolean : " + te.countdown);
//			}
//		}if(!par1World.isRemote){
//			for(int c = 0; c < par5EntityPlayer.inventory.getSizeInventory(); c ++){
//				if(par5EntityPlayer.inventory.getStackInSlot(c) != null)
//					FMLLog.getLogger().info("C "+par5EntityPlayer.inventory.getStackInSlot(c)+ " boolean : " + te.countdown);
//			}
//		}
		return true;
	}
	@SideOnly(Side.CLIENT)

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World par1World, int x, int y, int z, Random par5Random)
	{
		TEShrine te = (TEShrine)par1World.getBlockTileEntity(x, y, z);

		if(te != null){
			if(!te.cycleDone && te.countdown){
				par1World.spawnParticle("flame", x+0.15f,y+0.25f,z+0.15f, 0.02D, 0.02D, 0.02D);
				par1World.spawnParticle("flame", x+0.15f,y+0.25f,z+0.85f, 0.02D, 0.02D, -0.02D);
				par1World.spawnParticle("flame", x+0.85f,y+0.25f,z+0.85f, -0.02D, 0.02D, -0.02D);
				par1World.spawnParticle("flame", x+0.85f,y+0.25f,z+0.15f, -0.02D, 0.02D, 0.02D);
				par1World.spawnParticle("smoke", x+0.5f,y+0.4f,z+0.5f, 0.0D, 0.02D, 0.0D);
			}else{
				par1World.spawnParticle("flame", x+0.15f,y+0.3f,z+0.15f, 0.0D, 0.005D, 0.0D);
				par1World.spawnParticle("flame", x+0.15f,y+0.3f,z+0.85f, 0.0D, 0.003D, 0.0D);
				par1World.spawnParticle("flame", x+0.85f,y+0.3f,z+0.85f, 0.0D, 0.004D, 0.0D);
				par1World.spawnParticle("flame", x+0.85f,y+0.3f,z+0.15f, 0.0D, 0.005D, 0.0D);
				par1World.spawnParticle("smoke", x+0.15f,y+0.3f,z+0.15f, 0.0D, 0.02D, 0.0D);
				par1World.spawnParticle("smoke", x+0.15f,y+0.3f,z+0.85f, 0.0D, 0.02D, 0.0D);
				par1World.spawnParticle("smoke", x+0.85f,y+0.3f,z+0.85f, 0.0D, 0.02D, 0.0D);
				par1World.spawnParticle("smoke", x+0.85f,y+0.3f,z+0.15f, 0.0D, 0.02D, 0.0D);
			}
		}
	}

	private int getRandomBuddy(){
		
		int guiId;
		Random rand = new Random();
		
		int c = rand.nextInt(5);
		if(c >0){
			guiId = 3;
		}else{
			guiId = rand.nextInt(2+34);
		}
		
		return guiId;
	}
}
