package jakimbox.prefab.tileEntity;

import jakimbox.helper.SpatialHelper;
import jakimbox.helper.WorldHelper.AdjacentBlockType;
import jakimbox.reference.RelativeDirection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import werkbench.reference.Config;

/**
 *
 * Useful functions for tabbed TileEntities with an inventory
 *
 * @author jakimfett
 */
public abstract class TabbedInventoryTileEntity extends BasicInventoryTileEntity
{

    /**
     * Cached memory for the surrounding blocks,
     */
    private final Map<RelativeDirection, Block> cache = new EnumMap<RelativeDirection, Block>(RelativeDirection.class);

    /**
     * Counter for updating the cache,
     */
    private int tickCounter;

    public TabbedInventoryTileEntity(String tileEntityName, int inventorySize)
    {
        super(tileEntityName, inventorySize);
        resetBlockCache();
        resetTickCounter();
    }

    /**
     * Decrease the tick counter by one.
     */
    private void decrementTickCounter()
    {
        tickCounter--;
    }

    /**
     * Wipe the blockmemory, then set all valid directions to null,
     */
    private void resetBlockCache()
    {
        cache.clear();
        for (RelativeDirection direction : RelativeDirection.VALID_DIRECTIONS)
        {
            cache.put(direction, null);
        }
    }

    /**
     * Reset the tick counter to a random between 0 and the max update count.
     */
    private void resetTickCounter()
    {
        tickCounter = new Random().nextInt(Config.maxUpdateTickCount);
    }

    /**
     * Returns the cached surrounding blocks
     *
     * @return cache
     */
    public Map<RelativeDirection, Block> getBlockCache()
    {
        return cache;
    }

    /**
     * Main update loop
     */
    @Override
    public void updateEntity()
    {
        if (tickCounter <= 0)
        {
            updateSideChecks();
            resetTickCounter();
        } else
        {
            decrementTickCounter();
        }
    }

    /**
     * Check all sides on the Y plane, and update any changes to the block cache
     */
    public void updateSideChecks()
    {
        for (RelativeDirection direction : RelativeDirection.VALID_DIRECTIONS)
        {
            int[] directionOffset = SpatialHelper.getOffsetFromRelative(direction, blockMetadata);
            Block block = this.worldObj.getBlock(xCoord + directionOffset[0], yCoord, zCoord + directionOffset[1]);

            if (block != null)
            {
                if (AdjacentBlockType.isBlockSupported(block))
                {
                    Block cachedBlock = (Block) cache.get(direction);
                    if (cachedBlock != block)
                    {
                        cache.put(direction, block);
                    }
                }
            } else
            {
                cache.put(direction, null);
            }
        }
    }
}
