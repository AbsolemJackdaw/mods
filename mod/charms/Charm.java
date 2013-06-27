package charms;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
	private int cooldown;

	public Charm(int par1, int heal, int color) {
		super(par1);
		heartsToHeal = heal;
		cooldown = 40*20; //seconds * ticks >> 20 second coolDown.
		setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxDamage(heal);
		charmColor = color;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String texture = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon(texture);
	}

	public void cooldown(){
		if(cooldown >= 0 && cooldown < 40*20){
			cooldown++;
		}
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity ent, int par4, boolean par5){
		cooldown();
		if(ent instanceof EntityPlayer){

			EntityPlayer p = (EntityPlayer)ent;

			if(p.inventory.mainInventory[8]!= null && p.inventory.mainInventory[8].equals(is)){

				int hearts = p.getHealth();
				int maxHearts = p.getMaxHealth();
				int healthDif = maxHearts-hearts;
				//FMLLog.getLogger().info(""+hearts + " "+maxHearts + " "+healthDif+ " "+ (heartsToHeal -is.getItemDamage())+ " "+cooldown );

				if(hearts <= maxHearts/2){
					if((heartsToHeal - is.getItemDamage())<= 0){
						FMLLog.getLogger().info("0 condition");
						// re-damage the item to make sure.
						is.damageItem(1,p);
						//Do the break item stuff
						p.renderBrokenItemStack(is);
						//delete the item
						p.inventory.setInventorySlotContents(8, (ItemStack) null);
					}else{
						if(cooldown == 40*20){
							//if the charm has less hearts to heal then the player has actual health 
							//> case can be for noHero's more health mod
							if(healthDif > heartsToHeal){
								//FMLLog.getLogger().info("no hero case.");
								p.heal(heartsToHeal);
								is.damageItem(heartsToHeal, p);
								cooldown = 0;
							}
							//if the charm has less uses left then the player needs to heal hearts.
							else if(healthDif > (heartsToHeal - is.getItemDamage())){
								//FMLLog.getLogger().info("low charm case");
								p.heal((heartsToHeal - is.getItemDamage()));
								is.damageItem((heartsToHeal - is.getItemDamage()), p);
								cooldown = 0;
							}
							//in any other cases, use the health difference to heal.
							else{
								//FMLLog.getLogger().info("regular case.");
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

		list.add(StatCollector.translateToLocal("Heal : " + heartsToHeal));
		list.add(StatCollector.translateToLocal("Heals : " + (heartsToHeal-stack.getItemDamage())));
		list.add(StatCollector.translateToLocal("CoolDown : 20 s"));
		list.add(StatCollector.translateToLocal("Equip in last hotbar slot !"));

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
		return par1ItemStack.getMaxDamage() == 50*2 ? EnumRarity.rare :
			par1ItemStack.getMaxDamage() == 60*2? EnumRarity.epic :EnumRarity.common;
	}
}
