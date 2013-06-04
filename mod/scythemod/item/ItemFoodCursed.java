package scythemod.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFoodCursed extends ItemFood
{
	public ItemFoodCursed(int i, int j, float f, boolean B)
	{
		super(i, j, B);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("DSM:" + itemName);
	}
	
	public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		entityPlayer.getFoodStats().addStats(this);
		world.playSoundAtEntity(entityPlayer, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		itemStack.stackSize--;
		entityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 10 * 40, 6));
		entityPlayer.addChatMessage("A thousand wheaping souls hurl trough your mind...");    
	}
}