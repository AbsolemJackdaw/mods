package scythemod.block;

import java.util.Random;

import scythemod.BaseScytheFile;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockOreHoly extends Block
{
	public BlockOreHoly(int x, int y)
	{
		super(x, Material.rock);
	}

	public int idDropped(int par1, Random par2Random, int par3)
	{


		return BaseScytheFile.HolyCrystalSplinter.itemID;


	}

	public int quantityDropped(Random random)
	{
		return 1;
	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("DSM:holy");
	}
}
