package petBuddy.entity;

import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import petBuddy.PetBuddyMain;
import petBuddy.entity.model.Bat;
import petBuddy.entity.model.Boar;
import petBuddy.entity.model.Bull;
import petBuddy.entity.model.Chicken;
import petBuddy.entity.model.DragonsModel;
import petBuddy.entity.model.Harpy;
import petBuddy.entity.model.IronGolem;
import petBuddy.entity.model.ModelMagmaCube;
import petBuddy.entity.model.ModelSkellington;
import petBuddy.entity.model.ModelSpiderRpg;
import petBuddy.entity.model.Ocelot;
import petBuddy.entity.model.SheepFleece;
import petBuddy.entity.model.SilverFish;
import petBuddy.entity.model.Squid;
import petBuddy.entity.model.WitherModel;
import petBuddy.entity.model.Wolf;
import petBuddy.root.BuddyBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBuddy extends BuddyBase
{
	private float happynessFactorCooldown;

	private int findsItemTimer;
	private Object[][] foundItems;
	private int[][] foundItemsStackSize;

	private HashMap<String, String> itemInventory = new HashMap();
	private int harvestsWithHeldItem;

	public static DynamicTexture buddyTexture;

	public EntityBuddy(World par1World)
	{
		super(par1World);
		this.findsItemTimer = this.rand.nextInt(12000) + 12000;
		//i hope to prevent any buddies going lost in portals.
		this.timeUntilPortal = 6000;

		this.harvestsWithHeldItem = 12000;

		happynessFactorCooldown = rand.nextInt(600) + 800;

	}

	public EntityBuddy(World par1World, EntityPlayer player)
	{
		super(par1World, player);
		this.findsItemTimer = this.rand.nextInt(12000) + 12000;
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
		case 30:
			return 1f;
		case 31:
			return 1f;
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
		case 8:
			return new WitherModel();
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
		case 19:
			return new DragonsModel(0.0f);
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
		case 28:
			return new Bull();
		case 29:
			return new Boar();
		case 30:
			return new ModelMagmaCube();
		case 31:
			return new ModelSlime(16); // model from petbuddy, not from vanilla
		case 32:
			return new Harpy();
		default :
			return new ModelBiped();
		}
	}

	@Override
	public ResourceLocation getTexture(){
		String s = "textures/entity";

		switch(getGuiId()){
		case 2:
			return new ResourceLocation(s+"/pig/pig.png");
		case 3://you
			return new ResourceLocation(s+"/steve.png");
		case 4://Creeper
			return new ResourceLocation(s+"/creeper/creeper.png");
		case 5://cow
			return new ResourceLocation( s+"/cow/cow.png");
		case 6://fire>blaze
			return new ResourceLocation( s+"/blaze.png");
		case 7://spider
			return new ResourceLocation( s+"/spider/spider.png");
		case 8://wither
			return new ResourceLocation( s+"/wither/wither.png");
		case 9://rpg spider
			return new ResourceLocation( "subaraki","mobs/spider.png");
		case 10://skeleton
			return new ResourceLocation( s+"/skeleton/skeleton.png");
		case 11://wither skeleton
			return new ResourceLocation( s+"/skeleton/skeleton_wither.png");
		case 12://zombie villager
			return new ResourceLocation(s+ "/zombie/zombie_villager.png");
		case 13://ghast
			return new ResourceLocation( s+"/ghast/ghast.png");
		case 14://sheep
			return new ResourceLocation( s+"/sheep/sheep.png");
		case 15://enderman
			return new ResourceLocation( s+"/enderman/enderman.png");
		case 16://silverfish
			return new ResourceLocation( s+"/silverfish.png");
		case 17://snowman
			return new ResourceLocation( s+"/snowman.png");
		case 18://golem iron
			return new ResourceLocation( s+"/iron_golem.png");
		case 19://ender D
			return new ResourceLocation( s+"/enderdragon/dragon.png");
		case 20://bat
			return new ResourceLocation( s+"/bat.png");
		case 21://chicken
			return new ResourceLocation( s+"/chicken.png");
		case 22://redcow
			return new ResourceLocation( s+"/cow/mooshroom.png");
		case 23://ozelot
			return new ResourceLocation( s+"/cat/ocelot.png");
		case 24://squid
			return new ResourceLocation( s+"/squid.png");
		case 25://villager smith
			return new ResourceLocation( s+"/villager/smith.png");
		case 26://wolf
			return new ResourceLocation( s+"/wolf/wolf.png");
		case 27://pigzombie
			return new ResourceLocation( s+"/zombie_pigman.png");
		case 28://rpg bull 
			return new ResourceLocation( "subaraki","mobs/bull.png");
		case 29://rpg boar
			return new ResourceLocation( "subaraki","mobs/boar.png");
		case 30://lava slime
			return new ResourceLocation( s+"/slime/magmacube.png");
		case 31://slime
			return new ResourceLocation( s+"/slime/slime.png");
		case 32://rpg bull 
			return new ResourceLocation( "subaraki","mobs/harpy.png");
		default ://Default steve.png
			return new ResourceLocation( s+"/ghast/ghast.png");
		}

	}

	protected String getLivingSound()
	{
		switch(getGuiId()){
		case 2:
			return "mob.pig.say";
		case 4:
			return "mob.creeper.say";
		case 5:
			return "mob.cow.say";
		case 6:
			return "mob.blaze.breathe";
		case 7:
			return "mob.spider.say";
		case 8:
			return "mob.wither.idle";
		case 9:
			return "mob.spider.say";
		case 10:
			return "mob.skeleton.say";
		case 11:
			return "mob.skeleton.say";
		case 12:
			return "mob.zombie.say";
		case 13:
			return "mob.ghast.moan";
		case 14:
			return "mob.sheep.say";
		case 15:
			return "mob.endermen.idle";
		case 16:
			return "mob.silverfish.say";
		case 17:
			return "/mob/snowman.png";
		case 19:
			return "mob.enderdragon.growl";
		case 20:
			return "mob.bat.idle";
		case 21:
			return "mob.chicken.say";
		case 22:
			return "mob.cow.say";
		case 23:
			int x =rand.nextInt(4);
			return x == 0 ? "mob.cat.purreow" : x == 1 ? "mob.cat.meow" : "mob.cat.purr";
		case 26:
			return "mob.wolf.bark";
		case 27:
			return "mob.zombiepig.zpig";
		case 28:
			return "mob.cow.say";
		case 29:
			return "mob.pig.say";
		case 30:
			return "mob.magmacube.big";
		case 31:
			return "mob.slime.big";
		default :
			return null;
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
		if(!(this.ridingEntity instanceof EntityPlayer) && !this.worldObj.isRemote && this.findsItemTimer >=0){		        
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
		if(findsItemTimer + rand.nextInt(6000)  == 5000 && this.ridingEntity == null){
			if(!worldObj.isRemote){
				buddySpeak(getOwner(), "I'm tired... I want to sit on your head...");
			}
		}if(findsItemTimer + rand.nextInt(10000)  == 12000){
			if(!worldObj.isRemote){
				buddySpeak(getOwner(), "I feel a strong band between you and me " + getOwnerName() + ". Don't you ?");
			}
		}
		if(this.isBurning()){
			extinguish();
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
		player.addStat(PetBuddyMain.findBuddy, 1);

		if(player.getCurrentEquippedItem() != null){
			//adds happiness to pet.
			if(player.getCurrentEquippedItem().getItem() instanceof ItemFood){
				ItemFood food = (ItemFood) player.getCurrentEquippedItem().getItem();
				float f = (float) 0.4f/food.getHealAmount(); // 0.4/8 = 0.05; beaf should add 0.05 happiness
				//				this.HappynessFactor += f;
			}

			//colors the pet with 3 types of dye.
			if(player.getCurrentEquippedItem().getItem() instanceof ItemDye){
				ItemStack item = player.getCurrentEquippedItem();

				if(getGuiId() == 14){
					float fl= PetBuddyMain.proxy.getColor();
					float flo= PetBuddyMain.proxy.getColor2();
					float fla= PetBuddyMain.proxy.getColor3();
					if(item.getItemDamage() == 0){
						fl -= 0.05; flo -= 0.05; fla-= 0.05;
					}else if(item.getItemDamage() == 1){
						fl += 0.05; flo -= 0.05; fla-=0.05;
					}else if(item.getItemDamage() == 2){
						fl -= 0.05; flo += 0.05; fla-=0.05;
					}else if(item.getItemDamage() == 4){
						fl -= 0.05; flo -= 0.05; fla+=0.05;
					}else if(item.getItemDamage() == 15){
						fl += 0.05; flo += 0.05; fla+=0.05;
					}else{
						fl = rand.nextFloat();
						flo = rand.nextFloat();
						fla = rand.nextFloat();
					}
					if(fl < 0) fl =0;if (flo < 0) flo =0; if(fla < 0) fla =0;
					if(fl > 1) fl =1;if (flo > 1) flo =1; if(fla > 1) fla =1;

					PetBuddyMain.proxy.setColor(fl,flo,fla);
					item.stackSize--;
				}
				if(getGuiId() == 19 ){
					float fl= PetBuddyMain.proxy.getDragonColor();
					float flo= PetBuddyMain.proxy.getDragonColor2();
					float fla= PetBuddyMain.proxy.getDragonColor3();
					if(item.getItemDamage() == 0){
						fl -= 0.05; flo -= 0.05; fla-= 0.05;
					}else if(item.getItemDamage() == 1){
						fl += 0.05; flo -= 0.05; fla-=0.05;
					}else if(item.getItemDamage() == 2){
						fl -= 0.05; flo += 0.05; fla-=0.05;
					}else if(item.getItemDamage() == 4){
						fl -= 0.05; flo -= 0.05; fla+=0.05;
					}else if(item.getItemDamage() == 15){
						fl += 0.05; flo += 0.05; fla+=0.05;
					}else{
						fl = rand.nextFloat();
						flo = rand.nextFloat();
						fla = rand.nextFloat();
					}
					if(fl < 0) fl =0;if (flo < 0) flo =0; if(fla < 0) fla =0;
					if(fl > 1) fl =1;if (flo > 1) flo =1; if(fla > 1) fla =1;

					PetBuddyMain.proxy.setDragonColor(fl,flo,fla);
					item.stackSize--;
				}
			}
		}

		if(player.inventory.getCurrentItem() != null){
			if(player.inventory.getCurrentItem().getItem().equals(Item.stick)||
					player.inventory.getCurrentItem().getItem().equals(Item.leather) && !player.capabilities.isCreativeMode){
				PetBuddyMain.proxy.openGui(0, player, player.username, this.entityId, player.capabilities.isCreativeMode,player.inventory.getCurrentItem().getItem());
			}
		} 
		else if (!this.worldObj.isRemote && this.ridingEntity == null && hasItem == false){
			this.mountEntity(player);
			this.ridingEntity = player;
		}
		else if(!this.worldObj.isRemote && this.ridingEntity == player && hasItem == false){
			this.mountEntity(player);
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

	public float getDragonColor(){
		return PetBuddyMain.proxy.getDragonColor() ;
	}public float getDragonColor2(){
		return PetBuddyMain.proxy.getDragonColor2() ;
	}public float getDragonColor3(){
		return PetBuddyMain.proxy.getDragonColor3();
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
		findsItemTimer =  this.rand.nextInt(12000) + 12000;
	}

	public void buddySpeak(Entity entity , String text){
		if(entity instanceof EntityPlayer){
			((EntityPlayer)getOwner()).addChatMessage("<"+getName()+">" + text);
		}
	}

	/* this is mearely to save space. that's how I role. YOLO !! XD*/
	/**Picks a random integer, and adds 1 to the result, so we never have 0.*/
	public int s (int i){
		return rand.nextInt(i)+1;
	}

}
