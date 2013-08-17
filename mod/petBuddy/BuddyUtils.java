package petBuddy;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class BuddyUtils {


	public static String IDToName(int id){
		switch(id){
		case 2:
			return "Pig";
		case 3:
			return "Tiny you";
		case 4:
			return "Creeper";
		case 5:
			return "Cow";
		case 6:
			return "Blaze";
		case 7:
			return "Spider";
		case 8:
			return "Wither";
		case 9:
			return "RpgSpider";
		case 10:
			return "Skeleton";
		case 11:
			return "Skeleton (Wither)";
		case 12:
			return "Zombie";
		case 13:
			return "Ghast";
		case 14:
			return "Sheep";
		case 15:
			return "EnderMan";
		case 16:
			return "Silverfish";
		case 17:
			return "SnowMan";
		case 18:
			return "Iron Golem";
		case 19:
			return "Dragon";
		case 20:
			return "Bat";
		case 21:
			return "Chicken";
		case 22:
			return "Mooshroom";
		case 23:
			return "Ocelot";
		case 24:
			return "Squid";
		case 25:
			return "Villager";
		case 26:
			return "Wolf";
		case 27:
			return "Pig Zombie";
		case 28:
			return "Rpg Bull";
		case 29:
			return "Rpg Boar";
		case 30:
			return "Magma Cube";
		case 31:
			return "Slime";
		case 32:
			return "Harpy";
		case 33:
			return "Moa";
		case 34:
			return "Dwarf";
		case 35:
			return "Elf";
		case 36:
			return "Orc";

		default:
			return "ERROR";
		}
	}

	public static int EntityToID(Class<? extends EntityLiving> class1){

		if(class1.equals(EntityPig.class))
			return 2;
		if(class1.equals(EntityPlayer.class))
			return 3;
		if(class1.equals(EntityCreeper.class))
			return 4;
		if(class1.equals(EntityCow.class))
			return 5;
		if(class1.equals(EntityBlaze.class))
			return 6;
		if(class1.equals(EntitySpider.class))
			return 7;
		if(class1.equals(EntityWither.class))
			return 8;
		//		if(class1.equals(EntityPig.class))
		//			return 9;
		if(class1.equals(EntitySkeleton.class))
			return 10;
		//		if(class1.equals(EntitySkeleton.class))
		//			return 11;
		if(class1.equals(EntityZombie.class))
			return 12;
		if(class1.equals(EntityGhast.class))
			return 13;
		if(class1.equals(EntitySheep.class))
			return 14;
		if(class1.equals(EntityEnderman.class))
			return 15;
		if(class1.equals(EntitySilverfish.class))
			return 16;
		if(class1.equals(EntitySnowman.class))
			return 17;
		if(class1.equals(EntityIronGolem.class))
			return 18;
		if(class1.equals(EntityDragon.class))
			return 19;
		if(class1.equals(EntityBat.class))
			return 20;
		if(class1.equals(EntityChicken.class))
			return 21;
		if(class1.equals(EntityMooshroom.class))
			return 22;
		if(class1.equals(EntitySilverfish.class))
			return 23;
		if(class1.equals(EntitySquid.class))
			return 24;
		if(class1.equals(EntityVillager.class))
			return 25;
		if(class1.equals(EntityWolf.class))
			return 26;
		if(class1.equals(EntityPigZombie.class))
			return 27;
		//		if(class1.equals(EntityPig.class))
		//			return 28;
		//		if(class1.equals(EntityPig.class))
		//			return 29;
		if(class1.equals(EntityMagmaCube.class))
			return 30;
		if(class1.equals(EntitySlime.class))
			return 31;
		//		if(class1.equals(EntityPig.class))
		//			return 32;
		//		if(class1.equals(EntityPig.class))
		//			return 33;
		//		if(class1.equals(EntityPig.class))
		//			return 34;
		//		if(class1.equals(EntityPig.class))
		//			return 35;
		//		if(class1.equals(EntityPig.class))
		//			return 36;

		return -5;	
	}


	public static int ItemToID(ItemStack stack){

		Item item = stack.getItem();
		
		if(item.equals(Item.porkRaw))
			return 2;
		if(item.equals(Item.bread))
			return 3;
		if(item.equals(Item.gunpowder))
			return 4;
		if(item.equals(Item.beefRaw))
			return 5;
		if(item.equals(Item.blazeRod))
			return 6;
		if(item.equals(Item.spiderEye))
			return 7;
		if(item.equals(Item.netherStar))
			return 8;
		if(item.equals(Item.fermentedSpiderEye))
			return 9;
		if(item.equals(Item.arrow))
			return 10;
		if(item.equals(Item.skull))
			return 11;
		if(item.equals(Item.rottenFlesh))
			return 12;
		if(item.equals(Item.ghastTear))
			return 13;
		if(stack.itemID  == Block.cloth.blockID)
			return 14;
		if(item.equals(Item.enderPearl))
			return 15;
		if(item.equals(Item.fishRaw))
			return 16;
		if(item.equals(Item.snowball))
			return 17;
		if(stack.itemID == Block.blockIron.blockID)
			return 18;
		if(stack.itemID == Block.whiteStone.blockID)
			return 19;
		if(item.equals( Item.netherStalkSeeds))
			return 20;
		if(item.equals( Item.chickenRaw))
			return 21;
		if(stack.itemID == Block.mycelium.blockID)
			return 22;
		if(item.equals( Item.fishCooked))
			return 23;
		if(item.equals(Item.dyePowder) && stack.getItemDamage() == 0)
			return 24;
		if(stack.getItem() instanceof ItemBook)
			return 25;
		if(item.equals(Item.bone))
			return 26;
		if(item.equals(Item.netherQuartz))
			return 27;
		if(item.equals(Item.beefCooked))
			return 28;
		if(item.equals(Item.porkCooked))
			return 29;
		if(item.equals(Item.magmaCream))
			return 30;
		if(item.equals(Item.slimeBall))
			return 31;
		if(item.equals(Item.feather))
			return 32;
		if(item.equals(Item.wheat))
			return 33;
		if(item.equals(Item.pickaxeWood))
			return 34;
		if(item.equals(Item.ingotGold))
			return 35;
		if(item.equals(Block.obsidian))
			return 36;
		
		return -5;	
	}
}
