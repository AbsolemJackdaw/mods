package berryBushes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class BushGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		generateOverWorld(world, random, x*16, z*16);
	}

	private void generateOverWorld(World world, Random rand, int x, int z){

		int vy;
		int vx;
		int vz;
		
		int block;
		int sw = rand.nextInt(10);
		switch(sw){
		case 0:block = Base.bushII.blockID;break;
		case 1:block = Base.bushII.blockID;break;
		case 2:block = Base.bushII.blockID;break;
		case 3:block = Base.bushIII.blockID;break;
		case 4:block = Base.bushIII.blockID;break;
		case 5:block = Base.bushIV.blockID;break;
		default: block = Base.bushI.blockID;break;
		}
		
		for (int k = 0; k < 100; k++) {
			vx = x + rand.nextInt(16);
			vy = rand.nextInt(128);
			vz = z + rand.nextInt(16);
			if(!world.isAirBlock(vx, vy, vz) && world.getBlockId(vx, vy, vz) != Block.waterStill.blockID){
				if( world.getBlockId(vx, vy, vz) == Block.dirt.blockID||world.getBlockId(vx, vy, vz) == Block.grass.blockID){
					if(world.getBlockId(vx, vy+1, vz) == Block.snow.blockID || world.isAirBlock(vx, vy+1, vz) )
					world.setBlock(vx, vy+1, vz, block);
				}
			}
		}
	}
}