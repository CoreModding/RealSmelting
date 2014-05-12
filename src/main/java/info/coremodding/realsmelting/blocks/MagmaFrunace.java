package info.coremodding.realsmelting.blocks;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import info.coremodding.realsmelting.RealSmelting;
import info.coremodding.realsmelting.helpers.MultiBlockHelper;
import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author James The furnace block
 */
class MagmaFrunace extends BlockContainer
{
    
    protected MagmaFrunace()
    {
        super(Material.rock);
        this.setBlockName("MagmaFrunace");
        this.setCreativeTab(RealSmelting.tabRealSmelting);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz) {
    	if(!world.isRemote){
    		if(!player.isSneaking()){
    			if(MultiBlockHelper.isMultiBlockStructure(world, x, y, z)){
    				FMLNetworkHandler.openGui(player, RealSmelting.instance, RealSmelting.FurnaceGUIid, world, x, y, z);
    			}
    		}
    	}
    	return true;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2){
        return new TileEntityMagmaFrunace(20);
    }
    
    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if(MultiBlockHelper.isMultiBlockStructure(world, i, j, k) && !MultiBlockHelper.doesHaveTileEntity(world, i, j, k)){
            world.setTileEntity(i, j, k, new TileEntityMagmaFrunace(9, 100));
        } else if(!MultiBlockHelper.isMultiBlockStructure(world, i, j, k)) {
            world.removeTileEntity(i, j, k);
        }
    }
}