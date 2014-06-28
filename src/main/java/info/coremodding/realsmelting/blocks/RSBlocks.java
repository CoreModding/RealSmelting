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
package info.coremodding.realsmelting.blocks;

import info.coremodding.realsmelting.items.itemblocks.ItemBlockMagmaFurnace;
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
		GameRegistry.registerBlock(MagmaFrunace, ItemBlockMagmaFurnace.class , Names.MAGMA_FURNACE);
		GameRegistry.registerBlock(LavaFurnaceIdle, Names.LAVA_FURNACE_IDLE);
		GameRegistry.registerBlock(LavaFurnaceActive, Names.LAVA_FURNACE_ACTIVE);
	}
	
	

}
