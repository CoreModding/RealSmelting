package info.coremodding.realsmelting.gui.containers;

import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerMagmaFurnace extends Container{

    private TileEntityMagmaFrunace tileEntity;

    public ContainerMagmaFurnace(InventoryPlayer inventory, TileEntityMagmaFrunace entity) {
        this.tileEntity = entity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }
}
