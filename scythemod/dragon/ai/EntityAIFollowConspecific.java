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

import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.passive.EntityAnimal;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIFollowConspecific extends EntityAIFollowParent {

    public EntityAIFollowConspecific(EntityAnimal entity, float maxDist) {
        super(entity, maxDist);
        setMutexBits(3);
    }
}
