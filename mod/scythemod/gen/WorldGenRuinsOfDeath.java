package scythemod.gen;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRuinsOfDeath extends WorldGenerator
{
	
public WorldGenRuinsOfDeath()
{
}


public boolean generate(World world, Random random, int i, int j, int k) // x y z
{
int hight = random.nextInt(100);

	if (hight >= 20 && hight <= 50)
	{


int stoneBrick = Block.stoneBrick.blockID;
int bed = Block.bed.blockID;
int oven = Block.furnaceIdle.blockID;
int blockD = Block.blockDiamond.blockID;
int blockG = Block.blockGold.blockID;
int blockI = Block.blockIron.blockID;
int shroom = Block.mushroomBrown.blockID;
int shroom2 = Block.mushroomRed.blockID;
int dirt = Block.blockSnow.blockID;
int n;
int g = random.nextInt(10);

for (n=0; n<5; n++)
{
	
world.setBlockMetadataWithNotify(i, j, k, stoneBrick, 1);



world.setBlockMetadataWithNotify(i+n, j, k, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+5, j, k+n, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+n+1, j, k+5, stoneBrick, 1);

world.setBlockMetadataWithNotify(i, j, k+1, stoneBrick, 0);
world.setBlockMetadataWithNotify(i, j, k+2, stoneBrick, 1);
world.setBlockMetadataWithNotify(i, j, k+3, stoneBrick, 1);
world.setBlockMetadataWithNotify(i, j, k+5, stoneBrick, 1);


world.setBlockMetadataWithNotify(i+1, j+1, k, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+2, j+1, k, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+3, j+1, k, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+5, j+1, k, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+5, j+1, k+n, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+n+1, j+1, k+5, stoneBrick, 1);

world.setBlockMetadataWithNotify(i, j+1, k+1, stoneBrick, 1);
world.setBlockMetadataWithNotify(i, j+1, k+2, stoneBrick, 1);
world.setBlockMetadataWithNotify(i, j+1, k+3, stoneBrick, 0);
world.setBlockMetadataWithNotify(i, j+1, k+5, stoneBrick, 1);


world.setBlockMetadataWithNotify(i+3, j+2, k, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+4, j+2, k, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+5, j+2, k, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+5, j+2, k+n, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+n+1, j+2, k+5, stoneBrick, 1);

world.setBlockMetadataWithNotify(i, j+2, k+1, stoneBrick, 0);
world.setBlockMetadataWithNotify(i, j+2, k+2, stoneBrick, 1);
world.setBlockMetadataWithNotify(i, j+2, k+3, stoneBrick, 0);
world.setBlockMetadataWithNotify(i, j+2, k+5, stoneBrick, 1);
world.setBlockMetadataWithNotify(i, j+2, k+4, stoneBrick, 0);

	

world.setBlockMetadataWithNotify(i+1, j+3, k+4, stoneBrick, 0);
world.setBlockMetadataWithNotify(i+1, j+3, k+3, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+1, j+3, k+2, stoneBrick, 0);
world.setBlockMetadataWithNotify(i+1, j+3, k+1, stoneBrick, 1);

world.setBlockMetadataWithNotify(i+4, j+3, k+1, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+3, j+3, k+1, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+2, j+3, k+1, stoneBrick, 0);
world.setBlockMetadataWithNotify(i+1, j+3, k+1, stoneBrick, 1);

world.setBlockMetadataWithNotify(i+4, j+3, k+4, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+3, j+3, k+4, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+2, j+3, k+4, stoneBrick, 0);
world.setBlockMetadataWithNotify(i+1, j+3, k+4, stoneBrick, 0);

world.setBlockMetadataWithNotify(i+4, j+3, k+4, stoneBrick, 0);
world.setBlockMetadataWithNotify(i+4, j+3, k+3, stoneBrick, 1);
world.setBlockMetadataWithNotify(i+4, j+3, k+2, stoneBrick, 0);
//world.setBlockwithNotify(i+4, j+3, k+4, stoneBrick, 1);

world.setBlockMetadataWithNotify(i+2, j+3, k+3, stoneBrick, 1);
}
int v;
for(v=0; v<3; v++)
{
world.setBlock(i+1, j+v, k+1, 0);
world.setBlock(i+1, j+v, k+2, 0);
world.setBlock(i+1, j+v, k+3, 0);
world.setBlock(i+1, j+v, k+4, 0);

world.setBlock(i+2, j+v, k+1, 0);
world.setBlock(i+2, j+v, k+2, 0);
world.setBlock(i+2, j+v, k+3, 0);
world.setBlock(i+2, j+v, k+4, 0);

world.setBlock(i+3, j+v, k+1, 0);
world.setBlock(i+3, j+v, k+2, 0);
world.setBlock(i+3, j+v, k+3, 0);
world.setBlock(i+3, j+v, k+4, 0);


world.setBlock(i+4, j+1, k+1, 0);
world.setBlock(i+4, j+1, k+2, 0);
world.setBlock(i+4, j+1, k+3, 0);
world.setBlock(i+4, j+1, k+4, 0);

world.setBlock(i+4, j+2, k+1, 0);
world.setBlock(i+4, j+2, k+2, 0);
world.setBlock(i+4, j+2, k+3, 0);
world.setBlock(i+4, j+2, k+4, 0);

world.setBlock(i+4, j, k+4, 0);

}


int h;
for(h=1; h<3; h++)
{
world.setBlock(i+1, j-h, k+1, dirt);
world.setBlock(i+1, j-h, k+2, dirt);
world.setBlock(i+1, j-h, k+3, dirt);
world.setBlock(i+1, j-h, k+4, dirt);
world.setBlock(i+1, j-h, k+5, dirt);
world.setBlock(i+1, j-h, k, dirt);

world.setBlock(i+2, j-h, k+1, dirt);
world.setBlock(i+2, j-h, k+2, dirt);
world.setBlock(i+2, j-h, k+3, dirt);
world.setBlock(i+2, j-h, k+4, dirt);
world.setBlock(i+2, j-h, k+5, dirt);
world.setBlock(i+2, j-h, k, dirt);

world.setBlock(i+3, j-h, k+1, dirt);
world.setBlock(i+3, j-h, k+2, dirt);
world.setBlock(i+3, j-h, k+3, dirt);
world.setBlock(i+3, j-h, k+4, dirt);
world.setBlock(i+3, j-h, k+5, dirt);
world.setBlock(i+3, j-h, k, dirt);

world.setBlock(i+4, j-h, k+1, dirt);
world.setBlock(i+4, j-h, k+2, dirt);
world.setBlock(i+4, j-h, k+3, dirt);
world.setBlock(i+4, j-h, k+4, dirt);
world.setBlock(i+4, j-h, k+5, dirt);
world.setBlock(i+4, j-h, k, dirt);

world.setBlock(i+5, j-h, k+1, dirt);
world.setBlock(i+5, j-h, k+2, dirt);
world.setBlock(i+5, j-h, k+3, dirt);
world.setBlock(i+5, j-h, k+4, dirt);
world.setBlock(i+5, j-h, k+5, dirt);
world.setBlock(i+5, j-h, k, dirt);

world.setBlock(i, j-h, k+1, dirt);
world.setBlock(i, j-h, k+2, dirt);
world.setBlock(i, j-h, k+3, dirt);
world.setBlock(i, j-h, k+4, dirt);
world.setBlock(i, j-h, k+5, dirt);
world.setBlock(i, j-h, k, dirt);

}

world.setBlockMetadataWithNotify(i+4, j, k+3, bed,2);
world.setBlockMetadataWithNotify(i+4, j, k+2, bed,0);


world.setBlock(i+4, j, k+1, oven);


	if(g == 0)
	{
		world.setBlock(i+4, j-1, k+1, blockD);

	}
	if(g >= 1 && g < 5)
	{
		world.setBlock(i+4, j-1, k+1, blockG);

	}
	if (g >= 5 && g <=7)
	{
		world.setBlock(i+4, j-1, k+1, blockI);

	}
	if(g >= 8 && g <= 10)
	{
		world.setBlock(i+4, j-1, k+1, Block.dirt.blockID);

	}





	}
	
return true;




}
}