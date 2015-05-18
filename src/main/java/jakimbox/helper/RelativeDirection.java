package jakimbox.helper;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Rotated direction grid for use with block rotation meta values
 *
 * @author jakimfett
 */
public enum RelativeDirection
{
    LEFT(-1, 0), BACKLEFT(-1, 1), BACK(0, 1), BACKRIGHT(1, 1), RIGHT(1, 0), FRONTRIGHT(1, -1), FRONY(0, -1), FRONTLEFT(-1, -1);

    /**
     * Offset relative to the center block
     */
    private final int x, y;

    RelativeDirection(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the relative x/y for the direction
     *
     * @return
     */
    public int[] getOffset()
    {
        return new int[]
        {
            x, y
        };
    }

    /**
     * Returns inverse x/y direction
     *
     * @return
     */
    public int[] getOpposite()
    {
        return new int[]
        {
            -x, -y
        };
    }

    public int[] getOffsetFromMeta(int meta)
    {

        if (meta >= 0 && meta <= 3)
        {
            switch (meta)
            {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }

        return getOffset();
    }

    /**
     * Return the ForgeDirection for the RelativeDirection, given a meta value.
     *
     * @param meta 0 = South, 1 = West, 2 = North, 3 = East
     * @return
     */
    public ForgeDirection getForgeDirectionForMeta(int meta)
    {
        return ForgeDirection.getOrientation(meta);
    }

}
