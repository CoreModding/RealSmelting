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

import cpw.mods.fml.client.registry.ClientRegistry;
import info.coremodding.realsmelting.blocks.RSBlocks;
import info.coremodding.realsmelting.renderer.item.ItemRendererMagmaFurnace;
import info.coremodding.realsmelting.renderer.tileentity.TileEntityRendererMagmaFurnace;
import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxey extends ServerProxey{
	
	public void registerRenderThings(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagmaFrunace.class, new TileEntityRendererMagmaFurnace());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RSBlocks.MagmaFrunace), new ItemRendererMagmaFurnace());
	}

}
