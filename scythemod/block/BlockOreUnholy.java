package scythemod.block;

import java.util.Random;

import scythemod.BaseScytheFile;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockOreUnholy extends Block
{
	public BlockOreUnholy(int x, int y)
	{
		super(x, Material.rock);
	}

	public int idDropped(int par1, Random par2Random, int par3)
	{

		return BaseScytheFile.UnholyCrystalSplinter.itemID;
	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("DSM:unholy");
	}
	public int quantityDropped(Random random)
	{
		return 1;
	}


}
