package toolbelts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import cpw.mods.fml.common.FMLLog;

public class PlayerBeltTracker implements IExtendedEntityProperties {

	
	public final static String EXT_PROP_NAME = "BeltPropertiesID";

	private static final String tagName = "BeltID";
	
	public int BeltID = 0;
	
	public static final void register(EntityPlayer player){
		player.registerExtendedProperties(EXT_PROP_NAME, new PlayerBeltTracker());
		FMLLog.getLogger().info("Player properties registered" );
	}

	public static final PlayerBeltTracker get(EntityPlayer p){
		return (PlayerBeltTracker) p.getExtendedProperties(EXT_PROP_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setInteger(tagName, BeltID);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		BeltID = compound.getInteger(tagName);
	}

	@Override
	public void init(Entity entity, World world) {
		
	}
}
