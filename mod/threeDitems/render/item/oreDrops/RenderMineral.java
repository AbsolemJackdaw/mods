package threeDitems.render.item.oreDrops;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import threeDitems.mod_3d;
import threeDitems.render.Render3DInterface;

public class RenderMineral extends Render3DInterface {

	
	RenderItem rend = new RenderItem();

	
	public RenderMineral(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(90,1,0,0);
			
		GL11.glTranslatef(0f, 0.5f, -01f);		
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0f, 0f, 0f);		
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(-75,0,1,0);
		GL11.glRotatef(0,0,0,1);
		GL11.glRotatef(105,1,0,0);
			
		GL11.glTranslatef(0.1f,-0.4f,-0.3f);	
		
		float f = 0.7f;
		GL11.glScalef(f, f, f);			

	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);		
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
//		super.handleRenderType(item, type);
		if(item.getItem()instanceof ItemDye){
			if(item.getItemDamage() == 4)
				return mod_3d.inst.isRendering3D ?type != type.INVENTORY && !rend.renderInFrame :true;
			else
				return false;
		}
		return mod_3d.inst.isRendering3D ?type != type.INVENTORY && !rend.renderInFrame :false;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
//		super.handleRenderType(item, type);
		if(item.getItem()instanceof ItemDye){
			if(item.getItemDamage() == 4)
				return mod_3d.inst.isRendering3D ? type == type.EQUIPPED_FIRST_PERSON ? true : false  : true;
			else
				return false;
		}
		return mod_3d.inst.isRendering3D ? type == type.EQUIPPED_FIRST_PERSON ? true : false  : true;
	}


}