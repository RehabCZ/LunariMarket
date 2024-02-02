package cz.lunari.lunarimarket.config;

import com.google.gson.JsonObject;
import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.managers.JsonManager;

public class Localization extends JsonManager {

    public Localization(LunariMarket plugin) {
        super(plugin);
    }

    @Override
    protected String fileName() {
        return "messages.json";
    }

    @Override
    protected void loadDefaults() {
        data = new JsonObject();

        JsonObject messages = new JsonObject();
        messages.addProperty("enabled","&2Plugin was successfully loaded.");
        messages.addProperty("disabled","&2Plugin was successfully unloaded.");

        JsonObject inventory = new JsonObject();
        inventory.addProperty("menu_title","&6&lLunariMarket &7GUI");
        inventory.addProperty("menu_create_shop", "&a&lCreate shop");
        inventory.addProperty("menu_create_vault", "&a&lCreate vault");
        inventory.addProperty("menu_list_shops", "&a&lList your shops");
        inventory.addProperty("menu_exit", "&c&lExit menu");

        data.add("messages", messages);
        data.add("inventory",inventory);
    }
}
