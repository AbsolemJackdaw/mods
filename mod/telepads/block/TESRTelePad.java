package telepads.block;

import java.nio.FloatBuffer;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import telepads.model.telepad;


public class TESRTelePad extends TileEntitySpecialRenderer {

	telepad pad = new telepad();
	//	ResourceLocation loc = new ResourceLocation("subaraki:pad/padRaw.png");
	private static ResourceLocation loc = new ResourceLocation("subaraki:pad/telepad.png");
	private static ResourceLocation locUniversal = new ResourceLocation("subaraki:pad/padUniversal.png");

	// copied from RenderEndPortal.java
	private static final ResourceLocation enderPortalEndSkyTextures = new ResourceLocation("textures/environment/end_sky.png");
	private static final ResourceLocation endPortalTextures = new ResourceLocation("textures/entity/end_portal.png");
	private static final Random field_110644_e = new Random(31100L);
	FloatBuffer field_76908_a = GLAllocation.createDirectFloatBuffer(16);

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1,
			double d2, float f) {

		GL11.glPushMatrix();
		renderEndPortalSurface(d, d1, d2, f);
		GL11.glPopMatrix();

		GL11.glPushMatrix(); 
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.25F, (float)d2 + 0.5F); 
		GL11.glScalef(1.0F, -1F, -1F);

		bindTexture(loc);
		
		float f2 = 1.5f;
		GL11.glScalef(f2,f2,f2);
		pad.render(null, 0, 0, 0, 0, 0, 0.0625f);

		GL11.glPopMatrix();
	}


	public void renderEndPortalSurface(double par2, double par4, double par6, float par8)
	{

		float f1 = (float)this.field_147501_a.staticPlayerX; //tile entity renderer
		float f2 = (float)this.field_147501_a.staticPlayerY;
		float f3 = (float)this.field_147501_a.staticPlayerZ;
		GL11.glDisable(GL11.GL_LIGHTING);
		field_110644_e.setSeed(31100L);
		float f4 = 0.75F /5;//offset y

		par2+=0.05d; // offset x/z
		par6+=0.05d; // offset x/z
		par4+=0.04d;

		for (int i = 0; i < 16; ++i)
		{
			GL11.glPushMatrix();
			float f5 = (float)(16 - i);
			float f6 = 0.0625F *5f; // looking inside makes the stars go smaller, and more numberous
			float f7 = 1.0F / (f5/10 + 1.0F);//Colors the stars less or more bright



			if (i == 0)
			{
				this.bindTexture(enderPortalEndSkyTextures);
				f7 = 0.1F;
				f5 = 65.0F;
				f6 = 0.125F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}

			if (i == 1)
			{
				this.bindTexture(endPortalTextures);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				f6 = 0.5F;
			}

			float f8 = (float)(-(par4 + (double)f4));
			float f9 = f8 + ActiveRenderInfo.objectY;
			float f10 = f8 + f5 + ActiveRenderInfo.objectY;
			float f11 = f9 / f10;
			f11 += (float)(par4 + (double)f4);
			GL11.glTranslatef(f1, f11, f3);
			GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
			GL11.glTexGen(GL11.GL_S, GL11.GL_OBJECT_PLANE, this.func_76907_a(1.0F, 0.0F, 0.0F, 0.0F));
			GL11.glTexGen(GL11.GL_T, GL11.GL_OBJECT_PLANE, this.func_76907_a(0.0F, 0.0F, 1.0F, 0.0F));
			GL11.glTexGen(GL11.GL_R, GL11.GL_OBJECT_PLANE, this.func_76907_a(0.0F, 0.0F, 0.0F, 1.0F));
			GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, this.func_76907_a(0.0F, 1.0F, 0.0F, 0.0F));
			GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			//			GL11.glTranslatef(0.0F, (float)(Minecraft.getSystemTime() % 700000L) / 700000.0F, 0.0F);
			GL11.glRotatef((float)(Minecraft.getSystemTime() % 7000L) / 7000.0F, 1, 1, 0);
			GL11.glScalef(f6, f6, f6);//scales stars
			GL11.glTranslatef(0.5F, 0.5f, 0.0F);
			GL11.glRotatef((float)(i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
			GL11.glTranslatef(-f1, -f3, -f2);
			f9 = f8 + ActiveRenderInfo.objectY;
			GL11.glTranslatef(ActiveRenderInfo.objectX * f5 / f9, ActiveRenderInfo.objectZ * f5 / f9, -f2);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			f11 = field_110644_e.nextFloat() * 0.5F + 0.1F;
			float f12 = field_110644_e.nextFloat() * 0.5F + 0.4F;
			float f13 = field_110644_e.nextFloat() * 0.5F + 0.5F;

			if (i == 0)
			{
				f13 = 1.0F;
				f12 = 1.0F;
				f11 = 1.0F;
			}

			double scale = 0.9D;

			GL11.glTranslatef(0, 0, 0);
			tessellator.setColorRGBA_F(f11 * f7, f12 * f7, f13 * f7, 1.0F);
			tessellator.addVertex(par2, par4 + (double)f4, par6);
			tessellator.addVertex(par2, par4 + (double)f4, par6 + scale);
			tessellator.addVertex(par2 + scale, par4 + (double)f4, par6 + scale);
			tessellator.addVertex(par2 + scale, par4 + (double)f4, par6);
			tessellator.draw();
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	private FloatBuffer func_76907_a(float par1, float par2, float par3, float par4)
	{
		this.field_76908_a.clear();
		this.field_76908_a.put(par1).put(par2).put(par3).put(par4);
		this.field_76908_a.flip();
		return this.field_76908_a;
	}
}
