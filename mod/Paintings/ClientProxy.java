package Paintings;

import net.minecraft.entity.item.EntityPainting;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{

	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPainting.class, new RenderPaintingLate());
	}

}
