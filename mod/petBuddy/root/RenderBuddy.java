package petBuddy.root;

import java.util.Random;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;

import org.lwjgl.opengl.GL11;

import petBuddy.PetBuddyMain;
import petBuddy.entity.EntityBuddy;
import petBuddy.entity.model.DragonsModel;
import petBuddy.entity.model.ModelMagmaCube;
import petBuddy.entity.model.SheepBody;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBuddy extends RenderLiving
{
	private float scaleBuddy;
	public Random rand = new Random();

	//used for magma cube, copied from magma cube renderer
	private int field_77120_a;

	public RenderBuddy(float par2, float scale)
	{
		super(new ModelCow(), par2);
		scaleBuddy = scale;
		this.field_77120_a = 5;
	}

	protected void preRenderCallback(EntityLiving pet, float par2) {
		GL11.glScalef(scaleBuddy, scaleBuddy, scaleBuddy);
		if(PetBuddyMain.proxy.getGuiId() == 13){
			GL11.glTranslatef(0f, -1.5f, 0f);
		}
		if(PetBuddyMain.proxy.getGuiId() == 6){
			GL11.glTranslatef(0f, -0.5f, 0f);
		}
		if(PetBuddyMain.proxy.getGuiId() == 8){
			GL11.glTranslatef(0f, -1.5f, 0f);
		}
		if(PetBuddyMain.proxy.getGuiId() == 19){
			GL11.glScalef(scaleBuddy, scaleBuddy, scaleBuddy);
			GL11.glTranslatef(0f, -2f, 0f);
		}
		if(PetBuddyMain.proxy.getGuiId() == 31){
			float f1 = 3;
			float f2 = (((BuddyBase)pet).field_70812_c + (((BuddyBase)pet).field_70811_b - ((BuddyBase)pet).field_70812_c) * par2) / (f1 * 0.5F + 1.0F);
			float f3 = 1.0F / (f2 + 1.0F);
			GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);

		}
		if(PetBuddyMain.proxy.getGuiId() == 30){
			BuddyBase buddy = (BuddyBase)pet;
			int i = 3;
			float f1 = (buddy.field_70812_c + (buddy.field_70811_b - buddy.field_70812_c) * par2) / ((float)i * 0.5F + 1.0F);
			float f2 = 1.0F / (f1 + 1.0F);
			float f3 = (float)i;
			GL11.glScalef(f2 * f3, 1.0F / f2 * f3, f2 * f3);
		}
		if(pet.isRiding()){
			GL11.glTranslatef(0f, ((BuddyBase)pet).getMountedOffset(), 0f);
		}
	}

	private ModelEnderman model = new ModelEnderman();
	private SheepBody modelBody = new SheepBody();
	private DragonsModel modelDragon = new DragonsModel(0.0f);

	protected int sheepTexturing(BuddyBase buddy, int par2, float par3)
	{
		if (par2 == 0 && PetBuddyMain.proxy.getGuiId() == 14){
			this.setRenderPassModel(modelBody);
			this.loadTexture("/mob/sheep_fur.png");
			float f1 = 1.0F;
			GL11.glColor3f(((EntityBuddy)buddy).getColor(),((EntityBuddy)buddy).getColor2(),((EntityBuddy)buddy).getColor3());
			return 1;
		}

		if (par2 == 0 && PetBuddyMain.proxy.getGuiId() == 19){
			this.setRenderPassModel(modelDragon);
			this.loadTexture("/subaraki/mobs/ender.png");
			float f1 = 1.0F;
			GL11.glColor3f(((EntityBuddy)buddy).getDragonColor(),((EntityBuddy)buddy).getDragonColor2(),((EntityBuddy)buddy).getDragonColor3());
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
			if (buddy.isPotionActive(Potion.invisibility)) //isInvisible added in 1.5.2
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
		if(PetBuddyMain.proxy.getGuiId() == 31){
			if (par2 == 0){
				this.setRenderPassModel(new ModelSlime(0));
				GL11.glEnable(GL11.GL_NORMALIZE);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				return 1;
			}
			else if (par2 == 1)
			{
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				return -1;
			}

		}

		return -1;
	}


	public void renderCow(BuddyBase buddy, double par2, double par4, double par6, float par8, float par9)
	{
		this.mainModel = buddy.getModel();

		if( ((EntityBuddy)buddy).getGuiId() == 30){
			int i = 5;

			if (i != this.field_77120_a)
			{
				this.field_77120_a = i;
				this.mainModel = new ModelMagmaCube();
			}
		}
		super.doRenderLiving(buddy, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		BuddyBase pet = (BuddyBase)par1EntityLiving;
		this.renderCow(pet, par2, par4, par6, par8, par9);
	}

	protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
	{
		return this.sheepTexturing((BuddyBase)par1EntityLiving, par2, par3);
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
		String petname = ((EntityBuddy)pet).getName().equals("null")?pet.getOwnerName()+ "'s Buddy" : ((EntityBuddy)pet).getName();
		float offset = 0.3f;
		if(pet.isRiding()) offset = -1.2f; else offset = 0.3f;
		this.renderLivingLabel(pet, petname , par2, par4+offset, par6, 32);
	}



}