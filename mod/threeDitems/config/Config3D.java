package threeDitems.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class Config3D {

    public static Config3D instance = new Config3D();
    public boolean SwordModel = true;


    private Config3D() {}

    public void loadConfig(File file) {
        Configuration config = new Configuration(file);
        config.load();
        loadSettings(config);
        config.save();
    }
    private void loadSettings(Configuration config){
//        config.addCustomCategoryComment("Sword Model", "True for Smooth Model, false for Plain Model.");
//
//        SwordModel = config.get("Sword Model", "Smooth Sword", true).getBoolean(true);
    }
}
