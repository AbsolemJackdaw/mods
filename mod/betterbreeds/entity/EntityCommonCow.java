package betterbreeds.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityCommonCow extends EntityAnimal
{
	
	public static final String leatherCow = "leather";
	public static final String meatCow = "meat";
	
	private double speed = 0.25;
	private double health = 5;
	private String subSpecies = "";
	
	public EntityCommonCow(World par1World)
	{
		super(par1World);
		this.setSize(0.9F, 1.3F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Item.wheat.itemID, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}
	
	public EntityCommonCow(World w, String subSpecies){
		this(w);
		
		this.subSpecies = subSpecies;
		if(subSpecies.equals("leather")){
			health = 10;
			speed = 0.22;
		}else if(subSpecies.equals("meat")){
			health = 14;
			speed = 0.2;
		}
		
			
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(health);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(speed);
	}

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
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.cow.step", 0.15F, 1.0F);
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId()
	{
		return -1;
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	 * par2 - Level of Looting used to kill this mob.
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		int chancePerSpecies = subSpecies.equals("leather") ? 5 : 1;
		int chancePerSpecies2 = subSpecies.equals("leather") ? 1 : 7;

		int j = this.rand.nextInt(chancePerSpecies) + this.rand.nextInt(1 + par2/2);
		int k;

		for (k = 0; k < j; ++k)
		{
			if(subSpecies.equals("leather")){
				this.dropItem(Item.leather.itemID, 1);
			}
		}

		
		j = this.rand.nextInt(chancePerSpecies2) + this.rand.nextInt(1 + par2*2);

		for (k = 0; k < j; ++k)
		{
			if (this.isBurning())
			{
				this.dropItem(Item.beefCooked.itemID, 1);
			}
			else
			{
				this.dropItem(Item.beefRaw.itemID, 1);
			}
		}
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

		if (itemstack != null && itemstack.itemID == Item.bucketEmpty.itemID && !par1EntityPlayer.capabilities.isCreativeMode)
		{
			if (itemstack.stackSize-- == 1)
			{
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(Item.bucketMilk));
			}
			else if (!par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.bucketMilk)))
			{
				par1EntityPlayer.dropPlayerItem(new ItemStack(Item.bucketMilk.itemID, 1, 0));
			}

			return true;
		}
		else
		{
			return super.interact(par1EntityPlayer);
		}
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	 */
	public EntityAgeable spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		double chance = Math.random();

		//more chance of having a regular cow
		//0.25 meat, 0.45 cow, 0.29 leather
		if(chance < 0.25)
			return new EntityCommonCow(this.worldObj, meatCow);
		else if (chance < 0.7)
			return new EntityCow (this.worldObj);
		else if (chance < 0.99)
			return new EntityCommonCow (this.worldObj, leatherCow);
		else
			return new EntityMooshroom (this.worldObj);
	}

	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}


	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture() {

		return subSpecies.equals("meat") ? new ResourceLocation("subaraki:BB/cow2.png") : new ResourceLocation("subaraki:BB/cow3.png");
	}
}
