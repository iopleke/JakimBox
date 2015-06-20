package jakimbox.dispersion.gas;

import jakimbox.prefab.tileEntity.BasicTileEntity;
import jakimbox.reference.Corrodes;
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
    private static final int min = 2;
    private List<Corrodes> corrosiveness = new ArrayList<Corrodes>();
    private int diffusionCount;
    private Random random = new Random();
    private int randomDiffuseTick;
    private boolean syncd;

    public List<Block> corrodibleBlocks = new ArrayList<Block>();
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
        corrosiveness.add(Corrodes.ALL);

        updateCorrosiveness();
    }

    /**
     * Replace a block in a given direction with the gas
     *
     * @param direction
     */
    private void corrodeBlock(ForgeDirection direction)
    {
        int x = xCoord + direction.offsetX;
        int y = yCoord + direction.offsetY;
        int z = zCoord + direction.offsetZ;
        int meta = Math.max(diffusionCount - decrease, min);
        worldObj.setBlock(x, y, z, BlockRegistry.basicGas, meta, 3);
        worldObj.setTileEntity(x, y, z, new BasicGasTileEntity(Naming.basicGas, meta));
    }

    private void diffuseGas(int x, int y, int z)
    {
        int meta = Math.max(diffusionCount - decrease, min);
        this.setGas(x, y, z, meta);
    }

    /**
     * Equalize gas "pressure"
     */
    private void equalize()
    {
        if (diffusionCount > 0)
        {
            diffuseGas(xCoord, yCoord, zCoord);
        } else
        {
            removeGas(xCoord, yCoord, zCoord);
        }
    }

    private void removeGas(int x, int y, int z)
    {
        worldObj.setBlockToAir(x, y, z);
        worldObj.setTileEntity(x, y, z, null);
    }

    /**
     * Reset the random diffusion tick
     *
     * @TODO add config for these values
     */
    private void resetRandomDiffuseTick()
    {
        randomDiffuseTick = random.nextInt(15) + 5;
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

    private void setGas(int x, int y, int z, int meta)
    {
        worldObj.setBlock(x, y, z, BlockRegistry.basicGas, meta, 3);
        worldObj.setTileEntity(x, y, z, new BasicGasTileEntity(Naming.basicGas, meta));
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
     * Set corrodible blocks based on the corrosiveness of the gas
     *
     * @default: only replace air
     * @see jakimbox.reference.Corrodes
     */
    private void updateCorrosiveness()
    {
        corrodibleBlocks.clear();

        corrodibleBlocks.add(Blocks.air);
        if (corrosiveness.contains(Corrodes.CARBON) || corrosiveness.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.coal_block);
            corrodibleBlocks.add(Blocks.coal_ore);
            corrodibleBlocks.add(Blocks.diamond_block);
            corrodibleBlocks.add(Blocks.diamond_ore);
        }
        if (corrosiveness.contains(Corrodes.COBBLE) || corrosiveness.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.cobblestone);
            corrodibleBlocks.add(Blocks.cobblestone_wall);
            corrodibleBlocks.add(Blocks.stone_stairs);
        }
        if (corrosiveness.contains(Corrodes.DIRT) || corrosiveness.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.dirt);
            corrodibleBlocks.add(Blocks.grass);
        }
        if (corrosiveness.contains(Corrodes.METAL) || corrosiveness.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.anvil);
            corrodibleBlocks.add(Blocks.iron_bars);
            corrodibleBlocks.add(Blocks.iron_block);
            corrodibleBlocks.add(Blocks.iron_door);
            corrodibleBlocks.add(Blocks.iron_ore);
        }
        if (corrosiveness.contains(Corrodes.MINERAL) || corrosiveness.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.gravel);
            corrodibleBlocks.add(Blocks.sand);
            corrodibleBlocks.add(Blocks.clay);
        }
        if (corrosiveness.contains(Corrodes.VEGETATION) || corrosiveness.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.cactus);
            corrodibleBlocks.add(Blocks.deadbush);
            corrodibleBlocks.add(Blocks.double_plant);
            corrodibleBlocks.add(Blocks.leaves);
            corrodibleBlocks.add(Blocks.leaves2);
            corrodibleBlocks.add(Blocks.red_flower);
            corrodibleBlocks.add(Blocks.tallgrass);
            corrodibleBlocks.add(Blocks.yellow_flower);
        }
        if (corrosiveness.contains(Corrodes.STONE) || corrosiveness.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.stone);
            corrodibleBlocks.add(Blocks.stone_pressure_plate);
            corrodibleBlocks.add(Blocks.stone_button);
            corrodibleBlocks.add(Blocks.stone);
        }
        if (corrosiveness.contains(Corrodes.WOOD) || corrosiveness.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.acacia_stairs);
            corrodibleBlocks.add(Blocks.birch_stairs);
            corrodibleBlocks.add(Blocks.bookshelf);
            corrodibleBlocks.add(Blocks.chest);
            corrodibleBlocks.add(Blocks.crafting_table);
            corrodibleBlocks.add(Blocks.dark_oak_stairs);
            corrodibleBlocks.add(Blocks.double_wooden_slab);
            corrodibleBlocks.add(Blocks.fence);
            corrodibleBlocks.add(Blocks.fence_gate);
            corrodibleBlocks.add(Blocks.lever);
            corrodibleBlocks.add(Blocks.log);
            corrodibleBlocks.add(Blocks.planks);
            corrodibleBlocks.add(Blocks.trapdoor);
            corrodibleBlocks.add(Blocks.trapped_chest);
        }
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
        if (this.randomDiffuseTick <= 0)
        {
            if (diffusionCount > min && diffusionCount <= max)
            {
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
                {
                    int x = xCoord + direction.offsetX;
                    int y = yCoord + direction.offsetY;
                    int z = zCoord + direction.offsetZ;
                    Block block = this.worldObj.getBlock(x, y, z);

                    if (block != null)
                    {
                        if (corrodibleBlocks.isEmpty())
                        {
                            updateCorrosiveness();
                        }
                        if (corrodibleBlocks.contains(block))
                        {
                            corrodeBlock(direction);
                            equalize();

                            syncd = false;
                        } else if (block.equals(BlockRegistry.basicGas))
                        {
                            int sideBlockMeta = worldObj.getBlockMetadata(x, y, z);
                            int blockMeta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                            if (sideBlockMeta <= blockMeta - 2)
                            {
                                setGas(xCoord, yCoord, zCoord, (blockMeta + sideBlockMeta) / 2);
                                setGas(x, y, z, (blockMeta + sideBlockMeta) / 2);
                            }
                        }
                    }
                }
            } else
            {
                removeGas(xCoord, yCoord, zCoord);
            }
            resetRandomDiffuseTick();
        } else
        {
            this.randomDiffuseTick--;
        }

    }
}
