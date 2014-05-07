package info.coremodding.realsmelting.gui;

import info.coremodding.realsmelting.RealSmelting;
import info.coremodding.realsmelting.gui.containers.ContainerLavaFurnace;
import info.coremodding.realsmelting.tileentities.TileEntityLavaFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler  implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity= world.getTileEntity(x, y, z);
		
		switch(ID){
		case RealSmelting.LavaFurnaceGUI:
			if(entity instanceof TileEntityLavaFurnace){
				return new ContainerLavaFurnace(player.inventory, (TileEntityLavaFurnace) entity);	
			}
		
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity= world.getTileEntity(x, y, z);
		
		switch(ID){
		case RealSmelting.LavaFurnaceGUI:
			if(entity instanceof TileEntityLavaFurnace){
				return new GuiLavaFurnace(player.inventory, (TileEntityLavaFurnace) entity);
				}
		
		}
		
		return null;
	}

}
