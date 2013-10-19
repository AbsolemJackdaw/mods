package redstone.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import redstone.Targetting;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDefenseGun extends Item{

	public ItemDefenseGun(int par1) {
		super(par1);
		setFull3D();
		maxStackSize = 1;
		setMaxDamage(750);
	}

	public boolean isGunShot = false;
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("redstonehandguns:defGun");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("Base Dmg: 5");
		par3List.add("Range : 40 Blocks");
		par3List.add("Instant Hit");
		if(par1ItemStack.getTagCompound() != null){
			if(par1ItemStack.getTagCompound().hasKey("ammo")){
				par3List.add("Ammo : "+ par1ItemStack.getTagCompound().getInteger("ammo"));
			}else{
				par3List.add("Ammo : 0");
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		if(par1ItemStack.getTagCompound() == null)
			par1ItemStack.setTagCompound(new NBTTagCompound());

		if(par1ItemStack.getTagCompound().getTag("ammo") == null)
			par1ItemStack.getTagCompound().setInteger("ammo", 0);


		int ammo = par1ItemStack.getTagCompound().getInteger("ammo");

		if(ammo == 0)
			reload(par3EntityPlayer, par1ItemStack);
		else
			shoot(par2World, par3EntityPlayer, par1ItemStack);

		return par1ItemStack;
	}

	private void reload(EntityPlayer player, ItemStack stack){
		player.setItemInUse(stack, 72000);
	}

	private void shoot(World world, EntityPlayer player, ItemStack stack){
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int par4)
	{
		if(stack.getTagCompound() != null){
			if(stack.getTagCompound().hasKey("ammo")){
				if(stack.getTagCompound().getInteger("ammo") <=0){
						if(player.inventory.hasItem(Item.coal.itemID)){
							while (player.inventory.hasItem(Item.coal.itemID)) {
								if(stack.getTagCompound().getInteger("ammo") < 6){
									player.inventory.consumeInventoryItem(Item.coal.itemID);
									stack.getTagCompound().setInteger("ammo", 
											player.getCurrentEquippedItem().getTagCompound().getInteger("ammo")+1);
									player.playSound("fire.ignite", 1f, 1f);
								}else{
									return;
								}
							}
						}else{
							if(!player.worldObj.isRemote)
								player.addChatMessage("no ammo available.");
							return;
						}
					}
				}
			}
		if (world!= null && world.isRemote && FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			EntityLivingBase el= Targetting.isTargetingLivingEntity(40.0D); 

			if (el != null ) {
				if(stack.getTagCompound() != null && stack.getTagCompound().getInteger("ammo") > 0){
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					DataOutputStream dos = new DataOutputStream(bos);
					try {
						dos.writeInt(el.entityId);
						dos.writeInt(5);
					} catch (Throwable ex) {
					}
					Packet250CustomPayload pcp = new Packet250CustomPayload("GunPacket", bos.toByteArray());
					PacketDispatcher.sendPacketToServer(pcp);
				}
			}
		}

		stack.getTagCompound().setInteger("ammo", stack.getTagCompound().getInteger("ammo")-1);	
		player.playSound("fireworks.blast", 1.0f, 1.0f);
		isGunShot = true;
		stack.damageItem(1, player);
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		par1ItemStack.setTagCompound(new NBTTagCompound());
		par1ItemStack.getTagCompound().setInteger("ammo", 0);

	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {

		if(par1ItemStack.getTagCompound() != null){
			if(par1ItemStack.getTagCompound().hasKey("ammo")){
				if(par1ItemStack.getTagCompound().getInteger("ammo") <=0){
					return EnumAction.block;
				}
			}
		}
		return EnumAction.bow;
	}
}
