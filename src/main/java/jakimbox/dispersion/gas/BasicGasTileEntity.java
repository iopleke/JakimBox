package jakimbox.dispersion.gas;

import jakimbox.prefab.tileEntity.BasicTileEntity;
import jakimbox.reference.Naming;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

/**
 * Basic TileEntity object for creating gasses
 *
 * @author jakimfett
 */
public class BasicGasTileEntity extends BasicTileEntity
{
    public List<PotionEffect> potionEffects = new ArrayList<PotionEffect>();

    public BasicGasTileEntity()
    {
        this(Naming.basicGas);
    }

    /**
     * Create a new TileEntity instance
     *
     * @param gasName
     */
    public BasicGasTileEntity(String gasName)
    {
        super(gasName);

        potionEffects.add(new PotionEffect(16, 2));
    }

    public void applyGasEffects(EntityLivingBase entity)
    {
        Iterator potionEffectsIterator = potionEffects.iterator();
        while (potionEffectsIterator.hasNext())
        {
            entity.addPotionEffect((PotionEffect) potionEffectsIterator.next());
        }
    }

}
