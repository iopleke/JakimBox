package jakimbox.prefab.gui.tabTypes;

import jakimbox.helper.LogHelper;
import jakimbox.prefab.gui.Tabs;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.Tabs.TabState;
import jakimbox.prefab.gui.Tabs.TabType;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import werkbench.bench.BenchGUI;

/**
 *
 * @author jakimfett
 */
public abstract class AbstractTab
{

    /**
     * Texture coordinates for the tab inventory.
     */
    protected int invTextureX, invTextureY;

    /**
     * Texture coordinates for the tab icon
     */
    protected int iconTextureX, iconTextureY;

    /**
     * Zero point GUI coordinates of the tab.
     */
    protected int[] guiCoords;

    /**
     * Maximum dimensions for the tab
     */
    protected int invWidth, invHeight;

    /**
     * Which side of the GUI is the tab positioned
     */
    private final TabSide side;

    /**
     * Whether the tab is closed, open, or somewhere in between
     */
    private TabState state;

    /**
     * Default coordinates for rendering a tab
     */
    protected int defaultGUIX, defaultGUIY;

    /**
     * Texture location for the background
     */
    protected final ResourceLocation tabBackground;

    /**
     * Type of tab, potentially unnecessary since each type extends the abstract tab
     */
    public final TabType type;

    /**
     * Generalized tab object, should be extended to make various tab types
     *
     * @param modID       ID for the mod in question Background graphic resource
     * @param type        Type for the tab
     * @param side        Which side of the GUI is the tab on, from Enum
     * @param invTextureX texture X coordinate default for an open tab
     * @param invTextureY texture Y coordinate default for an open tab
     * @param invWidth    maximum tab width
     * @param invHeight   maximum tab width
     */
    public AbstractTab(String modID, TabType type, TabSide side, int invTextureX, int invTextureY, int invWidth, int invHeight)
    {
        this.tabBackground = new ResourceLocation(modID, "textures/gui/" + type.toString().toLowerCase().replace("_", "").replace("single", "").replace("double", "").replace("ender", "") + "TabBackground.png");
        this.type = type;
        this.side = side;
        this.invTextureX = invTextureX;
        this.invTextureY = invTextureY;
        this.invWidth = invWidth;
        this.invHeight = invHeight;
        iconTextureX = 0;
        iconTextureY = 0;

        guiCoords = new int[2];

        reset();
    }

    /**
     * Set tab state
     *
     * @param state closed, closing, open, opening
     */
    private void setTabState(TabState state)
    {
        this.state = state;
    }

    /**
     * Move tab to the default coordinate position
     */
    protected void resetTabGUICoordinates()
    {
        setTabGUICoordinates(defaultGUIX, defaultGUIY);
    }

    /**
     * Set GUI position for the tab
     *
     * @param x
     * @param y
     */
    protected void setTabGUICoordinates(int x, int y)
    {
        guiCoords[0] = x;
        guiCoords[1] = y;
    }

    /**
     * Check if an x/y coordinate intersects with the tab
     *
     * @param x
     * @param y
     * @return boolean
     */
    public boolean coordinateIntersect(int x, int y)
    {
        if (x >= guiCoords[0] && x <= guiCoords[0] + getIconWidth())
        {
            if (y >= guiCoords[1] && y <= guiCoords[1] + getIconHeight())
            {
                return true;
            }
        }
        return false;
    }

    public int getInvWidth()
    {
        return invWidth;
    }

    public int getInvHeight()
    {
        return invHeight;
    }

    public int getIconWidth()
    {
        return Tabs.iconWidth;
    }

    public int getIconHeight()
    {
        return Tabs.iconHeight;
    }

    public TabSide getTabSide()
    {
        return side;
    }

    public TabState getTabState()
    {
        return state;
    }

    /**
     * Get the tab type
     *
     * @return TabType
     */
    public TabType getType()
    {
        return type;
    }

    public void toggleTabState()
    {
        if (state == TabState.CLOSED)
        {
            setTabState(TabState.OPEN);
        } else
        {
            setTabState(TabState.CLOSED);
        }
    }

    /**
     * Render the tab and return coordinates for rendering
     *
     * @param gui a GuiContainer for rendering the visuals
     */
    public void renderTab(GuiContainer gui)
    {
        if (gui instanceof BenchGUI)
        {
            gui.mc.renderEngine.bindTexture(tabBackground);

            if (side == TabSide.LEFT)
            {
                renderLeftTab((BenchGUI) gui);
            } else if (side == TabSide.RIGHT)
            {
                renderRightTab((BenchGUI) gui);
            }
        }
    }

    private void renderRightTab(BenchGUI gui)
    {
        if (state == TabState.CLOSED)
        {
            gui.drawTexturedModalRect(guiCoords[0], guiCoords[1], iconTextureX + 3, iconTextureY, getIconWidth(), getIconHeight());
        } else
        {
            renderOpenTabForSide(gui, TabSide.RIGHT);
            gui.drawTexturedModalRect(guiCoords[0], guiCoords[1], iconTextureX, iconTextureY, getIconWidth(), getIconHeight());
            renderTabTransition(gui, side);
        }
    }

    /**
     * Render a graphical transition between tab icon and tab
     *
     * @param gui
     * @param side
     */
    protected void renderTabTransition(BenchGUI gui, TabSide side)
    {
    }

    /**
     * Render an open tab
     *
     * @param gui
     * @param side
     */
    protected abstract void renderOpenTabForSide(BenchGUI gui, TabSide side);

    private void renderLeftTab(BenchGUI gui)
    {
        if (state == TabState.CLOSED)
        {
            gui.drawTexturedModalRect(guiCoords[0], guiCoords[1], iconTextureX, iconTextureY, getIconWidth(), getIconHeight());
        } else
        {
            renderOpenTabForSide(gui, TabSide.LEFT);
            gui.drawTexturedModalRect(guiCoords[0], guiCoords[1], iconTextureX + 3, iconTextureY, getIconWidth(), getIconHeight());
            renderTabTransition(gui, side);
        }
    }

    /**
     * Do a hard reset of the tab
     */
    public final void reset()
    {
        setTabState(TabState.CLOSED);
        resetTabGUICoordinates();
    }

    /**
     * Set the GUI offsets to the tab, used when screen resizes
     *
     * @param x
     * @param y
     */
    public void setDefaultGUICoordinates(int x, int y)
    {
        if (defaultGUIX != x || defaultGUIY != y)
        {
            defaultGUIX = x;
            defaultGUIY = y;
            resetTabGUICoordinates();
        }
    }

    public List<String> tooltipForTab(int mouseX, int mouseY)
    {
        LogHelper.debug("Tab on " + getTabSide().toString() + " is hovered!");
        List<String> toolTipText = new ArrayList<String>();
        toolTipText.add("type: " + type.toString());
        toolTipText.add("side: " + side.toString());
        return toolTipText;
    }
}
