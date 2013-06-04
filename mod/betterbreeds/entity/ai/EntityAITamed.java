package betterbreeds.entity.ai;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAITamed extends EntityAINearestAttackableTarget
{
    private EntityTameable theTameable;
    private Class creep;
    private Class targetClass;
    public EntityAITamed(EntityTameable par1EntityTameable, Class par2Class, float par3, int par4, boolean par5)
    {
        super(par1EntityTameable, par2Class, par3, par4, par5);
        this.theTameable = par1EntityTameable;
        this.setTargetClass(EntityZombie.class);

        
      
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
