package jakimbox.prefab.container;

import jakimbox.prefab.gui.Tabs;
import jakimbox.prefab.tileEntity.BasicInventoryTileEntity;
import jakimbox.reference.RelativeDirection;
import java.util.EnumMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 *
 * Minimalistic container for inventories
 *
 * @author jakimfett
 */
public abstract class BasicInventoryContainer extends BasicContainer
{

    protected final Map<RelativeDirection, int[]> slotIDs = new EnumMap<RelativeDirection, int[]>(RelativeDirection.class);

    /**
     * Stored TileEntity object.
     */
    private BasicInventoryTileEntity tileEntity;

    /**
     * Container object for the TileEntity
     *
     * @param inventoryPlayer the player's inventory
     * @param tileEntity
     */
    public BasicInventoryContainer(InventoryPlayer inventoryPlayer, BasicInventoryTileEntity tileEntity)
    {
        super(inventoryPlayer, tileEntity);
        this.tileEntity = tileEntity;

    }

    /**
     * Determine if the player can interact with the container
     *
     * @param entityPlayer the player entity
     * @return boolean
     */
    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return tileEntity.isUseableByPlayer(entityPlayer);
    }

    public void moveBoundSlots(RelativeDirection direction, Tabs.TabSide side)
    {
        int tabSlots[] = slotIDs.get(direction);
        if (tabSlots != null)
        {
            int count = 0;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    if (side == Tabs.TabSide.LEFT)
                    {
                        ((Slot) this.inventorySlots.get(tabSlots[count])).xDisplayPosition = i * 18 - 1;
                    } else
                    {
                        ((Slot) this.inventorySlots.get(tabSlots[count])).xDisplayPosition = 270 + i * 18;
                    }

                    ((Slot) this.inventorySlots.get(tabSlots[count])).yDisplayPosition = 73 + j * 18;
                    count++;

                }
            }
        }
    }

    public void resetBoundSlots(RelativeDirection direction)
    {
        int tabSlots[] = slotIDs.get(direction);
        if (tabSlots != null)
        {
            int count = 0;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    ((Slot) this.inventorySlots.get(tabSlots[count])).xDisplayPosition = -999;
                    ((Slot) this.inventorySlots.get(tabSlots[count])).yDisplayPosition = -999;
                    count++;
                }
            }
        }
    }
}
