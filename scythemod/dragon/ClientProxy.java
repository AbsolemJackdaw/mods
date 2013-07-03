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

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import scythemod.dragon.entity.DragonEntity;
import scythemod.dragon.entity.DragonModel;
import scythemod.dragon.entity.DragonRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.audio.SoundPoolEntry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ClientProxy extends ServerProxy {
    
    private static final Logger L = Logger.getLogger(ClientProxy.class.getName());
    
    @Override
    public void onInit(FMLInitializationEvent evt) {
        super.onInit(evt);
        
        // register tick handlers
        TickRegistry.registerTickHandler(new OverlayTickHandler(), Side.CLIENT);
        TickRegistry.registerTickHandler(new RemoteControlTickHandler(), Side.CLIENT);
        TickRegistry.registerTickHandler(new ThirdPersonCameraTickHandler(), Side.CLIENT);
        
        // register entity renderer
        RenderingRegistry.registerEntityRenderingHandler(DragonEntity.class,
                new DragonRenderer(new DragonModel()));
        
        // New register sounds
        Minecraft mc = FMLClientHandler.instance().getClient();

// 		mc.installResource("sound/dragons/DgnStep1.ogg", new File(mc.mcDataDir, "resources/dragons/DgnStep1.ogg"));
// 		mc.installResource("sound/dragons/DgnStep2.ogg", new File(mc.mcDataDir, "resources/dragons/DgnStep2.ogg"));
// 		mc.installResource("sound/dragons/DgnStep3.ogg", new File(mc.mcDataDir, "resources/dragons/DgnStep3.ogg"));
// 		mc.installResource("sound/dragons/DgnStep4.ogg", new File(mc.mcDataDir, "resources/dragons/DgnStep4.ogg"));
// 		mc.installResource("sound/dragons/Dissolve.ogg", new File(mc.mcDataDir, "resources/dragons/Dissolve.ogg"));
 		
 		
        /* register sounds
        addSound("mob.enderdragon.walk", "/resources/dragons/DgnStep1.ogg");
        addSound("mob.enderdragon.walk", "/resources/dragons/sounds/DgnStep2.ogg");
        addSound("mob.enderdragon.walk", "/resources/dragons/sounds/DgnStep3.ogg");
        addSound("mob.enderdragon.walk", "/resources/dragons/sounds/DgnStep4.ogg");
        addSound("mob.enderdragon.transform", "/resources/newsound/mob/endermen/portal2.ogg");
        addSound("mob.enderdragon.death", "/resources/dragons/sounds/Dissolve.ogg");*/
    }
    
    private void addSound(String name, String url) {
        // HAAX! HAAAAAX!
        try {
            SoundManager sndManager = Minecraft.getMinecraft().sndManager;
            SoundPool soundPool = (SoundPool) ReflectionHelper.getPrivateValue(SoundManager.class, sndManager, 1);
            Map<String, List<SoundPoolEntry>> soundPoolMap = (Map<String, List<SoundPoolEntry>>) ReflectionHelper.getPrivateValue(SoundPool.class, soundPool, 1);
            List<SoundPoolEntry> soundPoolList = (List<SoundPoolEntry>) ReflectionHelper.getPrivateValue(SoundPool.class, soundPool, 2);
            
            if (!soundPoolMap.containsKey(name)) {
                soundPoolMap.put(name, new ArrayList<SoundPoolEntry>());
            }
            
            URL soundUrl = ClientProxy.class.getResource(url);
            
            // check local file system if the file isn't in the classpath
            if (soundUrl == null) {
                soundUrl = new File(Minecraft.getMinecraft().mcDataDir, url.substring(1)).toURI().toURL();
            }
            
            if (soundUrl == null) {
                L.log(Level.WARNING, "Couldn't find sound file {0} for {1}!", new Object[]{url, name});
                return;
            }
            
            SoundPoolEntry entry = new SoundPoolEntry(url, soundUrl);
            soundPoolMap.get(name).add(entry);
            soundPoolList.add(entry);
            
            L.log(Level.FINER, "Added sound \"{0}\" for \"{1}\"", new Object[]{name, url});
        } catch (Exception ex) {
            L.log(Level.WARNING, "Couldn't add sound!", ex);
        }
    }
}
