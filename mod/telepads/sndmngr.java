package telepads;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLLog;

public class sndmngr {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event)
	{
		try 
		{
			event.manager.addSound("subaraki:telepadShort.ogg");
			event.manager.addSound("subaraki:telepadLong.ogg");

		}
		catch (Exception e)
		{
			FMLLog.getLogger().info("could not load TelePads sound file. please report to mod author !");
		}
	}
}