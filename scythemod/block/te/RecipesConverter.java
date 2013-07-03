package scythemod.block.te;

import java.util.HashMap;
import java.util.Map;

import scythemod.BaseScytheFile;


import net.minecraft.item.ItemStack;

public class RecipesConverter
{
private static final RecipesConverter recipeconv = new RecipesConverter();

/** The list of smelting results. */
private Map ConverterList = new HashMap();
private Map ConverterXP = new HashMap();

/**
         * Used to call methods addSmelting and getSmeltingResult.
         */
public static final RecipesConverter smelting()
{
         return recipeconv;
}

private RecipesConverter()
{
         ConverterList = new HashMap();
        
         addSmelting(BaseScytheFile.HolyCrystalSplinter.itemID, new ItemStack(BaseScytheFile.HolySoulPearl, 1), 0.5F);
         addSmelting(BaseScytheFile.UnholyCrystalSplinter.itemID, new ItemStack(BaseScytheFile.UnholySoulPearl, 1), 0.5F);
         addSmelting(BaseScytheFile.HolySoulPearl.itemID, new ItemStack(BaseScytheFile.SoulPearl, 2), 0.5F);
         addSmelting(BaseScytheFile.UnholySoulPearl.itemID, new ItemStack(BaseScytheFile.SoulPearl, 2), 0.5F);

         
         /**
         * addSmelting syntax : first Parameter is the Block we give as input and second parameter is the
         * ItemStack we return as result of the"smelting" process.
         * and the third is the amout of EXPOrbs you get by smelting this item.
         */
}

/**
         * Adds a smelting recipe.
         */
public void addSmelting(int id, ItemStack itemStack, float experience)
{
         ConverterList.put(Integer.valueOf(id), itemStack);
         this.ConverterXP.put(Integer.valueOf(itemStack.itemID), Float.valueOf(experience));
}

/**
         * Returns the smelting result of an item.
         */
public ItemStack getSmeltingResult(int id)
{
         return (ItemStack)ConverterList.get(Integer.valueOf(id));
}

public Map getSmeltingList()
{
         return ConverterList;
}
public float getExperience(int par1)
{
         return this.ConverterXP.containsKey(Integer.valueOf(par1)) ? ((Float)this.ConverterXP.get(Integer.valueOf(par1))).floatValue() : 0.0F;
}

}