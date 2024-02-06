package cz.lunari.lunarimarket.config;

import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.objects.YamlObject;
import org.bukkit.configuration.ConfigurationSection;

public class Localization extends YamlObject {

    public Localization(LunariMarket plugin) {
        super(plugin);
    }

    @Override
    protected String filename() {
        return "messages.json";
    }

    @Override
    protected void loadDefaults() {
        ConfigurationSection messages = config.createSection("messages");
        messages.set("enabled", "&2Plugin was successfully loaded.");
        messages.set("disabled", "&2Plugin was successfully unloaded.");

        ConfigurationSection inventory = config.createSection("inventory");
        inventory.set("menu_title", "&6&lLunariMarket &7GUI");
        inventory.set("menu_create_shop", "&a&lCreate shop");
        inventory.set("menu_create_vault", "&a&lCreate vault");
        inventory.set("menu_list_shops", "&a&lList your shops");
        inventory.set("menu_exit", "&c&lExit menu");
    }
}
