package scythemod;


//Theses are all the imports you need
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.TcpConnection;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;


//Create a class and implement IPacketHandler
//This just handles the data packets in the server
public class ClientPacketHandler implements IPacketHandler
{
public void onPacketData(TcpConnection manager, Packet250CustomPayload payload, Player player)
{
DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
}

@Override
public void onPacketData(INetworkManager manager,
		Packet250CustomPayload packet, Player player)
{}

}