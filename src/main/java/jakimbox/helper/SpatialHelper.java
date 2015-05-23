package jakimbox.helper;

import jakimbox.reference.RelativeDirection;
import static jakimbox.reference.RelativeDirection.BACK;
import static jakimbox.reference.RelativeDirection.BACKLEFT;
import static jakimbox.reference.RelativeDirection.BACKRIGHT;
import static jakimbox.reference.RelativeDirection.FRONT;
import static jakimbox.reference.RelativeDirection.FRONTLEFT;
import static jakimbox.reference.RelativeDirection.FRONTRIGHT;
import static jakimbox.reference.RelativeDirection.LEFT;
import static jakimbox.reference.RelativeDirection.RIGHT;
import net.minecraft.tileentity.TileEntity;

/**
 * Helper class for relative and absolute coordinate operations
 *
 * @author jakimfett
 */
public class SpatialHelper
{

    /**
     * Return the x/z offset for the RelativeDirection, given a meta value.
     *
     * @param relative enum
     * @param meta     0 = South, 1 = West, 2 = North, 3 = East
     * @return int[] x/z offset
     */
    public static int[] getOffsetFromRelative(RelativeDirection relative, int meta)
    {
        if (meta >= 1 && meta <= 3)
        {
            switch (meta)
            {
                case 1:
                    return SpatialHelper.rotateRelativeDirection(SpatialHelper.rotateRelativeDirection(relative, false), false).getOffset();
                case 2:
                    return relative.getOpposite();
                case 3:
                    return SpatialHelper.rotateRelativeDirection(SpatialHelper.rotateRelativeDirection(relative, true), true).getOffset();
            }
        }

        return relative.getOffset();
    }

    /**
     * Gets the TileEntity for a given relative direction
     *
     * @param tileEntity
     * @param direction
     * @return TileEntity or null
     */
    public static TileEntity getTileEntityForRelativeDirection(TileEntity tileEntity, RelativeDirection direction)
    {
        if (tileEntity != null)
        {
            int[] offset = SpatialHelper.getOffsetFromRelative(direction, tileEntity.getBlockMetadata());
            TileEntity relativeTileEntity = WorldHelper.getWorld().getTileEntity(tileEntity.xCoord + offset[0], tileEntity.yCoord, tileEntity.zCoord + offset[1]);
            if (relativeTileEntity != null)
            {
                return relativeTileEntity;
            }
        }
        return null;
    }

    /**
     * Return a directionally rotated enum
     *
     * @param direction original direction to be rotated
     * @param clockwise flag for rotating clockwise (true) or anticlockwise (false)
     * @return RelativeDirection enum
     */
    public static RelativeDirection rotateRelativeDirection(RelativeDirection direction, boolean clockwise)
    {

        if (clockwise)
        {
            switch (direction)
            {
                case LEFT:
                    return BACKLEFT;
                case BACKLEFT:
                    return BACK;
                case BACK:
                    return BACKRIGHT;
                case BACKRIGHT:
                    return RIGHT;
                case RIGHT:
                    return FRONTRIGHT;
                case FRONTRIGHT:
                    return FRONT;
                case FRONT:
                    return FRONTLEFT;
            }
        } else
        {
            switch (direction)
            {
                case LEFT:
                    return FRONTLEFT;
                case FRONTLEFT:
                    return FRONT;
                case FRONT:
                    return FRONTRIGHT;
                case FRONTRIGHT:
                    return RIGHT;
                case RIGHT:
                    return BACKRIGHT;
                case BACKRIGHT:
                    return BACK;
                case BACK:
                    return BACKLEFT;
                case BACKLEFT:
                    return LEFT;
            }

        }
        return direction;
    }
}
