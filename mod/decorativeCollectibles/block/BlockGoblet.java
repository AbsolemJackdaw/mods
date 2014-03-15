package decorativeCollectibles.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import decorativeCollectibles.mod_collectibles;
import decorativeCollectibles.tileEntity.TileEntityGoblet;

public class BlockGoblet extends BlockContainer {

	public BlockGoblet (int id) {
		super(id, Material.iron);
		setCreativeTab(CreativeTabs.tabBlock);
		setResistance(1f);
		setHardness(7f);
	}

	private Icon[] icons = new Icon[mod_collectibles.GENERAL_META];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		for(int i = 0; i < mod_collectibles.GENERAL_META; i++)
		{
			icons[i] = par1IconRegister.registerIcon("collectibles" + ":goblet_" + i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return icons[par2];
	}


	@Override
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 2));
		subItems.add(new ItemStack(this, 1, 3));
		subItems.add(new ItemStack(this, 1, 4));
		subItems.add(new ItemStack(this, 1, 5));
		subItems.add(new ItemStack(this, 1, 6));

	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityGoblet();
	}

	@Override
	public int getRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	} 

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}