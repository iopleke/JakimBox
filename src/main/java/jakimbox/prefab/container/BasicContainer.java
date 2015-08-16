package jakimbox.prefab.container;

import jakimbox.prefab.tileEntity.BasicTileEntity;
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
public class BasicContainer extends Container
{
    /**
     * Starting x/y offsets for the player's inventory slots
     */
    private int[] offsets =
    {
        32, 166
    };

    /**
     * Saved slot index for adding/removing slots
     */
    private int slotIndex = 0;

    public static int playerInventoryIndex = 36;

    /**
     * Height of the slots, shouldn't ever change.
     */
    private final int slotHeight = 18;

    /**
     * Stored TileEntity object.
     */
    private BasicTileEntity tileEntity;

    /**
     * Container object for the TileEntity
     *
     * @param inventoryPlayer the player's inventory
     * @param tileEntity
     */
    public BasicContainer(InventoryPlayer inventoryPlayer, BasicTileEntity tileEntity)
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
        int x, y;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                x = offsets[0] + j * slotHeight;
                y = offsets[1] + i * slotHeight;
                addSlotToContainer(new Slot(inventoryPlayer, slotIndex++, x, y));
                if (i == 0)
                {
                    x = offsets[0] + j * slotHeight;
                    int hotbarOffset = 4;
                    int hotbarMultiplier = 3;
                    y = offsets[1] + hotbarMultiplier * slotHeight + hotbarOffset;
                    addSlotToContainer(new Slot(inventoryPlayer, slotIndex++, x, y));
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
        return true;
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
