package jakimbox.prefab.gui.tabTypes;

import jakimbox.helper.LogHelper;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.Tabs.TabState;
import jakimbox.prefab.gui.Tabs.TabType;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

/**
 *
 * @author jakimfett
 */
public abstract class AbstractTab
{
    private int animationSpeed;
    private int defaultGUIX;
    private int defaultGUIY;
    private final int defaultTextureClosedX;
    private final int defaultTextureClosedY;
    private final int defaultTextureOpenX;
    private final int defaultTextureOpenY;
    private int[] guiCoords;
    private final int maxX;
    private final int maxY;
    private final int minX;
    private final int minY;
    private final TabSide side;
    private int[] size;
    private TabState state;
    private int[] textureCoords;
    protected final ResourceLocation tabBackground;
    public final TabType type;

    /**
     * Generalized tab object, should be extended to make various tab types
     *
     * @param modID                 ID for the mod in question Background graphic resource
     * @param type                  Type for the tab
     * @param side                  Which side of the GUI is the tab on, from Enum
     * @param animationSpeed        how fast does the tab open and close
     * @param defaultTextureClosedX texture X coordinate default for a closed tab
     * @param defaultTextureClosedY texture Y coordinate default for a closed tab
     * @param defaultTextureOpenX   texture X coordinate default for an open tab
     * @param defaultTextureOpenY   texture Y coordinate default for an open tab
     * @param maxX                  maximum tab width
     * @param maxY                  maximum tab width
     * @param minX                  minimum tab width, should be 15
     * @param minY                  minimum tab width, should be 18
     */
    public AbstractTab(String modID, TabType type, TabSide side, int animationSpeed, int defaultTextureClosedX, int defaultTextureClosedY, int defaultTextureOpenX, int defaultTextureOpenY, int maxX, int maxY, int minX, int minY)
    {
        this.tabBackground = new ResourceLocation(modID, "textures/gui/" + type.toString() + "TabBackground.png");
        this.type = type;
        this.side = side;
        this.animationSpeed = animationSpeed;
        this.defaultTextureClosedX = defaultTextureClosedX;
        this.defaultTextureClosedY = defaultTextureClosedY;
        this.defaultTextureOpenX = defaultTextureOpenX;
        this.defaultTextureOpenY = defaultTextureOpenY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;

        reset();
    }

    /**
     * Process tab animation value changes
     */
    private void animateTab()
    {
        if (state == TabState.OPENING)
        {
            incrementTabValues();
        } else if (state == TabState.CLOSING)
        {
            decrementTabValues();
        }
    }

    private void decrementTabValues()
    {
        if (size[0] - animationSpeed * 2 > getMinTabSizeX())
        {
            size[0] = size[0] - animationSpeed * 2;
            setTabTextureCoordinates(textureCoords[0] + animationSpeed * 2, textureCoords[1]);
            setTabGUICoordinates(guiCoords[0] + animationSpeed * 2, guiCoords[1]);
        } else
        {
            size[0] = getMinTabSizeX();
        }
        if (size[1] - animationSpeed * 5 > getMinTabSizeY())
        {
            size[1] = size[1] - animationSpeed * 5;
        } else
        {
            size[1] = getMinTabSizeY();
        }
        if (size[0] <= getMinTabSizeX() && size[1] <= getMinTabSizeY())
        {
            setTabState(TabState.CLOSED);
            resetTabSize();
            resetTabTextureCoordinates();
            resetTabGUICoordinates();
        }
    }

    private void incrementTabValues()
    {
        if (size[0] + animationSpeed * 2 < getMaxTabSizeX())
        {
            size[0] = size[0] + animationSpeed * 2;
            setTabTextureCoordinates(textureCoords[0] - animationSpeed * 2, textureCoords[1]);
            setTabGUICoordinates(guiCoords[0] - animationSpeed * 2, guiCoords[1]);

        } else
        {
            size[0] = getMaxTabSizeX();
            setTabTextureCoordinates(defaultTextureOpenX, defaultTextureOpenY);
            resetTabGUICoordinates();
            setTabGUICoordinates(defaultGUIX, defaultGUIY);
        }
        if (size[1] + animationSpeed * 5 < getMaxTabSizeY())
        {
            size[1] = size[1] + animationSpeed * 5;
        } else
        {
            size[1] = getMaxTabSizeY();
        }

        if (size[0] >= getMaxTabSizeX() && size[1] >= getMaxTabSizeY())
        {
            setTabState(TabState.OPEN);
            resetTabSize();
            resetTabTextureCoordinates();
            resetTabGUICoordinates();
        }
    }

    private void resetTabGUICoordinates()
    {
        setTabGUICoordinates(defaultGUIX, defaultGUIY);
    }

    private void resetTabSize()
    {

        if (size == null)
        {
            size = new int[2];
        }
        if (state == TabState.CLOSED)
        {
            size[0] = getMinTabSizeX();
            size[1] = getMinTabSizeY();
        } else
        {
            size[0] = getMaxTabSizeX();
            size[1] = getMaxTabSizeY();
        }
    }

    private void resetTabTextureCoordinates()
    {
        if (state == TabState.CLOSED)
        {
            setTabTextureCoordinates(defaultTextureClosedX, defaultTextureClosedY);
        } else
        {
            setTabTextureCoordinates(defaultTextureOpenX, defaultTextureOpenY);
        }
    }

    private void setTabGUICoordinates(int x, int y)
    {
        guiCoords[0] = x;
        guiCoords[1] = y;
    }

    private void setTabState(TabState state)
    {
        this.state = state;
    }

    public boolean coordinateIntersect(int x, int y)
    {

        if (x >= guiCoords[0] && x <= guiCoords[0] + size[0])
        {
            if (y >= guiCoords[1] && y <= guiCoords[1] + size[1])
            {
                return true;
            }
        }
        return false;
    }

    public int getMaxTabSizeX()
    {
        return maxX;
    }

    public int getMaxTabSizeY()
    {
        return maxY;
    }

    public int getMinTabSizeX()
    {
        return minX;
    }

    public int getMinTabSizeY()
    {
        return minY;
    }

    public int[] getTabDimensions()
    {
        return size;
    }

    public TabSide getTabSide()
    {
        return side;
    }

    public TabState getTabState()
    {
        return state;
    }

    public void initializeTabAnimation()
    {
        if (state == TabState.CLOSED)
        {
            setTabState(TabState.OPENING);
        } else if (state == TabState.OPEN)
        {
            setTabState(TabState.CLOSING);
        }
    }

    /**
     * Render the tab and return coordinates for rendering
     *
     * @param gui a GuiContainer for rendering the visuals
     */
    public void renderTab(GuiContainer gui)
    {
        gui.mc.renderEngine.bindTexture(tabBackground);
        animateTab();
        gui.drawTexturedModalRect(guiCoords[0], guiCoords[1], textureCoords[0], textureCoords[1], size[0], size[1]);
    }

    /**
     * Do a hard reset of the tab
     */
    public final void reset()
    {
        setTabState(TabState.CLOSED);
        resetTabSize();
        resetTabTextureCoordinates();
        resetTabGUICoordinates();
    }

    public void setAnimationSpeed(int newAnimationSpeed)
    {
        animationSpeed = newAnimationSpeed;
    }

    /**
     * Set the GUI offsets to the tab, used when screen resizes
     *
     * @param x
     * @param y
     */
    public void setDefaultGUICoordinates(int x, int y)
    {
        defaultGUIX = x;
        defaultGUIY = y;
    }

    public void setTabTextureCoordinates(int x, int y)
    {
        textureCoords[0] = x;
        textureCoords[1] = y;
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
