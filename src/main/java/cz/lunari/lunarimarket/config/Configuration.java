package cz.lunari.lunarimarket.config;

import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.objects.YamlObject;
import org.bukkit.configuration.ConfigurationSection;

public class Configuration extends YamlObject {

    public Configuration(LunariMarket plugin) {
        super(plugin);
    }

    @Override
    protected String filename() {
        return "config";
    }

    @Override
    protected void loadDefaults() {
        ConfigurationSection database = config.createSection("database");
        database.set("dbHost", "localhost");
        database.set("dbPort", 3306);
        database.set("dbName", "lunarimarket");
        database.set("dbUser", "");
        database.set("dbPass", "");
    }
}
