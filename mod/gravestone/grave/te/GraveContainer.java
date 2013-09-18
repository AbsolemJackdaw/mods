package gravestone.grave.te;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class GraveContainer extends Container{

	protected TEGrave te;
	int slotCount = 0;

	public GraveContainer(InventoryPlayer inv, TEGrave te)
	{


		this.te = te;

		if(inv.player.capabilities.isCreativeMode){
			for (int i = 0; i < 4; i++)
			{
				for (int k = 0; k < 9; k++)
				{
					addSlotToContainer(new Slot(te,slotCount++, 
							8 + k * 18,
							18 + i * 18));
				}
			}
			for (int i = 0; i < 4; i++)
			{
				addSlotToContainer(new SlotArmorGrave(te,slotCount++, 
						174 ,
						18*4 - i * 18));

			}
		}else{
			for (int i = 0; i < 4; i++)
			{
				for (int k = 0; k < 9; k++)
				{
					addSlotToContainer(new SlotGrave(te,slotCount++, 
							8 + k * 18,
							18 + i * 18));
				}
			}
			for (int i = 0; i < 4; i++)
			{
				addSlotToContainer(new SlotGrave(te,slotCount++, 
						174 ,
						18*4 - i * 18));
			}
		}



		fillInv(inv);
		//		detectAndSendChanges();
	}



	private void fillInv(InventoryPlayer inv){

		for (int i = 0; i < 3; i++)
		{
			for (int k = 0; k < 9; k++)
			{
				addSlotToContainer(new Slot(inv, k + i * 9 + 9, 8 + k * 18, 104 + i * 18));
			}
		}

		for (int j = 0; j < 9; j++)
		{
			addSlotToContainer(new Slot(inv, j, 8 + j * 18, 162));
		}

	}
	/**
	 * Callback for when the crafting matrix is changed.
	 */
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

	}


	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return te.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack slotClick(int par1, int par2, int par3,
			EntityPlayer par4EntityPlayer) {
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
	{
		ItemStack stack = null;

		Slot slot= (Slot)inventorySlots.get(slotID);

		if(slot != null && slot.getHasStack()){
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if(slot.inventory instanceof TEGrave){
				if(!this.mergeItemStack(stack, 40, 76, true)){
					return null;
				}
				if(slotStack.stackSize == 1){
					slot.putStack(null);
				}else{
					slot.putStack(null);
					return null;
				}if(slotStack.stackSize == stack.stackSize){
					return null;
				}

				slot.onPickupFromSlot(player, slotStack);
			}
			else {
				return null;
			}					
		}		
		return stack;
	}
}
