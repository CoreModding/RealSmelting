package info.coremodding.realsmelting.gui.containers;

import info.coremodding.realsmelting.tileentities.TileEntityLavaFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author ProffessorVennie
 *
 */
public class ContainerLavaFurnace extends Container{
	
	private TileEntityLavaFurnace lava;
	
	public int lastcooktime;
	public float lastTemp;

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
	

	public void addCraftingToCrafters(ICrafting icrafting){
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.lava.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, (int) this.lava.Temp);
	}
	
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i ++){
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			
			if(this.lastcooktime != this.lava.cookTime){
				icrafting.sendProgressBarUpdate(this, 0, this.lava.cookTime);
			}
			
			if(this.lastTemp != this.lava.Temp){
				icrafting.sendProgressBarUpdate(this, 1, (int) this.lava.Temp);
			}
			
		}
		this.lastcooktime = this.lava.cookTime;
		this.lastTemp = this.lava.Temp;
	}
	
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2){
		if(slot == 0) this.lava.cookTime = par2;
		if(slot == 1) this.lava.Temp = par2;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return this.lava.isUseableByPlayer(var1);
	}

}
