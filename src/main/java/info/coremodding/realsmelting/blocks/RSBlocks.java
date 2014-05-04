package info.coremodding.realsmelting.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class RSBlocks {

	public static void mainRegistry() {
		InitialiseBlock();
		registerBlock();
	}
	
	public static Block BlockFrunace;

	

	public static void InitialiseBlock() {
		BlockFrunace = new BlockFurnace().setBlockName("BlockFurnace");
	}
	
	public static void registerBlock() {	
		GameRegistry.registerBlock(BlockFrunace, BlockFrunace.getUnlocalizedName());
	}
	
	

}
