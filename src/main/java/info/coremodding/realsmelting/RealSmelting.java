package info.coremodding.realsmelting;

import info.coremodding.realsmelting.blocks.RSBlocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author James The real smelting main mod class
 */
@Mod(modid = "cm_RealSmelting", name = "Real Smelting", version = "UNSET")
public class RealSmelting
{
	
	@EventHandler
	public static void PreLoad(FMLPreInitializationEvent PreEvent){
		RSBlocks.mainRegistry();
	}
	
	@EventHandler
	public static void load(FMLInitializationEvent event){
		
	}
	
	@EventHandler
	public static void PostLoad(FMLPostInitializationEvent PostEvent){
	
	}
    
    /**
     * @param evt
     *            The event that triggered the method
     */
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        
    }
}
