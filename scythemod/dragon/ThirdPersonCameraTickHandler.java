/*
 ** 2012 August 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ThirdPersonCameraTickHandler implements ITickHandler {
    
    private final Minecraft client = Minecraft.getMinecraft();
    private final ThirdPersonCameraAccessor tpca = new ThirdPersonCameraAccessor();

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if (client.thePlayer != null && client.thePlayer.ridingEntity == null) {
            tpca.resetThirdPersonDistance();
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.WORLD, TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return getClass().getSimpleName();
    }
    
}
