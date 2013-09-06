package threeDitems_old;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import threeDitems.mod_3d;
import threeDitems_old.helper.CaseEntity;
import threeDitems_old.helper.CaseEquipped;
import threeDitems_old.helper.FrameHelper;
import threeDitems_old.helper.MinecartHelper;
import threeDitems_old.models.bottle;

import com.google.common.collect.ObjectArrays;

public class Render3d implements IItemRenderer {

	private ModelBase theItem;
	public static MinecartHelper helper;
	protected RenderBlocks render;
	FrameHelper frame = new FrameHelper();
	private String name = "";
	public static String[] armorFilenamePrefix = new String[] {"cloth", "chain", "iron", "diamond", "gold"};

	
	private static final CaseEquipped eq = new CaseEquipped();
	private static final CaseEquipped e = new CaseEquipped();
	private static final CaseEntity caseEntity = new CaseEntity();

	private Block blockToRender;
	private float x;
	private float y;
	private float z;
	private float rotX;
	private float rotY;
	private float rotZ;
	private float fpsX;
	private float fpsY;
	private float fpsZ;
	private float Z;
	private float X;
	private float Y;
	private float r;
	private float g;
	private float b;
	public static boolean hastoRenderOnTick = false;
	private float scale;
	private float scaleWorld;
	/**
	 * 
	 * @param model     the model to be rendered
	 * @param texture   the texture to be applied.
	 * @param posX3p    translation X of model in 3th person
	 * @param posY3p	translation Y of model in 3th person
	 * @param posZ3p	translation Z of model in 3th person
	 * @param rotX3p    rotation of x axis in 3th person
	 * @param rotY3p	rotation of y axis in 3th person
	 * @param rotZ3p	rotation of z axis in 3th person
	 * @param posX1p	translation X of model in 1st person
	 * @param posY1p	translation Y of model in 1st person
	 * @param posZ1p	translation Z of model in 1st person
	 * @param rotX1p	rotation X of model in 1st person
	 * @param rotY1p	rotation Y of model in 1st person
	 * @param rotZ1p	rotation Z of model in 1st person
	 * @param ScaleDown Scales the model down to 0.5 if true
	 */
	public Render3d(ModelBase model, String texture, 
			float posX3p, float posY3p, float posZ3p,
			float rotX3p, float rotY3p, float rotZ3p,
			float posX1, float posY1, float posZ1,
			float rotXp1, float rotYp1, float rotZp1, float ScaleDown, float inworldScale) {
		theItem = model;
		name = texture;
		x = posX3p;
		y = posY3p;
		z = posZ3p;
		rotX = rotX3p;
		rotY = rotY3p;
		rotZ = rotZ3p;
		this.fpsX = posX1;
		this.fpsY = posY1;
		this.fpsZ = posZ1;
		Z= rotZp1;
		X = rotXp1;
		Y = rotYp1;
		scale = ScaleDown;
		scaleWorld = inworldScale;
		this.render = new RenderBlocks();
		helper = new MinecartHelper();
	}

	public Render3d(ModelBase model, String texture, 
			float posX3p, float posY3p, float posZ3p,
			float rotX3p, float rotY3p, float rotZ3p,
			float posX1, float posY1, float posZ1,
			float rotXp1, float rotYp1, float rotZp1, float ScaleDown, float inworldScale,Block block) {
		theItem = model;
		name = texture;
		x = posX3p;
		y = posY3p;
		z = posZ3p;
		rotX = rotX3p;
		rotY = rotY3p;
		rotZ = rotZ3p;
		this.fpsX = posX1;
		this.fpsY = posY1;
		this.fpsZ = posZ1;
		Z= rotZp1;
		X = rotXp1;
		Y = rotYp1;
		scale = ScaleDown;
		scaleWorld = inworldScale;
		this.render = new RenderBlocks();
		helper = new MinecartHelper();
		blockToRender = block;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if(mod_3d.inst.isRendering3D){
			switch(type)
			{
			//if itemdye is lapis and nothing else
			case  EQUIPPED: 
				if(!(item.getItem().equals(Item.dyePowder) && item.getItemDamage() != 4))
					return true;
			case  EQUIPPED_FIRST_PERSON: 
				if(!(item.getItem().equals(Item.dyePowder) && item.getItemDamage() != 4))
					return true;
			case ENTITY:
				if(!(item.getItem().equals(Item.dyePowder) && item.getItemDamage() != 4))
					return true;
			default: 
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		if(mod_3d.inst.isRendering3D){
//			if(renderOnTick() == true){
//				mod_3d.proxy.render();
//			}
			switch(type){
			case  EQUIPPED:
				eq.render(type, item, x, y, z, rotZ, rotY, rotX, X, Y, Z, fpsX, fpsY, fpsZ, scale, name, render,
						frame, theItem, helper, blockToRender, armorFilenamePrefix, data);
				break;
			case  EQUIPPED_FIRST_PERSON:
				e.render(type, item, x, y, z, rotZ, rotY, rotX, X, Y, Z, fpsX, fpsY, fpsZ, scale, name, render,
						frame, theItem, helper, blockToRender, armorFilenamePrefix, data);
				break;
			case ENTITY:
				caseEntity.render(type, item, x, y, z, rotZ, rotY, rotX, X, Y, Z, fpsX, fpsY, fpsZ, scaleWorld,
						name, render, frame, theItem, helper, blockToRender, armorFilenamePrefix, data);
				break;
			default:
				break;
			}		
		}
	}

	public void potionContent(Entity p, ItemStack item)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.func_110581_b(new ResourceLocation("/subaraki/3d/bottle.png"));
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if( p instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) p;
			if(player.getCurrentEquippedItem() != null){
				if(item.getItem().equals(Item.potion) || item.getItem().equals(Item.glassBottle)){
					int color = ((ItemPotion)item.getItem()).getColorFromDamage(item.getItemDamage());
					float red = (float)(color >> 16 & 255) / 255.0F;
					float green = (float)(color >> 8 & 255) / 255.0F;
					float blue = (float)(color & 255) / 255.0F;					
					GL11.glColor4f(red, green,blue, 1.0F);
					((bottle)theItem).renderContent(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				}else if(player.getCurrentEquippedItem().getItem().equals(Item.expBottle)){
					GL11.glColor4f(0.7f, 1.0f, 0.0f, 1.0F);
					((bottle)theItem).renderContent(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				}
			}
		}
	}

//	protected boolean renderOnTick(){
//		return this.hastoRenderOnTick;
//	}

	public static int addNewArmourRendererPrefix(String armor)
	{
		armorFilenamePrefix = ObjectArrays.concat(armorFilenamePrefix, armor);
		return armorFilenamePrefix.length - 1;
	}
}
