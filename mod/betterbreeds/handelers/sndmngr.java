package betterbreeds.handelers;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLLog;

public class sndmngr {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event)
	{
		try 
		{
			event.manager.soundPoolSounds.addSound("subaraki:bells.ogg");
			event.manager.soundPoolSounds.addSound("subaraki:song.ogg");
			
		}
		catch (Exception e)
		{
			FMLLog.getLogger().info("could not load Better Breading sound files. please report to mod author !");
		}
	}
}