package info.coremodding.realsmelting.tileentities;

import info.coremodding.realsmelting.blocks.LavaFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityLavaFurnace extends TileEntity implements ISidedInventory{
	

	private String localizedName;
	
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2, 1};
	private static final int[] slots_sides = new int[]{1};
	
	private ItemStack[] slots;
	
	public int furnaceSpeed;
	
	public float Temp;
	public static float MaxTemp;
	public static float TempRate;
	public static int SpeedRate;
	
	public int currentItemBurnTime;
	
	public int cookTime;

	private int smeltItem;
	
	/**
	 * 
	 * @param slots 
	 * 				The number of slots
	 * @param Temp 
	 * 				The starting temperature of the furnace
	 * @param MaxTemp 
	 * 				The max temperature of the furnace
	 * @param furnaceSpeeed 
	 * 				The starting speed of the furnace
	 * @param TempRate
	 * 				The amount the temperature increase or decreases
	 * @param SpeedRate
	 * 				The amount the furnace speed increase or decreases
	 */
	public TileEntityLavaFurnace(int slots, float Temp, float MaxTemp, int furnaceSpeeed, float TempRate, int SpeedRate){
		this.Temp = Temp;
		this.furnaceSpeed = furnaceSpeeed;
		this.MaxTemp = MaxTemp;
		this.slots = new ItemStack[slots];
		this.TempRate = TempRate;
		this.SpeedRate = SpeedRate;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return this.slots[var1];
	}
	
	public int getsizeInventory(){
		return this.slots.length;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		if(this.slots[var1] != null){
			ItemStack itemstack;
			if(this.slots[var1].stackSize <= var2){
				itemstack = this.slots[var1];
				this.slots[var1] = null;
				return itemstack;
			}else{
				itemstack = this.slots[var1].splitStack(var2);
				if(this.slots[var1].stackSize == 0){
					this.slots[var1] = null;
				}
				return itemstack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		if(this.slots[var1] != null){
			ItemStack itemstack = this.slots[var1];
			this.slots[var1] = null;
			return itemstack;
		}
		
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		this.slots[var1]= var2;
		
		if(var2 != null && var2.stackSize > this.getInventoryStackLimit()){
			var2.stackSize = this.getInventoryStackLimit();
		}
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
		return 64;
	}
	
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		
		NBTTagList list = nbt.getTagList("items", Constants.NBT.TAG_COMPOUND);
		this.slots = new ItemStack[this.getsizeInventory()];
		
		for(int i = 0; i < list.tagCount(); i ++){
			NBTTagCompound compound = list.getCompoundTagAt(i);
			 int j = compound.getByte("slot") & 0xff;
			
			if(j >= 0 && j < this.slots.length){
				this.slots[j] = ItemStack.loadItemStackFromNBT(compound);
				
			}
		}
		this.Temp = (int)nbt.getShort("burntime");
		this.cookTime = (int)nbt.getShort("cooktime");
		this.currentItemBurnTime = (int)nbt.getShort("currentItemBurnTime");
		
		if(nbt.hasKey("customname")){
			this.localizedName = nbt.getString("customname");
		}

	}
	
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		
		nbt.setShort("burntime", (short) this.Temp);
		nbt.setShort("cooktime", (short) this.cookTime);
		nbt.setShort("currentItemBurnTime", (short) this.currentItemBurnTime);
		
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < this.slots.length; i ++){
			if(this.slots[i] != null){
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("slot", (byte) i);
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}
		nbt.setTag("items", list);
		if(this.isInvNameLocalized()){
			nbt.setString("customname", this.localizedName);
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64D;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean hasPower(){
		if(this.Temp> 0) return true;
		
		return false;
	}
	
	public boolean isSmelting(){
		return this.cookTime > 0;
	}
	
	public void updateEntity(){
		if(worldObj.getBlock(xCoord, yCoord-1, zCoord).equals(Blocks.lava)){
			if(this.Temp < this.MaxTemp){
				this.Temp += TempRate;
			}else if(!worldObj.getBlock(xCoord, yCoord-1, zCoord).equals(Blocks.lava)){
				if(this.Temp > 0){
					this.Temp -= TempRate;
				}
			}
		}
		System.out.println(this.Temp);
		boolean flag = this.Temp > 0;
    	boolean flag1 = false;
	
    	
    	if(this.Temp > this.MaxTemp){
    		this.Temp = this.MaxTemp;
    	}
    	if(this.Temp > 0 && this.Temp <= 50){
    		if(this.furnaceSpeed>15)
    			this.furnaceSpeed += TempRate;
    	}	
    	if(this.Temp > 50 && this.Temp <= 100){
    		if(this.furnaceSpeed > 15)
    			this.furnaceSpeed += TempRate;
    	}
    	if(this.Temp > 100 && this.Temp <= 150){
    		if(this.furnaceSpeed > 25)
    			this.furnaceSpeed += TempRate;
    	}
    	if(this.Temp > 150 && this.Temp <= 200){
    		if(this.furnaceSpeed > 35)
    			this.furnaceSpeed += TempRate;
    	}
    	if(this.Temp > 200 && this.Temp <= 250){
    		if(this.furnaceSpeed > 45)
    			this.furnaceSpeed += TempRate;
    	}
    	if(this.Temp > 250 && this.Temp <= 300){
    		if(this.furnaceSpeed > 55)
    			this.furnaceSpeed += TempRate;
    	}

    	if (!this.worldObj.isRemote){
        	

        		flag1 = true;
        	
        		

                		
            	}                
        	
        	if (this.hasPower() && this.canSmelt())
        	{
            	this.cookTime++;

            	if (this.cookTime == this.furnaceSpeed)
            	{
                	this.cookTime = 0;
                	this.smeltItem();
                	flag1 = true;
            	}
        	}
        	else
        	{
            	this.cookTime = 0;
        	}
			
			if(flag != this.hasPower()){
				flag1 = true;
				LavaFurnace.updateSaltFurnaceBlockState(this.Temp > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
			
	
	
	if(flag1){
		this.markDirty();
	}
}

	private boolean canSmelt(){
		if(this.slots[0] == null){
			return false;
		}else{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			
			if(itemstack == null) return false;
			if(this.slots[1] == null) return true;
			if(!this.slots[1].isItemEqual(itemstack)) return false;
			
			int result = this.slots[1].stackSize + itemstack.stackSize;
			
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
				
			
		}
	}
	
	public void smeltItem(){
		if(this.canSmelt()){
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			
			if(this.slots[1] == null){
				this.slots[1] = itemstack.copy();
			}else if(this.slots[1].isItemEqual(itemstack)){
				this.slots[1].stackSize += itemstack.stackSize;
			}
			
			this.slots[0].stackSize--;
			
			if(this.slots[0].stackSize <= 0){
				this.slots[0] = null;
		}
	}
}

	

	
	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
	
		return var1 == 2 ? false : (var1 == 1);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 == 0 ? slots_bottom  : (var1 == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack var2, int var3) {
		return this.isItemValidForSlot(var1, var2);
	}

	@Override
	public boolean canExtractItem(int var1, ItemStack var2, int var3) {
		return var3 != 0 || var1 != 1 || var2.getItem() == Items.bucket;
	}

	public int getCookProgressScaled(int par1)
	{
  		return this.cookTime * par1 / this.furnaceSpeed;
	}

	public int getPowerRemainingScaled(int par1){
    	return (int) (this.Temp * par1 / this.MaxTemp);
	}

	public boolean isInvNameLocalized() {
		return false;
	}

	public String getInvName() {
		return "container.IronOxideFurnace";
	}

	public void setGuiDisplayName(String displayName) {
		
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}



	public int getCurrentCharge() {
		return (int) this.Temp;
	}



	public int getChargeCapcity() {
		return (int) this.MaxTemp;
	}

}