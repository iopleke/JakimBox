package jakimbox.box;

import jakimbox.JakimBox;
import jakimbox.prefab.block.BasicBlock;
import jakimbox.proxy.CommonProxyBase;
import jakimbox.reference.Naming;
import jakimbox.registry.CreativeTabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 *
 * @author jakimfett
 */
public class BoxBlock extends BasicBlock implements ITileEntityProvider
{

    public BoxBlock()
    {
        super(JakimBox.modID, Naming.box, Material.cloth, Block.soundTypeCloth);
        setCreativeTab(CreativeTabRegistry.TAB_JAKIMBOX);
        setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 1F, 0.9F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new BoxTileEntity();
    }

    /**
     * Get the render type
     *
     * @return render ID from the CommonProxy
     */
    @Override
    public int getRenderType()
    {
        return CommonProxyBase.RENDER_ID;
    }

    /**
     * Disable opaque cube rendering
     *
     * @return false
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Disable normal block rendering
     *
     * @return false
     */
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
