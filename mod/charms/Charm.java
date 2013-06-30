package charms;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class Charm extends Item{

	public final int heartsToHeal;
	private final int charmColor;
	public int cooldown;
	private final int cooldownMAX;

	private final int SLOT_ID;
	private final int TIER;
	public Charm(int par1, int heal, int color, int tier) {
		super(par1);
		this.setMaxStackSize(1);
		heartsToHeal = heal;
		cooldown = 30*20; //seconds * ticks >> 20 second coolDown.
		setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxDamage(heal);
		charmColor = color;
		SLOT_ID = ConfigClass.instance.slotID;
		TIER = tier;
		cooldownMAX= cooldown;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon(texture);
	}

	public void cooldown(){
		if(cooldown >= 0 && cooldown < 30*20){
			cooldown++;
		}
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity ent, int par4, boolean par5){
		cooldown();
		if(ent instanceof EntityPlayer){

			EntityPlayer p = (EntityPlayer)ent;

			if(p.inventory.mainInventory[SLOT_ID]!= null && p.inventory.mainInventory[SLOT_ID].equals(is)){

				int hearts = p.getHealth();
				int maxHearts = p.getMaxHealth();
				int healthDif = maxHearts-hearts;

				if(hearts <= maxHearts/2){
					if((heartsToHeal - is.getItemDamage())<= 0){
						// re-damage the item to make sure.
						is.damageItem(1,p);
						//Do the break item stuff
						p.renderBrokenItemStack(is);
						//delete the item
						p.inventory.setInventorySlotContents(SLOT_ID, (ItemStack) null);
					}else{
						if(cooldown == 30*20){
							//if the charm has less hearts to heal then the player has actual health 
							//> case can be for noHero's more health mod
							//example for 30 hearts and iron charm. 
							if(healthDif > heartsToHeal){ //30-15 > 10
								p.heal(heartsToHeal);     //heal 10
								is.damageItem(heartsToHeal, p); //damage item 10 > meaning it would deplete it immediatly
								p.renderBrokenItemStack(is);    //rendering.
								p.inventory.setInventorySlotContents(SLOT_ID, (ItemStack) null); //making sure it's gone.
							}
							//if the charm has less uses left then the player needs to heal hearts.
							//example> fall down and take 8 hearts of damage with golden charm that has 5 charges left
							else if(healthDif > (heartsToHeal - is.getItemDamage())){ //8 > 5
								p.heal((heartsToHeal - is.getItemDamage())); 		  //heal 5
								is.damageItem((heartsToHeal - is.getItemDamage()), p); //damage item 5
								//again, this would mean it breaks immediatly. making sure it does.
								p.renderBrokenItemStack(is);    //rendering.
								p.inventory.setInventorySlotContents(SLOT_ID, (ItemStack) null); //making sure it's gone.
							}
							//in any other cases, use the health difference to heal.
							else{
								p.heal(healthDif);
								is.damageItem(healthDif, p);
								cooldown = 0;
							}
						}
					}
				}	
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer p1, List list, boolean yesno) {

		String[] charm = {"Luck","Faith","Protection","Wisdom","Prosperity"};
		
		list.add(StatCollector.translateToLocal("Heal : " + heartsToHeal/ (ConfigClass.instance.halfHearts? 1 : 2)));
		list.add(StatCollector.translateToLocal("Heals : " + (heartsToHeal-stack.getItemDamage())/(ConfigClass.instance.halfHearts? 1 : 2)));
		list.add(StatCollector.translateToLocal("CoolDown : "+(cooldown/20)+" s"));
		list.add(StatCollector.translateToLocal("Equip in slot "+SLOT_ID+" !"));
		list.add(StatCollector.translateToLocal("-"+charm[TIER-1]+"-"));

	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		return charmColor;
	}

	@SideOnly(Side.CLIENT)	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		return par1ItemStack.getMaxDamage() >= 50*2;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return par1ItemStack.getMaxDamage() == 70*2 ? EnumRarity.rare :
			par1ItemStack.getMaxDamage() == 100*2? EnumRarity.epic :EnumRarity.common;
	}
}
