/*
 ** 2012 August 13
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.entity;

import scythemod.dragon.RemoteControlPacketHandler;
import scythemod.dragon.ServerProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
@Mod(
    modid = "DeathScytheModDragons",
    name = "DragonsforDsm",
    version = "DragonsForDsm 2.0",
    useMetadata = true
)
@NetworkMod
(
    clientSideRequired = true,
    serverSideRequired = false,
    channels = {"PlayerMoveInput"},
    packetHandler = RemoteControlPacketHandler.class
)
public class DragonsModForge {
    
    @SidedProxy(
        serverSide = "scythemod.dragon.ServerProxy",
        clientSide = "scythemod.dragon.ClientProxy"
    )
    public static ServerProxy proxy;
    
    @Instance (value = "DragonMounts")
    public static DragonsModForge instance;
    
    @Init
    public void onInit(FMLInitializationEvent evt) {      
        proxy.onInit(evt);
    }
    
    @ServerStarted
    public void onServerStart(FMLServerStartedEvent evt) {
        proxy.onServerStart(evt);
    }
}
