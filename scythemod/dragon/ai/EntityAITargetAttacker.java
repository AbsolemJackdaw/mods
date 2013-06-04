/*
 ** 2012 August 19
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIHurtByTarget;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAITargetAttacker extends EntityAIHurtByTarget  {
    public EntityAITargetAttacker(EntityLiving entity, boolean par2) {
        super(entity, par2);
        targetDistance = 48;
    }
}
