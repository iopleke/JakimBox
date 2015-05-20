package jakimbox.reference;

/**
 * Rotated direction grid for use with block rotation meta values
 *
 * @author jakimfett
 */
public enum RelativeDirection
{
    LEFT(-1, 0), BACKLEFT(-1, 1), BACK(0, 1), BACKRIGHT(1, 1), RIGHT(1, 0), FRONTRIGHT(1, -1), FRONT(0, -1), FRONTLEFT(-1, -1);

    /**
     * Array of all valid relative directions
     */
    public static final RelativeDirection[] VALID_DIRECTIONS =
    {
        LEFT, BACKLEFT, BACK, BACKRIGHT, RIGHT, FRONTRIGHT, FRONT, FRONTLEFT
    };

    /**
     * Offset relative to the center block
     */
    public final int x, z;

    RelativeDirection(int x, int z)
    {
        this.x = x;
        this.z = z;
    }

    /**
     * Returns the relative x/z for the direction
     *
     * @return
     */
    public int[] getOffset()
    {
        return new int[]
        {
            x, z
        };
    }

    /**
     * Returns inverse x/z direction
     *
     * @return
     */
    public int[] getOpposite()
    {
        return new int[]
        {
            -x, -z
        };
    }
}
