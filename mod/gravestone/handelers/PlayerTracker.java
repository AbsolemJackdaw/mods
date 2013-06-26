package gravestone.handelers;

import gravestone.mod_Gravestone;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerTracker implements IPlayerTracker {


	private int render;

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		try
		{
			this.render = mod_Gravestone.proxy.getRenderID(player.username);
			mod_Gravestone.proxy.setRenderID(player.username, this.render);
			FMLLog.getLogger().info("render id set. render id = " + this.render);
			
		} catch (Throwable e) {
			FMLLog.getLogger().info("render id corrupt. switching to default render ID");
			this.render = 6;
			mod_Gravestone.proxy.setRenderID(player.username, this.render);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		this.render = mod_Gravestone.proxy.getRenderID(player.username);
		mod_Gravestone.proxy.setRenderID(player.username, this.render);
		FMLLog.getLogger().info("render id saved. render id = " + this.render);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		// TODO Auto-generated method stub

	}
}
