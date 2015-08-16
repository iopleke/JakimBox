package jakimbox.prefab.gui;

import jakimbox.prefab.container.BasicContainer;
import jakimbox.prefab.tileEntity.BasicTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Minimalistic GUI using GUIContainer
 *
 * @author jakimfett
 */
public abstract class BasicGUI extends GuiContainer
{
    /**
     * Texture location for rendering the GUI background.
     */
    private ResourceLocation texture;

    /**
     * Texture coordinates for the background
     */
    protected int textureX, textureY;

    public BasicGUI(String modID, InventoryPlayer inventoryPlayer, BasicTileEntity tileEntity, World world, int width, int height)
    {
        super(new BasicContainer(inventoryPlayer, tileEntity));

        texture = new ResourceLocation(modID, "textures/gui/" + tileEntity.name.toLowerCase() + "GUIBackground.png");

        this.textureX = 0;
        this.textureY = 0;

        xSize = width;
        ySize = height;
    }

    /**
     * Bind the texture for rendering.
     */
    protected void bindGUITexture()
    {
        mc.renderEngine.bindTexture(texture);
    }

    /**
     * Draw the GUI background layer
     */
    protected void drawGUIBackground()
    {
        drawTexturedModalRect(getGUIOffsetX(), getGUIOffsetY(), textureX, textureY, xSize, ySize);
    }

    /**
     * Calculate the offset for centering the GUI.
     *
     * @return int
     */
    protected int getGUIOffsetX()
    {
        return (int) (width - xSize) / 2;
    }

    /**
     * Calculate the offset for centering the GUI.
     *
     * @return int
     */
    protected int getGUIOffsetY()
    {
        return (int) (height - ySize) / 2;
    }

    /**
     * Draw the background layer
     *
     * @param opacity
     * @param mouseX
     * @param mouseY
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mouseX, int mouseY)
    {
        bindGUITexture();
        drawGUIBackground();
    }
}
