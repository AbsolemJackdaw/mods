package toolbelts.handlers;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import toolbelts.handlers.packets.BeltPacket;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CommonTickHandler implements ITickHandler{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

	}
	int countdown = 0;

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		List<EntityPlayer> players = MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).playerEntityList;

		for (EntityPlayer player : players) {
			if (countdown == 0) {
				BeltPacket.sendServerPacket(player);
			}
		}

		countdown--;
		if (countdown < 0) {
			countdown = 20;
		}

	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		return "ToolBelts Packet Sender";
	}

}
