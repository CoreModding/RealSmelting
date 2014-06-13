package info.coremodding.realsmelting.tileentities;

import info.coremodding.realsmelting.helpers.MultiBlockHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author James The furnace tile entity
 */
public class TileEntityMagmaFrunace extends TileEntity implements ISidedInventory{
    
    private ItemStack[] que;
    private ItemStack[] smelted;
    private ItemStack[] smelting;
    
    private int[]       smelt_progress;
    private final int   smelt_time;
    
    /**
     * The tertiary constructor
     * 
     * @param ticks
     *            The ticks it takes to cook something
     */
    public TileEntityMagmaFrunace(int ticks)
    {
        this.que = new ItemStack[1];
        this.smelted = new ItemStack[1];
        this.smelting = new ItemStack[1];
        this.smelt_time = ticks;
    }
    
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
        this.smelting = new ItemStack[1];
        this.smelt_time = ticks;
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
        this.smelting = new ItemStack[at_once];
        this.smelt_progress = new int[at_once];
        this.smelt_time = ticks;
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
    public void updateEntity()
    {
        int i = 0;
        int emptyend = 0;
        for (ItemStack item : this.smelted)
        {
            if (item == null) emptyend++;
        }
        for (ItemStack item : this.smelting)
        {
            if (item != null && emptyend > 0)
            {
                emptyend++;
                this.smelt_progress[i]++;
                if (this.smelt_progress[i] > this.smelt_time)
                {
                    this.smelting[i] = null;
                    int i2 = 0;
                    for (ItemStack item2 : this.smelted)
                    {
                        if (item2 == null)
                        {
                            this.smelted[i2] = item;
                        }
                        i2++;
                    }
                    this.smelt_progress[i] = 0;
                }
            }
            if (item == null)
            {
                int i2 = 0;
                for (ItemStack item2 : this.que)
                {
                    if (item2 != null)
                    {
                        item2.stackSize--;
                        if (item2.stackSize == 0)
                        {
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
        
        if(MultiBlockHelper.isMultiBlockStructure(worldObj, this.xCoord, this.yCoord, this.zCoord))
        	System.out.println("check");
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack var2, int var3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int var1, ItemStack var2, int var3) {
		// TODO Auto-generated method stub
		return false;
	}
}
