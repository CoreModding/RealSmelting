package info.coremodding.realsmelting;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreaticeTabRealSmelting  extends CreativeTabs{

	public CreaticeTabRealSmelting(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(Blocks.furnace);
	}

}
