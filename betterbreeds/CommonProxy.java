package betterbreeds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;


public class CommonProxy implements IGuiHandler
{
    public void registerRenderInformation()
    {
    	 
    }
   
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
    public void openGUI(EntityPlayer p1,int id,int wolfid)
    {
    	
    }
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

	public void registerLate() {
		
	}
}
