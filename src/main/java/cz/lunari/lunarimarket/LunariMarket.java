package cz.lunari.lunarimarket;

import cz.lunari.lunarimarket.commands.AboutCommand;
import cz.lunari.lunarimarket.commands.HelpCommand;
import cz.lunari.lunarimarket.commands.MenuCommand;
import cz.lunari.lunarimarket.commands.ReloadCommand;
import cz.lunari.lunarimarket.config.Configuration;
import cz.lunari.lunarimarket.config.Localization;
import cz.lunari.lunarimarket.listeners.InventoryListener;
import cz.lunari.lunarimarket.managers.CommandManager;
import cz.lunari.lunarimarket.managers.DatabaseManager;
import cz.lunari.lunarimarket.managers.ListenerManager;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class LunariMarket extends JavaPlugin {
    @Getter
    private static LunariMarket instance;
    private Localization localization;
    private Configuration configuration;
    private DatabaseManager databaseManager;
    private ListenerManager listenerManager;
    private CommandManager commandManager;

    @Override
    public void onLoad() {
        /* Construct instance */
        instance = this;

        /* Initialize configManager file */
        configuration = new Configuration(this);
        localization = new Localization(this);
    }

    @Override
    public void onEnable() {
        /* Initialize Events Listener handler */
        listenerManager = new ListenerManager(this);
        listenerManager.addListener(new InventoryListener());
        listenerManager.init();

        /* Initialize MySQL connection */
        databaseManager = new DatabaseManager();

        /* Initialize Command handler */
        commandManager = new CommandManager(this, "lunarimarket");
        commandManager.addCommand(new AboutCommand());
        commandManager.addCommand(new MenuCommand());
        commandManager.addCommand(new HelpCommand());
        commandManager.addCommand(new ReloadCommand());

        /* Hello message */
        ChatMessageUtils.logConsole(localization.getString("messages.enabled"));
    }

    @Override
    public void onDisable() {
        /* Close MySQL connection */
        databaseManager.closeConnection();

        /* Bye message */
        ChatMessageUtils.logConsole(localization.getString("messages.disabled"));

        /* Deconstruct instance */
        instance = null;
    }
}
