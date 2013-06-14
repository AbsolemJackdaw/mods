package Paintings;

import java.lang.reflect.Field;

import net.minecraftforge.common.EnumHelper;
import Paintings.config.ConfigFile;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

@Mod(modid = "PaintingsMod", name = "Painitngs++", version = "1.5.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"Paintings"}, packetHandler = PHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"Paintings"}, packetHandler = PHandler.class))


public class Paintings {

	public static Paintings instance;
	@SidedProxy(serverSide = "Paintings.CommonProxy", clientSide = "Paintings.ClientProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		ConfigFile.instance.loadConfig(event.getSuggestedConfigurationFile());
	}
	@Init
	public void load(FMLInitializationEvent init){

		boolean Insane = ConfigFile.instance.Insane;
		boolean sphax = ConfigFile.instance.Sphax;
		boolean tiny = ConfigFile.instance.TinyPics ;
		boolean gib = ConfigFile.instance.Gibea;
		proxy.registerRenderInformation();

		if(tiny && !Insane && !sphax && !gib)
		{
			try {
				Class altClass= Class.forName("com.mcf.davidee.paintinggui.GuiPaintingButton");
				paintingGuiHelper(altClass, "KZ_WIDTH", 512);
				paintingGuiHelper(altClass, "KZ_HEIGHT", 512);
			}
			catch(Exception e1){
				FMLLog.getLogger().info("Davidees painting mod not installed. Skipping");
			}
		}
		if(!tiny && Insane && !sphax && !gib)
		{
			try {
				Class altClass= Class.forName("com.mcf.davidee.paintinggui.GuiPaintingButton");
				paintingGuiHelper(altClass, "KZ_WIDTH", 512);
				paintingGuiHelper(altClass, "KZ_HEIGHT", 512);
			}
			catch(Exception e1){
				FMLLog.getLogger().info("Davidees painting mod not installed. Skipping");
			}
		}else{
			try {
				Class altClass= Class.forName("com.mcf.davidee.paintinggui.GuiPaintingButton");
				paintingGuiHelper(altClass, "KZ_WIDTH", 256);
				paintingGuiHelper(altClass, "KZ_HEIGHT", 256);
			}
			catch(Exception e1){
				FMLLog.getLogger().info("Davidees painting mod not installed. Skipping");
			}
		}

		if(!tiny && !Insane && !sphax && gib)
		{
			EnumHelper.addArt("EnumArt","ptg001", 16, 16 ,112 , 0);
			EnumHelper.addArt("EnumArt","ptg002", 16, 16 ,128 , 0);
			EnumHelper.addArt("EnumArt","ptg003", 16, 16 ,144 , 0);
			EnumHelper.addArt("EnumArt","ptg004", 16, 16 ,160 , 0);
			EnumHelper.addArt("EnumArt","ptg005", 16, 16 ,176 , 0);
			EnumHelper.addArt("EnumArt","ptg006", 16, 16, 0 , 16);
			EnumHelper.addArt("EnumArt","ptg007", 16, 16, 16, 16);
			EnumHelper.addArt("EnumArt","ptg008", 16, 16, 32, 16);
			EnumHelper.addArt("EnumArt","ptg009", 16, 16, 48, 16);
			EnumHelper.addArt("EnumArt","ptg010", 16, 16, 64, 16);
			EnumHelper.addArt("EnumArt","ptg011", 16, 16, 80, 16);
			EnumHelper.addArt("EnumArt","ptg012", 16, 16, 96, 16);
			EnumHelper.addArt("EnumArt","ptg013", 16, 16 ,112 , 16);
			EnumHelper.addArt("EnumArt","ptg014", 16, 16 ,128 , 16);
			EnumHelper.addArt("EnumArt","ptg015", 16, 16 ,144 , 16);
			EnumHelper.addArt("EnumArt","ptg016", 16, 16 ,160 , 16);
			EnumHelper.addArt("EnumArt","ptg017", 16, 16 ,176 , 16);
			EnumHelper.addArt("EnumArt","ptg018", 32, 16, 160, 32);
			EnumHelper.addArt("EnumArt","ptg019", 32, 16, 0, 48);
			EnumHelper.addArt("EnumArt","ptg020", 32, 16, 32, 48);
			EnumHelper.addArt("EnumArt","ptg021", 32, 16, 64, 48);
			EnumHelper.addArt("EnumArt","ptg022", 32, 16, 96, 48);
			EnumHelper.addArt("EnumArt","ptg023", 32, 16, 128, 48);
			EnumHelper.addArt("EnumArt","ptg024", 32, 16, 160, 48);
			EnumHelper.addArt("EnumArt","ptg025", 16, 32, 32, 64);
			EnumHelper.addArt("EnumArt","ptg026", 16, 32, 48, 64);
			EnumHelper.addArt("EnumArt","ptg027", 16, 32, 64, 64);
			EnumHelper.addArt("EnumArt","ptg028", 16, 32, 80, 64);
			EnumHelper.addArt("EnumArt","ptg029", 16, 32, 96, 64);
			EnumHelper.addArt("EnumArt","ptg030", 16, 32, 112, 64);
			EnumHelper.addArt("EnumArt","ptg031", 16, 32, 128, 64);
			EnumHelper.addArt("EnumArt","ptg032", 16, 32, 144, 64);
			EnumHelper.addArt("EnumArt","ptg033", 16, 32, 160, 64);
			EnumHelper.addArt("EnumArt","ptg034", 16, 32, 176, 64);
			EnumHelper.addArt("EnumArt","ptg035", 32, 16, 64, 96);
			EnumHelper.addArt("EnumArt","ptg036", 32, 16, 96, 96);
			EnumHelper.addArt("EnumArt","ptg038", 32, 16, 160, 96);
			EnumHelper.addArt("EnumArt","ptg039", 32, 16, 64, 112);
			EnumHelper.addArt("EnumArt","ptg040", 32, 16, 96, 112);
			EnumHelper.addArt("EnumArt","ptg041", 32, 16, 128, 112);
			EnumHelper.addArt("EnumArt","ptg042", 32, 16, 160, 112);
			EnumHelper.addArt("EnumArt","ptg043", 32, 16 , 0, 160);
			EnumHelper.addArt("EnumArt","ptg044", 32, 16, 32, 160);
			EnumHelper.addArt("EnumArt","ptg045", 32, 16, 64, 160);
			EnumHelper.addArt("EnumArt","ptg046", 32, 16, 96, 160);
			EnumHelper.addArt("EnumArt","ptg047", 32, 16, 128, 160);
			EnumHelper.addArt("EnumArt","ptg048", 32, 16, 160, 160);
			EnumHelper.addArt("EnumArt","ptg049", 32, 16, 192, 160);
			EnumHelper.addArt("EnumArt","ptg050", 32, 16, 224, 160);
			EnumHelper.addArt("EnumArt","ptg051", 32, 16, 0, 176);
			EnumHelper.addArt("EnumArt","ptg052", 32, 16, 32, 176);
			EnumHelper.addArt("EnumArt","ptg053", 32, 16, 64, 176);
			EnumHelper.addArt("EnumArt","ptg054", 32, 16, 96, 176);
			EnumHelper.addArt("EnumArt","ptg055", 32, 16, 128, 176);
			EnumHelper.addArt("EnumArt","ptg056", 32, 16, 160, 176);
			EnumHelper.addArt("EnumArt","ptg057", 32, 16, 192, 176);
			EnumHelper.addArt("EnumArt","ptg058", 32, 16, 224, 176);
			EnumHelper.addArt("EnumArt","ptg059", 32, 32, 192, 192);
			EnumHelper.addArt("EnumArt","ptg060", 32, 32, 224, 192);
			EnumHelper.addArt("EnumArt","ptg061", 32, 32, 192, 224);
			EnumHelper.addArt("EnumArt","ptg062", 32, 32, 224, 224);
		}

		if(!tiny && !Insane && sphax && !gib)
		{
			EnumHelper.addArt("EnumArt","XtraImg001", 48, 16, 112, 0);
			EnumHelper.addArt("EnumArt","XtraImg002", 16, 48, 160, 0);
			EnumHelper.addArt("EnumArt","XtraImg003", 16, 48, 176, 0);
			EnumHelper.addArt("EnumArt","XtraImg004", 32, 16, 0, 16);
			EnumHelper.addArt("EnumArt","XtraImg005", 32, 16, 32, 16);
			EnumHelper.addArt("EnumArt","XtraImg006", 16, 16, 64, 16);
			EnumHelper.addArt("EnumArt","XtraImg007", 16, 16, 80, 16);
			EnumHelper.addArt("EnumArt","XtraImg008", 16, 16, 96, 16);
			EnumHelper.addArt("EnumArt","XtraImg009", 48, 16, 112, 16);
			EnumHelper.addArt("EnumArt","XtraImg010", 64, 16, 0, 48);
			EnumHelper.addArt("EnumArt","XtraImg011", 16, 48, 64, 48);
			EnumHelper.addArt("EnumArt","XtraImg012", 32, 48, 80, 48);
			EnumHelper.addArt("EnumArt","XtraImg013", 16, 32, 32, 64);
			EnumHelper.addArt("EnumArt","XtraImg014", 16, 32, 48, 64);
			EnumHelper.addArt("EnumArt","XtraImg015", 80, 80, 112, 48);
			EnumHelper.addArt("EnumArt","XtraImg016", 48, 32, 64, 96);
			EnumHelper.addArt("EnumArt","XtraImg017", 32, 32, 0, 160);
			EnumHelper.addArt("EnumArt","XtraImg018", 32, 32, 32, 160);
			EnumHelper.addArt("EnumArt","XtraImg019", 32, 32, 64, 160);
			EnumHelper.addArt("EnumArt","XtraImg020", 32, 32, 96, 160);
			EnumHelper.addArt("EnumArt","XtraImg021", 32, 32, 128, 160);
			EnumHelper.addArt("EnumArt","XtraImg022", 64, 48, 192, 160);
			EnumHelper.addArt("EnumArt","XtraImg023", 64, 48, 192, 208);
			EnumHelper.addArt("EnumArt","XtraImg101", 32, 32, 160, 160);
		}

		if(tiny && !Insane && !sphax && !gib)
		{
			EnumHelper.addArt("EnumArt","ptg_1x1_1",16,16,0,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_2",16,16,16,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_3",16,16,32,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_4",16,16,48,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_5",16,16,64,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_6",16,16,80,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_7",16,16,96,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_8",16,16,112,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_9",16,16,128,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_10",16,16,144,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_11",16,16,160,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_12",16,16,176,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_13",16,16,192,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_14",16,16,208,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_15",16,16,224,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_16",16,16,240,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_17",16,16,256,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_18",16,16,272,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_19",16,16,288,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_20",16,16,304,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_21",16,16,320,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_22",16,16,336,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_23",16,16,352,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_24",16,16,416,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_25",16,16,432,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_26",16,16,448,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_27",16,16,464,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_28",16,16,480,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_29",16,16,496,0);
			EnumHelper.addArt("EnumArt","ptg_1x1_30",16,16,0,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_31",16,16,16,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_32",16,16,32,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_33",16,16,48,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_34",16,16,64,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_35",16,16,80,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_36",16,16,96,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_37",16,16,112,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_38",16,16,128,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_39",16,16,144,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_40",16,16,160,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_41",16,16,176,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_42",16,16,192,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_43",16,16,208,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_44",16,16,224,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_45",16,16,240,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_46",16,16,256,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_47",16,16,272,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_48",16,16,288,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_49",16,16,304,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_50",16,16,320,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_51",16,16,336,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_52",16,16,352,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_53",16,16,416,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_54",16,16,432,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_55",16,16,448,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_56",16,16,464,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_57",16,16,480,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_58",16,16,496,16);
			EnumHelper.addArt("EnumArt","ptg_1x1_59",16,16,128,432);
			EnumHelper.addArt("EnumArt","ptg_1x1_60",16,16,144,432);
			EnumHelper.addArt("EnumArt","ptg_1x1_61",16,16,160,432);
			EnumHelper.addArt("EnumArt","ptg_1x1_62",16,16,176,432);
			EnumHelper.addArt("EnumArt","ptg_1x1_63",16,16,448,432);
			EnumHelper.addArt("EnumArt","ptg_1x1_64",16,16,464,432);
			EnumHelper.addArt("EnumArt","ptg_1x1_65",16,16,480,432);
			EnumHelper.addArt("EnumArt","ptg_1x1_66",16,16,496,432);
			EnumHelper.addArt("EnumArt","ptg_1x1_67",16,16,448,448);
			EnumHelper.addArt("EnumArt","ptg_1x1_68",16,16,464,448);
			EnumHelper.addArt("EnumArt","ptg_1x1_69",16,16,480,448);
			EnumHelper.addArt("EnumArt","ptg_1x1_70",16,16,496,448);
			EnumHelper.addArt("EnumArt","ptg_2x1_1",32,16,0,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_2",32,16,32,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_3",32,16,64,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_4",32,16,96,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_5",32,16,128,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_6",32,16,160,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_7",32,16,192,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_8",32,16,224,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_9",32,16,256,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_10",32,16,288,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_11",32,16,320,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_12",32,16,352,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_13",32,16,384,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_14",32,16,416,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_15",32,16,448,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_16",32,16,480,32);
			EnumHelper.addArt("EnumArt","ptg_2x1_17",32,16,0,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_18",32,16,32,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_19",32,16,64,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_20",32,16,96,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_21",32,16,128,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_22",32,16,160,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_23",32,16,192,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_24",32,16,224,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_25",32,16,256,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_26",32,16,288,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_27",32,16,320,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_28",32,16,352,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_29",32,16,384,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_30",32,16,416,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_31",32,16,448,48);
			EnumHelper.addArt("EnumArt","ptg_2x1_32",32,16,480,48);
			EnumHelper.addArt("EnumArt","ptg_1x2_1",16,32,0,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_2",16,32,16,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_3",16,32,32,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_4",16,32,48,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_5",16,32,64,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_6",16,32,80,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_7",16,32,96,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_8",16,32,112,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_9",16,32,128,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_10",16,32,144,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_11",16,32,160,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_12",16,32,176,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_13",16,32,192,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_14",16,32,208,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_15",16,32,224,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_16",16,32,240,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_17",16,32,256,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_18",16,32,272,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_19",16,32,288,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_20",16,32,304,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_21",16,32,320,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_22",16,32,336,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_23",16,32,352,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_24",16,32,368,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_25",16,32,384,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_26",16,32,400,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_27",16,32,416,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_28",16,32,432,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_29",16,32,448,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_30",16,32,464,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_31",16,32,480,64);
			EnumHelper.addArt("EnumArt","ptg_1x2_32",16,32,496,64);
			EnumHelper.addArt("EnumArt","ptg_3x1_1",48,16,0,96);
			EnumHelper.addArt("EnumArt","ptg_3x1_2",48,16,48,96);
			EnumHelper.addArt("EnumArt","ptg_3x1_3",48,16,96,96);
			EnumHelper.addArt("EnumArt","ptg_3x1_4",48,16,144,96);
			EnumHelper.addArt("EnumArt","ptg_3x1_5",48,16,192,96);
			EnumHelper.addArt("EnumArt","ptg_3x1_6",48,16,240,96);
			EnumHelper.addArt("EnumArt","ptg_3x1_7",48,16,288,96);
			EnumHelper.addArt("EnumArt","ptg_3x1_8",48,16,336,96);
			EnumHelper.addArt("EnumArt","ptg_3x1_9",48,16,0,112);
			EnumHelper.addArt("EnumArt","ptg_3x1_10",48,16,48,112);
			EnumHelper.addArt("EnumArt","ptg_3x1_11",48,16,96,112);
			EnumHelper.addArt("EnumArt","ptg_3x1_12",48,16,144,112);
			EnumHelper.addArt("EnumArt","ptg_3x1_13",48,16,192,112);
			EnumHelper.addArt("EnumArt","ptg_3x1_14",48,16,240,112);
			EnumHelper.addArt("EnumArt","ptg_3x1_15",48,16,288,112);
			EnumHelper.addArt("EnumArt","ptg_3x1_16",48,16,336,112);
			EnumHelper.addArt("EnumArt","ptg_3x1_17",48,16,0,128);
			EnumHelper.addArt("EnumArt","ptg_3x1_18",48,16,48,128);
			EnumHelper.addArt("EnumArt","ptg_3x1_19",48,16,96,128);
			EnumHelper.addArt("EnumArt","ptg_3x1_20",48,16,144,128);
			EnumHelper.addArt("EnumArt","ptg_3x1_21",48,16,192,128);
			EnumHelper.addArt("EnumArt","ptg_3x1_22",48,16,240,128);
			EnumHelper.addArt("EnumArt","ptg_3x1_23",48,16,288,128);
			EnumHelper.addArt("EnumArt","ptg_3x1_24",48,16,336,128);
			EnumHelper.addArt("EnumArt","ptg_3x1_25",48,16,0,144);
			EnumHelper.addArt("EnumArt","ptg_3x1_26",48,16,48,144);
			EnumHelper.addArt("EnumArt","ptg_3x1_27",48,16,96,144);
			EnumHelper.addArt("EnumArt","ptg_3x1_28",48,16,144,144);
			EnumHelper.addArt("EnumArt","ptg_3x1_29",48,16,192,144);
			EnumHelper.addArt("EnumArt","ptg_3x1_30",48,16,240,144);
			EnumHelper.addArt("EnumArt","ptg_3x1_31",48,16,288,144);
			EnumHelper.addArt("EnumArt","ptg_3x1_32",48,16,336,144);
			EnumHelper.addArt("EnumArt","ptg_4x1_1",64,16,384,96);
			EnumHelper.addArt("EnumArt","ptg_4x1_2",64,16,448,96);
			EnumHelper.addArt("EnumArt","ptg_4x1_3",64,16,384,112);
			EnumHelper.addArt("EnumArt","ptg_4x1_4",64,16,448,112);
			EnumHelper.addArt("EnumArt","ptg_4x1_5",64,16,384,128);
			EnumHelper.addArt("EnumArt","ptg_4x1_6",64,16,448,128);
			EnumHelper.addArt("EnumArt","ptg_4x1_7",64,16,384,144);
			EnumHelper.addArt("EnumArt","ptg_4x1_8",64,16,448,144);
			EnumHelper.addArt("EnumArt","ptg_1x3_1",16,48,0,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_2",16,48,16,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_3",16,48,32,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_4",16,48,48,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_5",16,48,64,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_6",16,48,80,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_7",16,48,96,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_8",16,48,112,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_9",16,48,128,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_10",16,48,144,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_11",16,48,160,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_12",16,48,176,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_13",16,48,192,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_14",16,48,208,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_15",16,48,224,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_16",16,48,240,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_17",16,48,256,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_18",16,48,272,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_19",16,48,288,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_20",16,48,304,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_21",16,48,320,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_22",16,48,336,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_23",16,48,352,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_24",16,48,368,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_25",16,48,384,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_26",16,48,400,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_27",16,48,416,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_28",16,48,432,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_29",16,48,448,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_30",16,48,464,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_31",16,48,480,160);
			EnumHelper.addArt("EnumArt","ptg_1x3_32",16,48,496,160);
			EnumHelper.addArt("EnumArt","ptg_2x2_1",32,32,0,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_2",32,32,32,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_3",32,32,64,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_4",32,32,96,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_5",32,32,128,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_6",32,32,160,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_7",32,32,192,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_8",32,32,224,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_9",32,32,256,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_10",32,32,288,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_11",32,32,320,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_12",32,32,352,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_13",32,32,384,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_14",32,32,416,208);
			EnumHelper.addArt("EnumArt","ptg_2x2_15",32,32,0,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_16",32,32,32,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_17",32,32,64,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_18",32,32,96,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_19",32,32,128,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_20",32,32,160,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_21",32,32,192,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_22",32,32,224,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_23",32,32,256,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_24",32,32,288,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_25",32,32,320,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_26",32,32,352,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_27",32,32,384,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_28",32,32,416,240);
			EnumHelper.addArt("EnumArt","ptg_2x2_29",32,32,192,432);
			EnumHelper.addArt("EnumArt","ptg_2x2_30",32,32,224,432);
			EnumHelper.addArt("EnumArt","ptg_2x2_31",32,32,256,432);
			EnumHelper.addArt("EnumArt","ptg_2x2_32",32,32,288,432);
			EnumHelper.addArt("EnumArt","ptg_2x2_33",32,32,320,432);
			EnumHelper.addArt("EnumArt","ptg_2x2_34",32,32,352,432);
			EnumHelper.addArt("EnumArt","ptg_2x2_35",32,32,384,432);
			EnumHelper.addArt("EnumArt","ptg_2x2_36",32,32,416,432);
			EnumHelper.addArt("EnumArt","ptg_1x4_1",16,64,448,208);
			EnumHelper.addArt("EnumArt","ptg_1x4_2",16,64,464,208);
			EnumHelper.addArt("EnumArt","ptg_1x4_3",16,64,480,208);
			EnumHelper.addArt("EnumArt","ptg_1x4_4",16,64,496,208);
			EnumHelper.addArt("EnumArt","ptg_3x2_1",48,32,0,272);
			EnumHelper.addArt("EnumArt","ptg_3x2_2",48,32,48,272);
			EnumHelper.addArt("EnumArt","ptg_3x2_3",48,32,96,272);
			EnumHelper.addArt("EnumArt","ptg_3x2_4",48,32,144,272);
			EnumHelper.addArt("EnumArt","ptg_3x2_5",48,32,192,272);
			EnumHelper.addArt("EnumArt","ptg_3x2_6",48,32,240,272);
			EnumHelper.addArt("EnumArt","ptg_3x2_7",48,32,288,272);
			EnumHelper.addArt("EnumArt","ptg_3x2_8",48,32,336,272);
			EnumHelper.addArt("EnumArt","ptg_3x2_9",48,32,0,304);
			EnumHelper.addArt("EnumArt","ptg_3x2_10",48,32,48,304);
			EnumHelper.addArt("EnumArt","ptg_3x2_11",48,32,96,304);
			EnumHelper.addArt("EnumArt","ptg_3x2_12",48,32,144,304);
			EnumHelper.addArt("EnumArt","ptg_3x2_13",48,32,192,304);
			EnumHelper.addArt("EnumArt","ptg_3x2_14",48,32,240,304);
			EnumHelper.addArt("EnumArt","ptg_3x2_15",48,32,288,304);
			EnumHelper.addArt("EnumArt","ptg_3x2_16",48,32,336,304);
			EnumHelper.addArt("EnumArt","ptg_4x2_1",64,32,384,272);
			EnumHelper.addArt("EnumArt","ptg_4x2_2",64,32,448,272);
			EnumHelper.addArt("EnumArt","ptg_4x2_3",64,32,384,304);
			EnumHelper.addArt("EnumArt","ptg_4x2_4",64,32,448,304);
			EnumHelper.addArt("EnumArt","ptg_2x3_1",32,48,0,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_2",32,48,32,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_3",32,48,64,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_4",32,48,96,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_5",32,48,128,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_6",32,48,160,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_7",32,48,192,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_8",32,48,224,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_9",32,48,256,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_10",32,48,288,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_11",32,48,320,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_12",32,48,352,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_13",32,48,384,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_14",32,48,416,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_15",32,48,448,336);
			EnumHelper.addArt("EnumArt","ptg_2x3_16",32,48,480,336);
			EnumHelper.addArt("EnumArt","ptg_2x4_1",32,64,0,384);
			EnumHelper.addArt("EnumArt","ptg_2x4_2",32,64,32,384);
			EnumHelper.addArt("EnumArt","ptg_2x4_3",32,64,64,384);
			EnumHelper.addArt("EnumArt","ptg_2x4_4",32,64,96,384);
			EnumHelper.addArt("EnumArt","ptg_3x3_1",48,48,128,384);
			EnumHelper.addArt("EnumArt","ptg_3x3_2",48,48,176,384);
			EnumHelper.addArt("EnumArt","ptg_3x3_3",48,48,224,384);
			EnumHelper.addArt("EnumArt","ptg_3x3_4",48,48,272,384);
			EnumHelper.addArt("EnumArt","ptg_3x3_5",48,48,320,384);
			EnumHelper.addArt("EnumArt","ptg_3x3_6",48,48,368,384);
			EnumHelper.addArt("EnumArt","ptg_3x3_7",48,48,416,384);
			EnumHelper.addArt("EnumArt","ptg_3x3_8",48,48,464,384);
			EnumHelper.addArt("EnumArt","ptg_4x4_1",64,64,0,448);
			EnumHelper.addArt("EnumArt","ptg_4x4_2",64,64,64,448);
			EnumHelper.addArt("EnumArt","ptg_4x4_3",64,64,128,448);
			EnumHelper.addArt("EnumArt","ptg_4x3_1",64,48,192,464);
			EnumHelper.addArt("EnumArt","ptg_4x3_2",64,48,256,464);
			EnumHelper.addArt("EnumArt","ptg_4x3_3",64,48,320,464);
			EnumHelper.addArt("EnumArt","ptg_4x3_4",64,48,384,464);
			EnumHelper.addArt("EnumArt","ptg_4x3_5",64,48,448,464);
		}

		if(!tiny && Insane && !sphax && !gib)
		{
			//eerste helft. rechts van origineel
			EnumHelper.addArt("EnumArt","ins001",192,0,128,64);
			EnumHelper.addArt("EnumArt","ins002",320,0,48,48);
			EnumHelper.addArt("EnumArt","ins003",368,32,48,16);
			EnumHelper.addArt("EnumArt","ins004",416,0,32,48);
			EnumHelper.addArt("EnumArt","ins005",448,0,64,64);
			EnumHelper.addArt("EnumArt","ins006",64,128,256,48);
			EnumHelper.addArt("EnumArt","ins007",128,48,320,48);
			EnumHelper.addArt("EnumArt","ins008",48,16,320,112);
			EnumHelper.addArt("EnumArt","ins000",48,16,384,112);
			EnumHelper.addArt("EnumArt","ins010",48,96,64,448);
			EnumHelper.addArt("EnumArt","ins011",48,96,160,448);
			EnumHelper.addArt("EnumArt","ins012",128,128,320,128);
			EnumHelper.addArt("EnumArt","ins013",64,64,256,192);
			// tweede deel, onder origineel
			EnumHelper.addArt("EnumArt","ins014",48,16,0,256);
			EnumHelper.addArt("EnumArt","ins015",16,16,48,256);
			EnumHelper.addArt("EnumArt","ins016",16,32,64,256);
			EnumHelper.addArt("EnumArt","ins017",16,32,80,256);
			EnumHelper.addArt("EnumArt","ins018",16,32,96,256);
			EnumHelper.addArt("EnumArt","ins019",48,16,112,256);
			EnumHelper.addArt("EnumArt","ins020",48,16,112,272);
			EnumHelper.addArt("EnumArt","ins021",16,48,160,256);
			EnumHelper.addArt("EnumArt","ins022",16,48,172,256);
			EnumHelper.addArt("EnumArt","ins023",32,16,0,172);
			EnumHelper.addArt("EnumArt","ins024",32,16,32,172);
			EnumHelper.addArt("EnumArt","ins025",32,16,0,288);
			EnumHelper.addArt("EnumArt","ins026",32,16,32,288);
			EnumHelper.addArt("EnumArt","ins027",32,16,64,288);
			EnumHelper.addArt("EnumArt","ins028",32,16,96,288);
			EnumHelper.addArt("EnumArt","ins029",32,16,128,288);
			EnumHelper.addArt("EnumArt","ins030",64,16,0,304);
			EnumHelper.addArt("EnumArt","ins031",16,48,64,304);
			EnumHelper.addArt("EnumArt","ins032",32,48,80,304);
			EnumHelper.addArt("EnumArt","ins033",80,80,112,304);
			EnumHelper.addArt("EnumArt","ins034",16,32,0,320);
			EnumHelper.addArt("EnumArt","ins035",16,32,16,320);
			EnumHelper.addArt("EnumArt","ins036",16,32,32,320);
			EnumHelper.addArt("EnumArt","ins037",16,32,48,320);
			EnumHelper.addArt("EnumArt","ins038",16,32,64,320);
			EnumHelper.addArt("EnumArt","ins039",48,32,0,352);
			EnumHelper.addArt("EnumArt","ins040",48,32,64,352);
			EnumHelper.addArt("EnumArt","ins041",64,32,0,384);
			EnumHelper.addArt("EnumArt","ins042",64,32,64,384);
			EnumHelper.addArt("EnumArt","ins043",64,32,128,384);
			EnumHelper.addArt("EnumArt","ins044",32,32,0,412);
			EnumHelper.addArt("EnumArt","ins045",32,32,32,412);
			EnumHelper.addArt("EnumArt","ins046",32,32,64,412);
			EnumHelper.addArt("EnumArt","ins047",32,32,98,412);
			EnumHelper.addArt("EnumArt","ins048",32,32,128,412);
			EnumHelper.addArt("EnumArt","ins049",32,32,160,412);
			EnumHelper.addArt("EnumArt","ins050",64,64,0,448);
			EnumHelper.addArt("EnumArt","ins051",64,64,64,448);
			EnumHelper.addArt("EnumArt","ins052",64,64,128,448);
			EnumHelper.addArt("EnumArt","ins053",64,48,186,320);
			EnumHelper.addArt("EnumArt","ins054",64,48,186,364);
			EnumHelper.addArt("EnumArt","ins055",64,48,186,408);
			EnumHelper.addArt("EnumArt","ins056",64,48,186,462);
			EnumHelper.addArt("EnumArt","ins057",128,64,192,256);
			EnumHelper.addArt("EnumArt","ins058",64,128,256,320);
			EnumHelper.addArt("EnumArt","ins059",64,64,256,448);
			EnumHelper.addArt("EnumArt","ins060",64,64,320,256);
			EnumHelper.addArt("EnumArt","ins061",64,64,368,256);
			EnumHelper.addArt("EnumArt","ins062",32,48,416,256);
			EnumHelper.addArt("EnumArt","ins063",64,64,448,256);
			EnumHelper.addArt("EnumArt","ins064",64,80,448,320);
			EnumHelper.addArt("EnumArt","ins065",64,80,448,412);
			EnumHelper.addArt("EnumArt","ins066",128,64,320,304);
			EnumHelper.addArt("EnumArt","ins067",64,16,320,368);
			EnumHelper.addArt("EnumArt","ins068",128,16,320,384);
			EnumHelper.addArt("EnumArt","ins069",64,16,320,400);
			EnumHelper.addArt("EnumArt","ins070",128,96,320,412);
			EnumHelper.addArt("EnumArt","ins071",64,16,384,368);
			EnumHelper.addArt("EnumArt","ins072",64,16,384,400);
			//sphax
			EnumHelper.addArt("EnumArt","XtraImg001", 48, 16, 112, 0);
			EnumHelper.addArt("EnumArt","XtraImg002", 16, 48, 160, 0);
			EnumHelper.addArt("EnumArt","XtraImg003", 16, 48, 176, 0);
			EnumHelper.addArt("EnumArt","XtraImg004", 32, 16, 0, 16);
			EnumHelper.addArt("EnumArt","XtraImg005", 32, 16, 32, 16);
			EnumHelper.addArt("EnumArt","XtraImg006", 16, 16, 64, 16);
			EnumHelper.addArt("EnumArt","XtraImg007", 16, 16, 80, 16);
			EnumHelper.addArt("EnumArt","XtraImg008", 16, 16, 96, 16);
			EnumHelper.addArt("EnumArt","XtraImg009", 48, 16, 112, 16);
			EnumHelper.addArt("EnumArt","XtraImg010", 64, 16, 0, 48);
			EnumHelper.addArt("EnumArt","XtraImg011", 16, 48, 64, 48);
			EnumHelper.addArt("EnumArt","XtraImg012", 32, 48, 80, 48);
			EnumHelper.addArt("EnumArt","XtraImg013", 16, 32, 32, 64);
			EnumHelper.addArt("EnumArt","XtraImg014", 16, 32, 48, 64);
			EnumHelper.addArt("EnumArt","XtraImg015", 80, 80, 112, 48);
			EnumHelper.addArt("EnumArt","XtraImg016", 48, 32, 64, 96);
			EnumHelper.addArt("EnumArt","XtraImg017", 32, 32, 0, 160);
			EnumHelper.addArt("EnumArt","XtraImg018", 32, 32, 32, 160);
			EnumHelper.addArt("EnumArt","XtraImg019", 32, 32, 64, 160);
			EnumHelper.addArt("EnumArt","XtraImg020", 32, 32, 96, 160);
			EnumHelper.addArt("EnumArt","XtraImg021", 32, 32, 128, 160);
			EnumHelper.addArt("EnumArt","XtraImg022", 64, 48, 192, 160);
			EnumHelper.addArt("EnumArt","XtraImg023", 64, 48, 192, 208);
			EnumHelper.addArt("EnumArt","XtraImg101", 32, 32, 160, 160);
		}
	}

	private void paintingGuiHelper(Class c, String field, int value) throws Exception{
		Field f = c.getField(field);
		f.setAccessible(true);
		f.set(null, value);
	}
}

