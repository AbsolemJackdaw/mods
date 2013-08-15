package petBuddy.item.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class PetStatueRender implements IItemRenderer {

	private ModelBiped pet;

	private static final ResourceLocation LOC = new ResourceLocation("subaraki:mobs/puppet.png");

	public PetStatueRender() {
		pet = new ModelBiped();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case  EQUIPPED: return true;
		case  EQUIPPED_FIRST_PERSON: return true;
		case ENTITY: return true;
		case INVENTORY: return true;
		default: 
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch(type)
		{
		case INVENTORY: return true;
		default: break;
		}
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

//		if(type.equals(IItemRenderer.ItemRenderType.ENTITY))
//			FMLLog.getLogger().info(""+(Entity)data[1]);
		Minecraft.getMinecraft().renderEngine.func_110577_a(LOC);
		pet.bipedHeadwear.isHidden = false;
		pet.bipedHead.isHidden = false;
		switch(type){

		case EQUIPPED:
			GL11.glPushMatrix();

			GL11.glScalef(0.5f,0.5f,0.5f);

			GL11.glRotatef(190, 1, 0, 0);
			GL11.glRotatef(-15, 0, 0, 1);
			GL11.glRotatef(-13, 0, 1, 0);

			GL11.glTranslatef(0.7f, -1f, 0f);

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
			break;

		case EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();

			GL11.glTranslatef(0.7F,1.5f,-0.5f);
			GL11.glRotatef(-180f, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(-40f, 0.0f, 1.0f, 0.0f);

			pet.bipedHead.isHidden = true;
			pet.render((Entity)data[1], 0f,0f,0f,0f,0f,0.0625f);

			GL11.glPopMatrix();
			break;
		case ENTITY:
			GL11.glPushMatrix();

			GL11.glScalef(0.5f,0.5f,0.5f);

			GL11.glRotatef(180, 1, 0, 0);
			GL11.glTranslatef(0f, -1.8f, 0f);

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
			break;

		case INVENTORY:
			GL11.glPushMatrix();

			GL11.glScalef(0.7f,0.7f,0.7f);

			GL11.glTranslatef(0.0F, 0.7F, 0.0F);
			GL11.glScalef(1f, 1f, 1f);
			GL11.glRotatef(180f, 0f, 0f, 1.0f);

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
			break;

		default:
			break;
		}		
	}
}
