package jakimbox.prefab.render;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Base class for simple item rendering
 *
 * @author jakimfett
 */
public class BasicItemRenderer implements IItemRenderer
{
    BasicTileEntityRenderer tesr;
    private final TileEntity tileEntity;

    public BasicItemRenderer(BasicTileEntityRenderer tesr, TileEntity tileEntity)
    {
        this.tesr = tesr;
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType type)
    {
        switch (type)
        {
            case ENTITY:
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (type == IItemRenderer.ItemRenderType.ENTITY)
        {
            GL11.glTranslatef(-0.5F, -0.0F, -0.5F);
        }
        tesr.renderTileEntityAt(this.tileEntity, 0.0D, -(tesr.yOffset / 3), 0.0D, 0.0625F);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack itemStack, ItemRendererHelper helper)
    {
        switch (helper)
        {
            case ENTITY_BOBBING:
            case ENTITY_ROTATION:
            case EQUIPPED_BLOCK:
            case INVENTORY_BLOCK:
                return true;
            default:
                return false;
        }
    }
}
