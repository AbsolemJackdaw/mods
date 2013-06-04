package scythemod;


//These are all the imports you will need
import scythemod.block.te.ContainerConverter;
import scythemod.block.te.GuiConverter;
import scythemod.block.te.TileEntityConverter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

//Create a class and implement IGuiHandler
public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){

		if(id == 1){

			return new ContainerConverter(player.inventory, (TileEntityConverter) world.getBlockTileEntity(x, y, z));

		}


		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if(id == 1)
		{
			return new GuiConverter(player.inventory, (TileEntityConverter)world.getBlockTileEntity(x, y, z));
		}
		return null;
	}
}
