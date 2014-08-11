package info.coremodding.realsmelting.gui;

import info.coremodding.realsmelting.gui.containers.ContainerMagmaFurnace;
import info.coremodding.realsmelting.lib.Strings;
import info.coremodding.realsmelting.tileentities.TileEntityMagmaFrunace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiMagmaFurnace extends GuiContainer{

    private TileEntityMagmaFrunace entity;
    public static final ResourceLocation texture = new ResourceLocation(Strings.MODID, "textures/gui/magmaFurnace.png");
    protected int mouseX = 0, mouseY = 0;

    public GuiMagmaFurnace(InventoryPlayer inventory, TileEntityMagmaFrunace entity) {
        super(new ContainerMagmaFurnace(inventory, entity));
        this.entity = entity;

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {


        List<String> text = new ArrayList<String>();
        if (entity.tank.getFluidAmount() > 0 || (entity.tank.getFluid() != null)) {
            text.clear();
            text.add(entity.tank.getFluid().getFluid().getUnlocalizedName());
            text.add(entity.tank.getFluidAmount() + "/" + entity.tank.getCapacity() + "mB");
            drawToolTipOverArea(mouseX, mouseY, 73, 16, 83, 67, text, fontRendererObj);
        } else {
            text.clear();
            text.add("Empty");
            drawToolTipOverArea(mouseX, mouseY, 73, 16, 83, 67, text, fontRendererObj);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        drawTanks();
    }

    public void drawTanks() {
        int j;
        if (entity.tank.getFluid() != null) {
            j = getValueScaled(entity.tank.getFluidAmount(), entity.tank.getCapacity(), 52);
            this.drawFluid(guiLeft + 73, guiTop + 68 - j, entity.tank.getFluid(), 11, j);
        }

    }

    @Override
    public void handleMouseInput() {
        int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;

        mouseX = x - guiLeft;
        mouseY = y - guiTop;

        super.handleMouseInput();
    }

    public int getValueScaled(int value, int max, int scale) {
        return (value * scale) / max;
    }

    public void drawToolTipOverArea(int mouseX, int mouseY, int minX, int minY, int maxX, int maxY, List<String> list, FontRenderer font) {
        if (list != null && font != null) {
            if ((mouseX >= minX && mouseX <= maxX) && (mouseY >= minY && mouseY <= maxY))
                drawHoveringText(list, mouseX, mouseY, font);
        }
    }

    public void drawFluid(int x, int y, FluidStack fluid, int width, int height) {
        if (fluid == null || fluid.getFluid() == null)
            return;
        mc.renderEngine.bindTexture(new ResourceLocation("textures/atlas/blocks.png"));
        GL11.glColor3ub((byte) (fluid.getFluid().getColor() >> 16 & 0xFF), (byte) (fluid.getFluid().getColor() >> 8 & 0xFF), (byte) (fluid.getFluid().getColor() & 0xFF));
        drawTiledTexture(x, y, fluid.getFluid().getIcon(fluid), width, height);
    }

    public void drawTiledTexture(int x, int y, IIcon icon, int width, int height) {
        int i = 0;
        int j = 0;

        int drawHeight = 0;
        int drawWidth = 0;

        for (i = 0; i < width; i += 16) {
            for (j = 0; j < height; j += 16) {
                drawWidth = (width - i) < 16 ? (width - i) : 16;
                drawHeight = (height - j) < 16 ? (height - j) : 16;
                drawScaledTexturedModelRectFromIcon(x + i, y + j, icon, drawWidth, drawHeight);
            }
        }
        GL11.glColor4f(1f, 1f, 1f, 1F);
    }

    public void drawScaledTexturedModelRectFromIcon(int x, int y, IIcon icon, int width, int height) {
        if (icon == null)
            return;

        double minU = icon.getMinU();
        double maxU = icon.getMaxU();
        double minV = icon.getMinV();
        double maxV = icon.getMaxV();

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, this.zLevel, minU, minV + (maxV - minV) * height / 16F);
        tessellator.addVertexWithUV(x + width, y + height, this.zLevel, minU + (maxU - minU) * width / 16F, minV + (maxV - minV) * height / 16F);
        tessellator.addVertexWithUV(x + width, y + 0, this.zLevel, minU + (maxU - minU) * width / 16F, minV);
        tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, minU, minV);
        tessellator.draw();
    }
}
