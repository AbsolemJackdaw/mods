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

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class RemoteControlPacketHandler implements IPacketHandler {
    
    private static final Logger L = Logger.getLogger(RemoteControlPacketHandler.class.getName());
    private static Map<EntityPlayer, MovementInputProxy> moveMap = new HashMap<EntityPlayer, MovementInputProxy>();

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        EntityPlayer playerEnt = (EntityPlayer) player;
        MovementInputProxy input;

        if (!moveMap.containsKey(playerEnt)) {
            moveMap.put(playerEnt, input = new MovementInputProxy());
        } else {
            input = moveMap.get(playerEnt);
        }
        
        unpack(packet, input);
    }
    
    private void unpack(Packet250CustomPayload packet, MovementInputProxy input) {
        ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
        DataInputStream dis = new DataInputStream(bis);
        
        try {
            input.moveForward = dis.readFloat();
            input.moveStrafe = dis.readFloat();
            input.jump = dis.readBoolean();
            input.sneak = dis.readBoolean();
            dis.close();
        } catch (IOException ex) {
            L.log(Level.WARNING, "Can't unpack packet", ex);
        }
        
        // validate speeds
        input.moveForward = MathX.clamp(input.moveForward, -1, 1);
        input.moveStrafe = MathX.clamp(input.moveStrafe, -1, 1);
    }
    
    public static MovementInputProxy getMovementInput(EntityPlayer player) {
        return moveMap.get(player);
    }
    
    public static void clear() {
        moveMap.clear();
    }
}
