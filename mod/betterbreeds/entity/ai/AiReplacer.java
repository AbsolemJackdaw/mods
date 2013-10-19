package betterbreeds.entity.ai;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITaskEntry;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.WorldServer;
import betterbreeds.BreedsConfig;
import betterbreeds.entity.ai.extended.EntityAIMateChicken;
import betterbreeds.entity.ai.extended.EntityAIMateCow;
import betterbreeds.entity.ai.extended.EntityAIMatePig;
import betterbreeds.entity.ai.extended.EntityAIMateSheep;
import betterbreeds.entity.ai.extended.EntityAIMateWolf;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class AiReplacer implements IScheduledTickHandler {

	@Override
	public int nextTickSpacing() {
		return 10;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		replaceAI();
		breedNaturally();
		
	}

	private void replaceAI(){
		if (FMLCommonHandler.instance().getMinecraftServerInstance().isServerRunning() && FMLCommonHandler.instance().getMinecraftServerInstance().worldServers != null) {
			if(FMLCommonHandler.instance().getEffectiveSide().isServer()){
				for (WorldServer ws : FMLCommonHandler.instance().getMinecraftServerInstance().worldServers) {
					if (ws != null) {
						try{
							for(int i=0;i<ws.getLoadedEntityList().size();i++){

								if(((Entity)ws.getLoadedEntityList().get(i)) instanceof EntityLiving){

									Entity e =(Entity) ws.getLoadedEntityList().get(i);

									if (e != null && e instanceof EntityAgeable){
										EntityAgeable entityageable = (EntityAgeable) e;
										EntityAITaskEntry oldAI = null;

										for (EntityAITaskEntry entityAI : (List<EntityAITaskEntry>) entityageable.tasks.taskEntries){

											if (entityAI.action instanceof EntityAIMate) {
												oldAI = (EntityAITaskEntry) entityAI;

												if (oldAI != null) {
													//only gets called if vanilla animal to prevent over-processing of setting the old ai to a new aiMate
													if(e instanceof EntityWolf || e instanceof EntityPig || e instanceof EntityChicken ||
															e instanceof EntityCow || e instanceof EntitySheep){

														if(e instanceof EntityWolf){
															entityageable.tasks.addTask(oldAI.priority, new EntityAIMateWolf(entityageable, 0.3f));
														}
														else if(e instanceof EntityPig){
															entityageable.tasks.addTask(oldAI.priority, new EntityAIMatePig(entityageable, 0.3f));	
														}
														else if(e instanceof EntityChicken){											
															entityageable.tasks.addTask(oldAI.priority, new EntityAIMateChicken(entityageable, 0.3f));
														}
														else if(e instanceof EntityCow){										
															entityageable.tasks.addTask(oldAI.priority, new EntityAIMateCow(entityageable, 0.3f));
														}
														else if(e instanceof EntitySheep){											
															entityageable.tasks.addTask(oldAI.priority, new EntityAIMateSheep(entityageable, 0.3f));
														}
														entityageable.tasks.taskEntries.remove(oldAI);
														//FMLLog.getLogger().info("AI got replaced for " + e.getEntityName() + " " + e.entityId);
													}
												}							 
											}
										}
									}
								}
							}
						}catch(Throwable en){
						}
					}
				}
			}
		}
	}


	private void breedNaturally(){

		if(BreedsConfig.instance.naturalBreeding){
			if (FMLCommonHandler.instance().getMinecraftServerInstance().isServerRunning() && FMLCommonHandler.instance().getMinecraftServerInstance().worldServers != null) {
				if(FMLCommonHandler.instance().getEffectiveSide().isServer()){
					for (WorldServer ws : FMLCommonHandler.instance().getMinecraftServerInstance().worldServers) {
						if (ws != null) {

							try
							{
								for(int i=0;i<ws.getLoadedEntityList().size();i++)
								{
									if(((Entity)ws.getLoadedEntityList().get(i)) instanceof EntityLiving)
									{
										Entity e =(Entity) ws.getLoadedEntityList().get(i);

										if (e != null && e instanceof EntityAnimal) 
										{
											EntityAnimal animal = (EntityAnimal) e;

											if( !animal.isInLove()){
												/*===== Breeding naturally ! =====*/
												double chanceOfFallingInLove = Math.random();
												if(chanceOfFallingInLove < 0.0004){
													if(animal.getGrowingAge() == 0 && animal.inLove <= 0){
														animal.func_110196_bT();
													}
												}
											}
										}
									}
								}
							}
							catch(Throwable en){
							}
						}
					}
				}
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
