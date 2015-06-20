package jakimbox.dispersion.gas;

import jakimbox.prefab.tileEntity.BasicTileEntity;
import jakimbox.reference.Naming;
import jakimbox.registry.BlockRegistry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Basic TileEntity object for creating gasses
 *
 * @author jakimfett
 */
public class BasicGasTileEntity extends BasicTileEntity
{
    private static final int decrease = 1;
    private static final int max = 15;
    private static final int min = 0;
    private int diffusionCount;
    private Random random = new Random();
    private int randomDiffuseTick;
    private boolean syncd;

    public List<PotionEffect> potionEffects = new ArrayList<PotionEffect>();

    public BasicGasTileEntity()
    {
        this(Naming.basicGas, max);
        syncd = false;
    }

    /**
     * Create a new TileEntity instance
     *
     * @param gasName
     * @param diffusionCount
     */
    public BasicGasTileEntity(String gasName, int diffusionCount)
    {
        super(gasName);

        potionEffects.add(new PotionEffect(16, 2));
        setDiffusionCount(diffusionCount);
        resetRandomDiffuseTick();
    }

    /**
     * Reset the random diffusion tick
     *
     * @TODO add config for these values
     */
    private void resetRandomDiffuseTick()
    {
        randomDiffuseTick = random.nextInt(15) + 20;
    }

    /**
     * Set the diffusion count from any given int
     *
     * @param diffusionCount
     */
    private void setDiffusionCount(int diffusionCount)
    {
        if (diffusionCount >= min && diffusionCount <= max)
        {
            this.diffusionCount = diffusionCount;
        } else if (diffusionCount < min)
        {
            this.diffusionCount = min;
        } else if (diffusionCount > max)
        {
            this.diffusionCount = max;
        }
    }

    /**
     * Get the diffusion count from the block meta
     */
    private void syncDiffusionCount()
    {
        diffusionCount = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        syncd = true;
    }

    /**
     * Apply the gas's potion effects
     *
     * @param entity
     */
    public void applyGasEffects(EntityLivingBase entity)
    {
        Iterator potionEffectsIterator = potionEffects.iterator();
        while (potionEffectsIterator.hasNext())
        {
            entity.addPotionEffect((PotionEffect) potionEffectsIterator.next());
        }
    }

    @Override
    public void updateEntity()
    {
        if (!syncd)
        {
            syncDiffusionCount();
        }
        if (diffusionCount > min && diffusionCount <= max)
        {
            if (this.randomDiffuseTick <= 0)
            {
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
                {
                    int x = xCoord + direction.offsetX;
                    int y = yCoord + direction.offsetY;
                    int z = zCoord + direction.offsetZ;
                    Block block = this.worldObj.getBlock(x, y, z);

                    if (block != null)
                    {
                        if (block.equals(Blocks.air))
                        {
                            worldObj.setBlock(x, y, z, BlockRegistry.basicGas, diffusionCount - decrease, 3);
                            worldObj.setTileEntity(x, y, z, new BasicGasTileEntity(Naming.basicGas, diffusionCount - decrease));

                            syncd = false;
                        }
                    }
                }
                resetRandomDiffuseTick();
            } else
            {
                this.randomDiffuseTick--;
            }
        }
    }
}
