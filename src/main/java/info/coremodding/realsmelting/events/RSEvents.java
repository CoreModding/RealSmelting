package info.coremodding.realsmelting.events;

import cpw.mods.fml.common.FMLCommonHandler;

public class RSEvents {
	
	public static void registerEvents(){
		FMLCommonHandler.instance().bus().register(new RSOnCrafted());
	}
}
