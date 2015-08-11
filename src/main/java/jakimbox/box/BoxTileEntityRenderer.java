package jakimbox.box;

import jakimbox.JakimBox;
import jakimbox.prefab.render.BasicTileEntityRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author jakimfett
 */
public class BoxTileEntityRenderer extends BasicTileEntityRenderer
{
    public BoxTileEntityRenderer()
    {
        super(0.24F, 0.0625F);

        setOffset(0.5D, 0.68D, 0.5D);

        model = new BoxModel();
        texture = new ResourceLocation(JakimBox.modID, "textures/models/jakimbox.png");
    }

    @Override
    protected void modelSpecificOperations(TileEntity tileEntity)
    {
        if (tileEntity instanceof BoxTileEntity)
        {
            GL11.glRotatef(180f, 0f, 0f, 1f);
        }
    }
}
