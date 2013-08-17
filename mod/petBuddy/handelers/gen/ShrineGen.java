package petBuddy.handelers.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import petBuddy.PetBuddyMain;
import petBuddy.block.TEShrine;
import cpw.mods.fml.common.IWorldGenerator;

public class ShrineGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		switch(world.provider.dimensionId) {
		case -1:
			//			generateNether();
			break;
		case 0:
			generateOverWorld(world, random, x*16, z*16);
			break;
		case 1:
			//			generateEnd();
			break;
		}
	}


	public void generateOverWorld(World world, Random rand, int x, int z){

		int y;

		for (int k = 0; k < 1; k++) {
			x = x + rand.nextInt(16);
			y = rand.nextInt(128);
			z = z + rand.nextInt(16);
			if(!world.isAirBlock(x, y, z) && world.isAirBlock(x, y+1, z) 
					&& world.getBlockId(x, y, z) != Block.waterStill.blockID){
				if( world.getBlockId(x, y, z) == Block.sand.blockID||world.getBlockId(x, y, z) == Block.dirt.blockID||
						world.getBlockId(x, y, z) == Block.stone.blockID){
					
				}
				int stone = Block.stoneBrick.blockID;
				int shrine = PetBuddyMain.shrine.blockID;
				int air = 0;

				world.setBlock(x, y+1, z, stone);

				world.setBlock(x, y+2, z, shrine);
				world.setBlockTileEntity(x, y+2, z, new TEShrine());
			}
		}
	}


}