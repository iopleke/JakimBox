package jakimbox.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;

/**
 * Notification and message helper class
 *
 * @author jakimfett
 */
public class NotificationHelper
{
    /**
     * Display a message in chat for a specific player
     *
     * @param player  the player to display the message for
     * @param message string containing the message
     */
    public static void sendPlayerChatMessage(EntityPlayer player, String message)
    {
        player.addChatMessage(new TextComponentString(I18n.translateToLocal(message)));
    }
}
