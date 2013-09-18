package threeDitems.render.item.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.helper.ArmorHelper;
import threeDitems.render.Render3DInterface;

public class RenderArmorBoots extends Render3DInterface{


	private static final ArmorHelper ah= new ArmorHelper();

	public RenderArmorBoots(ModelBase model, String texture) {
		super(model, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderEquippedFP() {

		GL11.glRotatef(90-(45/2),0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0.3f, -4.5f, 0.2f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(90,1,0,0);

		GL11.glTranslatef(0f, -11f, 0f);		

		float f = 3f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(20,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(-10,1,0,0);

		GL11.glTranslatef(-0.3f, -3.7f, 0.3f);		
	}

	@Override
	public void renderScale() {
		float f = 2.5f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void preSpecials(ItemStack item, ModelBase model, Object...data) {
		super.preSpecials(item, model);
		ah.setArmorModel((ModelBiped)model, item, 
				((ItemArmor)item.getItem()).armorType, RenderBiped.bipedArmorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
	}

	/**Do not use this unless you know what you are doing !
	 * Used for Armor and minecarts to ignore the actual loading of the primary, 
	 * If you use this, make sure to manually load a texture*/
	@Override @Deprecated protected boolean shouldIgnoreTextureRendering() {
		return true;
	}
}
