package gravestone;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigClass {

    public static ConfigClass instance = new ConfigClass();
    final int iID_OFFSET = 5000;
    final int bID_OFFSET = 500;

    public int grave;
    public int bones;
    public int graveBlock;
    public int bonesBlock;

    private ConfigClass() {}

    public void loadConfig(File file) {
        Configuration config = new Configuration(file);
        config.load();
        loadSettings(config);
        config.save();
    }
    private void loadSettings(Configuration config){

        int itemnum = iID_OFFSET;
        grave = config.getItem("Grave Item Id", itemnum).getInt(itemnum);
        itemnum++;
        bones = config.getItem("Bones Item Id", itemnum).getInt(itemnum);
        itemnum++;
        
        int blocknum = bID_OFFSET;
        graveBlock = config.getBlock("Grave Block Id", blocknum).getInt(blocknum);
        blocknum++;
        bonesBlock = config.getBlock("Bones Block Id", blocknum).getInt(blocknum);
        blocknum++;
    }
}
