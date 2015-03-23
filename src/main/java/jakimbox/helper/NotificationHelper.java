package jakimbox.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

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
        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal(message)));
    }
}
