package jakimbox.prefab.container;

import jakimbox.prefab.tileEntity.BasicInventoryTileEntity;
import jakimbox.prefab.tileEntity.TabbedInventoryTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 *
 * Minimalistic container for inventories
 *
 * @author jakimfett
 */
public abstract class BasicContainer extends Container
{
    /**
     * Starting x/y offsets for the player's inventory slots
     */
    private int[] offsets =
    {
        0, 0
    };

    /**
     * Height of the slots, shouldn't ever change.
     */
    private final int slotHeight = 18;

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
    public BasicContainer(InventoryPlayer inventoryPlayer, TabbedInventoryTileEntity tileEntity)
    {
        this.tileEntity = tileEntity;

        bindPlayerInventory(inventoryPlayer);
    }

    /**
     * Add the player's inventory slots to the GUI
     *
     * @param inventoryPlayer the player's inventory
     */
    private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        int slotIndex, x, y;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                slotIndex = j + i * 9 + 9;
                x = offsets[0] + j * slotHeight;
                y = offsets[1] + i * slotHeight;
                addSlotToContainer(new Slot(inventoryPlayer, slotIndex, x, y));
                if (i == 0)
                {

                    x = offsets[0] + j * slotHeight;
                    int hotbarOffset = 4;
                    int hotbarMultiplier = 3;
                    y = offsets[1] + hotbarMultiplier * slotHeight + hotbarOffset;
                    addSlotToContainer(new Slot(inventoryPlayer, j, x, y));
                }
            }
        }
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

    /**
     * Set the starting offset for the player's inventory slots
     *
     * @param x
     * @param y
     */
    public void setStartingOffsets(int x, int y)
    {
        offsets[0] = x;
        offsets[1] = y;
    }

    /**
     * Shift click transfer mechanic
     *
     * @param player the player object
     * @param slotID int ID of the slot
     * @return itemstack from the slot
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        // @TODO - implement shift clicking
        return null;
    }
}
