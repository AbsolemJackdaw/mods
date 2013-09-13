package betterbreeds.threeD;

import threeDitems.Render3DItem;
import threeDitems.models.bread;
import threeDitems.models.bucket;
import threeDitems.models.egg;
import threeDitems.models.meat;
import threeDitems.render.item.RenderBucket;
import threeDitems.render.item.food.RenderBread;
import threeDitems.render.item.food.RenderEGg;
import threeDitems.render.item.food.RenderMeat;
import betterbreeds.ModBreeds;
import cpw.mods.fml.common.FMLLog;

public class clp extends cmp{

	@Override
	public void render() {
		super.render();
		
		Render3DItem.inst.add3DItem(ModBreeds.Sheepmilk, new RenderBucket(new bucket(),"subaraki:3d/items/bucketMilk.png"));
		Render3DItem.inst.add3DItem(ModBreeds.BlackBread, new RenderBread(new bread(),"subaraki:3d/items/breadMeat.png"));
		Render3DItem.inst.add3DItem(ModBreeds.Sandwich, new RenderBread(new bread(),"subaraki:3d/items/breadEgg.png"));
		Render3DItem.inst.add3DItem(ModBreeds.SweetBread, new RenderBread(new bread(),"subaraki:3d/items/breadSweet.png"));
		Render3DItem.inst.add3DItem(ModBreeds.ChocoBrood, new RenderBread(new bread(),"subaraki:3d/items/breadC.png"));
		Render3DItem.inst.add3DItem(ModBreeds.horsemeat, new RenderMeat(new meat(),"subaraki:3d/items/beef.png"));
		Render3DItem.inst.add3DItem(ModBreeds.horsemeatCooked, new RenderMeat(new meat(),"subaraki:3d/items/beefCooked.png"));
		Render3DItem.inst.add3DItem(ModBreeds.EasterEgg, new RenderEGg(new egg(),"subaraki:3d/items/eggC.png"));

		FMLLog.getLogger().info("[MODBREEDS]Items Renderer");

	}	
}
