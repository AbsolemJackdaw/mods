package redstone.ammo;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlueAmmo extends EntityThrowable
{
	public int damage = 8;
	public World world;
	public BlueAmmo(World par1World)
	{
		super(par1World);
		world = par1World;
	}

	public BlueAmmo(World par1World, EntityLiving par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
		world = par1World;
	}

	public void onUpdate()
	{
		super.onUpdate();
		float f3 = 0.25F;

		this.worldObj.spawnParticle("reddust",
				this.posX - this.motionX * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				this.posY - this.motionY * (double)f3 - 0.5D, 
				this.posZ - this.motionZ * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				-1, 0, 1);

	}
	
	@SideOnly(Side.CLIENT)
	public BlueAmmo(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
		world = par1World;
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
	{
		if (par1MovingObjectPosition.entityHit != null)
		{
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
		}
		else
		 {
			 if(!this.worldObj.isRemote && this.getThrower()!= null && !((EntityPlayer)this.getThrower()).capabilities.isCreativeMode){
				 this.entityDropItem(new ItemStack(Item.dyePowder,1,4), 0f);
			 }
		 }
		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}
