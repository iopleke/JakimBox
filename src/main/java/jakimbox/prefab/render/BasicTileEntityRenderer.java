package jakimbox.prefab.render;

import jakimbox.prefab.model.BasicModel;
import jakimbox.prefab.tileEntity.BasicTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class BasicTileEntityRenderer extends TileEntitySpecialRenderer
{
    private float xRotation;
    private float yRotation;
    private float zRotation;

    protected BasicModel model;
    protected float rotation;
    protected ResourceLocation texture;

    protected double xOffset;
    protected float xScale;
    protected double yOffset;
    protected float yScale;
    protected double zOffset;
    protected float zScale;

    /**
     * Renders the model at a scale of 1
     */
    public BasicTileEntityRenderer()
    {
        this(1.0F);
    }

    public BasicTileEntityRenderer(float scale)
    {
        setScale(scale);
        setOffset(0.0D, 0.0D, 0.0D);
    }

    protected void doModelRotations()
    {
        if (xRotation != 0)
        {
            GL11.glRotatef(xRotation, 1F, 0F, 0F);
        }
        if (yRotation != 0)
        {
            GL11.glRotatef(yRotation, 0F, 1F, 0F);
        }
        if (zRotation != 0)
        {
            GL11.glRotatef(zRotation, 0F, 0F, 1F);
        }

    }

    /**
     * Override for model specific processing
     *
     * @param tileEntity TileEntity being rendered
     */
    protected void modelSpecificOperations(TileEntity tileEntity)
    {
    }

    public final void setScale(float scale)
    {
        this.setScale(scale, scale, scale);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick)
    {
        if (tileEntity instanceof BasicTileEntity)
        {
            GL11.glPushMatrix();
            GL11.glTranslated(x + xOffset, y + yOffset, z + zOffset);

            doModelRotations();

            GL11.glRotatef(tileEntity.getBlockMetadata() * (-90F), 0F, 1F, 0F);

            bindTexture(texture);

            modelSpecificOperations(tileEntity);

            // @TODO - figure out why using scale here causes weird rendering problems
            // ..I should really fix this shouldn't I.
            //model.render(scale);
            model.render(0.03125F);
            GL11.glPopMatrix();
        }
    }

    public final void setOffset(double xOffset, double yOffset, double zOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
    }

    public final float[] getRotation()
    {
        float[] rotation =
        {
            xRotation, yRotation, zRotation
        };
        return rotation;
    }

    public final void setRotation(float xRotation, float yRotation, float zRotation)
    {
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;
    }

    public final void setScale(float xScale, float yScale, float zScale)
    {
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;

    }

}
