package petItems.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import petItems.itemEater.ItemEaterAxe;
import petItems.itemEater.ItemEaterPick;
import petItems.itemEater.ItemEaterSpade;
import petItems.itemEater.ItemEaterSword;
import cpw.mods.fml.common.network.PacketDispatcher;

public class EntityToolPet extends EntityTameable
{

	public ItemStack toolPetItem;
	private int xp;
	public String toolname = "";
	private Item[] eatItems;
	private Block[] eatBlocks;
	public EntityToolPet(World par1World)
	{
		super(par1World);
		this.texture = "/mob/char.png";
		this.setSize(0.8F, 0.8F);
		this.moveSpeed = 0.3F;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, this.moveSpeed, 10.0F, 2.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, Entity.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		/**Later maybe*/
		//        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		//        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		//        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
	}
	public EntityToolPet(World par1World, String name, ItemStack item)
	{
		super(par1World);
		this.texture = "/mob/char.png";
		this.setSize(0.8F, 0.8F);
		this.moveSpeed = 0.3F;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, this.moveSpeed, 10.0F, 2.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		toolPetItem = item;
		toolname = name;
	}

	/*used for the itemstack that returns this entity*/
	public void setXp(int i){
		this.xp = i;
	}
	/*used for the itemstack that returns this entity*/
	public int getXp(){
		return xp;
	}
	public void setItem(ItemStack item){
		toolPetItem = item;
	}
	/*sets the eatBlocks to a set of blocks that the entity can consume*/
	public void setEatBlocks(Block[] eat){
		eatBlocks = eat;
	}
	/*sets the eatBlocks to a set of items that the entity can consume*/
	public void setEatItems(Item[] eat){
		eatItems = eat;
	}

	public void setName(String name){
		toolname = name;
	}
	public String getName(){
		return toolname;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	public int getMaxHealth()
	{
		return 6;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(18, new Integer(this.getHealth()));
	}


	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setInteger("xp", xp);
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		xp =par1NBTTagCompound.getInteger("xp");
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	protected boolean canDespawn()
	{
		return false;
	}

	public float getEyeHeight()
	{
		return this.height ;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		if(par1DamageSource.damageType.equals("outOfWorld")){
			return super.attackEntityFrom(par1DamageSource, par2);
		}
		return true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		int i = 1;
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		if (this.isTamed())
		{
			if(this.getOwnerName().equals(par1EntityPlayer.username)){
				if(par1EntityPlayer.inventory.getCurrentItem() != null){
					if(this.hasApropriateBlock(par1EntityPlayer)){
						if(par1EntityPlayer.inventory.getCurrentItem()!=null){
							if(getTool() == 1){
								if(par1EntityPlayer.inventory.getCurrentItem().getItem().equals(Item.diamond)){
									xp+=20;
								}else if(par1EntityPlayer.inventory.getCurrentItem().getItem().equals(Item.emerald)){
									xp+=15;
								}else if(par1EntityPlayer.inventory.getCurrentItem().getItem().equals(Item.ingotIron)){
									xp+=20;
								}else if(par1EntityPlayer.inventory.getCurrentItem().getItem().equals(Item.ingotGold)){
									xp+=20;
								}else{
									xp+=1;
								}
							}else if(getTool() == 3){
								if(par1EntityPlayer.inventory.getCurrentItem().itemID == (Block.wood.blockID)){
									xp+=4;
								}else if(par1EntityPlayer.inventory.getCurrentItem().itemID == (Block.chest.blockID)){
									xp+=10;
								}else if(par1EntityPlayer.inventory.getCurrentItem().itemID == (Block.chestTrapped.blockID)){
									xp+=12;
								}
							}else{
								if(par1EntityPlayer.inventory.getCurrentItem().itemID == (Block.oreIron.blockID)){
									xp+=5;
								}else if(par1EntityPlayer.inventory.getCurrentItem().itemID == (Block.oreGold.blockID)){
									xp+=5;
								}else if(par1EntityPlayer.inventory.getCurrentItem().itemID == (Block.blockRedstone.blockID)){
									xp+=15;
								}else if(par1EntityPlayer.inventory.getCurrentItem().itemID == (Block.oreLapis.blockID)){
									xp+=50;
								}
								else{
									xp+=1;
								}
							}
						}
					}

				}else{
					if(toolPetItem != null){
						ByteArrayOutputStream bytes = new ByteArrayOutputStream();
						ObjectOutput out;
						DataOutputStream outputStream = new DataOutputStream(bytes);
						try {
							outputStream.writeInt(1);
							outputStream.writeInt(getTool());
							outputStream.writeInt(xp);
							Packet250CustomPayload packet = new Packet250CustomPayload("petTools", bytes.toByteArray());
							PacketDispatcher.sendPacketToServer(packet);
						} catch (IOException e) {
						}
						this.setDead();
					}
				}
			}
		}
		return super.interact(par1EntityPlayer);
	}

	/**Checks for any given items in the inventory for the hunger item to consume*/
	public boolean hasApropriateBlock(EntityPlayer player){

		for (int i = 0; i < player.inventory.mainInventory.length; i++){
			if(player.inventory.getStackInSlot(i) != null){

				if(this.getTool() == 1){
					for(int c = 0; c < eatItems.length;c++){
						Item item = player.inventory.getStackInSlot(i).getItem();
						if(item.equals(eatItems[c])){
							if(xp < 10000){
								player.inventory.consumeInventoryItem(((Item)eatItems[c]).itemID);
								return true;
							}
						}
					}
				}else if(this.getTool() >1){
					for(int d = 0; d < eatBlocks.length;d++){
						ItemStack item = player.inventory.getStackInSlot(i);
						if(item.itemID == ((Block)eatBlocks[d]).blockID){
							if(xp < 10000){
								player.inventory.consumeInventoryItem(eatBlocks[d].blockID);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/*returns an integer depending on a different item. used to send a packet and retrieve the right item.*/
	private int getTool(){
		if (this.toolPetItem != null)
		{
			return toolPetItem.getItem() instanceof ItemEaterSword ? 1 :
				toolPetItem.getItem() instanceof ItemEaterSpade ? 2:
					toolPetItem.getItem() instanceof ItemEaterAxe ? 3:
						toolPetItem.getItem() instanceof ItemEaterPick ? 4 :0;
		}
		return 0;
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	 */
	public EntityToolPet spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return null;
	}

	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}
}
