package decorativeCollectibles.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import decorativeCollectibles.tileEntity.TileEntityGoblet;
import decorativeCollectibles.tileEntity.TileEntityPig;
import decorativeCollectibles.tileEntity.TileEntitySpecialRendererGoblet;
import decorativeCollectibles.tileEntity.TileEntitySpecialRendererGoldenPigs;

public class CLCollectiblesProxy extends SCollectiblesProxy{

	@Override
	public void registerRenderers() {
		ClientRegistry.registerTileEntity(TileEntityGoblet.class, "TileEntityGoblet", new TileEntitySpecialRendererGoblet());	
		ClientRegistry.registerTileEntity(TileEntityPig.class, "TileEntityPig", new TileEntitySpecialRendererGoldenPigs());		

	}

}
