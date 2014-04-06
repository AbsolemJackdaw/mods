package telepads.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import telepads.block.TETelepad;
import telepads.gui.GuiAskChannel;
import telepads.gui.GuiNameTelepad;
import telepads.gui.GuiTeleport;
import cpw.mods.fml.common.network.IGuiHandler;

public class TelePadGuiHandler implements IGuiHandler{

	public static final int TELEPORT = 0;
	public static final int NAMETELEPAD = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}
	

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		TETelepad te = (TETelepad) world.getTileEntity(x, y, z);

		switch (ID) {
		case 0:
			return new GuiTeleport(player, te);
		case 1:
			return new GuiNameTelepad(player, te);
		case 2:
			return new GuiAskChannel(player, te);
		}
		return null;
	}

}
