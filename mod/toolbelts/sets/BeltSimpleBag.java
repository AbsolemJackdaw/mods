package toolbelts.sets;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;

import org.lwjgl.opengl.GL11;

public class BeltSimpleBag extends BeltBase{

	public BeltSimpleBag(SetArmorModel evt) {
		super(evt);
	}

	@Override
	public void renderDefault(SetArmorModel evt) {
		super.renderDefault(evt);
		
		float f = 0.1f;
		float delta = -0.17f;
		for (int i = 0; i < evt.entityPlayer.inventory.getSizeInventory(); i ++){
			ItemStack is = evt.entityPlayer.inventory.getStackInSlot(i);
			if(is != null){
				f += 0.005f;
				delta += 0.00425f;
			}
		}
				
		ItemStack st = new ItemStack(Block.stainedClay, 1 , 12);
		renderShoulderBelt(st, evt.entityPlayer);
		
		ItemStack chest = new ItemStack(Block.chest);
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5f, 0.1f+delta, 0.15f);
		addItemToBelt(chest, evt.entityPlayer, 14, f);
		GL11.glPopMatrix();
		
	}
}
