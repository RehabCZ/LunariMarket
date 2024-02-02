package cz.lunari.lunarimarket.config;

import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.managers.YamlManager;
import org.bukkit.configuration.ConfigurationSection;

public class Configuration extends YamlManager {

    public Configuration(LunariMarket plugin) {
        super(plugin);
    }

    @Override
    protected String filename() {
        return "config.yml";
    }

    @Override
    protected void loadDefaults() {
        ConfigurationSection integration = config.createSection("integration");
        integration.set("WorldGuard", true);

        ConfigurationSection database = config.createSection("database");
        database.set("dbHost", "localhost");
        database.set("dbPort", 3306);
        database.set("dbName", "lunarimarket");
        database.set("dbUser", "");
        database.set("dbPass", "");
    }
}
