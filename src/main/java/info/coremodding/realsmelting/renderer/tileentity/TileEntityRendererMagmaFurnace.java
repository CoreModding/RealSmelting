package info.coremodding.realsmelting.renderer.tileentity;

import info.coremodding.realsmelting.lib.Strings;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererMagmaFurnace extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par) {
        int metaData = tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        ResourceLocation texture = new ResourceLocation(Strings.MODID + "textures/models/magma_Furnace.png");
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float)x, (float)y, (float)z);
            Tessellator tessellator = Tessellator.instance;
            this.bindTexture(texture);
            tessellator.startDrawingQuads();
            {
                if(metaData == 0){
                    tessellator.addVertexWithUV(1, 1, 1, 1, 0);
                    tessellator.addVertexWithUV(1, 0, 1, 1, 1);
                    tessellator.addVertexWithUV(1, 0, 0, 0, 1);
                    tessellator.addVertexWithUV(1, 1, 0, 0, 0);

                    tessellator.addVertexWithUV(0, 0, 1, 1, 1);
                    tessellator.addVertexWithUV(0, 1, 1, 1, 0);
                    tessellator.addVertexWithUV(0, 1, 0, 0, 0);
                    tessellator.addVertexWithUV(0, 0, 0, 0, 1);

                    tessellator.addVertexWithUV(1, 0, 1, 1, 1);
                    tessellator.addVertexWithUV(1, 1, 1, 1, 0);
                    tessellator.addVertexWithUV(0, 1, 1, 0, 0);
                    tessellator.addVertexWithUV(0, 0, 1, 0, 1);

                    tessellator.addVertexWithUV(1, 1, 0, 1, 0);
                    tessellator.addVertexWithUV(1, 0, 0, 1, 1);
                    tessellator.addVertexWithUV(0, 0, 0, 0, 1);
                    tessellator.addVertexWithUV(0, 1, 0, 0, 0);

                    tessellator.addVertexWithUV(1, 0, 1, 1, 1);
                    tessellator.addVertexWithUV(0, 0, 1, 1, 0);
                    tessellator.addVertexWithUV(0, 0, 0, 0, 0);
                    tessellator.addVertexWithUV(1, 0, 0, 0, 1);

                    tessellator.addVertexWithUV(0, 1, 1, 1, 0);
                    tessellator.addVertexWithUV(1, 1, 1, 1, 1);
                    tessellator.addVertexWithUV(1, 1, 0, 0, 1);
                    tessellator.addVertexWithUV(0, 1, 0, 0, 0);
                }
            }
            tessellator.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
