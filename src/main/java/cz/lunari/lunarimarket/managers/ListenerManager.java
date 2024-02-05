package cz.lunari.lunarimarket.managers;

import com.google.common.collect.Lists;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ListenerManager {

    private final List<Listener> listeners = Lists.newArrayList();

    private final JavaPlugin instance;

    public ListenerManager(JavaPlugin plugin) {
        instance = plugin;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void init() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        for (Listener listener : listeners) {
            ChatMessageUtils.logConsole(
                    "&7Event listener&2 " + listener.getClass().getSimpleName() + " &7was successfully initialized."
            );

            pluginManager.registerEvents(listener, instance);
        }
    }
}
