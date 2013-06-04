package scythemod.block.te;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerConverter extends Container
{

	private TileEntityConverter converterConv;
	private int lastConverterConvTime;
	private int lastConverterBurnTime;
	private int lastConverterItemconvTime;

	public ContainerConverter(InventoryPlayer par1InventoryPlayer, TileEntityConverter par2TileEntityConverter)
	{
		lastConverterConvTime = 0;
		lastConverterBurnTime = 0;
		lastConverterItemconvTime = 0;
		converterConv = par2TileEntityConverter;
		addSlotToContainer(new Slot(par2TileEntityConverter, 0, 86, 20));
		addSlotToContainer(new Slot(par2TileEntityConverter, 1, 46, 45));
		addSlotToContainer(new SlotConverter(par1InventoryPlayer.player, par2TileEntityConverter, 2, 126, 40));

		for (int i = 0; i < 3; i++)
		{
			for (int k = 0; k < 9; k++)
			{
				addSlotToContainer(new Slot(par1InventoryPlayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}

		for (int j = 0; j < 9; j++)
		{
			addSlotToContainer(new Slot(par1InventoryPlayer, j, 8 + j * 18, 142));
		}
	}

	/**
	 * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
	 */
	 public void updateCraftingResults()
	{
		super.detectAndSendChanges();
		Iterator var1 = this.crafters.iterator();

		while (var1.hasNext())
		{
			ICrafting var2 = (ICrafting)var1.next();

			if (this.lastConverterConvTime != this.converterConv.goldCookTime)
			{
				var2.sendProgressBarUpdate(this, 0, this.converterConv.goldCookTime);
			}

			if (this.lastConverterBurnTime != this.converterConv.goldBurnTime)
			{
				var2.sendProgressBarUpdate(this, 1, this.converterConv.goldBurnTime);
			}

			if (this.lastConverterItemconvTime != this.converterConv.goldItemBurnTime)
			{
				var2.sendProgressBarUpdate(this, 2, this.converterConv.goldItemBurnTime);
			}
		}

		this.lastConverterConvTime = this.converterConv.goldCookTime;
		this.lastConverterBurnTime = this.converterConv.goldBurnTime;
		this.lastConverterItemconvTime = this.converterConv.goldItemBurnTime;
	}

	 public void updateProgressBar(int par1, int par2)
	 {
		 if (par1 == 0)
		 {
			 converterConv.goldCookTime = par2;
		 }

		 if (par1 == 1)
		 {
			 converterConv.goldBurnTime = par2;
		 }

		 if (par1 == 2)
		 {
			 converterConv.goldItemBurnTime = par2;
		 }
	 }

	 public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	 {
		 return converterConv.isUseableByPlayer(par1EntityPlayer);
	 }

	 /**
	  * Called to transfer a stack from one inventory to the other eg. when shift clicking.
	  */
	 @Override
	 public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber)
	 {
		 ItemStack itemstack = null;
		 Slot slot = (Slot)inventorySlots.get(slotnumber);

		 if (slot != null && slot.getHasStack())
		 {
			 ItemStack itemstack1 = slot.getStack();
			 itemstack = itemstack1.copy();

			 if (slotnumber == 2)
			 {
				 if (!mergeItemStack(itemstack1, 3, 39, true))
				 {
					 return null;
				 }

				 slot.onSlotChange(itemstack1, itemstack);
			 }
			 else if (slotnumber == 1 || slotnumber == 0)
			 {
				 if (!mergeItemStack(itemstack1, 3, 39, false))
				 {
					 return null;
				 }
			 }
			 else if (RecipesConverter.smelting().getSmeltingResult(itemstack1.getItem().itemID) != null)
			 {
				 if (!mergeItemStack(itemstack1, 0, 1, false))
				 {
					 return null;
				 }
			 }
			 else if (TileEntityConverter.isItemFuel(itemstack1))
			 {
				 if (!mergeItemStack(itemstack1, 1, 2, false))
				 {
					 return null;
				 }
			 }
			 else if (slotnumber >= 3 && slotnumber < 30)
			 {
				 if (!mergeItemStack(itemstack1, 30, 39, false))
				 {
					 return null;
				 }
			 }
			 else if (slotnumber >= 30 && slotnumber < 39 && !mergeItemStack(itemstack1, 3, 30, false))
			 {
				 return null;
			 }

			 if (itemstack1.stackSize == 0)
			 {
				 slot.putStack(null);
			 }
			 else
			 {
				 slot.onSlotChanged();
			 }

			 if (itemstack1.stackSize == itemstack.stackSize)
			 {
				 return null;
			 }

			 slot.onPickupFromSlot(player, itemstack);
		 }

		 return itemstack;
	 }

}    