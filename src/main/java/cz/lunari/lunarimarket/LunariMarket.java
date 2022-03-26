package cz.lunari.lunarimarket;

import cz.lunari.lunarimarket.handlers.ConfigHandler;
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

        /* Hello message */
        this.getServer().getConsoleSender().sendMessage(
                ConfigHandler.getConfigString("messages.enabled")
        );
    }

    @Override
    public void onDisable() {

        /* Save config file */
        ConfigHandler.saveConfig();

        /* Bye message */
        this.getServer().getConsoleSender().sendMessage(
                ConfigHandler.getConfigString("messages.disabled")
        );

        /* Deconstruct instance */
        instance = null;
    }

    /* Instance getter */
    public static LunariMarket getInstance(){ return instance; }
}
