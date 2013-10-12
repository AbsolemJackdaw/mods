package betterbreeds.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import betterbreeds.ModBreeds;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCage extends Item{

	public ItemCage(int par1) {
		super(par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		if(par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey(ModBreeds.tag)){
			par3List.add("Animal Stowed : " + par1ItemStack.getTagCompound().getString(ModBreeds.tag));
		}

		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("minecraft:apple");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

		if(par1ItemStack.getTagCompound() != null && 
				par1ItemStack.getTagCompound().hasKey(ModBreeds.tag)){
			if(!(par1ItemStack.getTagCompound().getString(ModBreeds.tag).equals("none"))){
				Entity entity = EntityList.createEntityByName(par1ItemStack.getTagCompound().getString(ModBreeds.tag), par3World);
				entity.setLocationAndAngles(par4, par5+1, par6, 0, 0);
				if(!par2EntityPlayer.worldObj.isRemote)
					par3World.spawnEntityInWorld(entity);
				par1ItemStack.getTagCompound().setString(ModBreeds.tag, "none");	
			}else{
				if(!par2EntityPlayer.worldObj.isRemote)
					par2EntityPlayer.addChatMessage("The Animal Trap is empty ... ");
			}
		}else{
			if(!par2EntityPlayer.worldObj.isRemote)
				par2EntityPlayer.addChatMessage("The Animal Trap is empty ... ");
		}

		return true;
	}


	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase)
	{
		if(par1ItemStack.getTagCompound() == null)
			par1ItemStack.setTagCompound(new NBTTagCompound());

		if(par1ItemStack.getTagCompound().hasKey(ModBreeds.tag) && (!par1ItemStack.getTagCompound().getString(ModBreeds.tag).equals("none"))){
			if(!par2EntityPlayer.worldObj.isRemote)
				par2EntityPlayer.addChatMessage("The Animal Trap still has a " + par1ItemStack.getTagCompound().getString(ModBreeds.tag) + " inside !");
			return false;
		}else{
			if(par3EntityLivingBase instanceof EntityAnimal){
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString(ModBreeds.tag, EntityList.getEntityString(par3EntityLivingBase));
				par1ItemStack.setTagCompound(nbt);
				par3EntityLivingBase.setDead();
				if(!par2EntityPlayer.worldObj.isRemote)
					par2EntityPlayer.addChatMessage("Stored a " + EntityList.getEntityString(par3EntityLivingBase) + " in the Animal trap");
				par2EntityPlayer.setCurrentItemOrArmor(0, par1ItemStack);
			}else{
				if(!par2EntityPlayer.worldObj.isRemote)
					par2EntityPlayer.addChatMessage("That's not an animal !");
			}
		}
		return false;
	}

}
