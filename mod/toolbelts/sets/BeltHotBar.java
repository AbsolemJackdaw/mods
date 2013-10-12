package toolbelts.sets;

import net.minecraft.block.Block;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;

import org.lwjgl.opengl.GL11;

public class BeltHotBar extends BeltBase {

	public BeltHotBar(SetArmorModel evt) {
		super(evt);
	}

	int slot = 0;

	float offset = 0f;

	@Override
	public void renderDefault(SetArmorModel evt) {

		for (int i = 0; i < evt.entityPlayer.inventory.getSizeInventory(); i ++){
			ItemStack is = evt.entityPlayer.inventory.getStackInSlot(i);
			if(is != null){
				if(is.getItem() instanceof ItemSword || is.getItem() instanceof ItemPickaxe){
				}else{
					slot++;
					if(slot < 5){
						offset+=0.16f;
						GL11.glPushMatrix();
						GL11.glTranslatef(-0.6f + offset, -0.05f, 0f);
						addItemToBelt(is, evt.entityPlayer,15, 0.15f);
						GL11.glPopMatrix();
					}else if(slot < 9){
						offset-=0.16f;
						GL11.glPushMatrix();
						GL11.glRotatef(180, 0, 1, 0);
						GL11.glTranslatef(-0.45f+offset, -0.05f, 0f);
						addItemToBelt(is, evt.entityPlayer,16, 0.15f);
						GL11.glPopMatrix();
					}else{
						slot = 0;
						offset = 0;
						break;
					}	
				}
			}
		}
		ItemStack st = new ItemStack(Block.cloth, 1 , 12);
		renderBelt(st, evt.entityPlayer);

		renderHeldSword(heldItem, evt.entityPlayer);
		renderHeldPickaxe(heldItem, evt.entityPlayer);

	}
}
