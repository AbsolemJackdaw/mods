package telepads;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;

public class CLProxy extends SProxy{
	
	@Override
	public void registerTileEntity() {
		ClientRegistry.registerTileEntity(TETelepad.class, "TETelepad", new TESRTelePad());		
	}
	
	@Override
	public void registerItemRenderer(){
		MinecraftForgeClient.registerItemRenderer(mod_telepads.telepad.blockID, new ItemPadRenderer());

	}
	@Override
	public void registerSound(){
		
		MinecraftForge.EVENT_BUS.register(new sndmngr());
	}
}
