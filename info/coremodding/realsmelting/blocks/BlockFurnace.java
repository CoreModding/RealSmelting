package info.coremodding.realsmelting.blocks;

import info.coremodding.realsmelting.tileentities.FurnaceEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author James
 * The furnace block
 */
public class BlockFurnace extends BlockContainer {

	protected BlockFurnace() {
		super(Material.rock);
		//this.set
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		//return new FurnaceEntity();
		return null;
	}
	
    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block block){
     	FurnaceEntity tileEntity = (FurnaceEntity)world.getTileEntity(i, j, k);
     	if (tileEntity != null){
         	if(world.getBlock(tileEntity.xCoord, tileEntity.yCoord, 
              	tileEntity.zCoord) instanceof BlockFurnace){
         			world.removeTileEntity(i, j, k);
         	}
     	}
    }
}
