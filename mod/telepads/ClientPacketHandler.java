package telepads;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.util.ArrayList;

import telepads.block.TETelepad;
import telepads.gui.GuiTeleport;
import telepads.util.TelepadWorldData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class ClientPacketHandler extends ServerPacketHandler {

	public static final int IDENTIFIER_NAMEPAD = 5000;
	public static final int IDENTIFIER_TELEPORTER = 5100;
	public static final int IDENTIFIER_REGISTER = 5200;
	public static final int IDENTIFIER_TE = 5300;
	public static final int IDENTIFIER_PLATFORM = 5400;
	public static final int IDENTIFIER_RESETnNOTIFY = 5500;
	

	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event) {

		if(!event.packet.channel().equals(Telepads.channelName))
			return;

		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());
		ByteBuf buf = event.packet.payload();

		World world = p.worldObj;

		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;

		try {

			int packetID = dis.readInt();

			int x2 = dis.readInt();
			int y2 = dis.readInt();
			int z2 = dis.readInt();

			TETelepad pad = (TETelepad) p.worldObj.getTileEntity(x2, y2, z2);

			switch (packetID){

			case IDENTIFIER_GUI :

				int id = dis.readInt();
				p.openGui(Telepads.instance, id, world, x2,y2,z2);
				System.out.println(id);
				break;
				
			case IDENTIFIER_NAMEPAD:
				String name = dis.readUTF();
				String channel = dis.readUTF();

				pad.telepadname = name;
				pad.TELEPORTCHANNEL = channel;

				break;

			case IDENTIFIER_TELEPORTER:

				if(dis.readInt() == GuiTeleport.EXIT_BUTTON){
					pad.resetTE();
					pad.getDescriptionPacket();
					break;
				}else{
					//reset pad BEFORE the player gets teleported, or else it might be out of range and can't be reset
					pad.resetTE();
					pad.getDescriptionPacket();

					int otherPadX = dis.readInt();
					int otherPadY = dis.readInt();
					int otherPadZ = dis.readInt();

					int dimID = dis.readInt();

					//if the dimension id = the End, play endscreen and teleport to spawn point. 
					//this is needed or game will act funny if you don't.
					if( dimID!= p.worldObj.provider.dimensionId){
						if(p.worldObj.provider.dimensionId == 1){
							p.travelToDimension(1);
						}else{
							p.travelToDimension(dimID);
							p.setPositionAndUpdate(otherPadX+2, otherPadY+0.5d, otherPadZ);
						}

					}else{
						p.setPositionAndUpdate(otherPadX+2, otherPadY+0.5d, otherPadZ);
					}
				}

				break;

			case IDENTIFIER_PLATFORM :
				boolean b = dis.readBoolean();

				pad.isStandingOnPlatform = b;

				if(b == false){
					pad.resetTE();
					pad.playerStandingOnPad = null;
				}

				break;

			case IDENTIFIER_RESETnNOTIFY :
				p.addChatMessage(new ChatComponentText(dis.readUTF()));
				pad.resetTE();
				break;

			}

			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
