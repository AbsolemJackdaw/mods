package toolbelts.render;

import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;
import toolbelts.PlayerBeltTracker;
import toolbelts.sets.BeltAssassinSet;
import toolbelts.sets.BeltDefaultSet;
import toolbelts.sets.BeltHotBar;
import toolbelts.sets.BeltIChunSet;
import toolbelts.sets.BeltRamboSet;
import toolbelts.sets.BeltSimpleBag;
import toolbelts.sets.BeltSurvivorSet;

public class RenderHipItemsOnPlayer {

	@ForgeSubscribe
	public void PlayerRender(RenderPlayerEvent.SetArmorModel evt ){
		
		switch (PlayerBeltTracker.get(evt.entityPlayer).BeltID) {
		case 0:
			new BeltDefaultSet(evt);
			break;
		case 1:
			new BeltRamboSet(evt);
			break;
		case 2:
			new BeltAssassinSet(evt);
			break;
		case 3:
			new BeltSimpleBag(evt);
			break;
		case 4:
			new BeltHotBar(evt);
			break;
		case 5:
			new BeltSurvivorSet(evt);
			break;
		case 6:
			new BeltIChunSet(evt);
			break;
		default:
			new BeltDefaultSet(evt);
			break;
		}
	}
}
