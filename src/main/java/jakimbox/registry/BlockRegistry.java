package jakimbox.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import jakimbox.box.BoxBlock;
import jakimbox.dispersion.gas.BasicGasBlock;
import jakimbox.dispersion.gas.BasicGasTileEntity;
import jakimbox.reference.Naming;

/**
 * Registry class for blocks and TileEntities
 *
 * @author jakimfett
 */
public class BlockRegistry
{
    public static BoxBlock boxBlock;
    public static BasicGasBlock basicGasBlock;

    public static void init()
    {
        boxBlock = new BoxBlock();
        GameRegistry.registerBlock(boxBlock, Naming.box);
        GameRegistry.registerTileEntity(BasicGasTileEntity.class, "tile" + Naming.box);

        basicGasBlock = new BasicGasBlock(1);
        GameRegistry.registerBlock(basicGasBlock, Naming.gas);
        GameRegistry.registerTileEntity(BasicGasTileEntity.class, "tile" + Naming.gas);
    }
}
