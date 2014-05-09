package info.coremodding.realsmelting.blocks;

import java.util.List;

import info.coremodding.realsmelting.RealSmelting;
import info.coremodding.realsmelting.tileentities.TileEntityLavaFurnace;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LavaFurnace extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;

	private static boolean keepInventory;
	
	public static boolean isActive;

	public LavaFurnace(boolean isActive) {
		super(Material.rock);
		this.isActive = isActive;
		this.setHardness(2.5F);
		if(isActive){
			this.setBlockName("LavaFurnaceActive");
			this.setLightLevel(0.9F);
		}
		if(!isActive){
			this.setBlockName("LavaFurnaceIdle");
			this.setCreativeTab(RealSmelting.tabRealSmelting);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List info, boolean par4){
		 info.add("Strounger then gold");
		 info.add("but still enchatable");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityLavaFurnace();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, RealSmelting.instance, RealSmelting.LavaFurnaceGUI, world, x, y, z);
		}
		return true;
	}
	
	public static void updateSaltFurnaceBlockState(boolean active,
			World worldObj, int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;

		if (active) {
			worldObj.setBlock(xCoord, yCoord, zCoord, RSBlocks.LavaFurnaceActive);
		} else {
			worldObj.setBlock(xCoord, yCoord, zCoord, RSBlocks.LavaFurnaceIdle);
		}

		keepInventory = false;

		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

		if (tileentity != null) {
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}

}
