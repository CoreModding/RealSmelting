package info.coremodding.realsmelting.tileentities;

import info.coremodding.realsmelting.helpers.MultiBlockHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * @author James The furnace tile entity
 */
public class TileEntityMagmaFrunace extends TileEntity implements ISidedInventory, IFluidHandler{
    
    private ItemStack[] que;
    private ItemStack[] smelted;
    private ItemStack[] smelting;
    private ItemStack[] inventory;

    private boolean isMaster, hasMaster;
    private int masterX, masterY, masterZ;

    public boolean isMaster() {
        return isMaster;
    }

    public boolean hasMaster() {
        return hasMaster;
    }

    public int getMasterX() {
        return masterX;
    }

    public int getMasterY() {
        return masterY;
    }

    public int getMasterZ() {
        return masterZ;
    }

    public void reset() {
        masterX = 0;
        masterY = 0;
        masterZ = 0;
        hasMaster = false;
        isMaster = false;
    }

    public void setMaster(int x, int y, int z, boolean isMaster) {
        TileEntity tile = worldObj.getTileEntity(x, y, z);
        if (tile != null && (tile instanceof TileEntityMagmaFrunace)) {
            masterX = x;
            masterY = y;
            masterZ = z;
            hasMaster = true;
            this.isMaster = isMaster;
        }
    }

    public FluidTank tank;
    
    private int[]       smelt_progress;
    private final int   smelt_time;
    
    /**
     * The secondary constructor
     * 
     * @param slots
     *            The amount of slots of input
     * @param ticks
     *            The ticks it takes to cook something
     */
    public TileEntityMagmaFrunace(int slots, int ticks)
    {
        this.que = new ItemStack[slots];
        this.smelted = new ItemStack[slots];
        this.inventory = new ItemStack[slots];
        this.smelting = new ItemStack[1];
        this.smelt_time = ticks;
        tank = new FluidTank(FluidRegistry.LAVA, 0, 10000);
    }
    
    /**
     * The main constructor
     * 
     * @param slots
     *            The slots amount for input and output
     * @param ticks
     *            The amount of ticks to cook something
     * @param at_once
     *            How many things can be cooking at once
     */
    public TileEntityMagmaFrunace(int slots, int ticks, int at_once)
    {
        this.que = new ItemStack[slots];
        this.smelted = new ItemStack[slots];
        this.inventory = new ItemStack[slots];
        this.smelting = new ItemStack[at_once];
        this.smelt_progress = new int[at_once];
        this.smelt_time = ticks;
        tank = new FluidTank(FluidRegistry.LAVA, 0, 10000);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        try
        {
            {
                byte[] data;
                data = nbt.getByteArray("que");
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream oin = new ObjectInputStream(in);
                this.que = (ItemStack[]) oin.readObject();
            }
            {
                byte[] data;
                data = nbt.getByteArray("smelting");
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream oin = new ObjectInputStream(in);
                this.smelting = (ItemStack[]) oin.readObject();
            }
            {
                byte[] data;
                data = nbt.getByteArray("smelted");
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream oin = new ObjectInputStream(in);
                this.smelted = (ItemStack[]) oin.readObject();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateEntity() {
        int i = 0;
        int emptyend = 0;
        for (ItemStack item : this.smelted) {
            if (item == null) emptyend++;
        }
        for (ItemStack item : this.smelting) {
            if (item != null && emptyend > 0) {
                emptyend++;
                this.smelt_progress[i]++;
                if (this.smelt_progress[i] > this.smelt_time) {
                    this.smelting[i] = null;
                    int i2 = 0;
                    for (ItemStack item2 : this.smelted) {
                        if (item2 == null) {
                            this.smelted[i2] = item;
                        }
                        i2++;
                    }
                    this.smelt_progress[i] = 0;
                }
            }
            if (item == null) {
                int i2 = 0;
                for (ItemStack item2 : this.que) {
                    if (item2 != null) {
                        item2.stackSize--;
                        if (item2.stackSize == 0) {
                            this.que[i2] = null;
                        }
                        this.smelting[i] = new ItemStack(item2.getItem(), 1);
                        this.que[i2] = item2;
                    }
                    i2++;
                }
            }
            i++;
        }
        if (!worldObj.isRemote) {
            if (hasMaster()) {
                if (isMaster()) {
                    if (!checkMultiBlockForm())
                        resetMultiBlockStructure();
                    if (inventory[19] != null) {
                        if (inventory[19].getItem() == Items.lava_bucket) {
                            if (inventory[20] == null) {
                                if (tank.getFluidAmount() < tank.getCapacity())
                                    setInventorySlotContents(20, new ItemStack(Items.bucket));
                                if (tank.getFluid() == null) {
                                    tank.fill(new FluidStack(FluidRegistry.LAVA, 1000), true);
                                } else if (tank.getFluidAmount() < tank.getCapacity()) {
                                    if (tank.getFluidAmount() < tank.getCapacity())
                                        tank.getFluid().amount += 1000;
                                }
                                if (tank.getFluidAmount() < tank.getCapacity()) {
                                    inventory[19].stackSize--;
                                    if (inventory[19].stackSize == 0)
                                        inventory[19] = null;
                                }


                            } else {
                                if (tank.getFluidAmount() < tank.getCapacity()) {
                                    inventory[19].stackSize--;
                                    if (inventory[19].stackSize == 0)
                                        inventory[19] = null;
                                    inventory[20].stackSize++;
                                }

                                if (tank.getFluid() == null) {
                                    tank.fill(new FluidStack(FluidRegistry.LAVA, 1000), true);
                                } else if (tank.getFluidAmount() < tank.getCapacity()) {
                                    tank.getFluid().amount += 1000;
                                }
                            }
                        }
                    }
                } else {
                    if (!checkForMaster())
                        resetMultiBlockStructure();
                }
            } else {
                if (checkMultiBlockForm()) {
                    setupMultiBlockStructure();
                }
            }
        }
    }

    public boolean checkForMaster() {
        TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
        return (tile != null && (tile instanceof TileEntityMagmaFrunace));
    }

    public void setupMultiBlockStructure() {
        for (int x = xCoord; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 2; y++)
                for (int z = zCoord; z < zCoord + 2; z++) {
                    boolean master = (x == xCoord && y == yCoord && z == zCoord);
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    if (tile != null && (tile instanceof TileEntityMagmaFrunace))
                        ((TileEntityMagmaFrunace) tile).setMaster(xCoord, yCoord, zCoord, master);
                }
    }

    private void resetMultiBlockStructure() {
        reset();
    }

    private boolean checkMultiBlockForm() {
        return MultiBlockHelper.isMultiBlockStructure(worldObj, xCoord, yCoord, zCoord);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        try
        {
            {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream obj;
                obj = new ObjectOutputStream(out);
                obj.writeObject(this.que);
                nbt.setByteArray("que", out.toByteArray());
            }
            {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream obj;
                obj = new ObjectOutputStream(out);
                obj.writeObject(this.smelting);
                nbt.setByteArray("smelting", out.toByteArray());
            }
            {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream obj;
                obj = new ObjectOutputStream(out);
                obj.writeObject(this.smelted);
                nbt.setByteArray("smelted", out.toByteArray());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return this.inventory[var1];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
        if(this.inventory[var1] != null){
            ItemStack itemstack;
            if(this.inventory[var1].stackSize <= var2){
                itemstack = this.inventory[var1];
                this.inventory[var1] = null;
                return itemstack;
            }else{
                itemstack = this.inventory[var1].splitStack(var2);
                if(this.inventory[var1].stackSize == 0){
                    this.inventory[var1] = null;
                }
                return itemstack;
            }
        }
        return null;	}

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if(this.inventory[slot] != null){
            ItemStack itemstack = this.inventory[slot];
            this.inventory[slot] = null;
            return itemstack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.inventory[slot]= itemStack;

        if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()){
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return null;
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack var2, int var3) {
		return false;
	}

	@Override
	public boolean canExtractItem(int var1, ItemStack var2, int var3) {
		return false;
	}

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if(from.equals(ForgeDirection.UP) || from.equals(ForgeDirection.DOWN))
            tank.fill(resource, doFill);
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return drain(from, resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if(from.equals(ForgeDirection.UP) || from.equals(ForgeDirection.DOWN))
                tank.drain(maxDrain, doDrain);
            return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{tank.getInfo()};
    }
}
