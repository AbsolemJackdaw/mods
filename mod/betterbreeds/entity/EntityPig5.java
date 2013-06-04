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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;
public class EntityPig5 extends EntityAnimal
{
    public EntityPig5(World par1World)
    {
        super(par1World);
        this.texture = "/subaraki/pig5.png";
        this.setSize(0.9F, 0.9F);
        this.getNavigator().setAvoidsWater(true);
        float var2 = 0.25F;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
        this.tasks.addTask(2, new EntityAIMate(this, var2));
        this.tasks.addTask(3, new EntityAITempt(this, 0.3F, Item.carrotOnAStick.itemID, false));
        this.tasks.addTask(3, new EntityAITempt(this, 0.3F, Item.carrot.itemID, false));   
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(5, new EntityAIWander(this, var2));
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
        return 25;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.pig.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.pig.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.pig.death";
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        if (super.interact(par1EntityPlayer))
        {
            return true;
        }
        else if (this.getSaddled() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer))
        {
            par1EntityPlayer.mountEntity(this);
            return true;
        }
        
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

        if (var2 != null && var2.itemID == ModBreeds.XmasSpecial.itemID && var2.getItemDamage() == 5 && !worldObj.isRemote)
        {
        	 EntityPig6 var21 = new EntityPig6(this.worldObj);        	 var21.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);

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
       
 		int j = rand.nextInt(5);
 		int b = rand.nextInt(6);
 		
 	if(this.isBurning())
 		{
 			if (j == 0)
 	 		{
 	 			  this.dropItem(Item.porkCooked.itemID, 7);

 	 		}
 	 		else if (j > 0 && j <= 4)
 	 			{
 	 				this.dropItem(Item.porkCooked.itemID, 5);
 	 			}
 	 		if ( b == 1)
 	 		{
 	 			this.dropItem(Item.leather.itemID, 2);
 	 		}
 	 		else if ( b == 0)
 	 		{
 	 			this.dropItem(Item.leather.itemID,1);
 	 		}
 	 		
 	 		else if (b > 1 && b <= 5)
 	 		{
 	 			return 0;
 	 		}
 		}
 	else 
 	 		{
 		if (j == 0)
	 		{
	 			  this.dropItem(Item.porkRaw.itemID, 7);

	 		}
	 		else if (j > 0 && j <= 4)
	 			{
	 				this.dropItem(Item.porkRaw.itemID, 5);
	 			}
	 		if ( b == 1)
	 		{
	 			this.dropItem(Item.leather.itemID, 2);
	 		}
	 		else if ( b == 0)
	 		{
	 			this.dropItem(Item.leather.itemID,1);
	 		}
	 		
	 		else if (b > 1 && b <= 5)
	 		{
	 			return 0;
	 		}
 		}
 		
 		
		return 0;

 			
 	}

    /**
     * Returns true if the pig is saddled.
     */
    public boolean getSaddled()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    /**
     * Set or remove the saddle of the pig.
     */
    public void setSaddled(boolean par1)
    {
        if (par1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
        }
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1)
    {
        super.fall(par1);

        if (par1 > 5.0F && this.riddenByEntity instanceof EntityPlayer)
        {
            ((EntityPlayer)this.riddenByEntity).triggerAchievement(AchievementList.flyPig);
        }
    }

    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public EntityAnimal spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
    	int k = rand.nextInt(10);
    	
    	if(k <= 8)
    	{
        return new EntityPig(this.worldObj);
    	}
    	
    	else if (k == 9)
    	{
    		return new EntityPig5 (this.worldObj);
    	}
    	
    	return (EntityAnimal)par1EntityAgeable;
    }

    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack != null && par1ItemStack.itemID == Item.carrot.itemID;
    }
    public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt)
    {
        if (!this.worldObj.isRemote)
        {
            EntityPigZombie var2 = new EntityPigZombie(this.worldObj);
            var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.worldObj.spawnEntityInWorld(var2);
            this.setDead();
        }
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		// TODO Auto-generated method stub
		return spawnBabyAnimal(entityageable);
	}
}
