//package scythemod.block.te;
//
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.network.packet.Packet;
//import net.minecraft.network.packet.Packet132TileEntityData;
//import net.minecraft.tileentity.MobSpawnerBaseLogic;
//import net.minecraft.tileentity.*;
//import net.minecraft.tileentity.
//public class TileEntityAngelSpawning extends TileEntity
//{
//    private final MobSpawnerBaseLogic field_98050_a = new TileEntityMobSpawnerLogic(this);
//
//    /**
//     * Reads a tile entity from NBT.
//     */
//    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.readFromNBT(par1NBTTagCompound);
//        this.field_98050_a.readFromNBT(par1NBTTagCompound);
//    }
//
//    /**
//     * Writes a tile entity to NBT.
//     */
//    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.writeToNBT(par1NBTTagCompound);
//        this.field_98050_a.writeToNBT(par1NBTTagCompound);
//    }
//
//    /**
//     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
//     * ticks and creates a new spawn inside its implementation.
//     */
//    public void updateEntity()
//    {
//        this.field_98050_a.updateSpawner();
//        super.updateEntity();
//    }
//
//    /**
//     * Overriden in a sign to provide the text.
//     */
//    public Packet getDescriptionPacket()
//    {
//        NBTTagCompound nbttagcompound = new NBTTagCompound();
//        this.writeToNBT(nbttagcompound);
//        nbttagcompound.removeTag("SpawnPotentials");
//        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbttagcompound);
//    }
//
//    /**
//     * Called when a client event is received with the event number and argument, see World.sendClientEvent
//     */
//    public boolean receiveClientEvent(int par1, int par2)
//    {
//        return this.field_98050_a.setDelayToMin(par1) ? true : super.receiveClientEvent(par1, par2);
//    }
//
//    public MobSpawnerBaseLogic func_98049_a()
//    {
//        return this.field_98050_a;
//    }
//}
