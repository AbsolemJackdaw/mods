package betterbreeds.entity.ai.extended;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.world.World;
import betterbreeds.entity.EntityCow2;
import betterbreeds.entity.EntityCow3;

public class EntityAIMateCow extends EntityAIBase
{
	private EntityAnimal theAnimal;
	World theWorld;
	private EntityAnimal targetMate;
	Random rand = new Random();

	/**
	 * Delay preventing a baby from spawning immediately when two mate-able animals find each other.
	 */
	int spawnBabyDelay = 0;

	/** The speed the creature moves at during mating behavior. */
	float moveSpeed;

	public EntityAIMateCow(EntityAgeable par1EntityAnimal, float par2)
	{
		this.theAnimal = (EntityAnimal)par1EntityAnimal;
		this.theWorld = par1EntityAnimal.worldObj;
		this.moveSpeed = par2;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		if (!this.theAnimal.isInLove())
		{
			return false;
		}
		else
		{
			this.targetMate = this.getNearbyMate();
			return this.targetMate != null;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{
		return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.targetMate = null;
		this.spawnBabyDelay = 0;
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
		this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
		++this.spawnBabyDelay;

		if (this.spawnBabyDelay >= 60 && this.theAnimal.getDistanceSqToEntity(this.targetMate) < 9.0D)
		{
			this.spawnBaby();
		}
	}

	/**
	 * Loops through nearby animals and finds another animal of the same type that can be mated with. Returns the first
	 * valid mate found.
	 */
	private EntityAnimal getNearbyMate()
	{
		float f = 8.0F;
		List list = this.theWorld.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.boundingBox.expand((double)f, (double)f, (double)f));
		double d0 = Double.MAX_VALUE;
		EntityAnimal entityanimal = null;
		Iterator iterator = list.iterator();

		while (iterator.hasNext())
		{
			EntityAnimal entityanimal1 = (EntityAnimal)iterator.next();

			if (this.theAnimal.canMateWith(entityanimal1) && this.theAnimal.getDistanceSqToEntity(entityanimal1) < d0)
			{
				entityanimal = entityanimal1;
				d0 = this.theAnimal.getDistanceSqToEntity(entityanimal1);
			}
		}

		return entityanimal;
	}

	/**
	 * Spawns a baby animal of the same type.
	 */
	private void spawnBaby()
	{
		EntityAgeable entityageable = null;

		double chance = Math.random();
		
		if(chance < 0.40) // 40 % chance
		{
			entityageable= new EntityCow(theWorld);
		}
		else if (chance < 0.70)// its not < 4, so 5,6 or 7. 30% chance
		{
			entityageable= new EntityCow2 (theWorld);
		}
		else if (chance < 0.99 )// all is smaller then 7. so 8 or 9. 20 % or rather 29,9 %
		{
			entityageable= new EntityCow3 (theWorld);
		}
		else // 0.1%
		{
			entityageable= new EntityMooshroom (theWorld);
		}

		if (entityageable != null)
		{
			this.theAnimal.setGrowingAge(6000);
			this.targetMate.setGrowingAge(6000);
			this.theAnimal.resetInLove();
			this.targetMate.resetInLove();
			entityageable.setGrowingAge(-18000);
			entityageable.setLocationAndAngles(this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, 0.0F, 0.0F);
			this.theWorld.spawnEntityInWorld(entityageable);
			Random random = this.theAnimal.getRNG();

			for (int i = 0; i < 7; ++i)
			{
				double d0 = random.nextGaussian() * 0.02D;
				double d1 = random.nextGaussian() * 0.02D;
				double d2 = random.nextGaussian() * 0.02D;
				this.theWorld.spawnParticle("heart", this.theAnimal.posX + (double)(random.nextFloat() * this.theAnimal.width * 2.0F) - (double)this.theAnimal.width, this.theAnimal.posY + 0.5D + (double)(random.nextFloat() * this.theAnimal.height), this.theAnimal.posZ + (double)(random.nextFloat() * this.theAnimal.width * 2.0F) - (double)this.theAnimal.width, d0, d1, d2);
			}

			this.theWorld.spawnEntityInWorld(new EntityXPOrb(this.theWorld, this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, random.nextInt(7) + 1));
		}
	}
}
