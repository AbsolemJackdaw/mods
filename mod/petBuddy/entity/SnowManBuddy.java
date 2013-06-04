package petBuddy.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import petBuddy.root.BuddyBase;

public class SnowManBuddy extends BuddyBase
{
	public SnowManBuddy(World par1World)
	{
		super(par1World);		
		this.setMountedYOffset(2.7f);

	}
	public SnowManBuddy(World par1World, EntityPlayer player)
	{
		super(par1World, player);		
		this.setMountedYOffset(2.7f);


	}
	
	@SideOnly(Side.CLIENT)
	public ModelBase getModel(){
		return new ModelSnowMan();
	}

	@Override
	public String getTexture() {
		return "/mob/snowman.png";
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return super.interact(par1EntityPlayer);
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	 */
	public SnowManBuddy spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return null;
	}

	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}
}
