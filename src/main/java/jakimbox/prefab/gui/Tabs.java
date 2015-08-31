package jakimbox.prefab.gui;

import jakimbox.helper.LogHelper;
import jakimbox.prefab.container.BasicInventoryContainer;
import jakimbox.prefab.gui.tabTypes.AbstractTab;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * Class for managing multiple tabs
 *
 * @author jakimfett
 */
public class Tabs
{
    public static final int iconHeight = 18;
    public static final int iconWidth = 15;

    /**
     * List of tabs contained in the object
     */
    private AbstractTab[] tabList;

    /**
     * Offset widths for tabs, index 0 for left side, 1 for right side
     */
    private int tabOffsetCache[];

    /**
     * Basic constructor
     *
     * @param count defines total number of possible tabs
     */
    public Tabs(int count)
    {
        tabList = new AbstractTab[count];

        tabOffsetCache = new int[2];
        tabOffsetCache[0] = 0;
        tabOffsetCache[1] = 0;
    }

    /**
     * Check if a given tab is wider than current cached max
     *
     * @param id
     */
    private void updateOffsetCache(int id)
    {
        int side;
        if (tabList[id].getTabSide() == TabSide.LEFT)
        {
            side = 0;
        } else
        {
            side = 1;
        }
        if (tabList[id].getInvWidth() > tabOffsetCache[side])
        {
            tabOffsetCache[side] = tabList[id].getInvWidth();
        }
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

        updateOffsetCache(id);
    }

    /**
     * Check if a mouse click activates any of the tabs
     *
     * @param gui
     * @param clickX
     * @param clickY
     * @param side
     */
    public void doTabClicks(BasicTabbedGUI gui, int clickX, int clickY, TabSide side)
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
                    tab.toggleTabState();

                    if (tab.getTabState() == TabState.OPEN)
                    {
                        ((BasicInventoryContainer) gui.inventorySlots).moveBoundSlots(tab.getRelativeDirection(), tab.getTabSide());
                    } else
                    {
                        ((BasicInventoryContainer) gui.inventorySlots).resetBoundSlots(tab.getRelativeDirection());
                    }
                } else if (tab.sideIntersect(clickX, clickY))
                {
                    // @TODO - restrict this to only reset tabs if click is on a different tab
                    if (tab.getTabState() == TabState.OPEN)
                    {
                        tab.reset();
                        ((BasicInventoryContainer) gui.inventorySlots).resetBoundSlots(tab.getRelativeDirection());
                    }
                }
            }
        }
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
     * Get tooltips for tab mouseovers
     *
     * @param mouseX
     * @param mouseY
     * @return text for tooltip
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

    /**
     * Get the combined left and right max widths
     *
     * @return
     */
    public int getTabsWidth()
    {
        int size = 122;
        for (AbstractTab tab : tabList)
        {
            ListLoop:
            if (tab != null)
            {
                if (tab.getInvWidth() > size)
                {
                    size = tab.getInvWidth();
                }
            }
        }
        return size;
    }

    /**
     * Set the tab at a given index to null
     *
     * @param id
     */
    public void removeTab(int id)
    {
        tabList[id] = null;
    }

    /**
     * Render all the tabs in the list
     *
     * @param gui pass in the GUI object for rendering
     */
    public void renderTabs(BasicTabbedGUI gui)
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
     * Pass GUI offsets to the tabs, used when screen resizes
     *
     * @param x
     * @param y
     * @param guiWidth
     */
    public void setDefaultGUICoordinates(int x, int y, int guiWidth)
    {
        int offsetL = 0;
        int offsetR = 0;
        for (AbstractTab tab : tabList)
        {
            if (tab != null)
            {
                if (tab.getTabSide() == TabSide.RIGHT)
                {
                    tab.setDefaultGUICoordinates(x + guiWidth + tab.getIconWidth(), y + offsetR);
                    offsetR += 18;
                } else
                {
                    tab.setDefaultGUICoordinates(x, y + offsetL);
                    offsetL += 18;
                }
            }
        }
    }

    public static enum TabState
    {
        CLOSED, OPEN;
    }

    public static enum TabType
    {
        ANVIL, BREWING_STAND, CHEST_SINGLE, CHEST_DOUBLE, CHEST_ENDER, ENCHANTING_TABLE, FURNACE, HOPPER, PATREON
    }

    public static enum TabSide
    {
        LEFT, RIGHT
    }

    public static TabType getTabTypeFromBlock(Block block)
    {
        if (block == Blocks.anvil)
        {
            return TabType.ANVIL;
        }
        if (block == Blocks.chest)
        {
            return TabType.CHEST_SINGLE;
        }
        if (block == Blocks.ender_chest)
        {
            return TabType.CHEST_ENDER;
        }
        if (block == Blocks.enchanting_table)
        {
            return TabType.ENCHANTING_TABLE;
        }
        if (block == Blocks.furnace)
        {
            return TabType.FURNACE;
        }
        if (block == Blocks.hopper)
        {
            return TabType.HOPPER;
        }
        return null;
    }
}
