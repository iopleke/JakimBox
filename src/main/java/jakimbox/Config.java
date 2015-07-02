package jakimbox;

import net.minecraftforge.common.config.Configuration;

/**
 * Configuration class for JakimBox
 *
 * @author jakimfett
 */
public class Config
{
    public static Configuration config;

    // Enable or disable extra debug info and logging
    public static boolean debugMode = true;

    // Adjust the diffusion rate of gases
    public static int gasDiffusionRate = 3;

    // Maximum and minimum distance from source block for gas diffusion mechanic
    public static int gasDiffusionRadiusMax = 16;
    public static int gasDiffusionRadiusMin = 0;

    // Tick minimum and maximum random value for gas updates
    public static int gasUpdateTickMin = 20;
    public static int gasUpdateTickRandom = 35;
}
