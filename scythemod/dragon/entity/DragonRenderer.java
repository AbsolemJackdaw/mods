/*
** 2011 December 10
**
** The author disclaims copyright to this source code.  In place of
** a legal notice, here is a blessing:
**    May you do good and not evil.
**    May you find forgiveness for yourself and forgive others.
**    May you share freely, never taking more than you give.
*/

package scythemod.dragon.entity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

/**
 * Generic renderer for all dragons.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonRenderer extends RenderLiving {
    
//	isZombie() ? "/DSM/Zdragon.png":"/DSM/Hdragon.png"
    public static boolean updateModel;

    public DragonRenderer(ModelBase model) {
        super(model, 0);
        renderPassModel = model;
    }
    
    /**
     * Queries whether should render the specified pass or not.
     */
    @Override
    public int shouldRenderPass(EntityLivingBase entity, int pass, float scale) {
        return shouldRenderPass((DragonEntity) entity, pass, scale);
    }
    
    public int shouldRenderPass(DragonEntity dragon, int pass, float scale) {
        // update dragon model every second if enabled
        if (pass == 0 && updateModel && dragon.ticksExisted % 20 == 0) {
            mainModel = renderPassModel = new DragonModel();
        }
        
        ((DragonModel)renderPassModel).renderPass = pass;

        switch (pass) {
            // pass 1 - saddle
            case 0:
                if (dragon.isSaddled()) {
                	 Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation((dragon.getTexture(1))));
                    return 1;
                }
                break;
            case 3:
                if (dragon.isSaddled()&& dragon.isZombie()) {
                	 Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation((dragon.getTexture(5))));
                    return 1;
                }
                break;
            // pass 2 - glow overlay
            case 1:
                if (dragon.hurtTime == 0  ) {
                	 Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation((dragon.getTexture(2))));

                    // fix z-fighting
                    GL11.glDepthFunc(GL11.GL_EQUAL);

                    // disable alpha testing
                    GL11.glDisable(GL11.GL_ALPHA_TEST);

                    // enable blending
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

                    // use full lighting
                    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0, 255);

                    return 1;
                }
                break;
            case 4:
                if (dragon.hurtTime == 0  && dragon.isZombie()) {
                	 Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation(dragon.getTexture(4)));

                    // fix z-fighting
                    GL11.glDepthFunc(GL11.GL_EQUAL);

                    // disable alpha testing
                    GL11.glDisable(GL11.GL_ALPHA_TEST);

                    // enable blending
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

                    // use full lighting
                    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0, 255);

                    return 1;
                }
                break;
            // pass 3 - reset glow overlay
            case 2:
                GL11.glDepthFunc(GL11.GL_LEQUAL);
                break;
            	
        }
        
        return -1;
    }
    
    @Override
    protected void rotateCorpse(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        rotateCorpse((DragonEntity) par1EntityLiving, par2, par3, par4);
    }
    
    protected void rotateCorpse(DragonEntity dragon, float par2, float par3, float par4) {
        GL11.glRotatef(180 - par3, 0, 1, 0);
    }
    
    /**
     * Renders the model in RenderLiving
     */
    @Override
    protected void renderModel(EntityLivingBase entity, float moveTime,
            float moveSpeed, float ticksExisted, float lookYaw, float lookPitch,
            float scale) {
        renderModel((DragonEntity) entity, moveTime, moveSpeed, ticksExisted, lookYaw, lookPitch, scale);
    }

    protected void renderModel(DragonEntity dragon, float moveTime, float moveSpeed,
            float ticksExisted, float lookYaw, float lookPitch, float scale) {
        ((DragonModel) renderPassModel).renderPass = -1;

        if (dragon.getDeathTime() > 0 || dragon.isZombie() && dragon.deathTime >0) {
            float alpha = dragon.getDeathTime() / (float) dragon.getMaxDeathTime();
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glAlphaFunc(516, alpha);
            Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation(dragon.getTexture(3)));
            mainModel.render(dragon, moveTime, moveSpeed, ticksExisted, lookYaw, lookPitch, scale);
            GL11.glAlphaFunc(516, 0.1f);
            GL11.glDepthFunc(GL11.GL_EQUAL);
        }
        
        super.renderModel(dragon, moveTime, moveSpeed, ticksExisted, lookYaw, lookPitch, scale);
    }
    
    protected void renderEgg(DragonEntity dragon, double x, double y, double z, float pitch, float partialTicks) {
        Minecraft.getMinecraft().renderEngine.func_110581_b(new ResourceLocation("/terrain.png"));
        
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        
        // apply egg wiggle
        float tickX = dragon.getEggWiggleX();
        float tickZ = dragon.getEggWiggleZ();
        
        float rotX = 0;
        float rotZ = 0;
        
        if (tickX > 0) {
            rotX = (float) Math.sin(tickX - partialTicks) * 8;
        }
        if (tickZ > 0) {
            rotZ = (float) Math.sin(tickZ - partialTicks) * 8;
        }
        
        GL11.glRotatef(rotX, 1, 0, 0);
        GL11.glRotatef(rotZ, 0, 0, 1);

        GL11.glDisable(GL11.GL_LIGHTING);

        int bx = MathHelper.floor_double(dragon.posX);
        int by = MathHelper.floor_double(dragon.posY);
        int bz = MathHelper.floor_double(dragon.posZ);
        
        double tx = -bx - 0.5;
        double ty = -by;
        double tz = -bz - 0.5;

        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.setTranslation(tx, ty, tz);
        renderBlocks.blockAccess = dragon.worldObj;
        renderBlocks.renderBlockByRenderType(Block.dragonEgg, bx, by, bz);
        t.setTranslation(0, 0, 0);
        t.draw();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        doRender((DragonEntity) entity, x, y, z, yaw, partialTicks);
    }
    
    public void doRender(DragonEntity dragon, double x, double y, double z, float yaw, float partialTicks) {
       
            super.doRender(dragon, x, y, z, yaw, partialTicks);
        
    }
    
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTicks) {
        preRenderCallback((DragonEntity) entity, partialTicks);
    }
    
    protected void preRenderCallback(DragonEntity dragon, float partialTicks) {
        float size = dragon.getSize() * 0.8f;
        GL11.glScalef(size, size, size);
    }

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		// TODO Auto-generated method stub
		return new ResourceLocation(((DragonEntity)entity).isZombie() ? "/DSM/Zdragon.png":"/DSM/Hdragon.png");
	}
}
