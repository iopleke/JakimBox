package jakimbox.helper;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

/**
 * Static helper methods for logging
 *
 * @author jakimfett
 */
public class LogHelper
{
    public static boolean debugMode = false;
    public static String modID = "JakimBox";

    /**
     * Used for logging when debug is turned on in the config
     *
     * @param obj object to log
     */
    public static void debug(Object obj)
    {
        if (debugMode)
        {
            log(Level.INFO, obj);
        } else
        {
            log(Level.DEBUG, obj);
        }
    }

    /**
     * Used for logging an exception
     *
     * @param obj       object to log
     * @param exception exception to log
     * @param level     level of the log
     */
    public static void exception(Object obj, Throwable exception, Level level)
    {
        FMLLog.log(modID, level, exception, String.valueOf(obj));
    }

    /**
     * Used for logging an exception
     *
     * @param exception exception to log
     * @param level     level of the log
     */
    public static void exception(Throwable exception, Level level)
    {
        FMLLog.log(modID, level, exception, exception.toString());
    }

    /**
     * Used for logging a warning
     *
     * @param obj
     */
    public static void warn(Object obj)
    {
        log(Level.WARN, obj);
    }

    /**
     * Used for logging in any case
     *
     * @param obj object to log
     */
    public static void info(Object obj)
    {
        log(Level.INFO, obj);
    }

    /**
     * General logging method
     *
     * @param level Level of the log
     * @param obj   object to log
     */
    public static void log(Level level, Object obj)
    {
        FMLLog.log(modID, level, String.valueOf(obj));
    }
}
