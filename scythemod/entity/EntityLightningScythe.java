package scythemod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityLightningScythe extends EntityThrowable
{
    public EntityLightningScythe(World par1World)
    {
        super(par1World);
    }

    public EntityLightningScythe(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityLightningScythe(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit != null)
        {
            byte var2 = 12;

            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), var2);
        }

        for (int var3 = 0; var3 < 8; ++var3)
        {
        	this.worldObj.spawnEntityInWorld(new
        			EntityLightningBolt(this.worldObj, (double)((float)this.posX + 0.5F), (double)((float)this.posY + 0.5F), (double)((float)this.posZ + 0.5F)));
        	 this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, 2F, inGround);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
     
        
    }   

}