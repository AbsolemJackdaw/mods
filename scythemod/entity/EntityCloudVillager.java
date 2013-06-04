package scythemod.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import scythemod.BaseScytheFile;


import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Tuple;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCloudVillager extends EntityAgeable implements INpc, IMerchant
{
	private int randomTickDivider;
	private boolean isMating;
	private boolean isPlaying;
	Village villageObj;

	/** This villager's current customer. */
	private EntityPlayer buyingPlayer;

	/** Initialises the MerchantRecipeList.java */
	private MerchantRecipeList buyingList;
	private int timeUntilReset;

	/** addDefaultEquipmentAndRecipies is called if this is true */
	private boolean needsInitilization;
	private int wealth;

	/** Last player to trade with this villager, used for aggressivity. */
	private String lastBuyingPlayer;
	private boolean field_82190_bM;
	private float field_82191_bN;

	/**
	 * a villagers recipe list is intialized off this list ; the 2 params are min/max amount they will trade for 1
	 * emerald
	 */
	public static final Map villagerStockList = new HashMap();

	/**
	 * Selling list of Blacksmith items. negative numbers mean 1 emerald for n items, positive numbers are n emeralds
	 * for 1 item
	 */
	public static final Map blacksmithSellingList = new HashMap();

	public EntityCloudVillager(World par1World)
	{
		this(par1World, 0);
	}

	public EntityCloudVillager(World par1World, int par2)
	{
		super(par1World);
		this.randomTickDivider = 0;
		this.isMating = false;
		this.isPlaying = false;
		this.villageObj = null;
		this.setProfession(par2);
		this.texture = "/DSM/Angel2.png";
		this.moveSpeed = 0.5F;
		this.setSize(0.6F, 1.8F);
		this.getNavigator().setBreakDoors(true);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.3F, 0.35F));
		this.tasks.addTask(1, new EntityAITradeWithAngel(this));
		this.tasks.addTask(1, new EntityAIAngelLooksAtPlayer(this));
		this.tasks.addTask(2, new EntityAIMoveIndoors(this));
		this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(5, new EntityAIMoveTwardsRestriction(this, 0.3F));
		this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityCloudVillager.class, 5.0F, 0.02F));
		this.tasks.addTask(9, new EntityAIWander(this, 0.3F));
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
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
		if (--this.randomTickDivider <= 0)
		{
			this.worldObj.villageCollectionObj.addVillagerPosition(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
			this.randomTickDivider = 70 + this.rand.nextInt(50);
			this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);

			if (this.villageObj == null)
			{
				this.detachHome();
			}
			else
			{
				ChunkCoordinates chunkcoordinates = this.villageObj.getCenter();
				this.setHomeArea(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, (int)((float)this.villageObj.getVillageRadius() * 0.6F));

				if (this.field_82190_bM)
				{
					this.field_82190_bM = false;
					this.villageObj.func_82683_b(5);
				}
			}
		}

		if (!this.isTrading() && this.timeUntilReset > 0)
		{
			--this.timeUntilReset;

			if (this.timeUntilReset <= 0)
			{
				if (this.needsInitilization)
				{
					if (this.buyingList.size() > 1)
					{
						Iterator iterator = this.buyingList.iterator();

						while (iterator.hasNext())
						{
							MerchantRecipe merchantrecipe = (MerchantRecipe)iterator.next();

							if (merchantrecipe.func_82784_g())
							{
								merchantrecipe.func_82783_a(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
							}
						}
					}

					this.addDefaultEquipmentAndRecipies(1);
					this.needsInitilization = false;

					if (this.villageObj != null && this.lastBuyingPlayer != null)
					{
						this.worldObj.setEntityState(this, (byte)14);
						this.villageObj.setReputationForPlayer(this.lastBuyingPlayer, 1);
					}
				}

				this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
			}
		}

		super.updateAITick();
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		boolean flag = itemstack != null && itemstack.itemID == Item.monsterPlacer.itemID;

		if (!flag && this.isEntityAlive() && !this.isTrading() && !this.isChild())
		{
			if (!this.worldObj.isRemote)
			{
				this.setCustomer(par1EntityPlayer);
				par1EntityPlayer.displayGUIMerchant(this, this.func_94057_bL());
			}

			return true;
		}
		else
		{
			return super.interact(par1EntityPlayer);
		}
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
	}

	public int getMaxHealth()
	{
		return 20;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("Profession", this.getProfession());
		par1NBTTagCompound.setInteger("Riches", this.wealth);

		if (this.buyingList != null)
		{
			par1NBTTagCompound.setCompoundTag("Offers", this.buyingList.getRecipiesAsTags());
		}
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setProfession(par1NBTTagCompound.getInteger("Profession"));
		this.wealth = par1NBTTagCompound.getInteger("Riches");

		if (par1NBTTagCompound.hasKey("Offers"))
		{
			NBTTagCompound nbttagcompound1 = par1NBTTagCompound.getCompoundTag("Offers");
			this.buyingList = new MerchantRecipeList(nbttagcompound1);
		}
	}

	@SideOnly(Side.CLIENT)

	/**
	 * Returns the texture's file path as a String.
	 */
	public String getTexture()
	{
			return "/DSM/Angel2.png";
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
		return "mob.villager.default";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "mob.villager.defaulthurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "mob.villager.defaultdeath";
	}

	public void setProfession(int par1)
	{
		this.dataWatcher.updateObject(16, Integer.valueOf(par1));
	}

	public int getProfession()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	public boolean isMating()
	{
		return this.isMating;
	}

	public void setMating(boolean par1)
	{
		this.isMating = par1;
	}

	public void setPlaying(boolean par1)
	{
		this.isPlaying = par1;
	}

	public boolean isPlaying()
	{
		return this.isPlaying;
	}

	public void setRevengeTarget(EntityLiving par1EntityLiving)
	{
		super.setRevengeTarget(par1EntityLiving);

		if (this.villageObj != null && par1EntityLiving != null)
		{
			this.villageObj.addOrRenewAgressor(par1EntityLiving);

			if (par1EntityLiving instanceof EntityPlayer)
			{
				byte b0 = -1;

				if (this.isChild())
				{
					b0 = -3;
				}

				this.villageObj.setReputationForPlayer(((EntityPlayer)par1EntityLiving).getCommandSenderName(), b0);

				if (this.isEntityAlive())
				{
					this.worldObj.setEntityState(this, (byte)13);
				}
			}
		}
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	 public void onDeath(DamageSource par1DamageSource)
	{
		if (this.villageObj != null)
		{
			Entity entity = par1DamageSource.getEntity();

			if (entity != null)
			{
				if (entity instanceof EntityPlayer)
				{
					this.villageObj.setReputationForPlayer(((EntityPlayer)entity).getCommandSenderName(), -2);
				}
				else if (entity instanceof IMob)
				{
					this.villageObj.endMatingSeason();
				}
			}
			else if (entity == null)
			{
				EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 16.0D);

				if (entityplayer != null)
				{
					this.villageObj.endMatingSeason();
				}
			}
		}

		super.onDeath(par1DamageSource);
	}

	 public void setCustomer(EntityPlayer par1EntityPlayer)
	 {
		 this.buyingPlayer = par1EntityPlayer;
	 }

	 public EntityPlayer getCustomer()
	 {
		 return this.buyingPlayer;
	 }

	 public boolean isTrading()
	 {
		 return this.buyingPlayer != null;
	 }

	 public void useRecipe(MerchantRecipe par1MerchantRecipe)
	 {
		 par1MerchantRecipe.incrementToolUses();

		 if (par1MerchantRecipe.hasSameIDsAs((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1)))
		 {
			 this.timeUntilReset = 40;
			 this.needsInitilization = true;

			 if (this.buyingPlayer != null)
			 {
				 this.lastBuyingPlayer = this.buyingPlayer.getCommandSenderName();
			 }
			 else
			 {
				 this.lastBuyingPlayer = null;
			 }
		 }

		 if (par1MerchantRecipe.getItemToBuy().itemID == Item.emerald.itemID)
		 {
			 this.wealth += par1MerchantRecipe.getItemToBuy().stackSize;
		 }
	 }

	 public MerchantRecipeList getRecipes(EntityPlayer par1EntityPlayer)
	 {
		 if (this.buyingList == null)
		 {
			 this.addDefaultEquipmentAndRecipies(1);
		 }

		 return this.buyingList;
	 }

	 private float func_82188_j(float par1)
	 {
		 float f1 = par1 + this.field_82191_bN;
		 return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
	 }

	 /**
	  * based on the villagers profession add items, equipment, and recipies adds par1 random items to the list of things
	  * that the villager wants to buy. (at most 1 of each wanted type is added)
	  */
	 private void addDefaultEquipmentAndRecipies(int par1)
	 {
		 if (this.buyingList != null)
		 {
			 this.field_82191_bN = MathHelper.sqrt_float((float)this.buyingList.size()) * 0.2F;
		 }
		 else
		 {
			 this.field_82191_bN = 0.0F;
		 }

		 MerchantRecipeList merchantrecipelist;
		 merchantrecipelist = new MerchantRecipeList();
		 //VillagerRegistry.manageVillagerTrades(merchantrecipelist, this, this.getProfession(), this.rand);
		 int j;


			 switch (this.getProfession())
			 {
			 }

		 if (merchantrecipelist.isEmpty())
		 {
			 addMerchantItem(merchantrecipelist, BaseScytheFile.HolyCrystalSplinter.itemID, this.rand, 0.5F);
			 addMerchantItem(merchantrecipelist, BaseScytheFile.UnholyCrystalSplinter.itemID, this.rand, 0.5F);

			 addBlacksmithItem(merchantrecipelist, BaseScytheFile.ShadyPearl.itemID, this.rand, 0.9F);
			 addBlacksmithItem(merchantrecipelist, BaseScytheFile.HolySoulPearl.itemID, this.rand, 0.5F);
			 addBlacksmithItem(merchantrecipelist, BaseScytheFile.UnholySoulPearl.itemID, this.rand, 0.5F);
			 addBlacksmithItem(merchantrecipelist, BaseScytheFile.HolyCrystal.itemID, this.rand, 0.4F);
			 addBlacksmithItem(merchantrecipelist, BaseScytheFile.UnholyCrystal.itemID, this.rand, 0.4F);          }

		 Collections.shuffle(merchantrecipelist);

		 if (this.buyingList == null)
		 {
			 this.buyingList = new MerchantRecipeList();
		 }

		 for (int j1 = 0; j1 < par1 && j1 < merchantrecipelist.size(); ++j1)
		 {
			 this.buyingList.addToListWithCheck((MerchantRecipe)merchantrecipelist.get(j1));
		 }
	 }

	 @SideOnly(Side.CLIENT)
	 public void setRecipes(MerchantRecipeList par1MerchantRecipeList) {}

	 /**
	  * each recipie takes a random stack from villagerStockList and offers it for 1 emerald
	  */
	 public static void addMerchantItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3)
	 {
		 if (par2Random.nextFloat() < par3)
		 {
			 par0MerchantRecipeList.add(new MerchantRecipe(getRandomSizedStack(par1, par2Random), Item.emerald));
		 }
	 }

	 private static ItemStack getRandomSizedStack(int par0, Random par1Random)
	 {
		 return new ItemStack(par0, getRandomCountForItem(par0, par1Random), 0);
	 }

	 /**
	  * default to 1, and villagerStockList contains a min/max amount for each index
	  */
	 private static int getRandomCountForItem(int par0, Random par1Random)
	 {
		 Tuple tuple = (Tuple)villagerStockList.get(Integer.valueOf(par0));
		 return tuple == null ? 1 : (((Integer)tuple.getFirst()).intValue() >= ((Integer)tuple.getSecond()).intValue() ? ((Integer)tuple.getFirst()).intValue() : ((Integer)tuple.getFirst()).intValue() + par1Random.nextInt(((Integer)tuple.getSecond()).intValue() - ((Integer)tuple.getFirst()).intValue()));
	 }

	 public static void addBlacksmithItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3)
	 {
		 if (par2Random.nextFloat() < par3)
		 {
			 int j = getRandomCountForBlacksmithItem(par1, par2Random);
			 ItemStack itemstack;
			 ItemStack itemstack1;

			 if (j < 0)
			 {
				 itemstack = new ItemStack(Item.emerald.itemID, 1, 0);
				 itemstack1 = new ItemStack(par1, -j, 0);
			 }
			 else
			 {
				 itemstack = new ItemStack(Item.emerald.itemID, j, 0);
				 itemstack1 = new ItemStack(par1, 1, 0);
			 }

			 par0MerchantRecipeList.add(new MerchantRecipe(itemstack, itemstack1));
		 }
	 }

	 private static int getRandomCountForBlacksmithItem(int par0, Random par1Random)
	 {
		 Tuple tuple = (Tuple)blacksmithSellingList.get(Integer.valueOf(par0));
		 return tuple == null ? 1 : (((Integer)tuple.getFirst()).intValue() >= ((Integer)tuple.getSecond()).intValue() ? ((Integer)tuple.getFirst()).intValue() : ((Integer)tuple.getFirst()).intValue() + par1Random.nextInt(((Integer)tuple.getSecond()).intValue() - ((Integer)tuple.getFirst()).intValue()));
	 }

	 @SideOnly(Side.CLIENT)
	 public void handleHealthUpdate(byte par1)
	 {
		 if (par1 == 12)
		 {
			 this.generateRandomParticles("heart");
		 }
		 else if (par1 == 13)
		 {
			 this.generateRandomParticles("angryVillager");
		 }
		 else if (par1 == 14)
		 {
			 this.generateRandomParticles("happyVillager");
		 }
		 else
		 {
			 super.handleHealthUpdate(par1);
		 }
	 }

	 @SideOnly(Side.CLIENT)

	 /**
	  * par1 is the particleName
	  */
	 private void generateRandomParticles(String par1Str)
	 {
		 for (int i = 0; i < 5; ++i)
		 {
			 double d0 = this.rand.nextGaussian() * 0.02D;
			 double d1 = this.rand.nextGaussian() * 0.02D;
			 double d2 = this.rand.nextGaussian() * 0.02D;
			 this.worldObj.spawnParticle(par1Str, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 1.0D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
		 }
	 }

	 //    /**
	 //     * Initialize this creature.
	 //     */
	 //    public void initCreature()
	 //    {
	 //        VillagerRegistry.applyRandomTrade(this, worldObj.rand);
	 //    }

	 public void func_82187_q()
	 {
		 this.field_82190_bM = true;
	 }

	 public EntityCloudVillager func_90012_b(EntityAgeable par1EntityAgeable)
	 {
		 EntityCloudVillager entityvillager = new EntityCloudVillager(this.worldObj);
		 entityvillager.initCreature();
		 return entityvillager;
	 }

	 public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	 {
		 return this.func_90012_b(par1EntityAgeable);
	 }

	 static
	 {
		 villagerStockList.put(Integer.valueOf(Item.coal.itemID), new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
		 villagerStockList.put(Integer.valueOf(Item.ingotIron.itemID), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
		 villagerStockList.put(Integer.valueOf(Item.ingotGold.itemID), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
		 villagerStockList.put(Integer.valueOf(Item.diamond.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		 villagerStockList.put(Integer.valueOf(Item.paper.itemID), new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
		 villagerStockList.put(Integer.valueOf(Item.book.itemID), new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
		 villagerStockList.put(Integer.valueOf(Item.writtenBook.itemID), new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
		 villagerStockList.put(Integer.valueOf(Item.enderPearl.itemID), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		 villagerStockList.put(Integer.valueOf(Item.eyeOfEnder.itemID), new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
		 villagerStockList.put(Integer.valueOf(Item.porkRaw.itemID), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
		 villagerStockList.put(Integer.valueOf(Item.beefRaw.itemID), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
		 villagerStockList.put(Integer.valueOf(Item.chickenRaw.itemID), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
		 villagerStockList.put(Integer.valueOf(Item.fishCooked.itemID), new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
		 villagerStockList.put(Integer.valueOf(Item.seeds.itemID), new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
		 villagerStockList.put(Integer.valueOf(Item.melonSeeds.itemID), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
		 villagerStockList.put(Integer.valueOf(Item.pumpkinSeeds.itemID), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
		 villagerStockList.put(Integer.valueOf(Item.wheat.itemID), new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
		 villagerStockList.put(Integer.valueOf(Block.cloth.blockID), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
		 villagerStockList.put(Integer.valueOf(Item.rottenFlesh.itemID), new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
		 blacksmithSellingList.put(Integer.valueOf(Item.flintAndSteel.itemID), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		 blacksmithSellingList.put(Integer.valueOf(Item.shears.itemID), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		 blacksmithSellingList.put(Integer.valueOf(Item.swordIron.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
		 blacksmithSellingList.put(Integer.valueOf(Item.swordDiamond.itemID), new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
		 blacksmithSellingList.put(Integer.valueOf(Item.axeIron.itemID), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
		 blacksmithSellingList.put(Integer.valueOf(Item.axeDiamond.itemID), new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
		 blacksmithSellingList.put(Integer.valueOf(Item.pickaxeIron.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
		 blacksmithSellingList.put(Integer.valueOf(Item.pickaxeDiamond.itemID), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
		 blacksmithSellingList.put(Integer.valueOf(Item.shovelIron.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		 blacksmithSellingList.put(Integer.valueOf(Item.shovelDiamond.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
		 blacksmithSellingList.put(Integer.valueOf(Item.hoeIron.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		 blacksmithSellingList.put(Integer.valueOf(Item.hoeDiamond.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
		 blacksmithSellingList.put(Integer.valueOf(Item.bootsIron.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		 blacksmithSellingList.put(Integer.valueOf(Item.bootsDiamond.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
		 blacksmithSellingList.put(Integer.valueOf(Item.helmetIron.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		 blacksmithSellingList.put(Integer.valueOf(Item.helmetDiamond.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
		 blacksmithSellingList.put(Integer.valueOf(Item.plateIron.itemID), new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
		 blacksmithSellingList.put(Integer.valueOf(Item.plateDiamond.itemID), new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
		 blacksmithSellingList.put(Integer.valueOf(Item.legsIron.itemID), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
		 blacksmithSellingList.put(Integer.valueOf(Item.legsDiamond.itemID), new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
		 blacksmithSellingList.put(Integer.valueOf(Item.bootsChain.itemID), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
		 blacksmithSellingList.put(Integer.valueOf(Item.helmetChain.itemID), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
		 blacksmithSellingList.put(Integer.valueOf(Item.plateChain.itemID), new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
		 blacksmithSellingList.put(Integer.valueOf(Item.legsChain.itemID), new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
		 blacksmithSellingList.put(Integer.valueOf(Item.bread.itemID), new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
		 blacksmithSellingList.put(Integer.valueOf(Item.melon.itemID), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
		 blacksmithSellingList.put(Integer.valueOf(Item.appleRed.itemID), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
		 blacksmithSellingList.put(Integer.valueOf(Item.cookie.itemID), new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
		 blacksmithSellingList.put(Integer.valueOf(Block.glass.blockID), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
		 blacksmithSellingList.put(Integer.valueOf(Block.bookShelf.blockID), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		 blacksmithSellingList.put(Integer.valueOf(Item.plateLeather.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
		 blacksmithSellingList.put(Integer.valueOf(Item.bootsLeather.itemID), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
		 blacksmithSellingList.put(Integer.valueOf(Item.helmetLeather.itemID), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
		 blacksmithSellingList.put(Integer.valueOf(Item.legsLeather.itemID), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
		 blacksmithSellingList.put(Integer.valueOf(Item.saddle.itemID), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
		 blacksmithSellingList.put(Integer.valueOf(Item.expBottle.itemID), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
		 blacksmithSellingList.put(Integer.valueOf(Item.redstone.itemID), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
		 blacksmithSellingList.put(Integer.valueOf(Item.compass.itemID), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
		 blacksmithSellingList.put(Integer.valueOf(Item.pocketSundial.itemID), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
		 blacksmithSellingList.put(Integer.valueOf(Block.glowStone.blockID), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
		 blacksmithSellingList.put(Integer.valueOf(Item.porkCooked.itemID), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
		 blacksmithSellingList.put(Integer.valueOf(Item.beefCooked.itemID), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
		 blacksmithSellingList.put(Integer.valueOf(Item.chickenCooked.itemID), new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
		 blacksmithSellingList.put(Integer.valueOf(Item.eyeOfEnder.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
		 blacksmithSellingList.put(Integer.valueOf(Item.arrow.itemID), new Tuple(Integer.valueOf(-12), Integer.valueOf(-8)));
	 }
}
