package betterbreeds;

//Theses are all the imports you need
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;


//Create a class and implement IPacketHandler
//This just handles the data packets in the server
public class ClientPacketHandler implements IPacketHandler
{
@Override
public void onPacketData(INetworkManager manager,
		Packet250CustomPayload packet, Player player)
{}

}