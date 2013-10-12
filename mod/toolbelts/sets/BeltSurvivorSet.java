package toolbelts.sets;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;

public class BeltSurvivorSet extends BeltBase{

	public BeltSurvivorSet(SetArmorModel evt) {
		super(evt);
	}

	@Override
	public void renderDefault(SetArmorModel evt) {

		renderHeldSword(heldItem, evt.entityPlayer);
		renderHeldPickaxe(heldItem, evt.entityPlayer);

		if(evt.entityPlayer.inventory.hasItem(Item.bed.itemID)){
			ItemStack wool = new ItemStack(Block.cloth, 1, 14);

			if(!evt.entityPlayer.isSneaking()){
				GL11.glPushMatrix();
				GL11.glScalef(4f, 1, 1f);
				GL11.glTranslatef(-0.2f, -0.71f, 0.435f);
				addItemToBelt(wool, evt.entityPlayer, 2, 0.3f);
				GL11.glPopMatrix();
			}else{
				GL11.glPushMatrix();
				GL11.glRotatef(30, 1, 0, 0);
				GL11.glScalef(4f, 1, 1f);
				GL11.glTranslatef(-0.2f, -0.71f, 0.435f);
				addItemToBelt(wool, evt.entityPlayer, 100, 0.3f);
				GL11.glPopMatrix();
			}

		}

		ItemStack chest = new ItemStack(Block.chest);
		GL11.glPushMatrix();
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glTranslatef(-0.3f, -0.3f, 0.15f);
		addItemToBelt(chest, evt.entityPlayer, 17, 0.5f);
		GL11.glPopMatrix();

		ItemStack wool = new ItemStack(Block.cloth, 1, 12);

		renderBelt(wool, evt.entityPlayer);


		if(!evt.entityPlayer.isSneaking()){
			GL11.glPushMatrix();
			GL11.glScalef(1, 1, 0.5f);
			GL11.glTranslatef(-0.2f, -0.35f, 0.25f);
			addItemToBelt(wool, evt.entityPlayer, 0, 0.4f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glScalef(1f, 1, 3f);
			GL11.glTranslatef(-0.05f, -0.7f, 0.15f);
			addItemToBelt(wool, evt.entityPlayer, 0, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glScalef(1f, 1, 3f);
			GL11.glTranslatef(-0.35f, -0.7f, 0.15f);
			addItemToBelt(wool, evt.entityPlayer, 0, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glScalef(1f, 3, 1f);
			GL11.glTranslatef(-0.35f, -0.645f, 0.05f);
			addItemToBelt(wool, evt.entityPlayer, 0, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glScalef(1f, 3, 1f);
			GL11.glTranslatef(-0.05f, -0.645f, 0.05f);
			addItemToBelt(wool, evt.entityPlayer, 0, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glScalef(1.5f, 1, 3f);
			GL11.glTranslatef(-0.32f, -0.31f, 0.15f);
			addItemToBelt(wool, evt.entityPlayer, 0, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glScalef(1.5f, 1, 3f);
			GL11.glTranslatef(-0.08f, -0.31f, 0.15f);
			addItemToBelt(wool, evt.entityPlayer, 0, 0.1f);
			GL11.glPopMatrix();
		}else{
			GL11.glPushMatrix();
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glScalef(1, 1, 0.5f);
			GL11.glTranslatef(-0.2f, -0.35f, 0.25f);
			addItemToBelt(wool, evt.entityPlayer, 100, 0.4f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glScalef(1f, 1, 3f);
			GL11.glTranslatef(-0.05f, -0.7f, 0.15f);
			addItemToBelt(wool, evt.entityPlayer, 100, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glScalef(1f, 1, 3f);
			GL11.glTranslatef(-0.35f, -0.7f, 0.15f);
			addItemToBelt(wool, evt.entityPlayer, 100, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glScalef(1f, 3, 1f);
			GL11.glTranslatef(-0.35f, -0.645f, 0.05f);
			addItemToBelt(wool, evt.entityPlayer, 100, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glScalef(1f, 3, 1f);
			GL11.glTranslatef(-0.05f, -0.645f, 0.05f);
			addItemToBelt(wool, evt.entityPlayer, 100, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glScalef(1.5f, 1, 3f);
			GL11.glTranslatef(-0.32f, -0.31f, 0.15f);
			addItemToBelt(wool, evt.entityPlayer, 100, 0.1f);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glRotatef(30, 1, 0, 0);
			GL11.glScalef(1.5f, 1, 3f);
			GL11.glTranslatef(-0.08f, -0.31f, 0.15f);
			addItemToBelt(wool, evt.entityPlayer, 100, 0.1f);
			GL11.glPopMatrix();	
		}
	}
}
