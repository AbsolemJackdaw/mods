package scythemod.render;

import scythemod.model.ModelMiniender;
import net.minecraft.client.renderer.entity.RenderLiving;

public class RenderMiniender extends RenderLiving
{
protected ModelMiniender modelMiniender;
protected float field_40296_d;

public RenderMiniender(ModelMiniender par1ModelMiniender, float par2)
{
	this(par1ModelMiniender, par2, 1.0F);
	modelMiniender = par1ModelMiniender;
}

public RenderMiniender(ModelMiniender par1ModelMiniender, float par2, float par3)
{
	super(par1ModelMiniender, par2);
	modelMiniender = par1ModelMiniender;
	field_40296_d = par3;
}
}
