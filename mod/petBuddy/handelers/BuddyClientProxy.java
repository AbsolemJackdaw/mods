package petBuddy.handelers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import petBuddy.entity.EntityBuddy;
import petBuddy.entity.gui.PetInterface;
import petBuddy.root.RenderBuddy;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BuddyClientProxy extends BuddyCommonProxy{

	public void render() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBuddy.class, new RenderBuddy(0.3f,0.4f));
	}
	public void openGui(int id, EntityPlayer player, String name)
	{
		Minecraft.getMinecraft().displayGuiScreen(new PetInterface(player, name));
	}
	private int guiID = 3;
	public int getGuiId(){
		return guiID;
	}
	public void setGuiId(int guiId){
		guiID = guiId;
	}

	private float randomColor;
	private float randomColor2;
	private float randomColor3;

	public float getColor(){
		return randomColor ;
	}public float getColor2(){
		return randomColor2 ;
	}public float getColor3(){
		return randomColor3;
	}

	public void setColor(float f, float g, float h){
		randomColor  = f;
		randomColor2 = g;
		randomColor3 = h;
	}

	public NBTTagCompound getLoginPet(String username) {
		NBTTagCompound PETNBT = new NBTTagCompound();
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
					NBTTagCompound tag = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
					if(tag.hasKey("pet_login_id:"+username)) {
						PETNBT.setInteger("pet_login_id:"+username, player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getInteger("pet_login_id:"+username));

					}if(tag.hasKey("pet_color1:"+username)&&tag.hasKey("pet_color2:"+username)&&tag.hasKey("pet_color3:"+username)) {
						PETNBT.setFloat("pet_color1:"+username, tag.getFloat("pet_color1:"+username));
						PETNBT.setFloat("pet_color2:"+username, tag.getFloat("pet_color2:"+username));
						PETNBT.setFloat("pet_color3:"+username, tag.getFloat("pet_color3:"+username));
					}


					return PETNBT;
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username,3);
					PETNBT.setInteger("pet_login_id:"+username, 3);
					PETNBT.setFloat("pet_color1:"+username, 1);
					PETNBT.setFloat("pet_color2:"+username, 1);
					PETNBT.setFloat("pet_color3:"+username, 1);

					return PETNBT;
				}
			}
		}else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
					NBTTagCompound tag = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
					if(tag.hasKey("pet_login_id:"+username)) {
						PETNBT.setInteger("pet_login_id:"+username, player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getInteger("pet_login_id:"+username));

					}if(tag.hasKey("pet_color1:"+username)&&tag.hasKey("pet_color2:"+username)&&tag.hasKey("pet_color3:"+username)) {
						PETNBT.setFloat("pet_color1:"+username, player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getFloat("pet_color1:"+username));
						PETNBT.setFloat("pet_color2:"+username, player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getFloat("pet_color2:"+username));
						PETNBT.setFloat("pet_color3:"+username, player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getFloat("pet_color3:"+username));
					}


					return PETNBT;
				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username,3);
					PETNBT.setInteger("pet_login_id:"+username, 3);
					PETNBT.setFloat("pet_color1:"+username, 1);
					PETNBT.setFloat("pet_color2:"+username, 1);
					PETNBT.setFloat("pet_color3:"+username, 1);

					return PETNBT;
				}
			}
		}
		return PETNBT;
	}

	public void setLoginPet(String username, EntityBuddy buddy) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username, buddy.getGuiId());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color1:"+username, buddy.getColor());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color2:"+username, buddy.getColor2());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color3:"+username, buddy.getColor3());

				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username, 3);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color1:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color2:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color3:"+username, 1);
				}
			}
		}else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username, buddy.getGuiId());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color1:"+username, buddy.getColor());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color2:"+username, buddy.getColor2());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color3:"+username, buddy.getColor3());

				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username, 3);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color1:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color2:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color3:"+username, 1);
				}
			}
		}
	}
}
