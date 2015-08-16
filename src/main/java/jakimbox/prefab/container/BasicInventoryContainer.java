package jakimbox.prefab.container;

import jakimbox.prefab.tileEntity.BasicInventoryTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

/**
 *
 * Minimalistic container for inventories
 *
 * @author jakimfett
 */
public abstract class BasicInventoryContainer extends BasicContainer
{

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
}
