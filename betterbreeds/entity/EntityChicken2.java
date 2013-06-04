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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityChicken2 extends EntityAnimal
{
	public boolean field_70885_d = false;
	public float field_70886_e = 0.0F;
	public float destPos = 0.0F;
	public float field_70884_g;
	public float field_70888_h;
	public float field_70889_i = 1.0F;

	/** The time until the next egg is spawned. */
	public int timeUntilNextEgg;

	public EntityChicken2(World par1World)
	{
		super(par1World);
		this.texture = "/mob/chicken.png";
		this.setSize(0.3F, 0.7F);
		this.timeUntilNextEgg = this.rand.nextInt(6000) + 1200;
		float var2 = 0.25F;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		this.tasks.addTask(2, new EntityAIMate(this, var2));
		this.tasks.addTask(3, new EntityAITempt(this, 0.25F, Item.seeds.itemID, false));
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
		return 10;
	}

	public boolean interact(EntityPlayer par1EntityPlayer)
	{

		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

		if (var2 != null && var2.itemID == ModBreeds.XmasSpecial.itemID && var2.getItemDamage() == 5&& !worldObj.isRemote)
		{
			EntityChicken5 var21 = new EntityChicken5(this.worldObj);  
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
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		this.field_70888_h = this.field_70886_e;
		this.field_70884_g = this.destPos;
		this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);

		if (this.destPos < 0.0F)
		{
			this.destPos = 0.0F;
		}

		if (this.destPos > 1.0F)
		{
			this.destPos = 1.0F;
		}

		if (!this.onGround && this.field_70889_i < 1.0F)
		{
			this.field_70889_i = 1.0F;
		}

		this.field_70889_i = (float)((double)this.field_70889_i * 0.9D);

		if (!this.onGround && this.motionY < 0.0D)
		{
			this.motionY *= 0.6D;
		}

		this.field_70886_e += this.field_70889_i * 2.0F;

		if (!this.isChild() && !this.worldObj.isRemote && --this.timeUntilNextEgg <= 0)
		{
			int k = rand.nextInt(3)+1;
			this.worldObj.playSoundAtEntity(this, "mob.chickenplop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.dropItem(Item.feather.itemID, k);
			this.timeUntilNextEgg = this.rand.nextInt(6000) + 1200;
		}
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float par1) {}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "mob.chicken.say";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "mob.chicken.hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "mob.chicken.hurt";
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId()
	{
		return Item.feather.itemID;
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = this.rand.nextInt(5) + this.rand.nextInt(1 + par2);

		for (int var4 = 0; var4 < var3; ++var4)
		{
			this.dropItem(Item.feather.itemID, 1);
		}
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	 */
	public EntityAnimal spawnBabyAnimal(EntityAgeable var1)
	{
		int k = rand.nextInt(100);

		if(k <44)
		{
			return new EntityChicken3(this.worldObj);
		}
		else if (k >=44 && k <= 89)
		{
			return new EntityChicken2 (this.worldObj);
		}
		if (k > 89 && k<=99)
		{
			return new EntityChicken4 (this.worldObj);
		}
		if(k!=0)
		{
			int v = rand.nextInt(50);
			if(v < 50)
			{
				return new EntityChicken5 (this.worldObj);
			}
		}
		return (EntityAnimal) var1;

	}
	
	@Override
    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack != null && par1ItemStack.getItem() instanceof ItemSeeds;
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return spawnBabyAnimal(entityageable);
	}
}
