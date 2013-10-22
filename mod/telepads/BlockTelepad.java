package telepads;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class BlockTelepad extends BlockContainer{

	public BlockTelepad(int par1, Material par2Material) {
		super(par1, par2Material);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int x, int y, int z,
			EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {

		TETelepad te = new TETelepad();

		int c = 0;
		if(par5EntityLivingBase instanceof EntityPlayer){
			EntityPlayer p = (EntityPlayer)par5EntityLivingBase;		

			te.ownerName = p.username;
			int[] a_ray = new int[3];
			a_ray[0] = x;
			a_ray[1] = y;
			a_ray[2] = z;

			if(!par1World.isRemote)
				te.allCoords.add(te.allCoords.size(), a_ray);

			if(p.inventory.hasItem(mod_telepads.padLocator.itemID)){
				for(int i = 0; i < p.inventory.getSizeInventory(); i++){
					if(p.inventory.getStackInSlot(i) != null && p.inventory.getStackInSlot(i).getItem() instanceof ItemPadLocations){
						ItemStack stack = p.inventory.getStackInSlot(i);

						//for nitwits that put in a new list from creative or trough 'cheating'
						if(!stack.hasTagCompound()){
							stack.setTagCompound(new NBTTagCompound());
							stack.getTagCompound().setInteger(ItemPadLocations.SIZE, 1);
							stack.getTagCompound().setIntArray(ItemPadLocations.LOCATION_+0, a_ray);
							stack.getTagCompound().setInteger(ItemPadLocations.DIM_+0, par1World.provider.dimensionId);
						}else{
							int size = stack.getTagCompound().getInteger(ItemPadLocations.SIZE);
							for(int t =0; t < size; t++){
								te.allCoords.add(stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+t));
							}
							stack.getTagCompound().setIntArray(ItemPadLocations.LOCATION_+(size), a_ray);
							stack.getTagCompound().setInteger(ItemPadLocations.DIM_+size, par1World.provider.dimensionId);
							stack.getTagCompound().setInteger(ItemPadLocations.SIZE, size +1);
						}
					}
				}
			}else{
				ItemStack stack = new ItemStack(mod_telepads.padLocator);
				stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setInteger(ItemPadLocations.SIZE, 1);
				stack.getTagCompound().setIntArray(ItemPadLocations.LOCATION_+0, a_ray);
				stack.getTagCompound().setInteger(ItemPadLocations.DIM_+0, par1World.provider.dimensionId);
				p.inventory.addItemStackToInventory(stack);
			}

			FMLNetworkHandler.openGui(p, mod_telepads.instance, 1, par1World, x, y, z);

		}

		par1World.setBlockTileEntity(x, y, z, te);
	}

	@Override
	public boolean isBlockNormalCube(World world, int x, int y, int z) {
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
	{
		if(par5Entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)par5Entity;
			TETelepad te = (TETelepad)par1World.getBlockTileEntity(x, y, z);

			te.playerStandingOnPad = player;
		}
	}



	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TETelepad();
	}

	@Override
	public int getRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		// TODO Auto-generated method stub
		return this.blockID;
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
	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
		par1World.removeBlockTileEntity(par2, par3, par4);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y,
			int z, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {

		//
		TETelepad te = (TETelepad)par1World.getBlockTileEntity(x, y, z);


		if(par5EntityPlayer.getCurrentEquippedItem() == null || par5EntityPlayer.getCurrentEquippedItem() != null && !par5EntityPlayer.getCurrentEquippedItem().getItem().equals(mod_telepads.padLocator)){
			if(te.ownerName.equals(par5EntityPlayer.username) && par5EntityPlayer.isSneaking()){
				
				
				if(par5EntityPlayer.inventory.hasItem(mod_telepads.padLocator.itemID)){
					for(int i = 0; i < par5EntityPlayer.inventory.getSizeInventory(); i++){
						if(par5EntityPlayer.inventory.getStackInSlot(i) != null && par5EntityPlayer.inventory.getStackInSlot(i).getItem() instanceof ItemPadLocations){

							ItemStack stack = par5EntityPlayer.inventory.getStackInSlot(i);
							//in rare events of someone actually spawning in an empty register ... 
							if(!stack.hasTagCompound()){
								int[] a_ray = new int[3];
								a_ray[0] = x;
								a_ray[1] = y;
								a_ray[2] = z;
								stack.setTagCompound(new NBTTagCompound());
								stack.getTagCompound().setInteger(ItemPadLocations.SIZE, 1);
								stack.getTagCompound().setIntArray(ItemPadLocations.LOCATION_+0, a_ray);
								stack.getTagCompound().setInteger(ItemPadLocations.DIM_+0, par1World.provider.dimensionId);
							}
							
							registerUpdate(par5EntityPlayer, stack, te, par1World, x, y, z);
						}
					}
				}
				
				if(!par1World.isRemote)
					par5EntityPlayer.addChatMessage("TelePad " + te.telepadname + " got set to universal acces.");
				te.ownerName = "UNIVERSAL";

				
			}
		}else{
			if(par5EntityPlayer.inventory.hasItem(mod_telepads.padLocator.itemID)){
				for(int i = 0; i < par5EntityPlayer.inventory.getSizeInventory(); i++){
					if(par5EntityPlayer.inventory.getStackInSlot(i) != null && par5EntityPlayer.inventory.getStackInSlot(i).getItem() instanceof ItemPadLocations){

						ItemStack stack = par5EntityPlayer.inventory.getStackInSlot(i);

						//in rare events of someone actually spawning in an empty register ... 
						if(!stack.hasTagCompound()){
							int[] a_ray = new int[3];
							a_ray[0] = x;
							a_ray[1] = y;
							a_ray[2] = z;
							stack.setTagCompound(new NBTTagCompound());
							stack.getTagCompound().setInteger(ItemPadLocations.SIZE, 1);
							stack.getTagCompound().setIntArray(ItemPadLocations.LOCATION_+0, a_ray);
							stack.getTagCompound().setInteger(ItemPadLocations.DIM_+0, par1World.provider.dimensionId);
						}
						
						// if the TE is a Universal Pad
						if(te.ownerName.equals("UNIVERSAL")){
							registerAddPad(par5EntityPlayer, stack, te, par1World, x, y, z);
						}

						if(te.ownerName.equals(par5EntityPlayer.username)){
							registerRemovePad(par5EntityPlayer, stack, te, par1World, x, y, z);
						}
					}
				}
			}
		}

		return true;
	}



	@Override
	public void randomDisplayTick(World par1World, int x, int y, int z, Random par5Random)
	{
		TETelepad te = (TETelepad)par1World.getBlockTileEntity(x, y, z);

		if(te.isStandingOnPlatform)	{	
			par1World.playSound((double)x , (double)y, (double)z, "subaraki:telepadLong", 0.7F, par5Random.nextFloat() * 0.4F + 0.4F, false);
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
				par1World.playSound((double)x , (double)y, (double)z, "subaraki:telepadShort", 1.0F, par5Random.nextFloat() * 0.4F + 0.8F, false);
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



	/**Adds a universal pad to the register*/
	private void registerAddPad(EntityPlayer par5EntityPlayer, ItemStack stack, TETelepad te, World par1World, int x, int y, int z){
		if(par5EntityPlayer.getCurrentEquippedItem().itemID == stack.itemID){
			boolean sendRegisterMessage = true;
			int size = stack.getTagCompound().getInteger(ItemPadLocations.SIZE);

			int[] universalPadArray = new int[3];

			for(int c =0; c < size; c++){

				int[] ray = new int[3];
				ray[0] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[0];
				ray[1] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[1];
				ray[2] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[2];

				if(ray[0] == x && ray[1] == y && ray[2] == z){
					if(!par1World.isRemote)
						par5EntityPlayer.addChatMessage("I already registered this Universal Telepad.");
					sendRegisterMessage = false;
					universalPadArray = null;
					break;
				}
				else{
					universalPadArray[0] = x;
					universalPadArray[1] = y;
					universalPadArray[2] = z;
				}
			}

			if(sendRegisterMessage ){
				if(!par1World.isRemote)
					par5EntityPlayer.addChatMessage("Universal TelePad was added to the Register.");
				if(universalPadArray != null){
					//dont do size+1. size is already the maximum value and in loops always gets checked for -1 (0 included)
					//aka : size == list.addLast(Object)
					stack.getTagCompound().setIntArray(ItemPadLocations.LOCATION_+(size), universalPadArray);
					stack.getTagCompound().setString("TelePadName_"+(size), "Universal Pad");
					stack.getTagCompound().setInteger(ItemPadLocations.DIM_+size, par1World.provider.dimensionId);
					stack.getTagCompound().setInteger(ItemPadLocations.SIZE , size+1);
				}
			}
		}
	}

	/**re-runs all nbt and updates any names if needed.*/
	private void registerUpdate(EntityPlayer par5EntityPlayer, ItemStack stack, TETelepad te, World par1World, int x, int y, int z){

		int size = stack.getTagCompound().getInteger(ItemPadLocations.SIZE);

		//NBTTagCompound newNBT = new NBTTagCompound();

		for(int c =0; c < size; c++){

			int[] ray = new int[3];
			ray[0] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[0];
			ray[1] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[1];
			ray[2] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[2];


			if(ray[0] == x && ray[1] == y && ray[2] == z){
				stack.getTagCompound().setString("TelePadName_"+(c), "Universal Pad");
			}else{
				stack.getTagCompound().setString("TelePadName_"+(c), 
						stack.getTagCompound().getString("TelePadName_"+c));
			}

			stack.getTagCompound().setIntArray(ItemPadLocations.LOCATION_+(c),ray);
			stack.getTagCompound().setInteger(ItemPadLocations.DIM_+c, par1World.provider.dimensionId);

		}

		if(!par1World.isRemote)
			par5EntityPlayer.addChatMessage("Universal TelePad was added to the Register.");
		//stack.setTagCompound(newNBT);
	}


	/**Used to remove a pad from the register*/
	private void registerRemovePad(EntityPlayer par5EntityPlayer, ItemStack stack, TETelepad te, World par1World, int x, int y, int z){
		if(par5EntityPlayer.getCurrentEquippedItem().itemID == stack.itemID){
			
			int size = stack.getTagCompound().getInteger(ItemPadLocations.SIZE);

			int newID =0;
			NBTTagCompound newNBT = new NBTTagCompound();
			//
			for(int c = 0; c < size; c++){
				//
				int[] ray = new int[3];
				ray[0] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[0];
				ray[1] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[1];
				ray[2] = stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[2];

				//FMLLog.getLogger().info("ray" + ray[0] + " " + ray[1] + " " + ray[2]);
				//FMLLog.getLogger().info("pos" + x + " " + y + " " + z);
				//
				String name = stack.getTagCompound().getString("TelePadName_"+c);
				//
				if(ray[0] == te.xCoord && ray[1] == te.yCoord && ray[2] == te.zCoord){

					//if the coordinates are in, do nothing, it will 'remove' them from the list.
					//the player IS the owner, so theoreticly, it should be in the list. if it isn't, that's even better.
					FMLLog.getLogger().info(" 'removed' index " + c);

				}else{
					//copy over values to a new nbt. 
					newNBT.setIntArray(ItemPadLocations.LOCATION_+(newID),
							stack.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c));
					newNBT.setString("TelePadName_"+(newID), 
							stack.getTagCompound().getString("TelePadName_"+c));
					newNBT.setInteger(ItemPadLocations.DIM_+newID, stack.getTagCompound().getInteger(ItemPadLocations.DIM_+c));
					newNBT.setInteger(ItemPadLocations.SIZE , newID+1);

					newID++;
				}
			}
			
			stack.setTagCompound(newNBT);
			//and spawn a new block
			par1World.setBlockToAir(x, y, z);
			ItemStack telePad = new ItemStack(mod_telepads.telepad);
			EntityItem item = new EntityItem(par1World, x, y, z, telePad);
			if(!par1World.isRemote)
				par1World.spawnEntityInWorld(item);
		}
	}
}
