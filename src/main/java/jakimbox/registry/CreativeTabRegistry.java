package jakimbox.registry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jakimbox.JakimBox;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @todo figure out how to abstract this
 * @author jakimfett
 */
public class CreativeTabRegistry
{
    public static CreativeTab TAB_JAKIMBOX = new CreativeTab(JakimBox.modID);

    public static void init()
    {
        TAB_JAKIMBOX.setIcon(Blocks.noteblock);
    }

    /**
     * Improved CreativeTab implementation which allows the icon item/block to have metaData
     */
    private static class CreativeTab extends CreativeTabs
    {
        private ItemStack iconItemStack;

        public CreativeTab(String label, Item iconItem)
        {
            this(label, new ItemStack(iconItem));
        }

        public CreativeTab(String label, Item iconItem, int meta)
        {
            this(label, new ItemStack(iconItem, 1, meta));
        }

        public CreativeTab(String label, Block iconBlock)
        {
            this(label, new ItemStack(iconBlock));
        }

        public CreativeTab(String label, Block iconBlock, int meta)
        {
            this(label, new ItemStack(iconBlock, 1, meta));
        }

        public void setIcon(Item iconItem)
        {
            this.iconItemStack = new ItemStack(iconItem);
        }

        public void setIcon(Item iconItem, int meta)
        {
            this.iconItemStack = new ItemStack(iconItem, 1, meta);
        }

        public void setIcon(Block iconBlock)
        {
            this.iconItemStack = new ItemStack(iconBlock);
        }

        public void setIcon(Block iconBlock, int meta)
        {
            this.iconItemStack = new ItemStack(iconBlock, 1, meta);
        }

        public CreativeTab(String label, ItemStack iconItemStack)
        {
            super(label);
            this.iconItemStack = iconItemStack;
        }

        public CreativeTab(String label)
        {
            super(label);
        }

        @SideOnly(Side.CLIENT)
        @Override
        public Item getTabIconItem()
        {
            return iconItemStack.getItem();
        }

        @SideOnly(Side.CLIENT)
        @Override
        public ItemStack getIconItemStack()
        {
            return this.iconItemStack;
        }
    }
}
