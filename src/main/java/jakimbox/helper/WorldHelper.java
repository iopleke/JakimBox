package jakimbox.helper;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Helper class for world operations
 *
 * @author jakimfett
 */
public class WorldHelper
{

    /**
     * Check if a given TileEntity is a furnace block
     *
     * @param tileEntity TileEntity to check
     * @return boolean
     */
    public static boolean isActiveFurnace(TileEntity tileEntity)
    {
        Block block = WorldHelper.getWorld().getBlockState(tileEntity.getPos()).getBlock();

        if (block != null)
        {
            if (block.equals(Blocks.lit_furnace))
            {
                return true;
            }
        }
        return false;
    }

    public static World getWorld()
    {
        if (FMLClientHandler.instance().getSide() == Side.CLIENT)
        {
            return FMLClientHandler.instance().getClient().theWorld;
        } else if (FMLClientHandler.instance().getSide() == Side.SERVER)
        {
            return FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
        }
        return null;
    }

    /**
     * Get ForgeDirection offset for the second half of a double chest
     *
     * @param x
     * @param y
     * @param z
     * @return ForgeDirection of the chest, or null if none was found
     */
    public EnumFacing doubleChestOffset(int x, int y, int z)
    {
        return null;
    }

    public static enum AdjacentBlockType
    {
        CHEST_DOUBLE, CHEST_SINGLE, EMPTY, FURNACE_ACTIVE, FURNACE_INACTIVE, OFFSET;

        /**
         * Array of all valid block types
         */
        public static final AdjacentBlockType[] VALID_TYPES =
        {
            CHEST_DOUBLE, CHEST_SINGLE, EMPTY, FURNACE_ACTIVE, FURNACE_INACTIVE, OFFSET
        };

        /**
         * Check if the TileEntity type is supported
         *
         * @param block
         * @return boolean
         */
        public static boolean isBlockSupported(Block block)
        {
            return block == Blocks.anvil
                    || block == Blocks.brewing_stand
                    || block == Blocks.chest
                    || block == Blocks.ender_chest
                    || block == Blocks.enchanting_table
                    || block == Blocks.furnace
                    || block == Blocks.hopper
                    || block == Blocks.lit_furnace;
        }
    }
}
