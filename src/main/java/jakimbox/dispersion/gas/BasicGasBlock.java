package jakimbox.dispersion.gas;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jakimbox.JakimBox;
import jakimbox.prefab.block.BasicBlock;
import jakimbox.proxy.CommonProxyBase;
import jakimbox.reference.Naming;
import jakimbox.registry.CreativeTabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 *
 * @author jakimfett
 */
public class BasicGasBlock extends BasicBlock implements ITileEntityProvider
{

    public BasicGasBlock()
    {
        super(JakimBox.modID, Naming.basicGas, Material.glass, Block.soundTypeSnow);
        this.setCreativeTab(CreativeTabRegistry.TAB_JAKIMBOX);
        this.setBlockUnbreakable();
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
     * Disable player collision
     *
     * @param world
     * @param x
     * @param y
     * @param z
     * @return null
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    /**
     * Inflict effects on anything moving through the gas
     *
     * @param world
     * @param x
     * @param y
     * @param z
     * @param entity
     */
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity != null && entity instanceof EntityLivingBase)
        {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (tileEntity instanceof BasicGasTileEntity)
            {
                ((BasicGasTileEntity) tileEntity).applyGasEffects((EntityLivingBase) entity);
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new BasicGasTileEntity();
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

    /**
     * Set pass for block rendering.
     *
     * @return int 1
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }
}
