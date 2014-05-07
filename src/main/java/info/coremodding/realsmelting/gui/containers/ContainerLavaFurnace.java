package info.coremodding.realsmelting.gui.containers;

import info.coremodding.realsmelting.tileentities.TileEntityLavaFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerLavaFurnace extends Container{
	
	private TileEntityLavaFurnace lava;

	public ContainerLavaFurnace(InventoryPlayer inventory, TileEntityLavaFurnace entity) {
		this.lava = entity;
		
		this.addSlotToContainer(new Slot(entity, 0, 56, 36));
		this.addSlotToContainer(new SlotFurnace(inventory.player, entity, 1, 116, 35));
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i, 8 + i*18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return this.lava.isUseableByPlayer(var1);
	}

}
