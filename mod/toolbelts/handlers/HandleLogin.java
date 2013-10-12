package toolbelts.handlers;

import net.minecraft.entity.player.EntityPlayer;
import toolbelts.PlayerBeltTracker;
import toolbelts.handlers.packets.BeltPacket;
import cpw.mods.fml.common.IPlayerTracker;

/**
 * To catch login/Logouts for reading/writing the Inventory
 *
 * @author AbrarSyed
 */
public class HandleLogin implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		BeltPacket.sendServerPacket(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
	}
}
