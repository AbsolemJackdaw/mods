package scythemod.gen;

import java.util.Random;

import scythemod.BaseScytheFile;


import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class ChoiceGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		generateSurface(world,random, chunkX*16, chunkZ*16);
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);

		int randPositionX = chunkX + random.nextInt(2);
		int randPositionY = random.nextInt(15);
		int randPositionZ = chunkZ + random.nextInt(2);
		(new WorldGenMinable(BaseScytheFile.UnholyOre.blockID, 4)).generate(world, random, randPositionX, randPositionY, randPositionZ);

		int randPositionX2 = chunkX + random.nextInt(2);
		int randPositionY2 = random.nextInt(15);
		int randPositionZ2 = chunkZ + random.nextInt(2);
		new WorldGenMinable(BaseScytheFile.HolyOre.blockID, 4).generate(world, random, randPositionX2, randPositionY2, randPositionZ2);

		for(int i= 0; i < 10; i++)
		{
			int RandPosX = chunkX + random.nextInt(16);
			int RandPosY = random.nextInt(250);
			int RandPosZ = chunkZ + random.nextInt(16);
			if(RandPosY >= 245 )
			{
				(new WorldGenClouds()).generate(world, random, RandPosX, RandPosY, RandPosZ);

			}  
		}

		if (biome instanceof BiomeGenFrozen)
		{
			for(int i= 0; i < 3; i++)
			{
				int RandPosX2 = chunkX + random.nextInt(7);
				int RandPosZ2 = chunkZ + random.nextInt(7);
				int RandPosY2 = random.nextInt(70);

				if (RandPosY2 >=60 && RandPosY2 <= 70)
				{
					(new WorldGenRuinsOfDeath()).generate(world, random, RandPosX2, RandPosY2, RandPosZ2);
				}

			}
		}
	}
}
