package charms;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CharmHUDClientRenderer extends CharmHUDCommonRenderer{

	
	
	
	public void register(){
		TickRegistry.registerTickHandler(new CharmHUD(), Side.CLIENT);
		
		
	}

}
