package petItems.itemEater;

import java.util.List;

import petItems.PetItems;
import petItems.entity.EntityToolPet;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemEaterSword extends ItemSword {

	private int XP_TOTAL;
	private Item[] eatBlocks;
	private int dmg = 0;
	public int totalHarvestLevel = 0;
	public static int harvestLvl = 0;
	private int weaponDamage;
	private Item consumedItem;

	/** 
	 * @param par1 Item ID
	 * @param par2 Damage vs entity
	 * @param blocks blocks this tool is effective against
	 * */
	public ItemEaterSword(int par1, EnumToolMaterial par3EnumToolMaterial, Item[] eatAble ) {
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
				}				list.add(StatCollector.translateToLocal("Damage : " + String.valueOf(this.weaponDamage)));
			}
		}
	}
	
	public int getDamageVsEntity(Entity par1Entity)
	{
		return this.weaponDamage;
	}
	
	/**runs every tick if the item is in the player inventory. Sets the harvest levels and other stats.*/
	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
		this.weaponDamage = getLevelFromXP()+2+((int)getLevelFromXP()/2);
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
						pet.setEatItems(eatBlocks);
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
	/*here are the values set for each individual items. i could add the values in @mod file though*/
	@Override
	public ItemStack onItemRightClick(ItemStack is, World par2World, EntityPlayer player)
	{
		if(!player.isSneaking()){
			if(this.hasApropriateBlock(player)){

				if((this.getMaxDamage() - is.getItemDamage()) < this.getMaxDamage()){
					is.setItemDamage(is.getItemDamage() -1);

				}else{
					NBTTagCompound nbt = is.getTagCompound();
					int addXp =1;
					if(consumedItem != null){
						if(consumedItem.equals(Item.diamond))
							addXp =20;
						if(consumedItem.equals(Item.emerald))
							addXp =15;
						if(consumedItem.equals(Item.ingotGold) || consumedItem.equals(Item.ingotIron))
							addXp =5;
					}

					if(nbt != null){
						if(XP_TOTAL < 10000){
							if(!nbt.hasKey("xpTotal")){
								nbt.setInteger("xpTotal", 1);
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
						this.onMissedUse(is, par2World, player,addXp);
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

	/*returns an integer between 0 and 8 depending on the value of XP_TOTAL*/
	public int getLevelFromXP(){
		return XP_TOTAL > 1000 && XP_TOTAL <2000 ? 1 : XP_TOTAL >= 2000 && XP_TOTAL <4000 ? 2 : XP_TOTAL >= 4000 && XP_TOTAL <7000 ? 3 :
			XP_TOTAL >= 7000&& XP_TOTAL < 10000 ? 4 : XP_TOTAL >= 10000 && XP_TOTAL < 11000? 5 : XP_TOTAL >= 11000 && XP_TOTAL <12000 ? 6 :
				XP_TOTAL >= 12000 && XP_TOTAL <13000? 7 : XP_TOTAL == 13000 ? 8 : 0;
	}

	/*return an integer which represents the maximum value needed to pass that level.*/
	public int getXPCapFromLevel(){
		return getLevelFromXP() == 1 ? 2000 : getLevelFromXP() == 2 ? 4000 :getLevelFromXP() == 3 ?7000 :
			getLevelFromXP() == 4 ?10000 :getLevelFromXP() == 5 ? 11000:getLevelFromXP() == 6? 12000: 
				getLevelFromXP() == 7 ? 13000:getLevelFromXP() == 8 ? 13000: 1000;
	}

	/**Checks for any given items in the inventory for the hunger item to consume*/
	public boolean hasApropriateBlock(EntityPlayer player){

		for (int i = 0; i < player.inventory.mainInventory.length; i++){
			if(player.inventory.getStackInSlot(i) != null){
				for(int c = 0; c < eatBlocks.length;c++){
					Item item = player.inventory.getStackInSlot(i).getItem();
					if(item.equals(eatBlocks[c])){
						if(XP_TOTAL < 10000){
							player.inventory.consumeInventoryItem(((Item)eatBlocks[c]).itemID);
						}
						setConsumedItem(item);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**sets the item that is consumed, so that the item can check for it, and add more experience if needed.
	 * i dont think i use this somewhere ...*/
	public void setConsumedItem(Item item){
		consumedItem = item;
	}

	public void onMissedUse(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int xp) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("xpTotal", xp);
		par1ItemStack.setTagCompound(nbt);
	}
	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("xpTotal", 0);
		par1ItemStack.setTagCompound(nbt);
	}

}
