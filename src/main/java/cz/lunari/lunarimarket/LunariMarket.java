package cz.lunari.lunarimarket;

import cz.lunari.lunarimarket.managers.*;
import cz.lunari.lunarimarket.objects.json.Localization;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@Getter
public final class LunariMarket extends JavaPlugin {

    @Getter
    private static LunariMarket instance;

    private Localization localization;

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
        localization = new Localization(this);

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
        ChatMessageUtils.logConsole(localization.getString("messages","enabled"));
    }

    @Override
    public void onDisable() {
        /* Close MySQL connection */
        databaseManager.closeConnection();

        /* Bye message */
        ChatMessageUtils.logConsole(localization.getString("messages","disabled"));

        /* Deconstruct instance */
        instance = null;
    }
}
