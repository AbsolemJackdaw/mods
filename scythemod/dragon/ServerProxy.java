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

import java.util.logging.Logger;

import scythemod.dragon.entity.DragonEntity;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ServerProxy {
    
    private static final Logger L = Logger.getLogger(ServerProxy.class.getName());
    
    public void onInit(FMLInitializationEvent evt) {
        // register entities
        int dragonMountId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(DragonEntity.class, "DragonMount",
                dragonMountId, 0, 0xcc00ff);

        // register translations
        LanguageRegistry lr = LanguageRegistry.instance();
        lr.addStringLocalization("entity.DragonMount.name", "Ender Dragon");
        lr.addStringLocalization("tile.dragonEgg.tooCold", "This place isn't warm enough to hatch a dragon egg...");
        lr.addStringLocalization("commands.dragon.usage", "/dragon <unhatch|mature|rejuvenate>");
        lr.addStringLocalization("dragon.lifeStage.egg", "egg");
        lr.addStringLocalization("dragon.lifeStage.hatchling", "hatchling");
        lr.addStringLocalization("dragon.lifeStage.juvenile", "juvenile");
        lr.addStringLocalization("dragon.lifeStage.adult", "adult");
    }
        
    
    public void onServerStart(FMLServerStartedEvent evt) {
        // clear cached network movement data
        RemoteControlPacketHandler.clear();
        
     
    }
}
