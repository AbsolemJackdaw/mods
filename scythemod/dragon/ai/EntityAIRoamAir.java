/*
 ** 2012 August 17
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.ai;

import java.util.logging.Level;
import java.util.logging.Logger;

import scythemod.dragon.MathX;
import scythemod.dragon.entity.DragonEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIRoamAir extends EntityAIBase {
    
    private static final Logger L = Logger.getLogger(EntityAIRoamAir.class.getName());
    
    private final DragonEntity dragon;
    private final float speed;
    private final double minRange;
    private final double maxRange;
    private final int horizRange;
    private final int vertRange;
    private final float landChance;
    
    private double minAltitude = 16;
    private int collidedTicks;

    public EntityAIRoamAir(DragonEntity dragon, float speed, double minRange, double maxRange,
            int horizRange, int vertRange, float landChance) {
        this.dragon = dragon;
        this.speed = speed;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.horizRange = horizRange;
        this.vertRange = vertRange;
        this.landChance = landChance;
        setMutexBits(1);
    }
    
    @Override
    public boolean isInterruptible() {
        return false;
    }
    
    @Override
    public boolean continueExecuting() {
        return false;
    }

    @Override
    public boolean shouldExecute() {
        // TODO: interferes EntityAIFlyTo
        if (dragon.isTamed()) {
            return false;
        }
        
        // don't choose a new waypoint too often on collision. always wait
        // at least two seconds, then wait another second before retrying
        if (dragon.isCollided) {
            collidedTicks++;
            collidedTicks %= 40;
        } else {
            collidedTicks = 0;
        }
        
        return (isNearWaypoint() || collidedTicks == 20);
    }
    
    @Override
    public void startExecuting() {
        World world = dragon.worldObj;
        double maxBlockY = world.getHeight();
        boolean landing;
        Vec3 wp = null;
        
        for (int i = 0; i < 8; i++) {
            wp = RandomPositionGenerator.findRandomTarget(dragon, horizRange, vertRange);

            int blockX = (int) (wp.xCoord - 0.5);
            int blockZ = (int) (wp.zCoord - 0.5);
            
            double terrainHeight = world.getHeightValue(blockX, blockZ);
            
            // smaller dragons have a higher chance to land
            float landChance2 = landChance * 1f;
            
            // chance to land on the lowest block
            landing = dragon.getRNG().nextFloat() < landChance2;
            
            if (landing) {
                // get the material of the block beneath the waypoint
                int blockY = (int) (terrainHeight - 0.5);
                int blockBeneath = world.getBlockId(blockX, blockY - 1, blockZ);
                Material material = blockBeneath != 0 ? Block.blocksList[blockBeneath].blockMaterial : Material.air;

                // accept only if the landing block isn't liquid
                if (material.isLiquid()) {
                    wp = null;
                    continue;
                }
                
                wp.yCoord = terrainHeight - minRange;
            } else {
                wp.yCoord = MathX.clamp(wp.yCoord, terrainHeight + minAltitude, maxBlockY);
            }
            
            // don't choose a waypoint that is already too close or too far away
            double dist = dragon.getDistanceTo(wp);
            if (dist < minRange || dist > maxRange) {
                wp = null;
                continue;
            }
        }

        if (wp == null) {
            L.log(Level.FINE, "No valid waypoint found for dragon {0}!", dragon.entityId);
        } else {
            L.log(Level.FINE, "Set new waypoint for dragon {0} to {1}", new Object[] {dragon.entityId, wp});
            dragon.setMoveSpeedAir(speed);
            dragon.setWaypoint(wp);
        }
    }
    
    private boolean isNearWaypoint() {
        double dist = dragon.getDistanceToWaypoint();
        return dist < minRange || dist > maxRange;
    }
}
