package telepads;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;

public class CLProxy extends SProxy{
	
	@Override
	public void registerTileEntity() {
		ClientRegistry.bindTileEntitySpecialRenderer(TETelepad.class, new TESRTelePad());
	}
	
	@Override
	public void registerItemRenderer(){
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Telepads.telepad), new ItemPadRenderer());
		Telepads.Channel.register(new ClientPacketHandler());
	}
	@Override
	public void registerSound(){
		
		MinecraftForge.EVENT_BUS.register(new sndmngr());
	}
}
