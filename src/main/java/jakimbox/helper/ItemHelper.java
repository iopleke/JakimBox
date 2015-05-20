package jakimbox.helper;

import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Helper classes for item and itemstack objects
 *
 * @author jakimfett
 */
public class ItemHelper
{
    /**
     * Spawn a rotating, bobbing item stack in the world at the provided coordinates
     *
     * @param world
     * @param itemStack stack of items to put in the world
     * @param x         coordinate
     * @param y         coordinate
     * @param z         coordinate
     */
    public static void throwItemStack(World world, ItemStack itemStack, double x, double y, double z)
    {
        if (itemStack != null)
        {
            Random random = new Random();
            float xFloat = random.nextFloat() * 0.8F + 0.1F;
            float yFloat = random.nextFloat() * 0.8F + 0.1F;
            float zFloat = random.nextFloat() * 0.8F + 0.1F;
            float motionMultiplier = 0.05F;

            EntityItem entityitem = new EntityItem(world, (float) x + xFloat, (float) y + yFloat, (float) z + zFloat, itemStack);

            entityitem.motionX = (float) random.nextGaussian() * motionMultiplier;
            entityitem.motionY = (float) random.nextGaussian() * motionMultiplier + 0.2F;
            entityitem.motionZ = (float) random.nextGaussian() * motionMultiplier;

            world.spawnEntityInWorld(entityitem);
        }
    }
}
