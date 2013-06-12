package petBuddy.handelers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import petBuddy.entity.gui.PetInterface;
import petBuddy.root.RenderBuddy;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BuddyClientProxy extends BuddyCommonProxy{

	public void render() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBuddy.class, new RenderBuddy(0f,0.4f));
	}

	@Override
	public void openGui(int id, EntityPlayer player, String name, int entityID, boolean creative)
	{
		Minecraft.getMinecraft().displayGuiScreen(new PetInterface(player, name, entityID, creative));
	}
	
	String name = "null";
	public String getName(){
		return name;
	}
	public void setName(String n){
		name = n;
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

	private float randomDragonColor;
	private float randomDragonColor2;
	private float randomDragonColor3;

	public float getDragonColor(){
		return randomDragonColor ;
	}public float getDragonColor2(){
		return randomDragonColor2 ;
	}public float getDragonColor3(){
		return randomDragonColor3;
	}

	public void setDragonColor(float f, float g, float h){
		randomDragonColor  = f;
		randomDragonColor2 = g;
		randomDragonColor3 = h;
	}
	
	public NBTTagCompound getLoginPet(String username) {
		NBTTagCompound PETNBT = new NBTTagCompound();
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().getConfigurationManager() != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
					NBTTagCompound tag = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
					if(tag.hasKey("pet_login_id:"+username)) {
						PETNBT.setInteger("pet_login_id:"+username, tag.getInteger("pet_login_id:"+username));

					}if(tag.hasKey("pet_color1:"+username)&&tag.hasKey("pet_color2:"+username)&&tag.hasKey("pet_color3:"+username)) {
						PETNBT.setFloat("pet_color1:"+username, tag.getFloat("pet_color1:"+username));
						PETNBT.setFloat("pet_color2:"+username, tag.getFloat("pet_color2:"+username));
						PETNBT.setFloat("pet_color3:"+username, tag.getFloat("pet_color3:"+username));
					}if(tag.hasKey("pet_dragon_color1:"+username)&&tag.hasKey("pet_dragon_color2:"+username)&&tag.hasKey("pet_dragon_color3:"+username)) {
						PETNBT.setFloat("pet_dragon_color1:"+username, tag.getFloat("pet_dragon_color1:"+username));
						PETNBT.setFloat("pet_dragon_color2:"+username, tag.getFloat("pet_dragon_color2:"+username));
						PETNBT.setFloat("pet_dragon_color3:"+username, tag.getFloat("pet_dragon_color3:"+username));
					}

					if(tag.hasKey("pet_name:"+username)) {
						PETNBT.setString("pet_name:"+username, tag.getString("pet_name:"+username));
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
					PETNBT.setFloat("pet_dragon_color1:"+username, 0);
					PETNBT.setFloat("pet_dragon_color2:"+username, 0);
					PETNBT.setFloat("pet_dragon_color3:"+username, 0);
					PETNBT.setString("pet_name:"+username, "null");
					return PETNBT;
				}
			}
		}else{
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username);
			if (player != null) {
				if(player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
					NBTTagCompound tag = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
					if(tag.hasKey("pet_login_id:"+username)) {
						PETNBT.setInteger("pet_login_id:"+username, tag.getInteger("pet_login_id:"+username));

					}if(tag.hasKey("pet_color1:"+username)&&tag.hasKey("pet_color2:"+username)&&tag.hasKey("pet_color3:"+username)) {
						PETNBT.setFloat("pet_color1:"+username, tag.getFloat("pet_color1:"+username));
						PETNBT.setFloat("pet_color2:"+username, tag.getFloat("pet_color2:"+username));
						PETNBT.setFloat("pet_color3:"+username, tag.getFloat("pet_color3:"+username));
					}if(tag.hasKey("pet_dragon_color1:"+username)&&tag.hasKey("pet_dragon_color2:"+username)&&tag.hasKey("pet_dragon_color3:"+username)) {
						PETNBT.setFloat("pet_dragon_color1:"+username, tag.getFloat("pet_dragon_color1:"+username));
						PETNBT.setFloat("pet_dragon_color2:"+username, tag.getFloat("pet_dragon_color2:"+username));
						PETNBT.setFloat("pet_dragon_color3:"+username, tag.getFloat("pet_dragon_color3:"+username));
					}
					if(tag.hasKey("pet_name:"+username)) {
						PETNBT.setString("pet_name:"+username, tag.getString("pet_name:"+username));
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
					PETNBT.setFloat("pet_dragon_color1:"+username, 0);
					PETNBT.setFloat("pet_dragon_color2:"+username, 0);
					PETNBT.setFloat("pet_dragon_color3:"+username, 0);
					PETNBT.setString("pet_name:"+username, "null");
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
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color1:"+username, buddy.getColor());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color2:"+username, buddy.getColor2());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color3:"+username, buddy.getColor3());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setString("pet_name:"+username, buddy.getName());

				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username, 3);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color1:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color2:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color3:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color1:"+username, 0);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color2:"+username, 0);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color3:"+username, 0);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setString("pet_name:"+username, "null");

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
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color1:"+username, buddy.getColor());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color2:"+username, buddy.getColor2());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color3:"+username, buddy.getColor3());
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setString("pet_name:"+username, buddy.getName());

				}else{
					if (!player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)){
						player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound(player.PERSISTED_NBT_TAG));
					}
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setInteger("pet_login_id:"+username, 3);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color1:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color2:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_color3:"+username, 1);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color1:"+username, 0);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color2:"+username, 0);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setFloat("pet_dragon_color3:"+username, 0);
					player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).setString("pet_name:"+username, "null");

				}
			}
		}
	}
}
