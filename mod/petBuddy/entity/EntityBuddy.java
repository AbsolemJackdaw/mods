package petBuddy.entity;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import petBuddy.PetBuddyMain;
import petBuddy.entity.model.Bat;
import petBuddy.entity.model.Chicken;
import petBuddy.entity.model.IronGolem;
import petBuddy.entity.model.ModelSkellington;
import petBuddy.entity.model.ModelSpiderRpg;
import petBuddy.entity.model.Ocelot;
import petBuddy.entity.model.SheepFleece;
import petBuddy.entity.model.SilverFish;
import petBuddy.entity.model.Squid;
import petBuddy.entity.model.Wolf;
import petBuddy.root.BuddyBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBuddy extends BuddyBase
{

	private float randomColor ;
	private float randomColor2;
	private float randomColor3;

	private boolean hasItem = false;
	private int findsItemTimer;
	private Object[][] foundItems;
	private int[][] foundItemsStackSize;

	private HashMap<String, String> itemInventory = new HashMap();
	private int harvestsWithHeldItem;

	public EntityBuddy(World par1World)
	{
		super(par1World);
		this.findsItemTimer = this.rand.nextInt(6000) + 9000;
		//i hope to prevent any buddies going lost in portals.
		this.timeUntilPortal = 6000;

		this.harvestsWithHeldItem = 12000;

	}

	public EntityBuddy(World par1World, EntityPlayer player)
	{
		super(par1World, player);
		this.findsItemTimer = this.rand.nextInt(6000) + 9000;
		this.timeUntilPortal = 6000;

		this.harvestsWithHeldItem = 12000;
	}

	@Override
	public float getMountedOffset(){
		switch(getGuiId()){
		case 2:
			return 2.8f;
		case 4:
			return 3f;
		case 5:
			return 3f;
		case 8:
			return 3.5f;
		case 9:
			return 2.8f;
		case 11:
			return 2.8f;
		case 14:
			return 2.8f;
		case 15:
			return 4.2f;
		case 16:
			return 2.2f;
		case 17:
			return 2.7f;
		case 22:
			return 3f;
		case 23:
			return 2.6f;
		case 26:
			return 2.4f;
		default:
			return 3.2f;
		}
	}

	@SideOnly(Side.CLIENT)
	public ModelBase getModel(){
		switch(getGuiId()){
		case 2:
			return new ModelPig();
		case 4:
			return new ModelCreeper();
		case 5:
			return new ModelCow();
		case 6:
			return new ModelBlaze();
		case 7:
			return new ModelSpider();
		case 9:
			return new ModelSpiderRpg();
		case 10:
			return new ModelSkellington();
		case 11:
			return new ModelSkellington();
		case 12:
			return new ModelZombie();
		case 13:
			return new ModelGhast();
		case 14:
			return new SheepFleece();
		case 15:
			return new ModelEnderman();
		case 16:
			return new SilverFish();
		case 17:
			return new ModelSnowMan();
		case 18:
			return new IronGolem();
		case 20:
			return new Bat();
		case 21:
			return new Chicken();
		case 22:
			return new ModelCow();
		case 23:
			return new Ocelot();
		case 24:
			return new Squid();
		case 25:
			return new ModelVillager(0.0f);
		case 26:
			return new Wolf();
		case 27:
			return new ModelZombie();
		default :
			return new ModelBiped();
		}
	}

	@Override
	public String getTexture() {
		switch(getGuiId()){
		case 2:
			return "/mob/pig.png";
		case 3:
			return "/mob/char.png";
		case 4:
			return "/mob/creeper.png";
		case 5:
			return "/mob/cow.png";
		case 6:
			return "/mob/fire.png";
		case 7:
			return "/mob/spider.png";
		case 9:
			return "/subaraki/mobs/spider.png";
		case 10:
			return "/mob/skeleton.png";
		case 11:
			return "/mob/skeleton_wither.png";
		case 12:
			return "/mob/zombie_villager.png";
		case 13:
			return "/mob/ghast.png";
		case 14:
			return "/mob/sheep.png";
		case 15:
			return "/mob/enderman.png";
		case 16:
			return "/mob/silverfish.png";
		case 17:
			return "/mob/snowman.png";
		case 18:
			return "/mob/villager_golem.png";
		case 20:
			return "/mob/bat.png";
		case 21:
			return "/mob/chicken.png";
		case 22:
			return "/mob/redcow.png";
		case 23:
			return "/mob/ozelot.png";
		case 24:
			return "/mob/squid.png";
		case 25:
			return "/mob/villager/smith.png";
		case 26:
			return "/mob/wolf.png";
		case 27:
			return "/mob/pigzombie.png";
		default :
			return "/mob/char.png";
		}
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	public int getGuiId(){
		return PetBuddyMain.proxy.getGuiId();
	}

	@Override
	public void onLivingUpdate() {
		if(!this.isRiding() && !this.worldObj.isRemote && this.findsItemTimer >=0){		        
			findsItemTimer --;
		}else{
			getEntityData().setInteger("itemTimer", findsItemTimer);
		}

		if(findsItemTimer == 0){
			hasItem = true;
			if(!worldObj.isRemote){
				buddySpeak(getOwner(), "Hey, I think I found something !");
			}
		}

		if(!PetBuddyMain.playersWithPets.containsValue(this.entityId)){
			this.setDead();
		}
		super.onLivingUpdate();
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer player)
	{
		if(player.getCurrentEquippedItem() != null){
			if(player.getCurrentEquippedItem().getItem() instanceof ItemDye){
				float fl= 1.0f;
				float flo= 1.0f;
				float fla= 1.0f;

				fl = rand.nextFloat();
				flo = rand.nextFloat();
				fla = rand.nextFloat();
				PetBuddyMain.proxy.setColor(fl,flo,fla);
			}
		}


		if(player.inventory.getCurrentItem() != null){
			if(player.inventory.getCurrentItem().getItem().equals(Item.stick)){
				PetBuddyMain.proxy.openGui(0, player, player.username, this.entityId);
			}
		} 
		else if (!this.worldObj.isRemote && this.ridingEntity == null && hasItem == false){
			this.mountEntity(player);
			this.ridingEntity = player;
		}
		else if(!this.worldObj.isRemote && this.ridingEntity == player && hasItem == false){
			this.unmountEntity(player);
			this.ridingEntity = null;
		}
		if(player.inventory.getCurrentItem() == null && hasItem == true){
			giveOwnerRandomItem(player);
		}

		return super.interact(player);
	}

	public float getColor(){
		return PetBuddyMain.proxy.getColor() ;
	}public float getColor2(){
		return PetBuddyMain.proxy.getColor2() ;
	}public float getColor3(){
		return PetBuddyMain.proxy.getColor3();
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	 */
	public EntityBuddy spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return null;
	}

	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}

	public String getName(){
		return PetBuddyMain.proxy.getName();
	}

	/**Gives the given player a random itemstack in his inventory. Does not have a weighted randomness.*/
	public void giveOwnerRandomItem(EntityPlayer player){

		foundItems = new Object[][] {{Item.feather, Item.appleRed, Item.flint, Item.cake, Item.diamond, Item.coal, Item.coal, Item.pickaxeIron, Item.wheat, Item.arrow, Item.goldNugget} ,
				{Block.wood, Block.planks, Block.grass, Block.oreIron, Block.torchWood}};

		foundItemsStackSize = new int[][] {{s(20), s(5), s(64), 1, s(3), s(5), s(10), 1, s(10), s(10), s(10)},
				{s(20), s(20), s(5), s(4), s(64)}};

		int count = rand.nextInt(foundItems.length);

		if(count > foundItems[0].length){
			player.inventory.addItemStackToInventory(new ItemStack((Block)foundItems[1][foundItems.length-count], foundItemsStackSize[1][foundItems.length-count]));
			this.buddySpeak(getOwner(), "I found "+ foundItemsStackSize[1][foundItems.length-count] + " " +
					((Block)foundItems[1][foundItems.length-count]).getLocalizedName());

		}else{
			player.inventory.addItemStackToInventory(new ItemStack((Item)foundItems[0][count], foundItemsStackSize[0][count]));
			this.buddySpeak(getOwner(), "I found "+ foundItemsStackSize[0][count] +" "+
					((Item)foundItems[0][count]).getItemDisplayName(new ItemStack((Item)foundItems[0][count])));

		}

		hasItem = false;
		findsItemTimer = 6000 + rand.nextInt(9000);
	}

	public void buddySpeak(EntityLiving player , String text){
		if(player instanceof EntityPlayer){
			((EntityPlayer)getOwner()).sendChatToPlayer("<"+getName()+">" + text);
		}
	}

	/* this is mearely to save space. that's how I role. YOLO !! XD*/
	/**Picks a random integer, and adds 1 to the result, so we never have 0.*/
	public int s (int i){
		return rand.nextInt(i)+1;
	}
}
