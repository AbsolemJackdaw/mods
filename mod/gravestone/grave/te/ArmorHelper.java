package gravestone.grave.te;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class ArmorHelper {

	/**
	 * Set the specified armor model as the player model. Args: player, armorSlot, partialTick
	 */
	public void setArmorModel(ModelBiped model, ItemStack itemstack, int par2, String armor)
	{

		if (itemstack != null)
		{
			Item item = itemstack.getItem();

			if (item instanceof ItemArmor)
			{
				ItemArmor itemarmor = (ItemArmor)item;
				Minecraft.getMinecraft().renderEngine.bindTexture(RenderBiped.getArmorResource(null, itemstack, par2, null));

				boolean for0 = par2 == 0;
				boolean for1 = par2 == 1;
				boolean for2 = par2 == 2;
				boolean for3 = par2 == 3;

				model.bipedHead.showModel = for0;
				model.bipedHeadwear.showModel = for0;
				model.bipedBody.showModel =  for1 || for2 ;
				model.bipedRightArm.showModel = for1;
				model.bipedLeftArm.showModel = for1;
				model.bipedRightLeg.showModel = for2 || for3;
				model.bipedLeftLeg.showModel = for2 || for3;

				
				float f1 = 1.0F;
				GL11.glColor3f(f1, f1, f1);

				if (itemarmor.getArmorMaterial() == EnumArmorMaterial.CLOTH)
				{
					int j = itemarmor.getColor(itemstack);
					float f2 = (float)(j >> 16 & 255) / 255.0F;
					float f3 = (float)(j >> 8 & 255) / 255.0F;
					float f4 = (float)(j & 255) / 255.0F;
//					GL11.glColor3f(0f,0f,1f);

					GL11.glColor3f(f1 * f2, f1 * f3, f1 * f4);
				}
//				
			}
		}
	}
}
