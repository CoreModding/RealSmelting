package info.coremodding.realsmelting.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class RSBlocks {

	public static void mainRegistry() {
		InitialiseBlock();
		registerBlock();
	}
	
	public static Block BlockFrunace;
	public static Block LavaFurnaceIdle;
	public static Block LavaFurnaceActive;

	public static void InitialiseBlock() {
		BlockFrunace = new BlockFurnace().setBlockName("BlockFurnace");
		LavaFurnaceIdle = new LavaFurnace(false);
		LavaFurnaceActive = new LavaFurnace(true);
	}
	
	public static void registerBlock() {
		GameRegistry.registerBlock(BlockFrunace, BlockFrunace.getUnlocalizedName());
		GameRegistry.registerBlock(LavaFurnaceIdle, LavaFurnaceIdle.getUnlocalizedName());
		GameRegistry.registerBlock(LavaFurnaceActive, LavaFurnaceActive.getUnlocalizedName());
	}
	
	

}
