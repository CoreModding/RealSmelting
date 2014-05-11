package info.coremodding.realsmelting.achievements;

import info.coremodding.realsmelting.blocks.RSBlocks;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class RSAchievements {
	
	public static Achievement achievementLavaFurnace;
	public static Achievement achievementMagmaFurnace;
	
	public static void registerAchievements(){
		achievementLavaFurnace = new Achievement("achievement.lavafurnace", "lavaFurnace", 0, 0, RSBlocks.LavaFurnaceActive, AchievementList.buildFurnace).registerStat();
		achievementMagmaFurnace  = new Achievement("achievement.magmafurnace", "magmaFurnace", 2, 1, RSBlocks.MagmaFrunace, RSAchievements.achievementLavaFurnace).setSpecial().registerStat();
		
		
		AchievementPage.registerAchievementPage(new AchievementPage("Real Smelting", new Achievement[]{achievementLavaFurnace, achievementMagmaFurnace}));
	}

}
