package petBuddy.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import petBuddy.BuddyUtils;
import petBuddy.PetBuddyMain;
import petBuddy.RichTools.Targetting;
import petBuddy.entity.EntityBuddy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PetStatue extends Item {

	EntityBuddy pet;
	public PetStatue(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setMaxStackSize(1);

	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("brick");
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {

		String itemname = " Statue";
		String total = " Unity Statue";
		NBTTagCompound tags = par1ItemStack.getTagCompound();
		if (tags != null) {
			if (tags.hasKey("guiID")) {
				total = BuddyUtils.IDToName(tags.getInteger("guiID")) + itemname;
			}
		}

		return total;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {



		if(!par3World.isRemote){
			if(PetBuddyMain.playersWithPets.containsKey(par2EntityPlayer.username))
			{
				EntityBuddy oldPet = (EntityBuddy) par3World.getEntityByID(PetBuddyMain.playersWithPets.get(par2EntityPlayer.username));
				if(oldPet != null && par1ItemStack.stackTagCompound != null)
				{
					par1ItemStack.stackTagCompound.setInteger("guiID", oldPet.getGuiId());

					par1ItemStack.stackTagCompound.setString("name", oldPet.getName());

					par1ItemStack.stackTagCompound.setString("skin", oldPet.getSkinName());

					par1ItemStack.stackTagCompound.setFloat("d_c_1", oldPet.getDragonColor());
					par1ItemStack.stackTagCompound.setFloat("d_c_2", oldPet.getDragonColor2());
					par1ItemStack.stackTagCompound.setFloat("d_c_3", oldPet.getDragonColor3());

					par1ItemStack.stackTagCompound.setFloat("s_c_1", oldPet.getColor());
					par1ItemStack.stackTagCompound.setFloat("s_c_2", oldPet.getColor2());
					par1ItemStack.stackTagCompound.setFloat("s_c_3", oldPet.getColor3());

				}
				PetBuddyMain.playersWithPets.remove(par2EntityPlayer.username);
			}
			else
			{
				if(par1ItemStack.stackTagCompound != null)
				{
					if(par1ItemStack.stackTagCompound.hasKey("guiID")){
						if(par1ItemStack.stackTagCompound.hasKey("name")){
							if(par1ItemStack.stackTagCompound.hasKey("skin")){

								pet = new EntityBuddy(par3World, par2EntityPlayer);
								pet.setPosition(par4+0.5, par5+1, par6+0.5);

								NBTTagCompound nbt = par1ItemStack.stackTagCompound;

								pet.setName(par1ItemStack.stackTagCompound.getString("name"));
								pet.setSkinName(par1ItemStack.stackTagCompound.getString("skin"));
								pet.setGuiId(par1ItemStack.stackTagCompound.getInteger("guiID"));
								pet.setDragonColor(nbt.getFloat("d_c_1"),nbt.getFloat("d_c_2"),nbt.getFloat("d_c_3"));
								pet.setColor(nbt.getFloat("s_c_1"),nbt.getFloat("s_c_2"),nbt.getFloat("s_c_3"));

								if(!par3World.isRemote)
									par3World.spawnEntityInWorld(pet);
							}
						}
					}else{
						if(!par3World.isRemote)
							par2EntityPlayer.addChatMessage("There's no buddy in this statue...");
					}
				}else
					if(!par3World.isRemote)
						par2EntityPlayer.addChatMessage("There's no buddy in this statue...");
			}
		}
		FMLLog.getLogger().info(""+ PetBuddyMain.playersWithPets);

		return true;
	}

	/*this part enables to despawn the buddy even if the player is not pointing at a block*/
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		if (par2World!= null && par2World.isRemote && FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			EntityLiving el= Targetting.isTargetingLivingEntity(4.0D); 

			if (el != null ) {
				int id =  BuddyUtils.EntityToID(el.getClass());
				if(id != -5 && par1ItemStack.stackTagCompound.getInteger("guiID") != id){

					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					DataOutputStream dos = new DataOutputStream(bos);
					try {
						dos.writeInt(256);
						dos.writeInt(id);
					} catch (Throwable ex) {
					}
					Packet250CustomPayload pcp = new Packet250CustomPayload("buddyPet", bos.toByteArray());
					PacketDispatcher.sendPacketToServer(pcp);
				}
			}
		}



		if(!par2World.isRemote){	

			if(PetBuddyMain.playersWithPets.containsKey(par3EntityPlayer.username))
			{
				EntityBuddy oldPet = (EntityBuddy) par2World.getEntityByID(PetBuddyMain.playersWithPets.get(par3EntityPlayer.username));
				if(oldPet != null && par1ItemStack.stackTagCompound != null)
				{
					par1ItemStack.stackTagCompound.setInteger("guiID", oldPet.getGuiId());

					par1ItemStack.stackTagCompound.setString("name", oldPet.getName());

					par1ItemStack.stackTagCompound.setString("skin", oldPet.getSkinName());

					par1ItemStack.stackTagCompound.setFloat("d_c_1", oldPet.getDragonColor());
					par1ItemStack.stackTagCompound.setFloat("d_c_2", oldPet.getDragonColor2());
					par1ItemStack.stackTagCompound.setFloat("d_c_3", oldPet.getDragonColor3());

					par1ItemStack.stackTagCompound.setFloat("s_c_1", oldPet.getColor());
					par1ItemStack.stackTagCompound.setFloat("s_c_2", oldPet.getColor2());
					par1ItemStack.stackTagCompound.setFloat("s_c_3", oldPet.getColor3());

				}
				PetBuddyMain.playersWithPets.remove(par3EntityPlayer.username);
			}
		}
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}


	@Override
	public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {
		NBTTagCompound tags = stack.getTagCompound();

		if (tags != null) 
		{
			if (tags.hasKey("guiID")) 
			{
				list.add(StatCollector.translateToLocal("Type : "+BuddyUtils.IDToName(tags.getInteger("guiID"))));
				if(tags.getInteger("guiID") == 3){
					if (tags.hasKey("skin"))
					{
						list.add(StatCollector.translateToLocal("Skin : "+ String.valueOf(tags.getString("skin"))));
					}
				}
			}
			if (tags.hasKey("name")) 
			{
				list.add(StatCollector.translateToLocal("Name : " + String.valueOf(tags.getString("name"))));
			}
			if(tags.hasKey("guiID")){
				if(tags.getInteger("guiID") == 19){
					if (tags.hasKey("d_c_1")&&tags.hasKey("d_c_2")&&tags.hasKey("d_c_3"))
					{
						list.add(StatCollector.translateToLocal(" RGB : "+ new DecimalFormat("##.##").format(tags.getFloat("d_c_1"))+","+
								new DecimalFormat("##.##").format(tags.getFloat("d_c_2"))+","+
								new DecimalFormat("##.##").format(tags.getFloat("d_c_3"))));				
					}
				}if(tags.getInteger("guiID") == 14){
					if (tags.hasKey("s_c_1")&&tags.hasKey("s_c_2")&&tags.hasKey("s_c_3"))
					{
						list.add(StatCollector.translateToLocal(" RGB : "+ new DecimalFormat("##.##").format(tags.getFloat("s_c_1"))+"/"+
								new DecimalFormat("##.##").format(tags.getFloat("s_c_2"))+"/"+
								new DecimalFormat("##.##").format(tags.getFloat("s_c_3"))));	
					}
				}
			}
		}
	}

	//	public void sendPacket(int id, int secondID, String petName){
	//		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	//		ObjectOutput out;
	//		DataOutputStream outputStream = new DataOutputStream(bytes);
	//		try {
	//			outputStream.writeInt(id);
	//			outputStream.writeInt(secondID);
	//			outputStream.writeUTF(petName);
	//			Packet250CustomPayload packet = new Packet250CustomPayload("buddyPet", bytes.toByteArray());
	//			PacketDispatcher.sendPacketToServer(packet);
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//	}
}
