package betterbreeds.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemXmasSpecial extends Item
{

//	public static final String[] textures = new String[] {"mintegg", "ham", "mug","beefCooked","mugEmpty", "xmas"};
	@SideOnly(Side.CLIENT)
	private Icon[] getTexture;


	public ItemXmasSpecial(int id)
	{
		super(id);
//		super.setPotionEffect(Potion.heal.id, 3, 1, 100);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
//		this.getTexture = new Icon[textures.length];
//
//		for (int i = 0; i < textures.length; ++i)
//		{
//			if( i <3 || i >3)
				this.itemIcon = par1IconRegister.registerIcon("Breeds:"+"xmas");
//			this.getTexture[3] = par1IconRegister.registerIcon(textures[3]);
//		}
	}

//	@SideOnly(Side.CLIENT)
//	@Override
//	public Icon getIconFromDamage(int par1)
//	{
//		int j = MathHelper.clamp_int(par1, 0, 6);
//		return this.getTexture[j];
//	}
//	
//
//	@Override
//	public EnumAction getItemUseAction(ItemStack par1ItemStack)
//	{
//		if(par1ItemStack.getItemDamage() == 2)
//		{
//			return EnumAction.drink;
//		}
//		if(par1ItemStack.getItemDamage() == 4)
//		{
//			return null;
//		}
//		else
//		{
//			return EnumAction.eat;
//		}
//	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
			par3EntityPlayer.swingItem();
		return par1ItemStack;
	}

//	@Override
//	public String getUnlocalizedName(ItemStack par1ItemStack)
//	{
//		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 6);
//		return super.getUnlocalizedName() + "." + textures[i];
//	}


//	@Override
//	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
//	{
//		for (int var4 = 0; var4 < 6; ++var4)
//		{
//			par3List.add(new ItemStack(par1, 1, var4));
//		}
//	}
}