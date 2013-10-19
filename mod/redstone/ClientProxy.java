package redstone;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import redstone.ammo.AranAmmo;
import redstone.ammo.BlueAmmo;
import redstone.ammo.EndAmmo;
import redstone.ammo.GatlingAmmo;
import redstone.ammo.GreenAmmo;
import redstone.ammo.PlasmaAmmo;
import redstone.ammo.RedAmmo;
import redstone.ammo.ThawAmmo;
import redstone.ammo.models.ModelPlasma;
import redstone.ammo.render.RenderGatlingAmmo;
import redstone.ammo.render.RenderPlasma;
import redstone.ammo.render.RenderThawAmmo;
import redstone.item.render.RenderCannon;
import redstone.item.render.RenderDefGun;
import redstone.item.render.RenderGatling;
import redstone.item.render.RenderGunBlue;
import redstone.item.render.RenderGunEnd;
import redstone.item.render.RenderGunGreen;
import redstone.item.render.RenderHalo;
import redstone.item.render.RenderRedGun;
import redstone.item.render.RenderThawer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public void render(){

		RenderingRegistry.registerEntityRenderingHandler(RedAmmo.class, new RenderSnowball(Item.redstone));
		RenderingRegistry.registerEntityRenderingHandler(BlueAmmo.class, new RenderSnowball(Item.dyePowder,4));
		RenderingRegistry.registerEntityRenderingHandler(GreenAmmo.class, new RenderSnowball(Item.rottenFlesh));
		RenderingRegistry.registerEntityRenderingHandler(EndAmmo.class, new RenderSnowball(Item.netherStar));
		RenderingRegistry.registerEntityRenderingHandler(AranAmmo.class, new RenderSnowball(Item.fireballCharge));
		RenderingRegistry.registerEntityRenderingHandler(ThawAmmo.class, new RenderThawAmmo());
		RenderingRegistry.registerEntityRenderingHandler(GatlingAmmo.class, new RenderGatlingAmmo());
		RenderingRegistry.registerEntityRenderingHandler(PlasmaAmmo.class, new RenderPlasma(new ModelPlasma()));

		MinecraftForgeClient.registerItemRenderer(mod_RedStoneGuns.redGun.itemID, new RenderRedGun());
		MinecraftForgeClient.registerItemRenderer(mod_RedStoneGuns.blueGun.itemID, new RenderGunBlue());
		MinecraftForgeClient.registerItemRenderer(mod_RedStoneGuns.greenGun.itemID, new RenderGunGreen());
		MinecraftForgeClient.registerItemRenderer(mod_RedStoneGuns.rifle.itemID, new RenderGunEnd());
		MinecraftForgeClient.registerItemRenderer(mod_RedStoneGuns.cannon.itemID, new RenderCannon());
		MinecraftForgeClient.registerItemRenderer(mod_RedStoneGuns.thawer.itemID, new RenderThawer());
		MinecraftForgeClient.registerItemRenderer(mod_RedStoneGuns.gatling.itemID, new RenderGatling());
		MinecraftForgeClient.registerItemRenderer(mod_RedStoneGuns.plasmaRifle.itemID, new RenderHalo());

		MinecraftForgeClient.registerItemRenderer(mod_RedStoneGuns.defGun.itemID, new RenderDefGun());

	}
}
