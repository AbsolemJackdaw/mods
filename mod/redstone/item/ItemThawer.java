package redstone.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemThawer extends Item{


	public int thawerState = 0;

	public String state = "";
	private int overflow;

	private String ammoTag = "ammo";
	private String thawerTag = "thawerState";

//	private int ammoTracker = -1;

	public ItemThawer(int par1, int overflow) {
		super(par1);
		this.setMaxDamage(600);
		this.maxStackSize = 1;
		this.overflow = overflow;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return false;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("redstonehandguns:" + itemName);
	}

	public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {
//
//		NBTTagCompound nbt = stack.getTagCompound();
//		list.add("Base Dmg: 5");
//		list.add("Switchable State");
//		if(stack.getTagCompound() != null){
//			list.add("" + getState(stack));
//			
//			if(stack.getTagCompound().hasKey(ammoTag)){
//				list.add("Ammo : "+ stack.getTagCompound().getInteger(ammoTag));
//			}else{
//				list.add("Ammo : 0");
//			}
//		}
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack){
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack stack){

		if(stack.getTagCompound() != null){
			if(stack.getTagCompound().hasKey(ammoTag)){
				return stack.getTagCompound().getInteger(ammoTag) == 0 ? EnumAction.block : EnumAction.bow;
			}
		}
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

//		if(par1ItemStack.getTagCompound() == null)
//			par1ItemStack.setTagCompound(new NBTTagCompound());
//
//		if(par1ItemStack.getTagCompound().getTag(ammoTag) == null)
//			par1ItemStack.getTagCompound().setInteger(ammoTag, 0);
//
//		int ammo = par1ItemStack.getTagCompound().getInteger(ammoTag);
//
//		if(ammo == 0)
//			reload(par3EntityPlayer, par1ItemStack);
//		else
//			shoot(par3EntityPlayer, par1ItemStack);

		return par1ItemStack;
	}

	@Override
	public void onUsingItemTick(ItemStack stack, EntityPlayer p, int count) {

//		NBTTagCompound nbt = stack.getTagCompound();
//
//		ThawAmmo var9 = new ThawAmmo(p.worldObj, p, stack.getTagCompound().getInteger(thawerTag));
//		if (nbt.getInteger(ammoTag) > 0) {
//			
////			if(ammoTracker == -1)
////				ammoTracker = nbt.getInteger(ammoTag);
////
////			if(ammoTracker == 0 || nbt.getInteger(ammoTag) == 0 ){
////				p.stopUsingItem();
////				return;
////			}
////			ammoTracker--;
//			
//			nbt.setInteger(ammoTag,nbt.getInteger(ammoTag)-1);
//
//			if (!p.worldObj.isRemote)
//				p.worldObj.spawnEntityInWorld(var9);
//			if(stack.getTagCompound().getInteger(thawerTag) == 0)
//				p.worldObj.playSoundAtEntity(p, "liquid.water", 1.0F, 1.0F / (p.worldObj.rand.nextFloat() * 0.4F + 1.2F) + 0.7f * 0.5F);
//			else
//				p.worldObj.playSoundAtEntity(p, "liquid.lava", 1.0F, 1.0F / (p.worldObj.rand.nextFloat() * 0.4F + 1.2F) + 0.7f * 0.5F);
//		}
//		//p.addChatMessage(""+ ammoTracker);

		super.onUsingItemTick(stack, p, count);
	}

	private String getState(ItemStack stack){
		if(stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());

		if(!stack.getTagCompound().hasKey(thawerTag))
			stack.getTagCompound().setInteger(thawerTag,0);

		if(stack.getTagCompound().getInteger(thawerTag) == 0){
			state = "Frozen";
		}else{
			state = "Liquid";
		}

		return state;
	}


	private void reload(EntityPlayer player, ItemStack stack){
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
	}

	private void shoot(EntityPlayer player, ItemStack stack){
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer p){
		stack.setTagCompound(baseNBT());
	}	


	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World par2World,
			EntityPlayer player, int par4) {

//		if(stack.getTagCompound().hasKey(ammoTag)){
//			if(stack.getTagCompound().getInteger(ammoTag) <=0){
//				
//				for(int i = 0; i < player.inventory.mainInventory.length; i++){
//					if(i <9){
//						if(stack.getTagCompound().getInteger(ammoTag) <=0){
//							if(player.inventory.getStackInSlot(i) != null){
//
//								int objectID = player.inventory.getStackInSlot(i).itemID;
//
//								if(objectID == Item.bucketWater.itemID){
//
//									thawerState = 0;
//									stack.getTagCompound().setInteger(thawerTag,thawerState);
//									stack.getTagCompound().setInteger(ammoTag, 1000);
//									player.playSound("fire.ignite", 1f, 1f);
//									player.inventory.setInventorySlotContents(i, new ItemStack(Item.bucketEmpty));
////									ammoTracker = 1000;
//									break;
//								}
//								if(objectID == Item.bucketLava.itemID){
//
//									thawerState = 1;
//									stack.getTagCompound().setInteger(thawerTag,thawerState);
//
//									stack.getTagCompound().setInteger(ammoTag, 1000);
//									player.playSound("fire.ignite", 1f, 1f);
//									player.inventory.setInventorySlotContents(i, new ItemStack(Item.bucketEmpty));
////									ammoTracker = 1000;
//									break;
//								}
//							}
//						}
//					}
//				}
//				if(stack.getTagCompound().getInteger(ammoTag) <=0){
//					if(!player.worldObj.isRemote)
//						player.addChatMessage("no ammo available.");
//				}
//			}else{
//				stack.getTagCompound().setInteger(ammoTag, 1000);
//			}
//		}
//		super.onPlayerStoppedUsing(stack, par2World, player, par4);
	}

	public NBTTagCompound baseNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger(thawerTag, 0);
		nbt.setInteger("ammo", 0);
		return nbt;
	}

}
