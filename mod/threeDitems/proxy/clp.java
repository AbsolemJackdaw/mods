package threeDitems.proxy;

import charms.CharmHUD;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import threeDitems.bow.BowHud;
import threeDitems.handlers.KeyHandler;
import threeDitems.models.apple;
import threeDitems.models.arrow;
import threeDitems.models.axe;
import threeDitems.models.ball;
import threeDitems.models.bed;
import threeDitems.models.bone;
import threeDitems.models.book;
import threeDitems.models.bottle;
import threeDitems.models.bow;
import threeDitems.models.bowl;
import threeDitems.models.bread;
import threeDitems.models.brod;
import threeDitems.models.bucket;
import threeDitems.models.carrot;
import threeDitems.models.chicken;
import threeDitems.models.coal;
import threeDitems.models.cookie;
import threeDitems.models.disc;
import threeDitems.models.door;
import threeDitems.models.egg;
import threeDitems.models.emerald;
import threeDitems.models.enderball;
import threeDitems.models.fish;
import threeDitems.models.flintNSteel;
import threeDitems.models.head;
import threeDitems.models.hoe;
import threeDitems.models.ingot;
import threeDitems.models.meat;
import threeDitems.models.nugget;
import threeDitems.models.painting;
import threeDitems.models.paper;
import threeDitems.models.pick;
import threeDitems.models.pie;
import threeDitems.models.rod;
import threeDitems.models.saddle;
import threeDitems.models.scisor;
import threeDitems.models.sign;
import threeDitems.models.smallSword;
import threeDitems.models.spade;
import threeDitems.models.spidereye;
import threeDitems.models.stick;
import threeDitems.models.wheat;
import threeDitems.render.item.RenderArrow;
import threeDitems.render.item.RenderBall;
import threeDitems.render.item.RenderBed;
import threeDitems.render.item.RenderBlazeRod;
import threeDitems.render.item.RenderBoat;
import threeDitems.render.item.RenderBone;
import threeDitems.render.item.RenderBook;
import threeDitems.render.item.RenderBriquet;
import threeDitems.render.item.RenderBucket;
import threeDitems.render.item.RenderDisc;
import threeDitems.render.item.RenderDoor;
import threeDitems.render.item.RenderEnder;
import threeDitems.render.item.RenderIngot;
import threeDitems.render.item.RenderItemFrame;
import threeDitems.render.item.RenderMinecart;
import threeDitems.render.item.RenderMonsterPlacer;
import threeDitems.render.item.RenderPainting;
import threeDitems.render.item.RenderPaper;
import threeDitems.render.item.RenderPotion;
import threeDitems.render.item.RenderRod;
import threeDitems.render.item.RenderSaddle;
import threeDitems.render.item.RenderShears;
import threeDitems.render.item.RenderSign;
import threeDitems.render.item.RenderSkull;
import threeDitems.render.item.RenderSpiderEye;
import threeDitems.render.item.RenderStick;
import threeDitems.render.item.RenderWheat;
import threeDitems.render.item.armor.RenderArmorBoots;
import threeDitems.render.item.armor.RenderArmorHelm;
import threeDitems.render.item.armor.RenderArmorLegs;
import threeDitems.render.item.armor.RenderArmorPlate;
import threeDitems.render.item.block.RenderItemBlock;
import threeDitems.render.item.food.RenderApple;
import threeDitems.render.item.food.RenderBowl;
import threeDitems.render.item.food.RenderBread;
import threeDitems.render.item.food.RenderCarrot;
import threeDitems.render.item.food.RenderChicken;
import threeDitems.render.item.food.RenderCookie;
import threeDitems.render.item.food.RenderEGg;
import threeDitems.render.item.food.RenderFish;
import threeDitems.render.item.food.RenderMeat;
import threeDitems.render.item.oreDrops.RenderCoal;
import threeDitems.render.item.oreDrops.RenderMineral;
import threeDitems.render.item.oreDrops.RenderNugget;
import threeDitems.render.item.tools.RenderBow;
import threeDitems.render.item.tools.RenderHatchet;
import threeDitems.render.item.tools.RenderHoe;
import threeDitems.render.item.tools.RenderPickaxe;
import threeDitems.render.item.tools.RenderSpade;
import threeDitems.render.item.tools.RenderSword;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class clp extends cmp
{

	private int startId = 2002;
	ModelBiped biped = new ModelBiped();
	public void render() {	
		
		TickRegistry.registerTickHandler(new BowHud(), Side.CLIENT);

		KeyBindingRegistry.registerKeyBinding(new KeyHandler());

		/*===============================Stick===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.stick.itemID, 
				new RenderStick(new stick(),"subaraki:3d/items/stick.png"));
		MinecraftForgeClient.registerItemRenderer(Item.blazeRod.itemID, 
				new RenderBlazeRod(new brod(),"subaraki:3d/items/BlazeRod.png"));
		
		/*===============================Monster Spawner===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.monsterPlacer.itemID, 
				new RenderMonsterPlacer(new egg(),"subaraki:3d/items/eggSpawn.png"));

		/*===============================Ender Eye/Pearl===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.enderPearl.itemID, 
				new RenderEnder(new enderball(),"subaraki:3d/items/pearl.png"));
		MinecraftForgeClient.registerItemRenderer(Item.eyeOfEnder.itemID, 
				new RenderEnder(new enderball(),"subaraki:3d/items/pearl.png"));
		
		/*==============================Potions===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.potion.itemID, 
				new RenderPotion(new bottle(),"subaraki:3d/items/bottle.png"));
		MinecraftForgeClient.registerItemRenderer(Item.expBottle.itemID, 
				new RenderPotion(new bottle(),"subaraki:3d/items/bottle.png"));
		MinecraftForgeClient.registerItemRenderer(Item.glassBottle.itemID, 
				new RenderPotion(new bottle(),"subaraki:3d/items/bottle.png"));

		/*===============================Rods===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.fishingRod.itemID, 
				new RenderRod(new rod(),"subaraki:3d/items/rod.png"));
		MinecraftForgeClient.registerItemRenderer(Item.carrotOnAStick.itemID, 
				new RenderRod(new rod(),"subaraki:3d/items/carrots.png"));

		/*===============================Food===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.appleRed.itemID, 
				new RenderApple(new apple(),"subaraki:3d/items/apple.png"));
		MinecraftForgeClient.registerItemRenderer(Item.appleGold.itemID, 
				new RenderApple(new apple(),"subaraki:3d/items/appleGold.png"));

		MinecraftForgeClient.registerItemRenderer(Item.bowlEmpty.itemID, 
				new RenderBowl(new bowl(),"subaraki:3d/items/bowl.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bowlSoup.itemID, 
				new RenderBowl(new bowl(),"subaraki:3d/items/bowlfull.png"));
		
		MinecraftForgeClient.registerItemRenderer(Item.bread.itemID, 
				new RenderBread(new bread(),"subaraki:3d/items/bread.png"));
		
		MinecraftForgeClient.registerItemRenderer(Item.cookie.itemID, 
				new RenderCookie(new cookie(),"subaraki:3d/items/cookie.png"));
		
		MinecraftForgeClient.registerItemRenderer(Item.carrot.itemID, 
				new RenderCarrot(new carrot(),"subaraki:3d/items/carrotOrange.png"));
		MinecraftForgeClient.registerItemRenderer(Item.goldenCarrot.itemID, 
				new RenderCarrot(new carrot(),"subaraki:3d/items/carrotGold.png"));
		
		MinecraftForgeClient.registerItemRenderer(Item.egg.itemID, 
				new RenderEGg(new egg(),"subaraki:3d/items/egg.png"));
		
		MinecraftForgeClient.registerItemRenderer(Item.pumpkinPie.itemID, 
				new RenderBread(new pie(),"subaraki:3d/items/Pie.png"));
		
		MinecraftForgeClient.registerItemRenderer(Item.chickenRaw.itemID, 
				new RenderChicken(new chicken(),"subaraki:3d/items/chick.png"));
		MinecraftForgeClient.registerItemRenderer(Item.chickenCooked.itemID, 
				new RenderChicken(new chicken(),"subaraki:3d/items/chickCooked.png"));
		
		MinecraftForgeClient.registerItemRenderer(Item.fishRaw.itemID, 
				new RenderFish(new fish(),"subaraki:3d/items/fish.png"));
		MinecraftForgeClient.registerItemRenderer(Item.fishCooked.itemID, 
				new RenderFish(new fish(),"subaraki:3d/items/fishCooked.png"));

		MinecraftForgeClient.registerItemRenderer(Item.beefRaw.itemID, 
				new RenderMeat(new meat(),"subaraki:3d/items/beef.png"));
		MinecraftForgeClient.registerItemRenderer(Item.beefCooked.itemID, 
				new RenderMeat(new meat(),"subaraki:3d/items/beefCooked.png"));
		MinecraftForgeClient.registerItemRenderer(Item.porkRaw.itemID, 
				new RenderMeat(new meat(),"subaraki:3d/items/pork.png"));
		MinecraftForgeClient.registerItemRenderer(Item.porkCooked.itemID, 
				new RenderMeat(new meat(),"subaraki:3d/items/porkCooked.png"));
		MinecraftForgeClient.registerItemRenderer(Item.rottenFlesh.itemID, 
				new RenderMeat(new meat(),"subaraki:3d/items/rottenMeat.png"));

		/*===============================Balls===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.fireballCharge.itemID, 
				new RenderBall(new ball(),"subaraki:3d/items/fireball.png"));
		MinecraftForgeClient.registerItemRenderer(Item.slimeBall.itemID, 
				new RenderBall(new ball(),"subaraki:3d/items/slime.png"));
		MinecraftForgeClient.registerItemRenderer(Item.snowball.itemID, 
				new RenderBall(new ball(),"subaraki:3d/items/snowball.png"));
		MinecraftForgeClient.registerItemRenderer(Item.clay.itemID, 
				new RenderBall(new ball(),"subaraki:3d/items/snowball.png"));
		MinecraftForgeClient.registerItemRenderer(Item.magmaCream.itemID, 
				new RenderBall(new ball(),"subaraki:3d/items/slime.png"));
		/*===============================Buckets===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.bucketEmpty.itemID, 
				new RenderBucket(new bucket(),"subaraki:3d/items/bucket.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bucketLava.itemID, 
				new RenderBucket(new bucket(),"subaraki:3d/items/bucketLava.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bucketWater.itemID, 
				new RenderBucket(new bucket(),"subaraki:3d/items/bucketWater.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bucketMilk.itemID, 
				new RenderBucket(new bucket(),"subaraki:3d/items/bucketMilk.png"));

		/*===============================Books===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.book.itemID, 
				new RenderBook(new book(),"subaraki:3d/items/book.png"));
		MinecraftForgeClient.registerItemRenderer(Item.writableBook.itemID, 
				new RenderBook(new book(),"subaraki:3d/items/writtenBook.png"));
		MinecraftForgeClient.registerItemRenderer(Item.writtenBook.itemID, 
				new RenderBook(new book(),"subaraki:3d/items/writtenBook.png"));
		MinecraftForgeClient.registerItemRenderer(Item.enchantedBook.itemID, 
				new RenderBook(new book(),"subaraki:3d/items/enchanted.png"));

		/*===============================Records===============================*/
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

		/*===============================Minerals===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.emerald.itemID, 
				new RenderMineral(new emerald(),"subaraki:3d/items/emerald.png"));
		MinecraftForgeClient.registerItemRenderer(Item.diamond.itemID, 
				new RenderMineral(new emerald(),"subaraki:3d/items/diamond.png"));
		MinecraftForgeClient.registerItemRenderer(Item.netherQuartz.itemID, 
				new RenderMineral(new emerald(),"subaraki:3d/items/quartz.png"));
		MinecraftForgeClient.registerItemRenderer(Item.dyePowder.itemID, 
				new RenderMineral(new emerald(),"subaraki:3d/items/lapis.png"));

		/*===============================Ingots===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.ingotIron.itemID, 
				new RenderIngot(new ingot(),"subaraki:3d/items/ingot.png"));
		MinecraftForgeClient.registerItemRenderer(Item.ingotGold.itemID, 
				new RenderIngot(new ingot(),"subaraki:3d/items/ingotGold.png"));
		MinecraftForgeClient.registerItemRenderer(Item.brick.itemID, 
				new RenderIngot(new ingot(),"subaraki:3d/items/brick.png"));
		MinecraftForgeClient.registerItemRenderer(Item.netherrackBrick.itemID, 
				new RenderIngot(new ingot(),"subaraki:3d/items/brickNether.png"));

		/*===============================Tools===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.bow.itemID, 
				new RenderBow(new bow(),"subaraki:3d/items/bow.png"));
		
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
		
		MinecraftForgeClient.registerItemRenderer(Item.shovelWood.itemID, 
				new RenderSpade(new spade(),"subaraki:3d/items/spadeWood.png"));
		MinecraftForgeClient.registerItemRenderer(Item.shovelStone.itemID, 
				new RenderSpade(new spade(),"subaraki:3d/items/spadeStone.png"));
		MinecraftForgeClient.registerItemRenderer(Item.shovelGold.itemID, 
				new RenderSpade(new spade(),"subaraki:3d/items/spadeGold.png"));
		MinecraftForgeClient.registerItemRenderer(Item.shovelIron.itemID, 
				new RenderSpade(new spade(),"subaraki:3d/items/spadeIron.png"));
		MinecraftForgeClient.registerItemRenderer(Item.shovelDiamond.itemID, 
				new RenderSpade(new spade(),"subaraki:3d/items/spadeDiamond.png"));
		
		MinecraftForgeClient.registerItemRenderer(Item.axeWood.itemID, 
				new RenderHatchet(new axe(),"subaraki:3d/items/axeWood.png"));
		MinecraftForgeClient.registerItemRenderer(Item.axeStone.itemID, 
				new RenderHatchet(new axe(),"subaraki:3d/items/axeStone.png"));
		MinecraftForgeClient.registerItemRenderer(Item.axeGold.itemID, 
				new RenderHatchet(new axe(),"subaraki:3d/items/axeGold.png"));
		MinecraftForgeClient.registerItemRenderer(Item.axeIron.itemID, 
				new RenderHatchet(new axe(),"subaraki:3d/items/axeSteel.png"));
		MinecraftForgeClient.registerItemRenderer(Item.axeDiamond.itemID, 
				new RenderHatchet(new axe(),"subaraki:3d/items/axeDiamond.png"));
		
		/*===============================Armor===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.helmetDiamond.itemID, 
				new RenderArmorHelm(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.helmetGold.itemID, 
				new RenderArmorHelm(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.helmetLeather.itemID, 
				new RenderArmorHelm(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.helmetIron.itemID, 
				new RenderArmorHelm(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.helmetChain.itemID, 
				new RenderArmorHelm(biped,""));
		
		MinecraftForgeClient.registerItemRenderer(Item.plateDiamond.itemID, 
				new RenderArmorPlate(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.plateGold.itemID, 
				new RenderArmorPlate(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.plateLeather.itemID, 
				new RenderArmorPlate(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.plateIron.itemID, 
				new RenderArmorPlate(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.plateChain.itemID, 
				new RenderArmorPlate(biped,""));
		
		MinecraftForgeClient.registerItemRenderer(Item.legsDiamond.itemID, 
				new RenderArmorLegs(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.legsGold.itemID, 
				new RenderArmorLegs(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.legsLeather.itemID, 
				new RenderArmorLegs(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.legsIron.itemID, 
				new RenderArmorLegs(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.legsChain.itemID, 
				new RenderArmorLegs(biped,""));
		
		MinecraftForgeClient.registerItemRenderer(Item.bootsDiamond.itemID, 
				new RenderArmorBoots(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.bootsGold.itemID, 
				new RenderArmorBoots(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.bootsIron.itemID, 
				new RenderArmorBoots(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.bootsLeather.itemID, 
				new RenderArmorBoots(biped,""));
		MinecraftForgeClient.registerItemRenderer(Item.bootsChain.itemID, 
				new RenderArmorBoots(biped,""));
		
		/*===============================Minecarts===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.minecartEmpty.itemID, 
				new RenderMinecart(null,"textures/entity/minecart.png"));
		MinecraftForgeClient.registerItemRenderer(Item.minecartCrate.itemID, 
				new RenderMinecart(null,"textures/entity/minecart.png"));
		MinecraftForgeClient.registerItemRenderer(Item.minecartPowered.itemID, 
				new RenderMinecart(null,"textures/entity/minecart.png"));
		MinecraftForgeClient.registerItemRenderer(Item.minecartTnt.itemID, 
				new RenderMinecart(null,"textures/entity/minecart.png"));
		MinecraftForgeClient.registerItemRenderer(Item.minecartHopper.itemID, 
				new RenderMinecart(null,"textures/entity/minecart.png"));
		
		/*===============================Blocks===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.cake.itemID, 
				new RenderItemBlock(null,"", Block.cake));
		MinecraftForgeClient.registerItemRenderer(Item.reed.itemID, 
				new RenderItemBlock(null,"", Block.reed));
		MinecraftForgeClient.registerItemRenderer(Block.mushroomRed.blockID, 
				new RenderItemBlock(null,"", Block.mushroomRed));
		MinecraftForgeClient.registerItemRenderer(Block.mushroomBrown.blockID, 
				new RenderItemBlock(null,"", Block.mushroomBrown));
		MinecraftForgeClient.registerItemRenderer(Block.torchWood.blockID, 
				new RenderItemBlock(null,"", Block.torchWood));
		MinecraftForgeClient.registerItemRenderer(Block.torchRedstoneActive.blockID, 
				new RenderItemBlock(null,"", Block.torchRedstoneActive));
		MinecraftForgeClient.registerItemRenderer(Block.deadBush.blockID, 
				new RenderItemBlock(null,"", Block.deadBush));
		MinecraftForgeClient.registerItemRenderer(Block.sapling.blockID, 
				new RenderItemBlock(null,"", Block.sapling));
		MinecraftForgeClient.registerItemRenderer(Block.lever.blockID, 
				new RenderItemBlock(null,"", Block.torchRedstoneIdle));
		MinecraftForgeClient.registerItemRenderer(Block.plantRed.blockID, 
				new RenderItemBlock(null,"", Block.plantRed));
		MinecraftForgeClient.registerItemRenderer(Block.plantYellow.blockID, 
				new RenderItemBlock(null,"", Block.plantYellow));
		MinecraftForgeClient.registerItemRenderer(Block.tallGrass.blockID, 
				new RenderItemBlock(null,"", Block.tallGrass));
		MinecraftForgeClient.registerItemRenderer(Block.web.blockID, 
				new RenderItemBlock(null,"", Block.web));
		
		/*===============================Skulls===============================*/
		MinecraftForgeClient.registerItemRenderer(Item.skull.itemID, 
				new RenderSkull(new head(),""));
		
		
		
		
		
		MinecraftForgeClient.registerItemRenderer(Item.coal.itemID, 
				new RenderCoal(new coal(),"subaraki:3d/items/coal.png"));
		MinecraftForgeClient.registerItemRenderer(Item.saddle.itemID, 
				new RenderSaddle(new saddle(),"subaraki:3d/items/saddle.png"));
		MinecraftForgeClient.registerItemRenderer(Item.goldNugget.itemID, 
				new RenderNugget(new nugget(),"subaraki:3d/items/goldNugget.png"));
		MinecraftForgeClient.registerItemRenderer(Item.spiderEye.itemID, 
				new RenderSpiderEye(new spidereye(),"subaraki:3d/items/spidereye.png"));
		MinecraftForgeClient.registerItemRenderer(Item.fermentedSpiderEye.itemID, 
				new RenderSpiderEye(new spidereye(),"subaraki:3d/items/spidereye.png"));
		MinecraftForgeClient.registerItemRenderer(Item.doorWood.itemID, 
				new RenderDoor(new door(),"subaraki:3d/items/doorWood.png"));
		MinecraftForgeClient.registerItemRenderer(Item.doorIron.itemID, 
				new RenderDoor(new door(),"subaraki:3d/items/doorIron.png"));
		MinecraftForgeClient.registerItemRenderer(Item.paper.itemID, 
				new RenderPaper(new paper(),"subaraki:3d/items/paper.png"));
		MinecraftForgeClient.registerItemRenderer(Item.emptyMap.itemID, 
				new RenderPaper(new paper(),"subaraki:3d/items/paperMap.png"));
		MinecraftForgeClient.registerItemRenderer(Item.flintAndSteel.itemID, 
				new RenderBriquet(new flintNSteel(),"subaraki:3d/items/flintNSteel.png"));
		MinecraftForgeClient.registerItemRenderer(Item.sign.itemID, 
				new RenderSign(new sign(),"textures/entity/sign.png"));
		MinecraftForgeClient.registerItemRenderer(Item.painting.itemID, 
				new RenderPainting(new painting(),"subaraki:3d/items/painting.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bone.itemID, 
				new RenderBone(new bone(),"subaraki:3d/items/bone.png"));
		MinecraftForgeClient.registerItemRenderer(Item.wheat.itemID, 
				new RenderWheat(new wheat(),"subaraki:3d/items/wheat.png"));
		MinecraftForgeClient.registerItemRenderer(Item.boat.itemID, 
				new RenderBoat(new ModelBoat(),"textures/entity/boat.png"));
		MinecraftForgeClient.registerItemRenderer(Item.bed.itemID, 
				new RenderBed(new bed(),"subaraki:3d/items/bed.png"));
		MinecraftForgeClient.registerItemRenderer(Item.arrow.itemID, 
				new RenderArrow(new arrow(),"subaraki:3d/items/arrows.png"));
		MinecraftForgeClient.registerItemRenderer(Item.shears.itemID, 
				new RenderShears(new scisor(),"subaraki:3d/items/scisor.png"));
		MinecraftForgeClient.registerItemRenderer(Item.itemFrame.itemID, 
				new RenderItemFrame(null,""));
	}
}

