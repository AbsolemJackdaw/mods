/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petBuddy.RichTools;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author Richard Smith <rich1051414@gmail.com>
 */
public class Targetting {

    @SideOnly(Side.CLIENT)
    public static EntityLiving isTargetingLivingEntity(double parDistance) {
        net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();
        EntityLivingBase viewEntity = mc.renderViewEntity;
        World worldObj = viewEntity.worldObj;
        Entity Return = null;

        if (viewEntity != null) {

            MovingObjectPosition objectMouseOver = viewEntity.rayTrace(parDistance, 0.5F);
            Vec3 playerPosition = viewEntity.getPosition(1);

            double farDist = parDistance;
            if (objectMouseOver != null) {
                farDist = objectMouseOver.hitVec.distanceTo(playerPosition);
            }
            double closest = farDist;

            Vec3 dirVec = viewEntity.getLookVec();
            Vec3 lookFarCoord = playerPosition.addVector(dirVec.xCoord * parDistance, dirVec.yCoord * parDistance, dirVec.zCoord * parDistance);


            List<EntityLiving> targettedEntities = worldObj.getEntitiesWithinAABB(EntityLiving.class, viewEntity.boundingBox.addCoord(
            		dirVec.xCoord * parDistance, dirVec.yCoord * parDistance, dirVec.zCoord * parDistance).expand(0.1, 0.1, 0.1));
            targettedEntities.remove(viewEntity);

            for (EntityLiving targettedEntity : targettedEntities) {
                if (targettedEntity != null) {
                    double precheck = viewEntity.getDistanceToEntity(targettedEntity);
                    MovingObjectPosition mopElIntercept = targettedEntity.boundingBox.calculateIntercept(playerPosition, lookFarCoord);
                    if (mopElIntercept != null) {
                        if (precheck < closest) {
                            Return = targettedEntity;
                            closest = precheck;
                        }
                    }
                }
            }
        }
        if (Return != null && Return instanceof EntityLiving) {
            return (EntityLiving) Return;
        }
        return null;
    }
}
