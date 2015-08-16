package jakimbox.prefab.gui;

import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.tileEntity.TabbedInventoryTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

/**
 * Basic tabbed GUI class
 *
 * @author jakimfett
 */
public abstract class BasicTabbedGUI extends BasicGUI
{

    /**
     * List of tabs for the GUI
     */
    protected Tabs tabs;

    /**
     * Texture height for centering calculations
     */
    protected int textureHeight;

    /**
     * Texture width for centering calculations
     */
    protected int textureWidth;

    /**
     * Overlap adjustment based on texture
     */
    public int tabWidthOverlap = 24;

    /**
     * Creates a tabbed GUI object
     *
     * @param modID
     * @param inventoryPlayer
     * @param tileEntity
     * @param world
     * @param tabCount
     */
    public BasicTabbedGUI(String modID, InventoryPlayer inventoryPlayer, TabbedInventoryTileEntity tileEntity, World world, int tabCount)
    {
        super(modID, inventoryPlayer, tileEntity, world, 0, 0);

        textureWidth = 256;
        textureHeight = 256;

        tabs = new Tabs(tabCount);

        xSize = textureWidth + (tabs.getTabsWidth() - tabWidthOverlap);
        ySize = textureHeight;
    }

    /**
     * Adds GUI offset calculation and tab rendering to super method
     *
     * @param opacity
     * @param mouseX
     * @param mouseY
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mouseX, int mouseY)
    {
        super.drawGuiContainerBackgroundLayer(opacity, mouseX, mouseY);

        updateGUIOffsets();
        tabs.renderTabs(this);
    }

    /**
     * Mouse click listener for tabs
     *
     * @param clickX
     * @param clickY
     * @param button
     */
    @Override
    protected void mouseClicked(int clickX, int clickY, int button)
    {
        super.mouseClicked(clickX, clickY, button);

        TabSide sideClicked;
        if (clickX < this.width / 2)
        {
            sideClicked = TabSide.LEFT;
        } else
        {
            sideClicked = TabSide.RIGHT;
        }

        tabs.doTabClicks(clickX, clickY, sideClicked);
    }

    /**
     * Calculate where the tabs should be rendered
     */
    protected void updateGUIOffsets()
    {
        int xCoordOffset = width / 2 - textureWidth / 2 + tabWidthOverlap - Tabs.iconWidth;
        int yCoordOffset = height / 2 + textureHeight / 2;
        tabs.setDefaultGUICoordinates(xCoordOffset, yCoordOffset, textureWidth - tabWidthOverlap * 2 + 1);
    }

    /**
     * Returns the saved texture height
     *
     * @return
     */
    public int getTextureHeight()
    {
        return textureHeight;
    }

    /**
     * Returns the saved texture width
     *
     * @return
     */
    public int getTextureWidth()
    {
        return textureWidth;
    }
}
