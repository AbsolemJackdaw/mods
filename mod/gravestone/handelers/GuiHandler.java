package gravestone.handelers;

import gravestone.grave.te.GraveContainer;
import gravestone.grave.te.TEGrave;
import gravestone.gui.GuiGrave;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		return new GraveContainer(player.inventory, (TEGrave)player.worldObj.getBlockTileEntity(x, y, z));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		return new GuiGrave(player, (TEGrave)player.worldObj.getBlockTileEntity(x, y, z));
	}

}
