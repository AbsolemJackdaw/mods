/*     */ package threeDitems;
/*     */ 
/*     */ import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import threeDitems.models.bottle;
import threeDitems.models.rod;
import threeDitems.models.stick;

public class clp extends cmp
{
	public static clp instance = new clp();

	public void render() {
		instance = this;
		Render3d.hastoRenderOnTick = true;

		MinecraftForgeClient.registerItemRenderer(Item.stick.itemID, new Render3d(new stick(),"/subaraki/3d/stick.png", 0.5f,0.2f,0.0f,40f,90f,10f,0f,0f,0f,0f,0f,0f,1,1));
		MinecraftForgeClient.registerItemRenderer(Item.fishingRod.itemID, new Render3d(new rod(),"/subaraki/3d/rod.png", 0.5f,0.2f, -0.05f, -50f,-110f,0f,0.1f,-0.2f,0.4f,0f,0f,-30f,1,1));
		MinecraftForgeClient.registerItemRenderer(Item.carrotOnAStick.itemID,new Render3d(new rod(),"/subaraki/3d/carrots.png" ,	0.5f,0.2f, -0.05f, -50f,-110f,0f,0.1f,-0.2f,0.4f,0f,0f,-30f,1,1));

		MinecraftForgeClient.registerItemRenderer(Item.potion.itemID, new Render3d(new bottle(),"/subaraki/3d/bottle.png", 0.2f,0.2f,0.1f,0f,0f,0f,-0.1f,0.05f,0f,0f,0f,10f,1,1));
		MinecraftForgeClient.registerItemRenderer(Item.expBottle.itemID, new Render3d(new bottle(),"/subaraki/3d/bottle.png", 0.2f,0.2f,0.1f,0f,0f,0f,-0.1f,0.05f,0f,0f,0f,10f,1,1));

		RenderItem.inst.addEmptyBottle(Item.glassBottle,"/subaraki/3d/bottle.png",1,1);

		RenderItem.inst.addBread(Item.bread,"/subaraki/3d/bread.png",1,1);

		RenderItem.inst.addApple(Item.appleRed,"/subaraki/3d/apple.png",1,1);
		RenderItem.inst.addApple(Item.appleGold,"/subaraki/3d/appleGold.png",1,1);

		RenderItem.inst.addBowl(Item.bowlEmpty,"/subaraki/3d/bowl.png",1,1);
		RenderItem.inst.addBowl(Item.bowlSoup,"/subaraki/3d/bowlfull.png",1,1);

		RenderItem.inst.addBall(Item.fireballCharge,"/subaraki/3d/fireball.png",1,1);
		RenderItem.inst.addBall(Item.slimeBall,"/subaraki/3d/slime.png",1,1);
		RenderItem.inst.addBall(Item.snowball,"/subaraki/3d/snowball.png",1,1);
		RenderItem.inst.addEnder(Item.enderPearl,"/subaraki/3d/pearl.png",1,1);
		RenderItem.inst.addEnder(Item.eyeOfEnder,"/subaraki/3d/pearl.png",1,1);

		RenderItem.inst.addBucket(Item.bucketEmpty,"/subaraki/3d/bucket.png",1,1);
		RenderItem.inst.addBucket(Item.bucketLava,"/subaraki/3d/bucketLava.png",1,1);
		RenderItem.inst.addBucket(Item.bucketWater,"/subaraki/3d/bucketWater.png",1,1);
		RenderItem.inst.addBucket(Item.bucketMilk,"/subaraki/3d/bucketMilk.png",1,1);

		RenderItem.inst.addBook(Item.book,"/subaraki/3d/book.png",1,1);
		RenderItem.inst.addBook(Item.writableBook,"/subaraki/3d/writtenBook.png",1,1);
		RenderItem.inst.addBook(Item.writtenBook,"/subaraki/3d/writtenBook.png",1,1);
		RenderItem.inst.addBook(Item.enchantedBook,"/subaraki/3d/enchanted.png",1,1);

		RenderItem.inst.addDisc(Item.record13,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordCat,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordBlocks,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordChirp,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordFar,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordMall,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordMellohi,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.record11,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordStrad,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordStal,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordWard,"/subaraki/3d/disc.png",1,1);
		RenderItem.inst.addDisc(Item.recordWait,"/subaraki/3d/disc.png",1,1);

		RenderItem.inst.addMineral(Item.emerald,"/subaraki/3d/emerald.png",1,1);
		RenderItem.inst.addMineral(Item.diamond,"/subaraki/3d/diamond.png",1,1);
		RenderItem.inst.addMineral(Item.netherQuartz,"/subaraki/3d/quartz.png",1,1);
		RenderItem.inst.addMineral(Item.dyePowder,"/subaraki/3d/lapis.png",1,1);

		RenderItem.inst.addIngot(Item.ingotIron,"/subaraki/3d/ingot.png",1,1);
		RenderItem.inst.addIngot(Item.ingotGold,"/subaraki/3d/ingotGold.png",1,1);
		RenderItem.inst.addIngot(Item.brick,"/subaraki/3d/brick.png",1,1);
		RenderItem.inst.addIngot(Item.netherrackBrick,"/subaraki/3d/brickNether.png",1,1);

		if(Config3D.instance.SwordModel){
			RenderItem.inst.addSword(Item.swordDiamond,"/subaraki/3d/swordDiamond.png",1,1);
			RenderItem.inst.addSword(Item.swordGold,"/subaraki/3d/swordGold.png",1,1);
			RenderItem.inst.addSword(Item.swordIron,"/subaraki/3d/swordSteel.png",1,1);
			RenderItem.inst.addSword(Item.swordStone,"/subaraki/3d/swordStone.png",1,1);
			RenderItem.inst.addSword(Item.swordWood,"/subaraki/3d/swordWood.png",1,1);
		}else{
			RenderItem.inst.addSword(Item.swordDiamond,"/subaraki/3d/PlainSwordDiamond.png",0.7f,1);
			RenderItem.inst.addSword(Item.swordGold,"/subaraki/3d/PlainSwordGold.png",0.7f,1);
			RenderItem.inst.addSword(Item.swordIron,"/subaraki/3d/PlainSwordIron.png",0.7f,1);
			RenderItem.inst.addSword(Item.swordStone,"/subaraki/3d/PlainSwordStone.png",0.7f,1);
			RenderItem.inst.addSword(Item.swordWood,"/subaraki/3d/PlainSwordWood.png",0.7f,1);
		}
		

		RenderItem.inst.addPick(Item.pickaxeDiamond,"/subaraki/3d/pickDiamond.png",1,1);
		RenderItem.inst.addPick(Item.pickaxeGold,"/subaraki/3d/pickGold.png",1,1);
		RenderItem.inst.addPick(Item.pickaxeIron,"/subaraki/3d/pickSteel.png",1,1);
		RenderItem.inst.addPick(Item.pickaxeStone,"/subaraki/3d/pickStone.png",1,1);
		RenderItem.inst.addPick(Item.pickaxeWood,"/subaraki/3d/pickWood.png",1,1);

		RenderItem.inst.addSpade(Item.shovelDiamond,"/subaraki/3d/spadeDiamond.png",1,1);
		RenderItem.inst.addSpade(Item.shovelGold,"/subaraki/3d/spadeGold.png",1,1);
		RenderItem.inst.addSpade(Item.shovelIron,"/subaraki/3d/spadeIron.png",1,1);
		RenderItem.inst.addSpade(Item.shovelStone,"/subaraki/3d/spadeStone.png",1,1);
		RenderItem.inst.addSpade(Item.shovelWood,"/subaraki/3d/spadeWood.png",1,1);

		RenderItem.inst.addHatchet(Item.axeDiamond,"/subaraki/3d/axeDiamond.png",1,1);
		RenderItem.inst.addHatchet(Item.axeGold,"/subaraki/3d/axeGold.png",1,1);
		RenderItem.inst.addHatchet(Item.axeIron,"/subaraki/3d/axeSteel.png",1,1);
		RenderItem.inst.addHatchet(Item.axeStone,"/subaraki/3d/axeStone.png",1,1);
		RenderItem.inst.addHatchet(Item.axeWood,"/subaraki/3d/axeWood.png",1,1);

		RenderItem.inst.addHoe(Item.hoeDiamond,"/subaraki/3d/hoeDiamond.png",1,1);
		RenderItem.inst.addHoe(Item.hoeGold,"/subaraki/3d/hoeGold.png",1,1);
		RenderItem.inst.addHoe(Item.hoeIron,"/subaraki/3d/hoeIron.png",1,1);
		RenderItem.inst.addHoe(Item.hoeStone,"/subaraki/3d/hoeStone.png",1,1);
		RenderItem.inst.addHoe(Item.hoeWood,"/subaraki/3d/hoeWood.png",1,1);

		RenderItem.inst.addArmorHelm(Item.helmetDiamond,1,2);
		RenderItem.inst.addArmorHelm(Item.helmetGold,1,2);
		RenderItem.inst.addArmorHelm(Item.helmetLeather,1,2);
		RenderItem.inst.addArmorHelm(Item.helmetChain,1,2);
		RenderItem.inst.addArmorHelm(Item.helmetIron,1,2);

		RenderItem.inst.addArmorPlateBody(Item.plateDiamond,1,3);
		RenderItem.inst.addArmorPlateBody(Item.plateGold,1,3);
		RenderItem.inst.addArmorPlateBody(Item.plateIron,1,3);
		RenderItem.inst.addArmorPlateBody(Item.plateChain,1,3);
		RenderItem.inst.addArmorPlateBody(Item.plateLeather,1,3);

		RenderItem.inst.addArmorLegs(Item.legsDiamond,1,3);
		RenderItem.inst.addArmorLegs(Item.legsGold,1,3);
		RenderItem.inst.addArmorLegs(Item.legsIron,1,3);
		RenderItem.inst.addArmorLegs(Item.legsChain,1,3);
		RenderItem.inst.addArmorLegs(Item.legsLeather,1,3);

		RenderItem.inst.addArmorBoots(Item.bootsDiamond,1,3);
		RenderItem.inst.addArmorBoots(Item.bootsGold,1,3);
		RenderItem.inst.addArmorBoots(Item.bootsChain,1,3);
		RenderItem.inst.addArmorBoots(Item.bootsIron,1,3);
		RenderItem.inst.addArmorBoots(Item.bootsLeather,1,3);

		RenderItem.inst.addCart(Item.minecartEmpty,0.5f,1.0f);
		RenderItem.inst.addCart(Item.minecartCrate,0.5f,1.0f);
		RenderItem.inst.addCart(Item.minecartPowered,0.5f,1.0f);
		RenderItem.inst.addCart(Item.minecartTnt,0.5f,1.0f);
		RenderItem.inst.addCart(Item.minecartHopper,0.5f,1.0f);

		RenderItem.inst.addMeat(Item.beefRaw, "/subaraki/3d/beef.png", 1,1);
		RenderItem.inst.addMeat(Item.beefCooked, "/subaraki/3d/beefCooked.png", 1,1);
		RenderItem.inst.addMeat(Item.porkRaw, "/subaraki/3d/pork.png", 1,1);
		RenderItem.inst.addMeat(Item.porkCooked, "/subaraki/3d/porkCooked.png", 1,1);
		RenderItem.inst.addMeat(Item.rottenFlesh, "/subaraki/3d/rottenMeat.png", 1,1);

		RenderItem.inst.addChicken(Item.chickenRaw,"/subaraki/3d/chick.png",1,1);
		RenderItem.inst.addChicken(Item.chickenCooked,"/subaraki/3d/chickCooked.png",1,1);

		RenderItem.inst.addFish(Item.fishRaw,"/subaraki/3d/fish.png",0.5f,0.5f);
		RenderItem.inst.addFish(Item.fishCooked,"/subaraki/3d/fishCooked.png",0.5f,0.5f);

		RenderItem.inst.addBlock(Item.cake,Block.cake,"/assets/minecraft/textures/blocks/cake_top.png",0.5f,1.0f);
		RenderItem.inst.addBlock(Item.reed,Block.reed,"/assets/minecraft/textures/blocks/reeds.png",0.5f,1.0f);

		//broke for some odd reason.
		//		RenderItem.inst.addBlock(Item.cauldron,Block.cauldron,"/assets/minecraft/textures/blocks/reeds.png",1.0f,1.0f);
		//		RenderItem.inst.addBlock(Item.redstoneRepeater,Block.redstoneRepeaterIdle,"/assets/minecraft/textures/blocks/reeds.png",1.0f,1.0f);
		//		RenderItem.inst.addBlock(Item.comparator,Block.redstoneComparatorIdle,"/assets/minecraft/textures/blocks/reeds.png",1.0f,1.0f);
		//		RenderItem.inst.addBlock(Item.brewingStand,Block.brewingStand,"/assets/minecraft/textures/blocks/reeds.png",1.0f,1.0f);
		//		RenderItem.inst.addBlock(Item.flowerPot,Block.flowerPot,"/assets/minecraft/textures/blocks/reeds.png",1.0f,1.0f);

		RenderItem.inst.addDoor(Item.doorWood, "/subaraki/3d/doorWood.png",2,2);
		RenderItem.inst.addDoor(Item.doorIron, "/subaraki/3d/doorIron.png",2,2);

		RenderItem.inst.addChicken(Item.chickenRaw, "/subaraki/3d/chick.png", 1,1);
		RenderItem.inst.addChicken(Item.chickenCooked, "/subaraki/3d/chickCooked.png", 1,1);

		RenderItem.inst.addCarrot(Item.carrot, "/subaraki/3d/carrotOrange.png", 1,1);
		RenderItem.inst.addCarrot(Item.goldenCarrot, "/subaraki/3d/carrotGold.png", 1,1);

		RenderItem.inst.addPaper(Item.paper, "/subaraki/3d/paper.png", 0.7f, 1.0f);
		RenderItem.inst.addPaper(Item.emptyMap, "/subaraki/3d/paperMap.png", 0.7f, 1.0f);

		RenderItem.inst.addSaddle(Item.saddle, "/subaraki/3d/saddle.png", 1,1);

		RenderItem.inst.addSpiderEye(Item.spiderEye, "/subaraki/3d/spidereye.png", 1.0f,1.0f);

		RenderItem.inst.addCookie(Item.cookie, "/subaraki/3d/cookie.png", 1.0f,1.0f);

		RenderItem.inst.addNugget(Item.goldNugget, "/subaraki/3d/goldNugget.png", 1,1);

		RenderItem.inst.addBriquet(Item.flintAndSteel, "/subaraki/3d/flintNSteel.png", 0.7f,1.0f);

		RenderItem.inst.addSign(Item.sign, "/assets/minecraft/textures/entity/sign.png", 0.5f,0.9f);

		RenderItem.inst.addPainting(Item.painting, "/subaraki/3d/painting.png", 0.6f, 1.0f);

		RenderItem.inst.addStraightStick(Item.blazeRod, "/subaraki/3d/BlazeRod.png", 1.2f, 1.5f);

		RenderItem.inst.addCoal(Item.coal, "/subaraki/3d/coal.png", 0.7f, 1.0f);

		RenderItem.inst.addEgg(Item.egg,"/subaraki/3d/egg.png", 0.6f,0.6f);

		RenderItem.inst.addBoat(Item.boat,"/assets/minecraft/textures/entity/boat.png", 0.5f,1.0f);

		RenderItem.inst.addFrame(Item.itemFrame, 1,1);

		RenderItem.inst.addBed(Item.bed,"/subaraki/3d/bed.png",0.5f,1.0f);

		RenderItem.inst.addBone(Item.bone,"/subaraki/3d/bone.png",1,1);

		RenderItem.inst.addArrow(Item.arrow, "/subaraki/3d/arrows.png", 0.7f, 1.0f);

		RenderItem.inst.addScisor(Item.shears, "/subaraki/3d/scisor.png", 1.2f, 1.2f);

		RenderItem.inst.addSkull(Item.skull, 0.7f, 1.0f);

		RenderItem.inst.addSpawner(Item.monsterPlacer, 0.6f, 0.6f);

		RenderItem.inst.addBow(Item.bow, "/subaraki/3d/bow.png", 0.7f, 1.0f);

		RenderItem.inst.addPie(Item.pumpkinPie, 1.0f, 1.0f, "/subaraki/3d/pie.png");
		
		RenderItem.inst.addWheat(Item.wheat, 1.0f, 1.0f, "/subaraki/3d/wheat.png");
	}

}

