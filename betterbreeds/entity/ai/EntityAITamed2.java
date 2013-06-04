
package betterbreeds.entity.ai;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityTameable;



public class EntityAITamed2 extends EntityAINearestAttackableTarget
{
    private EntityTameable theTameable;
    private Class targetClass;
    public EntityAITamed2(EntityTameable par1EntityTameable, Class par2Class, float par3, int par4, boolean par5)
    {
        super(par1EntityTameable, par2Class, par3, par4, par5);
        this.theTameable = par1EntityTameable;
        this.setTargetClass(EntityEnderman.class);

        
      
    }
    public Class getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class targetClass) {
		this.targetClass = targetClass;
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
