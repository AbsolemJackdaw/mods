package petBuddy.handelers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import petBuddy.entity.BatBuddy;
import petBuddy.entity.BlazeBuddy;
import petBuddy.entity.ChickenBuddy;
import petBuddy.entity.CowBuddy;
import petBuddy.entity.CreeperBuddy;
import petBuddy.entity.EnderManBuddy;
import petBuddy.entity.EntityBuddy;
import petBuddy.entity.GhastBuddy;
import petBuddy.entity.GolemBuddy;
import petBuddy.entity.MooshroomBuddy;
import petBuddy.entity.OcelotBuddy;
import petBuddy.entity.PigBuddy;
import petBuddy.entity.PigManBuddy;
import petBuddy.entity.SheepBuddy;
import petBuddy.entity.SilverFishBuddy;
import petBuddy.entity.SkeletonBuddy;
import petBuddy.entity.SkeletonWBuddy;
import petBuddy.entity.SnowManBuddy;
import petBuddy.entity.SpiderBuddy;
import petBuddy.entity.SpiderCaveBuddy;
import petBuddy.entity.SpiderRpgBuddy;
import petBuddy.entity.SquidBuddy;
import petBuddy.entity.VillagerBuddy;
import petBuddy.entity.WitchBuddy;
import petBuddy.entity.WolfBuddy;
import petBuddy.entity.ZombieBuddy;
import petBuddy.root.BuddyBase;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class BuddyPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {	

		if(packet.channel.equals("buddyPet"))
		{
			this.handlePacket(packet, player);
		}
	}

	private void handlePacket(Packet250CustomPayload packet, Player player) {
		EntityPlayer p = (EntityPlayer) player;
		World world = p.worldObj;

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {

			int guiId = dis.readInt();
			BuddyBase myBuddy = null;
			BuddyBase oldBuddy=null;
			List<EntityLiving> entl = p.worldObj.getEntitiesWithinAABB(EntityLiving.class, p.boundingBox.expand(10, 10, 10));
			if (entl != null && entl.size() > 0) {
				for (EntityLiving el : entl) {
					if (el != null && el instanceof BuddyBase){
						if(((BuddyBase)el).getOwnerName().equals(p.username)){
							oldBuddy = (BuddyBase)el;
						}
					}
				}
			}
			if(guiId == 2){
				myBuddy = new PigBuddy(p.worldObj, p);
			}if(guiId == 3){
				myBuddy = new EntityBuddy(p.worldObj, p);
			}if(guiId == 4){
				myBuddy = new CreeperBuddy(p.worldObj, p);
			}if(guiId == 5){
				myBuddy = new CowBuddy(p.worldObj, p);
			}if(guiId == 6){
				myBuddy = new BlazeBuddy(p.worldObj, p);
			}if(guiId == 7){
				myBuddy = new SpiderBuddy(p.worldObj, p);
			}if(guiId == 8){
				myBuddy = new SpiderCaveBuddy(p.worldObj, p);
			}if(guiId == 9){
				myBuddy = new SpiderRpgBuddy(p.worldObj, p);
			}if(guiId == 10){
				myBuddy = new SkeletonBuddy(p.worldObj, p);
			}if(guiId == 11){
				myBuddy = new SkeletonWBuddy(p.worldObj, p);
			}if(guiId == 12){
				myBuddy = new ZombieBuddy(p.worldObj, p);
			}if(guiId == 13){
				myBuddy = new GhastBuddy(p.worldObj, p);
			}if(guiId == 14){
				myBuddy = new SheepBuddy(p.worldObj, p);
			}if(guiId == 15){
				myBuddy = new EnderManBuddy(p.worldObj, p);
			}if(guiId == 16){
				myBuddy = new SilverFishBuddy(p.worldObj, p);
			}if(guiId == 17){
				myBuddy = new SnowManBuddy(p.worldObj, p);
			}if(guiId == 18){
				myBuddy = new GolemBuddy(p.worldObj, p);
			}if(guiId == 19){
				myBuddy = new WitchBuddy(p.worldObj, p);
			}if(guiId == 20){
				myBuddy = new BatBuddy(p.worldObj, p);
			}if(guiId == 21){
				myBuddy = new ChickenBuddy(p.worldObj, p);
			}if(guiId == 22){
				myBuddy = new MooshroomBuddy(p.worldObj, p);
			}if(guiId == 23){
				myBuddy = new OcelotBuddy(p.worldObj, p);
			}if(guiId == 24){
				myBuddy = new SquidBuddy(p.worldObj, p);
			}if(guiId == 25){
				myBuddy = new VillagerBuddy(p.worldObj, p);
			}if(guiId == 26){
				myBuddy = new WolfBuddy(p.worldObj, p);
			}if(guiId == 27){
				myBuddy = new PigManBuddy(p.worldObj, p);
			}

			if(myBuddy != null && oldBuddy != null && oldBuddy.getOwnerName().equals(p.username)){
				myBuddy.setLocationAndAngles(oldBuddy.posX, oldBuddy.posY, oldBuddy.posZ, 0.0F, 0.0F);
				if(!p.worldObj.isRemote){
					p.worldObj.spawnEntityInWorld(myBuddy);
				}
				oldBuddy.setDead();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
