package cz.lunari.lunarimarket;

import cz.lunari.lunarimarket.config.Config;
import cz.lunari.lunarimarket.handlers.DatabaseHandler;
import cz.lunari.lunarimarket.handlers.HooksHandler;
import cz.lunari.lunarimarket.utils.ChatUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class LunariMarket extends JavaPlugin {

    private static LunariMarket instance;

    DatabaseHandler dbHandler;
    HooksHandler pluginHook;
    Config config;

    @Override
    public void onLoad() {
        /* Construct instance */
        instance = this;

        /* Initialize config file */
        config = new Config();
        config.initConfig();

        /* Initialize Hooks */
        pluginHook = new HooksHandler();
        pluginHook.initHooks();
    }

    @Override
    public void onEnable() {

        /* Initialize MySQL connection */
        dbHandler = new DatabaseHandler();
        dbHandler.initializeConnection();

        /* Hello message */
        ChatUtils.logConsole(Config.getConfigString("messages.enabled"));
    }

    @Override
    public void onDisable() {

        /* Close MySQL connection */
        dbHandler.closeConnection();

        /* Bye message */
        ChatUtils.logConsole(Config.getConfigString("messages.disabled"));

        /* Deconstruct instance */
        instance = null;
    }

    /* Instance getter */
    public static LunariMarket getInstance() {
        return instance;
    }
}
