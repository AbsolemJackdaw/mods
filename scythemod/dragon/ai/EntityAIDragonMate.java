/*
 ** 2012 August 26
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.ai;

import java.util.List;

import scythemod.dragon.entity.DragonEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

/**
 * Derivative EntityAIMate class to deal with some special values that can't be
 * applied with an extension thanks to the visibility.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIDragonMate extends EntityAIBase {

    private DragonEntity dragon;
    private DragonEntity dragonMate;
    private World theWorld;
    private int spawnBabyDelay = 0;
    private float moveSpeed;

    public EntityAIDragonMate(DragonEntity dragon, float speed) {
        this.dragon = dragon;
        theWorld = dragon.worldObj;
        moveSpeed = speed;
        setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if (!dragon.isInLove()) {
            return false;
        } else {
            dragonMate = getNearbyMate();
            return dragonMate != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        return dragonMate.isEntityAlive() && dragonMate.isInLove() && spawnBabyDelay < 60;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        dragonMate = null;
        spawnBabyDelay = 0;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        dragon.getLookHelper().setLookPositionWithEntity(dragonMate, 10.0F, (float) dragon.getVerticalFaceSpeed());
        dragon.getNavigator().tryMoveToEntityLiving(dragonMate, moveSpeed);
    }

    /**
     * Loops through nearby animals and finds another animal of the same type
     * that can be mated with. Returns the first valid mate found.
     */
    private DragonEntity getNearbyMate() {
        double range = 12;
        List<Entity> nearbyEntities = theWorld.getEntitiesWithinAABB(DragonEntity.class,
                dragon.boundingBox.expand(range, range, range));
        
        for (Entity entity : nearbyEntities) {
            DragonEntity nearbyDragon = (DragonEntity) entity;
            if (dragon.canMateWith(nearbyDragon)) {
                return nearbyDragon;
            }
        }
        
        return null;
    }
}
