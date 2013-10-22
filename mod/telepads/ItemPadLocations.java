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
import net.minecraft.world.World;

public class ItemPadLocations extends Item {

	public static final String SIZE = "size";
	public static final String LOCATION_ = "Location_";
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
					par3List.add("x" + a_ray[0] + " y"+ a_ray[1] + " z"+a_ray[2] + " "+ tag.getString("TelePadName_"+i));
			}
		}
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {

		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		// TODO Auto-generated method stub

		super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
	}
}
