package gravestone.handelers;

import gravestone.ConfigClass;
import gravestone.mod_Gravestone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class DeathEvent {

	@ForgeSubscribe
	public void onEntityLivingDeath(LivingDeathEvent event) {

		if(ConfigClass.instance.onDeathEvent){
			if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
				if (event.entity instanceof EntityPlayer) {
					EntityPlayer player = ((EntityPlayer) event.entity);
					mod_Gravestone.instance.buildGravestone(player,player.inventory);
				}
			}
		}else{
			if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
				if (event.entity instanceof EntityPlayer) {
					EntityPlayer player = ((EntityPlayer) event.entity);
					FMLLog.getLogger().info("[GraveStoneMod INFO] "+ "~!!!WARNING!!!~ "+player.username +"/ "+ player
							+" died.");
					FMLLog.getLogger().info("[GraveStoneMod INFO] "+ "WarningLevel: ~Normal~ "+"Spawning graves was disabled by Config.");
				}
			}
		}
	}
}