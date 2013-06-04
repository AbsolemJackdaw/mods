package scythemod.gen;

import scythemod.entity.EntityGhost;
import scythemod.entity.EntityTheFrozen;
import scythemod.entity.EntityZombieReaper;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenFrozen extends BiomeGenBase
{
	public BiomeGenFrozen(int par1)
	{
		super(par1);
		this.setBiomeName("FrozenDeath");
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityTheFrozen.class, 4, 8, 10) );
		this.spawnableMonsterList.add(new SpawnListEntry(EntityGhost.class, 1, 1, 3) );
		this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieReaper.class, 1, 1, 3));
		this.spawnableWaterCreatureList.clear();
		this.topBlock = (byte)Block.blockSnow.blockID;
		this.fillerBlock = (byte)Block.ice.blockID;
		this.minHeight = 0.5F;
		this.maxHeight = 0.7F;
		this.canSpawnLightningBolt();
		this.rainfall = 1F;
		this.setEnableSnow();
		this.temperature = -40F;

	} 
	public int getSkyColorByTemp(float par1)
	{
		return 0;
	}

}