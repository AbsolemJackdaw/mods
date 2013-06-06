package petBuddy.root;

import java.util.Random;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import petBuddy.entity.model.SheepBody;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBuddy extends RenderLiving
{
	private float scaleBuddy;
	public Random rand = new Random();

	public RenderBuddy(float par2, float scale)
	{
		super(new ModelCow(), par2);
		scaleBuddy = scale;
	}

	protected void preRenderCallback(EntityLiving pet, float par2) {
		GL11.glScalef(scaleBuddy, scaleBuddy, scaleBuddy);
		if(PetBuddyMain.proxy.getGuiId() == 13){
			GL11.glTranslatef(0f, -1.5f, 0f);
		}
		if(PetBuddyMain.proxy.getGuiId() == 6){
			GL11.glTranslatef(0f, -0.5f, 0f);
		}
		if(pet.isRiding()){
			GL11.glTranslatef(0f, ((BuddyBase)pet).getMountedOffset(), 0f);

		}
	}

	private ModelEnderman model = new ModelEnderman();
	private SheepBody modelBody = new SheepBody();
	protected int sheepTexturing(BuddyBase buddy, int par2, float par3)
	{
		if (par2 == 0 && PetBuddyMain.proxy.getGuiId() == 14){
			this.setRenderPassModel(modelBody);
			this.loadTexture("/mob/sheep_fur.png");
			float f1 = 1.0F;
			GL11.glColor3f(((EntityBuddy)buddy).getColor(),((EntityBuddy)buddy).getColor2(),((EntityBuddy)buddy).getColor3());
			return 1;
		}
		if(par2 == 0 && PetBuddyMain.proxy.getGuiId() == 15){
			this.setRenderPassModel(model);
			this.loadTexture("/mob/enderman_eyes.png");
			float f1 = 1.0F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			if (buddy.isInvisible())
			{
				GL11.glDepthMask(false);
			}
			else
			{
				GL11.glDepthMask(true);
			}

			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
			return 1;
		}
		else
		{
			return -1;
		}
	}

	protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
	{
		return this.sheepTexturing((BuddyBase)par1EntityLiving, par2, par3);
	}


	public void renderCow(BuddyBase buddy, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(buddy, par2, par4, par6, par8, par9);
		this.mainModel = buddy.getModel();
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		BuddyBase pet = (BuddyBase)par1EntityLiving;
		this.renderCow(pet, par2, par4, par6, par8, par9);
	}

	protected void getDownloadableTexture(EntityLiving living)
	{    		
		if(PetBuddyMain.proxy.getGuiId() == 3){
			this.loadDownloadableImageTexture(((EntityBuddy)living).getOwner().skinUrl, living.getTexture());
		}
		else{
			this.loadDownloadableImageTexture(((BuddyBase)living).getOwner().skinUrl+"BuddySubstitute", living.getTexture());
		}
	}

	@Override
	protected void func_98190_a(EntityLiving living)
	{
		this.getDownloadableTexture((BuddyBase)living);
	}

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		BuddyBase pet = (BuddyBase)par1Entity;
		this.renderCow((BuddyBase)par1Entity, par2, par4, par6, par8, par9);
		this.renderLivingLabel(pet, pet.getOwnerName()+ "'s Buddy" , par2, par4+0.3, par6, 32);
	}
}