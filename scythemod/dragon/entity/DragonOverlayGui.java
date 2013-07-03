/*
 ** 2012 April 3
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.util.StringTranslate;

import org.lwjgl.opengl.GL11;

/**
 * Dragon in-game overlay.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonOverlayGui extends Gui {
    
    private Minecraft mc;
 
    public DragonOverlayGui(Minecraft mc) {
        this.mc = mc;
    }

    public void draw() {
        if (mc.gameSettings.hideGUI || mc.currentScreen != null) {
            return;
        }
        
        EntityPlayerSP player = mc.thePlayer;

        if (player == null) {
            return;
        }
       
        DragonEntity dragon;
        
        if (player.ridingEntity != null && player.ridingEntity instanceof DragonEntity) {
            dragon = (DragonEntity) player.ridingEntity;
        } else if (mc.objectMouseOver != null) {
            Entity entity = mc.objectMouseOver.entityHit;
            
            if (entity == null || !(entity instanceof DragonEntity)) {
                return;
            }

            // get dragon base entity if a part is selected
//            if (entity instanceof DragonPart) {
//                entity = ((DragonPart) entity).base;
//            }
            
            dragon = (DragonEntity) entity;
        } else {
            // no or invalid entity
            return;
        }
        
        // don't show the health of untamed dragons
//        if (!dragon.isTamed()) {
//            return;
//        }
        Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation(("/DSM/dragongui.png")));

        float dragonHealth = (float) dragon.getDragonHealth() / (float) dragon.func_110138_aP();
        
        ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        
        int var3 = res.getScaledWidth();
        short var4 = 182;
        int var5 = var3 / 2 - var4 / 2;
        int var6 = (int)(dragonHealth * (float)(var4 + 1));
        byte var7 = 12;
        int y = res.getScaledHeight() - 46;
        this.drawTexturedModalRect(var5, y, 0, 0, var4, 5);
        this.drawTexturedModalRect(var5, y, 0, 5, var4, 5);

        if (var6 > 0)
        {
            this.drawTexturedModalRect(var5, y, 0, 79, var6, 5);
        }

        String caption = dragon.isZombie() ? "Death Dragon" : "Sacred Dragon" ;        
        
        int color = 0x05db34;
        FontRenderer fr = mc.fontRenderer;
        fr.drawStringWithShadow(caption, var3 / 2 - fr.getStringWidth(caption) / 2, y - 10, color);
        GL11.glColor4f(1, 1, 1, 1);
    }
}
