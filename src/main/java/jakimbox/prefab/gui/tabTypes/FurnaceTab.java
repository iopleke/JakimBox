package jakimbox.prefab.gui.tabTypes;

import jakimbox.Config;
import jakimbox.prefab.gui.BasicTabbedGUI;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.Tabs.TabType;

/**
 *
 * @author jakimfett
 */
public class FurnaceTab extends AbstractTab
{

    private boolean doFurnaceUpdate;
    private int tickCount;

    public FurnaceTab(String modID, TabSide side)
    {
        super(modID, // Used to set the texture
            TabType.FURNACE, // Tab type
            side, // Side, passed in from object construction
            0, // Texture coordinate for open (x)
            18, // Texture coordinate for open (y)
            76, // Maximum tab width, used to reset the tab
            76 // Maximum tab height, used to reset the tab
        );
        resetTickCount();
    }

    private void incrementTickCount()
    {
        tickCount++;
        if (tickCount >= Config.furnaceGUIUpdatePacketFrequency)
        {
            resetTickCount();
        }
    }

    private void resetTickCount()
    {
        tickCount = 0;
        doFurnaceUpdate = true;
    }

    @Override
    protected void renderOpenTabForSide(BasicTabbedGUI gui, TabSide side)
    {
        if (side == TabSide.LEFT)
        {
            gui.drawTexturedModalRect(guiCoords[0] - invWidth, gui.height / 2 - getInvHeight() / 2 - getIconHeight() + 3, invTextureX, invTextureY, getInvWidth(), getInvHeight());
        } else
        {
            gui.drawTexturedModalRect(guiCoords[0] + getIconWidth(), gui.height / 2 - getInvHeight() / 2 - getIconHeight() + 3, invTextureX, invTextureY, getInvWidth(), getInvHeight());
        }
    }

    @Override
    protected void renderTabTransition(BasicTabbedGUI gui, TabSide side)
    {
        if (side == TabSide.LEFT)
        {
            gui.drawTexturedModalRect(guiCoords[0] - 3, guiCoords[1], 21, 0, 3, getIconHeight());
        } else
        {
            gui.drawTexturedModalRect(guiCoords[0] + getIconWidth(), guiCoords[1], 18, iconTextureY, 3, getIconHeight());
        }
    }

    @Override
    public void renderTab(BasicTabbedGUI gui)
    {
        super.renderTab(gui);

        if (gui instanceof BasicTabbedGUI)
        {
            incrementTickCount();

            if (doFurnaceUpdate)
            {
                // need to refactor the method for determining
                //sendFurnaceGUIUpdateRequest(getTabSide());
            }

        }

//        renderFurnaceProgressBars(side, xOffset + guiOffsets[0], yOffset + guiOffsets[1]);
//        renderFurnaceForeground(xOffset + guiOffsets[0], yOffset + guiOffsets[1]);
    }

//    private void renderFurnaceForeground(int x, int y)
//    {
//        //drawTexturedModalRect(x, y, 0, 0, 76, 76);
//    }
//
//    private void renderFurnaceProgressBars(Compendium.RelativeBenchSide side, int x, int y)
//    {
//        int[] furnaceSideValues = bench.getFurnaceValuesForSide(side);
//        int cookProgress = 0;
//        int burnLevel = 0;
//        if (furnaceSideValues != null)
//        {
//            if (furnaceSideValues[0] > 0 && furnaceSideValues[2] > 0)
//            {
//                burnLevel = (furnaceSideValues[0] * 29 / furnaceSideValues[2]);
//            }
//            if (furnaceSideValues[1] > 0)
//            {
//                cookProgress = (furnaceSideValues[1] * 22 / 200);
//            }
//        }
//        drawTexturedModalRect(x, y, 0, 105, 76, 76);
//        if (SpatialHelper.getBlockForRelativeSide(bench, side) == Compendium.AdjacentBlockType.FURNACE_ACTIVE)
//        {
//            if (burnLevel != 0)
//            {
//                drawTexturedModalRect(x, y + 76 - burnLevel, 0, 76 + 14 - burnLevel / 2, 76, burnLevel);
//            }
//            if (cookProgress != 0)
//            {
//                drawTexturedModalRect(x + 26, y + 12, 76, 0, cookProgress, 15);
//            }
//        }
//
//        drawTexturedModalRect(x, y, 0, 0, 76, 76);
//    }
//    private void sendFurnaceGUIUpdateRequest(TabSide side)
//    {
//        TileEntity tileEntity = SpatialHelper..getTileEntityForRelativeSide(bench, side);
//        if (tileEntity instanceof TileEntityFurnace)
//        {
//            MessageHandler.INSTANCE.sendToServer(new FurnaceUpdateRequestMessage(((TileEntityFurnace) tileEntity), bench, side));
//        }
//    }
}
