package betterbreeds.handelers;

import net.minecraft.client.model.*;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import betterbreeds.ModBreeds;
import betterbreeds.entity.*;
import betterbreeds.entity.model.*;
import betterbreeds.entity.render.*;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;



public class ClientProxy extends CommonProxy
{
	public void registerRenderInformation()
	{

		RenderingRegistry.registerEntityRenderingHandler(EntityPig2.class, new RenderPig2(new ModelPig(), null, 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPig3.class, new RenderPig3(new ModelPig(), null, 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPig4.class, new RenderPig4(new ModelPig(), null, 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPig5.class, new RenderPig5(new ModelPig(), null, 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPig6.class, new RenderPig6(new ModelPig(), null, 0.2F));

		RenderingRegistry.registerEntityRenderingHandler(EntityCow2.class, new RenderCow2(new ModelCow(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCow3.class, new RenderCow3(new ModelCow(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCow4.class, new RenderCow4(new ModelCow(), 0.2F));

		RenderingRegistry.registerEntityRenderingHandler(EntityWolf2.class, new RenderWolf2(new Wolf2(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWolf3.class, new RenderWolf3(new Wolf3(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWolf4.class, new RenderWolf4(new Wolf4(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWolf5.class, new RenderWolf5(new Wolf5(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWolf6.class, new RenderWolf6(new Wolf6(), 0.2F));

		RenderingRegistry.registerEntityRenderingHandler(EntitySheep2.class, new RenderSheep2(new ModelSheep4(), new ModelSheep3(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySheep3.class, new RenderSheep3(new ModelSheep6(), new ModelSheep5(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySheep4.class, new RenderSheep4(new ModelSheep8(), new ModelSheep7(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySheep5.class, new RenderSheep5(new ModelSheep9(), new ModelSheep10(), 0.2F));

		RenderingRegistry.registerEntityRenderingHandler(EntityChicken2.class, new RenderChicken2 (new ModelChicken2(),0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityChicken3.class, new RenderChicken3 (new ModelChicken(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityChicken4.class, new RenderChicken4 (new ModelChicken(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityChicken5.class, new RenderChicken5 (new ModelChicken(), 0.2F));

		RenderingRegistry.registerEntityRenderingHandler(EntityJelly.class, new RenderJelly(new ModelJelly(), 0.2F));

	}

	@Override
	public void registerSound(){
		
		MinecraftForge.EVENT_BUS.register(new sndmngr());
	}
}