package gravestone;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigClass {

    public static ConfigClass instance = new ConfigClass();


    public int grave;
    public int bones;
    public int graveBlock;
    public int bonesBlock;

    public boolean onDeathEvent = true;
    
    private ConfigClass() {}

    public void loadConfig(File file) {
        Configuration config = new Configuration(file);
        config.load();
        loadSettings(config);
        config.save();
    }
    private void loadSettings(Configuration config){

        grave = config.getItem("Grave Item Id", 5000).getInt(5000);
        bones = config.getItem("Bones Item Id", 5001).getInt(5001);
        
        graveBlock = config.getBlock("Grave Block Id", 500).getInt(500);
        bonesBlock = config.getBlock("Bones Block Id", 501).getInt(501);
        
        config.addCustomCategoryComment("Grave", "Wether to spawn a grave on Death.");
        onDeathEvent = config.get("Grave", "Spawn Grave on Death", true).getBoolean(true);
    }
}
