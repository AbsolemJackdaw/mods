package petBuddy.root;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import petBuddy.PetBuddyMain;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BuddyBase extends EntityTameable
{
	/**values copied from the enderdragon for the dragon*/
	public int ringBufferIndex = -1;
	public float prevAnimTime = -0.0F;
	public float animTime = -0.0F;
	public double[][] ringBuffer = new double[64][3];

	/**values copied from slimes*/
	public float field_70813_a;
	public float field_70811_b;
	public float field_70812_c;
	private int slimeJumpDelay = 0;

	protected boolean hasItem = false;

	public boolean toggled = false;

	public final int NAME = 21;
	public final int SKIN = 22;
	public final int GUIID = 23;

	/**used as defaults for datawatchers*/
	String name = "MyPetBuddy";
	String skin = "-none-";

	private float randomColor;
	private float randomColor2;
	private float randomColor3;

	public float getColor(){
		return randomColor ;
	}public float getColor2(){
		return randomColor2 ;
	}public float getColor3(){
		return randomColor3;
	}

	public void setColor(float f, float g, float h){
		randomColor  = f;
		randomColor2 = g;
		randomColor3 = h;
	}

	private float randomDragonColor;
	private float randomDragonColor2;
	private float randomDragonColor3;

	public float getDragonColor(){
		return randomDragonColor ;
	}public float getDragonColor2(){
		return randomDragonColor2 ;
	}public float getDragonColor3(){
		return randomDragonColor3;
	}

	public void setDragonColor(float f, float g, float h){
		randomDragonColor  = f;
		randomDragonColor2 = g;
		randomDragonColor3 = h;
	}

	//	private int guiID;
	public BuddyBase(World par1World)
	{
		super(par1World);
		this.setSize(0.2F, 0.8F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5d, 10.0F, 2.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, Entity.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));

		/**Later maybe*/
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.slimeJumpDelay = this.rand.nextInt(20) + 10;
		setEntityHealth(666);

	}
	public BuddyBase(World par1World, EntityPlayer player)
	{
		this(par1World);
		//		this.setSize(0.8F, 0.8F);
		//		this.getNavigator().setAvoidsWater(true);
		//		this.tasks.addTask(1, new EntityAISwimming(this));
		//		this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5d, 10.0F, 2.0F));
		//		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		//		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.setOwner(player.username);
		//		this.slimeJumpDelay = this.rand.nextInt(20) + 10;
		//		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		//		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		//		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		//		setEntityHealth(666);
		PetBuddyMain.playersWithPets.put(this.getOwnerName(), entityId);

	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.dataWatcher.addObject(NAME, getOwnerName() + "'s Buddy");
		this.dataWatcher.addObject(SKIN, "-none-");
		this.dataWatcher.addObject(GUIID, (int) 3);
	}

	public String getName(){
		return dataWatcher.getWatchableObjectString(NAME);
	}
	public void setName(String n){
		dataWatcher.updateObject(NAME, n);
	}


	public String getSkinName(){
		return dataWatcher.getWatchableObjectString(SKIN);
	}
	
	public void setSkinName(String n){
		dataWatcher.updateObject(SKIN, n);
	}

	public int getGuiId(){
		return dataWatcher.getWatchableObjectInt(GUIID);
	}
	public void setGuiId(int guiId){
		dataWatcher.updateObject(GUIID, guiId);
	}
	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) {
		return false;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	/**retrieve the buddy's model based on the integer passed down by the buttons of the Gui.*/
	@SideOnly(Side.CLIENT)
	public abstract ModelBase getModel();

	//	@Override
	public abstract ResourceLocation getTexture();

	/**retrieve the buddy's mounted offset based on/switched trough the integer passed down by the buttons of the Gui.*/
	public abstract float getMountedOffset();

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
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
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.getOwner() == null) {
			this.setDead();
			return;
		}
		if (this.dimension != getOwner().dimension) {
			this.setDead();
			return;
		}
		if(getOwner().isDead){
			this.setDead();
			return;
		}

		if(getGuiId() == 19){
			float f;
			float f1;

			f = MathHelper.cos(this.animTime * (float)Math.PI * 2.0F);
			f1 = MathHelper.cos(this.prevAnimTime * (float)Math.PI * 2.0F);

			if (f1 <= -0.3F && f >= -0.3F)
			{
				this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
			}


			this.prevAnimTime = this.animTime;
			float f2;


			f = 0.2F / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F);
			f *= (float)Math.pow(2.0D, this.motionY);

			this.animTime += f/5;

			this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);

			if (this.ringBufferIndex < 0)
			{
				for (int i = 0; i < this.ringBuffer.length; ++i)
				{
					this.ringBuffer[i][0] = (double)this.rotationYaw;
					this.ringBuffer[i][1] = this.posY;
				}
			}

			if (++this.ringBufferIndex == this.ringBuffer.length)
			{
				this.ringBufferIndex = 0;
			}

			this.ringBuffer[this.ringBufferIndex][0] = (double)this.rotationYaw;
			this.ringBuffer[this.ringBufferIndex][1] = this.posY;
			double d0;
			double d1;
			double d2;
			double d3;
			float f3;

			this.renderYawOffset = this.rotationYaw;
		}
	}

	@Override
	public void onUpdate(){
		super.onUpdate();
		if (ridingEntity != null) {
			//stops up-and-down head movement
			rotationPitch = 0;
			//Control where the pet is facing (doesn't work while standing still)
			rotationYaw = prevRotationYaw = ridingEntity.rotationYaw;
		}

		//method copied from slimes
		if(getGuiId() == 30 || getGuiId() == 31){
			this.field_70811_b += (this.field_70813_a - this.field_70811_b) * 0.5F;
			this.field_70812_c = this.field_70811_b;
			boolean flag = this.onGround;
			super.onUpdate();
			int i;

			if (this.onGround && !flag)
			{
				i = 3;

				for (int j = 0; j < i * 8; ++j)
				{
					float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
					float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
					float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
					float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
					//commented out this line because it spawned only particles on the ground when mounted, and only on the spot where it got mounted
					//					this.worldObj.spawnParticle("slime", this.posX + (double)f2, this.boundingBox.minY, this.posZ + (double)f3, 0.0D, 0.0D, 0.0D);
				}

				this.field_70813_a = -0.5F;
			}

			else if (!this.onGround && flag)
			{
				this.field_70813_a = 1.0F;
			}

			this.func_70808_l();

			if (this.worldObj.isRemote)
			{
				i = 3;
				// had to delete size change for slimes. it glitched the Name offset in the world.
				//				this.setSize(0.6F * (float)i, 0.6F * (float)i);
			}
		}
	}

	//method copied from slimes.
	protected void func_70808_l()
	{
		this.field_70813_a *= 0.6F;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		int i = 1;
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
	}


	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean interact(EntityPlayer player)
	{
		if(getOwner() instanceof EntityPlayer){
			if(((EntityPlayer)getOwner()).isSneaking() && ((EntityPlayer)getOwner()).inventory.getCurrentItem().getItem().equals(Item.stick)){
				if(toggled)
					toggled = false;
				else 
					toggled = true;
			}
		}

		/**==Changing buddy appearance now gets handled with the shrine==**/
//		if(player.inventory.getCurrentItem() != null && !player.capabilities.isCreativeMode && hasItem == false && toggled){
//			ItemStack is = player.inventory.getCurrentItem();
//			Item item = is.getItem();
//
//			//Sending a packet for changing buddy apearence does not use strings. they are here mainly used to find back 
//			// the proper entity without any hassle.
//			if(item.equals(Item.porkRaw)){
//				sendPacket(2, 2, "pig");
//				setGuiId(2);player.addStat(PetBuddyMain.pa.pigAchieve, 1);
//			}
//			if(item.equals(Item.bread)){
//				sendPacket(3, 3, "tiny you");
//				setGuiId(3);player.addStat(PetBuddyMain.pa.tinyYou, 1);
//			}
//			if(item.equals(Item.gunpowder)){
//				sendPacket(4, 4, "creeper");
//				setGuiId(4);player.addStat(PetBuddyMain.pa.creeper, 1);
//			}
//			if(item.equals(Item.beefRaw)){
//				sendPacket(5, 5, "cow");
//				setGuiId(5);player.addStat(PetBuddyMain.pa.cow, 1);
//			}
//			if(item.equals(Item.blazeRod) || item.equals(Item.blazePowder)){
//				setGuiId(6);player.addStat(PetBuddyMain.pa.ghost, 1);
//				sendPacket(6, 6, "blaze");
//			}
//			if(item.equals(Item.spiderEye)){ // fermented eye is for rpg spider // silk for buddy to ride
//				sendPacket(7, 7, "spider");
//				setGuiId(8);player.addStat(PetBuddyMain.pa.spider, 1);
//			}
//			if(item.equals(Item.netherStar)){
//				setGuiId(8);player.addStat(PetBuddyMain.pa.wither, 1);
//				sendPacket(8, 8, "wither boss");
//			}
//			if(item.equals(Item.fermentedSpiderEye)){
//				setGuiId(9);player.addStat(PetBuddyMain.pa.rpgSpider, 1);
//				sendPacket(9, 9, "rpg spider");
//			}
//			if(item.equals(Item.arrow)){
//				sendPacket(10, 10, "skeleton");
//				setGuiId(10);player.addStat(PetBuddyMain.pa.skelet, 1);
//			}
//			if(item.equals(Item.skull) && is.getItemDamage() == 1){
//				setGuiId(11);player.addStat(PetBuddyMain.pa.witherSkelly, 1);
//				sendPacket(11, 11, "skeleton w");
//			}
//			if(item.equals(Item.rottenFlesh)){
//				if(getGuiId() == 3)
//					setGuiId(12);player.addStat(PetBuddyMain.pa.zombie, 1);
//				sendPacket(12, 12, "zombie");
//			}
//			if(item.equals(Item.ghastTear)){
//				setGuiId(13);player.addStat(PetBuddyMain.pa.ghast, 1);
//				sendPacket(13, 13, "ghast");
//			}
//			if(is.itemID == Block.cloth.blockID){
//				sendPacket(14, 14, "sheep");
//				setGuiId(14);player.addStat(PetBuddyMain.pa.sheep, 1);
//			}
//			if(item.equals(Item.enderPearl)){
//				setGuiId(15);player.addStat(PetBuddyMain.pa.endPearl, 1);
//				sendPacket(15, 15, "enderman");
//			}
//			if(item.equals(Item.fishCooked)){
//				sendPacket(16, 16, "silverfish");
//				setGuiId(16);player.addStat(PetBuddyMain.pa.silverfish, 1);
//			}
//			if(item.equals(Item.fishRaw)){
//				sendPacket(23, 23, "cat");
//				setGuiId(23);player.addStat(PetBuddyMain.pa.cat, 1);
//			}
//			if(item.equals(Item.snowball)){
//				sendPacket(17, 17, "snowman");
//				setGuiId(17);player.addStat(PetBuddyMain.pa.snow, 1);
//			}
//			if(is.itemID == Block.blockIron.blockID){
//				sendPacket(18, 18, "iron golem");
//				setGuiId(18);player.addStat(PetBuddyMain.pa.golem, 1);
//			}
//			if(is.itemID == Block.whiteStone.blockID){
//				setGuiId(19);player.addStat(PetBuddyMain.pa.endDragon, 1);
//				sendPacket(19, 19, "dragon");
//			}
//			if(item.equals(Item.netherStalkSeeds)){
//				setGuiId(20);player.addStat(PetBuddyMain.pa.bat, 1);
//				sendPacket(20, 20, "bat");
//			}
//			if(item.equals(Item.chickenRaw)){
//				sendPacket(21, 21, "chicken");
//				setGuiId(21);player.addStat(PetBuddyMain.pa.chicken, 1);
//			}
//			if(is.itemID == Block.mycelium.blockID){
//				sendPacket(22, 22, "mooshroom");
//				setGuiId(22);player.addStat(PetBuddyMain.pa.mooshroom, 1);
//			}
//			if(item.equals(Item.dyePowder) && is.getItemDamage() == 0){
//				if(getGuiId() != 19 && getGuiId() != 14){
//					sendPacket(24, 24, "squid");
//					setGuiId(24);player.addStat(PetBuddyMain.pa.squid, 1);
//				}
//			}
//			if(item instanceof ItemBook){
//				sendPacket(25, 25, "villager");
//				setGuiId(25);player.addStat(PetBuddyMain.pa.villager, 1);
//			}
//			if(item.equals(Item.bone)){
//				sendPacket(26, 26, "wolf");
//				setGuiId(26);player.addStat(PetBuddyMain.pa.wolf, 1);
//			}
//			if(item.equals(Item.netherQuartz)){
//				setGuiId(27);player.addStat(PetBuddyMain.pa.pigZombie, 1);
//				sendPacket(27, 27, "pig zombie");
//			}
//			if(item.equals(Item.beefCooked)){
//				setGuiId(28);player.addStat(PetBuddyMain.pa.rpgBull, 1);
//				sendPacket(28, 28, "rpg bull");
//			}
//			if(item.equals(Item.porkCooked)){
//				setGuiId(29);player.addStat(PetBuddyMain.pa.rpgBoar, 1);
//				sendPacket(29, 29, "rpg boar");
//			}
//			if(item.equals(Item.magmaCream)){
//				setGuiId(30);player.addStat(PetBuddyMain.pa.magma, 1);
//				sendPacket(30, 30, "MagmaCube");
//			}
//			if(item.equals(Item.slimeBall)){
//				sendPacket(31, 31, "Slime");
//				setGuiId(31);player.addStat(PetBuddyMain.pa.slime, 1);
//			}
//			if(item.equals(Item.feather)){ // turn a chicken into a harpy
//				if(getGuiId() == 21){
//					setGuiId(32);player.addStat(PetBuddyMain.pa.harpy, 1);
//					sendPacket(32, 32, "harpy");
//				}
//			}
//			if(item.equals(Item.wheat)){ // turn a chicken into a moa
//				if(getGuiId() == 21){
//					setGuiId(33);player.addStat(PetBuddyMain.pa.moa, 1);
//					sendPacket(33, 33, "moa");
//				}
//			}
//			if(item.equals(Item.pickaxeWood)){
//				setGuiId(34);player.addStat(PetBuddyMain.pa.dwarf, 1);
//				sendPacket(34, 34, "dwarf");
//			}
//			if(item.equals(Item.ingotGold)){
//				setGuiId(35);player.addStat(PetBuddyMain.pa.elf, 1);
//				sendPacket(35, 35, "elf");
//			}
//			if(item.equals(Block.obsidian)){
//				setGuiId(36);player.addStat(PetBuddyMain.pa.orc, 1);
//				sendPacket(36, 36, "orc");
//			}
//		}
		return super.interact(player);
	}

	@Deprecated /**crashes server. mehod is now empty*/
	public void sendPacket(int id, int secondID, String petName){
//		if(!worldObj.isRemote){
//			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//			ObjectOutput out;
//			DataOutputStream outputStream = new DataOutputStream(bytes);
//			try {
//				outputStream.writeInt(id);
//				outputStream.writeInt(secondID);
//				outputStream.writeUTF(petName);
//				Packet250CustomPayload packet = new Packet250CustomPayload("buddyPet", bytes.toByteArray());
//				PacketDispatcher.sendPacketToServer(packet);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	 */
	public BuddyBase spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return null;
	}

	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		return this.spawnBabyAnimal(par1EntityAgeable);
	}

	public double[] getMovementOffsets(int par1, float par2)
	{
		if (this.func_110143_aJ() <= 0)
		{
			par2 = 0.0F;
		}

		par2 = 1.0F - par2;
		int j = this.ringBufferIndex - par1 * 1 & 63;
		int k = this.ringBufferIndex - par1 * 1 - 1 & 63;
		double[] adouble = new double[3];
		double d0 = this.ringBuffer[j][0];
		double d1 = MathHelper.wrapAngleTo180_double(this.ringBuffer[k][0] - d0);
		adouble[0] = d0 + d1 * (double)par2;
		d0 = this.ringBuffer[j][1];
		d1 = this.ringBuffer[k][1] - d0;
		adouble[1] = d0 + d1 * (double)par2;
		adouble[2] = this.ringBuffer[j][2] + (this.ringBuffer[k][2] - this.ringBuffer[j][2]) * (double)par2;
		return adouble;
	}

	protected void updateEntityActionState()
	{
		if(getGuiId() == 31){

			if (this.onGround && this.slimeJumpDelay-- <= 0)
			{
				this.slimeJumpDelay = rand.nextInt(20) + 10;

				this.slimeJumpDelay /= 3;

				this.isJumping = true;

				this.playSound("mob.slime.big", this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);

				this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
				this.moveForward = (float)(1 * 3);
			}
			else
			{
				this.isJumping = false;

				if (this.onGround)
				{
					this.moveStrafing = this.moveForward = 0.0F;
				}
			}
		}
	}
	
	public float getHeight(){
		switch(getGuiId()){
		case 2:
			return 0.3f;
		case 4:
			return 0.1f;
		case 5:
			return 0.3f;
		case 7:
			return 0.4f;
		case 8:
			return -0.3f;
		case 9:
			return 0.4f;
		case 14:
			return 0.3f;
		case 15:
			return -0.2f;
		case 16:
			return 0.7f;
		case 17:
			return 0.2f;
		case 18:
			return -0.2f;
		case 20:
			return 0.3f;
		case 21:
			return 0.5f;
		case 22:
			return 0.3f;
		case 23:
			return 0.7f;
		case 24:
			return 0.3f;
		case 26:
			return 0.4f;
		case 28:
			return 0.3f;
		case 29:
			return 0.3f;
		case 30:
			return 0.2f;
		case 31:
			return 0.2f;
		default:
			return 0f;
		}
	}
}
