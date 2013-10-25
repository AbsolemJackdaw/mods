package cubeItems.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cubeItems.ModelCubeWorld;
import cubeItems.renderers.RenderCubeBowl;
import cubeItems.renderers.RenderCubeDisc;
import cubeItems.renderers.food.RenderCubePotato;
import cubeItems.renderers.potion.RenderCubePotion;
import cubeItems.renderers.tools.RenderCubeAxe;
import cubeItems.renderers.tools.RenderCubePickAxe;
import cubeItems.renderers.tools.RenderCubeSword;


public class CubeCL extends CubeS {

	String[] swords = new String[] {"swordWood","swordStone", "swordIron", "swordGold", "swordDiamond"};
	int[] swordIDs = new int[] {Item.swordWood.itemID,Item.swordStone.itemID,Item.swordIron.itemID,Item.swordGold.itemID,Item.swordDiamond.itemID};

	String[] axes = new String[] {"axeWood","axeStone", "axeIron", "axeGold", "axeDiamond"};
	int[] axeID = new int[] {Item.axeWood.itemID,Item.axeStone.itemID,Item.axeIron.itemID,Item.axeGold.itemID,Item.axeDiamond.itemID};

	String[] pick = new String[] {"pickWood","pickStone", "pickIron", "pickGold", "pickDiamond"};
	int[] pickID = new int[] {Item.pickaxeWood.itemID,Item.pickaxeStone.itemID,Item.pickaxeIron.itemID,Item.pickaxeGold.itemID,Item.pickaxeDiamond.itemID};

	String[] potatos = new String[] {"potato_eatable","potato_poisonous", "potato_cooked"};
	int[] potatosID = new int[] {Item.potato.itemID, Item.poisonousPotato.itemID, Item.bakedPotato.itemID};

	String[] bowl = new String[] {"bowl_empty","bowl_stew"};
	int[] bowlID = new int[] {Item.bowlEmpty.itemID, Item.bowlSoup.itemID};

	String[] apple = new String[] {"apple_red","apple_gold"};
	int[] appleID = new int[] {Item.appleRed.itemID, Item.appleGold.itemID};

	String[] carrot = new String[] {"carrot","carrot_gold"};
	int[] carrotID = new int[] {Item.carrot.itemID, Item.goldenCarrot.itemID};
	
	int[] discsID = new int[] {Item.record13.itemID, Item.recordCat.itemID, Item.recordBlocks.itemID,Item.recordChirp.itemID,Item.recordFar.itemID,
			Item.recordMall.itemID,Item.recordMellohi.itemID,Item.recordStal.itemID,Item.recordStrad.itemID,Item.recordWard.itemID,Item.record11.itemID,
			Item.recordWait.itemID};

	int[] potionID = new int[] {Item.glassBottle.itemID, Item.potion.itemID};
	
	@Override
	public void render() {

		for(int i = 0; i < swords.length; i++){
			MinecraftForgeClient.registerItemRenderer(swordIDs[i], new RenderCubeSword(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+swords[i]+".cub"))));
		}

		for(int i = 0; i < axes.length; i++){
			MinecraftForgeClient.registerItemRenderer(axeID[i], new RenderCubeAxe(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+axes[i]+".cub"))));
		}

		for(int i = 0; i < pick.length; i++){
			MinecraftForgeClient.registerItemRenderer(pickID[i], new RenderCubePickAxe(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+pick[i]+".cub"))));
		}

		for(int i = 0; i < potatos.length; i++){
			MinecraftForgeClient.registerItemRenderer(potatosID[i], new RenderCubePotato(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+potatos[i]+".cub"))));
		}

		for(int i = 0; i < bowl.length; i++){
			MinecraftForgeClient.registerItemRenderer(bowlID[i], new RenderCubeBowl(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+bowl[i]+".cub"))));
		}

		for(int i = 0; i < apple.length; i++){
			MinecraftForgeClient.registerItemRenderer(appleID[i], new RenderCubePotato(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+apple[i]+".cub"))));
		}

		for(int i = 0; i < carrot.length; i++){
			MinecraftForgeClient.registerItemRenderer(carrotID[i], new RenderCubePotato(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/"+carrot[i]+".cub"))));
		}

		
		for(int i = 0; i < potionID.length; i++){
			MinecraftForgeClient.registerItemRenderer(potionID[i], new RenderCubePotion(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bottle_top.cub")),i));
		}

		for(int i = 0; i < discsID.length; i++){
			MinecraftForgeClient.registerItemRenderer(discsID[i], new RenderCubeDisc(
					new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/music_disc_"+(i+1)+".cub"))));
		}

		MinecraftForgeClient.registerItemRenderer(Item.bread.itemID, new RenderCubePotato(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/bread.cub"))));

		MinecraftForgeClient.registerItemRenderer(Item.cookie.itemID, new RenderCubeDisc(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/cookie.cub"))));
		
		MinecraftForgeClient.registerItemRenderer(Item.stick.itemID, new RenderCubeSword(
				new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/subaraki/cubeModels/stick.cub"))));
	}
}
