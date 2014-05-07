package info.coremodding.realsmelting;

import net.minecraft.creativetab.CreativeTabs;
import info.coremodding.realsmelting.blocks.RSBlocks;
import info.coremodding.realsmelting.gui.GuiHandler;
import info.coremodding.realsmelting.items.RSItems;
import info.coremodding.realsmelting.lib.Strings;
import info.coremodding.realsmelting.tileentities.FurnaceEntity;
import info.coremodding.realsmelting.tileentities.TileEntityLavaFurnace;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author James The real smelting main mod class
 */

@Mod(modid = Strings.MODID, name = Strings.name , version = Strings.version)

public class RealSmelting
{
	
	@Instance(Strings.MODID)
	public static RealSmelting instance;
	
	public static final int FurnaceGUIid = 1;
	public static final int LavaFurnaceGUI = 2;
	
	public static CreativeTabs tabRealSmelting = new CreaticeTabRealSmelting("RealSmelting");
	@SidedProxy(clientSide = "info.coremodding.realsmelting.ClientProxey", serverSide = "info.coremodding.realsmelting.ServerProxey")
	public static ServerProxey proxey;
	
	@EventHandler
	public static void PreLoad(FMLPreInitializationEvent PreEvent){
		RSBlocks.mainRegistry();
		RSItems.mainRegistry();
	}
	
	@EventHandler
	public static void load(FMLInitializationEvent event){
		NetworkRegistry.INSTANCE.registerGuiHandler(Strings.MODID, new GuiHandler());
	}
	
	@EventHandler
	public static void PostLoad(FMLPostInitializationEvent PostEvent){
		GameRegistry.registerTileEntity(FurnaceEntity.class,  "RS_Furnace");
		GameRegistry.registerTileEntity(TileEntityLavaFurnace.class,  "RS_Lava_Furnace");
	}
    
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        
    }
}
