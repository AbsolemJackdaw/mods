package gravestone.bones;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TESRBones extends TileEntitySpecialRenderer {


	private ModelBones bones = new ModelBones();
	private ModelSkullAndBones block = new ModelSkullAndBones();
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double d, double d1,
			double d2, float f) {

		TEBones bone = (TEBones)tile;
		String icon;
		float rot = bone.rotation;
		float red = 1.0f;
		float green = 1.0f;
		float blue = 1.0f;
		int blockcolor = bone.getSat();
		red = ((float) ((blockcolor>> 16) & 0xFF)) / 255.0F;
		green = ((float) ((blockcolor >>8) & 0xFF)) / 255.0F;
		blue = ((float)(blockcolor & 0xFF)) / 255.0F;

		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0+rot, 0.0F, 1.0F, 0.0F);
			this.tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("subaraki:grave/skull.png"));
		bones.render(0.0625f);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glColor3f(red, green, blue);	

		if(bone != null){
			
			icon = bone.texture;
			if(icon.length() >0)
					this.tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("textures/blocks/"+icon+".png"));
			else
					this.tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("textures/blocks/dirt.png"));
		}

		
		block.render(0.0625f);
		GL11.glPopMatrix();
	}
}
