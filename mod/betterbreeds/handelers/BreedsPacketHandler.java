package betterbreeds.handelers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import betterbreeds.ModBreeds;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class BreedsPacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {


		EntityPlayer par3EntityPlayer = (EntityPlayer)player;

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));

		ItemStack par1ItemStack;
		try {
			par1ItemStack = Packet.readItemStack(dis);
			Entity entityID = par3EntityPlayer.worldObj.getEntityByID(dis.readInt());

			if(par1ItemStack.getTagCompound() == null)
				par1ItemStack.setTagCompound(new NBTTagCompound());

			if(par1ItemStack.getTagCompound().hasKey(ModBreeds.tag) && (!par1ItemStack.getTagCompound().getString(ModBreeds.tag).equals("none"))){
				par3EntityPlayer.addChatMessage("Pet trap still has a  " + EntityList.getEntityString(entityID) + " inside !");
			}else{
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString(ModBreeds.tag, EntityList.getEntityString(entityID));
				par1ItemStack.setTagCompound(nbt);
				entityID.setDead();
				par3EntityPlayer.setCurrentItemOrArmor(0, par1ItemStack);
				if(!par3EntityPlayer.worldObj.isRemote)
					par3EntityPlayer.addChatMessage("Stored a " + EntityList.getEntityString(entityID)+ " in the Animal trap");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
