package gravestone.gui;

import gravestone.mod_Gravestone;
import gravestone.grave.ModelGrave;
import gravestone.grave.ModelHead;
import gravestone.grave.TEGrave;
import gravestone.handelers.PacketHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.StringUtils;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiGrave extends GuiScreen {

	private float xSize_lo;
	private float ySize_lo;
	private int xSize = 0;
	private int ySize = 0;
	private short rotationCounter = 0;
	public static String hi ;
	public static String hi2;
	public EntityPlayer thePlayer;
	public String playerName;
	private static ModelGrave grave = new ModelGrave();
	private static ModelHead head = new ModelHead();
	private int clicks;
	TEGrave te;

	int x;
	int y;
	int z;

	public GuiGrave(EntityPlayer player, String name, TEGrave grave ) {
		super();
		clicks =0;
		x = PacketHandler.instance.x1;
		y = PacketHandler.instance.y1;
		z = PacketHandler.instance.z1;

		if(grave != null)
			if(grave.message1.length() <= 0)
			{
				if(name.equals("!Empty!"))
				{
					hi = "The Grave is empty !";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,true);
				}
				else if(grave.theMeta == 1)
				{
					hi = "Here rests " + name+ ". May he rest in piece.";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 2)
				{
					hi = "Our Dearest Friend " + name+ " rests here. May he rest in piece.";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}else if(grave.theMeta == 3)
				{
					hi = "People die, but Hero's Live on. In memorial of " + name+ ".";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}else if(grave.theMeta == 4)
				{
					hi = "Here rests in honored glory "+name+". You will always be remembered.";
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 5)
				{
					hi = "In your travels, pause awhile to remember "+name+", who passed away at this spot." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 7)
				{
					hi = "Please keep a moment of silence for "+name+", who died at this very place." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 6)
				{
					hi = "In memory of "+name+" who died here." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 8)
				{
					hi = "Ye frail mortals who gaze upon this sight, forget not the fate of "
							+name+", once mighty, now surrendered to the inescapable grasp of destiny."
							+"Requiescat in pace." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
				else if(grave.theMeta == 9)
				{
					hi = "Here lies"+name+", a Knight of First Order. Great in life, glorious in death." ;
					mod_Gravestone.proxy.setCustomNameBoolean(grave,false);
				}
			}
			else
			{
				hi = grave.message1.replace("_"," ") + " "+grave.playername + " "+ grave.message2.replace("_", " ");
				mod_Gravestone.proxy.setCustomNameBoolean(grave,true);
			}

		hi2 = hi;
		thePlayer = player;
		playerName = name;
		te = grave;
	}

	@Override
	public void initGui() {
		this.buttonList.clear();

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		this.buttonList.add(new GuiButton(1, posX+150 , posY-100 , 20, 20, "X"));

		if(te != null){
			if(thePlayer.username.equals(playerName))
			{
				if(te.hasItems){
					this.buttonList.add(new GuiButton(2, posX+110 , posY , 60, 20, "Get Items"));
				}else{
					this.buttonList.add(new GuiButton(3, posX+90 , posY+50 , 80, 20, "Salvage Grave"));
				}
			}
			if(!thePlayer.username.equals(playerName)){
				this.buttonList.add(new GuiButton(4, posX+90 , posY+30 , 100, 20, "Request to Remove"));
				if(te.customName == true){
					this.buttonList.add(new GuiButton(3, posX+90 , posY+30 , 100, 20, "Salvage Grave"));
				}
			}
		}
	}

	public boolean hasItems()
	{
		if(te != null){
			for( int i =0; i < 36; i++){
				try{
					ItemStack stack = te.getStackInSlot(i);
					if(stack.getItem() != null){
						return true;
					}
				}catch(Throwable e){
					return false;
				}
			}
		}
		return false;
	}

	public void drawScreen(int par1, int par2, float par3) {
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;
		super.drawScreen(par1, par2, par3);

		try
		{
			this.mc.renderEngine.func_110577_a(new ResourceLocation("/gui/demo_bg.png"));
			GL11.glColor4f(0.0F, 0.0F, 0.0F, 5.0F);
			int posX = (this.width - xSize) / 2;
			int posY = (this.height - ySize) / 2;
			drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
			drawTexturedModalRect(posX*2, posY+5, 0, 90, 45, ySize);
			fontRenderer.drawSplitString(hi, this.width / 2-49, this.height / 2-69, 150 ,0x000000);
			fontRenderer.drawSplitString(hi, this.width / 2-50, this.height / 2-70, 150 ,0xffffff);
		}
		catch (Throwable e){
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
			this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/gravestone.png"));
			break;
		case 2:
			grave.showZerk(true);
			try{
				this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/gravezerk.png"));	
			}catch(Throwable e){}

			break;
		case 3 :
			grave.showTomb(true);
			try{
				this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/gravestone.png"));
			}catch(Throwable e){}
			break;
		case 4:
			grave.showPillar(true);
			grave.renderSkeleton(true);
			try{
				this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/gravepillar.png"));
			}catch(Throwable e){}
			break;
		case 5:
			grave.showPillar(true);
			try{
				this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/gravepillar.png"));
			}catch(Throwable e){}

			break;
		case 6:
			grave.renderCross(true);try{
				this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/gravewood.png"));
			}catch(Throwable e){}
			break;
		case 7:
			grave.showPillar(true);
			try{
				this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/gravepillar.png"));
			}catch(Throwable e){}
			break;
		case 8:
			grave.renderAngel(true);
			try{
				this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/Angel.png"));
			}catch(Throwable e){}
			break;
		case 9:
			grave.renderKnight(true);
			try{
				this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/knight.png"));
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
				this.mc.renderEngine.func_110577_a(new ResourceLocation("subaraki:grave/gravestone.png"));
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
				ResourceLocation resourcelocation = AbstractClientPlayer.field_110314_b;
				if (playerName != null && playerName.length() > 0)
				{
					resourcelocation = AbstractClientPlayer.func_110305_h(playerName);
					AbstractClientPlayer.func_110304_a(resourcelocation, playerName);

				}else{
					resourcelocation = new ResourceLocation( "textures/entity/steve.png");
				}
				Minecraft.getMinecraft().renderEngine.func_110577_a(resourcelocation);
			}catch(Throwable e){}

			GL11.glTranslatef(this.width / 2-150, this.height / 2-40, 40);
			GL11.glScaled(50, 50, -50);
			GL11.glRotatef((float) 5, 1f, 0f, 0f);
			GL11.glRotatef(rotationCounter, 0, 1, 0);

			head.renderHead(0.0625f);
			GL11.glPopMatrix();
		}
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton button) {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		if (button.id == 2) 
		{
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ObjectOutput out;
			DataOutputStream outputStream = new DataOutputStream(bytes);
			try {
				outputStream.writeInt(1);
				outputStream.writeInt(te.xCoord);
				outputStream.writeInt(te.yCoord);
				outputStream.writeInt(te.zCoord);
				outputStream.writeInt(6);
				Packet250CustomPayload packet = new Packet250CustomPayload("graveData", bytes.toByteArray());
				PacketDispatcher.sendPacketToServer(packet);
				thePlayer.closeScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
			te.hasItems = false;
		} 
		if (button.id == 3) 
		{
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ObjectOutput out;
			DataOutputStream outputStream = new DataOutputStream(bytes);
			try {
				outputStream.writeInt(2);
				outputStream.writeInt(te.xCoord);
				outputStream.writeInt(te.yCoord);
				outputStream.writeInt(te.zCoord);
				outputStream.writeInt(7);
				Packet250CustomPayload packet = new Packet250CustomPayload("graveData", bytes.toByteArray());
				PacketDispatcher.sendPacketToServer(packet);
				thePlayer.closeScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		if (button.id == 1) 
		{
			mc.thePlayer.closeScreen();
		}
		if (button.id == 4) 
		{
			if(clicks == 0){
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				ObjectOutput out;
				DataOutputStream outputStream = new DataOutputStream(bytes);
				try {
					outputStream.writeInt(4);
					outputStream.writeInt(te.xCoord);
					outputStream.writeInt(te.yCoord);
					outputStream.writeInt(te.zCoord);
					outputStream.writeUTF(thePlayer.username);
					Packet250CustomPayload packet = new Packet250CustomPayload("graveData", bytes.toByteArray());
					PacketDispatcher.sendPacketToServer(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
				clicks = 100;
			}

			else if(clicks <= 100){
				clicks-=1;
			}

		}
	}
}
