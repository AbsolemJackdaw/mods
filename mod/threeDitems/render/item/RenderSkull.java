package threeDitems.render.item;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import threeDitems.models.head;
import threeDitems.render.Render3DInterface;

public class RenderSkull extends Render3DInterface{

	String name ="textures/entity/steve.png";
	ResourceLocation loc = new ResourceLocation(name);;

	public RenderSkull(ModelBase model, String texture) {
		super(model, texture);
//
//		name = "textures/entity/steve.png";
//		loc = new ResourceLocation(name);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(90,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
			
		GL11.glTranslatef(0.3f, -0.5f, 0.5f);		
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
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(30,1,0,0);
			
		GL11.glTranslatef(0f, 01f, 1f);		
	}

	@Override
	public void renderScale() {
		float f = 2f;
		GL11.glScalef(f, f, f);		
	}

	public void postSpecials(ItemStack item, ModelBase model) {

		switch(item.getItemDamage()){
		case 0:
			name = "textures/entity/skeleton/skeleton.png";
			break;
		case 1:
			name = "textures/entity/skeleton/wither_skeleton.png";
			break;
		case 2:
			name = "textures/entity/zombie/zombie.png";
			break;
		case 3: 
			if(item.getTagCompound() != null){
				if (item.getTagCompound().hasKey("SkullOwner")){
					ResourceLocation resourcelocation = AbstractClientPlayer.field_110314_b;

					if (item.getTagCompound().getString("SkullOwner") != null && item.getTagCompound().getString("SkullOwner").length() > 0)
					{
						resourcelocation = AbstractClientPlayer.func_110305_h(item.getTagCompound().getString("SkullOwner"));
						AbstractClientPlayer.func_110304_a(resourcelocation, item.getTagCompound().getString("SkullOwner"));
					}
					Minecraft.getMinecraft().renderEngine.func_110577_a(resourcelocation);
				}
			}else{
				name = "textures/entity/steve.png";
			}
			break;
		case 4:
			name = "textures/entity/creeper/creeper.png";
			break;
		default:
			name = "textures/entity/steve.png";
			break;
		}

		Minecraft.getMinecraft().renderEngine.func_110577_a(new ResourceLocation(name));
		
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
	@Deprecated
	protected boolean shouldIgnoreTextureRendering() {
		// TODO Auto-generated method stub
		return true;
	}
}
