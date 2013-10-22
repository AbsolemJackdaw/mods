package telepads;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class TelePadsTeleportHandler implements IPacketHandler {


	public static final int IDENTIFIER_NAMEPAD = 5000;
	public static final int IDENTIFIER_TELPORTER = 5100;
	public static final int IDENTIFIER_REGISTER = 5200;
	public static final int IDENTIFIER_TE = 5300;

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		EntityPlayer p = (EntityPlayer)player;


		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));

		try {

			int guiIdentifier = dis.readInt();
			switch (guiIdentifier) {
			case IDENTIFIER_NAMEPAD:

				int x3 = dis.readInt();
				int y3 = dis.readInt();
				int z3 = dis.readInt();

				TileEntity tile = p.worldObj.getBlockTileEntity(x3, y3, z3);

				String name = dis.readUTF();


				if(p.inventory.hasItem(mod_telepads.padLocator.itemID)){
					for(int i = 0; i < p.inventory.getSizeInventory(); i++){
						if(p.inventory.getStackInSlot(i) != null && p.inventory.getStackInSlot(i).getItem() instanceof ItemPadLocations){
							ItemStack stack = p.inventory.getStackInSlot(i);

							int size = stack.getTagCompound().getInteger(ItemPadLocations.SIZE);
							stack.getTagCompound().setString("TelePadName_"+(size-1), name);

						}
					}
				}
				if(tile != null){
					if(tile instanceof TETelepad){
						TETelepad pad = (TETelepad)tile;
						pad.telepadname = name;
						//pad.allNames.add(name);
						FMLLog.getLogger().info("name is "+name);
						pad.getDescriptionPacket();
						//pad.resetTE();
					}
				}

				break;

			case IDENTIFIER_TELPORTER:
				if(dis.readInt() == 10000){
					int x4 = dis.readInt();
					int y4 = dis.readInt();
					int z4 = dis.readInt();
					TileEntity tile1 = p.worldObj.getBlockTileEntity(x4, y4, z4);
					if(tile1 != null){
						if(tile1 instanceof TETelepad){
							TETelepad pad = (TETelepad)tile1;							
							pad.getDescriptionPacket();
							pad.resetTE();
						}
					}
					break;
				}
				int x2 = dis.readInt();
				int y2 = dis.readInt();
				int z2 = dis.readInt();

				TileEntity te = p.worldObj.getBlockTileEntity(x2, y2, z2);

				if(te != null){
					if(te instanceof TETelepad){
						TETelepad pad = (TETelepad)te;
						pad.resetTE();
						pad.getDescriptionPacket();
					}
				}

				int x = dis.readInt();
				int y = dis.readInt();
				int z = dis.readInt();

				p.setPositionAndUpdate(x+2, y+0.5d, z);
				
				break;

			case IDENTIFIER_REGISTER:
				FMLLog.getLogger().info("recieved");
				ItemStack stack = packet.readItemStack(dis);
				EntityItem item = new EntityItem(p.worldObj, p.posX, p.posY, p.posZ, stack);
				p.worldObj.spawnEntityInWorld(item);
				//				p.inventory.addItemStackToInventory(stack);
				break;

			case IDENTIFIER_TE :

				ItemStack st = packet.readItemStack(dis);

				TETelepad thePad = (TETelepad)p.worldObj.getBlockTileEntity(dis.readInt(), dis.readInt(), dis.readInt());

				thePad.allCoords = new ArrayList<int[]>();
				int size = st.getTagCompound().getInteger(ItemPadLocations.SIZE);

				for(int c =0; c < size; c++){
					int[] ray = new int[3];
					ray[0] = st.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[0];
					ray[1] = st.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[1];
					ray[2] = st.getTagCompound().getIntArray(ItemPadLocations.LOCATION_+c)[2];

					FMLLog.getLogger().info(""+ray);
					thePad.allCoords.add(ray);
					//allNames.add(stack.getTagCompound().getString("TelePadName_"+i));
				}
				break;


			default:
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
