package petBuddy.handelers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.MinecraftForgeClient;
import petBuddy.PetBuddyMain;
import petBuddy.block.PetShrineRender;
import petBuddy.block.TESRShrine;
import petBuddy.block.TEShrine;
import petBuddy.entity.EntityBuddy;
import petBuddy.entity.gui.PetInterface;
import petBuddy.item.render.PetStatueRender;
import petBuddy.root.RenderBuddy;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BuddyClientProxy extends BuddyCommonProxy{

	public static final int originalGuiScale = Minecraft.getMinecraft().gameSettings.guiScale;

	public int getGuiScale() {
		return originalGuiScale;
	}

	public void render() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBuddy.class, new RenderBuddy(0f,0.4f));

		ClientRegistry.registerTileEntity(TEShrine.class, "PetShrine", new TESRShrine());

		MinecraftForgeClient.registerItemRenderer(PetBuddyMain.petStatue.itemID, new PetStatueRender());
		MinecraftForgeClient.registerItemRenderer(PetBuddyMain.shrine.blockID, new PetShrineRender());
	}

	@Override
	public void openGui(int id, EntityPlayer player, String name, int entityID, boolean creative, Item item)
	{
		Minecraft.getMinecraft().displayGuiScreen(new PetInterface(player, name, entityID, creative, item, Minecraft.getMinecraft().gameSettings.guiScale));
	}
}
