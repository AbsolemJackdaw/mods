package telepads;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemPadLocations extends Item {

	public static final String SIZE = "size";
	public static final String LOCATION_ = "Location_";
	public static final String DIM_ = "Dimension_";

	public ItemPadLocations(int par1) {
		super(par1);
		setCreativeTab(CreativeTabs.tabTransport);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		this.itemIcon = par1IconRegister.registerIcon("map_filled");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return 0x1af0ab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.hasTagCompound()){
			NBTTagCompound tag = par1ItemStack.getTagCompound();
			for(int i = 0; i < tag.getInteger(SIZE); i++){

				int [] a_ray = new int[3];
				a_ray = tag.getIntArray(LOCATION_+i);

				//FMLLog.getLogger().info("" + tag);
				if(a_ray != null)
					par3List.add("x" + a_ray[0] + " y"+ a_ray[1] + " z"+a_ray[2] + " "+ tag.getString("TelePadName_"+i) + " dim. "+ tag.getInteger(DIM_+i));
			}
		}
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {

		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		if(!par2World.isRemote){
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "/*======= How To Use =======*/");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "/*=Register=*/");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "Right Click for Information Guide.");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "/*=TelePads=*/");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "1.Click Empty handed to set TelePad to Universal");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "2.Use Register on TelePad to add Universal TelePad, if not already registered.");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "3.Standing on a TelePad will cause it to make a backup of your current Register ,");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "if lost, stand back on a pad to get a new one.");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "4.TelePads are player only and can be shared with anyone if set to universal.");
			par3EntityPlayer.addChatMessage(EnumChatFormatting.DARK_AQUA + "");

		}


		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		// TODO Auto-generated method stub

		super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
	}
}
