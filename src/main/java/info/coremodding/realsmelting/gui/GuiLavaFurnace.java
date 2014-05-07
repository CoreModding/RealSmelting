package info.coremodding.realsmelting.gui;

import info.coremodding.realsmelting.RealSmelting;
import info.coremodding.realsmelting.gui.containers.ContainerLavaFurnace;
import info.coremodding.realsmelting.lib.Strings;
import info.coremodding.realsmelting.tileentities.TileEntityLavaFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiLavaFurnace extends GuiContainer{
	private TileEntityLavaFurnace lava;
	
	public static final ResourceLocation texture = new ResourceLocation(Strings.MODID, "textures/gui/Lava_Furnace.png");


	public GuiLavaFurnace(InventoryPlayer inventory, TileEntityLavaFurnace entity) {
		super(new ContainerLavaFurnace(inventory, entity));
		this.lava = entity;
	}

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){
		String name = this.lava.isInvNameLocalized() ? this.lava.getInvName() : I18n.format(this.lava.getInvName(), RealSmelting.instance);
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("contaniner.inventory", RealSmelting.instance), 30, this.ySize - 96 + 2, 4210752);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		int i1;
		
		if(this.lava.hasPower()){
			i1 = this.lava.getPowerRemainingScaled(60);
			this.drawTexturedModalRect(guiLeft + 12, guiTop + 71 - i1, 176, 91 - i1, 3, i1);
		}
		
		int k = this.lava.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, k + 1, 16);
	}

}
