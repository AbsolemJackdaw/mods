/*
 ** 2012 April 20
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.ai;

import scythemod.dragon.entity.DragonEntity;
import net.minecraft.entity.ai.EntityAIBase;

/**
 * AI controller for autonomous liftoffs.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAILiftOff extends EntityAIBase {
    
    private final DragonEntity dragon;
    private float chance;
    private int ticks;

    public EntityAILiftOff(DragonEntity dragon, float chance) {
        this.dragon = dragon;
        this.chance = chance;
    }
    
    @Override
    public boolean isInterruptible() {
        return false;
    }

    @Override
    public boolean shouldExecute() {
        // check every second, not every tick
        if (--ticks > 0) {
            return false;
        }
        ticks = 20;
        
        // don't lift off when the dragon is engaged or tamed
        if (dragon.isTamed() || dragon.getAITarget() != null || dragon.getAttackTarget() != null) {
            return false;
        }
        
        return dragon.getRNG().nextFloat() < chance;
    }

    @Override
    public void startExecuting() {
        dragon.getJumpHelper().setJumping();
    }

    public float getChance() {
        return chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }
}
