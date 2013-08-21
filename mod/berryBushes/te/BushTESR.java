package berryBushes.te;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;

public class BushTESR extends TileEntitySpecialRenderer{

	private static final bush b = new bush();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1,
			double d2, float f) {

		if(tileentity != null && tileentity instanceof BushTE){
			BushTE te = (BushTE)tileentity;
			this.func_110628_a(new ResourceLocation("berries:bush.png"));

			GL11.glPushMatrix();

			GL11.glTranslatef((float)d + 0.4F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
			GL11.glScalef(1.0F, -1F, -1F);
			
			float f1 = 0.8f;
			float f3 = 1.2f;
			float f4 = 1.3f;

			switch (te.Meta) {
			case 0:
				GL11.glTranslatef(0, 0.3f, 0);
				GL11.glScalef(f1,f1,f1);
				break;
			case 1:
				GL11.glTranslatef(0, 0, 0);
				GL11.glScalef(1, 1, 1);
				break;
			case 2:
				GL11.glTranslatef(0, -0.3f, 0);
				GL11.glScalef(f3,f3,f3);
				break;
			case 3:
				GL11.glTranslatef(0, -0.45f, 0);
				GL11.glScalef(f4,f4,f4);
				break;
			default:
				break;
			}

			b.render(0.0625f);

			GL11.glPopMatrix();
		}
	}

}
