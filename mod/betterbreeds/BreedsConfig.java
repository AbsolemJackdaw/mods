package betterbreeds;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class BreedsConfig {


	public static BreedsConfig instance = new BreedsConfig();

	final int iID_OFFSET = 12265;

	public int milk;
	public int sheep;
	public int sheepraw;
	public int meatpie;
	public int dough;
	public int Cegg;
	public int tag;
	public int pieC;
	public int bread;
	public int breadC;
	public int breadB;
	public int breadS;
	public int xmas;
	public int bear;
	public int horseraw;
	public int horse;
	public int lasagna;


	private BreedsConfig() {}

	public void loadConfig(File file) {
		Configuration config = new Configuration(file);
		config.load();
		loadItems(config);
		config.save();
	}

	private void loadItems(Configuration config){

		int itemnum = iID_OFFSET;
		meatpie = config.getItem("Meatpie", itemnum).getInt(itemnum);
		itemnum++;
		pieC = config.getItem("Chocolat Pie", itemnum).getInt(itemnum);
		itemnum++;
		bread = config.getItem("SweatBread", itemnum).getInt(itemnum);
		itemnum++;
		breadC = config.getItem("ChocoBread", itemnum).getInt(itemnum);
		itemnum++;
		breadB = config.getItem("MeatBread", itemnum).getInt(itemnum);
		itemnum++;
		breadS = config.getItem("EggBread", itemnum).getInt(itemnum);
		itemnum++;
		sheep = config.getItem("Sheep", itemnum).getInt(itemnum);
		itemnum++;
		sheepraw = config.getItem("Sheepraw", itemnum).getInt(itemnum);
		itemnum++;
		dough = config.getItem("Pastry Dough", itemnum).getInt(itemnum);
		itemnum++;
		Cegg = config.getItem("Chocolat Egg", itemnum).getInt(itemnum);
		itemnum++;
		milk = config.getItem("Milk", itemnum).getInt(itemnum);
		itemnum++;
		xmas = config.getItem("Christma Items", itemnum).getInt(itemnum);
		itemnum++;
		bear = config.getItem("Jelly Bear", itemnum).getInt(itemnum);
		itemnum++;
		lasagna = config.getItem("Lasagna", itemnum).getInt(itemnum);
		itemnum++;
		horseraw = config.getItem("Raw Horse Meat", itemnum).getInt(itemnum);
		itemnum++;
		horse = config.getItem("Horse Meat", itemnum).getInt(itemnum);
		itemnum++;		
		tag = config.getItem("DogTag", itemnum).getInt(itemnum);
		itemnum++;
	}

}
