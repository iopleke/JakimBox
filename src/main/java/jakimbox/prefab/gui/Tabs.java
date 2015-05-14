package jakimbox.prefab.gui;

import jakimbox.helper.LogHelper;
import jakimbox.prefab.gui.tabTypes.AbstractTab;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;

/**
 * Class for managing multiple tabs
 *
 * @author jakimfett
 */
public class Tabs
{
    /**
     * List of tabs contained in the object
     */
    private AbstractTab[] tabList;

    /**
     * Basic constructor
     *
     * @param count defines total number of possible tabs
     */
    public Tabs(int count)
    {
        tabList = new AbstractTab[count];
    }

    /**
     * Add a tab to the list
     *
     * @param tab Tab object
     * @param id  position in the tab list
     */
    public void addTab(AbstractTab tab, int id)
    {
        tabList[id] = tab;
    }

    /**
     * Get a tab at a specific place on the list
     *
     * @param id tab list ID
     * @return Tab object (or NULL)
     */
    public AbstractTab getTab(int id)
    {
        return tabList[id];
    }

    /**
     * Render all the tabs in the list
     *
     * @param gui pass in the GUI object for rendering
     */
    public void renderTabs(GuiContainer gui)
    {
        for (AbstractTab tab : tabList)
        {
            if (tab != null)
            {
                tab.renderTab(gui);
            }
        }
    }

    /**
     * Check if a mouse click activates any of the tabs
     *
     * @param clickX
     * @param clickY
     */
    public void doTabClicks(int clickX, int clickY)
    {
        LogHelper.debug("Clicked at x:" + clickX + " y:" + clickY);

        for (AbstractTab tab : tabList)
        {
            ListLoop:
            if (tab != null)
            {
                if (tab.coordinateIntersect(clickX, clickY))
                {
                    LogHelper.debug("Tab on " + tab.getTabSide().toString() + " was clicked!");
                    tab.initializeTabAnimation();
                    break ListLoop;
                }
            }
        }
    }

    /**
     * Get tooltips for tab mouseovers
     *
     * @param mouseX
     * @param mouseY
     */
    public List<String> getTabToolTip(int mouseX, int mouseY)
    {
        for (AbstractTab tab : tabList)
        {
            ListLoop:
            if (tab != null)
            {
                if (tab.coordinateIntersect(mouseX, mouseY) && tab.getTabState() == TabState.CLOSED)
                {
                    return tab.tooltipForTab(mouseX, mouseY);
                }
            }
        }
        return null;
    }

    public static enum TabState
    {
        CLOSED, CLOSING, OPEN, OPENING;

    }

    public static enum TabType
    {
        BREWING_STAND, CHEST, CHEST_DOUBLE, CHEST_ENDER, ENCHANTING_TABLE, FURNACE, PATREON
    }

    public static enum TabSide
    {
        LEFT, RIGHT
    }
}
