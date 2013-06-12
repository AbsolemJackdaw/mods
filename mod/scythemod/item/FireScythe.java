package scythemod.item;

import java.util.List;

import scythemod.BaseScytheFile;
import scythemod.entity.EntityGhost;
import scythemod.entity.EntityTheFrozen;
import scythemod.entity.EntityZombieReaper;


import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FireScythe extends Item
{
	private int weaponDamage;
	private final EnumToolMaterialTest toolMaterial;
	public boolean active = false;


	public FireScythe(int par1, EnumToolMaterialTest par2EnumToolMaterialTest)
	{
		super(par1);
		this.toolMaterial = par2EnumToolMaterialTest;
		this.maxStackSize = 1;
		this.setMaxDamage(par2EnumToolMaterialTest.getMaxUses());
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.weaponDamage = 4 + par2EnumToolMaterialTest.getDamageVsEntity();
		active = false;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("DSM:" + itemName);
		if(active == true)
		{
			this.itemIcon = par1IconRegister.registerIcon("DSM:scythefireON");
		}
	}

	/**
    /**
	 * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
	 * sword
	 */
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		return par2Block.blockID == Block.web.blockID ? 15.0F : 1.5F;
	}

	/**
	 * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
	 * the damage on the stack.
	 */
	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		par1ItemStack.damageItem(1, par3EntityLiving);
		return true;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{

		if(stack.itemID == BaseScytheFile.FireScythe.itemID && !entity.worldObj.isRemote)
		{
			active = false;
			if(entity instanceof EntityGhost || entity instanceof EntityZombieReaper || entity instanceof EntityTheFrozen)
			{
				this.weaponDamage = 75;
				entity.setFire(3);
				active = true;
				System.out.println("It's SuperEffective !");
			}
			else
			{
				this.weaponDamage = 1;
				active = false;
				System.out.println("It's not very effective...");
			}
		}
		return false;
	}
	@SideOnly(Side.CLIENT)

	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno)
	{
		if(stack.itemID  == BaseScytheFile.FireScythe.itemID)
		{
			if(active == true)
			{
				list.add(StatCollector.translateToLocal("Active"));
			}
			else
			{
				list.add(StatCollector.translateToLocal("Unactive"));
			}
		}
	}
	public boolean func_77660_a(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
	{
		if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
		{
			par1ItemStack.damageItem(2, par7EntityLiving);
		}

		return true;
	}

	/**
	 * Returns the damage against a given entity.
	 */
	public int getDamageVsEntity(Entity par1Entity)
	{
		return this.weaponDamage;
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	public boolean isFull3D()
	{
		return true;
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.block;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	/**
	 * Returns if the item (tool) can harvest results from the block type.
	 */
	public boolean canHarvestBlock(Block par1Block)
	{
		return par1Block.blockID == Block.web.blockID;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getItemEnchantability()
	{
		return this.toolMaterial.getEnchantability();
	}

	public String func_77825_f()
	{
		return this.toolMaterial.toString();
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World par2World, EntityPlayer par3EntityPlayer)
	{

		switch( itemstack.getItemDamage() )
		{
		case -1: 
			itemstack.addEnchantment( Enchantment.knockback, 2 );
			itemstack.addEnchantment(Enchantment.fireAspect, 2);
			break;

		case -2:
			itemstack.addEnchantment( Enchantment.smite, 4 );
			break;

		case -3:
			itemstack.addEnchantment( Enchantment.smite, 5 );
			break;

		case -4:
			itemstack.addEnchantment( Enchantment.sharpness, 1 );
			break;

		case -5:
			itemstack.addEnchantment(Enchantment.knockback, 2 );
			itemstack.addEnchantment(Enchantment.fireAspect, 2);
			itemstack.addEnchantment(Enchantment.sharpness, 1 );
			break;

		case -6:
			break;

		}
		itemstack.setItemDamage( 0 );
	}


}
