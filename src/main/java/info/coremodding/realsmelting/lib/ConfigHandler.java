package info.coremodding.realsmelting.lib;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	public static boolean LavaFurnaceCraft;

	
	public static void init(File configFile){
		Configuration config = new Configuration(configFile);
		config.load();
			LavaFurnaceCraft = config.get(Configuration.CATEGORY_GENERAL, "Lava furnace craftable", true).getBoolean(true);
		config.save();
	}

}
