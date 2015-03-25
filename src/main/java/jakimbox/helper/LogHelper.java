package jakimbox.helper;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

/**
 *
 * @author jakimfett
 */
public class LogHelper
{
    public boolean debugMode = false;
    public String modID;

    public LogHelper(String modID)
    {
        this.modID = modID;
    }

    /**
     * Used for logging when debug is turned on in the config
     *
     * @param obj object to log
     */
    public void debug(Object obj)
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
    public void exception(Object obj, Throwable exception, Level level)
    {
        FMLLog.log(modID, level, exception, String.valueOf(obj));
    }

    /**
     * Used for logging an exception
     *
     * @param exception exception to log
     * @param level     level of the log
     */
    public void exception(Throwable exception, Level level)
    {
        FMLLog.log(modID, level, exception, exception.toString());
    }

    /**
     * Used for logging a warning
     *
     * @param obj
     */
    public void warn(Object obj)
    {
        log(Level.WARN, obj);
    }

    /**
     * Used for logging in any case
     *
     * @param obj object to log
     */
    public void info(Object obj)
    {
        log(Level.INFO, obj);
    }

    /**
     * General logging method
     *
     * @param level Level of the log
     * @param obj   object to log
     */
    public void log(Level level, Object obj)
    {
        FMLLog.log(modID, level, String.valueOf(obj));
    }
}
