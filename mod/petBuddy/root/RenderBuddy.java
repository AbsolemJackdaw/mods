package petBuddy.root;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import petBuddy.entity.EntityBuddy;
import petBuddy.entity.model.DragonsModel;
import petBuddy.entity.model.SheepBody;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBuddy extends RenderLiving
{
	private float scaleBuddy;
	public Random rand = new Random();
	private static final ResourceLocation sheepLoc = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
	private static final ResourceLocation dragonLoc = new ResourceLocation("subaraki:mobs/ender.png");
	private static final ResourceLocation enderLoc = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
	public RenderBuddy(float par2, float scale)
	{
		super(new ModelCow(), par2);
		scaleBuddy = scale;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase pet, float par2) {
		BuddyBase bud = (BuddyBase)pet;

		GL11.glScalef(scaleBuddy, scaleBuddy, scaleBuddy);
		if(bud.getGuiId() == 13){
			GL11.glTranslatef(0f, -1.5f, 0f);
		}
		if(bud.getGuiId() == 6){
			GL11.glTranslatef(0f, -0.5f, 0f);
		}
		if(bud.getGuiId() == 8){
			GL11.glTranslatef(0f, -1.5f, 0f);
		}
		if(bud.getGuiId() == 19){
			if(pet.isRiding())
				GL11.glTranslatef(0f, 1f, 0f);
			GL11.glScalef(scaleBuddy, scaleBuddy, scaleBuddy);
			GL11.glTranslatef(0f, -2f, 0f);

		}
		if(bud.getGuiId() == 31){
			float f1 = 3;
			float f2 = (((BuddyBase)pet).field_70812_c + (((BuddyBase)pet).field_70811_b - ((BuddyBase)pet).field_70812_c) * par2) / (f1 * 0.5F + 1.0F);
			float f3 = 1.0F / (f2 + 1.0F);
			GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);

		}
		if(bud.getGuiId() == 30){
			BuddyBase buddy = (BuddyBase)pet;
			int i = 3;
			float f1 = (buddy.field_70812_c + (buddy.field_70811_b - buddy.field_70812_c) * par2) / ((float)i * 0.5F + 1.0F);
			float f2 = 1.0F / (f1 + 1.0F);
			float f3 = (float)i;
			GL11.glScalef(f2 * f3, 1.0F / f2 * f3, f2 * f3);
		}
//		if(bud.getGuiId() == 3){
//			BuddyBase buddy = (BuddyBase)pet;
//			ModelBase base = ((BuddyBase)pet).getModel();
//
//			if(base instanceof ModelBiped){
//				ModelBiped biped = (ModelBiped)base;
//				ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
//				if (buddy.getSkinName() != null && buddy.getSkinName().length() > 0){
//					resourcelocation = AbstractClientPlayer.getLocationCape(buddy.getSkinName());
//					AbstractClientPlayer.getDownloadImageCape(resourcelocation, buddy.getSkinName());
//					
//						bindTexture(resourcelocation);
//						GL11.glPushMatrix();
//						GL11.glRotatef(180, 0, 1, 0);
//						GL11.glTranslatef(0, -1.5f, -0.15f);
//						biped.renderCloak(0.0625f);
//						GL11.glPopMatrix();
//					
//				}
//			}
//		}
		if(pet.isRiding()){
			GL11.glTranslatef(0f, ((BuddyBase)pet).getMountedOffset(), 0f);
		}
	}

	private ModelEnderman model = new ModelEnderman();
	private SheepBody modelBody = new SheepBody();
	private DragonsModel modelDragon = new DragonsModel(0.0f);

	protected int sheepTexturing(BuddyBase buddy, int par2, float par3)
	{
		if (par2 == 0 && buddy.getGuiId() == 14){
			this.setRenderPassModel(modelBody);
			bindTexture(sheepLoc);
			float f1 = 1.0F;
			GL11.glColor3f(((EntityBuddy)buddy).getColor(),((EntityBuddy)buddy).getColor2(),((EntityBuddy)buddy).getColor3());
			return 1;
		}

		if (par2 == 0 && buddy.getGuiId() == 19){
			this.setRenderPassModel(modelDragon);
			bindTexture(dragonLoc);
			float f1 = 1.0F;
			GL11.glColor3f(((EntityBuddy)buddy).getDragonColor(),((EntityBuddy)buddy).getDragonColor2(),((EntityBuddy)buddy).getDragonColor3());
			return 1;
		}

		if(par2 == 0 && buddy.getGuiId() == 15){
			this.setRenderPassModel(model);
			bindTexture(enderLoc);
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
		if(buddy.getGuiId() == 31){
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
		super.doRenderLiving(buddy, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		BuddyBase pet = (BuddyBase)par1EntityLiving;
		this.renderCow(pet, par2, par4, par6, par8, par9);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
	{
		return this.sheepTexturing((BuddyBase)par1EntityLiving, par2, par3);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		BuddyBase pet = (BuddyBase)par1Entity;
		this.renderCow((BuddyBase)par1Entity, par2, par4, par6, par8, par9);
		String petname = ((EntityBuddy)pet).getName().equals("null")?pet.getOwnerName()+ "'s Buddy" : ((EntityBuddy)pet).getName();

		if(pet.isRiding())
			this.renderLivingLabel(pet, petname , par2, par4-pet.getHeight() -1f, par6, 32);
		else
			this.renderLivingLabel(pet, petname , par2, par4-pet.getHeight() , par6, 32);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		BuddyBase pet = (BuddyBase)entity;

		if(pet.getGuiId() ==3){
			ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
			if (pet.getSkinName() != null && pet.getSkinName().length() > 0){

				resourcelocation = AbstractClientPlayer.getLocationSkin(pet.getSkinName());
				AbstractClientPlayer.getDownloadImageSkin(resourcelocation, pet.getSkinName());

			}
			Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
			return resourcelocation;
		}
		else{
			return pet.getTexture();
		}
	}


}