package jakimbox.proxy.client;

import com.mia.craftstudio.minecraft.AnimationManager;
import com.mia.craftstudio.minecraft.CSMCConnector;
import com.mia.craftstudio.minecraft.client.CSClientEventHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import jakimbox.box.BoxTileEntity;
import jakimbox.prefab.render.RenderProp;
import jakimbox.prefab.render.RenderPropInv;
import jakimbox.proxy.CommonProxyBase;
import jakimbox.registry.BlockRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 *
 * @author jakimfett
 */
public class ClientProxyBase extends CommonProxyBase
{
    @Override
    public void registerRenderers()
    {
        RENDER_ID = RenderingRegistry.getNextAvailableRenderId();

        ClientRegistry.bindTileEntitySpecialRenderer(BoxTileEntity.class, new RenderProp());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.boxBlock), new RenderPropInv());

        CSClientEventHandler.register();
        AnimationManager.INSTANCE.ordinal();
        CSMCConnector.INSTANCE.ordinal();
    }
}
