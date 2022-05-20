package cz.lunari.lunarimarket.utils;

import cz.lunari.lunarimarket.LunariMarket;
import org.bukkit.ChatColor;

public class ChatMessageUtils {

    public static String translateColors(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /* Console send method */
    public static void logConsole(String message) {
        LunariMarket.getInstance().getServer().getConsoleSender().sendMessage("[LunariMarket] " + translateColors(message));
    }
}
