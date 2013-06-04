package redstone.ammo;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RedAmmo extends EntityThrowable
{
	public int damage = 3;
	public RedAmmo(World par1World)
	{
		super(par1World);
	}

	public RedAmmo(World par1World, EntityLiving par2EntityLiving)
	{
		super(par1World, par2EntityLiving);

	}

	@SideOnly(Side.CLIENT)
	public RedAmmo(World par1World, double par2, double par4, double par6)
	{
		super(par1World);
		this.setSize(0.25F, 0.25F);
		this.setPosition(par2, par4, par6);
		this.yOffset = 0.0F;

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
			if(!this.worldObj.isRemote && this.getThrower() != null && !((EntityPlayer)this.getThrower()).capabilities.isCreativeMode){
				this.dropItem(Item.redstone.itemID, 1);
			}
		}
		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}

	public void onUpdate()
	{
		super.onUpdate();
		float f3 = 0.25F;

		this.worldObj.spawnParticle("reddust",
				this.posX - this.motionX * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				this.posY - this.motionY * (double)f3 - 0.5D, 
				this.posZ - this.motionZ * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				1, 0, 0);

	}
}
