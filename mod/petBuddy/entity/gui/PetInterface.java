package petBuddy.entity.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PetInterface extends GuiScreen {

	private float xSize_lo;
	private float ySize_lo;
	private int xSize = 0;
	private int ySize = 0;
	public static String hi ;
	public EntityPlayer thePlayer;
	public String playerName;
	public static PetInterface inst;
	private GuiTextField textfield;
	private GuiTextField skinfield;

	private  EntityBuddy buddy;
	private  int buddyID;
	private boolean isPlayerCreativeMode;


	public PetInterface(EntityPlayer player, String name, int entityID, boolean creative, Item item, int scale) {
		super();

		thePlayer = player;
		playerName = name;
		inst = this;
		World world = player.worldObj;
		buddyID = entityID;
		buddy= (EntityBuddy)world.getEntityByID(entityID);
		isPlayerCreativeMode = creative;
		String skinOrName = item.equals(Item.leather) ? "skin": "name";
		if(isPlayerCreativeMode)
			hi = "Choose a new Buddy to adventure with you.";
		else
			hi = "Choose a new "+ skinOrName +" for " + buddy.getName();

	}

	@Override
	public void initGui() {
		this.buttonList.clear();

		mc.getMinecraft().gameSettings.guiScale = 2;
//		FMLLog.getLogger().info(""+mc.getMinecraft().gameSettings.guiScale + " " + PetBuddyMain.proxy.getGuiScale());
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		this.buttonList.add(new GuiButton(1, posX+160+50-20 , posY-110 , 20, 20, "X"));

		if(isPlayerCreativeMode){
			this.buttonList.add(new GuiButton(2,  posX-200 , posY-20, 50, 20, "Pig"));
			this.buttonList.add(new GuiButton(3,  posX-200 , posY-50, 50, 20, "Tiny you"));
			this.buttonList.add(new GuiButton(21, posX-200 , posY+10, 50, 20, "Chicken"));
			this.buttonList.add(new GuiButton(5,  posX-200 , posY+40, 50, 20, "Cow"));
			this.buttonList.add(new GuiButton(22, posX-200 , posY+70, 50, 20, "Mooshroom"));

			this.buttonList.add(new GuiButton(14, posX-140 , posY-50, 50, 20, "Sheep"));
			this.buttonList.add(new GuiButton(20, posX-140 , posY-20, 50, 20, "Bat"));
			this.buttonList.add(new GuiButton(17, posX-140 , posY+10, 50, 20, "SnowMan"));
			this.buttonList.add(new GuiButton(18, posX-140 , posY+40, 50, 20, "Iron Golem"));
			this.buttonList.add(new GuiButton(23, posX-140 , posY+70, 50, 20, "Ocelot"));

			this.buttonList.add(new GuiButton(24, posX-80 , posY-50, 50, 20, "Squid"));
			this.buttonList.add(new GuiButton(25, posX-80 , posY-20, 50, 20, "Villager"));

			this.buttonList.add(new GuiButton(26, posX-80 , posY+10,50, 20, "Wolf"));

			this.buttonList.add(new GuiButton(6,  posX-80 , posY+40, 50, 20, "Blaze"));
			this.buttonList.add(new GuiButton(4,  posX-80 , posY+70, 50, 20, "Creeper"));

			this.buttonList.add(new GuiButton(7,  posX-20 , posY-50, 50, 20, "Spider"));			
			this.buttonList.add(new GuiButton(10, posX-20 , posY-20, 50, 20, "Skeleton"));
			this.buttonList.add(new GuiButton(11, posX-20 , posY+10, 50, 20, "W.Skeleton"));
			this.buttonList.add(new GuiButton(12, posX-20 , posY+40, 50, 20, "Zombie"));
			this.buttonList.add(new GuiButton(13, posX-20 , posY+70, 50, 20, "Ghast"));

			this.buttonList.add(new GuiButton(15, posX+40 , posY-50, 50, 20, "EnderMan"));
			this.buttonList.add(new GuiButton(16, posX+40 , posY-20, 50, 20, "SilverFish"));
			this.buttonList.add(new GuiButton(27, posX+40 , posY+10, 50, 20, "Pig Zombie"));
			this.buttonList.add(new GuiButton(30, posX+40 , posY+40, 50, 20, "MagmaCube"));
			this.buttonList.add(new GuiButton(31, posX+40 , posY+70, 50, 20, "Slime"));

			this.buttonList.add(new GuiButton(19, posX+100 , posY-50, 50, 20, "Dragon"));
			this.buttonList.add(new GuiButton(8,  posX+100 , posY-20, 50, 20, "Wither"));
			this.buttonList.add(new GuiButton(28, posX+100 , posY+10, 50, 20, "Rpg Bull"));
			this.buttonList.add(new GuiButton(29, posX+100 , posY+40, 50, 20, "Rpg Boar"));
			this.buttonList.add(new GuiButton(9,  posX+100 , posY+70, 50, 20, "RpgSpider"));

			this.buttonList.add(new GuiButton(32, posX+160 , posY-50, 50, 20, "Harpy"));
			this.buttonList.add(new GuiButton(33, posX+160 , posY-20, 50, 20, "Moa"));
			this.buttonList.add(new GuiButton(34, posX+160 , posY+10, 50, 20, "Dwarf"));
			this.buttonList.add(new GuiButton(35, posX+160 , posY+40, 50, 20, "Elf"));
			this.buttonList.add(new GuiButton(36, posX+160 , posY+70, 50, 20, "Orc"));
		}

		///////////////////////////___PET__NAME_____/////////////////////////////////////////////////
		////BUTTON////
		if(isPlayerCreativeMode)
			this.buttonList.add(new GuiButton(100, posX-200 , posY-110, 70, 20, "Submit name"));
		else if (thePlayer.getCurrentEquippedItem() != null && thePlayer.getCurrentEquippedItem().getItem().equals(Item.stick))
			this.buttonList.add(new GuiButton(100, (posX-posX/2) + 150/2 , posY, 70, 20, "Submit name"));
		////TEXTFIELD////
		if(isPlayerCreativeMode)
			textfield = new GuiTextField(fontRenderer, posX-200 , posY-80, 150, 20);
		else if (thePlayer.getCurrentEquippedItem() != null && thePlayer.getCurrentEquippedItem().getItem().equals(Item.stick))
			textfield = new GuiTextField(fontRenderer, (posX-posX/2) + 70/2 , posY-50, 150, 20);
		///STRING FOR TEXTFIELD///
		String text = buddy.getName().equals("null") ? buddy.getOwnerName() + "'s Buddy" : buddy.getName();
		//SET TEXTFIELD STRING////
		if(textfield != null){
			textfield.setText(text);
			textfield.setMaxStringLength(50);
		}

		///////////////////////////___SKIN__NAME_____////////////////////////////////////////////////
		////BUTTON////
		if(isPlayerCreativeMode)
			this.buttonList.add(new GuiButton(101, posX+80 , posY-110, 70, 20, "Submit Skin"));
		else if (thePlayer.getCurrentEquippedItem() != null && thePlayer.getCurrentEquippedItem().getItem().equals(Item.leather))
			this.buttonList.add(new GuiButton(101, (posX-posX/2) + 150/2 , posY, 70, 20, "Submit Skin"));
		////TEXTFIELD////
		if(isPlayerCreativeMode)
			skinfield = new GuiTextField(fontRenderer, posX , posY-80, 150, 20);
		else if (thePlayer.getCurrentEquippedItem() != null && thePlayer.getCurrentEquippedItem().getItem().equals(Item.leather))
			skinfield = new GuiTextField(fontRenderer, (posX-posX/2) + 70/2 , posY-50, 150, 20);
		///STRING FOR TEXTFIELD///
		String s = buddy.getSkinName();
		String skin = s.equals("null") || s.equals("")|| s.toLowerCase().equals("me") || s.toLowerCase().equals("i") ? buddy.getOwnerName() : buddy.getSkinName();
		//SET TEXTFIELD STRING////
		if(skinfield != null){
			skinfield.setText(buddy.getSkinName());
			skinfield.setMaxStringLength(32);
		}
	}

	public void drawScreen(int par1, int par2, float par3) {
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;

		fontRenderer.drawString("Toggled = "+buddy.toggled, 0, 0, 0xffffff);

		try
		{
			this.mc.renderEngine.func_110581_b(new ResourceLocation("/gui/demo_bg.png"));
			GL11.glColor4f(0.0F, 0.0F, 0.0F, 5.0F);
			int posX = (this.width ) / 2;
			int posY = (this.height) / 2;
			drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
			drawTexturedModalRect(posX*2, posY+5, 0, 90, 45, ySize);
			if(isPlayerCreativeMode){
				fontRenderer.drawSplitString(hi, this.width / 2-105, this.height / 2-110, 175 ,0x000000);
				fontRenderer.drawSplitString(hi, this.width / 2-106, this.height / 2-111, 175 ,0xffffff);
			}else{
				fontRenderer.drawSplitString(hi, (posX-posX/2) + 70/2, this.height / 2-80, 180 ,0x000000);
				fontRenderer.drawSplitString(hi, (posX-posX/2) + 70/2-1, this.height / 2-81, 180 ,0xffffff);
			}

		}
		finally
		{
			if(textfield != null) textfield.drawTextBox();
			if(skinfield != null) skinfield.drawTextBox();

		}
		super.drawScreen(par1, par2, par3);

	}
	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		if(textfield != null) textfield.textboxKeyTyped(c, i);
		if(skinfield != null) skinfield.textboxKeyTyped(c, i);
		if(i == 1){
			mc.gameSettings.guiScale = PetBuddyMain.proxy.getGuiScale();
		}

	}
	protected void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		if(textfield != null) textfield.mouseClicked(i, j, k);
		if(skinfield != null) skinfield.mouseClicked(i, j, k);

	}
	public boolean doesGuiPauseGame() {
		return false;
	}


	@Override
	public void actionPerformed(GuiButton button) {
		if (button.id == 1) 
		{
			mc.thePlayer.closeScreen();
			mc.gameSettings.guiScale = PetBuddyMain.proxy.getGuiScale();
		}
		else if(button.id == 100){
			sendPacket(button.id, buddyID, textfield.getText());
			if(!isPlayerCreativeMode){
				mc.thePlayer.closeScreen();
				mc.gameSettings.guiScale = PetBuddyMain.proxy.getGuiScale();
			}
		}
		else if(button.id == 101){
			sendPacket(button.id, buddyID, skinfield.getText());
			if(!isPlayerCreativeMode){
				mc.thePlayer.closeScreen();
				mc.gameSettings.guiScale = PetBuddyMain.proxy.getGuiScale();
			}
		}
		else{
			sendPacket(button.id, button.id, "Failed to read string");
			mc.thePlayer.closeScreen();
			mc.gameSettings.guiScale = PetBuddyMain.proxy.getGuiScale();
		}
	}

	public void sendPacket(int id, int secondID, String petName){
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutput out;
		DataOutputStream outputStream = new DataOutputStream(bytes);
		try {
			outputStream.writeInt(id);
			outputStream.writeInt(secondID);
			outputStream.writeUTF(petName);
			Packet250CustomPayload packet = new Packet250CustomPayload("buddyPet", bytes.toByteArray());
			PacketDispatcher.sendPacketToServer(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
