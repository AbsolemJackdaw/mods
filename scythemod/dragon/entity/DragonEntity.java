/*
 ** 2012 August 13
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon.entity;


import java.util.logging.Level;
import java.util.logging.Logger;

import scythemod.dragon.FlyingTameableEntity;
import scythemod.dragon.ItemUtils;
import scythemod.dragon.ai.EntityAIBlock;
import scythemod.dragon.ai.EntityAIDragonMate;
import scythemod.dragon.ai.EntityAIFlyToOwner;
import scythemod.dragon.ai.EntityAIFlyToTarget;
import scythemod.dragon.ai.EntityAIFollowConspecific;
import scythemod.dragon.ai.EntityAIFollowOwnerGround;
import scythemod.dragon.ai.EntityAILiftOff;
import scythemod.dragon.ai.EntityAILookIdle2;
import scythemod.dragon.ai.EntityAIRideAir;
import scythemod.dragon.ai.EntityAIRideGround;
import scythemod.dragon.ai.EntityAIRoamAir;
import scythemod.dragon.ai.EntityAITargetAttacker;
import scythemod.dragon.ai.EntityAIWatchLiving;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonEntity extends FlyingTameableEntity {
    
    private static final Logger L = Logger.getLogger(DragonEntity.class.getName());
    
    // data value IDs
    private static final int INDEX_SADDLED = 19;
    private static final int INDEX_HEALTH = 20;
    private static final int INDEX_Zombie = 21;
    // data NBT IDs
    private static final String NBT_SADDLED = "Saddle";
    private static final String NBT_zombie = "Zombie";
    // client-only stuff
    private DragonAnimator animator;
    private int eggWiggleX;
    private int eggWiggleZ;

    public DragonEntity(World world) {
        super(world);
        this.setSize(2.0f, 2.0f);

        // HAAAAAX!
        try {
            // EntityLiving.moveHelper
            ReflectionHelper.setPrivateValue(EntityLiving.class, this, new DragonMoveHelper(this), 55);
            // EntityLiving.bodyHelper
            ReflectionHelper.setPrivateValue(EntityLiving.class, this, new DragonBodyHelper(this), 57);
        } catch (Exception ex) {
            L.log(Level.WARNING, "Can't override AI helper!", ex);
        }
        
        
        
        isImmuneToFire = true;
        stepHeight = 1;
        float moveSpeed = 0.3f;
        moveSpeedAir = 1;
        jumpMovementFactor = 0.5f;//landMovementFactor;
        ignoreFrustumCheck = true;
        
        if (isClient()) {
            animator = new DragonAnimator(this);
        } else {
            // too big!
            getNavigator().setEnterDoors(false);
            this.setEntityHealth(80f);
            // mutex 1: movement
            // mutex 2: looking
            // mutex 4: special state
            tasks.addTask(0, new EntityAIBlock(this)); // mutex all
            tasks.addTask(1, new EntityAIRideGround(this, moveSpeed)); // mutex all
            tasks.addTask(2, aiSit); // mutex 4+1
            tasks.addTask(3, new EntityAIDragonMate(this, moveSpeed * 0.75f)); // mutex 2+1
            tasks.addTask(4, new EntityAITempt(this, moveSpeed * 0.5f, Item.fishRaw.itemID, false)); // mutex 2+1
            tasks.addTask(5, new EntityAILiftOff(this, 0.05f)); // mutex 0
            tasks.addTask(6, new EntityAIAttackOnCollide(this, moveSpeed, true)); // mutex 2+1
            tasks.addTask(7, new EntityAIFollowConspecific(this, moveSpeed * 0.8f)); // mutex 2+1
            tasks.addTask(8, new EntityAIFollowOwnerGround(this, moveSpeed, 12, 48, 256)); // mutex 2+1
            tasks.addTask(10, new EntityAIWander(this, moveSpeed)); // mutex 1
            tasks.addTask(11, new EntityAIWatchLiving(this, 16)); // mutex 2
            tasks.addTask(12, new EntityAILookIdle2(this)); // mutex 2

            // mutex 1: waypointing
            // mutex 2: continuous waypointing
            airTasks.addTask(0, new EntityAIRideAir(this, moveSpeedAir)); // mutex all
            airTasks.addTask(1, new EntityAIRoamAir(this, moveSpeedAir, 100, 22500, 80, 32, 0.12f)); // mutex 1
            airTasks.addTask(2, new EntityAIFlyToTarget(this, moveSpeedAir)); // mutex 2+1
            airTasks.addTask(3, new EntityAIFlyToOwner(this, moveSpeedAir)); // mutex 2+1

            // mutex 1: generic targeting
            targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this)); // mutex 1
            targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this)); // mutex 1
            targetTasks.addTask(3, new EntityAITargetAttacker(this, false)); // mutex 1
            targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySheep.class, 16, false)); // mutex 1
            targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityPig.class, 16, false)); // mutex 1
            targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityChicken.class, 16, false)); // mutex 1
        }
    }
    
   

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(INDEX_SADDLED, (byte) 0);
        dataWatcher.addObject(INDEX_Zombie, (byte)0);
        dataWatcher.addObject(INDEX_HEALTH, 0);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean(NBT_SADDLED, isSaddled());
        nbt.setBoolean(NBT_zombie, isZombie());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        setSaddled(nbt.getBoolean(NBT_SADDLED));
        setZombie (nbt.getBoolean(NBT_zombie));
    }
    public boolean isZombie() {
        return (dataWatcher.getWatchableObjectByte(INDEX_Zombie) & 1) != 0;
    }

    public void setZombie(boolean zombie) {
        dataWatcher.updateObject(INDEX_Zombie, Byte.valueOf(zombie ? (byte) 1 : (byte) 0));

    }
    /**
     * Returns true if the dragon is saddled.
     */
    public boolean isSaddled() {
        return (dataWatcher.getWatchableObjectByte(INDEX_SADDLED) & 1) != 0;
    }

    /**
     * Set or remove the saddle of the dragon.
     */
    public void setSaddled(boolean saddled) {
        dataWatcher.updateObject(INDEX_SADDLED, Byte.valueOf(saddled ? (byte) 1 : (byte) 0));
    }
    
    /**
     * Returns the health points of the dragon (used by clients only).
     */
    public int getDragonHealth() {
        return this.dataWatcher.getWatchableObjectInt(INDEX_HEALTH);
    }
    
    public boolean isPlayerOwner(EntityPlayer player) {
        return player.username.equalsIgnoreCase(getOwnerName());
    }
    
    public boolean isRiddenByOwner() {
        return riddenByEntity == getOwner();
    }

    public EntityPlayer getRider() {
        if (riddenByEntity instanceof EntityPlayer) {
            return (EntityPlayer) riddenByEntity;
        } else {
            return null;
        }
    }
    
    public void setRider(EntityPlayer player) {
        if (riddenByEntity == player) {
            player.mountEntity(null);
            
            // perform soft unmounting by teleporting the player close to the
            // ground in the direction he/she is looking at
            double dist = width;
            Vec3 look = player.getLookVec();
            
            look.xCoord *= dist;
            look.zCoord *= dist;
            
            look.xCoord += player.posX;
            look.yCoord += player.posY - getMountedYOffset();
            look.zCoord += player.posZ;
            
            player.setPositionAndUpdate(look.xCoord, look.yCoord, look.zCoord);
        } else {
            player.mountEntity(this);
        }
    }
    
    public DragonAnimator getAnimator() {
        return animator;
    }
    
    @Override
    public void onLivingUpdate() {
        int age = getGrowingAge();
       
       
        
        if (isClient()) {
             {
                animator.setOnGround(!isFlying());
                animator.update();
            }
        } else {
            // update health for clients
            dataWatcher.updateObject(INDEX_HEALTH, this.func_110143_aJ());
        }
        
        super.onLivingUpdate();
    }
    
    /**
     * Handles entity death timer, experience orb and particle creation
     */
    @Override
    protected void onDeathUpdate() {
        deathTime++;
        
        // unmount any riding entity
        if (riddenByEntity != null) {
            riddenByEntity.mountEntity(null);
        }
                
        // freeze at place
        motionX = motionY = motionZ = 0;
        rotationYaw = prevRotationYaw;
        rotationYawHead = prevRotationYawHead;

        {
            if (isClient()) {
                if (deathTime < getMaxDeathTime() - 20) {
                    spawnBodyParticles("cloud", 4);
                }
            }
            
            if (deathTime == getMaxDeathTime()) {
                setDead();
            }
        }
    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    @Override
    public boolean interact(EntityPlayer player) {
       
        if (isTamed() || isChild()) {
            // heal dragon with food
            ItemFood food = null;
            
            if (getDragonHealth() != this.func_110138_aP()) {
                food = (ItemFood) ItemUtils.consumeEquipped(player, Item.fishRaw,
                        Item.porkRaw, Item.beefRaw, Item.chickenRaw);
            }
            
            if (food != null) {
                heal(food.getHealAmount());
                worldObj.playSoundAtEntity(this, "random.eat", 0.7f, (rand.nextFloat() - rand.nextFloat()) * 0.2f + 0.5f);
                return true;
            }
            
            if (isPlayerOwner(player) && !isChild()) {
                if (ItemUtils.consumeEquipped(player, Item.fishRaw)) {
                    if (isClient()) {
                        spawnBodyParticles("heart");
                    }
                    
                    try {
                        // inLove = 600; (private -_-)
                        ReflectionHelper.setPrivateValue(EntityAnimal.class, this, (Integer) 600, 0);
                    } catch (Exception ex) {
                        L.log(Level.WARNING, null, ex);
                    }

                    entityToAttack = null;
                } else {
                    if (isServer()) {
                        if (!isSaddled() && ItemUtils.consumeEquipped(player, Item.saddle)) {
                            L.log(Level.FINE, "Dragon {0} was saddled by {1}",
                                    new Object[]{entityId, player.username});
                            
                            // put on a saddle
                            setSaddled(true);
                        } else if (riddenByEntity == null && ItemUtils.hasEquipped(player, Item.bone)) {
                            L.log(Level.FINE, "Dragon {0} was told to sit by {1}",
                                    new Object[]{entityId, player.username});

                            // toggle sitting state with the bone item
                            aiSit.setSitting(!isSitting());
                            isJumping = false;
                            setPathToEntity(null);
                        } else if (isSaddled() && !ItemUtils.hasEquippedUsable(player)) {
                            L.log(Level.FINE, "Dragon {0} got a mount request from {1}",
                                    new Object[]{entityId, player.username});

                            // (un-)mount dragon when saddled
                            setRider(player);
                        }
                    }
                }
            }
        } else {
            if (isServer()) {
                if (ItemUtils.consumeEquipped(player, Item.fishRaw)) {
                    // tame dragon with raw fish and a random chance
                    if (rand.nextInt(3) == 0) {
                        setTamed(true);
                        setPathToEntity(null);
                        setAttackTarget(null);
//                        aiSit.setSitting(true);
                        setOwner(player.username);
                        playTameEffect(true);
                        worldObj.setEntityState(this, (byte) 7);
                    } else {
                        playTameEffect(false);
                        worldObj.setEntityState(this, (byte) 6);
                    }
                }
            }
            
            return true;
        }

        return false;
    }
    
    @Override
    protected void dropFewItems(boolean par1, int par2) {
        super.dropFewItems(par1, par2);
        
        // drop saddle if equipped
        if (isSaddled()) {
            dropItem(Item.saddle.itemID, 1);
        }
    }
    
    @Override
    public boolean attackEntityAsMob(Entity victim) {
        worldObj.playSoundAtEntity(this, "random.eat", 0.7f, (rand.nextFloat() - rand.nextFloat()) * 0.2f + 0.5f);
        attackTime = 20;
        this.setRevengeTarget((EntityLivingBase) victim);
        int damage = (int) (8 * getSize());
        return victim.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
    }
    
    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource src, float par2) {
        if (isImmuneToDamage(src)) {
            return false;
        }
        
        // don't just sit there!
        aiSit.setSitting(false);
        
       
            worldObj.playSoundAtEntity(this, "mob.enderdragon.growl", 1, 1);
        
        
        return super.attackEntityFrom(src, par2);
    }
    
    public boolean isImmuneToDamage(DamageSource src) {
        Entity srcEnt = src.getEntity();
        if (srcEnt != null) {
            // ignore own damage
            if (srcEnt == this) {
                return true;
            }
            
            // ignore damage from rider
            if (srcEnt == riddenByEntity) {
                return true;
            }
        }
        
        // ignore suffocation damage
        if (src.damageType.equals("inWall")) {
            return true;
        }
        
      
        
        return false;
    }
    
    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith() {
        // disable projectile collision while the rider is using the bow
        EntityPlayer rider = getRider();
        if (rider != null && ItemUtils.hasEquipped(rider, Item.bow)) {
            return false;
        }

        return super.canBeCollidedWith();
    }
    
    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    @Override
    protected void fall(float par1) {
        // this thing's got wings!
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    protected boolean isAIEnabled() {
        return true;
    }
    
    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    @Override
    public boolean canBePushed() {
        // one does not simply push a dragon!
        return false;
    }
    
    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    @Override
    protected boolean canDespawn() {
        return false;
    }
    
    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    @Override
    public boolean isOnLadder() {
        // this better doesn't happen...
        return false;
    }
    
    public void transformToEgg() {
        if (this.func_110143_aJ() <= 0) {
            // no can do
            return;
        }
        
        worldObj.playSoundAtEntity(this, "mob.enderdragon.transform",
                1, 0.5f + (0.5f - rand.nextFloat()) * 0.1f);
        
        if (isSaddled()) {
            dropItem(Item.saddle.itemID, 1);
        }
        
        dropEgg();
        setDead();
        
        L.log(Level.FINE, "Dragon {0} transformed to egg", entityId);
    }
    
    public void dropEgg() {
        {
            // spawn a falling block entity
            double px = Math.round(posX) - 0.5;
            double py = posY + (height / 2.0);
            double pz = Math.round(posZ) - 0.5;

            EntityFallingSand egg = new EntityFallingSand(worldObj, px, py, pz,
                    Block.dragonEgg.blockID);
            egg.fallTime = 2;
            worldObj.spawnEntityInWorld(egg);
        }
    }
    
    /**
     * Returns the height of the eyes. Used for looking at other entities.
     */
    @Override
    public float getEyeHeight() {
        // the head will be higher the more health the dragon has
        float healthMulti = getDragonHealth() / (float) func_110138_aP();
        // or lower when the dragon is sitting
        float sitMulti = isSitting() ? 0.6f : 1;
        // or when it isn't adult
        float size = getSize();
        return (height * 0.5f + height * healthMulti * 0.75f) * size * sitMulti;
    }
    
    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    @Override
    public boolean canMateWith(EntityAnimal mate) {
       return false;
    }
        
    public String getTexture(int index) {
        switch (index) {
        case 0: return "/DSM/Hdragon.png";
        case 1: return "/DSM/Dsaddle.png";
        case 2: return "/DSM/Hdragon.png";
        case 3: return "/DSM/shuffle.png";
        case 4: return "/DSM/Zdragon.png";
        case 5: return "/DSM/Dsaddle.png"; 
            default: return null;
        }
    }
   
    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    @Override
    public double getMountedYOffset() {
        return isSitting() ? 1.5f : 2f;
    }
    
    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return "";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
       
            return "mob.enderdragon.hit";
        
    }
    
    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
       
            return "mob.enderdragon.death";
        
    }
    
    /**
     * Returns the sound this mob makes when it beats its wings.
     */
    protected String getWingsSound() {
        return inWater ? null : "mob.enderdragon.wings";
    }
    
    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(int x, int y, int z, int blockId) {
       
            worldObj.playSoundAtEntity(this, "mob.enderdragon.walk", 0.5f, 1);
        
    }
    
    public void onWingsDown(float speed) {
        // play wing sounds
        float pitch = 0.8f + (0.5f - rand.nextFloat()) * 0.3f + (1 - speed) * 0.2f;
        pitch *= getSize();
        float volume = 0.3f + (1 - speed) * 0.2f;
        
        worldObj.playSound(posX, posY, posZ, getWingsSound(), volume, pitch,false);
    }
    
    protected void spawnBodyParticle(String effect) {
        double ox, oy, oz;
        float s = getSize() * 1.2f;

        if (effect.equals("explode")) {
            ox = rand.nextGaussian() * s;
            oy = rand.nextGaussian() * s;
            oz = rand.nextGaussian() * s;
        } else if (effect.equals("cloud")) {
            ox = (rand.nextDouble() - 0.5) * 0.1;
            oy = rand.nextDouble() * 0.2;
            oz = (rand.nextDouble() - 0.5) * 0.1;
        } else if (effect.equals("reddust")) {
            ox = 0.8;
            oy = 0;
            oz = 0.8;
        } else {
            ox = 0;
            oy = 0;
            oz = 0;
        }
        
        // use generic random box spawning
        double x = posX + (rand.nextDouble() - 0.5) * width * s;
        double y = posY + (rand.nextDouble() - 0.5) * height * s;
        double z = posZ + (rand.nextDouble() - 0.5) * width * s;

        worldObj.spawnParticle(effect, x, y, z, ox, oy, oz);
    }
    
    protected void spawnBodyParticles(String effect, int baseAmount) {
        int amount = (int) (baseAmount * getSize());
        for (int i = 0; i < amount; i++) {
            spawnBodyParticle(effect);
        }
    }
    
    protected void spawnBodyParticles(String effect) {
        spawnBodyParticles(effect, 32);
    }
    
    /**
     * Spawns an explosion particle around the Entity's location
     */
    @Override
    public void spawnExplosionParticle() {
        spawnBodyParticles("explode");
    }
    
    @Override
    protected void playTameEffect(boolean success) {
        spawnBodyParticles(success ? "heart" : "smoke");
    }
    
    public int getEggWiggleX() {
        return eggWiggleX;
    }

    public int getEggWiggleZ() {
        return eggWiggleZ;
    }
    
    public int getDeathTime() {
        return deathTime;
    }
    
    public int getMaxDeathTime() {
        return 120;
    }
    
//    @Override
//    public int getMaxHealth() {
//        return (int) (80 * getSize());
//    }
//    
    /**
     * Returns the size multiplier for the current age.
     * 
     * @return size
     */
    public float getSize() {
        return 1F;
    }



	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
