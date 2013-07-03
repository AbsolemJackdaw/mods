/*
 ** 2012 April 25
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
 * AI for player-controlled ground movements.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIRideGround extends EntityAIRide {

    public EntityAIRideGround(DragonEntity dragon, float speed) {
        super(dragon, speed);
    }
    
    @Override
    public void startExecuting() {
        dragon.getNavigator().clearPathEntity();
    }
    
    @Override
    public void updateTask() {
        super.updateTask();
        
        // change speed with forward
        if (riderInput.moveForward > 0) {
            dragon.setAIMoveSpeed(speed);
        } else {
            dragon.setAIMoveSpeed(0);
        }
        
        // rotate with strafing
        if (riderInput.moveStrafe != 0) {
            dragon.rotationYaw -= riderInput.moveStrafe * dragon.getYawSpeed();
            dragon.renderYawOffset = dragon.rotationYaw;
        }
        
        // lift off by jumping
        if (riderInput.jump) {
            dragon.getJumpHelper().setJumping();
        }
    }
}
