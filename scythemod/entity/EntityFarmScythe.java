package scythemod.entity;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFarmScythe extends EntityThrowable
{
	Minecraft mc = Minecraft.getMinecraft();
	public EntityFarmScythe(World par1World)
	{
		super(par1World);
	}

	public EntityFarmScythe(World par1World, EntityLiving par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	public EntityFarmScythe(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
	{
		int var7 = this.worldObj.getBlockId(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ);
		int var3 = this.worldObj.getBlockMetadata(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ);
		if (!this.worldObj.isRemote)
		{
			if (var7 == Block.crops.blockID && var3 == 7)
			{
				this.worldObj.setBlockMetadataWithNotify(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, 0,0);
				this.mc.sndManager.playSound("random.stone", (float)this.posX + 0.5F, (float)this.posY + 0.5F, (float)this.posZ + 0.5F, 2.0F, 1.0F);
				int var4 = this.rand.nextInt(4);
				if(var4 != 0)
				{
					this.dropItem(Item.wheat.itemID, var4);
					this.dropItem(Item.seeds.itemID, var4);
				}
				if(var4 == 0)
				{
					var4 = 1;
					this.dropItem(Item.wheat.itemID, var4);
					this.dropItem(Item.seeds.itemID, var4);
				}
			}
			if (par1MovingObjectPosition.entityHit instanceof EntityPig)
			{
				byte var2 = 50;
				this.dropItem(Item.porkRaw.itemID, 3);
				par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), var2);
			}

			if (par1MovingObjectPosition.entityHit instanceof EntityCow)
			{
				byte var2 = 50;
				this.dropItem(Item.beefRaw.itemID, 3);
				par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), var2);
			}

			if (par1MovingObjectPosition.entityHit instanceof EntityChicken)
			{
				byte var2 = 50;
				this.dropItem(Item.chickenRaw.itemID, 1);
				this.dropItem(Item.feather.itemID, 3);
				this.dropItem(Item.bone.itemID, 2);
				par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), var2);
			}

			if (par1MovingObjectPosition.entityHit instanceof EntitySheep)
			{
				byte var2 = 50;
				this.dropItem(Block.cloth.blockID, 3);
				par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), var2);
			}


			this.setDead();
		}


	}

	/**
	 * Gets the amount of gravity to apply to the thrown entity with each tick.
	 */
	protected float getGravityVelocity()
	{
		return 0.7F;
	}
}
