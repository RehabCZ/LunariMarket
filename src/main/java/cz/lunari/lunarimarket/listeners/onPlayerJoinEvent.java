package cz.lunari.lunarimarket.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().sendMessage("This is test message");
    }

}
