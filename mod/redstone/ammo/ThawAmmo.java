package redstone.ammo;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ThawAmmo extends EntityThrowable
{
	public int damage = 4;
	public World world;
	public static int status;
	public ThawAmmo(World par1World)
	{
		super(par1World);
		world = par1World;
	}

	public ThawAmmo(World par1World, EntityLivingBase par2EntityLiving, int state)
	{
		super(par1World, par2EntityLiving);
		world = par1World;
		status = state;
	}

	public void onUpdate()
	{
		super.onUpdate();
		float f3 = 0.25F;
		String particle = "";
		if(status == 0){
			particle = "smoke";
		}
		else if (status == 1){
			particle = "flame";
		}

		this.worldObj.spawnParticle(particle,
				this.posX - this.motionX * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				this.posY - this.motionY * (double)f3 - 0.5D, 
				this.posZ - this.motionZ * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				0, 0, 0);
	}

	@SideOnly(Side.CLIENT)
	public ThawAmmo(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
		world = par1World;
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(MovingObjectPosition var1)
	{
		int var2 = this.worldObj.getBlockId(var1.blockX, var1.blockY, var1.blockZ);
		int var3 = this.worldObj.getBlockMetadata(var1.blockX, var1.blockY, var1.blockZ);
		int x = var1.blockX;
		int y = var1.blockY;
		int z = var1.blockZ;
		if(status == 0){
			//			if (world.getBlockMaterial(x, y, z)== Material.water && world.getBlockMetadata(x,y,z) == 0){
			//				world.setBlockToAir(x,y,z);
			//				world.setBlock(x, y, z, Block.ice.blockID);
			//			}
			//			if (world.getBlockMaterial(x, y, z )== Material.lava && world.getBlockMetadata(x,y,z) == 0){
			//				world.setBlockToAir(x,y,z);
			//				world.setBlock(x, y, z, Block.stone.blockID);
			//			}
			if (world.getBlockMaterial(x, y+1, z ) == Material.air && 
					world.getBlockMaterial(x, y, z ) != Material.snow &&
					world.getBlockMaterial(x, y, z ) != Material.ice ){
				world.setBlock(x, y+1, z, Block.snow.blockID);
			}
		}else if(status == 1){
			//			if (world.getBlockMaterial(x, y, z )== Material.rock && world.getBlockMetadata(x,y,z) == 0){
			//				world.setBlockToAir(x,y,z);
			//				world.setBlock(x, y, z, Block.lavaStill.blockID);
			//			}
			if (world.getBlockMaterial(x, y, z )== Material.ice && world.getBlockMetadata(x,y,z) == 0){
				world.setBlockToAir(x,y,z);
				world.setBlock(x, y, z, Block.waterStill.blockID);
			}
			if (world.getBlockMaterial(x, y, z )== Material.snow && world.getBlockMetadata(x,y,z) == 0){
				world.setBlockToAir(x,y,z);
			}
		}



		if (var1.entityHit != null)
		{
			var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
			if(status == 1){
				var1.entityHit.setFire(20);
			}else if(status == 0 && var1.entityHit instanceof EntityLiving){
				((EntityLiving)var1.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 2));
			}
		}


		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}
