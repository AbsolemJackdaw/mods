package betterbreeds.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import betterbreeds.ModBreeds;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityJelly extends EntityTameable
{

	public EntityJelly(World par1World)
	{
		super(par1World);
		this.texture = "/subaraki/Jelly.png";
		this.setSize(0.6F, 0.8F);
		this.moveSpeed = 0.3F;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(5, new EntityAIFollowOwner(this, this.moveSpeed, 10.0F, 2.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick()
	{
		this.dataWatcher.updateObject(18, Integer.valueOf(this.getHealth()));
	}

	public int getMaxHealth()
	{
		return 20;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(18, new Integer(this.getHealth()));
		this.dataWatcher.addObject(19, new Byte((byte)0));
	}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.slime", 0.15F, 1.0F);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn()
	{
		return false;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "mob.slime";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "mob.slime";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "mob.slime";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.7F;
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId()
	{
		return ModBreeds.jelly.itemID;
	}


	public float getEyeHeight()
	{
		return this.height * 0.8F;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		return false;
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		int k = rand.nextInt(20);    
		World world = par1EntityPlayer.worldObj;

		if(par1EntityPlayer.username.equals(this.getOwnerName()))
		{
			this.playTameEffect(true);

			if(!world.isRemote)
			{
				if(world.isDaytime())
				{
					if (k == 0)
						par1EntityPlayer.addChatMessage("I love you");
					if (k == 1)
						par1EntityPlayer.addChatMessage("You are so cute :3");
					if (k == 2)
						par1EntityPlayer.addChatMessage("Please dont leave me <3");
					if (k == 3)
						par1EntityPlayer.addChatMessage("I wanna be with you forever !");
					if (k == 4)
						par1EntityPlayer.addChatMessage("I love you so much "+ par1EntityPlayer.username +"!");
					if (k == 5)
						par1EntityPlayer.addChatMessage("You are the only reason I'm still alive ... <3");
					if (k == 6)
						par1EntityPlayer.addChatMessage("I wanna hug you and never let you go !");
					if (k == 7)
						par1EntityPlayer.addChatMessage("You're the most pretty person I've ever seen :3");
					if (k == 8)
						par1EntityPlayer.addChatMessage("I'm terribly in love with you *-*");
					if (k == 9)
						par1EntityPlayer.addChatMessage("Can we make love together...?");
					if (k == 10)
						par1EntityPlayer.addChatMessage("Hug me "+par1EntityPlayer.username+"!");
					if (k == 11)
						par1EntityPlayer.addChatMessage("<3<3<3<3<3<3<3");
				}
				else
				{
					if (k == 0)
						par1EntityPlayer.addChatMessage("I'm scared in the dark !");
					if (k == 1)
						par1EntityPlayer.addChatMessage("Please stay close to me...");
					if (k == 2)
						par1EntityPlayer.addChatMessage("Don't leave me alone !");
					if (k == 3)
						par1EntityPlayer.addChatMessage("I'm scared of the monsters...:( ");
					if (k == 4)
						par1EntityPlayer.addChatMessage("Oh "+ par1EntityPlayer.username +", stay close to me !");
					if (k == 5)
						par1EntityPlayer.addChatMessage("Thanks for staying so close to me ... :3");
					if (k == 6)
						par1EntityPlayer.addChatMessage("I love you, please don't go D:");
					if (k == 7)
						par1EntityPlayer.addChatMessage("Fear of the dark... I'm scared "+ par1EntityPlayer.username);
					if (k == 8)
						par1EntityPlayer.addChatMessage("Please hold my hand...");
					if (k == 9)
						par1EntityPlayer.addChatMessage("I wanna sleep in your arms at night ...");
					if (k == 10)
						par1EntityPlayer.addChatMessage("Hug me "+par1EntityPlayer.username+"!");
					if (k == 11)
						par1EntityPlayer.addChatMessage("<3<3<3<3<3<3<3");
				}
			}
		}
		if(!par1EntityPlayer.username.equals(this.getOwnerName()))
		{
			if(!world.isRemote)
			{
				par1EntityPlayer.addChatMessage("You're not "+ this.getOwnerName()+"... D:");
			}
		}

		return super.interact(par1EntityPlayer);
	}

	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte par1)
	{
		super.handleHealthUpdate(par1);
	}
	
	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	 */
	public EntityJelly spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return null;
	}


	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return spawnBabyAnimal(entityageable);
	}
}
