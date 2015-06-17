package jakimbox.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import jakimbox.JakimBox;
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

    public static BasicGasBlock basicGas;

    public static void init()
    {
        basicGas = new BasicGasBlock();
        GameRegistry.registerBlock(basicGas, JakimBox.modID);
        GameRegistry.registerTileEntity(BasicGasTileEntity.class, Naming.basicGas + "TileEntity");
    }
}
