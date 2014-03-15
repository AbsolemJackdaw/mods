package betterbreeds.entity;

import cpw.mods.fml.common.FMLLog;
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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import betterbreeds.ModBreeds;


public class EntityCommonPig extends EntityAnimal
{
//	private int subspecies = 1;
//	private double health = 5;
	private double speed = 0.2;
	private double speedSaddled = 0.2;

	private EntityCommonPig(World par1World)
	{
		super(par1World);
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

	public EntityCommonPig (World w, int subspecies){
		this(w);
		setSubspecies(subspecies);
		setBaseHp((10 + ((2*getSubSpecies())/((getSubSpecies()/3)+1))));
		speedSaddled = 0.2 + ((double)getSubSpecies()/ 10)/2;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes() {

		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(getBaseHp());

		if(!this.getSaddled() && this.riddenByEntity == null)
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(speed);
		else
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(speedSaddled);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(25, 5);
		this.dataWatcher.addObject(26, 5);

	}
	
	public void setSubspecies(int i){
		dataWatcher.updateObject(25, i);
	}
	
	public int getSubSpecies(){
		return dataWatcher.getWatchableObjectInt(25);
	}

	public void setBaseHp(int i){
		dataWatcher.updateObject(26, i);
	}
	
	public int getBaseHp(){
		return dataWatcher.getWatchableObjectInt(26);
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
		FMLLog.getLogger().info("" + getHealth());
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
			EntityPig6 var21 = new EntityPig6(this.worldObj);
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
		int j = rand.nextInt(getSubSpecies())+((2*getSubSpecies())/(getSubSpecies()/3));
		if (this.isBurning())
		{
			this.dropItem(Item.porkCooked.itemID, j);
		}

		else
		{
			this.dropItem(Item.porkRaw.itemID, j);
		}				  
		return Item.porkRaw.itemID;
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

		double chance = Math.random();

		switch (getSubSpecies()) {
		case 1:
			if(chance < 0.5)return new EntityPig2(this.worldObj);
			else if(chance < 0.7)return new EntityPig3(this.worldObj);
			break;
		case 2:
			if(chance < 0.5)return new EntityPig3(this.worldObj);
			else if(chance < 0.7)return new EntityPig4(this.worldObj);
			break;
		case 3:
			if(chance < 0.5)return new EntityPig4(this.worldObj);
			else if(chance < 0.7)return new EntityPig5(this.worldObj);
			break;
		case 4:
			if(chance < 0.5)return new EntityPig2(this.worldObj);
			else if(chance < 0.6)return new EntityPig5(this.worldObj);
			break;
		default:
			break;
		}
		return new EntityPig(this.worldObj);	
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

		return this.spawnBabyAnimal(entityageable);
	}

	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture() {

		switch (getSubSpecies()) {
		case 1:
			return new ResourceLocation("subaraki:BB/pig2.png");
		case 2:
			return new ResourceLocation("subaraki:BB/pig3.png");
		case 3:
			return new ResourceLocation("subaraki:BB/pig4.png");
		case 4:
			return new ResourceLocation("subaraki:BB/pig5.png");
		default:
			break;
		}
		return null;
	}
}
