package petBuddy.root;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import petBuddy.PetBuddyMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BuddyBase extends EntityTameable
{
//	private int guiID;
	public BuddyBase(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.5F);
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
		return 6;
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
		return super.attackEntityFrom(par1DamageSource, 0);
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote) {
			EntityPlayer player = (EntityPlayer) getOwner();
			if (player == null) {
				this.setDead();
				return;
			}
			if (this.dimension != getOwner().dimension) {
				this.setDead();
				return;
			}
		}
		super.onLivingUpdate();
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
		if(player.inventory.getCurrentItem() != null){
			if(player.inventory.getCurrentItem().getItem().equals(Item.stick)){
				PetBuddyMain.proxy.openGui(0, player, player.username);
			}
		} 
		else if (!this.worldObj.isRemote && this.ridingEntity == null){
			this.mountEntity(player);
			this.ridingEntity = player;
		}
		else if(!this.worldObj.isRemote && this.ridingEntity == player){
			this.unmountEntity(player);
			this.ridingEntity = null;
		}
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
}
