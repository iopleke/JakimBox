package jakimbox.prefab.gui.tabTypes;

import jakimbox.prefab.gui.BasicTabbedGUI;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.Tabs.TabType;
import jakimbox.reference.RelativeDirection;

/**
 *
 * @author jakimfett
 */
public class AnvilTab extends AbstractTab
{

    public AnvilTab(String modID, TabSide side, RelativeDirection direction)
    {
        super(modID, // Used to set the texture
            TabType.ANVIL, // Tab type
            side, // Side, passed in from object construction
            direction, // Direction, relative to the block
            0, // Texture coordinate for open (x)
            18, // Texture coordinate for open (y)
            100, // Maximum tab width, used to reset the tab
            82 // Maximum tab height, used to reset the tab
        );
    }

    @Override
    protected void renderOpenTabForSide(BasicTabbedGUI gui, TabSide side)
    {
        if (side == TabSide.LEFT)
        {
            gui.drawTexturedModalRect(guiCoords[0] - invWidth, gui.height / 2 + ((BasicTabbedGUI) gui).getTextureHeight() / 2 - getInvHeight() - getInvHeight(), invTextureX, invTextureY, getInvWidth(), getInvHeight());
        } else
        {
            gui.drawTexturedModalRect(guiCoords[0] + getIconWidth(), gui.height / 2 + ((BasicTabbedGUI) gui).getTextureHeight() / 2 - getInvHeight() - getInvHeight(), invTextureX, invTextureY, getInvWidth(), getInvHeight());
        }
    }

    @Override
    protected void renderTabTransition(BasicTabbedGUI gui, TabSide side)
    {
        if (side == TabSide.LEFT)
        {
            gui.drawTexturedModalRect(guiCoords[0] - 3, guiCoords[1], 54, 0, 3, getIconHeight());
        } else
        {
            gui.drawTexturedModalRect(guiCoords[0] + getIconWidth(), guiCoords[1], 57, iconTextureY, 3, getIconHeight());
        }
    }
}
