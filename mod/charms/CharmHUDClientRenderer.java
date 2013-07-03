package charms;

import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.renderer.texture.DynamicTexture;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CharmHUDClientRenderer extends CharmHUDCommonRenderer{

	public void register(){
		TickRegistry.registerTickHandler(new CharmHUD(), Side.CLIENT);
	}

}
