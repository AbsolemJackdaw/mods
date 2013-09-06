package threeDitems_old;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import threeDitems_old.models.Nihil;
import threeDitems_old.models.apple;
import threeDitems_old.models.arrow;
import threeDitems_old.models.axe;
import threeDitems_old.models.ball;
import threeDitems_old.models.bed;
import threeDitems_old.models.bone;
import threeDitems_old.models.book;
import threeDitems_old.models.bottle;
import threeDitems_old.models.bow;
import threeDitems_old.models.bowl;
import threeDitems_old.models.bread;
import threeDitems_old.models.brod;
import threeDitems_old.models.bucket;
import threeDitems_old.models.carrot;
import threeDitems_old.models.chicken;
import threeDitems_old.models.coal;
import threeDitems_old.models.cookie;
import threeDitems_old.models.disc;
import threeDitems_old.models.door;
import threeDitems_old.models.egg;
import threeDitems_old.models.emerald;
import threeDitems_old.models.enderball;
import threeDitems_old.models.fish;
import threeDitems_old.models.flintNSteel;
import threeDitems_old.models.head;
import threeDitems_old.models.hoe;
import threeDitems_old.models.ingot;
import threeDitems_old.models.meat;
import threeDitems_old.models.nugget;
import threeDitems_old.models.painting;
import threeDitems_old.models.paper;
import threeDitems_old.models.pick;
import threeDitems_old.models.pie;
import threeDitems_old.models.saddle;
import threeDitems_old.models.scisor;
import threeDitems_old.models.sign;
import threeDitems_old.models.smallSword;
import threeDitems_old.models.spade;
import threeDitems_old.models.spidereye;
import threeDitems_old.models.wheat;

public class RenderItem
{
	public static RenderItem inst = new RenderItem();

	public void addIngot(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new ingot(), texture,
				0.25F, 0.2F, 0.0F, 40.0F, 80.0F, 90.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addScisor(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new scisor(), texture,
				0.25F, 0.2F, -0.05F, 20.0F, -10.0F, -90.0F, -0.1F, 0.3F, 0.1F, 90.0F, 10.0F, 0.0F, scale, inWorldScale));
	}

	public void addBread(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new bread(), texture, 
				0.2F, 0.2F, 0F, 0.0F, 90.0F, -30.0F, -0.1F, 0F, -0.2F, 0.0F, 90.0F, 0.0F, scale, inWorldScale));
	}

	public void addBook(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new book(), texture, 
				0.28F, -0.1F, 0.2F, -150.0F, 0.0F, -90.0F, 0.5F, 0.0F, 0.2F, -90.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addBucket(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new bucket(), texture, 
				0.2F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F, -0.1F, 0.2F, 0.0F, 0.0F, 0.0F, 10.0F, scale, inWorldScale));
	}

	public void addBall(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new ball(), texture, 
				0.2F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F, -0.1F, 0.2F, 0.0F, 0.0F, 0.0F, 10.0F, scale, inWorldScale));
	}

	@Deprecated
	public void addEnder(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new enderball(), texture,
				0.2F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F, -0.1F, 0.2F, 0.0F, 0.0F, 0.0F, 10.0F, scale, inWorldScale));
	}

	public void addDisc(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new disc(), texture, 
				0.25F, 0.2F, 0.0F, 40.0F, 80.0F, 90.0F, 0.1F, 0.0F, 0.1F, -60.0F, 0.0F, 50.0F, scale, inWorldScale));
	}

	public void addMineral(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new emerald(), texture,
				0.25F, 0.2F, 0.0F, 40.0F, 80.0F, 90.0F, 0.1F, 0.0F, 0.1F, -60.0F, 0.0F, 50.0F, scale, inWorldScale));
	}

	public void addChicken(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new chicken(), texture,
				0.25F, 0.1F, -0.1F, 40.0F, 0.0F, 0.0F, 0.0F, 0.5F, 0.2F, 0.0F, 0.0F, 50.0F, scale, inWorldScale));
	}

	public void addSword(Item item, String texture, float scale, float inWorldScale)
	{
		ModelBase model;
		if(Config3D.instance.SwordModel){
			model = new smallSword();
			MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(model, texture,
					0.5F, 0.25F, -0.05F, -50.0F, -10.0F, -70.0F, -0.1F, 0.3F, -0.5F, -75.0F, 90.0F, 50.0F, scale, inWorldScale));
		}else{
			model = new smallSwordPlain();
			MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(model, texture,
					0f,0f,0f,0f,0f,0f,-0.2f,0.2f,-0.2f,0f,70f,0f, scale, inWorldScale));
		}
	}

	public void addHatchet(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new axe(), texture, 
				0.5F, 0.1F, -0.05F, 140.0F, -105.0F, 0.0F, 0.1F, 0.5F, -0.2F, 0.0F, 0.0F, 70.0F, scale, inWorldScale));
	}

	public void addHoe(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new hoe(), texture, 
				0.5F, 0.1F, -0.05F, 140.0F, -105.0F, 0.0F, 0.1F, 0.5F, -0.2F, 0.0F, 0.0F, 60.0F, scale, inWorldScale));
	}

	public void addSpade(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new spade(), texture, 
				0.5F, 0.1F, -0.05F, 140.0F, -105.0F, 0.0F, 0.1F, 0.1F, -0.2F, 180.0F, 0.0F, 70.0F, scale, inWorldScale));
	}

	public void addPick(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new pick(), texture, 
				0.1F, 0.66F, 0.07F, 40.0F, -20.0F, 20.0F, -0.1f,-0.1f,-0.5f, 10f, 90f,0f, scale, inWorldScale));
	}

	public void addFish(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new fish(), texture, 
				0.15F, 0.45F, 0.4F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5F, -1.0F, 0.0F, 170.0F, 50.0F, scale, inWorldScale));
	}

	public void addBowl(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new bowl(), texture, 
				0.2F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F, -0.1F, 0.2F, 0.0F, 0.0F, 0.0F, 10.0F, scale, inWorldScale));
	}

	public void addEmptyBottle(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new bottle(), texture, 
				0.2F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F, -0.1F, 0.05F, 0.0F, 0.0F, 0.0F, 10.0F, scale, inWorldScale));
	}

	public void addApple(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new apple(), texture, 
				0.2F, 0.1F, -0.1F, 30.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addArmorHelm(Item item, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new ModelBiped(), "/subaraki/3d/stick.png", 
				0.1F, 0.7F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addArmorPlateBody(Item item, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new ModelBiped(), "/subaraki/3d/stick.png", 
				-0.1F, 1.0F, -0.6F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addArmorLegs(Item item, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new ModelBiped(), "/subaraki/3d/stick.png", 
				-0.1F, 1.0F, -0.6F, 0.0F, 0.0F, 0.0F, 0.0F, -1.2F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addArmorBoots(Item item, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new ModelBiped(), "/subaraki/3d/stick.png", 
				-0.2F, 1.3F, -0.8F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addBlock(Item item, Block block, String texture, float scale, float inWorldScale)
	{
		Render3d rd = new Render3d(new Nihil(), texture, 
				0.25F, 0.2F, 180.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5F, 0.2F, -180.0F, 0.0F, 0.0F, scale,inWorldScale, block);

		MinecraftForgeClient.registerItemRenderer(item.itemID, rd);
	}

	public void addFrame(Item item, float scale, float inWorldScale) {
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new Nihil(), "/subaraki/3d/stick.png", 
				-0.2F, 0.3F, 0.0F, -30.0F, 10.0F, 90.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addCart(Item item, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new ModelMinecart(), "/assets/minecraft/textures/entity/minecart.png",
				0.25F, 0.2F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addBoat(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new ModelBoat(), texture,
				0.25F, 0.35F, 0.1F, 30.0F, -90.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -90.0F, 0.0F, scale, inWorldScale));
	}

	public void addBed(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new bed(), texture, 
				0.25F, 0.2F, 0.0F, 10.0F, -90.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -90.0F, 0.0F, scale, inWorldScale));
	}

	public void addBone(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new bone(), texture, 
				0.4F, 0.3F, 0.0F, 40.0F, 80.0F, 145.0F, 0.1F, 0.0F, 0.1F, -60.0F, 0.0F, 50.0F, scale, inWorldScale));
	}

	public void addEgg(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new egg(), texture, 
				0.2F, 0.1F, -0.1F, 30.0F, 0.0F, 0.0F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addSaddle(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new saddle(), texture, 
				0.25F, 0.2F, 0.0F, 30.0F, -90.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addCarrot(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new carrot(), texture,
				0.25F, 0.2F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addMeat(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new meat(), texture, 
				0.25F, 0.2F, 0.0F, 40.0F, 80.0F, 90.0F, -0.2F, 0.2F, 0.3F, -60.0F, 0.0F, 50.0F, scale, inWorldScale));
	}

	public void addCookie(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new cookie(), texture,
				0.2F, 0.3F, 0.1F, 40.0F, 80.0F, 90.0F, 0.0F, 0.0F, 0F, -60.0F, 0.0F, 50.0F, scale, inWorldScale));
	}

	public void addSpiderEye(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new spidereye(), texture,
				0.25F, 0.2F, 0.0F, 40.0F, 80.0F, 90.0F, 0.1F, 0F, -0.5F, 0.0F, 0.0F, -90.0F, scale, inWorldScale));
	}

	public void addNugget(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new nugget(), texture,
				0.25F, 0.2F, 0.0F, 40.0F, 80.0F, 90.0F, 0.1F, 0.0F, 0.1F, -60.0F, 0.0F, 50.0F, scale, inWorldScale));
	}

	public void addDoor(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new door(), texture,
				0.25F, 0.2F, 0.0F, 40.0F, 80.0F, 90.0F, -0.35F, 0.3F, -0.4F, -70.0F, 0.0F, 90.0F, scale, inWorldScale));
	}

	public void addBriquet(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new flintNSteel(), texture,
				0.2F, 0.1F, -0.05F, -70.0F, -90.0F, 20.0F, 0.45F, 0F, -0.4F, -100.0F, 0.0F, -90.0F, scale, inWorldScale));
	}

	public void addStraightStick(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new brod(), texture,
				0.15F, 0.4F, 0F, -70.0F, -90.0F, 20.0F, 0.45F, 0.2F, -0.3F, -100.0F, 0.0F, -110.0F, scale, inWorldScale));
	}
	public void addSign(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new sign(), texture,
				0.25F, 0.2F, 0.0F, 40.0F, 80.0F, 90.0F, -0.2F, 0.4F, 0.1F, -70.0F, 0.0F, 50.0F, scale, inWorldScale));
	}

	public void addPainting(Item item, String texture, float scale, float inWorldScale) {
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new painting(), texture, 
				0.2F, 0.3F, 0.2F, 180.0F, 90.0F, 60.0F, 0.4F, 0.1F, 0.3F, -70.0F, 0.0F, 0.0F, scale, inWorldScale));
	}
	public void addPaper(Item item, String texture, float scale, float inWorldScale) {
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new paper(), texture, 
				0.3F, 0F, 0.1F, 100.0F, 180.0F, 0.0F, 0F, 0.2F, 0.3F, 0.0F, 0.0F, 40.0F, scale, inWorldScale));
	}

	public void addArrow(Item item, String texture, float scale, float inWorldScale) {
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new arrow(), texture, 
				0.4F,0f,0f, 50.0F, -50.0F, 100.0F, 0.5F, 0.5F, 0F, -110.0F, -50.0F, 0.0F, scale, inWorldScale));
	}

	public void addCoal(Item item, String texture, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new coal(), texture,
				0.3F, 0.1F, 0F, 50.0F, 0.0F, 0.0F, -0.1F, 0.1F, 0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}

	public void addSkull(Item item, float scale, float inWorldScale)
	{		
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new head(),"/subaraki/3d/stick.png",
				0.3F, 0.1F, 0F, 0.0F, 120.0F, -40.0F, -0.1F, 0.1F, -0.1F, -10.0F, 50.0F, 1.0F, scale, inWorldScale));
	}

	public void addSpawner(Item item, float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new egg(), "/subaraki/3d/eggSpawn.png", 
				0.2F, 0.1F, -0.1F, 30.0F, 0.0F, 0.0F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F, 0.0F, scale, inWorldScale));
	}
	public void addBow(Item item, String texture,float scale, float inWorldScale)
	{
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new bow(), texture, 
				0.4F, 0.4F, -0.06F, -40.0F, 55.0F, -60.0F, 0.5F, 0F, -0.5F, 0F, 180.0F, 10.0F, scale, inWorldScale));
	}

	public void addPie(Item item, float scale, float inWorldScale, String texture){
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new pie(), texture, 
				0,0,0,0,0,0,0,0,0,0,0,0, scale, inWorldScale));
	}
	
	public void addWheat(Item item, float scale, float inWorldScale, String texture){
		MinecraftForgeClient.registerItemRenderer(item.itemID, new Render3d(new wheat(), texture, 
				0.35f,0.4f,-0.55f,40f,10f,0,0.5f,-0.1f,0.6f,0,0,0, scale, inWorldScale));
	}
}
