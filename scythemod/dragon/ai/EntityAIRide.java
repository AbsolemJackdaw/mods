/*
 ** 2012 March 18
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.ai;

import scythemod.dragon.MovementInputProxy;
import scythemod.dragon.RemoteControlPacketHandler;
import scythemod.dragon.entity.DragonEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Abstract "AI" for player-controlled movements.
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public abstract class EntityAIRide extends EntityAIBase {

    protected final DragonEntity dragon;
    protected EntityPlayer rider;
    protected MovementInputProxy riderInput;
    protected float speed;

    public EntityAIRide(DragonEntity dragon, float speed) {
        this.dragon = dragon;
        this.speed = speed;
        setMutexBits(Integer.MAX_VALUE);
    }
    
    @Override
    public boolean shouldExecute() {
        rider = dragon.getRider();
        
        if (rider != null) {
            riderInput = RemoteControlPacketHandler.getMovementInput(rider);
        }
        
        return rider != null && riderInput != null;
    }
}
