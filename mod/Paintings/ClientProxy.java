package Paintings;

import Paintings.config.ConfigFile;
import net.minecraft.entity.item.EntityPainting;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{

	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPainting.class, new RenderPaintingLate());
	}

}
