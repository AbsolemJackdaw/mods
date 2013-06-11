package gravestone.handelers;

import gravestone.mod_Gravestone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.FMLCommonHandler;

public class DeathEvent {
	@ForgeSubscribe
	public void onEntityLivingUpdate(LivingUpdateEvent event) {

	}

	@ForgeSubscribe
	public void onEntityLivingDeath(LivingDeathEvent event) {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			if (event.entity instanceof EntityPlayer) {
				EntityPlayer player = ((EntityPlayer) event.entity);
				mod_Gravestone.instance.buildGravestone(player,player.inventory);
			}
		}
	}
}