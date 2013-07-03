package betterbreeds.entity;

import betterbreeds.ModBreeds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCow3 extends EntityAnimal
{
	public EntityCow3(World par1World)
	{
		super(par1World);
		this.setSize(0.9F, 1.3F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		this.tasks.addTask(2, new EntityAIMate(this, 0.2F));
		this.tasks.addTask(3, new EntityAITempt(this, 0.25F, Item.wheat.itemID, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 0.25F));
		this.tasks.addTask(5, new EntityAIWander(this, 0.2F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
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
		return 20;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "mob.cow.say";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "mob.cow.hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "mob.cow.hurt";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.4F;
	}
	public boolean interact(EntityPlayer par1EntityPlayer)
	{

		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

		if (var2 != null && var2.itemID == ModBreeds.XmasSpecial.itemID && var2.getItemDamage() == 5 && !worldObj.isRemote)
		{
			EntityCow4 var21 = new EntityCow4(this.worldObj);
			var21.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.worldObj.spawnEntityInWorld(var21);
			this.setDead();

			if (--var2.stackSize <= 0)
			{
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, null);
			}


			return true;
		}
		else
		{
			return super.interact(par1EntityPlayer);
		}
	}
	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	 protected int getDropItemId()
	{
		 int v = rand.nextInt (5);
		 int s = rand.nextInt(3);


		 if (v ==0)
		 {
			 this.dropItem(Item.leather.itemID, 1);
		 }
		 else if (v ==1)
		 {
			 this.dropItem(Item.leather.itemID, 2);

		 }
		 else if (v ==2)
		 {
			 this.dropItem(Item.leather.itemID, 3);

		 }
		 else if (v ==3)
		 {
			 this.dropItem(Item.leather.itemID, 4);

		 }
		 else if (v ==4)
		 {
			 this.dropItem(Item.leather.itemID, 5);

		 }

		 if(this.isBurning())
		 {
			 if (s == 0)
			 {
				 this.dropItem(Item.beefCooked.itemID,1);
			 }

			 else if (s == 2)
			 {
				 this.dropItem(Item.bone.itemID,1);
			 }
			 else if (s == 3)
			 {
				 return 0;
			 }
		 }
		 else
		 {
			 if (s == 0)
			 {
				 this.dropItem(Item.beefRaw.itemID,1);
			 }

			 else if (s == 2)
			 {
				 this.dropItem(Item.bone.itemID,1);
			 }
			 else if (s == 3)
			 {
				 return 0;
			 }
		 }
		 return 0;
	}





	 /**
	  * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	  */
	 public EntityAnimal spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	 {
		 int k = rand.nextInt(50);

		 if(k <= 24)
		 {
			 return new EntityCow3(this.worldObj);
		 }
		 else if (k > 24 && k <=36)
		 {
			 return new EntityCow (this.worldObj);
		 }
		 else if (k > 36 && k <= 48)
		 {
			 return new EntityCow2 (this.worldObj);
		 }
		 else if (k > 48 && k <=49)
		 {
			 return new EntityMooshroom (this.worldObj);
		 }
		 if(k!=0)
		 {
			 int v = rand.nextInt(50);
			 if(v < 50)
			 {
				 return new EntityCow3 (this.worldObj);
			 }}
		 return (EntityAnimal)par1EntityAgeable;
	 }


		@Override
		public EntityAgeable createChild(EntityAgeable entityageable) {
			// TODO Auto-generated method stub
			return spawnBabyAnimal(entityageable);
		}
}
