package scythemod.weaponrender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import scythemod.BaseScytheFile;
import scythemod.item.FireScythe;
import scythemod.item.ItemSwordTest2;

public class ScytheRenderer implements IItemRenderer {

	ModelBase model;
	String textureFilePath;

	public ScytheRenderer(ModelBase scythemodel, String texture){
		model = scythemodel;
		textureFilePath = texture;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type){
		case  EQUIPPED: 
			return true;
		case  EQUIPPED_FIRST_PERSON: 
			return true;
		case  ENTITY:
			return false;
		default: 
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch(type){

		case  EQUIPPED:
			ScytheInHand hand = new ScytheInHand();
			hand.renderInHand(type, item, textureFilePath, model, data);
			break;
		case  EQUIPPED_FIRST_PERSON:
			ScytheInHand sh = new ScytheInHand();
			sh.renderInHand(type, item, textureFilePath, model, data);
			break;
		case  ENTITY:
			ScytheInWorld siw = new ScytheInWorld();
			siw.renderInWorld(item, textureFilePath, model, data);
			break;

		default:
			break;
		}
	}

}
