/*
 ** 2012 April 22
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.ai;

import scythemod.dragon.entity.DragonEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIWatchLiving extends EntityAIBase {

    private DragonEntity dragon;
    private Entity watchedEntity;
    private float maxDist;
    private int watchTicks;
    private float watchChance = 0.02f;

    public EntityAIWatchLiving(DragonEntity dragon, float maxDist) {
        this.dragon = dragon;
        this.maxDist = maxDist;
        setMutexBits(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if (dragon.getRNG().nextFloat() >= watchChance) {
            return false;
        }
        
        watchedEntity = null;
        
        if (watchedEntity == null) {
            AxisAlignedBB aabb = dragon.boundingBox.expand(maxDist, dragon.height, maxDist);
            Class clazz = EntityLiving.class;
            watchedEntity = dragon.worldObj.findNearestEntityWithinAABB(clazz, aabb, dragon);
        }
        
        // don't try to look at the rider when being ridden
        if (watchedEntity != null && watchedEntity == dragon.getRider()) {
            watchedEntity = null;
        }

        return watchedEntity != null;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        if (!watchedEntity.isEntityAlive()) {
            return false;
        }

        if (dragon.getDistanceSqToEntity(watchedEntity) > maxDist * maxDist) {
            return false;
        } else {
            return watchTicks > 0;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        watchTicks = 40 + dragon.getRNG().nextInt(40);
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        watchedEntity = null;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        double lx = watchedEntity.posX;
        double ly = watchedEntity.posY + watchedEntity.getEyeHeight();
        double lz = watchedEntity.posZ;
        dragon.getLookHelper().setLookPosition(lx, ly, lz, 10, dragon.getVerticalFaceSpeed());
        watchTicks--;
    }
}
