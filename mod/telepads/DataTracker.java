package telepads;

import telepads.util.TelepadWorldData;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class DataTracker {


	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerChangedDimensionEvent e) {
		TelepadWorldData.get(e.player.worldObj);
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent e) {
		TelepadWorldData.get(e.player.worldObj);
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerLoggedOutEvent e) {
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		TelepadWorldData.get(e.player.worldObj);
	}

}
