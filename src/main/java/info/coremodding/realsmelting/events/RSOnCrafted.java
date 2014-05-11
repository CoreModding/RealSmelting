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
