package threeDitems.render.item.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.helper.ArmorHelper;
import threeDitems.render.Render3DInterface;

public class RenderArmorLegs extends Render3DInterface{

	public static String[] armorFilenamePrefix = new String[] {"cloth", "chain", "iron", "diamond", "gold"};

	private static final ArmorHelper ah= new ArmorHelper();

	public RenderArmorLegs(ModelBase model, String texture) {
		super(model, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(90-(45/2),0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0.3f, -4f, 0.2f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(90,1,0,0);
			
		GL11.glTranslatef(0f, -9f, 0f);		
		
		float f = 3f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(30,1,0,0);
			
		GL11.glTranslatef(0f, -3f, 0.9f);		
	}

	@Override
	public void renderScale() {
		float f = 2.5f;
		GL11.glScalef(f, f, f);		
	}

	@Override
	public void preSpecials(ItemStack item, ModelBase model) {
		super.preSpecials(item, model);
		ah.setArmorModel((ModelBiped)model, item, 
				((ItemArmor)item.getItem()).armorType, armorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
	}

	/**Do not use this unless you know what you are doing !
	 * Used for Armor and minecarts to ignore the actual loading of the primary, 
	 * If you use this, make sure to manually load a texture*/
	@Override @Deprecated protected boolean shouldIgnoreTextureRendering() {
		return true;
	}
}
