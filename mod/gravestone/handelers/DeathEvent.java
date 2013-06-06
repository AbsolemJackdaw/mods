package gravestone.handelers;

import gravestone.mod_Gravestone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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

				if(!player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
				{
					player.worldObj.getGameRules().setOrCreateGameRule("keepInventory", "true");
					player.inventory.clearInventory(-1, -1);
					player.worldObj.getGameRules().setOrCreateGameRule("keepInventory", "false");

				}else{
					player.inventory.clearInventory(-1, -1);
				}
			}
		}
	}


	private void createPlayerGrave(EntityPlayer player, LivingDeathEvent event) {
		ItemStack[] items = new ItemStack[40];
		System.arraycopy(player.inventory.mainInventory, 0, items, 0, 36);
		System.arraycopy(player.inventory.armorInventory, 0, items, 36, 4);
		player.inventory.clearInventory(-1, -1);
		mod_Gravestone.instance.setStack(items);
	}
}