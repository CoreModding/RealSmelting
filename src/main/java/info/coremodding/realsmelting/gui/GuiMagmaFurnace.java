package info.coremodding.realsmelting.gui;

import info.coremodding.realsmelting.gui.containers.ContainerMagmaFurnace;
import info.coremodding.realsmelting.lib.Strings;
import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiMagmaFurnace extends GuiContainer{

    private TileEntityMagmaFrunace tileEntity;
    public static final ResourceLocation texture = new ResourceLocation(Strings.MODID, "textures/gui/magmaFurnace.png");


    public GuiMagmaFurnace(InventoryPlayer inventory, TileEntityMagmaFrunace entity) {
        super(new ContainerMagmaFurnace(inventory, entity));
        this.tileEntity = entity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
