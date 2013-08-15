package petBuddy.entity.model;

import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelDwarfMale extends ModelNPCMale
{

    public ModelDwarfMale(float f)
    {
        super(f);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);

        GL11.glPushMatrix();
        GL11.glScalef(1, 0.8f, 1);
        renderHead(entity, f5);

        GL11.glPushMatrix();
        GL11.glScalef(1.2f, 1f, 1f);
        renderArms(entity, f5);
        renderLegs(entity, f5);
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        GL11.glScalef(1.1f, 1, 1.55f);
        renderBody(entity,f5);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        
        

    }

}
