package jakimbox.prefab.render;

import jakimbox.prefab.model.BasicModel;
import jakimbox.prefab.tileEntity.BasicTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class BasicTileEntityRenderer extends TileEntitySpecialRenderer
{
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
        this(scale, 0.0625F);
    }

    /**
     * Rendering object with scale and rotation
     *
     * @param scale    the scale for the object, relative to 1
     * @param rotation rotation for the object
     */
    public BasicTileEntityRenderer(float scale, float rotation)
    {
        setScale(scale);
        setRotation(rotation);
        setOffset(0.0D, 0.0D, 0.0D);
    }

    /**
     * Override for model specific processing
     *
     * @param tileEntity TileEntity being rendered
     */
    protected void modelSpecificOperations(TileEntity tileEntity)
    {
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale)
    {
        if (tileEntity instanceof BasicTileEntity)
        {
            GL11.glPushMatrix();
            GL11.glTranslated(x + xOffset, y + yOffset, z + zOffset);

            GL11.glPushMatrix();
            GL11.glTranslatef(0.5F, 0, 0.5F);
            GL11.glRotatef(tileEntity.getBlockMetadata() * (-90F), 0F, 1F, 0F);
            GL11.glTranslatef(-0.5F, 0, -0.5F);

            bindTexture(texture);

            modelSpecificOperations(tileEntity);

            // @TODO - figure out why using scale here causes weird rendering problems
            //model.render(scale);
            model.render(0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }

    public final void setOffset(double xOffset, double yOffset, double zOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
    }

    public final void setRotation(float rotation)
    {
        this.rotation = rotation;
    }

    public final void setScale(float scale)
    {
        this.xScale = scale;
        this.yScale = scale;
        this.zScale = scale;
    }

    public final void setScale(float xScale, float yScale, float zScale)
    {
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;

    }

}
