/*
 ** 2012 August 21
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

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIFlyToConspecific extends EntityAIFlyTo {

    public EntityAIFlyToConspecific(DragonEntity dragon, float speed) {
        super(dragon, speed);
    }
    
    @Override
    public boolean shouldExecute() {
        if (!dragon.isChild()) {
            return false;
        }
        
        List<DragonEntity> nearDragons = dragon.worldObj.getEntitiesWithinAABB(
                dragon.getClass(), dragon.boundingBox.expand(16, 8, 16));
        
        if (nearDragons.isEmpty()) {
            // forever alone
            return false;
        }
        
        DragonEntity followDragon = null;
        double minDist = Double.MAX_VALUE;
        
        for (DragonEntity nearDragon : nearDragons) {
            if (nearDragon.isChild()) {
                continue;
            }
            
            double dist = dragon.getDistanceSqToEntity(nearDragon);

            if (dist < minDist) {
                followDragon = nearDragon;
                minDist = dist;
            }
        }
        
        if (followDragon == null) {
            return false;
        }
        
        target = followDragon;
        
        return super.shouldExecute();
    }
}
