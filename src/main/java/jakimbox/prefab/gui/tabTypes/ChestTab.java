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
        super(ModID, TabType.CHEST, side, 2, 0, 0, 0, 18, 68, 194, 15, 18);
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

    /**
     * Move tab to the default coordinate position
     */
    @Override
    protected void resetTabGUICoordinates()
    {
        int xOffset = defaultGUIX;
        int yOffset = defaultGUIY;
        if (getTabState() == TabState.CLOSED)
        {
            xOffset = xOffset + DOUBLE_TAB_WIDTH - getMinTabSizeX();
            yOffset = yOffset + getMinTabSizeY();
        } else if (getTabState() == TabState.OPEN)
        {
            if (getType() == TabType.CHEST)
            {
                xOffset = xOffset + DOUBLE_TAB_WIDTH - SINGLE_TAB_WIDTH;
            }
            yOffset = yOffset + getMinTabSizeY();
        }
        setTabGUICoordinates(xOffset, yOffset);
    }
}
