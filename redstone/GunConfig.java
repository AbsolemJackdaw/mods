package redstone;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class GunConfig {


	public static GunConfig instance = new GunConfig();

	final int iID_OFFSET = 7000;

	public int redGun;
	public int blueGun;
	public int greenGun;
	public int endGun;
	public int cannon;
	public int thawer;
	public int components;
	public int gatling;
	public int plasma;

	public boolean setAmmoTab;


	private GunConfig() {}

	public void loadConfig(File file) {
		Configuration config = new Configuration(file);
		config.load();
		loadItems(config);
		config.save();
	}

	private void loadItems(Configuration config){

		config.addCustomCategoryComment("ammo", "Set to false to set all ammo items back to their original tabs.");

		setAmmoTab = config.get("ammo", "Set ammo to custom tab", true).getBoolean(true);

		int itemnum = iID_OFFSET;
		redGun = config.getItem("red Gun", itemnum).getInt(itemnum);
		itemnum++;
		blueGun = config.getItem("blue Gun", itemnum).getInt(itemnum);
		itemnum++;
		greenGun = config.getItem("green Gun", itemnum).getInt(itemnum);
		itemnum++;
		endGun = config.getItem("end Gun", itemnum).getInt(itemnum);
		itemnum++;
		components = config.getItem("components", itemnum).getInt(itemnum);
		itemnum++;
		cannon = config.getItem("cannon", itemnum).getInt(itemnum);
		itemnum++;
		thawer = config.getItem("thawer", itemnum).getInt(itemnum);
		itemnum++;
		gatling = config.getItem("gatling", itemnum).getInt(itemnum);
		itemnum++;
		plasma = config.getItem("halo plasma gun", itemnum).getInt(itemnum);
		itemnum++;
	}

}
