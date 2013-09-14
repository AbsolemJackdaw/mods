package gravestone.handelers;

import gravestone.mod_Gravestone;
import gravestone.bones.ItemBonesRenderer;
import gravestone.bones.TEBones;
import gravestone.bones.TESRBones;
import gravestone.grave.GraveItemRenderer;
import gravestone.grave.te.TEGrave;
import gravestone.grave.te.TEGraveSpecial;
import gravestone.gui.GuiGrave;
import gravestone.gui.GuiGraveChoice;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;

public class ClientProxy extends CommonProxy{

	public void registerRender()
	{
		ClientRegistry.registerTileEntity(TEGrave.class, "GraveStone", new TEGraveSpecial());
		ClientRegistry.registerTileEntity(TEBones.class, "Bones", new TESRBones());
		MinecraftForgeClient.registerItemRenderer(mod_Gravestone.graveItem.itemID, new GraveItemRenderer());
		MinecraftForgeClient.registerItemRenderer(mod_Gravestone.bonesItem.itemID, new ItemBonesRenderer());

		KeyBindingRegistry.registerKeyBinding(new KeyHandler());
	}

	@Deprecated
	public void openGui(int id, EntityPlayer player, String name, TEGrave te)
	{
		if(id == 1)
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiGrave(player, te));
		}
	}
	
	public void openGui(int id, EntityPlayer player, TEGrave te)
	{
		openGui(id, player, "", te);
	}
	
	public void openGui2(int id, EntityPlayer player)
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiGraveChoice(player));

	}
	public void setCustomNameBoolean(TEGrave te, boolean b) {
		te.customName = b;
	}


	public int getRenderID(String username) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).hasKey("renderID_"+username)) {
					return player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getInteger("renderID_"+username);
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("renderID_"+username, 6);
					return 6;
				}
			}
		}else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if (player.getEntityData().hasKey(player.PERSISTED_NBT_TAG) && player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).hasKey("renderID_"+username)) {
					return player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getInteger("renderID_"+username);
				} else {
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("renderID_"+username,6);
					return 6;
				}
			}
		}
		return 0;
	}

	public void setRenderID(String username, int renderID) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("renderID_"+username, renderID);
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("renderID_"+username, 6);
				}
			}
		}else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if (player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("renderID_"+username, renderID);
				} else {
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("renderID_"+username,6);
				}
			}
		}
	}
}