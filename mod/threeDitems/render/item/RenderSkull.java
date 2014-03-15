package threeDitems.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import threeDitems.models.head;
import threeDitems.render.Render3DInterface;

public class RenderSkull extends Render3DInterface{

	private static final ResourceLocation skullSkelly = new ResourceLocation("textures/entity/skeleton/skeleton.png");
	private static final ResourceLocation skullWither = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
	private static final ResourceLocation skullZombie = new ResourceLocation("textures/entity/zombie/zombie.png");
	private static final ResourceLocation skullCreeper = new ResourceLocation("textures/entity/creeper/creeper.png");
	private static final ResourceLocation skullSteve = new ResourceLocation("textures/entity/steve.png");

	public RenderSkull(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void postSpecials(ItemStack item, ModelBase model, Object... data) {

		switch(item.getItemDamage()){
		case 0:
			Minecraft.getMinecraft().renderEngine.bindTexture(skullSkelly);
			break;
		case 1:
			Minecraft.getMinecraft().renderEngine.bindTexture(skullWither);
			break;
		case 2:
			Minecraft.getMinecraft().renderEngine.bindTexture(skullZombie);
			break;
		case 3:
			if(item.getTagCompound() != null)
				if (item.getTagCompound().hasKey("SkullOwner")){
					ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;

					if ((item.getTagCompound().getString("SkullOwner") != null) && (item.getTagCompound().getString("SkullOwner").length() > 0))
					{
						resourcelocation = AbstractClientPlayer.getLocationSkin(item.getTagCompound().getString("SkullOwner"));
						AbstractClientPlayer.getDownloadImageSkin(resourcelocation, item.getTagCompound().getString("SkullOwner"));
					}

					Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
				}
			break;
		case 4:
			Minecraft.getMinecraft().renderEngine.bindTexture(skullCreeper);
			break;
		default:
			Minecraft.getMinecraft().renderEngine.bindTexture(skullSteve);
			break;
		}

		switch(item.getItemDamage()){
		case 2:
			((head)model).renderZombie(0.0625f);
			break;
		default:
			((head)model).renderHead(0.0625f);
			break;
		}
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 0f, 0f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(15,0,0,1);
		GL11.glRotatef(180,1,0,0);

		GL11.glTranslatef(0.5f, 0f, 0f);

		float f = 0.7f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(90,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0.3f, -0.5f, 0.5f);
	}

	@Override
	public void renderScale() {
		float f = 2f;
		GL11.glScalef(f, f, f);
	}

	@Override
	@Deprecated
	protected boolean shouldIgnoreModelRendering(){
		return true;
	}

	@Override
	@Deprecated
	protected boolean shouldIgnoreTextureRendering() {
		// TODO Auto-generated method stub
		return true;
	}
}
