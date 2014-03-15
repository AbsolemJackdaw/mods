package threeDitems.render.item.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.models.bow;
import threeDitems.render.Render3DInterface;

public class RenderBow extends Render3DInterface{

	public RenderBow(ModelBase model, String texture) {
		super(model, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void preSpecials(ItemStack item, ModelBase model, Object... data) {
		super.preSpecials(item, model, data);

		if (((Entity) data[1] instanceof EntityPlayer) && (((EntityPlayer)data[1]).getCurrentEquippedItem() != null)) {

			if((item.getItem() != null) && item.getItem().equals(Item.bow)){

				EntityPlayer player = Minecraft.getMinecraft().thePlayer;
				int count =player.getItemInUseCount();
				int passed = 72000 - count;
				((bow)model).renderBase();
				((bow)model).string.isHidden = false;
				((bow)model).restBow(false);
				((bow)model).pullSlow(false);
				((bow)model).pullHard(false);
				if(passed == 72000)
					((bow)model).restBow(true);
				if((passed >0) && (passed <=5)){
					((bow)model).string.isHidden = true;
					((bow)model).restBow(true);
					//				((bow)model).pullSlow(true);
					if(!((player == Minecraft.getMinecraft().renderViewEntity) &&
							(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0))){

					}else{
						GL11.glTranslatef(0.1f,-0.1f,0.1f);
						GL11.glRotatef(5, 0.0f, 0.0f, 1.0f);
						GL11.glRotatef(-10, 0.0f, 1.0f, 0.0f);
						GL11.glRotatef(-20, 1.0f, 0.0f, 0.0f);
					}

				}
				if((passed >5) && (passed <=18)){
					((bow)model).pullSlow(true);
					if(!((player == Minecraft.getMinecraft().renderViewEntity) &&
							(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0))){

					}else{
						GL11.glTranslatef(0.1f,-0.1f,0.1f);
						GL11.glRotatef(5, 0.0f, 0.0f, 1.0f);
						GL11.glRotatef(-10, 0.0f, 1.0f, 0.0f);
						GL11.glRotatef(-20, 1.0f, 0.0f, 0.0f);
					}
				}
				if((passed >18) && (passed <72000)){
					((bow)model).pullHard(true);
					if(!((player == Minecraft.getMinecraft().renderViewEntity) &&
							(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0))){

					}else{
						GL11.glTranslatef(0.1f,-0.1f,0.1f);
						GL11.glRotatef(5, 0.0f, 0.0f, 1.0f);
						GL11.glRotatef(-10, 0.0f, 1.0f, 0.0f);
						GL11.glRotatef(-20, 1.0f, 0.0f, 0.0f);
					}
				}
			}else{
				((bow)model).renderBase();
				((bow)model).restBow(true);
				((bow)model).pullSlow(false);
				((bow)model).pullHard(false);
			}
		}
		else{
			((bow)model).renderBase();
			((bow)model).string.isHidden = false;
			((bow)model).restBow(true);
			((bow)model).pullSlow(false);
			((bow)model).pullHard(false);
			GL11.glTranslatef(0.1f, -0.1f, 0);
			GL11.glScalef(0.7f, 0.7f, 0.7f);
		}
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(90,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 0f, 0f);
		GL11.glScalef(5,5,5);

	}

	@Override
	public void renderEquipped() {

		GL11.glRotatef(90-15,0,1,0);
		GL11.glRotatef(180-15,0,0,1);
		GL11.glRotatef(220,1,0,0);

		GL11.glTranslatef(-0.2f,0.2f,-1f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(-45,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(-1f, -1f, -0.5f);

		GL11.glScalef(2, 2, 2);
	}

	//	@Override
	@Override
	public void renderScale() {
		float f = 1f;
		GL11.glScalef(f, f, f);
	}

	//	@Override
	//	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
	//		super.renderItem(type, item, data);
	//		if(!((Entity)data[1] instanceof EntityPlayer)){
	//
	//		}
	//	}
}
