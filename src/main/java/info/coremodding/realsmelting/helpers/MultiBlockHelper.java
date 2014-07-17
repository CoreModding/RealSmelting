package info.coremodding.realsmelting.helpers;

import info.coremodding.realsmelting.blocks.MagmaFrunace;
import net.minecraft.block.BlockFurnace;
import net.minecraft.world.World;

/**
 * @author Kasparas
 * 
 */
public class MultiBlockHelper
{
    /**
     * Checks if the structure is complete
     * 
     * @param world
     *            World
     * @param x
     *            X
     * @param y
     *            Y
     * @param z
     *            Z
     * @return true if the structure is complete
     */
    public static boolean isMultiBlockStructure(World world, int x, int y, int z)
    {
        if (checkNorth(world, x, y, z)) /* North */return true;
        if (checkEast(world, x, y, z)) /* East */return true;
        if (checkSouth(world, x, y, z)) /* South */return true;
        if (checkWest(world, x, y, z)) /* West */return true;
        return false;
    }
    
    /**
     * Checks if the structure has a tile entity
     * 
     * @param world
     *            World
     * @param x
     *            X
     * @param y
     *            Y
     * @param z
     *            Z
     * @return true if it has a tile entity
     */
    public static boolean doesHaveTileEntity(World world, int x, int y, int z)
    {
        if (checkTileEntityNorth(world, x, y, z)) /* North */return true;
        if (checkTileEntityEast(world, x, y, z)) /* East */return true;
        if (checkTileEntitySouth(world, x, y, z)) /* South */return true;
        if (checkTileEntityWest(world, x, y, z)) /* West */return true;
        return false;
    }
    
    private static boolean checkNorth(World world, int x, int y, int z){
        if (world.getBlock(x, y, z -1) instanceof MagmaFrunace){

            if (world.getBlock(x, y + 1, z) instanceof MagmaFrunace){

                if (world.getBlock(x, y + 1, z -1) instanceof MagmaFrunace){

                    if (world.getBlock(x + 1, y, z) instanceof MagmaFrunace){

                        if (world.getBlock(x + 1, y, z -1) instanceof MagmaFrunace){

                            if (world.getBlock(x + 1, y + 1, z) instanceof MagmaFrunace){

                                if (world.getBlock(x + 1, y + 1, z -1) instanceof MagmaFrunace){

                                	return true;
                                	}
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean checkEast(World world, int x, int y, int z)
    {
        if (world.getBlock(x + 1, y + 0, z + 0) instanceof MagmaFrunace)
        {
            if (world.getBlock(x + 0, y + 1, z + 0) instanceof MagmaFrunace)
            {
                if (world.getBlock(x + 1, y + 1, z + 0) instanceof MagmaFrunace)
                {
                    if (world.getBlock(x + 0, y + 0, z + 1) instanceof MagmaFrunace)
                    {
                        if (world.getBlock(x + 1, y + 0, z + 1) instanceof MagmaFrunace)
                        {
                            if (world.getBlock(x + 0, y + 1, z + 1) instanceof MagmaFrunace)
                            {
                                if (world.getBlock(x + 1, y + 1, z + 1) instanceof MagmaFrunace) { return true; }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean checkSouth(World world, int x, int y, int z)
    {
        if (world.getBlock(x + 0, y + 0, z + 1) instanceof MagmaFrunace)
        {
            if (world.getBlock(x + 0, y + 1, z + 0) instanceof MagmaFrunace)
            {
                if (world.getBlock(x + 0, y + 1, z + 1) instanceof MagmaFrunace)
                {
                    if (world.getBlock(x + -1, y + 0, z + 0) instanceof MagmaFrunace)
                    {
                        if (world.getBlock(x + -1, y + 0, z + 1) instanceof MagmaFrunace)
                        {
                            if (world.getBlock(x + -1, y + 1, z + 0) instanceof MagmaFrunace)
                            {
                                if (world.getBlock(x + -1, y + 1, z + 1) instanceof MagmaFrunace) { return true; }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean checkWest(World world, int x, int y, int z)
    {
        if (world.getBlock(x + -1, y + 0, z + 0) instanceof MagmaFrunace)
        {
            if (world.getBlock(x + 0, y + 1, z + 0) instanceof MagmaFrunace)
            {
                if (world.getBlock(x + -1, y + 1, z + 0) instanceof MagmaFrunace)
                {
                    if (world.getBlock(x + 0, y + 0, z + -1) instanceof MagmaFrunace)
                    {
                        if (world.getBlock(x + -1, y + 0, z + -1) instanceof MagmaFrunace)
                        {
                            if (world.getBlock(x + 0, y + 1, z + -1) instanceof MagmaFrunace)
                            {
                                if (world.getBlock(x + -1, y + 1, z + -1) instanceof MagmaFrunace) { return true; }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean checkTileEntityNorth(World world, int x, int y, int z)
    {
        
        if (world.getTileEntity(x + 0, y + 0, z + -1) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 1, z + 0) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 1, z + -1) != null) return true;
        
        if (world.getTileEntity(x + 1, y + 0, z + 0) != null) return true;
        
        if (world.getTileEntity(x + 1, y + 0, z + -1) != null) return true;
        
        if (world.getTileEntity(x + 1, y + 1, z + 0) != null) return true;
        
        if (world.getTileEntity(x + 1, y + 1, z + -1) != null) return true;
        
        return false;
    }
    
    private static boolean checkTileEntityEast(World world, int x, int y, int z)
    {
        
        if (world.getTileEntity(x + 1, y + 0, z + 0) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 1, z + 0) != null) return true;
        
        if (world.getTileEntity(x + 1, y + 1, z + 0) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 0, z + 1) != null) return true;
        
        if (world.getTileEntity(x + 1, y + 0, z + 1) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 1, z + 1) != null) return true;
        
        if (world.getTileEntity(x + 1, y + 1, z + 1) != null) return true;
        
        return false;
    }
    
    private static boolean checkTileEntitySouth(World world, int x, int y, int z)
    {
        
        if (world.getTileEntity(x + 0, y + 0, z + 1) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 1, z + 0) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 1, z + 1) != null) return true;
        
        if (world.getTileEntity(x + -1, y + 0, z + 0) != null) return true;
        
        if (world.getTileEntity(x + -1, y + 0, z + 1) != null) return true;
        
        if (world.getTileEntity(x + -1, y + 1, z + 0) != null) return true;
        
        if (world.getTileEntity(x + -1, y + 1, z + 1) != null) return true;
        
        return false;
    }
    
    private static boolean checkTileEntityWest(World world, int x, int y, int z)
    {
        
        if (world.getTileEntity(x + -1, y + 0, z + 0) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 1, z + 0) != null) return true;
        
        if (world.getTileEntity(x + -1, y + 1, z + 0) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 0, z + -1) != null) return true;
        
        if (world.getTileEntity(x + -1, y + 0, z + -1) != null) return true;
        
        if (world.getTileEntity(x + 0, y + 1, z + -1) != null) return true;
        
        if (world.getTileEntity(x + -1, y + 1, z + -1) != null) return true;
        
        return false;
    }
}
