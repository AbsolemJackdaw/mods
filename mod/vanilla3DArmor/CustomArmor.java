//package vanilla3DArmor;
//
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.client.renderer.texture.IconRegister;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.EnumArmorMaterial;
//import net.minecraft.item.ItemArmor;
//import net.minecraft.item.ItemStack;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//
//
//public class CustomArmor extends ItemArmor{
//
//	public CustomArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,
//			int par3, int par4) {
//		super(par1, par2EnumArmorMaterial, par3, par4);
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IconRegister par1IconRegister) {
//		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
//		this.itemIcon = par1IconRegister.registerIcon(itemName);
//	}
//
//	@Override
//	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
//			String type) {
//		if (stack.itemID == mod_VanillaArmor3D.chestD.itemID || stack.itemID == mod_VanillaArmor3D.bootsD.itemID || stack.itemID == mod_VanillaArmor3D.helmetD.itemID) {
//			return "armor:armorD.png";
//		}
//		if (stack.itemID == mod_VanillaArmor3D.legsD.itemID) {
//			return "armor:armorD2.png";
//		}
//		return null;
//	}
//
//	ModelBiped armorModel = null;
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public ModelBiped getArmorModel(EntityLivingBase entityLiving,
//			ItemStack itemStack, int armorSlot) {
//
//		if(itemStack != null){
//			int type = ((ItemArmor)itemStack.getItem()).armorType;
//
//			if(type == 1 || type == 3){
//				armorModel = mod_VanillaArmor3D.proxy.getArmorModel(0);
//			}else{
//				armorModel = mod_VanillaArmor3D.proxy.getArmorModel(1);
//			}
//
//			if(armorModel != null){
//				armorModel.bipedHead.showModel = armorSlot == 0;
//				armorModel.bipedHeadwear.showModel = armorSlot == 0;
//				armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
//				armorModel.bipedRightArm.showModel = armorSlot == 1;
//				armorModel.bipedLeftArm.showModel = armorSlot == 1;
//				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
//				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
//
//				armorModel.isSneak = entityLiving.isSneaking();
//				armorModel.isRiding = entityLiving.isRiding();
//				armorModel.isChild = entityLiving.isChild();
//				armorModel.heldItemRight = entityLiving.getCurrentItemOrArmor(0) != null ? 1 :0;
//				if(entityLiving instanceof EntityPlayer){
//					armorModel.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
//				}
//				return armorModel;
//			}	
//		}
//		return null;
//	}
//
//}
