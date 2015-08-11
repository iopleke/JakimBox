package jakimbox.prefab.gui.tabTypes;

import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.Tabs.TabState;
import jakimbox.prefab.gui.Tabs.TabType;

/**
 *
 * @author jakimfett
 */
public class ChestTab extends AbstractTab
{
    private final int DOUBLE_TAB_WIDTH = 122;
    private final int SINGLE_TAB_WIDTH = 68;

    public ChestTab(String ModID, TabSide side)
    {
        super(ModID, // Used to set the texture
            TabType.CHEST_SINGLE, // Tab type
            "chest", // Texture prefix
            side, // Side, passed in from object construction
            2, // Animation speed
            0, // Texture coordinate for closed (x)
            0, // Texture coordinate for closed (y)
            0, // Texture coordinate for open (x)
            18, // Texture coordinate for open (y)
            68, // Maximum tab width, used to reset the tab
            194, // Maximum tab height, used to reset the tab
            15, // Minimum tab width, used to reset the tab
            18 // Minimum tab height, used to reset the tab
        );
    }

    /**
     * Move tab to the default coordinate position
     */
    @Override
    protected void resetTabGUICoordinates()
    {
        int xOffset = defaultGUIX;
        int yOffset = defaultGUIY;

        switch (getTabState())
        {
            case CLOSED:
                xOffset = xOffset + DOUBLE_TAB_WIDTH - getMinTabSizeX();
                yOffset = yOffset + getMinTabSizeY();
                break;
            case OPEN:
                if (getType() == TabType.CHEST_SINGLE)
                {
                    xOffset = xOffset + DOUBLE_TAB_WIDTH - SINGLE_TAB_WIDTH;
                }
                yOffset = yOffset + getMinTabSizeY();
                break;
            case CLOSING:
                break;
            case OPENING:
                break;
        }

        setTabGUICoordinates(xOffset, yOffset);
    }

    @Override
    public void initializeTabAnimation()
    {
        super.initializeTabAnimation();
        if (getTabState() == TabState.CLOSED)
        {
            setTabTextureCoordinates(getMaxTabSizeX() - getMinTabSizeX(), getMinTabSizeY());
        }
    }
}
