package gravestone.handelers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 1)
		{
//			return new ContainerGrave(player, (TEGrave)world.getBlockTileEntity(x, y, z));	
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		if(ID == 1)
		{
//			return new GuiGraveChest(player, (TEGrave)world.getBlockTileEntity(x, y, z));
		}
		return null;
	}

}
