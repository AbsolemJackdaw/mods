package telepads.util;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class sndmngr {
	
	@SubscribeEvent
	public void onSound(SoundLoadEvent event)
	{
		try 
		{
//			event.manager.addSound("subaraki:telepadShort.ogg");
//			event.manager.addSound("subaraki:telepadLong.ogg");

		}
		catch (Exception e)
		{
			FMLLog.getLogger().info("could not load TelePads sound file. please report to mod author !");
		}
	}
}