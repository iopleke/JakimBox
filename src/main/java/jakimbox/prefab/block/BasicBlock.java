package jakimbox.prefab.block;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/*
 * Extendable class for simple non-container blocks
 *
 * @author jakimfett
 */
public abstract class BasicBlock extends Block
{

    /**
     * Unnamed blocks are given a default name
     *
     * @param modID the mod's ID
     */
    public BasicBlock(String modID)
    {
        this(modID, "basicBlock");
    }

    /**
     * Create a basic block with a given name
     *
     * @param modID     the mod's ID
     * @param blockName unlocalized name of the block
     */
    public BasicBlock(String modID, String blockName)
    {
        this(modID, blockName, Material.grass, Block.soundTypeGrass);
    }

    /**
     *
     * @param modID     the mod's ID
     * @param blockName unlocalized name of the block
     * @param material  material the block is made from, used for step sounds
     */
    public BasicBlock(String modID, String blockName, Material material)
    {
        this(modID, blockName, material, material == Material.cloth ? Block.soundTypeCloth : material == Material.wood ? Block.soundTypeWood : material == Material.glass ? Block.soundTypeGlass : material == Material.iron ? Block.soundTypeMetal : Block.soundTypeGrass);
    }

    /**
     *
     * @param modID     the mod's ID
     * @param blockName unlocalized name of the block
     * @param material  material the block is made from
     * @param soundType soundtype for the block
     */
    public BasicBlock(String modID, String blockName, Material material, SoundType soundType)
    {
        super(material);
        setBlockName(blockName);
        setStepSound(soundType);
        textureName = modID + ":" + blockName + "Icon";
    }

    /**
     * Register the block icon from the texture name
     *
     * @param iconRegister
     */
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(textureName);
    }
}
