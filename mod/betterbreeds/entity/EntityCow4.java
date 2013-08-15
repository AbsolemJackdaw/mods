package betterbreeds.entity;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import betterbreeds.ModBreeds;

public class EntityCow4 extends EntityAnimal
{
    public EntityCow4(World par1World)
    {
        super(par1World);
        this.isImmuneToFire =true;
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
    	int r = rand.nextInt(2);
        return r == 0 ? "subaraki:bells": "mob.cow.say";
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

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {int k = 1+rand.nextInt(4);
this.entityDropItem(new ItemStack(ModBreeds.XmasSpecial,1,3), 0f);
        return 4;
    }
    
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

        if (var2 != null && var2.itemID ==(ModBreeds.XmasSpecial.itemID) && var2.getItemDamage()==4)
        {
            if (--var2.stackSize <= 0)
            {
                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(ModBreeds.XmasSpecial,1,2));
            }
            else if (!par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ModBreeds.XmasSpecial,1,2)))
            {
                par1EntityPlayer.dropPlayerItem(new ItemStack(ModBreeds.XmasSpecial.itemID, 1, 2));
            }

            return true;
        }
        else
        {
            return super.interact(par1EntityPlayer);
        }
    }

    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	 worldObj.spawnParticle("smoke", posX, posY+(double)(height), posZ, 0.0D, 0.0D, 0.0D);
    	 worldObj.spawnParticle("smoke", posX+0.2F, posY+(double)(height), posZ+0.2f, 0.0D, 0.0D, 0.0D);
    	 worldObj.spawnParticle("smoke", posX-0.2f, posY+(double)(height), posZ+0.2f, 0.0D, 0.0D, 0.0D);
    	 worldObj.spawnParticle("smoke", posX-0.2f, posY+(double)(height), posZ-0.2f, 0.0D, 0.0D, 0.0D);
    	 worldObj.spawnParticle("smoke", posX+0.2f, posY+(double)(height), posZ-0.2f, 0.0D, 0.0D, 0.0D);



    }

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
