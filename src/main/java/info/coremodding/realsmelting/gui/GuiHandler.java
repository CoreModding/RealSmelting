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
package info.coremodding.realsmelting.gui;

import info.coremodding.realsmelting.gui.containers.ContainerLavaFurnace;
import info.coremodding.realsmelting.gui.containers.ContainerMagmaFurnace;
import info.coremodding.realsmelting.lib.GuiIds;
import info.coremodding.realsmelting.tileentities.TileEntityLavaFurnace;
import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler  implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity= world.getTileEntity(x, y, z);
		
		switch(ID){
		case GuiIds.LAVA_FURNACE_ID:
			if(entity instanceof TileEntityLavaFurnace){
				return new ContainerLavaFurnace(player.inventory, (TileEntityLavaFurnace) entity);	
			}

            case GuiIds.MAGMA_FURNACE_ID:
                if(entity instanceof TileEntityMagmaFrunace){
                    return new ContainerMagmaFurnace(player.inventory, (TileEntityMagmaFrunace) entity);
                }
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity= world.getTileEntity(x, y, z);
		
		switch(ID){
		case GuiIds.LAVA_FURNACE_ID:
			if(entity instanceof TileEntityLavaFurnace){
				return new GuiLavaFurnace(player.inventory, (TileEntityLavaFurnace) entity);
		    }


        case GuiIds.MAGMA_FURNACE_ID:
            if(entity instanceof TileEntityMagmaFrunace){
                 return new GuiMagmaFurnace(player.inventory, (TileEntityMagmaFrunace) entity);
             }
		
		}
		
		return null;
	}

}
