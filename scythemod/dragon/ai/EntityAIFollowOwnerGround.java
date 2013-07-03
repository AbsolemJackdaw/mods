/*
 ** 2012 März 21
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
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * AI to follow the owner on ground.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIFollowOwnerGround extends EntityAIBase {

    private final DragonEntity dragon;
    private EntityLiving owner;
    
    private final float moveSpeed;
    private final float minDist;
    private final float maxDist;
    private final float maxDistTele;
    private boolean avoidWater;
    
    private PathNavigate nav;

    public EntityAIFollowOwnerGround(DragonEntity dragon, float moveSpeed,
            float minDist, float maxDist, float maxDistTele) {
        this.dragon = dragon;
        nav = dragon.getNavigator();
        
        this.moveSpeed = moveSpeed;
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.maxDistTele = maxDistTele;
        
        setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if (!dragon.isTamed() || dragon.isSitting() || dragon.isChild()) {
            return false;
        }
        
        if (dragon.riddenByEntity != null) {
            return false;
        }
        
        EntityLiving currentOwner = (EntityLiving) dragon.getOwner();
        
        if (currentOwner == null) {
            return false;
        }
        
        owner = currentOwner;

        // start only if the owner isn't near
        double dist = dragon.getDistanceSqToEntity(dragon.getOwner());
        return dist > minDist * minDist;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        avoidWater = dragon.getNavigator().getAvoidsWater();
        dragon.getNavigator().setAvoidsWater(false);
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        nav.clearPathEntity();
        dragon.resetWaypoint();
        dragon.getNavigator().setAvoidsWater(avoidWater);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        double dist = dragon.getDistanceSqToEntity(owner);
        boolean liftOff = dist > maxDist * maxDist;

        if (dist > maxDistTele * maxDistTele) {
            // teleport if the owner is way too far away
            teleportToOwner();
        } else {
            // try to walk to owner
            dragon.getLookHelper().setLookPositionWithEntity(owner, dragon.getYawSpeed(), dragon.getVerticalFaceSpeed());
            if (!nav.tryMoveToEntityLiving(owner, moveSpeed)) {
                liftOff = true;
            }
        }
        
        // lift off if the owner is too far away or unreachable
        if (liftOff) {
            dragon.getJumpHelper().setJumping();
        } 
    }
    
    private void teleportToOwner() {
        World world = owner.worldObj;
        int tx = MathHelper.floor_double(owner.posX) - 2;
        int tz = MathHelper.floor_double(owner.posZ) - 2;
        int ty = MathHelper.floor_double(owner.boundingBox.minY);

        for (int x = 0; x <= 4; x++) {
            for (int z = 0; z <= 4; z++) {
                if ((x < 1 || z < 1 || x > 3 || z > 3)
                        && world.isBlockNormalCube(tx + x, ty - 1, tz + z)
                        && !world.isBlockNormalCube(tx + x, ty, tz + z)
                        && !world.isBlockNormalCube(tx + x, ty + 1, tz + z)) {
                    dragon.setLocationAndAngles(tx + x + 0.5f, ty, tz + z + 0.5f,
                            dragon.rotationYaw, dragon.rotationPitch);
                    dragon.getNavigator().clearPathEntity();
                    return;
                }
            }
        }
    }
}
