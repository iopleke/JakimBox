package jakimbox;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * Configuration class for JakimBox
 *
 * @author jakimfett
 */
public class Config
{
    public static Configuration config;
    public static String configPrefix = "config/jakimfett/";
    public static String CATEGORY_DEBUGGING = "debugging";
    public static String CATEGORY_GASES = "gases";
    public static String CATEGORY_PERFORMANCE = "performance";

    // Enable or disable extra debug info and logging
    public static boolean debugMode = true;

    // Adjust the diffusion rate of gases
    public static int gasDiffusionRate = 1;

    // Maximum and minimum distance from source block for gas diffusion mechanic
    public static int gasDiffusionRadiusMax = 16;
    // Minimum distance intentionally left unconfigurable by the end user
    public static int gasDiffusionRadiusMin = 0;

    // Tick minimum and maximum random value for gas updates
    public static int gasUpdateTickMin = 20;
    public static int gasUpdateTickRandom = 35;

    public static int furnaceGUIUpdatePacketFrequency = 40;
    public static int maxUpdateTickCount = 100;

    public static void init()
    {
        if (config == null)
        {
            config = new Configuration(new File(Config.configPrefix + "JakimBox.cfg"));
            loadConfig();
        }
    }

    private static void loadConfig()
    {
        Property prop;
        List<String> configList = new ArrayList<String>();

        //config.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, StatCollector.translateToLocal("config.general.description"));
        config.addCustomCategoryComment(Config.CATEGORY_DEBUGGING, StatCollector.translateToLocal("config.debugging.description"));
        config.addCustomCategoryComment(Config.CATEGORY_GASES, StatCollector.translateToLocal("config.gases.description"));
        config.addCustomCategoryComment(Config.CATEGORY_PERFORMANCE, StatCollector.translateToLocal("config.performance.description"));

        prop = config.get(Config.CATEGORY_DEBUGGING, "debugMode", Config.debugMode);
        prop.comment = StatCollector.translateToLocal("config.debugging.debugMode.description");
        prop.setLanguageKey("config.debugging.debugMode.name");
        debugMode = prop.getBoolean();
        configList.add(prop.getName());

        prop = config.get(Config.CATEGORY_GASES, "gasDiffusionRate", Config.gasDiffusionRate);
        prop.setMinValue(1);
        prop.comment = StatCollector.translateToLocal("config.gases.gasDiffusionRate.description");
        prop.setLanguageKey("config.gases.gasDiffusionRate.name");
        gasDiffusionRate = prop.getInt();
        configList.add(prop.getName());

        prop = config.get(Config.CATEGORY_GASES, "gasDiffusionRadiusMax", Config.gasDiffusionRadiusMax);
        prop.comment = StatCollector.translateToLocal("config.gases.gasDiffusionRadiusMax.description");
        prop.setLanguageKey("config.gases.gasDiffusionRadiusMax.name");
        gasDiffusionRadiusMax = prop.getInt();
        configList.add(prop.getName());

        prop = config.get(Config.CATEGORY_GASES, "gasUpdateTickMin", Config.gasUpdateTickMin);
        prop.setMinValue(1);
        prop.comment = StatCollector.translateToLocal("config.gases.gasUpdateTickMin.description");
        prop.comment += "\n" + StatCollector.translateToLocal("config.gases.gasUpdateTickMin.explanation");
        prop.setLanguageKey("config.gases.gasUpdateTickMin.name");
        gasUpdateTickMin = prop.getInt();
        configList.add(prop.getName());

        prop = config.get(Config.CATEGORY_GASES, "gasUpdateTickRandom", Config.gasUpdateTickRandom);
        prop.setMinValue(3);
        prop.comment = StatCollector.translateToLocal("config.gases.gasUpdateTickRandom.description");
        prop.comment += "\n" + StatCollector.translateToLocal("config.gases.gasUpdateTickRandom.explanation");
        prop.setLanguageKey("config.gases.gasUpdateTickRandom.name");
        gasUpdateTickRandom = prop.getInt();
        configList.add(prop.getName());

        prop = Config.config.get(Config.CATEGORY_PERFORMANCE, "maxUpdateTickCount", Config.maxUpdateTickCount);
        prop.comment = StatCollector.translateToLocal("config.maxUpdateTickCount.description");
        prop.setLanguageKey("config.maxUpdateTickCount.tooltip");
        Config.maxUpdateTickCount = prop.getInt();
        configList.add(prop.getName());

        prop = Config.config.get(Config.CATEGORY_PERFORMANCE, "furnaceGUIUpdatePacketFrequency", Config.furnaceGUIUpdatePacketFrequency);
        prop.comment = StatCollector.translateToLocal("config.furnaceGUIUpdatePacketFrequency.description");
        prop.setLanguageKey("config.furnaceGUIUpdatePacketFrequency.tooltip");
        Config.furnaceGUIUpdatePacketFrequency = prop.getInt();
        configList.add(prop.getName());

        if (config.hasChanged())
        {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(JakimBox.modID))
        {
            loadConfig();
        }
    }
}
