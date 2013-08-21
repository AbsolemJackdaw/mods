package berryBushes;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class BerryConfig {

	public static BerryConfig instance = new BerryConfig();

	public int berryI;
	public int berryII;
	public int berryIII;
	public int berryIV;



	private BerryConfig() {}

	public void loadConfig(File file) {
		Configuration config = new Configuration(file);
		config.load();
		loadSettings(config);
		config.save();
	}
	private void loadSettings(Configuration config){

		berryI = config.getItem("berryI", 7542).getInt(7542);
		berryII = config.getItem("berryII", 7543).getInt(7543);
		berryIII = config.getItem("berryIII", 7544).getInt(7544);
		berryIV = config.getItem("berryIV", 7545).getInt(7545);

	}
}
