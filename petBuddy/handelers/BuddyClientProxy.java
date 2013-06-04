package petBuddy.handelers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.entity.player.EntityPlayer;
import petBuddy.entity.BatBuddy;
import petBuddy.entity.BlazeBuddy;
import petBuddy.entity.ChickenBuddy;
import petBuddy.entity.CowBuddy;
import petBuddy.entity.CreeperBuddy;
import petBuddy.entity.EnderManBuddy;
import petBuddy.entity.EntityBuddy;
import petBuddy.entity.GhastBuddy;
import petBuddy.entity.GolemBuddy;
import petBuddy.entity.MooshroomBuddy;
import petBuddy.entity.OcelotBuddy;
import petBuddy.entity.PigBuddy;
import petBuddy.entity.PigManBuddy;
import petBuddy.entity.SheepBuddy;
import petBuddy.entity.SilverFishBuddy;
import petBuddy.entity.SkeletonBuddy;
import petBuddy.entity.SkeletonWBuddy;
import petBuddy.entity.SnowManBuddy;
import petBuddy.entity.SpiderBuddy;
import petBuddy.entity.SpiderCaveBuddy;
import petBuddy.entity.SpiderRpgBuddy;
import petBuddy.entity.SquidBuddy;
import petBuddy.entity.VillagerBuddy;
import petBuddy.entity.WitchBuddy;
import petBuddy.entity.WolfBuddy;
import petBuddy.entity.ZombieBuddy;
import petBuddy.entity.gui.PetInterface;
import petBuddy.entity.model.SheepBody;
import petBuddy.root.BuddyBase;
import petBuddy.root.RenderBuddy;
import cpw.mods.fml.client.registry.RenderingRegistry;



public class BuddyClientProxy extends BuddyCommonProxy{

	public void render() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(PigBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(CowBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(CreeperBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(BlazeBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(GhastBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(SkeletonBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(SkeletonWBuddy.class, new RenderBuddy(0.4f,0.5f));
		RenderingRegistry.registerEntityRenderingHandler(SpiderBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(SpiderCaveBuddy.class, new RenderBuddy(0.2f,0.3f));
		RenderingRegistry.registerEntityRenderingHandler(SpiderRpgBuddy.class, new RenderBuddy(0.4f,0.5f));
		RenderingRegistry.registerEntityRenderingHandler(ZombieBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(SheepBuddy.class, new RenderBuddy(new SheepBody(), 0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(EnderManBuddy.class, new RenderBuddy(new ModelEnderman(),0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(SilverFishBuddy.class, new RenderBuddy(0.2f,0.5f));
		RenderingRegistry.registerEntityRenderingHandler(SnowManBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(GolemBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(WitchBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(BatBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(ChickenBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(MooshroomBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(OcelotBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(SquidBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(VillagerBuddy.class, new RenderBuddy(0.3f,0.4f));
		RenderingRegistry.registerEntityRenderingHandler(WolfBuddy.class, new RenderBuddy(0.3f,0.5f));
		RenderingRegistry.registerEntityRenderingHandler(PigManBuddy.class, new RenderBuddy(0.3f,0.4f));
	}

	public void openGui(int id, EntityPlayer player, String name)
	{
		Minecraft.getMinecraft().displayGuiScreen(new PetInterface(player, name));
	}
}
