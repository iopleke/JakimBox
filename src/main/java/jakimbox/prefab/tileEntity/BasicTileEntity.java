package jakimbox.prefab.tileEntity;

import net.minecraft.tileentity.TileEntity;

public abstract class BasicTileEntity extends TileEntity
{
    public String name;

    public BasicTileEntity(String tileEntityName)
    {
        this.name = tileEntityName;
    }

    /**
     * Verify that the worldobject isn't null before returning the metadata
     *
     * @return int TileEntity metadata
     */
    @Override
    public int getBlockMetadata()
    {
        return worldObj != null ? super.getBlockMetadata() : 0;
    }

    @Override
    public abstract void updateEntity();
}
