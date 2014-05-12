package info.coremodding.realsmelting;

import info.coremodding.realsmelting.blocks.RSBlocks;
import info.coremodding.realsmelting.lib.ConfigHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class modrecipes {
	
	public static void registerRecipes(){
		if(ConfigHandler.LavaFurnaceCraft)
			GameRegistry.addRecipe(new ItemStack(RSBlocks.LavaFurnaceIdle), "   ", " x ", "   ", 'x', new ItemStack(Items.stick));
		GameRegistry.addRecipe(new ItemStack(RSBlocks.MagmaFrunace), "   ", " x ", "   ", 'x', new ItemStack(Items.wooden_pickaxe));
	}

}
