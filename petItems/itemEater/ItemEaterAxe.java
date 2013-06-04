package petItems.itemEater;

import java.util.List;

import cpw.mods.fml.common.FMLLog;

import petItems.PetItems;
import petItems.entity.EntityToolPet;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemEaterAxe extends ItemAxe {

	private int XP_TOTAL;
	private Block[] eatBlocks;
	private int dmg = 0;
	public int totalHarvestLevel = 0;
	public static int harvestLvl = 0;
	private Block consumedBlock;

	/** 
	 * @param par1 Item ID
	 * @param par2 Damage vs entity
	 * @param blocks blocks this tool is effective against
	 * */
	public ItemEaterAxe(int par1, EnumToolMaterial par3EnumToolMaterial, Block[] eatAble ) {
		super(par1, par3EnumToolMaterial);
		this.setMaxDamage(100 + dmg);
		eatBlocks = eatAble;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon("petItems:" + itemName);
	}

	/**TOOLTIP W00T !*/
	@Override
	public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {
		NBTTagCompound tags = stack.getTagCompound();

		if (tags != null) {
			if (tags.hasKey("xpTotal")) {
				list.add(StatCollector.translateToLocal("Xp Total : " + String.valueOf(tags.getInteger("xpTotal"))));
				list.add(StatCollector.translateToLocal("Level : " + String.valueOf(getLevelFromXP())));
				if(getLevelFromXP() <5){
					list.add(StatCollector.translateToLocal("Xp to next Level : " + String.valueOf(getXPCapFromLevel()-tags.getInteger("xpTotal"))));
				}else{
					list.add(StatCollector.translateToLocal("Maxed Out"));
				}			
				list.add(StatCollector.translateToLocal("Mining Speed : " + String.valueOf(efficiencyOnProperMaterial )));
			}
		}
	}

	/**runs every tick if the item is in the player inventory. Sets the harvest levels and other stats.*/
	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {

		this.efficiencyOnProperMaterial = getLevelFromXP() +1f;
		this.setMaxDamage(100 + (getLevelFromXP()+1)*10);
	}
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		if(par2EntityPlayer.isSneaking()){
			EntityToolPet pet = new EntityToolPet(par3World);
			NBTTagCompound nbt = par1ItemStack.getTagCompound();

			if(nbt != null){
				if (pet != null) {
					try{
						pet.setEatBlocks(eatBlocks);
						pet.setXp(par1ItemStack.getTagCompound().getInteger("xpTotal"));
						pet.setItem(par2EntityPlayer.inventory.getCurrentItem());
						pet.setPosition(x,y+1,z);
						pet.setTamed(true);
						pet.setOwner(par2EntityPlayer.username);
						if(!par3World.isRemote){
							par3World.spawnEntityInWorld(pet);
						}
						par2EntityPlayer.inventory.consumeInventoryItem(this.itemID);

					}catch(Throwable e){

					}
				}
			}

		}
		return false;
	}

	/**Right clicking the item to check if any food, eat it, or heal.*/
	@Override
	public ItemStack onItemRightClick(ItemStack is, World par2World, EntityPlayer player)
	{
		if(!player.isSneaking()){
			if(this.hasApropriateBlock(player)){

				if((this.getMaxDamage() - is.getItemDamage()) < this.getMaxDamage()){
					is.setItemDamage(is.getItemDamage() -1);

				}else{
					int addXp =1;
					if(consumedBlock != null){
						if(consumedBlock.equals(Block.wood))
							addXp =4;
						if(consumedBlock.equals(Block.workbench))
							addXp =4;
						if(consumedBlock.equals(Block.chest))
							addXp =10;
						if(consumedBlock.equals(Block.chestTrapped))
							addXp =12;
					}

					NBTTagCompound nbt = is.getTagCompound();
					if(nbt != null){
						if(XP_TOTAL < 10000){
							if(!nbt.hasKey("xpTotal")){
								nbt.setInteger("xpTotal", addXp);
							}else{
								int xp= nbt.getInteger("xpTotal");
								nbt.setInteger("xpTotal", xp+addXp);
							}
							XP_TOTAL = nbt.getInteger("xpTotal");
						}else if (XP_TOTAL >= 10000){
							if(player.inventory.hasItem(PetItems.Upgrade6.itemID) && XP_TOTAL == 10000){
								nbt.setInteger("xpTotal", 11000);
								player.inventory.consumeInventoryItem(PetItems.Upgrade6.itemID);
							}if(player.inventory.hasItem(PetItems.Upgrade7.itemID) && XP_TOTAL == 11000){
								nbt.setInteger("xpTotal", 12000);
								player.inventory.consumeInventoryItem(PetItems.Upgrade7.itemID);
							}if(player.inventory.hasItem(PetItems.Upgrade8.itemID) && XP_TOTAL == 12000){
								nbt.setInteger("xpTotal", 13000);
								player.inventory.consumeInventoryItem(PetItems.Upgrade8.itemID);
							}
						}
					}else{
						this.onCreated(is, par2World, player);
					}
				}
			}
		}
		return is;
	}

	/**returns the item's current xp TOTAL.*/
	public int getItemTotalXp(){
		return XP_TOTAL;
	}

	public int getLevelFromXP(){

		return XP_TOTAL > 1000 && XP_TOTAL <2000 ? 1 : XP_TOTAL >= 2000 && XP_TOTAL <4000 ? 2 : XP_TOTAL >= 4000 && XP_TOTAL <7000 ? 3 :
			XP_TOTAL >= 7000&& XP_TOTAL < 10000 ? 4 : XP_TOTAL >= 10000 && XP_TOTAL < 11000? 5 : XP_TOTAL >= 11000 && XP_TOTAL <12000 ? 6 :
				XP_TOTAL >= 12000 && XP_TOTAL <13000? 7 : XP_TOTAL == 13000 ? 8 : 0;
	}

	public int getXPCapFromLevel(){
		return getLevelFromXP() == 1 ? 2000 : getLevelFromXP() == 2 ? 4000 :getLevelFromXP() == 3 ?7000 :
			getLevelFromXP() == 4 ?10000 :getLevelFromXP() == 5 ? 11000:getLevelFromXP() == 6? 12000: 
				getLevelFromXP() == 7 ? 13000:getLevelFromXP() == 8 ? 13000:2000;
	}
	/**Checks for any given blocks in the inventory for the item to consume
	 * and sets the consumed item so it can be called in onItemRightClick*/
	public boolean hasApropriateBlock(EntityPlayer player){

		for (int i = 0; i < player.inventory.mainInventory.length; i++){
			if(player.inventory.getStackInSlot(i) != null){
				for(int d = 0; d < eatBlocks.length;d++){
					ItemStack item = player.inventory.getStackInSlot(i);

					if(item.itemID == ((Block)eatBlocks[d]).blockID){

						if(XP_TOTAL < 10000){
							player.inventory.consumeInventoryItem(((Block)eatBlocks[d]).blockID);
							setConsumedItem(eatBlocks[d]);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**sets the item that is consumed, so that the item can check for it, and add more experience if needed.*/
	public void setConsumedItem(Block block){
		consumedBlock = block;
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("xpTotal", 0);
		par1ItemStack.setTagCompound(nbt);
	}

}
