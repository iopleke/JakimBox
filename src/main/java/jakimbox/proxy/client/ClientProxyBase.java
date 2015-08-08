package jakimbox.proxy.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import jakimbox.box.BoxTileEntity;
import jakimbox.box.BoxTileEntityRenderer;
import jakimbox.prefab.render.BasicItemRenderer;
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

        BoxTileEntityRenderer boxModelRenderer = new BoxTileEntityRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(BoxTileEntity.class, boxModelRenderer);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.boxBlock),
            new BasicItemRenderer(boxModelRenderer, new BoxTileEntity()));
    }
}
