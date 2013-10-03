package petBuddy.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class PetShrineRender implements IItemRenderer {

	private static final ResourceLocation LOC = new ResourceLocation("subaraki:mobs/Shrine.png");

	public PetShrineRender() {
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

		Minecraft.getMinecraft().renderEngine.bindTexture(LOC);
	
		switch(type){

		case EQUIPPED:
			GL11.glPushMatrix();

//			GL11.glScalef(0.5f,0.5f,0.5f);

//			GL11.glRotatef(2, 1, 0, 0);
			GL11.glRotatef(15, 0, 0, 1);
			GL11.glRotatef(2, 0, 1, 0);

			GL11.glTranslatef(0f, -0.3f, -0.5f);

			TileEntityRenderer.instance.renderTileEntityAt(new TEShrine(), 0.0D, 0.0D, 0.0D, 0.0F);

			GL11.glPopMatrix();
			
			break;
			
		case EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();

			GL11.glTranslatef(0.1F,0.1f,-0.5f);
//			GL11.glRotatef(-180f, 1.0f, 0.0f, 0.0f);
//			GL11.glRotatef(-40f, 0.0f, 1.0f, 0.0f);

			TileEntityRenderer.instance.renderTileEntityAt(new TEShrine(), 0.0D, 0.0D, 0.0D, 0.0F);
			GL11.glPopMatrix();
			break;
			
		case ENTITY:
			GL11.glPushMatrix();

//			GL11.glScalef(0.5f,0.5f,0.5f);

//			GL11.glRotatef(180, 1, 0, 0);
//			GL11.glTranslatef(0f, -1.8f, 0f);

			TileEntityRenderer.instance.renderTileEntityAt(new TEShrine(), 0.0D, 0.0D, 0.0D, 0.0F);
			
			GL11.glPopMatrix();
			break;

		case INVENTORY:
			GL11.glPushMatrix();

//			GL11.glScalef(0.7f,0.7f,0.7f);

			GL11.glTranslatef(0.0F, 0.2F, 0.0F);
			GL11.glScalef(1.3f, 1.3f, 1.3f);
//			GL11.glRotatef(180f, 0f, 0f, 1.0f);

			TileEntityRenderer.instance.renderTileEntityAt(new TEShrine(), 0.0D, 0.0D, 0.0D, 0.0F);

			GL11.glPopMatrix();
			break;

		default:
			break;
		}		
	}
}
