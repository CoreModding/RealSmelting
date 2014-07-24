/**
 * Copyright 2014 CoreModding under LGPLv3 or later
 * Copyright 2014 professorvennie under LGPLv3 or later
 *
 * This file is part of Real Smelting.
 *
 * Real Smelting is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Real Smelting is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Real Smelting.  If not, see <http://www.gnu.org/licenses/>.
 */
package info.coremodding.realsmelting;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import info.coremodding.realsmelting.achievements.RSAchievements;
import info.coremodding.realsmelting.blocks.RSBlocks;
import info.coremodding.realsmelting.events.RSEvents;
import info.coremodding.realsmelting.gui.GuiHandler;
import info.coremodding.realsmelting.items.RSItems;
import info.coremodding.realsmelting.lib.ConfigHandler;
import info.coremodding.realsmelting.lib.Names;
import info.coremodding.realsmelting.lib.Strings;
import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
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


@Mod(modid = Strings.MODID, name = Strings.name , version = Strings.version)
public class RealSmelting{
	
	@Instance(Strings.MODID)
	public static RealSmelting instance;
	
	public static CreativeTabs tabRealSmelting = new CreaticeTabRealSmelting(Names.CREATIVETAB);
	@SidedProxy(clientSide = "info.coremodding.realsmelting.ClientProxey", serverSide = "info.coremodding.realsmelting.ServerProxey")
	public static ServerProxey proxey;
	
	@EventHandler
	public static void PreLoad(FMLPreInitializationEvent event){
		RSBlocks.mainRegistry();
		RSItems.mainRegistry();
		ConfigHandler.init(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public static void load(FMLInitializationEvent event){
		NetworkRegistry.INSTANCE.registerGuiHandler(Strings.MODID, new GuiHandler());
		RSAchievements.registerAchievements();
		RSEvents.registerEvents();
		modrecipes.registerRecipes();

        proxey.registerRenderThings();
	}
	
	@EventHandler
	public static void PostLoad(FMLPostInitializationEvent PostEvent){
		GameRegistry.registerTileEntity(TileEntityMagmaFrunace.class, Names.TILE_MAGMA_FURNACE);
		GameRegistry.registerTileEntity(TileEntityLavaFurnace.class, Names.TILE_LAVA_FURNACE);
	}
    
    @EventHandler
    public void init(FMLInitializationEvent e){
        
    }
}
