package betterbreeds;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITaskEntry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.Event.Result;
import betterbreeds.entity.ai.extended.EntityAIMateChicken;
import betterbreeds.entity.ai.extended.EntityAIMateCow;
import betterbreeds.entity.ai.extended.EntityAIMatePig;
import betterbreeds.entity.ai.extended.EntityAIMateSheep;
import betterbreeds.entity.ai.extended.EntityAIMateWolf;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class AiReplacer implements IScheduledTickHandler {

	@Override
	public int nextTickSpacing() {
		return 10;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		//nothing
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		//
		if (FMLCommonHandler.instance().getMinecraftServerInstance().isServerRunning() && FMLCommonHandler.instance().getMinecraftServerInstance().worldServers != null) {
			if(FMLCommonHandler.instance().getEffectiveSide().isServer()){
				for (WorldServer ws : FMLCommonHandler.instance().getMinecraftServerInstance().worldServers) {
					if (ws != null) {
						Entity e = null;
						EntityItem breedingItem =null;
						try
						{
							for(int i=0;i<ws.getLoadedEntityList().size();i++)
							{
								if(((Entity)ws.getLoadedEntityList().get(i)) instanceof EntityLiving)
								{
									e =(Entity) ws.getLoadedEntityList().get(i);
								}
								if(((Entity)ws.getLoadedEntityList().get(i)) instanceof EntityItem)
								{
									breedingItem =(EntityItem) ws.getLoadedEntityList().get(i);
								}
							}
						}
						catch(Throwable en)
						{

						}
						
//						if(e != null && breedingItem != null){
//
//							if(e instanceof EntityPig && ((EntityPig) e).isBreedingItem(breedingItem.getEntityItem()))
//							{
//								FMLLog.getLogger().info(""+e+" "+breedingItem);
//								if(((EntityPig) e).inLove == 0){
//									this.feedItem((EntityPig)e,breedingItem);
//								}
//							}
//						}
						if (e != null && e instanceof EntityAgeable) 
						{
							EntityAgeable entityageable = (EntityAgeable) e;
							EntityAITaskEntry oldAI = null;

							for (EntityAITaskEntry entityAI : (List<EntityAITaskEntry>) entityageable.tasks.taskEntries) {
								if (entityAI.action instanceof EntityAIMate ) {
									oldAI = (EntityAITaskEntry) entityAI;
								}
							}

							if (oldAI != null) {

								if(e instanceof EntityWolf)
								{
									entityageable.tasks.addTask(oldAI.priority, new EntityAIMateWolf(entityageable, 0.3f));
								}
								if(e instanceof EntityPig)
								{
									entityageable.tasks.addTask(oldAI.priority, new EntityAIMatePig(entityageable, 0.3f));
								}
								if(e instanceof EntityChicken)
								{
									entityageable.tasks.addTask(oldAI.priority, new EntityAIMateChicken(entityageable, 0.3f));
								}
								if(e instanceof EntityCow)
								{
									entityageable.tasks.addTask(oldAI.priority, new EntityAIMateCow(entityageable, 0.3f));
								}
								if(e instanceof EntitySheep)
								{
									entityageable.tasks.addTask(oldAI.priority, new EntityAIMateSheep(entityageable, 0.3f));
								}
								else
								{
									entityageable.tasks.addTask(oldAI.priority, new EntityAIMate((EntityAnimal)entityageable, 0.3f));
								}

								entityageable.tasks.taskEntries.remove(oldAI);
							}							 
						}
					}
				}
			}
		}
	}


	public void feedItem(EntityAnimal ent, EntityItem item)
	{
		if (!ent.worldObj.isRemote)
		{
			if (item.delayBeforeCanPickup > 0)
			{
				return;
			}

			ItemStack itemstack = item.getEntityItem();
			int i = itemstack.stackSize;

			if (item.delayBeforeCanPickup <= 0 || i <= 0 )
			{
				Random rand = new Random();

				item.playSound("random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);

				if (itemstack.stackSize <= 0)
				{
					item.setDead();
				}
				ent.inLove = 600;
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return "AIReplace";
	}
}
