package petBuddy.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import petBuddy.PetBuddyMain;
import petBuddy.entity.model.Bat;
import petBuddy.entity.model.Boar;
import petBuddy.entity.model.Bull;
import petBuddy.entity.model.Chicken;
import petBuddy.entity.model.DragonsModel;
import petBuddy.entity.model.Harpy;
import petBuddy.entity.model.IronGolem;
import petBuddy.entity.model.Moa;
import petBuddy.entity.model.ModelDwarfMale;
import petBuddy.entity.model.ModelElfMale;
import petBuddy.entity.model.ModelMagmaCube;
import petBuddy.entity.model.ModelOrcMale;
import petBuddy.entity.model.ModelSkellington;
import petBuddy.entity.model.ModelSpiderRpg;
import petBuddy.entity.model.Ocelot;
import petBuddy.entity.model.SheepFleece;
import petBuddy.entity.model.SilverFish;
import petBuddy.entity.model.Squid;
import petBuddy.entity.model.WitherModel;
import petBuddy.entity.model.Wolf;
import petBuddy.root.BuddyBase;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBuddy extends BuddyBase
{
	private float happynessFactorCooldown;

	/*Used for randomized entity textures*/
	private int rand2Text ;
	private int rand3Text ;
	private int rand4Text ;
	private int rand5Text ;

	ItemStack []pickedupItems = new ItemStack[50];

	public EntityBuddy(World par1World)
	{
		super(par1World);

		//i hope to prevent any buddies going lost in portals.
		this.timeUntilPortal = 6000;

		happynessFactorCooldown = rand.nextInt(600) + 800;
		rand2Text = rand.nextInt(1);
		rand3Text = rand.nextInt(2);
		rand4Text = rand.nextInt(3);
		rand5Text = rand.nextInt(5);

	}

	public EntityBuddy(World par1World, EntityPlayer player)
	{
		super(par1World, player);

		this.timeUntilPortal = 6000;

		rand2Text = rand.nextInt(1);
		rand3Text = rand.nextInt(2);
		rand4Text = rand.nextInt(3);
		rand5Text = rand.nextInt(5);

	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.pickedupItems.length; ++i)
		{
			if (this.pickedupItems[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				pickedupItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.pickedupItems =  new ItemStack[50];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			this.pickedupItems[i]=ItemStack.loadItemStackFromNBT(nbttagcompound1);

		}
	}

	int sayItems = 600;
	int delay = 10;
	@Override
	public void onLivingUpdate() {

		sayItems--;
		if(sayItems == 0){
			sayItems = 600;
			if(pickedupItems.length > 0){
				this.buddySpeak(getOwner(), "I've got some picked up items master");
			}
		}

		List<EntityItem> xps = worldObj.getEntitiesWithinAABB(EntityItem.class, boundingBox.copy().expand(0.5D, 0.5D, 0.5D));
		if (xps != null && xps.size() > 0) {
			if(-- delay <= 0){
				for (EntityItem xp : xps) {
					delay = 10;
					for(int i =0; i < 50; i++){
						if(pickedupItems[i] == null){
							pickedupItems[i] = xp.getEntityItem();
							xp.setDead();
							break;
						}
					}if(sayItems % 10 == 0){
						buddySpeak(getOwner(), "I've picked up items master");
					}
				}
			}
		}

		if(this.isBurning()){
			extinguish();
		}
		if(!PetBuddyMain.playersWithPets.containsValue(this.entityId) && !worldObj.isRemote){
			this.setDead();
		}
		super.onLivingUpdate();
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer player)
	{
		player.addStat(PetBuddyMain.pa.findBuddy, 1);

		if(player.getCurrentEquippedItem() != null){

			//colors the pet with 3 types of dye.
			if(player.getCurrentEquippedItem().getItem() instanceof ItemDye){
				ItemStack item = player.getCurrentEquippedItem();

				if(getGuiId() == 14){
					float fl= getColor();
					float flo= getColor2();
					float fla= getColor3();
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

					setColor(fl,flo,fla);
					item.stackSize--;
				}
				if(getGuiId() == 19 ){
					float fl= getDragonColor();
					float flo= getDragonColor2();
					float fla= getDragonColor3();
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

					setDragonColor(fl,flo,fla);
					item.stackSize--;
				}
			}
		}

		if(player.inventory.getCurrentItem() != null){

			if(player.inventory.getCurrentItem().getItem().equals(Item.stick) && !player.isSneaking()||
					player.inventory.getCurrentItem().getItem().equals(Item.leather) && !player.capabilities.isCreativeMode){
				PetBuddyMain.proxy.openGui(0, player, player.username, this.entityId, player.capabilities.isCreativeMode,player.inventory.getCurrentItem().getItem());
			}
			if(player.inventory.getCurrentItem().getItem().equals(Item.silk)){
				if (!this.worldObj.isRemote && hasItem == false){
					if( this.ridingEntity == null ){
						this.mountEntity(player);
						this.ridingEntity = player;
					}else{
						this.mountEntity(player);
						this.ridingEntity = null;
					}
				}
			}
		} 

		if(player.inventory.getCurrentItem() == null ){

			if(pickedupItems.length >0){
				for(int c = 0; c < pickedupItems.length; c++){
					if(pickedupItems[c] != null){

						EntityItem item = new EntityItem(worldObj, getOwner().posX, getOwner().posY, getOwner().posZ,pickedupItems[c]);
						if(!worldObj.isRemote){
							worldObj.spawnEntityInWorld(item);
							FMLLog.getLogger().info(pickedupItems[c] + "");
						}
						pickedupItems[c] = null;
					}
				}
			}
		}
		return super.interact(player);
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

	public void buddySpeak(Entity entity , String text){
		if(entity instanceof EntityPlayer){
			if(!((EntityPlayer)getOwner()).worldObj.isRemote)
				((EntityPlayer)getOwner()).addChatMessage("<"+getName()+">" + text);
		}
	}


	/*========RENDERING METHODS=========*/

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
		case 33 :
			return new Moa();
		case 34:
			return new ModelDwarfMale(0f);
		case 35:
			return new ModelElfMale(0f);
		case 36:
			return new ModelOrcMale(0f);
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
			return new ResourceLocation( s+"/spider/"+(rand2Text == 0 ? "cave_":"")+"spider.png");
		case 8://wither
			return new ResourceLocation( s+"/wither/wither.png");
		case 9://rpg spider
			return new ResourceLocation( "subaraki:mobs/spider.png");
		case 10://skeleton
			return new ResourceLocation( s+"/skeleton/skeleton.png");
		case 11://wither skeleton
			return new ResourceLocation( s+"/skeleton/wither_skeleton.png");
		case 12://zombie villager
			return new ResourceLocation(s+ "/zombie/" + (rand2Text == 0 ? "zombie_villager" : "zombie")+".png");
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
			return new ResourceLocation( s+"/cat/"+(rand4Text == 0 ? "black" :
				rand4Text == 1 ? "ocelot" :
					rand4Text == 2 ? "red" :"siamese")+".png");
		case 24://squid
			return new ResourceLocation( s+"/squid.png");
		case 25://villager
			return new ResourceLocation( s+"/villager/"+ 
					(rand5Text == 0 ? "smith" :
						rand5Text == 1 ? "butcher" :
							rand5Text == 2 ? "farmer" :
								rand5Text == 3 ? "priest" :
									rand5Text == 4 ? "librarian" : "villager")+ ".png");
		case 26://wolf
			return new ResourceLocation( s+"/wolf/wolf.png");
		case 27://pigzombie
			return new ResourceLocation( s+"/zombie_pigman.png");
		case 28://rpg bull 
			return new ResourceLocation( "subaraki:mobs/bull.png");
		case 29://rpg boar
			return new ResourceLocation( "subaraki:mobs/boar.png");
		case 30://lava slime
			return new ResourceLocation( s+"/slime/magmacube.png");
		case 31://slime
			return new ResourceLocation( s+"/slime/slime.png");
		case 32://harpy
			return new ResourceLocation( "subaraki:mobs/harpy.png");
		case 33: //moa
			return new ResourceLocation("subaraki:mobs/moa.png");
		case 34: //dwarf
			return new ResourceLocation("subaraki:mobs/mpmDwarf.png");
		case 35: //elf
			return new ResourceLocation("subaraki:mobs/mpmElf.png");
		case 36: //orc
			return new ResourceLocation("subaraki:mobs/mpmOrc.png");
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
		case 25 :
			int y =rand.nextInt(2);
			return y == 0 ?"mob.villager.haggle" : "mob.villager.idle";
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
		case 33:
			return "mob.chicken.say";
		default :
			return null;
		}
	}


}
