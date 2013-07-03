package scythemod.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityThrowCake extends EntityThrowable 
{   
	private EntityLiving thrower;

	public EntityThrowCake(World par1World)
	{
		super(par1World);
	}

	public EntityThrowCake(World par1World, EntityLiving par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	@SideOnly(Side.CLIENT)
	public EntityThrowCake(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
	{

		if (par1MovingObjectPosition.entityHit != null)
		{
			byte var2 = 8;

			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), var2);

			if (!this.worldObj.isRemote)
			{
				this.setDead();
			}
		} 
		for (int var3 = 0; var3 < 8; ++var3)
		{
			this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}        

	}
	protected float getGravityVelocity()
	{
		return 0.1F;
	}

}

