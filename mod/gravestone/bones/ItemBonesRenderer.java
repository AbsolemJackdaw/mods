package gravestone.bones;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemBonesRenderer implements IItemRenderer {

	private ModelBones grave;
	private TEBones te = new TEBones();
	public ItemBonesRenderer() {
		grave = new ModelBones();
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
		te.setSat(0xffffff);
		switch(type){

		case  EQUIPPED:
			GL11.glPushMatrix();
			GL11.glRotatef(45, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(-10, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0.5F, 0.0F, -0.5F);
			TileEntityRenderer.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);

			GL11.glPopMatrix();
			break;
		case  EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			GL11.glRotatef(45, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(-10, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0.5F, 0.0F, -0.5F);
			TileEntityRenderer.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);

			GL11.glPopMatrix();
			break;
		case ENTITY:
			TileEntityRenderer.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);
			break;
		case INVENTORY:
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.1F, 0.0F);
			GL11.glScalef(1f, 1f, 1f);
			TileEntityRenderer.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);
			GL11.glPopMatrix();
			break;
		default:
			break;

		}		
	}
}
