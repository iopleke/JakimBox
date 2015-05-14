package jakimbox.prefab.gui;

import jakimbox.prefab.gui.tabTypes.AbstractTab;
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

}
