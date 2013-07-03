package betterbreeds.entity;
import java.util.Random;

import betterbreeds.ModBreeds;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySheep5 extends EntityAnimal
{
	/**
	 * Holds the RGB table of the sheep colors - in OpenGL glColor3f values - used to render the sheep colored fleece.
	 */
	public static final float[][] fleeceColorTable = new float[][] {{1.0F, 1.0F, 1.0F}, {0.95F, 0.7F, 0.2F}, {0.9F, 0.5F, 0.85F}, {0.6F, 0.7F, 0.95F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.7F, 0.8F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.6F, 0.7F}, {0.7F, 0.4F, 0.9F}, {0.2F, 0.4F, 0.8F}, {0.5F, 0.4F, 0.3F}, {0.4F, 0.5F, 0.2F}, {0.8F, 0.3F, 0.3F}, {0.1F, 0.1F, 0.1F}};

	/**
	 * Used to control movement as well as wool regrowth. Set to 40 on handleHealthUpdate and counts down with each
	 * tick.
	 */
	private int sheepTimer;

	/** The eat grass AI task for this mob. */
	private EntityAIEatGrass aiEatGrass = new EntityAIEatGrass(this);

	public EntitySheep5(World par1World)
	{
		super(par1World);
		this.setSize(0.9F, 1.3F);
		float var2 = 0.23F;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAIAvoidEntity(this, EntityWolf3.class, 16.0F, 0.5F, 0.7F));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIPanic(this, 0.38F));
		this.tasks.addTask(3, new EntityAIMate(this, var2));
		this.tasks.addTask(4, new EntityAITempt(this, 0.25F, Item.wheat.itemID, false));
		this.tasks.addTask(5, new EntityAIFollowParent(this, 0.25F));
		this.tasks.addTask(6, this.aiEatGrass);
		this.tasks.addTask(7, new EntityAIWander(this, var2));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));


	}
	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		// TODO Auto-generated method stub
		return spawnBabyAnimal(entityageable);
	}
	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	protected void updateAITasks()
	{
		this.sheepTimer = this.aiEatGrass.getEatGrassTick();
		super.updateAITasks();
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		if (this.worldObj.isRemote)
		{
			this.sheepTimer = Math.max(0, this.sheepTimer - 1);
		}

		super.onLivingUpdate();
	}

	public int getMaxHealth()
	{
		return 10;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		if (!this.getSheared())
		{
			this.entityDropItem(new ItemStack(Block.cloth.blockID, 1, this.getFleeceColor()), 0.0F);
		}
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId()
	{
		return Block.cloth.blockID;
	}

	public void handleHealthUpdate(byte par1)
	{
		if (par1 == 10)
		{
			this.sheepTimer = 40;
		}
		else
		{
			super.handleHealthUpdate(par1);
		}
	}

	public float func_70894_j(float par1)
	{
		return this.sheepTimer <= 0 ? 0.0F : (this.sheepTimer >= 4 && this.sheepTimer <= 36 ? 1.0F : (this.sheepTimer < 4 ? ((float)this.sheepTimer - par1) / 4.0F : -((float)(this.sheepTimer - 40) - par1) / 4.0F));
	}

	public float func_70890_k(float par1)
	{
		if (this.sheepTimer > 4 && this.sheepTimer <= 36)
		{
			float var2 = ((float)(this.sheepTimer - 4) - par1) / 32.0F;
			return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(var2 * 28.7F);
		}
		else
		{
			return this.sheepTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch / (180F / (float)Math.PI);
		}
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	 public boolean interact(EntityPlayer par1EntityPlayer)
	 {
		 ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

		 if (var2 != null && var2.itemID == Item.shears.itemID && !this.getSheared() && !this.isChild())
		 {
			 if (!this.worldObj.isRemote)
			 {
				 this.setSheared(true);
				 int var3 = 1 + this.rand.nextInt(2);

				 for (int var4 = 0; var4 < var3; ++var4)
				 {
					 int p = rand.nextInt(5);
					 int c = rand.nextInt(10);
					 if (c >=8)
					 {
						 EntityItem var5 = this.entityDropItem(new ItemStack(ModBreeds.XmasSpecial, 1, p), 1.0F);
						 var5.motionY += (double)(this.rand.nextFloat() * 0.05F);
						 var5.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
						 var5.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
					 }
					 if(c < 8)
					 {
						 EntityItem var5 = this.entityDropItem(new ItemStack(Item.coal), 1.0F);
						 var5.motionY += (double)(this.rand.nextFloat() * 0.05F);
						 var5.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
						 var5.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
					 }

				 }
			 }

			 var2.damageItem(1, par1EntityPlayer);
		 }


		 return super.interact(par1EntityPlayer);
	 }

	 /**
	  * (abstract) Protected helper method to write subclass entity data to NBT.
	  */
	 public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	 {
		 super.writeEntityToNBT(par1NBTTagCompound);


		 par1NBTTagCompound.setBoolean("Sheared", this.getSheared());
		 par1NBTTagCompound.setByte("Color", (byte)this.getFleeceColor());

	 }

	 /**
	  * (abstract) Protected helper method to read subclass entity data from NBT.
	  */
	 public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	 {
		 super.readEntityFromNBT(par1NBTTagCompound);


		 this.setSheared(par1NBTTagCompound.getBoolean("Sheared"));
		 this.setFleeceColor(par1NBTTagCompound.getByte("Color"));

	 }

	 /**
	  * Returns the sound this mob makes while it's alive.
	  */
	 /**
	  * Returns the sound this mob makes while it's alive.
	  */
	 protected String getLivingSound()
	 {
		 int r = rand.nextInt(2);
		 return r == 0 ? "xmasbb.bells": "mod.sheep.say";
	 }

	 /**
	  * Returns the sound this mob makes when it is hurt.
	  */
	 protected String getHurtSound()
	 {
		 return "mob.sheep.say";
	 }

	 /**
	  * Returns the sound this mob makes on death.
	  */
	 protected String getDeathSound()
	 {
		 return "mob.sheep.say";
	 }

	 public int getFleeceColor()
	 {
		 return this.dataWatcher.getWatchableObjectByte(16) & 15;
	 }

	 public void setFleeceColor(int par1)
	 {
		 byte var2 = this.dataWatcher.getWatchableObjectByte(16);
		 this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 240 | par1 & 15)));
	 }

	 /**
	  * returns true if a sheeps wool has been sheared
	  */
	 public boolean getSheared()
	 {
		 return (this.dataWatcher.getWatchableObjectByte(16) & 16) != 0;
	 }

	 /**
	  * make a sheep sheared if set to true
	  */
	 public void setSheared(boolean par1)
	 {
		 byte var2 = this.dataWatcher.getWatchableObjectByte(16);

		 if (par1)
		 {
			 this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 16)));
		 }
		 else
		 {
			 this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -17)));
		 }
	 }

	 /**
	  * This method is called when a sheep spawns in the world to select the color of sheep fleece.
	  */
	 public static int getRandomFleeceColor(Random par0Random)
	 {
		 int var1 = par0Random.nextInt(100);
		 return var1 < 5 ? 15 : (var1 < 10 ? 7 : (var1 < 15 ? 8 : (var1 < 18 ? 12 : (par0Random.nextInt(500) == 0 ? 6 : 0))));
	 }

	 /**
	  * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	  */
	 public EntityAnimal spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	 {
		 return null;

	 }

	 /**
	  * This function applies the benefits of growing back wool and faster growing up to the acting entity. (This
	  * function is used in the AIEatGrass)
	  */
	 public void eatGrassBonus()
	 {
		 this.setSheared(false);

		 if (this.isChild())
		 {
			 int var1 = this.getGrowingAge() + 3600;

			 if (var1 > 0)
			 {
				 var1 = 0;
			 }
			 this.setGrowingAge(var1);
		 }
	 }



}
