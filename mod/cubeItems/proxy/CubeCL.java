package cubeItems.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cubeItems.ModelCubeWorld;
import cubeItems.renderers.RenderCubeBowl;
import cubeItems.renderers.RenderCubeBucket;
import cubeItems.renderers.RenderCubeDisc;
import cubeItems.renderers.RenderCubeDust;
import cubeItems.renderers.RenderCubeTransparentBall;
import cubeItems.renderers.RenderSpawnEgg;
import cubeItems.renderers.food.RenderCubeDiscFood;
import cubeItems.renderers.food.RenderCubeGeneric;
import cubeItems.renderers.food.RenderCubeGenericBig;
import cubeItems.renderers.potion.RenderCubePotion;
import cubeItems.renderers.tools.RenderCubeAxe;
import cubeItems.renderers.tools.RenderCubePickAxe;
import cubeItems.renderers.tools.RenderCubeSword;


public class CubeCL extends CubeS {

	//PLUS FISHING POLE
	String[] swords = new String[] {"swordWood","swordStone", "swordIron", "swordGold", "swordDiamond", "fishpole"};
	int[] swordIDs = new int[] {Item.swordWood.itemID,Item.swordStone.itemID,Item.swordIron.itemID,Item.swordGold.itemID,Item.swordDiamond.itemID, Item.fishingRod.itemID};

	String[] hoes = new String[] {"hoeWood", "hoeIron", "hoeStone" , "hoeDiamond", "hoeGold"};
	int[] hoeIDs = new int[] {Item.hoeWood.itemID,Item.hoeIron.itemID, Item.hoeStone.itemID, Item.hoeDiamond.itemID, Item.hoeGold.itemID};

	String[] axes = new String[] {"axeWood","axeStone", "axeIron", "axeGold", "axeDiamond"};
	int[] axeID = new int[] {Item.axeWood.itemID,Item.axeStone.itemID,Item.axeIron.itemID,Item.axeGold.itemID,Item.axeDiamond.itemID};

	String[] pick = new String[] {"pickWood","pickStone", "pickIron", "pickGold", "pickDiamond"};
	int[] pickID = new int[] {Item.pickaxeWood.itemID,Item.pickaxeStone.itemID,Item.pickaxeIron.itemID,Item.pickaxeGold.itemID,Item.pickaxeDiamond.itemID};

	String[] spades = new String[] {
			"spade_wood",
			"spade_stone",
			"spade_iron",
			"spade_gold",
			"spade_diamond"
	};




	String[] potatos = new String[] {"potato_eatable","potato_poisonous", "potato_cooked"};
	int[] potatosID = new int[] {Item.potato.itemID, Item.poisonousPotato.itemID, Item.bakedPotato.itemID};

	String[] bowl = new String[] {"bowl_empty","bowl_stew"};
	int[] bowlID = new int[] {Item.bowlEmpty.itemID, Item.bowlSoup.itemID};

	String[] apple = new String[] {"apple_red","apple_gold"};
	int[] appleID = new int[] {Item.appleRed.itemID, Item.appleGold.itemID};

	String[] carrot = new String[] {"carrot","carrot_gold"};
	int[] carrotID = new int[] {Item.carrot.itemID, Item.goldenCarrot.itemID};

	String[] ball = new String[] {"ball_snow","ball_slime", "ball_magma"};
	int[] ballID = new int[] {Item.snowball.itemID, Item.slimeBall.itemID, Item.magmaCream.itemID};

	String[] ballEnder = new String[] {"ball_ender", "ball_ender"};
	int[] ballIDEnd = new int[] {Item.enderPearl.itemID, Item.eyeOfEnder.itemID};

	String[] dust = new String[] {"sugar","redstone", "gunpowder", "glowstone"};
	int[] dustID = new int[] {Item.sugar.itemID, Item.redstone.itemID, Item.gunpowder.itemID, Item.glowstone.itemID};

	int[] discsID = new int[] {Item.record13.itemID, Item.recordCat.itemID, Item.recordBlocks.itemID,Item.recordChirp.itemID,Item.recordFar.itemID,
			Item.recordMall.itemID,Item.recordMellohi.itemID,Item.recordStal.itemID,Item.recordStrad.itemID,Item.recordWard.itemID,Item.record11.itemID,
			Item.recordWait.itemID};

	int[] potionID = new int[] {Item.glassBottle.itemID, Item.potion.itemID};

	String[] minerals = new String[]{"emerald", "diamond", "nugget"};
	int[] minIDs = new int[] {Item.emerald.itemID, Item.diamond.itemID, Item.goldNugget.itemID};

	String[] buckets = new String[]{"bucket", "bucket_milk", "bucket_lava", "bucket_water"};
	int[] buckIDs = new int[] {Item.bucketEmpty.itemID, Item.bucketMilk.itemID,Item.bucketLava.itemID, Item.bucketWater.itemID};

	String[] books = new String[]{"book", "book_written", "book_quill", "book_enchanted"};
	int[] bookIDs = new int[]{Item.book.itemID, Item.writtenBook.itemID, Item.writableBook.itemID, Item.enchantedBook.itemID};

	String[] food = new String[] {"fish", "fish_cooked", "chicken_raw", "chicken_cooked"};
	int [] foodIDs = new int[] {Item.fishRaw.itemID, Item.fishCooked.itemID, Item.chickenRaw.itemID, Item.chickenCooked.itemID};

	String[] food2 = new String[] {"beef", "beef_cooked", "pork", "pork_cooked"};
	int [] foodIDs2 = new int[] {Item.beefRaw.itemID, Item.beefCooked.itemID, Item.porkRaw.itemID, Item.porkCooked.itemID};

	String[] armor = new String[]{"armor_legs_iron", "armor_chest_iron"};
	int[] armorIDs = new int[]{Item.legsIron.itemID, Item.plateIron.itemID};

	@Override
	public void render() {

		//Potato is used for 10x10 surcfaces.
		//Disc is 15x15
		//any other surface has his own renderer, like swords, pickaxes and axes

		for(int i = 0; i < swords.length; i++)
			MinecraftForgeClient.registerItemRenderer(swordIDs[i], new RenderCubeSword(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+swords[i]+".cub"))));

		for(int i = 0; i < hoes.length; i++)
			MinecraftForgeClient.registerItemRenderer(hoeIDs[i], new RenderCubeSword(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+hoes[i]+".cub"))));

		for(int i = 0; i < axes.length; i++)
			MinecraftForgeClient.registerItemRenderer(axeID[i], new RenderCubeAxe(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+axes[i]+".cub"))));

		for(int i = 0; i < pick.length; i++)
			MinecraftForgeClient.registerItemRenderer(pickID[i], new RenderCubePickAxe(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+pick[i]+".cub"))));

		for(int i = 0; i < potatos.length; i++)
			MinecraftForgeClient.registerItemRenderer(potatosID[i], new RenderCubeGeneric(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+potatos[i]+".cub"))));

		for(int i = 0; i < bowl.length; i++)
			MinecraftForgeClient.registerItemRenderer(bowlID[i], new RenderCubeBowl(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+bowl[i]+".cub"))));

		for(int i = 0; i < apple.length; i++)
			MinecraftForgeClient.registerItemRenderer(appleID[i], new RenderCubeGeneric(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+apple[i]+".cub"))));

		for(int i = 0; i < armor.length; i++)
			MinecraftForgeClient.registerItemRenderer(armorIDs[i], new RenderCubeGenericBig(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+armor[i]+".cub"))));

		for(int i = 0; i < carrot.length; i++)
			MinecraftForgeClient.registerItemRenderer(carrotID[i], new RenderCubeGeneric(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+carrot[i]+".cub"))));

		for(int i = 0; i < ball.length; i++)
			MinecraftForgeClient.registerItemRenderer(ballID[i], new RenderCubeGeneric(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+ball[i]+".cub"))));

		for(int i = 0; i < ballEnder.length; i++)
			MinecraftForgeClient.registerItemRenderer(ballIDEnd[i], new RenderCubeTransparentBall(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+ballEnder[i]+".cub"))));

		for(int i = 0; i < dust.length; i++)
			MinecraftForgeClient.registerItemRenderer(dustID[i], new RenderCubeDust(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/dust_"+dust[i]+".cub"))));

		for(int i = 0; i < minerals.length; i++)
			MinecraftForgeClient.registerItemRenderer(minIDs[i], new RenderCubeGeneric(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+minerals[i]+".cub"))));

		for(int i = 0; i < potionID.length; i++)
			MinecraftForgeClient.registerItemRenderer(potionID[i], new RenderCubePotion(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_top.cub")),i));

		for(int i = 0; i < discsID.length; i++)
			MinecraftForgeClient.registerItemRenderer(discsID[i], new RenderCubeDisc(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/music_disc_"+(i+1)+".cub"))));

		for(int i = 0; i < buckets.length; i++)
			MinecraftForgeClient.registerItemRenderer(buckIDs[i], new RenderCubeBucket(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+buckets[i]+".cub"))));

		for(int i = 0; i < books.length; i++)
			MinecraftForgeClient.registerItemRenderer(bookIDs[i], new RenderCubeDisc(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+books[i]+".cub"))));

		for(int i = 0; i < food.length; i++)
			MinecraftForgeClient.registerItemRenderer(foodIDs[i], new RenderCubeGenericBig(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+food[i]+".cub"))));

		for(int i = 0; i < food2.length; i++)
			MinecraftForgeClient.registerItemRenderer(foodIDs2[i], new RenderCubeDiscFood(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+food2[i]+".cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.bread.itemID, new RenderCubeGeneric(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bread.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.cookie.itemID, new RenderCubeDisc(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/cookie.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.stick.itemID, new RenderCubeSword(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/stick.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.firework.itemID, new RenderCubeGeneric(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/fireTwerks_rocket.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.redstoneRepeater.itemID, new RenderCubeDisc(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/repeater.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.comparator.itemID, new RenderCubeDisc(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/comparator.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.flowerPot.itemID, new RenderCubeGeneric(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/flowerPot.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.monsterPlacer.itemID, new RenderSpawnEgg(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/egg.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.egg.itemID, new RenderCubeGeneric(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/egg.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.arrow.itemID, new RenderCubeSword(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/arrow.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.bone.itemID, new RenderCubeGeneric(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bone.cub"))));


	}
}
