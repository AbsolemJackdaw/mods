package charms;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.FMLLog;

public class CharmRes {



	@ForgeSubscribe
	public void onPlayerHurt(LivingHurtEvent event) {

		if (event.entityLiving instanceof EntityPlayer && event.entityLiving.getHealth() - event.ammount <= 0)
		{
			EntityPlayer p = (EntityPlayer)event.entityLiving;
			ItemStack stack = p.inventory.mainInventory[ConfigClass.instance.slotID];

			if(stack != null && stack.getItem() instanceof Charm)
			{
				Charm charm = (Charm)stack.getItem();
				int hearts = p.getHealth();
				int maxHearts = p.getMaxHealth();

				if(charm.cooldown == 30*20){
					//if the charm has less hearts to heal then the player has actual health 
					//> case can be for noHero's more health mod
					//example for 30 hearts and iron charm. 
					if(hearts > charm.heartsToHeal){ //30-15 > 10
						p.heal(-hearts +charm.heartsToHeal);     //heal 10
						stack.damageItem(charm.heartsToHeal, p); //damage item 10 > meaning it would deplete it immediatly
						p.renderBrokenItemStack(stack);    //rendering.
						p.inventory.setInventorySlotContents(ConfigClass.instance.slotID, (ItemStack) null); //making sure it's gone.
					}
					//if the charm has less uses left then the player needs to heal hearts.
					//example> fall down and take 8 hearts of damage with golden charm that has 5 charges left
					else if(hearts > (charm.heartsToHeal - stack.getItemDamage())){ //8 > 5
						p.heal(- hearts + (charm.heartsToHeal - stack.getItemDamage())); 		  //heal 5
						stack.damageItem((charm.heartsToHeal - stack.getItemDamage()), p); //damage item 5
						//again, this would mean it breaks immediatly. making sure it does.
						p.renderBrokenItemStack(stack);    //rendering.
						p.inventory.setInventorySlotContents(ConfigClass.instance.slotID, (ItemStack) null); //making sure it's gone.
					}
					//in any other cases, use the health difference to heal.
					else{
						p.heal(maxHearts);
						stack.damageItem(maxHearts, p);
						charm.cooldown = 0;
					}
				}
			}
			FMLLog.getLogger().info(event.entityLiving.getEntityName() + " got reseructed.");
			event.setCanceled(true);
		}
	}

}
