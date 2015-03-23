package jakimbox.prefab.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Extendable class for simple items
 *
 * @author jakimfett
 */
public class ItemBasic extends Item
{

    // Default lifespan of an item is 5 minutes
    private int lifespan = 6000;

    /**
     * Create a simple item by passing in the mod ID and the unlocalized name
     *
     * @param modID    unlocalized ID of the mod
     * @param itemName unlocalized name for the item
     */
    public ItemBasic(String modID, String itemName)
    {
        super();
        this.iconString = modID + ":" + itemName;
        this.setUnlocalizedName(itemName);
    }

    /**
     * Overrides the default return for lifespan of an item
     *
     * @param itemStack n/a
     * @param world     n/a
     * @return the lifespan variable
     */
    @Override
    public int getEntityLifespan(ItemStack itemStack, World world)
    {
        return this.lifespan;
    }

    /**
     * Set the item's lifespan
     *
     * @param lifespan duration, in ticks
     */
    public void setLifespan(int lifespan)
    {
        this.lifespan = lifespan;
    }

    /**
     * Get the default item damage
     *
     * @return int the item damage of a new itemstack containing this item
     * @deprecated unsure why this would be used
     */
    public int getItemDamage()
    {
        return this.getDamage(new ItemStack(this));
    }

}
