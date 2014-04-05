//package telepads;
//
//import java.util.List;
//
//import net.minecraft.client.renderer.texture.IIconRegister;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.ChatComponentText;
//import net.minecraft.util.EnumChatFormatting;
//import net.minecraft.world.World;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//public class ItemPadLocations extends Item {
//
//	public static final String SIZE = "size";
//	public static final String LOCATION_ = "Location_";
//	public static final String DIM_ = "Dimension_";
//
//	public ItemPadLocations() {
//		super();
//		setCreativeTab(CreativeTabs.tabTransport);
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IIconRegister par1IconRegister) {
//		super.registerIcons(par1IconRegister);
//		this.itemIcon = par1IconRegister.registerIcon("map_filled");
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
//		return 0x1af0ab;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void addInformation(ItemStack par1ItemStack,
//			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
//		if(par1ItemStack.hasTagCompound()){
//			NBTTagCompound tag = par1ItemStack.getTagCompound();
//			for(int i = 0; i < tag.getInteger(SIZE); i++){
//
//				int [] a_ray = new int[3];
//				a_ray = tag.getIntArray(LOCATION_+i);
//
//				if(a_ray != null)
//					par3List.add("x" + a_ray[0] + " y"+ a_ray[1] + " z"+a_ray[2] + " "+ tag.getString("TelePadName_"+i) + " dim. "+ tag.getInteger(DIM_+i));
//			}
//		}
//	}
//
//	@Override
//	public void onUpdate(ItemStack par1ItemStack, World par2World,
//			Entity par3Entity, int par4, boolean par5) {
//
//		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
//
//	}
//
//	@Override
//	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
//			EntityPlayer par3EntityPlayer) {
//
//		if(!par2World.isRemote){
//			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "/*======= How To Use =======*/"));
//			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + ""));
//			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "/*=REGISTER=*/"));
//			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "Right Click for Information Guide."));
//			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + ""));
//			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "/*=TELEPADS=*/"));
//			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "1.Teleporting : stand on pad and wait 3-5 Seconds."));
//			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "2.Removing TelePad : Right Click on TelePad"));
////			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "3.Making a copy of your register : "));
////			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + ".~ Soon"));
////			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + ".~ "));
////			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "4.Lost your register ? Stand on one of your pads to recover"));
////			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "one. TelePads Remember what locations you travelled to last !"));
////			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "5.FYI :TelePads are player only and can be shared with anyone if set to Universal Acces(Step 3)"));
////			par3EntityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "Universal Pads can't be removed."));
//
//		}
//
//
//		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
//	}
//
//	
//	@Override
//	public void onCreated(ItemStack par1ItemStack, World par2World,
//			EntityPlayer par3EntityPlayer) {
//		// TODO Auto-generated method stub
//
//		super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
//	}
//}
