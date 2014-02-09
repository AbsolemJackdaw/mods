package Paintings;

import java.lang.reflect.Field;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import Paintings.config.ConfigFile;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "PaintingsMod", name = "Paintings++", version = "1.7.2")


public class Paintings {

	private static final String name = "Paintings++";
	private static final String version = "1.7.2 v1";
	
	public static Paintings instance;
	@SidedProxy(serverSide = "Paintings.CommonProxy", clientSide = "Paintings.ClientProxy")
	public static CommonProxy proxy;

	private static final String CLASS_LOC = "com.mcf.davidee.paintinggui.gui.PaintingButton";
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ConfigFile.instance.loadConfig(event.getSuggestedConfigurationFile());
	}
	@EventHandler
	public void load(FMLInitializationEvent init){
		
		boolean Insane = ConfigFile.instance.Insane;
		boolean sphax = ConfigFile.instance.Sphax;
		boolean tiny = ConfigFile.instance.TinyPics ;
		boolean gib = ConfigFile.instance.Gibea;
		proxy.registerRenderInformation();

		//adds PaintingGui compatibility trough reflection
		if(tiny && !Insane && !sphax && !gib)
		{
			try {
				Class altClass= Class.forName(CLASS_LOC);
				paintingGuiTextureHelper(altClass, "TEXTURE", new ResourceLocation("subaraki:art/tiny.png"));
				paintingGuiHelper(altClass, "KZ_WIDTH", 512);
				paintingGuiHelper(altClass, "KZ_HEIGHT", 512);
			}
			catch(Exception e1){
				FMLLog.getLogger().info("Davidees painting mod not installed or to old/new. Skipping");
			}
		}
		else if(!tiny && Insane && !sphax && !gib)
		{
			try {
				Class altClass= Class.forName(CLASS_LOC);
				paintingGuiTextureHelper(altClass, "TEXTURE", new ResourceLocation("subaraki:art/insane.png"));
				paintingGuiHelper(altClass, "KZ_WIDTH", 512);
				paintingGuiHelper(altClass, "KZ_HEIGHT", 512);
			}
			catch(Exception e1){
				FMLLog.getLogger().info("Davidees painting mod not installed or to old/new. Skipping");
			}
		}else if(!tiny && !Insane && sphax && !gib){
			try {
				Class altClass= Class.forName(CLASS_LOC);
				paintingGuiTextureHelper(altClass, "TEXTURE", new ResourceLocation("subaraki:art/sphax.png"));
				paintingGuiHelper(altClass, "KZ_WIDTH", 256);
				paintingGuiHelper(altClass, "KZ_HEIGHT", 256);
			}
			catch(Exception e1){
				FMLLog.getLogger().info("Davidees painting mod not installed or to old/new. Skipping");
			}
		}else{
			try {
				Class altClass= Class.forName(CLASS_LOC);
				paintingGuiTextureHelper(altClass, "TEXTURE", new ResourceLocation("subaraki:art/gib.png"));
				paintingGuiHelper(altClass, "KZ_WIDTH", 256);
				paintingGuiHelper(altClass, "KZ_HEIGHT", 256);
			}
			catch(Exception e1){
				FMLLog.getLogger().info("Davidees painting mod not installed or to old/new. Skipping");
			}
		}

		int i = 0;
		
		if(!tiny && !Insane && !sphax && gib)
		{
			EnumHelper.addArt("EnumArt_"+i,"ptg001", 16, 16 ,112 , 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg002", 16, 16 ,128 , 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg003", 16, 16 ,144 , 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg004", 16, 16 ,160 , 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg005", 16, 16 ,176 , 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg006", 16, 16, 0 , 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg007", 16, 16, 16, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg008", 16, 16, 32, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg009", 16, 16, 48, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg010", 16, 16, 64, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg011", 16, 16, 80, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg012", 16, 16, 96, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg013", 16, 16 ,112 , 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg014", 16, 16 ,128 , 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg015", 16, 16 ,144 , 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg016", 16, 16 ,160 , 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg017", 16, 16 ,176 , 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg018", 32, 16, 160, 32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg019", 32, 16, 0, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg020", 32, 16, 32, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg021", 32, 16, 64, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg022", 32, 16, 96, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg023", 32, 16, 128, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg024", 32, 16, 160, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg025", 16, 32, 32, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg026", 16, 32, 48, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg027", 16, 32, 64, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg028", 16, 32, 80, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg029", 16, 32, 96, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg030", 16, 32, 112, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg031", 16, 32, 128, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg032", 16, 32, 144, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg033", 16, 32, 160, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg034", 16, 32, 176, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg035", 32, 16, 64, 96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg036", 32, 16, 96, 96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg038", 32, 16, 160, 96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg039", 32, 16, 64, 112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg040", 32, 16, 96, 112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg041", 32, 16, 128, 112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg042", 32, 16, 160, 112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg043", 32, 16 , 0, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg044", 32, 16, 32, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg045", 32, 16, 64, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg046", 32, 16, 96, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg047", 32, 16, 128, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg048", 32, 16, 160, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg049", 32, 16, 192, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg050", 32, 16, 224, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg051", 32, 16, 0, 176); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg052", 32, 16, 32, 176); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg053", 32, 16, 64, 176); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg054", 32, 16, 96, 176); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg055", 32, 16, 128, 176); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg056", 32, 16, 160, 176); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg057", 32, 16, 192, 176); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg058", 32, 16, 224, 176); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg059", 32, 32, 192, 192); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg060", 32, 32, 224, 192); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg061", 32, 32, 192, 224); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg062", 32, 32, 224, 224); i++;
		}

		if(!tiny && !Insane && sphax && !gib)
		{
			EnumHelper.addArt("EnumArt_"+i,"XtraImg001", 48, 16, 112, 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg002", 16, 48, 160, 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg003", 16, 48, 176, 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg004", 32, 16, 0, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg005", 32, 16, 32, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg006", 16, 16, 64, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg007", 16, 16, 80, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg008", 16, 16, 96, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg009", 48, 16, 112, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg010", 64, 16, 0, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg011", 16, 48, 64, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg012", 32, 48, 80, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg013", 16, 32, 32, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg014", 16, 32, 48, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg015", 80, 80, 112, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg016", 48, 32, 64, 96); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg017", 32, 32, 0, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg018", 32, 32, 32, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg019", 32, 32, 64, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg020", 32, 32, 96, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg021", 32, 32, 128, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg022", 64, 48, 192, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg023", 64, 48, 192, 208); i++;
			EnumHelper.addArt("EnumArt_"+i,"XtraImg101", 32, 32, 160, 160); i++;
		}

		if(tiny && !Insane && !sphax && !gib)
		{

			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_8",16,16,112,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_9",16,16,128,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_10",16,16,144,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_11",16,16,160,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_12",16,16,176,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_13",16,16,192,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_14",16,16,208,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_15",16,16,224,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_16",16,16,240,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_17",16,16,256,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_18",16,16,272,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_19",16,16,288,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_20",16,16,304,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_21",16,16,320,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_22",16,16,336,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_23",16,16,352,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_24",16,16,416,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_25",16,16,432,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_26",16,16,448,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_27",16,16,464,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_28",16,16,480,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_29",16,16,496,0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_30",16,16,0,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_31",16,16,16,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_32",16,16,32,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_33",16,16,48,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_34",16,16,64,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_35",16,16,80,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_36",16,16,96,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_37",16,16,112,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_38",16,16,128,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_39",16,16,144,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_40",16,16,160,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_41",16,16,176,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_42",16,16,192,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_43",16,16,208,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_44",16,16,224,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_45",16,16,240,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_46",16,16,256,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_47",16,16,272,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_48",16,16,288,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_49",16,16,304,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_50",16,16,320,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_51",16,16,336,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_52",16,16,352,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_53",16,16,416,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_54",16,16,432,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_55",16,16,448,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_56",16,16,464,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_57",16,16,480,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_58",16,16,496,16); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_59",16,16,128,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_60",16,16,144,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_61",16,16,160,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_62",16,16,176,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_63",16,16,448,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_64",16,16,464,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_65",16,16,480,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_66",16,16,496,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_67",16,16,448,448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_68",16,16,464,448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_69",16,16,480,448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x1_70",16,16,496,448); i++;
//			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_1",32,16,0,32); i++;
//			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_2",32,16,32,32); i++;
//			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_3",32,16,64,32); i++; Duplicates of original paintings
//			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_4",32,16,96,32); i++;
//			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_5",32,16,128,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_6",32,16,160,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_7",32,16,192,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_8",32,16,224,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_9",32,16,256,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_10",32,16,288,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_11",32,16,320,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_12",32,16,352,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_13",32,16,384,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_14",32,16,416,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_15",32,16,448,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_16",32,16,480,32); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_17",32,16,0,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_18",32,16,32,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_19",32,16,64,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_20",32,16,96,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_21",32,16,128,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_22",32,16,160,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_23",32,16,192,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_24",32,16,224,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_25",32,16,256,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_26",32,16,288,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_27",32,16,320,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_28",32,16,352,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_29",32,16,384,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_30",32,16,416,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_31",32,16,448,48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x1_32",32,16,480,48); i++;
//			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_1",16,32,0,64); i++;
//			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_2",16,32,16,64); i++; Duplicates of original
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_3",16,32,32,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_4",16,32,48,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_5",16,32,64,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_6",16,32,80,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_7",16,32,96,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_8",16,32,112,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_9",16,32,128,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_10",16,32,144,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_11",16,32,160,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_12",16,32,176,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_13",16,32,192,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_14",16,32,208,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_15",16,32,224,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_16",16,32,240,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_17",16,32,256,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_18",16,32,272,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_19",16,32,288,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_20",16,32,304,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_21",16,32,320,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_22",16,32,336,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_23",16,32,352,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_24",16,32,368,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_25",16,32,384,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_26",16,32,400,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_27",16,32,416,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_28",16,32,432,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_29",16,32,448,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_30",16,32,464,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_31",16,32,480,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x2_32",16,32,496,64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_1",48,16,0,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_2",48,16,48,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_3",48,16,96,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_4",48,16,144,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_5",48,16,192,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_6",48,16,240,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_7",48,16,288,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_8",48,16,336,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_9",48,16,0,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_10",48,16,48,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_11",48,16,96,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_12",48,16,144,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_13",48,16,192,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_14",48,16,240,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_15",48,16,288,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_16",48,16,336,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_17",48,16,0,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_18",48,16,48,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_19",48,16,96,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_20",48,16,144,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_21",48,16,192,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_22",48,16,240,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_23",48,16,288,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_24",48,16,336,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_25",48,16,0,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_26",48,16,48,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_27",48,16,96,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_28",48,16,144,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_29",48,16,192,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_30",48,16,240,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_31",48,16,288,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x1_32",48,16,336,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x1_1",64,16,384,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x1_2",64,16,448,96); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x1_3",64,16,384,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x1_4",64,16,448,112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x1_5",64,16,384,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x1_6",64,16,448,128); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x1_7",64,16,384,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x1_8",64,16,448,144); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_1",16,48,0,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_2",16,48,16,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_3",16,48,32,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_4",16,48,48,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_5",16,48,64,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_6",16,48,80,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_7",16,48,96,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_8",16,48,112,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_9",16,48,128,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_10",16,48,144,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_11",16,48,160,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_12",16,48,176,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_13",16,48,192,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_14",16,48,208,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_15",16,48,224,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_16",16,48,240,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_17",16,48,256,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_18",16,48,272,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_19",16,48,288,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_20",16,48,304,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_21",16,48,320,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_22",16,48,336,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_23",16,48,352,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_24",16,48,368,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_25",16,48,384,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_26",16,48,400,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_27",16,48,416,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_28",16,48,432,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_29",16,48,448,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_30",16,48,464,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_31",16,48,480,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x3_32",16,48,496,160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_1",32,32,0,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_2",32,32,32,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_3",32,32,64,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_4",32,32,96,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_5",32,32,128,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_6",32,32,160,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_7",32,32,192,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_8",32,32,224,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_9",32,32,256,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_10",32,32,288,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_11",32,32,320,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_12",32,32,352,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_13",32,32,384,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_14",32,32,416,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_15",32,32,0,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_16",32,32,32,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_17",32,32,64,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_18",32,32,96,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_19",32,32,128,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_20",32,32,160,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_21",32,32,192,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_22",32,32,224,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_23",32,32,256,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_24",32,32,288,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_25",32,32,320,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_26",32,32,352,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_27",32,32,384,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_28",32,32,416,240); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_29",32,32,192,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_30",32,32,224,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_31",32,32,256,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_32",32,32,288,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_33",32,32,320,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_34",32,32,352,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_35",32,32,384,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x2_36",32,32,416,432); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x4_1",16,64,448,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x4_2",16,64,464,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x4_3",16,64,480,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_1x4_4",16,64,496,208); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_1",48,32,0,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_2",48,32,48,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_3",48,32,96,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_4",48,32,144,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_5",48,32,192,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_6",48,32,240,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_7",48,32,288,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_8",48,32,336,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_9",48,32,0,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_10",48,32,48,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_11",48,32,96,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_12",48,32,144,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_13",48,32,192,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_14",48,32,240,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_15",48,32,288,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x2_16",48,32,336,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x2_1",64,32,384,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x2_2",64,32,448,272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x2_3",64,32,384,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x2_4",64,32,448,304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_1",32,48,0,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_2",32,48,32,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_3",32,48,64,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_4",32,48,96,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_5",32,48,128,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_6",32,48,160,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_7",32,48,192,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_8",32,48,224,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_9",32,48,256,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_10",32,48,288,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_11",32,48,320,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_12",32,48,352,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_13",32,48,384,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_14",32,48,416,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_15",32,48,448,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x3_16",32,48,480,336); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x4_1",32,64,0,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x4_2",32,64,32,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x4_3",32,64,64,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_2x4_4",32,64,96,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x3_1",48,48,128,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x3_2",48,48,176,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x3_3",48,48,224,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x3_4",48,48,272,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x3_5",48,48,320,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x3_6",48,48,368,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x3_7",48,48,416,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_3x3_8",48,48,464,384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x4_1",64,64,0,448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x4_2",64,64,64,448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x4_3",64,64,128,448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x3_1",64,48,192,464); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x3_2",64,48,256,464); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x3_3",64,48,320,464); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x3_4",64,48,384,464); i++;
			EnumHelper.addArt("EnumArt_"+i,"ptg_4x3_5",64,48,448,464); i++;
		}

		if(!tiny && Insane && !sphax && !gib)
		{
			EnumHelper.addArt("EnumArt_"+i,"ins_001", 16, 16, 48, 256); i++;
			
			EnumHelper.addArt("EnumArt_"+i,"ins_002", 32, 16, 0, 272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_003", 32, 16, 32, 272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_004", 32, 16, 0, 288); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_005", 32, 16, 32, 288); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_006", 32, 16, 64, 288); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_007", 32, 16, 96, 288); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_008", 32, 16, 128, 288); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_009", 16, 32, 64, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_010", 16, 32, 80, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_011", 16, 32, 96, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_012", 16, 32, 0, 320); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_013", 16, 32, 16, 320); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_014", 16, 32, 32, 320); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_015", 16, 32, 48, 320); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_016", 32, 32, 0, 416); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_017", 32, 32, 32, 416); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_018", 32, 32, 64, 416); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_019", 32, 32, 96, 416); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_020", 32, 32, 128, 416); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_021", 32, 32, 160, 416); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_022", 64, 32, 0, 352); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_023", 64, 32, 0, 384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_024", 64, 32, 64, 384); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_025", 64, 32, 128, 384); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_026", 64, 16, 0, 304); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_027", 48, 32, 64, 352); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_028", 48, 16, 0, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_029", 48, 16, 112, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_030", 48, 16, 112, 272); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_031", 48, 16, 368, 32); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_032", 64, 64, 0, 448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_033", 64, 64, 64, 448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_034", 64, 64, 128, 448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_035", 64, 64, 256, 448); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_036", 64, 64, 448, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_037", 64, 64, 256, 192); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_038", 64, 64, 448, 0); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_039", 64, 48, 192, 320); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_040", 64, 48, 192, 368); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_041", 64, 48, 192, 416); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_042", 64, 48, 192, 464); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_043", 64, 96, 448, 320); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_044", 64, 96, 448, 416); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_045", 64, 96, 448, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_045_2", 64, 96, 448, 64); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_046", 80, 80, 112, 304); i++;
			
			EnumHelper.addArt("EnumArt_"+i,"ins_047", 128, 96, 320, 416); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_048", 128, 128, 320, 128); i++;
			
			EnumHelper.addArt("EnumArt_"+i,"ins_049", 48, 48, 320, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_050", 48, 48, 368, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_050_2", 48, 48, 320, 0); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_051", 64, 16, 320, 112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_052", 64, 16, 384, 112); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_053", 64, 16, 320, 368); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_054", 64, 16, 384, 368); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_055", 64, 16, 320, 400); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_056", 64, 16, 384, 400); i++;
			
			EnumHelper.addArt("EnumArt_"+i,"ins_057", 128, 16, 320, 384); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_058", 128, 64, 192, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_059", 128, 64, 320, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_060", 128, 64, 320, 304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_060_2", 128, 64, 192, 0); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_061", 64, 128, 256, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_062", 64, 128, 256, 320); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_063", 32, 48, 416, 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_064", 32, 48, 416, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_065", 32, 48, 80, 304); i++;

			EnumHelper.addArt("EnumArt_"+i,"ins_066", 16, 48, 64, 304); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_067", 16, 48, 160, 256); i++;
			EnumHelper.addArt("EnumArt_"+i,"ins_068", 16, 48, 176, 256); i++;

			//sphax
			EnumHelper.addArt("EnumArt_"+i,"insXtra_001", 48, 16, 112, 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_002", 16, 48, 160, 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_003", 16, 48, 176, 0); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_004", 32, 16, 0, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_005", 32, 16, 32, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_006", 16, 16, 64, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_007", 16, 16, 80, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_008", 16, 16, 96, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_009", 48, 16, 112, 16); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_010", 64, 16, 0, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_011", 16, 48, 64, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_012", 32, 48, 80, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_013", 16, 32, 32, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_014", 16, 32, 48, 64); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_015", 80, 80, 112, 48); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_016", 48, 32, 64, 96); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_017", 32, 32, 0, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_018", 32, 32, 32, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_019", 32, 32, 64, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_020", 32, 32, 96, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_021", 32, 32, 128, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_022", 64, 48, 192, 160); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_023", 64, 48, 192, 208); i++;
			EnumHelper.addArt("EnumArt_"+i,"insXtra_101", 32, 32, 160, 160); i++;
		}
	}

	private void paintingGuiHelper(Class c, String field, int value) throws Exception{
		Field f = c.getField(field);
		f.setAccessible(true);
		f.set(null, value);
	}
	
	private void paintingGuiTextureHelper(Class c, String field, ResourceLocation loc) throws Exception{
		Field f = c.getField(field);
		f.setAccessible(true);
		f.set(null, loc);
	}
}

