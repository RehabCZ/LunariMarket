package cz.lunari.lunarimarket;

import cz.lunari.lunarimarket.handlers.ConfigHandler;
import cz.lunari.lunarimarket.handlers.DatabaseHandler;
import cz.lunari.lunarimarket.handlers.HooksHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class LunariMarket extends JavaPlugin {

    private static LunariMarket instance;

    @Override
    public void onLoad(){
        /* Construct instance */
        instance = this;

        /* Initialize config file */
        ConfigHandler.initConfig();

        /* Initialize Hooks */
        HooksHandler pluginHook = new HooksHandler();
        pluginHook.initHooks();
    }

    @Override
    public void onEnable() {

        /* Initialize MySQL connection */
        DatabaseHandler.initializeConnection();

        /* Hello message */
        sendConsole(ConfigHandler.getConfigString("messages.enabled"));
    }

    @Override
    public void onDisable() {

        /* Close MySQL connection */
        DatabaseHandler.closeConnection();

        /* Bye message */
        sendConsole(ConfigHandler.getConfigString("messages.disabled"));

        /* Deconstruct instance */
        instance = null;
    }

    /* Instance getter */
    public static LunariMarket getInstance(){ return instance; }

    /* Console send method */
    public static void sendConsole(String message)
    {
        instance.getServer().getConsoleSender().sendMessage("[LunariMarket] " + message);
    }
}
