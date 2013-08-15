package petBuddy.block;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import petBuddy.PetBuddyMain;

public class TESRShrine extends TileEntitySpecialRenderer{


	private static final Shrine shrine = new Shrine();
	private static final ModelBiped pet = new ModelBiped();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1,
			double d2, float f) {

		if(tileentity != null){
			TEShrine te = (TEShrine)tileentity;

			if(te.hasStatue){

				this.func_110628_a(new ResourceLocation("subaraki:mobs/puppet.png"));
				GL11.glPushMatrix();
				GL11.glColor3f( 1f,1f,1f);

				GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
				GL11.glScalef(1.0F, -1F, -1F);

				GL11.glScalef(0.2f,0.2f,0.2f);

				GL11.glTranslatef(0f, 2.8f, 0f);

				pet.bipedBody.render(0.0625f);
				pet.bipedHead.render(0.0625f);
				pet.bipedLeftArm.render(0.0625f);
				pet.bipedLeftLeg.render(0.0625f);
				pet.bipedRightArm.render(0.0625f);
				pet.bipedRightLeg.render(0.0625f);

				GL11.glTranslatef(0f, 2f, 0f);
				GL11.glScalef(1.5f, 1, 1.3f);
				pet.bipedHeadwear.render(0.0625f);

				GL11.glPopMatrix();					
			}	

			this.func_110628_a(new ResourceLocation("subaraki:mobs/Shrine.png"));
			GL11.glPushMatrix();

			GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
			GL11.glScalef(1.0F, -1F, -1F);

			shrine.render(0.0625f);
			shrine.candles(0.0625f);

			GL11.glPopMatrix();
			
			if(te.hasStatue){
				GL11.glPushMatrix();
				if(!te.cycleDone)
				GL11.glColor4f( 1f,0f + (float) te.cooldown / (40*20), 0f+ (float) te.cooldown / (30*20), 1f);
				else{
					float time = (float)Math.cos(PetBuddyMain.getSysTimeF()/10f) +0.4f;
					GL11.glColor4f( 1f,time, time, 1f);
				}

				this.func_110628_a(new ResourceLocation("subaraki:mobs/ShrineLayover.png"));
				GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); 
				GL11.glScalef(1.0F, -1F, -1F);
				shrine.render(0.0625f);
				shrine.candles(0.0625f);
				GL11.glPopMatrix();	
			}
		}
	}

}
