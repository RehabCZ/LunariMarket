package cz.lunari.lunarimarket;

import cz.lunari.lunarimarket.managers.ConfigManager;
import cz.lunari.lunarimarket.managers.CommandManager;
import cz.lunari.lunarimarket.managers.DatabaseManager;
import cz.lunari.lunarimarket.managers.IntegrationManager;
import cz.lunari.lunarimarket.managers.ListenerManager;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@Getter
public final class LunariMarket extends JavaPlugin {

    @Getter
    private static LunariMarket instance;

    private ConfigManager configManager;

    private DatabaseManager databaseManager;
    private IntegrationManager integrationManager;
    private ListenerManager listenerManager;

    @Override
    public void onLoad() {
        /* Construct instance */
        instance = this;

        /* Initialize configManager file */
        configManager = new ConfigManager(this);

        /* Initialize Hooks */
        integrationManager = new IntegrationManager();
    }

    @Override
    public void onEnable() {
        /* Initialize Events Listener handler */
        listenerManager = new ListenerManager(this);

        /* Initialize MySQL connection */
        databaseManager = new DatabaseManager();

        /* Initialize Command handler */
        Objects.requireNonNull(getCommand("lunarimarket")).setExecutor(new CommandManager());

        /* Hello message */
        ChatMessageUtils.logConsole(ConfigManager.getString("messages.enabled"));
    }

    @Override
    public void onDisable() {
        /* Close MySQL connection */
        databaseManager.closeConnection();

        /* Bye message */
        ChatMessageUtils.logConsole(ConfigManager.getString("messages.disabled"));

        /* Deconstruct instance */
        instance = null;
    }
}
