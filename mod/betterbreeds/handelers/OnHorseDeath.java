package betterbreeds.handelers;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import betterbreeds.ModBreeds;

public class OnHorseDeath {

	Random rand = new Random();
	
	@ForgeSubscribe
	public void onEntityHurt(LivingHurtEvent event) {

		if (event.entityLiving instanceof EntityHorse)
		{
			if(event.entityLiving.getHealth() - event.ammount <= 0){
				EntityItem entityitem = new EntityItem(event.entityLiving.worldObj, 
						event.entityLiving.posX,event.entityLiving.posY,event.entityLiving.posZ, 
						new ItemStack(ModBreeds.horsemeat));

				float f3 = 0.05F;
				entityitem.motionX = (double)((float)rand.nextGaussian() * f3);
				entityitem.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
				entityitem.motionZ = (double)((float)rand.nextGaussian() * f3);
				event.entityLiving.worldObj.spawnEntityInWorld(entityitem);
			}

		}
	}

}
