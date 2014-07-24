package info.coremodding.realsmelting.blocks;

import info.coremodding.realsmelting.RealSmelting;
import info.coremodding.realsmelting.helpers.MultiBlockHelper;
import info.coremodding.realsmelting.lib.GuiIds;
import info.coremodding.realsmelting.lib.Names;
import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author James The furnace block
 */
public class MagmaFrunace extends BlockContainer{
    
    public MagmaFrunace(){
        super(Material.rock);
        this.setBlockName(Names.MAGMA_FURNACE);
        this.setCreativeTab(RealSmelting.tabRealSmelting);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz) {
        if (!world.isRemote) {
            if (!player.isSneaking()) {
                TileEntityMagmaFrunace tile = (TileEntityMagmaFrunace) world.getTileEntity(x, y, z);
                if (tile != null) {
                    if (tile.hasMaster()) {
                        if (tile.isMaster()) {
                            System.out.println("is Master");
                            player.openGui(RealSmelting.instance, GuiIds.MAGMA_FURNACE_ID, world, x, y, z);
                        }else {
                            System.out.println("not master");
                            player.openGui(RealSmelting.instance, GuiIds.MAGMA_FURNACE_ID, world, tile.getMasterX(), tile.getMasterY(), tile.getMasterZ());
                        }
                            return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2){
        return new TileEntityMagmaFrunace(30, 100);
    }
    
    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if(MultiBlockHelper.isMultiBlockStructure(world, i, j, k) && !MultiBlockHelper.doesHaveTileEntity(world, i, j, k)){
            world.setTileEntity(i, j, k, new TileEntityMagmaFrunace(30, 100));
        } else if(!MultiBlockHelper.isMultiBlockStructure(world, i, j, k)) {
            world.removeTileEntity(i, j, k);
        }
    }
}
