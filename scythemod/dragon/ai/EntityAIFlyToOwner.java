/*
 ** 2012 May 1
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.ai;

import net.minecraft.entity.EntityLiving;
import scythemod.dragon.entity.DragonEntity;


/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIFlyToOwner extends EntityAIFlyTo {

    public EntityAIFlyToOwner(DragonEntity dragon, float speed) {
        super(dragon, speed);
    }

    @Override
    public boolean shouldExecute() {
        if (dragon.isRiddenByOwner()) {
            return false;
        }
        
        if (dragon.isSitting()) {
            return false;
        }
        
        target = (EntityLiving) dragon.getOwner();
        
        return super.shouldExecute();
    }
}
