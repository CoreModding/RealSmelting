package info.coremodding.realsmelting.blocks;

import info.coremodding.realsmelting.helpers.MultiBlockHelper;
import info.coremodding.realsmelting.tileentities.FurnaceEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author James The furnace block
 */
class BlockFurnace extends BlockContainer
{
    
    protected BlockFurnace()
    {
        super(Material.rock);
        // this.set
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        // return new FurnaceEntity();
        return null;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
    {
        if(MultiBlockHelper.isMultiBlockStructure(world, i, j, k) && !MultiBlockHelper.doesHaveTileEntity(world, i, j, k)){
            world.setTileEntity(i, j, k, new FurnaceEntity(9, 100));
        } else if(!MultiBlockHelper.isMultiBlockStructure(world, i, j, k)) {
            world.removeTileEntity(i, j, k);
        }
    }
}
