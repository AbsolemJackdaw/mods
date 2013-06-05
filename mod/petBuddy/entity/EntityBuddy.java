package petBuddy.entity;

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
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
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

	public float randomColor = 1.0f;
	public float randomColor2 = 1.0f;
	public float randomColor3 = 1.0f;
	int sheepColor = rand.nextInt(5);

	int catTextures = rand.nextInt(4);
	int spiderTextures = rand.nextInt(2);
	int villagerTextures = rand.nextInt(6);
	int zombieModelChoice = rand.nextInt(2);
	String cat;
	String spider;
	String villager;
	
	public EntityBuddy(World par1World)
	{
		super(par1World);	
		if(sheepColor <4){
			randomColor = rand.nextFloat();
			randomColor2 = rand.nextFloat();
			randomColor3 = rand.nextFloat();
		}
		cat = catTextures == 0 ? "ozelot" : catTextures == 1 ? "cat_black" : catTextures == 2 ? "cat_siamese" : "cat_red";
		spider = spiderTextures == 0 ? "spider" : "cavespider";
		villager = villagerTextures == 0 ? "smith" : villagerTextures == 1 ? "librarian" :villagerTextures == 2 ? 
				"farmer" :villagerTextures == 3 ? "priest" : villagerTextures == 4 ? "witch" : villagerTextures == 5 ? "butcher" : "villager";
	}

	public EntityBuddy(World par1World, EntityPlayer player)
	{
		super(par1World, player);	
		if(sheepColor <4){
			randomColor = rand.nextFloat();
			randomColor2 = rand.nextFloat();
			randomColor3 = rand.nextFloat();
		}
		cat = catTextures == 0 ? "ozelot" : catTextures == 1 ? "cat_black" : catTextures == 2 ? "cat_siamese" : "cat_red";
		spider = spiderTextures == 0 ? "spider" : "cavespider";
		villager = villagerTextures == 0 ? "smith" : villagerTextures == 1 ? "librarian" :villagerTextures == 2 ? 
				"farmer" :villagerTextures == 3 ? "priest" : villagerTextures == 4 ? "witch" : villagerTextures == 5 ? "butcher" : "villager";
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
			return zombieModelChoice == 0 ? new ModelZombieVillager() : new ModelZombie();
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
			return villagerTextures ==  4 ? new ModelWitch(0.0f) : new ModelVillager(0.0f);
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
			return "/mob/"+ spider +".png";
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
//		case 19:
//			return "/mob/witch.png";
		case 20:
			return "/mob/bat.png";
		case 21:
			return "/mob/chicken.png";
		case 22:
			return "/mob/redcow.png";
		case 23:
			return "/mob/"+cat+".png";
		case 24:
			return "/mob/squid.png";
		case 25:
			return "/mob/villager/"+ villager+ ".png";
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
		par1NBTTagCompound.setString("catTexture", cat);
		par1NBTTagCompound.setString("vilTexture", villager);
		par1NBTTagCompound.setString("spiTexture", spider);
		
		par1NBTTagCompound.setInteger("catInt", catTextures);
		par1NBTTagCompound.setInteger("vilInt", villagerTextures);
		par1NBTTagCompound.setInteger("spiInt", spiderTextures);
		par1NBTTagCompound.setInteger("zombieInt", zombieModelChoice);
		
		par1NBTTagCompound.setFloat("firstRandomColor", randomColor);
		par1NBTTagCompound.setFloat("seconRandomColor", randomColor2);
		par1NBTTagCompound.setFloat("thirdRandomColor", randomColor3);

	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		randomColor = par1NBTTagCompound.getFloat("firstRandomColor");
		randomColor2 = par1NBTTagCompound.getFloat("seconRandomColor");
		randomColor3 = par1NBTTagCompound.getFloat("thirdRandomColor");

		zombieModelChoice = par1NBTTagCompound.getInteger("zombieInt");
		catTextures = par1NBTTagCompound.getInteger("catInt");
		villagerTextures = par1NBTTagCompound.getInteger("vilInt");
		spiderTextures = par1NBTTagCompound.getInteger("spiInt");

		cat = par1NBTTagCompound.getString("catTexture");
		villager = par1NBTTagCompound.getString("vilTexture");
		spider = par1NBTTagCompound.getString("spiTexture");

	}

	private int getGuiId(){
		return PetBuddyMain.proxy.getGuiId();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return super.interact(par1EntityPlayer);
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
}
