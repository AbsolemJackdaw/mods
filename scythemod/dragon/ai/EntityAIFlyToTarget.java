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

import scythemod.dragon.entity.DragonEntity;


/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIFlyToTarget extends EntityAIFlyTo {

    public EntityAIFlyToTarget(DragonEntity dragon, float speed) {
        super(dragon, speed);
    }

    @Override
    public boolean shouldExecute() {
        if (dragon.getAITarget() == null) {
            return false;
        }
        
        target = dragon.getAITarget();
        
        return super.shouldExecute();
    }
}
