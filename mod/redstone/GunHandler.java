package redstone;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class GunHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));

		EntityPlayer p = (EntityPlayer)player;
		World world = p.worldObj;

		try {
			EntityLivingBase el = (EntityLiving) world.getEntityByID(dis.readInt());
			if(el != null && !el.isDead)
				el.attackEntityFrom(DamageSource.causePlayerDamage(p), 5);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
