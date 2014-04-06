package telepads.util;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import telepads.block.TETelepad;

public class TelepadWorldData extends WorldSavedData{

	private static final String IDENTIFIER = "telepadsData";

	private static ArrayList<int[]> allCoords = new ArrayList<int[]>();

	private static ArrayList<String> allNames = new ArrayList<String>();

	private static ArrayList<String> allChannels = new ArrayList<String>();

	private static ArrayList<Integer> allDims = new ArrayList<Integer>();

	public TelepadWorldData() {
		super(IDENTIFIER);
	}

	public TelepadWorldData (String s){
		super(s);
	}

	@Override
	public void readFromNBT(NBTTagCompound var1) {

		int c = var1.getInteger("arraySize");

		allCoords = new ArrayList<int[]>();
		allNames = new ArrayList<String>();
		allChannels = new ArrayList<String>();
		allDims = new ArrayList<Integer>();

		for (int i = 0; i < c ; ++i){
			this.allCoords.add(var1.getIntArray("Coords_"+i));
			this.allNames.add(var1.getString("PadName_"+i));
			this.allDims.add(var1.getInteger("Dimension_"+i));
			this.allChannels.add(var1.getString("Channel_"+i));
		}
		trim();

		System.out.println("READ FROM SAVE");
	}

	@Override
	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("arraySize", allCoords.size());

		trim();

		for (int i = 0; i < this.allCoords.size(); ++i)
			var1.setIntArray("Coords_"+i, allCoords.get(i));

		for (int i = 0; i < this.allNames.size(); ++i)
			var1.setString("PadName_"+i, allNames.get(i));

		for(int i = 0; i < allDims.size(); i++)
			var1.setInteger("Dimension_"+i, allDims.get(i));

		for(int i = 0; i < allChannels.size(); i++)
			var1.setString("Channel_"+i, allChannels.get(i));

		System.out.println("WRITTEN TO SAVE");

	}

	public ArrayList<int[]> getCoordsForChannel(String channel){

		ArrayList<int[]> a = new ArrayList<int[]>();

		for(int i =0; i < allChannels.size(); i++){
			if(allChannels.get(i).equals(channel)){
				a.add(allCoords.get(i));
				System.out.println(
						allCoords.get(i)[0]+ " "+
								allCoords.get(i)[1] + " "+
								allCoords.get(i)[2] 
						);
			}
		}
		return a;
	}

	public /*static*/ void addPad(TETelepad pad){

		int[] a = new int[3];
		a[0] = pad.xCoord;
		a[1] = pad.yCoord;
		a[2] = pad.zCoord;

		allCoords.add(a);

		String s = pad.telepadname;
		allNames.add(s);

		String chan = pad.TELEPORTCHANNEL;
		allChannels.add(chan);

		int d = pad.dimension;
		allDims.add(d);

		trim();
		print();
	}

	public /*static*/ void removePad(TETelepad pad){
		int[] a = new int[3];
		a[0] = pad.xCoord;
		a[1] = pad.yCoord;
		a[2] = pad.zCoord;

		for(int i = 0; i < allCoords.size(); i++){
			if(allCoords.get(i)[0] == a[0] && allCoords.get(i)[1] == a[1] && allCoords.get(i)[2] == a[2]){
				allCoords.remove(i);

				if(allNames.get(i) != null)
					allNames.remove(i);

				if(allChannels.get(i) != null)
					allChannels.remove(i);

				if(allDims.get(i) != null)
					allDims.remove(i);

				break;
			}
		}
		trim();
	}


	private /*static*/ void trim(){
		allCoords.trimToSize();
		allNames.trimToSize();
		allDims.trimToSize();
		allChannels.trimToSize();
		markDirty();
	}

	private /*static*/ int getAnIndex(int[] a){

		for(int i = 0; i < allCoords.size(); i++){
			if(allCoords.get(i)[0] == a[0] && allCoords.get(i)[1] == a[1] && allCoords.get(i)[2] == a[2]){
				return i;
			}
		}

		return -1;
	}

	public /*static*/ String getPadName(int[] is) {
		int i = getAnIndex(is);		
		if(i >= 0){
			if(allNames.get(i) != null){
				return allNames.get(i);
			}
		}
		return null;
	}

	public void print(){
		System.out.println(allCoords + "\n " + allNames + "\n " + allChannels + "\n"+allDims);
	}

	public /*static*/ ArrayList<int[]> getCoords(){
		return allCoords;
	}

	public String getChannelName(int[] is){
		int i = getAnIndex(is);		
		if(i >= 0){
			if(allChannels.get(i) != null){
				return allChannels.get(i);
			}
		}
		return "";
	}

	public /*static*/ int getDimension(int[] is) {
		int i = getAnIndex(is);		
		if(i >= 0){
			if(allDims.get(i) != null){
				return allDims.get(i);
			}
		}
		return 0;
	}

	public static TelepadWorldData get(World world) {
		MapStorage storage = world.perWorldStorage;

		TelepadWorldData data = (TelepadWorldData)world.loadItemData(TelepadWorldData.class, IDENTIFIER);

		if (data == null) {
			data = new TelepadWorldData();
			world.setItemData(IDENTIFIER, data);
		}
		return data;
	}


	public void reset(){
		allDims = new ArrayList<Integer>();
		allChannels = new ArrayList<String>();
		allNames = new ArrayList<String>();
		allCoords = new ArrayList<int[]>();
		trim();
	}
}
