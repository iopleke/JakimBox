package jakimbox.prefab.render;

import com.mia.craftstudio.minecraft.client.CSClientModelWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author ProfMobius
 */
public class RenderPropInv implements IItemRenderer
{
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack itemstack, Object... data)
    {
        GL11.glPushMatrix();

        DecoModelMetadata modelData = Props.modelData.get(itemstack.getItemDamage());

        if (type != ItemRenderType.INVENTORY)
        {
            GL11.glTranslatef(0.5F, 0F, 0.5F);
        } else
        {
            GL11.glTranslatef(modelData.itemOffset.x, modelData.itemOffset.y, modelData.itemOffset.z);
        }

        if (type == ItemRenderType.INVENTORY || type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glScalef(modelData.itemScale, modelData.itemScale, modelData.itemScale);
        }

        ((CSClientModelWrapper) modelData.wrapper).render(null, 0.0625F, (type == ItemRenderType.EQUIPPED_FIRST_PERSON ? 12 : 8), 0, 0, 0, 0f);
        GL11.glPopMatrix();
    }
}
