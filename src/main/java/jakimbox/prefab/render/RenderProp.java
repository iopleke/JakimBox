package jakimbox.prefab.render;

import com.mia.craftstudio.minecraft.client.CSClientModelWrapper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author ProfMobius
 */
public class RenderProp extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
        ((CSClientModelWrapper) Props.modelData.get(tile.type).wrapper).render(tile, 0.0625F, tile.rotation, x, y, z, f);
        GL11.glPopMatrix();
    }
}
