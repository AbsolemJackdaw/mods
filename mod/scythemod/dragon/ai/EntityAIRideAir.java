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
import net.minecraft.util.Vec3;

/**
 * AI for player-controlled air movements.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIRideAir extends EntityAIRide {
        
    public EntityAIRideAir(DragonEntity dragon, float speed) {
        super(dragon, speed);
    }
    
    @Override
    public void updateTask() {
        super.updateTask();
        
        double dist = 100;

        // default to the direction of the dragon
        Vec3 wp = dragon.getLookVec();
        
        // scale with distance
        wp.xCoord *= dist;
        wp.yCoord *= dist;
        wp.zCoord *= dist;
        
        // convert to absolute position
        wp.xCoord += dragon.posX;
        wp.yCoord += dragon.posY;
        wp.zCoord += dragon.posZ;
        
        dragon.setWaypoint(wp);
        dragon.setMoveSpeedAir(0);

        // change speed with forward
        if (riderInput.moveForward != 0) {
            float moveSpeed = speed;
            
            if (riderInput.moveForward < 0) {
                moveSpeed *= 0.5;
            }
            
            dragon.setMoveSpeedAir(riderInput.moveForward * moveSpeed);
        }
        
        if (riderInput.moveStrafe != 0) {
            // rotate with strafing when hovering
            dragon.rotationYaw -= riderInput.moveStrafe * dragon.getYawSpeed();
        }
        
        // control height with jumping/sneaking when hovering
        if (riderInput.jump) {
            dragon.setMoveSpeedAirVertical(0.5f);
        } else if (riderInput.sneak) {
            dragon.setMoveSpeedAirVertical(-0.5f);
        } else{
            dragon.setMoveSpeedAirVertical(0);
        }
    }
}
