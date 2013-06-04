package scythemod.item;

import scythemod.BaseScytheFile;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHaloGod extends Item {
	public boolean field_70885_d = false;
	public float field_70886_e = 0.0F;
	public float destPos = 0.0F;
	public float field_70884_g;
	public float field_70888_h;
	public float field_70889_i = 1.0F;

	public ItemHaloGod(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.maxStackSize = 1;
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		return true;
	}

	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		EntityPlayer par1 = (EntityPlayer) par3Entity;
		if(par3Entity instanceof EntityPlayer)
		{ 
			if( par1.inventory.hasItem(BaseScytheFile.GodHalo.itemID) && !(par1.inventory.hasItem(BaseScytheFile.DedHalo.itemID)) && par2World.provider.isHellWorld == false)
			{

				par3Entity.fallDistance = 0.0F;
				par1.landMovementFactor = 0.1F;
				if(par1.getFoodStats().getFoodLevel()<18)
				{
					par1.getFoodStats().setFoodLevel(20);
				}
				if (!(par1.getFoodStats().getFoodLevel() < 0) && (!(par1.isInWater())))
				{
					par1.heal(20);
				}

				if(par1.isJumping)
				{
					par1.motionY = 0.41999998688697815D;

					if (par1.isSprinting())
					{
						float var1 = par1.rotationYaw * 0.017453292F;
						par1.motionX -= (double)(MathHelper.sin(var1) * 0.2F);
						par1.motionZ += (double)(MathHelper.cos(var1) * 0.2F);
					}
				}

				this.field_70888_h = this.field_70886_e;
				this.field_70884_g = this.destPos;
				this.destPos = (float)((double)this.destPos + (double)(par1.onGround ? -1 : 4) * 0.3D);

				if (this.destPos < 0.0F)
				{
					this.destPos = 0.0F;
				}

				if (this.destPos > 1.0F)
				{
					this.destPos = 1.0F;
				}



				this.field_70889_i = (float)((double)this.field_70889_i * 0.9D);

				if (!par1.onGround && par1.motionY < 0.0D)
				{
					par1.motionY *= 0.9D;
				}

				if(par1.isSneaking() && !par1.onGround && par1.motionY < 0.0D)
				{
					par1.motionY *= 2.0D;
				}
			}	 

		}

		if (par1.inventory.hasItem(BaseScytheFile.DedHalo.itemID) && (par1.inventory.hasItem(BaseScytheFile.GodHalo.itemID)))	  
		{
			((EntityLiving)par3Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 60, 5));
			((EntityLiving)par3Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 3));
			((EntityLiving)par3Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 80, 5));


		}
		if( par1.inventory.hasItem(BaseScytheFile.GodHalo.itemID) && !(par1.inventory.hasItem(BaseScytheFile.DedHalo.itemID)) && par2World.provider.isHellWorld == true)
		{
			par1.landMovementFactor = par1.capabilities.getWalkSpeed()*3;
			((EntityLiving)par3Entity).addPotionEffect(new PotionEffect(Potion.nightVision.id, 60, 5));
			if(par1.getFoodStats().getFoodLevel()<18)	
			{
				par1.getFoodStats().setFoodLevel(20);
			}
			if (!(par1.getFoodStats().getFoodLevel() < 0) && (!(par1.isInWater())))
			{
				par1.heal(20);
			}
		}
	}
	protected void fall(float par1) {}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("DSM:" + itemName);
	}

}
