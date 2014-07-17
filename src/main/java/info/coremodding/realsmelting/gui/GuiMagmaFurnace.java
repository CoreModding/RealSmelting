package info.coremodding.realsmelting.gui;

import info.coremodding.realsmelting.gui.containers.ContainerMagmaFurnace;
import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiMagmaFurnace extends GuiContainer{

    private TileEntityMagmaFrunace tileEntity;

    public GuiMagmaFurnace(InventoryPlayer inventory, TileEntityMagmaFrunace entity) {
        super(new ContainerMagmaFurnace(inventory, entity));
        this.tileEntity = entity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

    }
}
