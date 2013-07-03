package scythemod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.MinecraftForgeClient;
import scythemod.entity.EntityCReaper;
import scythemod.entity.EntityCloudVillager;
import scythemod.entity.EntityDeath;
import scythemod.entity.EntityDrainScythe;
import scythemod.entity.EntityFarmScythe;
import scythemod.entity.EntityGhost;
import scythemod.entity.EntityLightningScythe;
import scythemod.entity.EntityMiniender;
import scythemod.entity.EntityTheFrozen;
import scythemod.entity.EntityThrowCake;
import scythemod.entity.EntityThrowScythe;
import scythemod.entity.EntityZombieReaper;
import scythemod.gen.BiomeGenFrozen;
import scythemod.gen.BiomeGenShadow;
import scythemod.model.ModelDeath;
import scythemod.model.ModelMiniender;
import scythemod.render.RenderANgel;
import scythemod.render.RenderCReaper;
import scythemod.render.RenderGhost;
import scythemod.render.RenderMiniender;
import scythemod.weaponrender.RenderScytheHelper;
import scythemod.weaponrender.ScytheRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy
{
	public static BiomeGenBase Frozen = (new BiomeGenFrozen(40));
	public static BiomeGenBase Shadow = (new BiomeGenShadow(39));

	public void registerRenderInformation()
	{
		GameRegistry.addBiome(Frozen);
		GameRegistry.addBiome(Shadow);

		RenderingRegistry.registerEntityRenderingHandler(EntityCReaper.class, new RenderCReaper());
		RenderingRegistry.registerEntityRenderingHandler(EntityTheFrozen.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderGhost(new ModelBlaze(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieReaper.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDeath.class, new RenderBiped(new ModelDeath(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMiniender.class, new RenderMiniender(new ModelMiniender(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCloudVillager.class, new RenderANgel());

		RenderingRegistry.registerEntityRenderingHandler(EntityLightningScythe.class, new RenderSnowball(Item.fireballCharge));
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowScythe.class, new RenderSnowball(Item.feather) );
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowCake.class, new RenderSnowball(Item.cake));
		RenderingRegistry.registerEntityRenderingHandler(EntityFarmScythe.class, new RenderSnowball(Item.wheat));
		RenderingRegistry.registerEntityRenderingHandler(EntityDrainScythe.class, new RenderSnowball(Item.redstone));

		RenderScytheHelper.inst.addScythe(BaseScytheFile.DeathScythe, "scytheDeath");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.DeathScythe1, "scytheDeath");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.HolyScythe, "scytheHoly");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.HolyScythe1, "scytheHoly");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.ZombieScythe, "scytheZombie");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.ZombieScythe1, "scytheZombie");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.UnholyScythe, "scytheUnholy");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.UnholyScythe1, "scytheUnholy");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.FarmScythe, "scytheFarm");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.DrainScythe, "scytheDrain");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.FireScythe, "fireScythe");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.LightningScythe, "scytheThunder");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.ThrowScythe, "scytheDeath");
		RenderScytheHelper.inst.addScythe(BaseScytheFile.Scake, "scake");

	}    

}