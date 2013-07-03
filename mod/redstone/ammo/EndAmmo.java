package redstone.ammo;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EndAmmo extends EntityThrowable
{
	public int damage = 14;

	public EndAmmo(World par1World)
	{
		super(par1World);
	}

	public EndAmmo(World par1World, EntityLivingBase par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	@SideOnly(Side.CLIENT)
	public EndAmmo(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		float f3 = 0.25F; 
		this.worldObj.spawnParticle("enchantmenttable", this.posX - this.motionX * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, this.posY - this.motionY * (double)f3 - 0.5D, this.posZ - this.motionZ * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, this.motionX, this.motionY, this.motionZ);

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
				this.dropItem(Item.enderPearl.itemID, 1);
			}
		}
		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
	@Override
	protected float getGravityVelocity(){
		return 0.001F;
	}
}
