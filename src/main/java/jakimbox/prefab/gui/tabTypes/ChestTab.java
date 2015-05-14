package jakimbox.prefab.gui.tabTypes;

import jakimbox.JakimBox;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.Tabs.TabState;
import jakimbox.prefab.gui.Tabs.TabType;

/**
 *
 * @author jakimfett
 */
public class ChestTab extends AbstractTab
{

    public ChestTab(TabSide side)
    {
        super(JakimBox.modID, TabType.CHEST, side, 2, 0, 0, 0, 18, 68, 194, 15, 18);
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
