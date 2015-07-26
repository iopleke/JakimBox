package jakimbox.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import jakimbox.dispersion.gas.BasicGasBlock;
import jakimbox.dispersion.gas.BasicGasTileEntity;
import jakimbox.reference.Color.HexColor;
import jakimbox.reference.Naming;

/**
 * Registry class for blocks and TileEntities
 *
 * @author jakimfett
 */
public class BlockRegistry
{

    public static BasicGasBlock gasBlue;
    public static BasicGasBlock gasWhite;

    public static void init()
    {
        gasBlue = new BasicGasBlock(HexColor.BLUE);
        GameRegistry.registerBlock(gasBlue, "gasBlue");
        GameRegistry.registerTileEntity(BasicGasTileEntity.class, Naming.basicGas + "BlueTileEntity");

        gasWhite = new BasicGasBlock(HexColor.WHITE);
        GameRegistry.registerBlock(gasWhite, "gasWhite");
        GameRegistry.registerTileEntity(BasicGasTileEntity.class, Naming.basicGas + "WhiteTileEntity");
    }
}
