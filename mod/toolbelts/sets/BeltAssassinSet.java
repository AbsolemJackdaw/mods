package toolbelts.sets;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.opengl.GL11;

public class BeltAssassinSet extends BeltBase {

	public BeltAssassinSet(RenderPlayerEvent.SetArmorModel evt){
		super(evt);
	}

	public void renderDefault(RenderPlayerEvent.SetArmorModel evt ){
		
		float scale = 0.3f;

		renderHeldSword(heldItem, evt.entityPlayer);

		int totalFilledSlots = 0;
		for (int i = 0; i < evt.entityPlayer.inventory.getSizeInventory(); i ++){
			ItemStack is = evt.entityPlayer.inventory.getStackInSlot(i);
			if(is != null){

				totalFilledSlots ++;

				if(is.getItem().equals(Item.bow)){
					
					float f = 0;
					GL11.glPushMatrix();
					GL11.glTranslatef(0.2f, -0.2f, 0.0f);
					GL11.glRotatef(180, 0, 1, 0);
					addItemToBelt(is, evt.entityPlayer, 8, 0.7f);
					GL11.glPopMatrix();
				}

				if(is.getItem().equals(Item.arrow)){
					
					GL11.glPushMatrix();
					GL11.glTranslatef(0.1f, -0.15f, -0f);
					GL11.glRotatef(180, 0, 1, 0);
					addItemToBelt(is, evt.entityPlayer, 13 , 0.4f);
					GL11.glPopMatrix();
				}
			}
		}

		ItemStack st = new ItemStack(Block.cloth, 1 , 14);
		renderBelt(st, evt.entityPlayer);
		renderShoulderBelt(st, evt.entityPlayer);

		ItemStack chest = new ItemStack(Block.chest);

		/*2 front chests*/
		if(totalFilledSlots > 5){
			GL11.glPushMatrix();
			GL11.glRotatef(-90, 0, 1, 0);
			GL11.glTranslatef(-0.3f,0.15f,-0.05f);
			GL11.glScalef(0.8f,0.8f,0.8f);
			addItemToBelt(chest, evt.entityPlayer, 11, 0);
			GL11.glPopMatrix();

			if(totalFilledSlots > 10){
				GL11.glPushMatrix();
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslatef(-0.28f,0.15f,0.32f);
				GL11.glScalef(0.8f,0.8f,0.8f);
				addItemToBelt(chest, evt.entityPlayer, 11, 0);
				GL11.glPopMatrix();
			}
			
			if(totalFilledSlots > 15){
				
				/*6 small chests*/
				GL11.glPushMatrix();
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslatef(-0.2f,0.5f,0.1f);
				GL11.glScalef(0.2f,0.2f,0.2f);
				addItemToBelt(chest, evt.entityPlayer, 10, 0);
				GL11.glPopMatrix();
				
				if(totalFilledSlots > 17){
					GL11.glPushMatrix();
					GL11.glRotatef(-90, 0, 1, 0);
					GL11.glTranslatef(-0.2f,0.5f,0.05f);
					GL11.glScalef(0.2f,0.2f,0.2f);
					addItemToBelt(chest, evt.entityPlayer, 10, 0);
					GL11.glPopMatrix();

					if(totalFilledSlots > 19){
						GL11.glPushMatrix();
						GL11.glRotatef(-90, 0, 1, 0);
						GL11.glTranslatef(-0.2f,0.55f,0.1f);
						GL11.glScalef(0.2f,0.2f,0.2f);
						addItemToBelt(chest, evt.entityPlayer, 10, 0);
						GL11.glPopMatrix();

						if(totalFilledSlots > 25){
							GL11.glPushMatrix();
							GL11.glRotatef(-90, 0, 1, 0);
							GL11.glTranslatef(-0.2f,0.55f,0.05f);
							GL11.glScalef(0.2f,0.2f,0.2f);
							addItemToBelt(chest, evt.entityPlayer, 10, 0);
							GL11.glPopMatrix();

							if(totalFilledSlots > 27){
								GL11.glPushMatrix();
								GL11.glRotatef(-90, 0, 1, 0);
								GL11.glTranslatef(-0.2f,0.55f,0.0f);
								GL11.glScalef(0.2f,0.2f,0.2f);
								addItemToBelt(chest, evt.entityPlayer,10, 0);
								GL11.glPopMatrix();

								if(totalFilledSlots > 29){
									GL11.glPushMatrix();
									GL11.glRotatef(-90, 0, 1, 0);
									GL11.glTranslatef(-0.2f,0.5f,0.0f);
									GL11.glScalef(0.2f,0.2f,0.2f);
									addItemToBelt(chest, evt.entityPlayer, 10, 0);
									GL11.glPopMatrix();

								}
							}
						}
					}
				}
			}
		}

		/*butt chest*/
		GL11.glPushMatrix();
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glTranslatef(-0.3f, 0.05f, 0.12f);
		GL11.glScalef(1-0.2f, 1, 1+0.5f);
		addItemToBelt(chest, evt.entityPlayer, 12, 0);
		GL11.glPopMatrix();
	}
}
