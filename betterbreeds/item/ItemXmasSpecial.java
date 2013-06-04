package betterbreeds.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import betterbreeds.ModBreeds;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemXmasSpecial extends ItemFood
{

	public static final String[] textures = new String[] {"mintegg", "ham", "mug","beefCooked","mugEmpty", "xmas"};
	@SideOnly(Side.CLIENT)
	private Icon[] getTexture;


	public ItemXmasSpecial(int id)
	{
		super(id, 4, 1.0f, false);
		super.setPotionEffect(Potion.heal.id, 3, 1, 100);
		this.hasSubtypes = true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.getTexture = new Icon[textures.length];

		for (int i = 0; i < textures.length; ++i)
		{
			if( i <3 || i >3)
				this.getTexture[i] = par1IconRegister.registerIcon("Breeds:"+textures[i]);
			this.getTexture[3] = par1IconRegister.registerIcon(textures[3]);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int par1)
	{
		int j = MathHelper.clamp_int(par1, 0, 6);
		return this.getTexture[j];
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		if(par1ItemStack.getItemDamage() == 2)
		{
			return EnumAction.drink;
		}
		if(par1ItemStack.getItemDamage() == 4)
		{
			return null;
		}
		else
		{
			return EnumAction.eat;
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par1ItemStack.getItemDamage() < 4 )
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}
		else
		{
			par3EntityPlayer.swingItem();
		}

		return par1ItemStack;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 6);
		return super.getUnlocalizedName() + "." + textures[i];
	}


	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < 6; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}
}