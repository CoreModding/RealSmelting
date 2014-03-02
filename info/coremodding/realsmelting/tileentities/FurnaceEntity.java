package info.coremodding.realsmelting.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author James The furnace tile entity
 */
public class FurnaceEntity extends TileEntity {

    ItemStack[] que;
    ItemStack[] smelted;
    ItemStack[] smelting;

    int[] smeltprogress;
    int smelttime;

    /**
     * The teritary constructor
     *
     * @param ticks The ticks it takes to cook something
     */
    public FurnaceEntity(int ticks) {
        this.que = new ItemStack[1];
        this.smelted = new ItemStack[1];
        this.smelting = new ItemStack[1];
        this.smelttime = ticks;
    }

    /**
     * The secondary constructor
     *
     * @param slots The amount of slots of input
     * @param ticks The ticks it takes to cook something
     */
    public FurnaceEntity(int slots, int ticks) {
        this.que = new ItemStack[slots];
        this.smelted = new ItemStack[slots];
        this.smelting = new ItemStack[1];
        this.smelttime = ticks;
    }

    /**
     * The main constructor
     *
     * @param slots  The slots amount for input and output
     * @param ticks  The amount of ticks to cook something
     * @param atonce How many things can be cooking at once
     */
    public FurnaceEntity(int slots, int ticks, int atonce) {
        this.que = new ItemStack[slots];
        this.smelted = new ItemStack[slots];
        this.smelting = new ItemStack[atonce];
        this.smeltprogress = new int[atonce];
        this.smelttime = ticks;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEntity() {
        int i = 0;
        int emptyend = 0;
        for (ItemStack item : this.smelted) {
            if (item == null)
                emptyend++;
        }
        for (ItemStack item : this.smelting) {
            if (item != null && emptyend > 0) {
                emptyend++;
                this.smeltprogress[i]++;
                if (this.smeltprogress[i] > this.smelttime) {
                    this.smelting[i] = null;
                    int i2 = 0;
                    for (ItemStack item2 : this.smelted) {
                        if (item2 == null) {
                            this.smelted[i2] = item;
                        }
                        i2++;
                    }
                    this.smeltprogress[i] = 0;
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
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
