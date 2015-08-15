package jakimbox.prefab.tileEntity;

import net.minecraft.tileentity.TileEntity;

/**
 *
 * Bare minimum TileEntity implementation
 *
 * @author jakimfett
 */
public abstract class BasicTileEntity extends TileEntity
{
    /**
     * Name for the TileEntity.
     */
    public String name;

    /**
     * Basic TileEntity
     *
     * @param tileEntityName
     */
    public BasicTileEntity(String tileEntityName)
    {
        this.name = tileEntityName;
    }

    /**
     * Verify that the worldobject isn't null before returning the metadata
     *
     * @return int
     */
    @Override
    public int getBlockMetadata()
    {
        if (worldObj != null)
        {
            return super.getBlockMetadata();
        }
        return 0;
    }

    /**
     * Main update loop.
     */
    @Override
    public abstract void updateEntity();
}
