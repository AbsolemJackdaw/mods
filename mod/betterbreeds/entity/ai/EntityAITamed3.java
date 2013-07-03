package betterbreeds.entity.ai;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAITamed3 extends EntityAINearestAttackableTarget
{
    private EntityTameable theTameable;
    private Class creep;

    public EntityAITamed3(EntityTameable par1EntityTameable, Class par2Class, float par3, int par4, boolean par5)
    {
        super(par1EntityTameable, par2Class, par4, par5);
        this.theTameable = par1EntityTameable;

    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	if (this.theTameable.isTamed() == true)
    	{
    		super.shouldExecute();
    	}
        return true;
    }
}
