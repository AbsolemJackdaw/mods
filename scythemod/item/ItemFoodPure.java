package scythemod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFoodPure extends ItemFood
{
	public ItemFoodPure(int i, int j, float f, boolean B)
	{
		super(i, j, B);
	}
	public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		entityPlayer.getFoodStats().addStats(this);
		world.playSoundAtEntity(entityPlayer, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		itemStack.stackSize--;
		entityPlayer.addPotionEffect(new PotionEffect(Potion.heal.id, 10 * 4, 6));
		entityPlayer.addChatMessage("A thousand souls make your heart pump...");    

	}
}