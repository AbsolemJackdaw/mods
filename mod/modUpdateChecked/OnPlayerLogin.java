package modUpdateChecked;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

public class OnPlayerLogin implements IPlayerTracker{


	private String version;
	private String name;

	public OnPlayerLogin(String modVersion, String modName){
		version = modVersion;
		name = modName;
	}


	@Override
	public void onPlayerLogin(EntityPlayer player) {	
		verifyModVersion(player);
	}


	private void verifyModVersion(EntityPlayer player){
		try {
			URL url = new URL("http://www.dnstechpack.com/user/subaraki/updates.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {

				String[] pairedString = str.split(":");
				if(pairedString[0].equals(name)){
					if(pairedString[1].equals(version)){
//						player.addChatMessage(pairedString[0] + " is up to date");
						break;
					}else{
						player.addChatMessage("a new update is available for " + pairedString[0] + "("+pairedString[1]+")");
						break;
					}
				}
			}
			in.close();
		} catch (MalformedURLException e) {
			FMLLog.getLogger().info("[ERROR] Couldn't Handle Update. Index 1.");
			player.addChatMessage("Could not verify mod version. ");
		} catch (IOException e) {
			FMLLog.getLogger().info("[ERROR] Couldn't Handle Update. Index 2.");
			player.addChatMessage("Could not verify mod version. ");
		}
	}

	/*===============UNUSED==============*/
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