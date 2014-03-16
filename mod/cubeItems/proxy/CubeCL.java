package cubeItems.proxy;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cubeItems.ModelCubeWorld;
import cubeItems.renderers.RenderCubeBowl;
import cubeItems.renderers.RenderCubeBucket;
import cubeItems.renderers.RenderCubeDisc;
import cubeItems.renderers.RenderCubeDust;
import cubeItems.renderers.RenderCubeTransparentBall;
import cubeItems.renderers.RenderSpawnEgg;
import cubeItems.renderers.blocks.RenderVanillaBlocks;
import cubeItems.renderers.food.RenderCubeDiscFood;
import cubeItems.renderers.food.RenderCubeGeneric;
import cubeItems.renderers.food.RenderCubeGenericBig;
import cubeItems.renderers.potion.RenderCubePotion;
import cubeItems.renderers.tools.RenderCubeAxe;
import cubeItems.renderers.tools.RenderCubePickAxe;
import cubeItems.renderers.tools.RenderCubeSword;

public class CubeCL extends CubeS {

	// PLUS FISHING POLE
	String[] swords = new String[] { "swordWood", "swordStone", "swordIron",
			"swordGold", "swordDiamond", "fishpole", "fishpole_carrot" };
	Item[] swordIDs = new Item[] { Items.wooden_sword, Items.stone_sword,
			Items.iron_sword, Items.golden_sword, Items.diamond_sword,
			Items.fishing_rod, Items.carrot_on_a_stick };

	String[] hoes = new String[] { "hoeWood", "hoeIron", "hoeStone",
			"hoeDiamond", "hoeGold" };
	Item[] hoeIDs = new Item[] { Items.wooden_hoe, Items.iron_hoe,
			Items.stone_hoe, Items.diamond_hoe, Items.golden_hoe };

	String[] axes = new String[] { "axeWood", "axeStone", "axeIron", "axeGold",
			"axeDiamond" };
	Item[] axeID = new Item[] { Items.wooden_axe, Items.stone_axe,
			Items.iron_axe, Items.golden_axe, Items.diamond_axe };

	String[] pick = new String[] { "pickWood", "pickStone", "pickIron",
			"pickGold", "pickDiamond" };
	Item[] pickID = new Item[] { Items.wooden_pickaxe, Items.stone_pickaxe,
			Items.iron_pickaxe, Items.golden_pickaxe, Items.diamond_pickaxe };

	String[] spades = new String[] { "spade_wood", "spade_stone", "spade_iron",
			"spade_gold", "spade_diamond" };
	Item[] spadeIDs = new Item[] { Items.wooden_shovel, Items.stone_shovel,
			Items.iron_shovel, Items.golden_shovel, Items.diamond_shovel };

	String[] potatos = new String[] { "potato_eatable", "potato_poisonous",
			"potato_cooked" };
	Item[] potatosID = new Item[] { Items.potato, Items.poisonous_potato,
			Items.baked_potato };

	String[] bowl = new String[] { "bowl_empty", "bowl_stew" };
	Item[] bowlID = new Item[] { Items.bowl, Items.mushroom_stew };

	String[] apple = new String[] { "apple_red", "apple_gold" };
	Item[] appleID = new Item[] { Items.apple, Items.golden_apple };

	String[] carrot = new String[] { "carrot", "carrot_gold" };
	Item[] carrotID = new Item[] { Items.carrot, Items.golden_carrot };

	String[] ball = new String[] { "ball_snow", "ball_slime", "ball_magma" };
	Item[] ballID = new Item[] { Items.snowball, Items.slime_ball,
			Items.magma_cream };

	String[] ballEnder = new String[] { "ball_ender", "ball_ender" };
	Item[] ballIDEnd = new Item[] { Items.ender_pearl, Items.ender_eye };

	String[] dust = new String[] { "sugar", "redstone", "gunpowder",
			"glowstone" };
	Item[] dustID = new Item[] { Items.sugar, Items.redstone, Items.gunpowder,
			Items.glowstone_dust };

	Item[] discsID = new Item[] { Items.record_13, Items.record_cat,
			Items.record_blocks, Items.record_chirp, Items.record_far,
			Items.record_mall, Items.record_mellohi, Items.record_stal,
			Items.record_strad, Items.record_ward, Items.record_11,
			Items.record_wait };

	Item[] potionID = new Item[] { Items.glass_bottle, Items.potionitem };

	String[] minerals = new String[] { "emerald", "diamond", "nugget" };
	Item[] minIDs = new Item[] { Items.emerald, Items.diamond,
			Items.gold_nugget };

	String[] buckets = new String[] { "bucket", "bucket_milk", "bucket_lava",
			"bucket_water" };
	Item[] buckIDs = new Item[] { Items.bucket, Items.milk_bucket,
			Items.lava_bucket, Items.water_bucket };

	String[] books = new String[] { "book", "book_written", "book_quill",
			"book_enchanted" };
	Item[] bookIDs = new Item[] { Items.book, Items.written_book,
			Items.writable_book, Items.enchanted_book };

	// others
	String[] food = new String[] { "fish", "fish_cooked", "chicken_raw",
			"chicken_cooked", "pumpkin_pie" };
	Item[] foodIDs = new Item[] { Items.fish, Items.cooked_fished,
			Items.chicken, Items.cooked_chicken, Items.pumpkin_pie };

	// disc or flat food that has to be held right up
	String[] food2 = new String[] { "beef", "beef_cooked", "pork",
			"pork_cooked" };
	Item[] foodIDs2 = new Item[] { Items.beef, Items.cooked_beef,
			Items.porkchop, Items.cooked_porkchop };

	String[] armor = new String[] { "armor_legs_iron", "armor_chest_iron" };
	Item[] armorIDs = new Item[] { Items.iron_leggings, Items.iron_chestplate };

	@Override
	public void render() {

		// Potato is used for 10x10 surcfaces.
		// Disc is 15x15
		// any other surface has his own renderer, like swords, pickaxes and
		// axes

		for (int i = 0; i < swords.length; i++) {
			MinecraftForgeClient.registerItemRenderer(
					swordIDs[i],
					new RenderCubeSword(new ModelCubeWorld(ModelCubeWorld.class
							.getResourceAsStream("/assets/subaraki/cubeModels/"
									+ swords[i] + ".cub"))));
		}

		for (int i = 0; i < spades.length; i++) {
			MinecraftForgeClient.registerItemRenderer(
					spadeIDs[i],
					new RenderCubeSword(new ModelCubeWorld(ModelCubeWorld.class
							.getResourceAsStream("/assets/subaraki/cubeModels/"
									+ spades[i] + ".cub"))));
		}

		for (int i = 0; i < hoes.length; i++) {
			MinecraftForgeClient.registerItemRenderer(
					hoeIDs[i],
					new RenderCubeSword(new ModelCubeWorld(ModelCubeWorld.class
							.getResourceAsStream("/assets/subaraki/cubeModels/"
									+ hoes[i] + ".cub"))));
		}

		for (int i = 0; i < axes.length; i++) {
			MinecraftForgeClient.registerItemRenderer(
					axeID[i],
					new RenderCubeAxe(new ModelCubeWorld(ModelCubeWorld.class
							.getResourceAsStream("/assets/subaraki/cubeModels/"
									+ axes[i] + ".cub"))));
		}

		for (int i = 0; i < pick.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							pickID[i],
							new RenderCubePickAxe(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ pick[i] + ".cub"))));
		}

		for (int i = 0; i < potatos.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							potatosID[i],
							new RenderCubeGeneric(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ potatos[i]
															+ ".cub"))));
		}

		for (int i = 0; i < bowl.length; i++) {
			MinecraftForgeClient.registerItemRenderer(
					bowlID[i],
					new RenderCubeBowl(new ModelCubeWorld(ModelCubeWorld.class
							.getResourceAsStream("/assets/subaraki/cubeModels/"
									+ bowl[i] + ".cub"))));
		}

		for (int i = 0; i < apple.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							appleID[i],
							new RenderCubeGeneric(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ apple[i] + ".cub"))));
		}

		for (int i = 0; i < armor.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							armorIDs[i],
							new RenderCubeGenericBig(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ armor[i] + ".cub"))));
		}

		for (int i = 0; i < carrot.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							carrotID[i],
							new RenderCubeGeneric(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ carrot[i]
															+ ".cub"))));
		}

		for (int i = 0; i < ball.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							ballID[i],
							new RenderCubeGeneric(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ ball[i] + ".cub"))));
		}

		for (int i = 0; i < ballEnder.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							ballIDEnd[i],
							new RenderCubeTransparentBall(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ ballEnder[i]
															+ ".cub"))));
		}

		for (int i = 0; i < dust.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							dustID[i],
							new RenderCubeDust(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/dust_"
															+ dust[i] + ".cub"))));
		}

		for (int i = 0; i < minerals.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							minIDs[i],
							new RenderCubeGeneric(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ minerals[i]
															+ ".cub"))));
		}

		for (int i = 0; i < potionID.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							potionID[i],
							new RenderCubePotion(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/bottle_top.cub")),
									i));
		}

		for (int i = 0; i < discsID.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							discsID[i],
							new RenderCubeDisc(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/music_disc_"
															+ (i + 1) + ".cub"))));
		}

		for (int i = 0; i < buckets.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							buckIDs[i],
							new RenderCubeBucket(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ buckets[i]
															+ ".cub"))));
		}

		for (int i = 0; i < books.length; i++) {
			MinecraftForgeClient.registerItemRenderer(
					bookIDs[i],
					new RenderCubeDisc(new ModelCubeWorld(ModelCubeWorld.class
							.getResourceAsStream("/assets/subaraki/cubeModels/"
									+ books[i] + ".cub"))));
		}

		for (int i = 0; i < food.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							foodIDs[i],
							new RenderCubeGenericBig(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ food[i] + ".cub"))));
		}

		for (int i = 0; i < food2.length; i++) {
			MinecraftForgeClient
					.registerItemRenderer(
							foodIDs2[i],
							new RenderCubeDiscFood(
									new ModelCubeWorld(
											ModelCubeWorld.class
													.getResourceAsStream("/assets/subaraki/cubeModels/"
															+ food2[i] + ".cub"))));
		}

		MinecraftForgeClient
				.registerItemRenderer(
						Items.bread,
						new RenderCubeGeneric(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/bread.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.cookie,
						new RenderCubeDisc(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/cookie.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.stick,
						new RenderCubeSword(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/stick.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.fireworks,
						new RenderCubeGeneric(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/fireTwerks_rocket.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.repeater,
						new RenderCubeDisc(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/repeater.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.comparator,
						new RenderCubeDisc(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/comparator.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.flower_pot,
						new RenderCubeGeneric(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/flowerPot.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.spawn_egg,
						new RenderSpawnEgg(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/egg.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.egg,
						new RenderCubeGeneric(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/egg.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.arrow,
						new RenderCubeSword(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/arrow.cub"))));

		MinecraftForgeClient
				.registerItemRenderer(
						Items.bone,
						new RenderCubeGeneric(
								new ModelCubeWorld(
										ModelCubeWorld.class
												.getResourceAsStream("/assets/subaraki/cubeModels/bone.cub"))));

		MinecraftForgeClient.registerItemRenderer(Items.cake,
				new RenderVanillaBlocks(null, Blocks.cake));
		MinecraftForgeClient.registerItemRenderer(Items.reeds,
				new RenderVanillaBlocks(null, Blocks.reeds));
		MinecraftForgeClient.registerItemRenderer(
				Item.getItemFromBlock(Blocks.red_mushroom),
				new RenderVanillaBlocks(null, Blocks.red_mushroom));
		MinecraftForgeClient.registerItemRenderer(
				Item.getItemFromBlock(Blocks.brown_mushroom),
				new RenderVanillaBlocks(null, Blocks.brown_mushroom_block));
		MinecraftForgeClient.registerItemRenderer(Item
				.getItemFromBlock(Blocks.torch), new RenderVanillaBlocks(null,
				Blocks.torch));
		MinecraftForgeClient.registerItemRenderer(
				Item.getItemFromBlock(Blocks.redstone_torch),
				new RenderVanillaBlocks(null, Blocks.redstone_torch));
		MinecraftForgeClient.registerItemRenderer(
				Item.getItemFromBlock(Blocks.deadbush),
				new RenderVanillaBlocks(null, Blocks.deadbush));
		MinecraftForgeClient.registerItemRenderer(Item
				.getItemFromBlock(Blocks.sapling), new RenderVanillaBlocks(
				null, Blocks.sapling));
		MinecraftForgeClient.registerItemRenderer(Item
				.getItemFromBlock(Blocks.lever), new RenderVanillaBlocks(null,
				Blocks.unlit_redstone_torch));
		MinecraftForgeClient.registerItemRenderer(
				Item.getItemFromBlock(Blocks.red_flower),
				new RenderVanillaBlocks(null, Blocks.red_flower));
		MinecraftForgeClient.registerItemRenderer(
				Item.getItemFromBlock(Blocks.yellow_flower),
				new RenderVanillaBlocks(null, Blocks.yellow_flower));
		// MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Blocks.tallGrass),
		// new RenderVanillaBlocks(null,Blocks.tallGrass));
		MinecraftForgeClient.registerItemRenderer(Item
				.getItemFromBlock(Blocks.web), new RenderVanillaBlocks(null,
				Blocks.web));

	}
}
