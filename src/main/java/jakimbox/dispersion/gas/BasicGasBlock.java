package jakimbox.dispersion.gas;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jakimbox.JakimBox;
import jakimbox.helper.LogHelper;
import jakimbox.prefab.block.BasicBlock;
import jakimbox.proxy.CommonProxyBase;
import jakimbox.reference.Naming;
import jakimbox.registry.CreativeTabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 *
 * @author jakimfett
 */
public class BasicGasBlock extends BasicBlock implements ITileEntityProvider
{

    @SideOnly(Side.CLIENT)
    protected IIcon[] blockIconArray = new IIcon[16];

    public BasicGasBlock()
    {
        super(JakimBox.modID, Naming.basicGas, Material.glass, Block.soundTypeSnow);
        this.setCreativeTab(CreativeTabRegistry.TAB_JAKIMBOX);
        this.setBlockUnbreakable();
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new BasicGasTileEntity();
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
     * Returns block texture based on meta
     *
     * @param side
     * @param meta
     * @return IIcon
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return blockIconArray[meta];
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
     * Temporary debugging code, increments meta on right click
     *
     * @param world
     * @param x
     * @param y
     * @param z
     * @param player
     * @param side
     * @param hitX
     * @param hitY
     * @param hitZ
     * @return
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof BasicGasTileEntity)
        {
            int meta = world.getBlockMetadata(x, y, z);
            LogHelper.info("Block meta is " + meta);
        }
        return false;
    }

    /**
     * Set block metadata for model rotation
     *
     * @param world        the world object
     * @param x            world X coordinate of placed block
     * @param y            world Y coordinate of placed block
     * @param z            world Z coordinate of placed block
     * @param livingEntity the entity that placed the block
     * @param itemStack    ItemStack object used to place the block
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingEntity, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, livingEntity, itemStack);
        world.setBlockMetadataWithNotify(x, y, z, 15, 2);
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

    /**
     * Register the block icons from the texture name
     *
     * @param iconRegister
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < 16; i++)
        {
            blockIconArray[i] = iconRegister.registerIcon(textureName + i);
        }
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
