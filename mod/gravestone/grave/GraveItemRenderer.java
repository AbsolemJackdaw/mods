package gravestone.grave;

import gravestone.grave.te.TEGrave;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class GraveItemRenderer implements IItemRenderer {

	private ModelGrave grave;

	public GraveItemRenderer() {
		grave = new ModelGrave();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case  EQUIPPED: return true;
		case  EQUIPPED_FIRST_PERSON: return true;
		case ENTITY: return true;
		case INVENTORY: return true;
		default: 
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch(type)
		{
		case INVENTORY: return true;
		default: break;
		}
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch(type){

		case EQUIPPED:
			GL11.glPushMatrix();
			grave.showBasic(true);
			grave.showZerk(false);
			grave.showTomb(false);
			grave.showPillar(false);
			grave.renderSkeleton(false);
			grave.renderCross(false);
			grave.renderAngel(false);
			grave.renderKnight(false);

			GL11.glRotatef(30, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(-10, 1.0f, 0.0f, 0.0f);

			GL11.glTranslatef(0.1F, -0.2F, -0.6F);
			TileEntityRenderer.instance.renderTileEntityAt(new TEGrave(), 0.0D, 0.0D, 0.0D, 0.0F);

			GL11.glPopMatrix();
			break;
			//		case  EQUIPPED_FIRST_PERSON:
			//			GL11.glPushMatrix();
			//			grave.showBasic(true);
			//			grave.showZerk(false);
			//			grave.showTomb(false);
			//			grave.showPillar(false);
			//			grave.renderSkeleton(false);
			//			grave.renderCross(false);
			//			grave.renderAngel(false);
			//			grave.renderKnight(false);
			//
			//			GL11.glRotatef(30, 0.0f, 0.0f, 1.0f);
			//			GL11.glRotatef(-10, 1.0f, 0.0f, 0.0f);
			//
			//			GL11.glTranslatef(0.1F, -0.2F, -0.6F);
			//			TileEntityRenderer.instance.renderTileEntityAt(new TEGrave(), 0.0D, 0.0D, 0.0D, 0.0F);
			//
			//			GL11.glPopMatrix();
			//			break;
		case ENTITY:
			TileEntityRenderer.instance.renderTileEntityAt(new TEGrave(), 0.0D, 0.0D, 0.0D, 0.0F);
			break;
		case INVENTORY:
			GL11.glPushMatrix();
			grave.showBasic(true);
			grave.showZerk(false);
			grave.showTomb(false);
			grave.showPillar(false);
			grave.renderSkeleton(false);
			grave.renderCross(false);
			grave.renderAngel(false);
			grave.renderKnight(false);
			GL11.glTranslatef(0.0F, -0.1F, 0.0F);
			GL11.glScalef(1.4f, 1.2f, 1.4f);
			TileEntityRenderer.instance.renderTileEntityAt(new TEGrave(), 0.0D, 0.0D, 0.0D, 0.0F);
			GL11.glPopMatrix();
			break;
		default:
			if("EQUIPPED_FIRST_PERSON".equals(type.name())){
				GL11.glPushMatrix();
				grave.showBasic(true);
				grave.showZerk(false);
				grave.showTomb(false);
				grave.showPillar(false);
				grave.renderSkeleton(false);
				grave.renderCross(false);
				grave.renderAngel(false);
				grave.renderKnight(false);

				GL11.glRotatef(30, 0.0f, 0.0f, 1.0f);
				GL11.glRotatef(-10, 1.0f, 0.0f, 0.0f);

				GL11.glTranslatef(0.1F, -0.2F, -0.6F);
				TileEntityRenderer.instance.renderTileEntityAt(new TEGrave(), 0.0D, 0.0D, 0.0D, 0.0F);

				GL11.glPopMatrix();
			}

		}		
	}
}
