package info.coremodding.realsmelting.blocks;

import info.coremodding.realsmelting.itemblocks.ItemBlockMagmaFurnace;
import info.coremodding.realsmelting.lib.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class RSBlocks {

	public static void mainRegistry() {
		InitialiseBlock();
		registerBlock();
	}
	
	public static Block MagmaFrunace;
	public static Block LavaFurnaceIdle;
	public static Block LavaFurnaceActive;

	public static void InitialiseBlock() {
		MagmaFrunace = new MagmaFrunace();
		LavaFurnaceIdle = new LavaFurnace(false);
		LavaFurnaceActive = new LavaFurnace(true);
	}
	
	public static void registerBlock() {
		GameRegistry.registerBlock(MagmaFrunace, ItemBlockMagmaFurnace.class ,  Names.MAGMA_FURNACE);
		GameRegistry.registerBlock(LavaFurnaceIdle, Names.LAVA_FURNACE_IDLE);
		GameRegistry.registerBlock(LavaFurnaceActive, Names.LAVA_FURNACE_ACTIVE);
	}
	
	

}
