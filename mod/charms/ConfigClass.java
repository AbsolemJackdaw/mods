package charms;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigClass {

    public static ConfigClass instance = new ConfigClass();
    final int iID_OFFSET = 845;

    public int I;
    public int II;
    public int III;
    public int IV;
    public int V;
    public boolean leftyFlip;


    private ConfigClass() {}

    public void loadConfig(File file) {
        Configuration config = new Configuration(file);
        config.load();
        loadSettings(config);
        config.save();
    }
    private void loadSettings(Configuration config){

        int itemnum = iID_OFFSET;
        I = config.getItem("tier I Item Id", itemnum).getInt(itemnum);
        itemnum++;
        II = config.getItem("tier II Item Id", itemnum).getInt(itemnum);
        itemnum++;
        III = config.getItem("tier III Item Id", itemnum).getInt(itemnum);
        itemnum++;
        IV = config.getItem("tier IV Item Id", itemnum).getInt(itemnum);
        itemnum++;
        V = config.getItem("tier V Item Id", itemnum).getInt(itemnum);
        itemnum++;
        
        config.addCustomCategoryComment("HUD Flip", "true for charm HUD lefty flip.");

        leftyFlip = config.get("HUD Flip", "leftyFlip", false).getBoolean(false);

    }
}
