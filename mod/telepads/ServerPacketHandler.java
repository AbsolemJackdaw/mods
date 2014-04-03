package telepads;

import java.util.ArrayList;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class ServerPacketHandler {

	
	public static final int IDENTIFIER_NAMEPAD = 5000;
	public static final int IDENTIFIER_TELEPORTER = 5100;
	public static final int IDENTIFIER_REGISTER = 5200;
	public static final int IDENTIFIER_TE = 5300;
	public static final int IDENTIFIER_PLATFORM = 5400;
	public static final int IDENTIFIER_RESETnNOTIFY = 5500;
	public static final int IDENTIFIER_GUI = 5600;
	
	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {

		if(!event.packet.channel().equals(Telepads.channelName))
			return;

		EntityPlayerMP p = ((NetHandlerPlayServer) event.handler).playerEntity;
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
				break;
				
			case IDENTIFIER_NAMEPAD:

				String name = dis.readUTF();

				if(p.inventory.hasItem(Telepads.padLocator)){
					for(int i = 0; i < p.inventory.getSizeInventory(); i++){
						if(p.inventory.getStackInSlot(i) != null && p.inventory.getStackInSlot(i).getItem() instanceof ItemPadLocations){
							ItemStack stack = p.inventory.getStackInSlot(i);

							int size = stack.getTagCompound().getInteger(ItemPadLocations.SIZE);
							stack.getTagCompound().setString("TelePadName_"+(size-1), name);
						}
					}
				}
				System.out.println(name  + " server " );
				pad.telepadname = name;
				pad.allNames.add(name);
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

			case IDENTIFIER_REGISTER:

				ItemStack stack = new ItemStack(Telepads.padLocator);
				stack.setTagCompound(new NBTTagCompound());

				for(int i = 0; i < pad.allCoords.size(); i++ ){

					stack.getTagCompound().setIntArray(ItemPadLocations.LOCATION_+i, pad.allCoords.get(i));
				}

				for (int t = 0; t < pad.allNames.size(); t++){
					stack.getTagCompound().setString("TelePadName_"+t, pad.allNames.get(t));
				}

				for (int t = 0; t < pad.allDims.size(); t++){
					stack.getTagCompound().setInteger(ItemPadLocations.DIM_+t, pad.allDims.get(t));
				}

				stack.getTagCompound().setInteger(ItemPadLocations.SIZE, pad.allCoords.size());

				EntityItem item = new EntityItem(p.worldObj, p.posX, p.posY, p.posZ, stack);
				p.worldObj.spawnEntityInWorld(item);
				break;

			case IDENTIFIER_TE :

				//TODO 
//				ItemStack stack1 = ByteBufUtils.readItemStack(buf);
//
//				pad.allCoords = new ArrayList<int[]>();
//				pad.allNames = new ArrayList<String>();
//				pad.allDims = new ArrayList<Integer>();
//
//				int size = stack1.getTagCompound().getInteger(ItemPadLocations.SIZE);
//
//				for(int c =0; c < size; c++){
//
//					int[] ray = new int[3];
//					ray[0] = stack1.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[0];
//					ray[1] = stack1.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[1];
//					ray[2] = stack1.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[2];
//
//					String padName = stack1.getTagCompound().getString("TelePadName_"+c);
//					int dim = stack1.getTagCompound().getInteger(ItemPadLocations.DIM_+c);
//					pad.allCoords.add(ray);
//					pad.allNames.add(padName);
//					pad.allDims.add(dim);
//				}
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
