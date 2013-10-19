package redstone.item.render;

import java.nio.FloatBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import redstone.item.ItemDefenseGun;
import redstone.models.defensegun;

public class RenderDefGun implements IItemRenderer {

	private static defensegun gun = new defensegun();

	private static final ResourceLocation LOC = new ResourceLocation("subaraki:rhg/defGun.png");

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.INVENTORY ? false: true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}


	int rotateShot = 35;
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		ItemDefenseGun g = (ItemDefenseGun)item.getItem();
		
		switch (type) {
		case EQUIPPED:
			GL11.glPushMatrix();

			GL11.glRotatef(90, 0, 1, 0);
			GL11.glRotatef(135, 1, 0, 0);
			GL11.glTranslatef(0f, 0.1f, -0.8f);
			if (g.isGunShot){
				if(rotateShot > 0)
					GL11.glRotatef(-rotateShot--, 1, 0, 0);
				else{
					rotateShot = 35;
					g.isGunShot = false;
				}
			}
			Minecraft.getMinecraft().renderEngine.bindTexture(LOC);
			gun.render(null, 0, 0, 0, 0, 0, 0.0625f);

			GL11.glPopMatrix();
			break;

		case EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();

			GL11.glRotatef(90, 0, 1, 0);
			GL11.glRotatef(135, 1, 0, 0);
			if (g.isGunShot){
				if(rotateShot > 0)
					GL11.glRotatef(-rotateShot--, 1, 0, 0);
				else{
					rotateShot = 35;
					g.isGunShot = false;
				}
			}
			GL11.glTranslatef(0f, 0.1f, -0.8f);
			
			GL11.glScalef(1.5f, 1.5f, 1.5f);
			Minecraft.getMinecraft().renderEngine.bindTexture(LOC);
			gun.render(null, 0, 0, 0, 0, 0, 0.0625f);

			GL11.glPopMatrix();
			break;

		case ENTITY :
			GL11.glPushMatrix();

			applyBuffer();

			Minecraft.getMinecraft().renderEngine.bindTexture(LOC);
			gun.render(null, 0, 0, 0, 0, 0, 0.0625f);

			GL11.glPopMatrix();
			break;

		default:
			break;
		}
	}


	public void applyBuffer(){
		FloatBuffer[] mat;

		float[] matAmbient={0.25f,0.25f,0.25f,1};
		FloatBuffer matAmbientB = BufferUtils.createFloatBuffer(4);
		matAmbientB.put(matAmbient);
		matAmbientB.flip();

		float[] matDiffuse={0.4f,0.4f,0.4f,0};
		FloatBuffer matDiffuseB = BufferUtils.createFloatBuffer(4);
		matDiffuseB.put(matDiffuse);
		matDiffuseB.flip();

		float[] matSpec={0.774597f,0.774597f,0.774597f,0};
		FloatBuffer matSpecB = BufferUtils.createFloatBuffer(4);
		matSpecB.put(matSpec);
		matSpecB.flip();

		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, matAmbientB);

		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, matDiffuseB);

		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, matSpecB);

		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 01f * 128.0f);

	}

}
