/*

 ** 2012 April 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.ai;

import scythemod.dragon.entity.DragonEntity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * AI to follow the owner in air.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public abstract class EntityAIFlyTo extends EntityAIBase {
    
    protected final DragonEntity dragon;
    protected final float speed;
    protected EntityLiving target;
    protected int yOffset;
    
    public EntityAIFlyTo(DragonEntity dragon, float speed) {
        this.dragon = dragon;
        this.speed = speed;
        setMutexBits(3);
    }
    
    @Override
    public boolean shouldExecute() {
        // check if it can see the target
        Vec3 dragonPos = Vec3.fakePool.getVecFromPool(dragon.posX, dragon.posY, dragon.posZ);
        Vec3 ownerPos = Vec3.fakePool.getVecFromPool(target.posX, target.posY, target.posZ);
        MovingObjectPosition pos = dragon.worldObj.rayTraceBlocks(dragonPos,ownerPos);
        return pos == null;
    }
    
    @Override
    public void startExecuting() {
        dragon.setMoveSpeedAir(speed);
    }

    @Override
    public void updateTask() {
        World world = dragon.worldObj;

        Vec3 dragonVec = Vec3.fakePool.getVecFromPool(dragon.posX, dragon.posY, dragon.posZ);
        Vec3 targetVec = Vec3.fakePool.getVecFromPool(target.posX, target.posY, target.posZ);
        
        // find a waypoint with free line of sight to the target
        MovingObjectPosition pos = world.rayTraceBlocks(dragonVec, targetVec);
        if (pos == null || pos.typeOfHit == EnumMovingObjectType.ENTITY) {
            // set actual waypoint some blocks below the target to land
            dragon.setWaypoint(targetVec.xCoord, targetVec.yCoord - 4, targetVec.zCoord);
        }
    }
}
