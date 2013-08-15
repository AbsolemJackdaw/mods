// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package petBuddy.entity.model;

import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer, MathHelper, Entity

public class ModelElfMale extends ModelNPCMale
{

    public ModelElfMale(float f)
    {
        super(f);
    }
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);

        GL11.glPushMatrix();
        GL11.glScalef(1f, 0.88f, 1f);
        renderHead(entity, f5);
        GL11.glPopMatrix();
        
        renderArms(entity, f5);
        renderLegs(entity, f5);
        renderBody(entity, f5);
    }

}
