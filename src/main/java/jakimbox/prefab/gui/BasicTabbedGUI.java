package jakimbox.prefab.gui;

import jakimbox.Config;
import jakimbox.prefab.container.BasicContainer;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.tabTypes.AnvilTab;
import jakimbox.prefab.gui.tabTypes.ChestTab;
import jakimbox.prefab.gui.tabTypes.FurnaceTab;
import jakimbox.prefab.tileEntity.TabbedInventoryTileEntity;
import jakimbox.reference.RelativeDirection;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Basic tabbed GUI class
 *
 * @author jakimfett
 */
public abstract class BasicTabbedGUI extends BasicGUI
{
    /**
     * Counter for updating the tabs.
     */
    private int tickCounter;

    /**
     * TileEntity object
     */
    private TabbedInventoryTileEntity tileEntity;

    /**
     * Mod ID of the mod using the tabs
     */
    protected String modID;

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
     * @param container
     * @param tileEntity
     * @param world
     * @param tabCount
     */
    public BasicTabbedGUI(String modID, BasicContainer container, TabbedInventoryTileEntity tileEntity, World world, int tabCount)
    {
        super(modID, container, tileEntity, world, 0, 0);
        this.modID = modID;
        this.tileEntity = tileEntity;

        textureWidth = 256;
        textureHeight = 256;

        tabs = new Tabs(tabCount);

        xSize = textureWidth + (tabs.getTabsWidth() - tabWidthOverlap);
        ySize = textureHeight;

        // Update the tabs within the first 1/2 second after the GUI loads
        tickCounter = 10;
    }

    /**
     * Decrease the tick counter by one.
     */
    private void decrementTickCounter()
    {
        tickCounter--;
    }

    /**
     * Reset the tick counter to a random between 0 and the max update count.
     */
    private void resetTickCounter()
    {
        tickCounter = new Random().nextInt(Config.maxUpdateTickCount);
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

        if (tickCounter <= 0)
        {
            updateTabState();
            resetTickCounter();
        } else
        {
            decrementTickCounter();
        }

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
        tabs.doTabClicks(this, clickX, clickY, sideClicked);
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
     * Update the tabs based on the block cache
     */
    protected void updateTabState()
    {
        int tabID = 0;
        for (Map.Entry<RelativeDirection, Block> entry : tileEntity.getBlockCache().entrySet())
        {
            if (tabs.getTab(tabID) == null || tabs.getTab(tabID).type != Tabs.getTabTypeFromBlock(entry.getValue()))
            {
                if (entry.getValue() == Blocks.chest)
                {
                    // @TODO - add logic to check for double chests
                    tabs.addTab(new ChestTab(modID, RelativeDirection.getRelativeDirectionTabSide(entry.getKey()), Tabs.TabType.CHEST_SINGLE, entry.getKey()), tabID);
                } else if (entry.getValue() == Blocks.ender_chest)
                {
                    tabs.addTab(new ChestTab(modID, RelativeDirection.getRelativeDirectionTabSide(entry.getKey()), Tabs.TabType.CHEST_ENDER, entry.getKey()), tabID);
                } else if (entry.getValue() == Blocks.furnace)
                {
                    tabs.addTab(new FurnaceTab(modID, RelativeDirection.getRelativeDirectionTabSide(entry.getKey()), entry.getKey()), tabID);
                } else if (entry.getValue() == Blocks.anvil)
                {
                    tabs.addTab(new AnvilTab(modID, RelativeDirection.getRelativeDirectionTabSide(entry.getKey()), entry.getKey()), tabID);
                } else if (tabs.getTab(tabID) != null)
                {
                    tabs.removeTab(tabID);
                }
            }
            tabID++;
        }
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
