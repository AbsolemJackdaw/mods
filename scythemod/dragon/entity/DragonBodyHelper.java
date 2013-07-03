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

import scythemod.dragon.MathX;
import net.minecraft.entity.EntityBodyHelper;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonBodyHelper extends EntityBodyHelper {

    private DragonEntity dragon;
    private int turnTicks;
    private float prevRotationYawHead;

    public DragonBodyHelper(DragonEntity dragon) {
        super(dragon);
        this.dragon = dragon;
    }

    @Override
    public void func_75664_a() {
        // don't rotate the body when sitting
        if (dragon.isSitting()) {
            return;
        }
        
        double deltaX = dragon.posX - dragon.prevPosX;
        double deltaY = dragon.posZ - dragon.prevPosZ;
        double dist = deltaX * deltaX + deltaY * deltaY;
        
        float yawThresh = 90;

        // rotate instantly if moving
        if (dist > 0.00000025 && !dragon.isSitting()) {
            dragon.renderYawOffset = dragon.rotationYaw;
            dragon.rotationYawHead = updateRotation(dragon.renderYawOffset, dragon.rotationYawHead, yawThresh);
            prevRotationYawHead = dragon.rotationYawHead;
            turnTicks = 0;
            return;
        }
        
        int turnTicksThresh = 20;

        if (Math.abs(dragon.rotationYawHead - prevRotationYawHead) > 15) {
            turnTicks = 0;
            prevRotationYawHead = dragon.rotationYawHead;
        } else {
            turnTicks++;

            if (turnTicks > turnTicksThresh) {
                yawThresh = Math.max(1 - (float) (turnTicks - turnTicksThresh) / turnTicksThresh, 0) * 75;
            }
        }

        dragon.renderYawOffset = updateRotation(dragon.rotationYawHead, dragon.renderYawOffset, yawThresh);
    }

    private float updateRotation(float par1, float par2, float par3) {
        float var4 = MathX.normalizeAngle(par1 - par2);

        if (var4 < -par3) {
            var4 = -par3;
        }

        if (var4 >= par3) {
            var4 = par3;
        }

        return par1 - var4;
    }
}
