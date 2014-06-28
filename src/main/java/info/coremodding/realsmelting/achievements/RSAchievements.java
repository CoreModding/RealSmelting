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
package info.coremodding.realsmelting.achievements;

import info.coremodding.realsmelting.blocks.RSBlocks;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.AchievementPage;

public class RSAchievements {
	
	public static Achievement achievementLavaFurnace;
	public static Achievement achievementMagmaFurnace;
	
	public static void registerAchievements(){
		achievementLavaFurnace = new Achievement("achievement.lavafurnace", "lavaFurnace", 0, 0, RSBlocks.LavaFurnaceActive, AchievementList.buildFurnace).registerStat();
		achievementMagmaFurnace  = new Achievement("achievement.magmafurnace", "magmaFurnace", 2, 1, RSBlocks.MagmaFrunace, RSAchievements.achievementLavaFurnace).setSpecial().registerStat();
		
		
		AchievementPage.registerAchievementPage(new AchievementPage(StatCollector.translateToLocal("achievements.page"), new Achievement[]{achievementLavaFurnace, achievementMagmaFurnace}));
	}

}
