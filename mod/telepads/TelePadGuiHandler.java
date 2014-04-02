package telepads;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class TelePadGuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
		case 0:
			return new GuiTeleport(player, (TETelepad) world.getTileEntity(x, y, z));
		case 1:
			return new GuiNameTelepad(player, (TETelepad) world.getTileEntity(x, y, z));
		case 2:
			return new GuiNewRegister(player, (TETelepad) world.getTileEntity(x, y, z));
		}
		return null;
	}

}
