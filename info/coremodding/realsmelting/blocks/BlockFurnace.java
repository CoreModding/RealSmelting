package info.coremodding.realsmelting.blocks;

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
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return null;
	}
	
    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block block){
            TileEntityGag tileEntity = (TileEntityGag)world.getBlockTileEntity(i, j, k);
            if (tileEntity != null){
                    if(world.getBlockId(tileEntity.primary_x, tileEntity.primary_y, 
                    		tileEntity.primary_z) < 1){
                      	world.removeTileEntity(i, j, k);
                    }
            }
    }
}
