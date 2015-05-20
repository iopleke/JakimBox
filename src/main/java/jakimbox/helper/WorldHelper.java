package jakimbox.helper;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

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
        Block block = null;
        if (FMLClientHandler.instance().getSide() == Side.CLIENT)
        {
            block = FMLClientHandler.instance().getClient().theWorld.getBlock(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        } else if (FMLClientHandler.instance().getSide() == Side.SERVER)
        {
            block = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getBlock(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        }
        if (block != null)
        {
            if (block.equals(Blocks.lit_furnace))
            {
                return true;
            }
        }
        return false;
    }
}
