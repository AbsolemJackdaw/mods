package gravestone.gui;

import gravestone.mod_Gravestone;
import gravestone.grave.ModelGrave;
import gravestone.grave.ModelHead;
import gravestone.grave.te.GraveContainer;
import gravestone.grave.te.TEGrave;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiGrave extends GuiContainer{

	//	private float xSize_lo;
	//	private float ySize_lo;
	//	private int xSize = 0;
	//	private int ySize = 0;

	private short rotationCounter = 0;

	public static String gravetext ;

	public EntityPlayer deathPlayer;
	public EntityPlayer playerOpenGui;

	public String nameOfDeathPlayer;
	public String nameOfPlayerOpeningGui;

	TEGrave te;

	private static ModelGrave grave = new ModelGrave();
	private static ModelHead head = new ModelHead();

	//	int x;
	//	int y;
	//	int z;

	ResourceLocation gravestone = new ResourceLocation("subaraki:grave/gravestone.png");
	ResourceLocation zerk  =new ResourceLocation("subaraki:grave/gravezerk.png");
	ResourceLocation pillar = new ResourceLocation("subaraki:grave/gravepillar.png");
	ResourceLocation wood = new ResourceLocation("subaraki:grave/gravewood.png");
	ResourceLocation angel = new ResourceLocation("subaraki:grave/Angel.png");
	ResourceLocation knight = new ResourceLocation("subaraki:grave/knight.png");
	ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
	ResourceLocation steve = new ResourceLocation("textures/entity/steve.png");;


	public GuiGrave(EntityPlayer player, TEGrave grave ) {
		super(new GraveContainer(player.inventory, grave));

		deathPlayer = player.worldObj.getPlayerEntityByName(grave.playername);
		playerOpenGui = player;

		nameOfDeathPlayer = grave.playername;
		nameOfPlayerOpeningGui = player.username;

		te = grave;

		this.xSize = 198; // size of gui image
		this.ySize = 186;

		if(grave != null)
			if(grave.message1.length() <= 0)
			{
				if(nameOfDeathPlayer.equals("!Empty!"))
				{
					gravetext = "The Grave is empty !";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,true);
				}
				else if(grave.theMeta == 1)
				{
					gravetext = "Here rests " + nameOfDeathPlayer+ ". May he rest in piece.";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 2)
				{
					gravetext = "Our Dearest Friend " + nameOfDeathPlayer+ " rests here. May he rest in piece.";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}else if(grave.theMeta == 3)
				{
					gravetext = "People die, but Hero's Live on. In memorial of " + nameOfDeathPlayer+ ".";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}else if(grave.theMeta == 4)
				{
					gravetext = "Here rests in honored glory "+nameOfDeathPlayer+". You will always be remembered.";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 5)
				{
					gravetext = "In your travels, pause a while to remember "+nameOfDeathPlayer+", who passed away at this spot." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 7)
				{
					gravetext = "Please keep a moment of silence for "+nameOfDeathPlayer+", who died at this very place." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 6)
				{
					gravetext = "In memory of "+nameOfDeathPlayer+" who died here." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 8)
				{
					gravetext = "Ye frail mortals who gaze upon this sight, forget not the fate of "
							+nameOfDeathPlayer+", once mighty, now surrendered to the inescapable grasp of destiny."
							+" Requiescat in pace." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 9)
				{
					gravetext = "Here lies"+nameOfDeathPlayer+", a Knight of First Order. Great in life, glorious in death." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
			}
			else
			{
				gravetext = grave.message1.replace("_"," ") + " "+grave.playername + " "+ grave.message2.replace("_", " ");
				mod_Gravestone.proxy.setCustomNameBoolean(grave,true);
			}
	}

	private static final int BUTTON_CLOSE = 1;
	private static final int BUTTON_ITEMS = 2;
	private static final int BUTTON_SALVAGE = 3;
	private static final int BUTTON_REQUEST = 4;

	//	@Override
	//	public void initGui() {
	//		this.buttonList.clear();
	////
	//		int posX = (this.width - xSize) / 2;
	//		int posY = (this.height - ySize) / 2;
	//		this.buttonList.add(new GuiButton(BUTTON_CLOSE, posX+300 , posY , 20, 20, "X"));
	//
	//	}

	//	public boolean doesGuiPauseGame() {
	//		return false;
	//	}

	//	@Override
	//	public void actionPerformed(GuiButton button) {
	//		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
	////		
	//		if (button.id == BUTTON_CLOSE) 
	//		{
	//			mc.thePlayer.closeScreen();
	//		}
	//	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString(StatCollector.translateToLocal("Grave"), 8, (ySize - 96) + 2, 0xffffff);
		//		fontRenderer.drawString("Mold Forge", 5, (ySize - 180), 0xffffff);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;

		mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/grave_chest.png"));
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


		fontRenderer.drawSplitString(gravetext, this.width / 2+109, this.height / 2-89, 100 ,0x000000);
		fontRenderer.drawSplitString(gravetext, this.width / 2+110, this.height / 2-90, 100 ,0xffffff);

		if(te.locked.length() > 0){
			fontRenderer.drawSplitString(te.locked, this.width / 2+79, this.height / 2+39, 150 ,0x000000);
			fontRenderer.drawSplitString(te.locked, this.width / 2+80, this.height / 2+40, 150 ,0xffffff);
		}

		GL11.glPushMatrix();
		grave.showBasic(false);
		grave.showZerk(false);
		grave.showTomb(false);
		grave.showPillar(false);
		grave.renderSkeleton(false);
		grave.renderCross(false);
		grave.renderAngel(false);
		grave.renderKnight(false);

		switch(te.theMeta)
		{
		case 1:
			grave.showBasic(true);
			this.mc.renderEngine.bindTexture(gravestone);
			break;
		case 2:
			grave.showZerk(true);
			try{
				this.mc.renderEngine.bindTexture(zerk);	
			}catch(Throwable e){}

			break;
		case 3 :
			grave.showTomb(true);
			try{
				this.mc.renderEngine.bindTexture(gravestone);
			}catch(Throwable e){}
			break;
		case 4:
			grave.showPillar(true);
			grave.renderSkeleton(true);
			try{
				this.mc.renderEngine.bindTexture(pillar);
			}catch(Throwable e){}
			break;
		case 5:
			grave.showPillar(true);
			try{
				this.mc.renderEngine.bindTexture(pillar);
			}catch(Throwable e){}

			break;
		case 6:
			grave.renderCross(true);try{
				this.mc.renderEngine.bindTexture(wood);
			}catch(Throwable e){}
			break;
		case 7:
			grave.showPillar(true);
			try{
				this.mc.renderEngine.bindTexture(pillar);
			}catch(Throwable e){}
			break;
		case 8:
			grave.renderAngel(true);
			try{
				this.mc.renderEngine.bindTexture(angel);
			}catch(Throwable e){}
			break;
		case 9:
			grave.renderKnight(true);
			try{
				this.mc.renderEngine.bindTexture(knight);
			}catch(Throwable e){}
			break;
		default :
			grave.showBasic(true);
			grave.showZerk(false);
			grave.showTomb(false);
			grave.showPillar(false);
			grave.renderCross(false);
			grave.renderAngel(false);
			grave.renderKnight(false);
			try{
				this.mc.renderEngine.bindTexture(gravestone);
			}catch(Throwable e){}
			break;
		}
		switch(te.theMeta)
		{
		case 4:
			GL11.glTranslatef(this.width / 2 - 150, this.height / 2 - 40, 40);
			GL11.glScaled(75, 75, -75);
			break;
		case 5:
			GL11.glTranslatef(this.width / 2 - 150, this.height / 2 - 40, 40);
			GL11.glScaled(75, 75, -75);
			break;
		case 7:
			GL11.glTranslatef(this.width / 2 - 150, this.height / 2 - 40, 40);
			GL11.glScaled(75, 75, -75);
			break;
		case 8:
			GL11.glTranslatef(this.width / 2 - 150, this.height / 2 - 40, 40);
			GL11.glTranslatef(-70,0,0f);
			GL11.glScaled(60, 60, -60);
			break;
		case 9:
			GL11.glTranslatef(this.width / 2 - 150, this.height / 2 - 40, 40);
			GL11.glScaled(60, 60, -60);
			break;
		default:
			GL11.glTranslatef(this.width / 2 - 150, this.height / 2 - 80, 40);
			GL11.glScaled(75, 75, -75);
			break;
		}
		GL11.glRotatef((float) 5, 1f, 0f, 0f);
		GL11.glRotatef(rotationCounter++, 0, 1, 0);

		grave.renderModel(0.0625f);
		GL11.glPopMatrix();
		switch(te.theMeta)
		{
		case 5:
			renderBust();
			break;
		default:
			break;
		}
	}


	private void renderBust()
	{
		GL11.glPushMatrix();
		if(playerOpenGui != null)
		{
			grave.showBasic(false);
			grave.showZerk(false);
			grave.showTomb(false);
			grave.showPillar(true);
			grave.renderSkeleton(false);
			grave.renderCross(false);
			try{
				if (nameOfDeathPlayer != null && nameOfDeathPlayer.length() > 0)
				{
					resourcelocation = AbstractClientPlayer.getLocationSkin(nameOfDeathPlayer);
					AbstractClientPlayer.getDownloadImageSkin(resourcelocation, nameOfDeathPlayer);

				}else{
					resourcelocation = steve; 
				}
				Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
			}catch(Throwable e){}

			GL11.glTranslatef(this.width / 2-150, this.height / 2-40, 40);
			GL11.glScaled(50, 50, -50);
			GL11.glRotatef((float) 5, 1f, 0f, 0f);
			GL11.glRotatef(rotationCounter, 0, 1, 0);

			head.renderHead(0.0625f);
		}
		GL11.glPopMatrix();

	}
}
