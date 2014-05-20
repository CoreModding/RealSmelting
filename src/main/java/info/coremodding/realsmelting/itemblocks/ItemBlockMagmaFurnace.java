package info.coremodding.realsmelting.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMagmaFurnace extends ItemBlock{

	public ItemBlockMagmaFurnace(Block block) {
		super(block);
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par1){
		list.add("3x3x3 MultiBlock");
	}

}
