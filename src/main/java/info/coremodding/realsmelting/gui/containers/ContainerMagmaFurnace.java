package info.coremodding.realsmelting.gui.containers;

import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerMagmaFurnace extends Container{

    private TileEntityMagmaFrunace tileEntity;

    public ContainerMagmaFurnace(InventoryPlayer inventory, TileEntityMagmaFrunace entity) {
        this.tileEntity = entity;


        //Input
        addSlotToContainer(new Slot(entity, 0, 12, 16));
        addSlotToContainer(new Slot(entity, 1, 12+18, 16));
        addSlotToContainer(new Slot(entity, 2, 12+36, 16));
        addSlotToContainer(new Slot(entity, 3, 12, 16+18));
        addSlotToContainer(new Slot(entity, 4, 12+18, 16+18));
        addSlotToContainer(new Slot(entity, 5, 12+36, 16+18));
        addSlotToContainer(new Slot(entity, 6, 12, 16+36));
        addSlotToContainer(new Slot(entity, 7, 12+18, 16+36));
        addSlotToContainer(new Slot(entity, 8, 12+36, 16+36));

        //Output
        addSlotToContainer(new Slot(entity, 10, 114, 16));
        addSlotToContainer(new Slot(entity, 11, 114+18, 16));
        addSlotToContainer(new Slot(entity, 12, 114+36, 16));
        addSlotToContainer(new Slot(entity, 13, 114, 16+18));
        addSlotToContainer(new Slot(entity, 14, 114+18, 16+18));
        addSlotToContainer(new Slot(entity, 15, 114+36, 16+18));
        addSlotToContainer(new Slot(entity, 16, 114, 16+36));
        addSlotToContainer(new Slot(entity, 17, 114+18, 16+36));
        addSlotToContainer(new Slot(entity, 18, 114+36, 16+36));

        //middle slots
        addSlotToContainer(new Slot(entity, 19, 91, 16));
        addSlotToContainer(new Slot(entity, 20, 91, 52));


        //players inv
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
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }
}
