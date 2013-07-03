/*
 ** 2012 August 16
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public abstract class FlyingTameableEntity extends EntityTameable {
    
    // data value IDs
    private static final int INDEX_FLYING = 18;
    
    // data NBT IDs
    private static String NBT_FLYING = "Flying";
    private static String NBT_WAYPOINT_X = "WaypointX";
    private static String NBT_WAYPOINT_Y = "WaypointX";
    private static String NBT_WAYPOINT_Z = "WaypointX";

    protected float moveSpeedAir = 1;
    protected float moveSpeedAirVert = 0;
    public int yawSpeed = 30;
    public int inAirTicks;
    
    protected Vec3 waypoint = Vec3.createVectorHelper(0, 0, 0);
    protected float yawAdd;
    protected EntityAITasks airTasks;
    protected boolean canFly = true;

    public FlyingTameableEntity(World world) {
        super(world);
        
        airTasks = new EntityAITasks(world != null ? world.theProfiler : null);
    }
    
    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(INDEX_FLYING, Byte.valueOf((byte) 0));
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean(NBT_FLYING, isFlying());
        nbt.setDouble(NBT_WAYPOINT_X, waypoint.xCoord);
        nbt.setDouble(NBT_WAYPOINT_Y, waypoint.yCoord);
        nbt.setDouble(NBT_WAYPOINT_Z, waypoint.zCoord);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        setFlying(nbt.getBoolean(NBT_FLYING));
        waypoint.xCoord = nbt.getDouble(NBT_WAYPOINT_X);
        waypoint.yCoord = nbt.getDouble(NBT_WAYPOINT_Y);
        waypoint.zCoord = nbt.getDouble(NBT_WAYPOINT_Z);
    }
    
    /**
     * Returns true if the entity is flying.
     */
    public boolean isFlying() {
        return (dataWatcher.getWatchableObjectByte(INDEX_FLYING) & 1) != 0;
    }

    /**
     * Set the flying flag of the entity.
     */
    public void setFlying(boolean saddled) {
        dataWatcher.updateObject(INDEX_FLYING, Byte.valueOf(saddled ? (byte) 1 : (byte) 0));
    }

    @Override
    public void onLivingUpdate() {
        if (this.func_110143_aJ() <= 0) {
            return;
        }
        
        if (isServer()) {
            if (!onGround) {
                inAirTicks++;
            } else {
                inAirTicks = 0;
            }
            
            setFlying(inAirTicks > 10 && canFly);
        }
        
        if (isFlying()) {
            rotationYaw = MathX.normalizeAngle(rotationYaw);
            
            if (isClient()) {
                onFlyingClient();
            } else {
                onFlyingServer();
            }
                        
            renderYawOffset = rotationYaw;
        } else {
            if (isClient()) {
                onGroundClient();
            } else {
                onGroundServer();
            }
            
            super.onLivingUpdate();
        }
    }
    
    protected void onFlyingClient() {
        if (newPosRotationIncrements > 0) {
            double px = posX + (newPosX - posX) / newPosRotationIncrements;
            double py = posY + (newPosY - posY) / newPosRotationIncrements;
            double pz = posZ + (field_110152_bk - posZ) / newPosRotationIncrements;
            double newYaw = MathX.normalizeAngle(newRotationYaw - rotationYaw);
            
            rotationYaw += (float) newYaw / newPosRotationIncrements;
            rotationPitch += ((float) newRotationPitch - rotationPitch) / newPosRotationIncrements;
            
            --newPosRotationIncrements;
            
            setPosition(px, py, pz);
            setRotation(rotationYaw, rotationPitch);
        }
    }
    
    protected void onFlyingServer() {
        // update waypoint movement
        double deltaX = waypoint.xCoord - posX;
        double deltaY = waypoint.yCoord - posY;
        double deltaZ = waypoint.zCoord - posZ;
        
        double dist = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
        
        if (dist > 0.001) {
            deltaY /= Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
            deltaY = MathX.clamp(deltaY, -moveSpeedAir, moveSpeedAir) / 3;

            double newYaw = Math.toDegrees((Math.PI * 2) - Math.atan2(deltaX, deltaZ));
            double yawDelta = MathX.normalizeAngle(newYaw - rotationYaw);
            yawDelta = MathX.clamp(yawDelta, -yawSpeed, yawSpeed);
            
            Vec3 motionVec = Vec3.fakePool.getVecFromPool(motionX, motionY, motionZ).normalize();
            Vec3 deltaVec = Vec3.fakePool.getVecFromPool(deltaX, deltaY, deltaZ).normalize();
            Vec3 rotVec = Vec3.fakePool.getVecFromPool(
                    -Math.sin(Math.toRadians(rotationYaw)),
                    motionY,
                    Math.cos(Math.toRadians(rotationYaw))
                ).normalize();

            double motion1 = Math.hypot(motionX, motionZ) + 1;
            double motion2 = motion1 > 40 ? 40 : motion1;

            yawAdd *= 0.8f;
            yawAdd += yawDelta * (0.7 / motion2 / motion1);

            rotationYaw += yawAdd * 0.1f;

            // calculate acceleration
            float tmp1 = (float) (rotVec.dotProduct(deltaVec) + 0.5) / 1.5f;
            if (tmp1 < 0) {
                tmp1 = 0;
            }
            float tmp2 = (float) (2 / (motion2 + 1));
            float acc = 0.06f * (tmp1 * tmp2 + (1 - tmp2));

            // set vertical motion
            motionY = deltaY + moveSpeedAirVert;

            // update motion in facing direction
            moveFlying(0, moveSpeedAir, acc);
            
            // apply "friction"
            float friction = (float) (motionVec.dotProduct(rotVec) + 1) / 2f;
            friction = 0.8f + 0.15f * friction;

            if (inWater) {
                friction *= 0.8f;
            }

            motionX *= friction;
            motionY *= friction;
            motionZ *= friction;

            // apply movement
            moveEntity(motionX, motionY, motionZ);
        }
 
        // update AI
        if (isAIEnabled()) {
            worldObj.theProfiler.startSection("newAi");
            updateAITasks();
            worldObj.theProfiler.endSection();
        } else {
            worldObj.theProfiler.startSection("oldAi");
            updateEntityActionState();
            worldObj.theProfiler.endSection();
            rotationYawHead = rotationYaw;
        }

        // apply collision
        List<Entity> entities = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.2, 0, 0));

        if (entities != null && !entities.isEmpty()) {
            for (Entity entity : entities) {
                if (entity.canBePushed()) {
                    entity.applyEntityCollision(this);
                }
            }
        }
    }
    
    protected void onGroundClient() {
    }
    
    protected void onGroundServer() {
        resetWaypoint();
    }

    public void setWaypoint(double x, double y, double z) {
        waypoint.xCoord = x;
        waypoint.yCoord = y;
        waypoint.zCoord = z;
    }
    
    public void setWaypoint(Vec3 waypoint) {
        setWaypoint(waypoint.xCoord, waypoint.yCoord, waypoint.zCoord);
    }
    
    public void resetWaypoint() {
        setWaypoint(posX, posY, posZ);
    }
    
    public double getDistanceTo(Vec3 vec) {
        double tx = vec.xCoord - posX;
        double ty = vec.yCoord - posY;
        double tz = vec.zCoord - posZ;
        return tx * tx + ty * ty + tz * tz;
    }
    
    public double getDistanceToWaypoint() {
        return getDistanceTo(waypoint);
    }
    
    public float getYawSpeed() {
        return 6;
    }
    

    /**
     * @return the flySpeed
     */
    public float getMoveSpeedAir() {
        return moveSpeedAir;
    }

    /**
     * @param moveSpeedAir the flySpeed to set
     */
    public void setMoveSpeedAir(float moveSpeedAir) {
        this.moveSpeedAir = moveSpeedAir;
    }

    /**
     * @return the flySpeedVert
     */
    public float getMoveSpeedAirVertical() {
        return moveSpeedAirVert;
    }

    /**
     * @param moveSpeedAirVert the flySpeedVert to set
     */
    public void setMoveSpeedAirVertical(float moveSpeedAirVert) {
        this.moveSpeedAirVert = moveSpeedAirVert;
    }
    
    public double getAltitude() {
        int blockX = (int) (posX - 0.5);
        int blockZ = (int) (posZ - 0.5);
        return posY - worldObj.getHeightValue(blockX, blockZ);
    }
    
    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    @Override
    protected void jump() {
        super.jump();
        // stronger jump for an easier lift-off
        motionY += 0.5;
        inAirTicks += 20;
    }

    @Override
    protected void updateAITick() {
        if (isFlying()) {
            airTasks.onUpdateTasks();
        }
        
        super.updateAITick();
    }
    
    /**
     * Checks if this entity is running on a client.
     * 
     * Required since MCP's isClientWorld returns the exact opposite...
     * 
     * @return true if the entity runs on a client or false if it runs on a server
     */
    public boolean isClient() {
        return worldObj.isRemote;
    }
    
    /**
     * Checks if this entity is running on a server.
     * 
     * @return true if the entity runs on a server or false if it runs on a client
     */
    public boolean isServer() {
        return !worldObj.isRemote;
    }
    
    public float getMoveSpeedGround() {
        return 0.4f;
    }
}
