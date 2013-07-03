package redstone.ammo;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AranAmmo extends EntityThrowable
{
	public int damage = 8;
	public World world;
	public AranAmmo(World par1World)
	{
		super(par1World);
		world = par1World;
	}

	public AranAmmo(World par1World, EntityLivingBase par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
		world = par1World;
	}

	public void onUpdate()
	{
		super.onUpdate();
		float f3 = 0.25F;

		this.worldObj.spawnParticle("flame",
				this.posX - this.motionX * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				this.posY - this.motionY * (double)f3 - 0.5D, 
				this.posZ - this.motionZ * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				0.5, 0, 0);
	}

	@SideOnly(Side.CLIENT)
	public AranAmmo(World par1World, double par2, double par4, double par6)
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
		this.worldObj.createExplosion(this, posX, posY, posZ, 1.0f, true);

		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}
