package threeDitems.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import threeDitems.handlers.KeyHandler;
import threeDitems.render.item.RenderBall;
import threeDitems.render.item.RenderBook;
import threeDitems.render.item.RenderBucket;
import threeDitems.render.item.RenderDisc;
import threeDitems.render.item.RenderEnder;
import threeDitems.render.item.RenderIngot;
import threeDitems.render.item.RenderMineral;
import threeDitems.render.item.RenderMonsterPlacer;
import threeDitems.render.item.RenderPotion;
import threeDitems.render.item.RenderRod;
import threeDitems.render.item.RenderStick;
import threeDitems.render.item.food.RenderApple;
import threeDitems.render.item.food.RenderBowl;
import threeDitems.render.item.tools.RenderHoe;
import threeDitems.render.item.tools.RenderPickaxe;
import threeDitems.render.item.tools.RenderSword;
import threeDitems_old.models.apple;
import threeDitems_old.models.ball;
import threeDitems_old.models.book;
import threeDitems_old.models.bottle;
import threeDitems_old.models.bowl;
import threeDitems_old.models.bucket;
import threeDitems_old.models.disc;
import threeDitems_old.models.egg;
import threeDitems_old.models.emerald;
import threeDitems_old.models.enderball;
import threeDitems_old.models.hoe;
import threeDitems_old.models.ingot;
import threeDitems_old.models.pick;
import threeDitems_old.models.rod;
import threeDitems_old.models.smallSword;
import threeDitems_old.models.stick;
import cpw.mods.fml.client.registry.KeyBindingRegistry;

public class clp extends cmp
{

	private int startId = 2002;

	public void render() {		
		KeyBindingRegistry.registerKeyBinding(new KeyHandler());

		/*stick*/
		MinecraftForgeClient.registerItemRenderer(Item.stick.itemID, 
				new RenderStick(new stick(),"subaraki:3d/items/stick.png"));
		/*Monster Spawner*/
		MinecraftForgeClient.registerItemRenderer(Item.monsterPlacer.itemID, 
				new RenderMonsterPlacer(new egg(),"subaraki:3d/items/eggSpawn.png"));

		/*Ender Eye/Pearl*/
		MinecraftForgeClient.registerItemRenderer(Item.enderPearl.itemID, 
				new RenderEnder(new enderball(),"subaraki:3d/items/pearl.png"));
		MinecraftForgeClient.registerItemRenderer(Item.eyeOfEnder.itemID, 
				new RenderEnder(new enderball(),"subaraki:3d/items/pearl.png"));
		
		/*potions*/
		MinecraftForgeClient.registerItemRenderer(Item.potion.itemID, 
				new RenderPotion(new bottle(),"subaraki:3d/items/bottle.png"));
		MinecraftForgeClient.registerItemRenderer(Item.expBottle.itemID, 
				new RenderPotion(new bottle(),"subaraki:3d/items/bottle.png"));
		MinecraftForgeClient.registerItemRenderer(Item.glassBottle.itemID, 
				new RenderPotion(new bottle(),"subaraki:3d/items/bottle.png"));

		/*rods*/
		MinecraftForgeClient.registerItemRenderer(Item.fishingRod.itemID, 
				new RenderRod(new rod(),"subaraki:3d/items/rod.png"));
		MinecraftForgeClient.registerItemRenderer(Item.carrotOnAStick.itemID, 
				new RenderRod(new rod(),"subaraki:3d/items/carrots.png"));

		/*Food*/
		MinecraftForgeClient.registerItemRenderer(Item.appleRed.itemID, 
				new RenderApple(new apple(),"subaraki:3d/items/apple.png"));
		MinecraftForgeClient.registerItemRenderer(Item.appleGold.itemID, 
				new RenderApple(new apple(),"subaraki:3d/items/appleGold.png"));

		MinecraftForgeClient.registerItemRenderer(Item.bowlEmpty.itemID, 
				new RenderBowl(new bowl(),"subaraki:3d/items/bowl.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bowlSoup.itemID, 
				new RenderBowl(new bowl(),"subaraki:3d/items/bowlfull.png"));

		/*Balls*/
		MinecraftForgeClient.registerItemRenderer(Item.fireballCharge.itemID, 
				new RenderBall(new ball(),"subaraki:3d/items/fireball.png"));
		MinecraftForgeClient.registerItemRenderer(Item.slimeBall.itemID, 
				new RenderBall(new ball(),"subaraki:3d/items/slime.png"));
		MinecraftForgeClient.registerItemRenderer(Item.snowball.itemID, 
				new RenderBall(new ball(),"subaraki:3d/items/snowball.png"));

		/*Buckets*/
		MinecraftForgeClient.registerItemRenderer(Item.bucketEmpty.itemID, 
				new RenderBucket(new bucket(),"subaraki:3d/items/bucket.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bucketLava.itemID, 
				new RenderBucket(new bucket(),"subaraki:3d/items/bucketLava.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bucketWater.itemID, 
				new RenderBucket(new bucket(),"subaraki:3d/items/bucketWater.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bucketMilk.itemID, 
				new RenderBucket(new bucket(),"subaraki:3d/items/bucketMilk.png"));

		/*Books*/
		MinecraftForgeClient.registerItemRenderer(Item.book.itemID, 
				new RenderBook(new book(),"subaraki:3d/items/book.png"));
		MinecraftForgeClient.registerItemRenderer(Item.writableBook.itemID, 
				new RenderBook(new book(),"subaraki:3d/items/writtenBook.png"));
		MinecraftForgeClient.registerItemRenderer(Item.writtenBook.itemID, 
				new RenderBook(new book(),"subaraki:3d/items/writtenBookbook.png"));
		MinecraftForgeClient.registerItemRenderer(Item.enchantedBook.itemID, 
				new RenderBook(new book(),"subaraki:3d/items/enchanted.png"));

		/*Records*/
		MinecraftForgeClient.registerItemRenderer(Item.record13.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordCat.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordBlocks.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordChirp.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordFar.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordMall.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordMellohi.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.record11.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordStrad.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordStal.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordWard.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));
		MinecraftForgeClient.registerItemRenderer(Item.recordWait.itemID, 
				new RenderDisc(new disc(),"subaraki:3d/items/disc.png"));

		/*Minerals*/
		MinecraftForgeClient.registerItemRenderer(Item.emerald.itemID, 
				new RenderMineral(new emerald(),"subaraki:3d/items/emerald.png"));
		MinecraftForgeClient.registerItemRenderer(Item.diamond.itemID, 
				new RenderMineral(new emerald(),"subaraki:3d/items/diamond.png"));
		MinecraftForgeClient.registerItemRenderer(Item.netherQuartz.itemID, 
				new RenderMineral(new emerald(),"subaraki:3d/items/quartz.png"));
		MinecraftForgeClient.registerItemRenderer(Item.dyePowder.itemID, 
				new RenderMineral(new emerald(),"subaraki:3d/items/lapis.png"));

		/*Ingots*/
		MinecraftForgeClient.registerItemRenderer(Item.ingotIron.itemID, 
				new RenderIngot(new ingot(),"subaraki:3d/items/ingot.png"));
		MinecraftForgeClient.registerItemRenderer(Item.ingotGold.itemID, 
				new RenderIngot(new ingot(),"subaraki:3d/items/ingotGold.png"));
		MinecraftForgeClient.registerItemRenderer(Item.brick.itemID, 
				new RenderIngot(new ingot(),"subaraki:3d/items/brick.png"));
		MinecraftForgeClient.registerItemRenderer(Item.brick.itemID, 
				new RenderIngot(new ingot(),"subaraki:3d/items/brickNether.png"));

		/*Tools*/
		MinecraftForgeClient.registerItemRenderer(Item.swordWood.itemID, 
				new RenderSword(new smallSword(),"subaraki:3d/items/swordWood.png"));
		MinecraftForgeClient.registerItemRenderer(Item.swordStone.itemID, 
				new RenderSword(new smallSword(),"subaraki:3d/items/swordStone.png"));
		MinecraftForgeClient.registerItemRenderer(Item.swordIron.itemID, 
				new RenderSword(new smallSword(),"subaraki:3d/items/swordSteel.png"));
		MinecraftForgeClient.registerItemRenderer(Item.swordGold.itemID, 
				new RenderSword(new smallSword(),"subaraki:3d/items/swordGold.png"));
		MinecraftForgeClient.registerItemRenderer(Item.swordDiamond.itemID, 
				new RenderSword(new smallSword(),"subaraki:3d/items/swordDiamond.png"));

		MinecraftForgeClient.registerItemRenderer(Item.hoeWood.itemID, 
				new RenderHoe(new hoe(),"subaraki:3d/items/hoeWood.png"));
		MinecraftForgeClient.registerItemRenderer(Item.hoeStone.itemID, 
				new RenderHoe(new hoe(),"subaraki:3d/items/hoeStone.png"));
		MinecraftForgeClient.registerItemRenderer(Item.hoeIron.itemID, 
				new RenderHoe(new hoe(),"subaraki:3d/items/hoeIron.png"));
		MinecraftForgeClient.registerItemRenderer(Item.hoeGold.itemID, 
				new RenderHoe(new hoe(),"subaraki:3d/items/hoeGold.png"));
		MinecraftForgeClient.registerItemRenderer(Item.hoeDiamond.itemID, 
				new RenderHoe(new hoe(),"subaraki:3d/items/hoeDiamond.png"));

		MinecraftForgeClient.registerItemRenderer(Item.pickaxeWood.itemID, 
				new RenderPickaxe(new pick(),"subaraki:3d/items/pickWood.png"));
		MinecraftForgeClient.registerItemRenderer(Item.pickaxeStone.itemID, 
				new RenderPickaxe(new pick(),"subaraki:3d/items/pickStone.png"));
		MinecraftForgeClient.registerItemRenderer(Item.pickaxeGold.itemID, 
				new RenderPickaxe(new pick(),"subaraki:3d/items/pickGold.png"));
		MinecraftForgeClient.registerItemRenderer(Item.pickaxeIron.itemID, 
				new RenderPickaxe(new pick(),"subaraki:3d/items/pickSteel.png"));
		MinecraftForgeClient.registerItemRenderer(Item.pickaxeDiamond.itemID, 
				new RenderPickaxe(new pick(),"subaraki:3d/items/pickDiamond.png"));
	}
}

