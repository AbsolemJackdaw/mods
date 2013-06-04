package redstone.ammo;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GreenAmmo extends EntityThrowable
{
	public int damage = 5;
	public GreenAmmo(World par1World)
	{
		super(par1World);
	}

	public GreenAmmo(World par1World, EntityLiving par2EntityLiving, double d)
	{
		super(par1World, par2EntityLiving);

		this.setSize(0.25F, 0.25F);
		this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + (double)par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
		this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F)+d;
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F+d);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		float f = 0.4F;
		this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
		this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
		this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * f);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.func_70182_d(), 1.0F);
	}

	@SideOnly(Side.CLIENT)
	public GreenAmmo(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		float f3 = 0.25F; 
		this.worldObj.spawnParticle("flame", this.posX - this.motionX * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, this.posY - this.motionY * (double)f3 - 0.5D, this.posZ - this.motionZ * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, this.motionX, this.motionY, this.motionZ);

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
			 if(!this.worldObj.isRemote &&this.getThrower() != null && !((EntityPlayer)this.getThrower()).capabilities.isCreativeMode){
				 this.dropItem(Item.rottenFlesh.itemID, 1);
			 }
		 }
		 if (!this.worldObj.isRemote)
		 {
			 this.setDead();
		 }
	 }
}
