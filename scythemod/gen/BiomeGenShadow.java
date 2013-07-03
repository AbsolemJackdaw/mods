package scythemod.gen;

import scythemod.entity.EntityCReaper;
import scythemod.entity.EntityDeath;
import net.minecraft.block.Block;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenShadow extends BiomeGenBase
{
public BiomeGenShadow(int par1)
{
         super(par1);
         this.setBiomeName("Shadow Biome");
         this.spawnableMonsterList.clear();
         this.spawnableCreatureList.clear();
         this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 16, 8, 16) );
         this.spawnableMonsterList.add(new SpawnListEntry(EntityCReaper.class, 4, 8, 10) );
         this.spawnableMonsterList.add(new SpawnListEntry(EntityDeath.class, 4, 8, 10));
         this.spawnableWaterCreatureList.clear();
         this.topBlock = (byte)Block.mycelium.blockID;
         this.fillerBlock = (byte)Block.dirt.blockID;
         this.minHeight = 0.1F;
         this.maxHeight = 0.3F;
         this.canSpawnLightningBolt();
        
         
}
public int getSkyColorByTemp(float par1)
{
        return 8847615;
}

}