package jakimbox.dispersion.gas;

import jakimbox.Config;
import jakimbox.JakimBox;
import jakimbox.helper.ColorHelper;
import jakimbox.helper.LogHelper;
import jakimbox.prefab.tileEntity.BasicTileEntity;
import jakimbox.reference.Corrodes;
import jakimbox.reference.Naming;
import jakimbox.registry.BlockRegistry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Basic TileEntity object for creating gasses
 *
 * @author jakimfett
 */
public class BasicGasTileEntity extends BasicTileEntity
{
    private int buoyancy;
    private int color;
    private int density;
    private int radius;
    private int radiusCount;
    private int randomDiffuseTick;
    private boolean syncd = false;

    /**
     * @TODO - move corrosion registry to gas block registration class
     */
    public ArrayList<Corrodes> corrodes = new ArrayList<Corrodes>();
    public List<Block> corrodibleBlocks = new ArrayList<Block>();

    public List<PotionEffect> potionEffects = new ArrayList<PotionEffect>();

    public BasicGasTileEntity(int color)
    {
        this(Naming.gas, 15, 15, 0, 100, color, new ArrayList<Corrodes>());
    }

    public BasicGasTileEntity(BasicGasTileEntity tileEntity)
    {
        this(Naming.gas, tileEntity.getRadius(), tileEntity.getRadiusCount() - tileEntity.getDecrease(), tileEntity.getBuoyancy(), tileEntity.getDensity() - Config.gasDiffusionRate, tileEntity.getColor(), tileEntity.getCorrodes());
    }

    /**
     * Create a new TileEntity instance
     *
     * @param gasName
     * @param radius
     * @param radiusCount
     * @param buoyancy
     * @param density
     * @param corrodes
     */
    public BasicGasTileEntity(String gasName, int radius, int radiusCount, int buoyancy, int density, int color, ArrayList<Corrodes> corrodes)
    {
        super(gasName);
        this.density = density;

        this.buoyancy = buoyancy;
        this.radius = radius;
        if (corrodes.isEmpty())
        {
            corrodes.add(Corrodes.NONE);
        }
        this.corrodes = corrodes;
        this.radiusCount = radiusCount;

        resetRandomDiffuseTick();
        updateCorrosiveness();

        potionEffects.add(new PotionEffect(16, 2));

        this.color = color;
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
        worldObj.setBlock(x, y, z, BlockRegistry.basicGasBlock, getAdjustedMeta(), 3);
        worldObj.setTileEntity(x, y, z, new BasicGasTileEntity(this));
    }

    private void diffuseGas(int x, int y, int z)
    {
        this.setGas(x, y, z, getAdjustedMeta());
    }

    /**
     * Equalize gas "pressure"
     */
    private void equalize()
    {
        if (radiusCount > Config.gasDiffusionRadiusMin)
        {
            diffuseGas(xCoord, yCoord, zCoord);
        } else
        {
            removeGas(xCoord, yCoord, zCoord);
        }
    }

    private int getAdjustedMeta()
    {
        return Math.max(getRadiusCount() - getDecrease(), Config.gasDiffusionRadiusMin);
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
        randomDiffuseTick = JakimBox.random.nextInt(Config.gasUpdateTickRandom) + Config.gasUpdateTickMin;
    }

    private void setGas(int x, int y, int z, int meta)
    {
        int updateTypeFlag = 3;
        worldObj.setBlock(x, y, z, BlockRegistry.basicGasBlock, meta, updateTypeFlag);
        worldObj.setTileEntity(x, y, z, new BasicGasTileEntity(this));
    }

    /**
     * Get the diffusion count from the block meta
     */
    private void syncDiffusionCount()
    {
        radiusCount = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
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
        if (corrodes.contains(Corrodes.CARBON) || corrodes.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.coal_block);
            corrodibleBlocks.add(Blocks.coal_ore);
            corrodibleBlocks.add(Blocks.diamond_block);
            corrodibleBlocks.add(Blocks.diamond_ore);
        }
        if (corrodes.contains(Corrodes.COBBLE) || corrodes.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.cobblestone);
            corrodibleBlocks.add(Blocks.cobblestone_wall);
            corrodibleBlocks.add(Blocks.stone_stairs);
        }
        if (corrodes.contains(Corrodes.DIRT) || corrodes.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.dirt);
            corrodibleBlocks.add(Blocks.grass);
        }
        if (corrodes.contains(Corrodes.METAL) || corrodes.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.anvil);
            corrodibleBlocks.add(Blocks.iron_bars);
            corrodibleBlocks.add(Blocks.iron_block);
            corrodibleBlocks.add(Blocks.iron_door);
            corrodibleBlocks.add(Blocks.iron_ore);
        }
        if (corrodes.contains(Corrodes.MINERAL) || corrodes.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.gravel);
            corrodibleBlocks.add(Blocks.sand);
            corrodibleBlocks.add(Blocks.clay);
        }
        if (corrodes.contains(Corrodes.VEGETATION) || corrodes.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.cactus);
            corrodibleBlocks.add(Blocks.carrots);
            corrodibleBlocks.add(Blocks.deadbush);
            corrodibleBlocks.add(Blocks.double_plant);
            corrodibleBlocks.add(Blocks.leaves);
            corrodibleBlocks.add(Blocks.leaves2);
            corrodibleBlocks.add(Blocks.potatoes);
            corrodibleBlocks.add(Blocks.red_flower);
            corrodibleBlocks.add(Blocks.reeds);
            corrodibleBlocks.add(Blocks.sapling);
            corrodibleBlocks.add(Blocks.tallgrass);
            corrodibleBlocks.add(Blocks.waterlily);
            corrodibleBlocks.add(Blocks.wheat);
            corrodibleBlocks.add(Blocks.yellow_flower);
        }
        if (corrodes.contains(Corrodes.STONE) || corrodes.contains(Corrodes.ALL))
        {
            corrodibleBlocks.add(Blocks.stone);
            corrodibleBlocks.add(Blocks.stone_pressure_plate);
            corrodibleBlocks.add(Blocks.stone_button);
            corrodibleBlocks.add(Blocks.stone);
        }
        if (corrodes.contains(Corrodes.WOOD) || corrodes.contains(Corrodes.ALL))
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

    /**
     * Get the gas buoyancy
     *
     * @return buoyancy
     */
    public int getBuoyancy()
    {
        return buoyancy;
    }

    /**
     * Get the the block color
     *
     * @return int
     */
    public int getColor()
    {
        return ColorHelper.getHexColorFromMeta(randomDiffuseTick % 16);
    }

    /**
     * Get the gas corrodes list
     *
     * @return corrodes
     */
    public ArrayList<Corrodes> getCorrodes()
    {
        return corrodes;
    }

    /**
     * Get the gas radius decrease
     *
     * @return decrease
     */
    public int getDecrease()
    {
        return Config.gasDiffusionRate;
    }

    public int getDensity()
    {
        return density;
    }

//    @Override
//    public Packet getDescriptionPacket()
//    {
//        writeToNBT(new NBTTagCompound());
//        MessageHandler.INSTANCE.sendToServer(new GasUpdateMessage(this));
//        return null;
//    }
    /**
     * Get the gas diffusion radius
     *
     * @return radius
     */
    public int getRadius()
    {
        return radius;
    }

    /**
     * Get the gas radius decrease
     *
     * @return decrease
     */
    public int getRadiusCount()
    {
        return radiusCount;
    }

    /**
     * Read saved values from NBT
     *
     * @param nbt
     */
    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        density = nbt.getInteger("density");
        color = nbt.getInteger("color");
    }

    /**
     * Set gas buoyancy relative to air, zero is neutral
     *
     * @param buoyancy -1 for heavier, 1 for lighter, 0 for neutral
     */
    public void setBuoyancy(int buoyancy)
    {
        this.buoyancy = buoyancy;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public void setDensity(int density)
    {
        this.density = density;
    }

    @Override
    public void updateEntity()
    {
        if (this.worldObj.isRemote)
        {
            LogHelper.debug("Client color: " + this.color);

        } else
        {
            LogHelper.debug("Server color: " + this.color);
        }

        if (!syncd)
        {
            syncDiffusionCount();
            getDescriptionPacket();
        }
        if (this.randomDiffuseTick <= 0)
        {
            if (radiusCount > Config.gasDiffusionRadiusMin && radiusCount <= Config.gasDiffusionRadiusMax)
            {
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
                {
                    if (direction != ForgeDirection.UP)
                    {
                        continue;
                    }
                    if (buoyancy > 0 && direction == ForgeDirection.DOWN)
                    {
                        continue;
                    } else if (buoyancy < 0 && direction == ForgeDirection.UP)
                    {
                        continue;
                    }

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
                        } else if (block.equals(BlockRegistry.basicGasBlock))
                        {
                            int sideBlockMeta = worldObj.getBlockMetadata(x, y, z);
                            int blockMeta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                            // @TODO - make high frequency gas block updates a config option
//                            if (sideBlockMeta < blockMeta)
//                            {
//                                setGas(xCoord, yCoord, zCoord, blockMeta - 1);
//                                setGas(x, y, z, sideBlockMeta + 1);
//                            }
                            if (sideBlockMeta < blockMeta - 1)
                            {
                                int newMeta = Math.round((blockMeta + sideBlockMeta) / 2);
                                setGas(xCoord, yCoord, zCoord, newMeta);
                                setGas(x, y, z, newMeta);
                            }
//                            else if (sideBlockMeta <= min && sideBlockMeta < blockMeta)
//                            {
//                                setGas(xCoord, yCoord, zCoord, blockMeta - 1);
//                                setGas(x, y, z, sideBlockMeta + 1);
//                            }
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

    /**
     * Save data to NBT
     *
     * @param nbt
     */
    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("density", density);
        nbt.setInteger("color", color);
    }
}
