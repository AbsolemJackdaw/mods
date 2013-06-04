package scythemod.entity;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIAngelLooksAtPlayer extends EntityAIWatchClosest
{
    private final EntityCloudVillager theMerchant;

    public EntityAIAngelLooksAtPlayer(EntityCloudVillager par1EntityVillager)
    {
        super(par1EntityVillager, EntityPlayer.class, 8.0F);
        this.theMerchant = par1EntityVillager;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.theMerchant.isTrading())
        {
            this.closestEntity = this.theMerchant.getCustomer();
            return true;
        }
        else
        {
            return false;
        }
    }
}
