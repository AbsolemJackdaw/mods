package petBuddy.root;

import petBuddy.PetBuddyMain;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BuddyBase extends EntityTameable
{
	/**values copied from the enderdragon for the dragon*/
	public int ringBufferIndex = -1;
	public float prevAnimTime = -0.0F;
	public float animTime = -0.0F;
	public double[][] ringBuffer = new double[64][3];


	//	private int guiID;
	public BuddyBase(World par1World)
	{
		super(par1World);
		this.setSize(0.2F, 0.8F);
		this.moveSpeed = 0.3F;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, this.moveSpeed, 10.0F, 2.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, Entity.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		/**Later maybe*/
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

	}
	public BuddyBase(World par1World, EntityPlayer player)
	{
		super(par1World);
		this.setSize(0.8F, 0.8F);
		this.moveSpeed = 0.3F;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, this.moveSpeed, 10.0F, 2.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.setOwner(player.username);

	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	public int getMaxHealth()
	{
		return 666;
	}

	/**retrieve the buddy's model based on the integer passed down by the buttons of the Gui.*/
	@SideOnly(Side.CLIENT)
	public abstract ModelBase getModel();

	@Override
	public abstract String getTexture();

	/**retrieve the buddy's mounted offset based on the integer passed down by the buttons of the Gui.*/
	public abstract float getMountedOffset();

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(18, new Integer(this.getHealth()));
	}


	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	protected boolean canDespawn()
	{
		return false;
	}

	public float getEyeHeight()
	{
		return this.height ;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.getOwner() == null) {
			this.setDead();
			return;
		}
		if (this.dimension != getOwner().dimension) {
			this.setDead();
			return;
		}
		if(getOwner().isDead){
			this.setDead();
			return;
		}

		if(PetBuddyMain.proxy.getGuiId() == 19){
			float f;
			float f1;

			f = MathHelper.cos(this.animTime * (float)Math.PI * 2.0F);
			f1 = MathHelper.cos(this.prevAnimTime * (float)Math.PI * 2.0F);

			if (f1 <= -0.3F && f >= -0.3F)
			{
				this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
			}


			this.prevAnimTime = this.animTime;
			float f2;


			f = 0.2F / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F);
			f *= (float)Math.pow(2.0D, this.motionY);

			this.animTime += f/5;

			this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);

			if (this.ringBufferIndex < 0)
			{
				for (int i = 0; i < this.ringBuffer.length; ++i)
				{
					this.ringBuffer[i][0] = (double)this.rotationYaw;
					this.ringBuffer[i][1] = this.posY;
				}
			}

			if (++this.ringBufferIndex == this.ringBuffer.length)
			{
				this.ringBufferIndex = 0;
			}

			this.ringBuffer[this.ringBufferIndex][0] = (double)this.rotationYaw;
			this.ringBuffer[this.ringBufferIndex][1] = this.posY;
			double d0;
			double d1;
			double d2;
			double d3;
			float f3;

			if (this.worldObj.isRemote)
			{
				if (this.newPosRotationIncrements > 0)
				{
					d3 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
					d0 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
					d1 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
					d2 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
					this.rotationYaw = (float)((double)this.rotationYaw + d2 / (double)this.newPosRotationIncrements);
					this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
					--this.newPosRotationIncrements;
					this.setPosition(d3, d0, d1);
					this.setRotation(this.rotationYaw, this.rotationPitch);
				}
			}

							this.renderYawOffset = this.rotationYaw;
		}
	}

	@Override
	public void onUpdate(){
		super.onUpdate();
		if (ridingEntity != null) {
			//stops up-and-down head movement
			rotationPitch = 0;
			//Control where the pet is facing (doesn't work while standing still)
			rotationYaw = prevRotationYaw = ridingEntity.rotationYaw;
		}
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		int i = 1;
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
	}


	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer player)
	{
		return super.interact(player);
	}


	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	 */
	public BuddyBase spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return null;
	}

	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}

	public double[] getMovementOffsets(int par1, float par2)
	{
		if (this.health <= 0)
		{
			par2 = 0.0F;
		}

		par2 = 1.0F - par2;
		int j = this.ringBufferIndex - par1 * 1 & 63;
		int k = this.ringBufferIndex - par1 * 1 - 1 & 63;
		double[] adouble = new double[3];
		double d0 = this.ringBuffer[j][0];
		double d1 = MathHelper.wrapAngleTo180_double(this.ringBuffer[k][0] - d0);
		adouble[0] = d0 + d1 * (double)par2;
		d0 = this.ringBuffer[j][1];
		d1 = this.ringBuffer[k][1] - d0;
		adouble[1] = d0 + d1 * (double)par2;
		adouble[2] = this.ringBuffer[j][2] + (this.ringBuffer[k][2] - this.ringBuffer[j][2]) * (double)par2;
		return adouble;
	}
}
