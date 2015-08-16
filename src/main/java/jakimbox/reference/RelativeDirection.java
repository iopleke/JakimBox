package jakimbox.reference;

import jakimbox.prefab.gui.Tabs;
import jakimbox.prefab.gui.Tabs.TabSide;

/**
 * Rotated direction grid for use with block rotation meta values
 *
 * @author jakimfett
 */
public enum RelativeDirection
{
    LEFT(1, 0), BACKLEFT(1, 1), BACK(0, 1), BACKRIGHT(-1, 1), RIGHT(-1, 0), FRONTRIGHT(-1, -1), FRONT(0, -1), FRONTLEFT(1, -1);

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

    /**
     * Return the tab side for a given relative direction
     *
     * @param direction
     * @return TabSide
     */
    public static TabSide getRelativeDirectionTabSide(RelativeDirection direction)
    {
        if (direction == RelativeDirection.FRONT || direction == RelativeDirection.FRONTLEFT || direction == RelativeDirection.LEFT || direction == RelativeDirection.BACKLEFT)
        {
            return Tabs.TabSide.LEFT;
        }
        return Tabs.TabSide.RIGHT;
    }
}
