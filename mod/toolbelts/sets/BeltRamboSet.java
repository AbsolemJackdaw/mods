package toolbelts.sets;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

public class BeltRamboSet extends BeltBase{

	public BeltRamboSet(RenderPlayerEvent.SetArmorModel evt){
		super(evt);
	}

	public void renderDefault(RenderPlayerEvent.SetArmorModel evt ){

		float scale = 0.3f;
		
		renderHeldSword(heldItem, evt.entityPlayer);

		for (int i = 0; i < evt.entityPlayer.inventory.getSizeInventory(); i ++){
			ItemStack is = evt.entityPlayer.inventory.getStackInSlot(i);
			if(is != null){
				if(is.getItem().equals(Item.potion)){
					
					float f = 0;
					GL11.glPushMatrix();
					GL11.glTranslatef(-0.35f, -0.15f, 0f);
					addItemToBelt(is, evt.entityPlayer,6, scale);
					GL11.glPopMatrix();
				}
				if(is.getItem().equals(Item.bow)){
					
					float f = 0;
					GL11.glPushMatrix();
					GL11.glTranslatef(0.2f, -0.2f, 0.0f);
					GL11.glRotatef(180, 0, 1, 0);
					addItemToBelt(is, evt.entityPlayer,8, 0.7f);
					GL11.glPopMatrix();
				}
				if(is.itemID == Block.torchWood.blockID){
					
					GL11.glPushMatrix();
					GL11.glTranslatef(-0.07f, -0.5f, 0f);
					addItemToBelt(is, evt.entityPlayer, 7 , scale + 0.1f);
					GL11.glPopMatrix();
				}
				if(is.getItem().equals(Item.arrow)){
					
					GL11.glPushMatrix();
					GL11.glTranslatef(0.2f, -0.3f, -0.3f);
					GL11.glRotatef(180, 0, 1, 0);
					addItemToBelt(is, evt.entityPlayer, 9 , 0.4f);
					GL11.glPopMatrix();
				}
			}
		}

		ItemStack st = new ItemStack(Block.stainedClay, 1 , 12);
		renderBelt(st, evt.entityPlayer);
		renderShoulderBelt(st, evt.entityPlayer);

		ItemStack chest = new ItemStack(Block.chest);
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5f, 0.1f, 0.15f);
		addItemToBelt(chest, evt.entityPlayer, 14, scale);
		GL11.glPopMatrix();
	}
}
