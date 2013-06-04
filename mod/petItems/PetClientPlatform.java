package petItems;

import petItems.entity.EntityToolPet;
import petItems.entity.RenderToolPet;
import petItems.entity.ToolPetModel;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class PetClientPlatform extends PetCommonPlatform {


	@Override
	public void render(){
//		KeyBindingRegistry.registerKeyBinding(new PetKeyHandler());
		RenderingRegistry.registerEntityRenderingHandler(EntityToolPet.class, new RenderToolPet(new ToolPetModel(), 0.5f));
	}
}
