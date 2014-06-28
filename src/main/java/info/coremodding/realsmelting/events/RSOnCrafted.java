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
package info.coremodding.realsmelting.events;

import info.coremodding.realsmelting.achievements.RSAchievements;
import info.coremodding.realsmelting.blocks.RSBlocks;
import net.minecraft.block.Block;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class RSOnCrafted {

	@SubscribeEvent
	public void whenCrafted(PlayerEvent.ItemCraftedEvent e){
		if(Block.getBlockFromItem(e.crafting.getItem()).equals(RSBlocks.LavaFurnaceIdle))
			e.player.addStat(RSAchievements.achievementLavaFurnace, 1);
		if(Block.getBlockFromItem(e.crafting.getItem()).equals(RSBlocks.MagmaFrunace))
			e.player.addStat(RSAchievements.achievementMagmaFurnace, 1);
	}
}
