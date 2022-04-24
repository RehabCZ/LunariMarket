package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.listeners.MenuClickEvent;
import cz.lunari.lunarimarket.utils.ChatUtils;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ListenerHandler {

    private final ArrayList<Listener> listeners = new ArrayList<>();

    public ListenerHandler() {
        listeners.add(new MenuClickEvent());
    }

    public void initListeners() {
        for (int i = 0; i < getListener().size(); i++) {
            ChatUtils.logConsole(
                    "&7Event listener&2 " + getListener().get(i).getClass().getSimpleName() + " &7was successfully initialized."
            );
            try {
                LunariMarket.getInstance().getServer().getPluginManager().registerEvents(getListener().get(i).getClass().getConstructor().newInstance(), LunariMarket.getInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Listener> getListener() {
        return listeners;
    }
}
