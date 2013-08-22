package berryBushes;

import java.util.List;

import berryBushes.te.BushTE;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Berry extends ItemFood {

	private int Meta;
	public Berry(int i, int healNuggets, float saturationModifier , int meta){
		super(i, healNuggets, saturationModifier, false);
		Meta = meta;
	}

	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {

		switch(Meta){
		case 0:
			this.itemIcon = par1IconRegister.registerIcon("berries:berryI");
			break;
		case 1:
			this.itemIcon = par1IconRegister.registerIcon("berries:berry");
			break;
		case 2:
			this.itemIcon = par1IconRegister.registerIcon("berries:berryIII");
			break;
		case 3:
			this.itemIcon = par1IconRegister.registerIcon("berries:berryIV");
			break;

		default :
			break;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List list, boolean par4) {

		switch(Meta){

		case 0:
			list.add(StatCollector.translateToLocal("A small juicy berry"));
			list.add(StatCollector.translateToLocal("1.5/10"));
			break;
		case 1:
			list.add(StatCollector.translateToLocal("A sweet small tasty berry"));
			list.add(StatCollector.translateToLocal("2/10"));
			break;
		case 2:
			list.add(StatCollector.translateToLocal("A juicy, tasty, big berry"));
			list.add(StatCollector.translateToLocal("3/10"));
			break;
		case 3:
			list.add(StatCollector.translateToLocal("A giant sweet and juicy berry"));
			list.add(StatCollector.translateToLocal("4/10"));
			break;

		default :
			break;
		}

	}


	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if(Meta == 0){
			if(par3World.getBlockId(par4, par5, par6) == Block.grass.blockID ||
					par3World.getBlockId(par4, par5, par6) == Block.dirt.blockID){
				par3World.setBlock(par4, par5+1, par6, Base.berryCrop.blockID);
				BushTE te = new BushTE();
				te.isCrop = true;
				par3World.setBlockTileEntity(par4, par5+1, par6, te);
				par2EntityPlayer.getCurrentEquippedItem().stackSize--;
			}
		}
		return false;
	}
}
