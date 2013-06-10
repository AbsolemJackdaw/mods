package offlineSkin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class SkinAplyer implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		try {
			DownloadSkin skin = new DownloadSkin(new File(Minecraft.getMinecraftDir().getAbsolutePath()),new URL(player.skinUrl));
			skin.download();
			try{
				
			}catch(Throwable e){

			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {

	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {

	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {

	}

}
