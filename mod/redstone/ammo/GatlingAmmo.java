package redstone.ammo;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GatlingAmmo extends EntityThrowable
{
	public int damage = 1;
	public World world;
	public static int blockType;

	public GatlingAmmo(World par1World)
	{
		super(par1World);
		world = par1World;
	}

	public GatlingAmmo(World par1World, EntityLivingBase par2EntityLiving, int parser)
	{
		super(par1World, par2EntityLiving);
		world = par1World;
		blockType = parser;
	}

	public void onUpdate()
	{
		super.onUpdate();
		float f3 = 0.25F;

		this.worldObj.spawnParticle("",
				this.posX - this.motionX * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				this.posY - this.motionY * (double)f3 - 0.5D, 
				this.posZ - this.motionZ * (double)f3 + this.rand.nextDouble() * 0.6D - 0.3D, 
				0, 0, 0);
	}

	@SideOnly(Side.CLIENT)
	public GatlingAmmo(World par1World, double par2, double par4, double par6)
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

			if(par1MovingObjectPosition.entityHit instanceof EntityLiving){
				if(!(par1MovingObjectPosition.entityHit instanceof EntityPlayer)){
					((EntityLiving)par1MovingObjectPosition.entityHit).hurtTime = 0;
					((EntityLiving)par1MovingObjectPosition.entityHit).hurtResistantTime = 0;
				}
			}
		}
		else
		{
			if(!this.worldObj.isRemote && this.getThrower() != null && !((EntityPlayer)this.getThrower()).capabilities.isCreativeMode){

				if(blockType == 1){
					this.dropItem(Block.dirt.blockID,1);

				}
				if(blockType == 2){
					this.dropItem(Block.cobblestone.blockID,1);

				}
			}
		}

		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}
