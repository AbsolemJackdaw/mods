package threeDitems.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import threeDitems.models.bottle;
import threeDitems.render.Render3DInterface;

public class RenderPotion extends Render3DInterface{

	private final ResourceLocation content;

	public RenderPotion(ModelBase model, String texture) {
		super(model, texture);
		content = new ResourceLocation("subaraki:3d/items/bottle.png");
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(190,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, -0.7f, 0.5f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 0f, 0f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(15,0,0,1);
		GL11.glRotatef(180,1,0,0);
			
		GL11.glTranslatef(0.4f, -0.2f, -0.3f);	
		
		float f = 0.5f;
		GL11.glScalef(f, f, f);			
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);		
	}

//	@Override
//	public void preSpecials(ItemStack item, ModelBase model, Object...data) {
//		// TODO Auto-generated method stub
//		super.preSpecials(item, model);
//	}

	@Override
	public void postSpecials(ItemStack item,ModelBase model, Object... data) {
		super.postSpecials(item, model);
		
		if(item.getItem() instanceof ItemPotion|| item.getItem().equals(Item.expBottle)){

			Minecraft.getMinecraft().renderEngine.bindTexture(content);
//			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
//			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);

			if(item.getItem().equals(Item.potion) || item.getItem().equals(Item.glassBottle))
			{
				int color = ((ItemPotion)item.getItem()).getColorFromDamage(item.getItemDamage());
				float red = (float)(color >> 16 & 255) / 255.0F;
				float green = (float)(color >> 8 & 255) / 255.0F;
				float blue = (float)(color & 255) / 255.0F;					
				GL11.glColor4f(red, green,blue, 1.0F);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
				GL11.glEnable(GL11.GL_BLEND);
				((bottle)model).renderContent(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				GL11.glDisable(GL11.GL_BLEND);
			}else if(item.getItem().equals(Item.expBottle)){
				GL11.glColor4f(0.7f, 1.0f, 0.0f, 1.0F);
				((bottle)model).renderContent(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}
		}

		if(item.getItem() instanceof ItemPotion || 
				item.getItem().equals(Item.glassBottle)||
				item.getItem().equals(Item.expBottle)){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(0.2f, 0.2f, 0.2f, 0.2f);
			((bottle)model).renderBottle(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
	}
}
