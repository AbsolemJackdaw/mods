package gravestone.gui;

import gravestone.mod_Gravestone;
import gravestone.grave.ModelGrave;
import gravestone.grave.ModelHead;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiGraveChoice extends GuiScreen {

	private float xSize_lo;
	private float ySize_lo;
	private int xSize = 0;
	private int ySize = 0;
	private short rotationCounter = 0;
	EntityPlayer thePlayer;
	public int render;
	public String price ="";
	private static ModelGrave grave = new ModelGrave();
	private static ModelHead head = new ModelHead();
	public NBTTagCompound nbt = new NBTTagCompound();

	public GuiGraveChoice(EntityPlayer player) {
		super();
		thePlayer = player;
		try{
			nbt = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
		}catch(Throwable e){
			player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
			FMLLog.getLogger().info("Player data could not be read.");
		}
		try{
			render = mod_Gravestone.proxy.getRenderID(player.username);
			sendPacket(render, 3);
		}catch(Throwable e){
			FMLLog.getLogger().info("render id corrupt or missing. Switching to default render ID");
			render = 6;
			sendPacket(render, 3);
		}

	}

	@Override
	// adds buttons to the gui screen
	public void initGui() {
		this.buttonList.clear();
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		this.buttonList.add(new GuiButton(0, posX+150 , posY-100 , 20, 20, "X"));
		this.buttonList.add(new GuiButton(1, posX+90 , posY-70 , 80, 20, "Cross"));
		this.buttonList.add(new GuiButton(2, posX+90 , posY-40 , 80, 20, "Classic Grave"));
		this.buttonList.add(new GuiButton(3, posX+90 , posY-10 , 80, 20, "Tomb"));
		this.buttonList.add(new GuiButton(4, posX+90 , posY+20 , 80, 20, "Pillar"));
		this.buttonList.add(new GuiButton(5, posX+90 , posY+50 , 80, 20, "Bust"));
		this.buttonList.add(new GuiButton(6, posX , posY-70 , 80, 20, "Cheap Cross"));
		this.buttonList.add(new GuiButton(7, posX , posY-40 , 80, 20, "Stele"));
		this.buttonList.add(new GuiButton(10, posX-180 , posY+80 , 80, 20, "Set Grave"));
		//		this.buttonList.add(new GuiButton(11, posX-100 , posY    , 80 ,20, "clear"));
		this.buttonList.add(new GuiButton(8, posX , posY-10 , 80, 20, "Angel"));
		this.buttonList.add(new GuiButton(9, posX , posY+20 , 80, 20, "Knight"));

	}

	public void drawScreen(int par1, int par2, float par3) {
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;
		super.drawScreen(par1, par2, par3);

//		this.mc.renderEngine.bindTexture(new ResourceLocation(("/gui/demo_bg.png")));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 5.0F);
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		fontRenderer.drawSplitString("Choose the Grave you want to spawn when you die.", this.width / 2-84, this.height / 2-100, 150 ,0xffffff);
		fontRenderer.drawSplitString(""+render, this.width / 2 +140, this.height / 2-95, 150 ,0xffffff);

		fontRenderer.drawSplitString(price, this.width / 2 -94, this.height / 2+86, 150 ,0x444444);
		fontRenderer.drawSplitString(price, this.width / 2 -95, this.height / 2+85, 150 ,0xffffff);
		renderGraves();
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	// whenever a button is clicked, this is called.
	// sets the render Integer, the nbt to a new one.
	public void actionPerformed(GuiButton button) {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;

		if(button.id ==11){
			nbt= new NBTTagCompound();
		}if (button.id == 10){
			getPrice();
			buyGrave();
		}else if (button.id == 0){
			mc.thePlayer.closeScreen();
		}else {
			for(int id = 1; id < 10; id++){
				if (button.id == id){
					render =id;	
					getPrice();
				}
			}
		}
	}

	//sends a packet
	public void sendPacket(int ID,int packetNR)
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutput out;
		DataOutputStream outputStream = new DataOutputStream(bytes);
		try {
			outputStream.writeInt(packetNR);
			outputStream.writeInt(ID);
			Packet250CustomPayload packet = new Packet250CustomPayload("graveData", bytes.toByteArray());
			PacketDispatcher.sendPacketToServer(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Sets an integer to an always reseting nbt tag. Shows a message if the grave is already owned.
	//works in one instace only
	public void getPrice(){

		if(nbt.hasKey("shop") && nbt.getInteger("shop") == render)
			price = "Current Grave";
		else
			price = "Grave available";

	}

	// sets an integer to an always reseting nbt tag
	// old name for an old used code. mainly unused
	@Deprecated
	public void buyGrave()
	{
		nbt.setInteger("shop", render);
		mod_Gravestone.proxy.setRenderID(thePlayer.username, render);
		if(nbt.hasKey("shop") && nbt.getInteger("shop") == render)
			price = "Current Grave";
		sendPacket(render, 3);
	}

	public void renderGraves(){
		GL11.glPushMatrix();
		grave.showBasic(false);
		grave.showZerk(false);
		grave.showTomb(false);
		grave.showPillar(false);
		grave.renderSkeleton(false);
		grave.renderCross(false);
		grave.renderAngel(false);
		grave.renderKnight(false);
		switch(render)
		{
		case 1:
			grave.showBasic(true);
			mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/gravestone.png"));
			break;
		case 2:
			grave.showZerk(true);
			try{
				mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/gravezerk.png"));	
			}catch(Throwable e){}

			break;
		case 3 :
			grave.showTomb(true);
			try{
				mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/gravestone.png"));
			}catch(Throwable e){}
			break;
		case 4:
			grave.showPillar(true);
			grave.renderSkeleton(true);
			try{
				mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/gravepillar.png"));
			}catch(Throwable e){}
			break;
		case 5:
			grave.showPillar(true);
			try{
				mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/gravepillar.png"));
			}catch(Throwable e){}

			break;
		case 6:
			grave.renderCross(true);try{
				mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/gravewood.png"));
			}catch(Throwable e){}
			break;
		case 7:
			grave.showPillar(true);
			try{
				mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/gravepillar.png"));
			}catch(Throwable e){}
			break;
		case 8:
			grave.renderAngel(true);
			try{
				mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/Angel.png"));
			}catch(Throwable e){}
			break;
		case 9:
			grave.renderKnight(true);
			try{
				mc.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/knight.png"));
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
				mc.renderEngine.bindTexture(new ResourceLocation("subaraki/gravestone.png"));
			}catch(Throwable e){}
			break;
		}
		switch(render)
		{
		case 4:
			GL11.glTranslatef(width / 2 - 150, height / 2 - 40, 40);
			GL11.glScaled(75, 75, -75);
			break;
		case 5:
			GL11.glTranslatef(width / 2 - 150, height / 2 - 40, 40);
			GL11.glScaled(75, 75, -75);
			break;
		case 7:
			GL11.glTranslatef(width / 2 - 150, height / 2 - 40, 40);
			GL11.glScaled(75, 75, -75);
			break;
		case 8:
			GL11.glTranslatef(width / 2 - 150, height / 2 - 40, 40);
			GL11.glScaled(60, 60, -60);
			break;
		case 9:
			GL11.glTranslatef(width / 2 - 150, height / 2 - 40, 40);
			GL11.glScaled(60, 60, -60);
			break;
		default:
			GL11.glTranslatef(width / 2 - 150, height / 2 - 80, 40);
			GL11.glScaled(75, 75, -75);
			break;
		}
		GL11.glRotatef((float) 5, 1f, 0f, 0f);
		GL11.glRotatef(rotationCounter++, 0, 1, 0);

		grave.renderModel(0.0625f);
		GL11.glPopMatrix();
		switch(render)
		{
		case 5:
			renderBust();
			break;
		default:
			break;
		}
	}

	public void renderBust()
	{
		GL11.glPushMatrix();
		if(thePlayer != null)
		{
			grave.showBasic(false);
			grave.showZerk(false);
			grave.showTomb(false);
			grave.showPillar(true);
			grave.renderSkeleton(false);
			grave.renderCross(false);
			try{
				ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
				if (thePlayer.username != null && thePlayer.username.length() > 0)
				{
					resourcelocation = AbstractClientPlayer.getLocationSkin(thePlayer.username);
					AbstractClientPlayer.getDownloadImageSkin(resourcelocation, thePlayer.username);

				}else{
					resourcelocation = new ResourceLocation( "textures/entity/steve.png");
				}
				Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
			}catch(Throwable e){}

			GL11.glTranslatef(width / 2-150, height / 2-40, 40);
			GL11.glScaled(50, 50, -50);
			GL11.glRotatef((float) 5, 1f, 0f, 0f);
			GL11.glRotatef(rotationCounter, 0, 1, 0);

			head.renderHead(0.0625f);
			GL11.glPopMatrix();
		}
	}
}
