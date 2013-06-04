package petItems.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderToolPet extends RenderLiving
{
    public RenderToolPet( ModelBase model ,float par2)
    {
        super(model, par2);
    }

    public void renderCow(EntityToolPet buddy, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(buddy, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
    	EntityToolPet pet = (EntityToolPet)par1EntityLiving;
        this.renderCow(pet, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
    	EntityToolPet pet = (EntityToolPet)par1Entity;
        this.renderCow((EntityToolPet)par1Entity, par2, par4, par6, par8, par9);
		this.renderLivingLabel(pet, pet.getOwnerName()+ "'s ToolPet" , par2, par4, par6, 32);
    }
}