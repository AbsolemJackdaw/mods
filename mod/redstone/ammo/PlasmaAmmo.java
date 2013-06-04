package redstone.ammo;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlasmaAmmo extends EntityThrowable
{
	public int damage = 6;
	static NBTTagCompound tag;

	public PlasmaAmmo(World par1World)
	{
		super(par1World);
	}

	public PlasmaAmmo(World par1World, EntityLiving par2EntityLiving)
	{
		super(par1World, par2EntityLiving);

	}

	@SideOnly(Side.CLIENT)
	public PlasmaAmmo(World par1World, double par2, double par4, double par6)
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
		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}

	public void onUpdate()
	{
		super.onUpdate();
		float f3 = 0.25F;

		this.worldObj.spawnParticle("mobSpell",
				this.posX - this.motionX * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				this.posY - this.motionY * (double)f3 - 0.5D, 
				this.posZ - this.motionZ * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				0, 0.9, 0.9);

		if(tag == null){
			tag = new NBTTagCompound();
			tag.setFloat("deadtime", 10.0f);
		}

		if(tag != null && tag.hasKey("deadtime")){
			float time = tag.getFloat("deadtime");
			time -= 1.0f;
			tag.setFloat("deadtime", time);

			if (time <= 0.0f){
				if(!worldObj.isRemote)
					this.setDead();
				tag.setFloat("deadtime", 10.0f);
			}
		}
	}

	@Override
	protected float getGravityVelocity(){
		return 0.001F;
	}
}
