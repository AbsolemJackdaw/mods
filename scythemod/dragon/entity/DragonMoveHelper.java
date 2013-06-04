/*
 ** 2012 March 20
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */

package scythemod.dragon.entity;

import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.MathHelper;

public class DragonMoveHelper extends EntityMoveHelper {

    private DragonEntity dragon;
    private double posX;
    private double posY;
    private double posZ;
    private float speed;
    private boolean needsUpdate;
    private boolean resetSpeed;

    public DragonMoveHelper(DragonEntity dragon) {
        super(dragon);
        this.dragon = dragon;
        posX = dragon.posX;
        posY = dragon.posY;
        posZ = dragon.posZ;
    }

    @Override
    public boolean isUpdating() {
        return needsUpdate;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setMoveTo(double posX, double posY, double posZ, float speed) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.speed = speed;
        needsUpdate = true;
    }

    @Override
    public void onUpdateMoveHelper() {
        if (!needsUpdate) {
            // reset speed once only so it can be controlled by tasks
            if (resetSpeed) {
                resetSpeed = false;
                dragon.setAIMoveSpeed(0);
            }
            return;
        }

        resetSpeed = true;
        needsUpdate = false;
        double bbY = MathHelper.floor_double(dragon.boundingBox.minY + 0.5);
        double deltaX = posX - dragon.posX;
        double deltaZ = posZ - dragon.posZ;
        double deltaY = posY - bbY;
        double dist = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;

        if (dist < 0.00000025) {
            return;
        }

        float newRotationYaw = (float) Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90;
        dragon.rotationYaw = updateRotation(dragon.rotationYaw, newRotationYaw, dragon.getYawSpeed());
        dragon.setAIMoveSpeed(speed);
    }

    private float updateRotation(float r1, float r2, float limit) {
        float f;

        for (f = r2 - r1; f < -180F; f += 360F) {
        }

        for (; f >= 180F; f -= 360F) {
        }

        if (f > limit) {
            f = limit;
        }

        if (f < -limit) {
            f = -limit;
        }

        return r1 + f;
    }
}
